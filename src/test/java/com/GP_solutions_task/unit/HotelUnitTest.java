package com.GP_solutions_task.unit;

import com.GP_solutions_task.HotelTestBuilder;
import com.bogdanlopatenko.constant.ExceptionConstant;
import com.bogdanlopatenko.dto.HotelFilterDto;
import com.bogdanlopatenko.dto.HotelSpecification;
import com.bogdanlopatenko.dto.brand.BrandRequestDto;
import com.bogdanlopatenko.dto.brand.BrandResponseDto;
import com.bogdanlopatenko.dto.brand.BrandShortResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelFullResponseDto;
import com.bogdanlopatenko.dto.hotel.HotelRequestDto;
import com.bogdanlopatenko.dto.hotel.HotelShortResponseDto;
import com.bogdanlopatenko.entity.Brand;
import com.bogdanlopatenko.entity.Hotel;
import com.bogdanlopatenko.exception.BrandNotFoundException;
import com.bogdanlopatenko.exception.HotelNotFoundException;
import com.bogdanlopatenko.mapper.HotelMapper;
import com.bogdanlopatenko.repository.AmenityRepository;
import com.bogdanlopatenko.repository.BrandRepository;
import com.bogdanlopatenko.repository.HotelRepository;
import com.bogdanlopatenko.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelUnitTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void shouldReturnAllHotels() {
        Hotel hotel1 = new HotelTestBuilder()
                .withId(1L)
                .withName("Hilton")
                .build();

        Hotel hotel2 = new HotelTestBuilder()
                .withId(2L)
                .withName("Marriott")
                .build();

        List<Hotel> hotels = List.of(hotel1, hotel2);

        HotelShortResponseDto dto1 = new HotelShortResponseDto(
                1L,
                "Hilton",
                "Some description",
                new BrandShortResponseDto("Trump"),
                "USA, New-York",
                "+375256546565"
        );

        HotelShortResponseDto dto2 = new HotelShortResponseDto(
                2L,
                "Marriott",
                "Some description",
                new BrandShortResponseDto("Trump"),
                "USA, New-York",
                "+375256546565"
        );

        List<HotelShortResponseDto> expectedDtos = List.of(dto1, dto2);

        when(hotelRepository.findAll())
                .thenReturn(hotels);

        when(hotelMapper.toShortResponseList(hotels))
                .thenReturn(expectedDtos);

        List<HotelShortResponseDto> actual =
                hotelService.getAll();

        assertThat(actual).hasSize(2);

        assertThat(actual)
                .extracting(HotelShortResponseDto::getName)
                .containsExactly("Hilton", "Marriott");

        verify(hotelRepository).findAll();

        verify(hotelMapper)
                .toShortResponseList(hotels);
    }

    @Test
    void shouldReturnEmptyListWhenNoHotelsFound() {

        List<Hotel> hotels = Collections.emptyList();

        when(hotelRepository.findAll())
                .thenReturn(hotels);

        when(hotelMapper.toShortResponseList(hotels))
                .thenReturn(Collections.emptyList());

        List<HotelShortResponseDto> actual =
                hotelService.getAll();

        assertThat(actual).isEmpty();

        verify(hotelRepository).findAll();

        verify(hotelMapper)
                .toShortResponseList(hotels);
    }

    @Test
    void shouldReturnHotelFullResponseDto_whenHotelExists() {

        Long id = 1L;

        Hotel hotel = new HotelTestBuilder()
                .withId(id)
                .withName("Trump Tower")
                .build();

        HotelFullResponseDto expectedDto =
                new HotelFullResponseDto(
                        id,
                        "Trump Tower",
                        "Some description",
                        new BrandResponseDto(1L, "Trump"),
                        "12",
                        "Some street",
                        "New-York",
                        "USA",
                        "00050",
                        "+375256546565",
                        "sometrump@email.com",
                        LocalTime.of(12, 0),
                        LocalTime.of(14, 0),
                        Set.of()
                );

        when(hotelRepository.findById(id))
                .thenReturn(Optional.of(hotel));

        when(hotelMapper.toFullResponseDto(hotel))
                .thenReturn(expectedDto);

        HotelFullResponseDto actual =
                hotelService.getById(id);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getName()).isEqualTo("Trump Tower");

        verify(hotelRepository).findById(id);
        verify(hotelMapper).toFullResponseDto(hotel);
    }

    @Test
    void shouldThrowException_whenHotelNotFound() {

        Long id = 99L;

        when(hotelRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.getById(99L))
                .isInstanceOf(HotelNotFoundException.class)
                .hasMessageContaining("Hotel not found by id");

        verify(hotelRepository).findById(id);
        verifyNoInteractions(hotelMapper);
    }

    @Test
    void shouldReturnFilteredHotels() {

        HotelFilterDto filterDto = new HotelFilterDto();
        filterDto.setCity("New-York");

        Specification<Hotel> spec =
                HotelSpecification.withFilters(filterDto);

        Hotel hotel = new HotelTestBuilder()
                .withId(1L)
                .withCity("New-York")
                .build();

        List<Hotel> hotels = List.of(hotel);

        HotelShortResponseDto dto =
                new HotelShortResponseDto(
                        1L,
                        "Trump Tower",
                        "Some description",
                        new BrandShortResponseDto("Trump"),
                        "USA, New-York",
                        "+375256546565"
                );

        List<HotelShortResponseDto> expected = List.of(dto);

        when(hotelRepository.findAll(any(Specification.class)))
                .thenReturn(hotels);

        when(hotelMapper.toShortResponseList(hotels))
                .thenReturn(expected);

        List<HotelShortResponseDto> result =
                hotelService.search(filterDto);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAddress().contains("New-York"));

        verify(hotelRepository)
                .findAll(any(Specification.class));

        verify(hotelMapper)
                .toShortResponseList(hotels);
    }

    @Test
    void shouldCreateHotelSuccessfully() {

        String brandName = "Trump";

        Brand brand = new Brand(1L, brandName);

        BrandRequestDto brandDto = new BrandRequestDto();
        brandDto.setName(brandName);

        HotelRequestDto request = new HotelRequestDto();
        request.setBrand(brandDto);

        Hotel hotel = new HotelTestBuilder()
                .withId(null)
                .build();

        Hotel savedHotel = new HotelTestBuilder()
                .withId(1L)
                .build();

        HotelShortResponseDto expectedDto =
                new HotelShortResponseDto(
                        1L,
                        "Trump Tower",
                        "Some description",
                        null,
                        "USA, New-York",
                        "+375256546565"
                );

        when(brandRepository.findByName(brandName))
                .thenReturn(Optional.of(brand));

        when(hotelMapper.toHotel(request))
                .thenReturn(hotel);

        when(hotelRepository.save(hotel))
                .thenReturn(savedHotel);

        when(hotelMapper.toShortResponseDto(savedHotel))
                .thenReturn(expectedDto);

        HotelShortResponseDto result =
                hotelService.create(request);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);

        verify(brandRepository).findByName(brandName);

        verify(hotelMapper).toHotel(request);

        verify(hotelRepository).save(hotel);

        verify(hotelMapper).toShortResponseDto(savedHotel);
    }

    @Test
    void shouldThrowException_whenBrandNotFound() {

        String brandName = "Unknown";

        BrandRequestDto brandDto = new BrandRequestDto();
        brandDto.setName(brandName);

        HotelRequestDto request = new HotelRequestDto();
        request.setBrand(brandDto);

        when(brandRepository.findByName(brandName))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                hotelService.create(request))
                .isInstanceOf(BrandNotFoundException.class)
                .hasMessage(
                        ExceptionConstant.BRAND_NOT_FOUND_BY_NAME + "Unknown"
                );

        verify(brandRepository).findByName(brandName);

        verifyNoInteractions(hotelMapper);

        verifyNoInteractions(hotelRepository);
    }
}

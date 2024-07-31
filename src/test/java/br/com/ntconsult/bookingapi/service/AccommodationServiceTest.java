package br.com.ntconsult.bookingapi.service;

import br.com.ntconsult.bookingapi.domain.mapper.AccommodationAvailableMapper;
import br.com.ntconsult.bookingapi.repository.AccommodationRepository;
import br.com.ntconsult.bookingapi.repository.AddressRepository;
import br.com.ntconsult.bookingapi.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class AccommodationServiceTest {

    private final String PATH_ACCOMMODATION = "src/test/java/br/com/ntconsult/bookingapi/json/search.json";

    @Mock
    private AccommodationRepository accommodationRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AccommodationAvailableMapper accommodationAvailableMapper;

    @InjectMocks
    private AccommodationServiceImpl accommodationService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
}

package com.ramenshop.server.service;

import com.ramenshop.server.converter.RamenConverter;
import com.ramenshop.server.exception.RamenNotFoundException;
import com.ramenshop.server.model.Measurement;
import com.ramenshop.server.model.Ramen;
import com.ramenshop.server.model.Serving;
import com.ramenshop.server.repository.RamenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RamenServiceTest {


    private RamenRepository ramenRepository;

    private RamenService service;

    public static final String NAME = "Tonkotsu";
    public static final double PRICE = 12.59;
    public static final String MENU_CODE = "1A";

    private final Ramen entry = Ramen.builder()
            .name(NAME)
            .menuCode(MENU_CODE)
            .recipe(List.of(
                    new Serving("Pork bone broth", 3.0, Measurement.cup),
                    new Serving("Noodles", 1.0, Measurement.lbs),
                    new Serving("Roasted pork", 0.5, Measurement.lbs)
            ))
            .price(PRICE)
            .build();

    @BeforeEach
    void init(){
        this.ramenRepository = Mockito.mock(RamenRepository.class);
        this.service = new RamenService(new RamenConverter(), this.ramenRepository);
    }

    @Test
    void findAllRamenOfferings_EmptyCollection(){
        when(ramenRepository.findAll()).thenReturn(Collections.emptyList());
        var result = service.findAllRamenOfferings();
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllRamenOfferings_NonEmptyCollection(){
        when(ramenRepository.findAll()).thenReturn(List.of(entry));

        var result = service.findAllRamenOfferings();

        assertFalse( result.isEmpty());
        assertEquals(1 , result.size());

        var foundEntry = result.get(0);
        assertEquals(NAME, foundEntry.getName());
        assertEquals(PRICE, foundEntry.getPrice());
    }

    @Test
    void findRamenByMenuCode_Success(){
        when(ramenRepository.findById(MENU_CODE)).thenReturn(Optional.of(entry));
        var result = service.findRamenByMenuCode(MENU_CODE);
        assertNotNull(result);
    }

    @Test
    void findRamenByMenuCode_NotFound(){
        when(ramenRepository.findById(MENU_CODE)).thenReturn(Optional.empty());
        assertThrows(RamenNotFoundException.class, () -> {
            service.findRamenByMenuCode(MENU_CODE);
        });
    }

}

package com.ramenshop.server.service;

import com.ramenshop.server.converter.MenuItemConverter;
import com.ramenshop.server.converter.RamenConverter;
import com.ramenshop.server.dto.MenuItemDto;
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
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RamenServiceTest {


    private RamenRepository ramenRepository;
    private GenericConversionService conversionService;

    private RamenService service;

    public static final String NAME = "Tonkotsu";
    public static final double PRICE = 12.59;
    public static final String MENU_CODE = "1A";

    private Ramen entry;
    private MenuItemDto menuItem;

    @BeforeEach
    void init() {
        this.entry = Ramen.builder()
                .name(NAME)
                .menuCode(MENU_CODE)
                .recipe(List.of(
                        new Serving("Pork bone broth", 3.0, Measurement.cup),
                        new Serving("Noodles", 1.0, Measurement.lbs),
                        new Serving("Roasted pork", 0.5, Measurement.lbs)
                ))
                .price(PRICE)
                .build();

        this.menuItem = MenuItemDto.builder()
                .name(NAME)
                .ingredients(List.of(
                        new Serving("Pork bone broth", 3.0, Measurement.cup),
                        new Serving("Noodles", 1.0, Measurement.lbs),
                        new Serving("Roasted pork", 0.5, Measurement.lbs)
                ))
                .suggestedPrice(PRICE)
                .build();

        this.conversionService = new GenericConversionService();
        this.conversionService.addConverter(new RamenConverter());
        this.conversionService.addConverter(new MenuItemConverter());
        this.ramenRepository = Mockito.mock(RamenRepository.class);
        this.service = new RamenService(this.conversionService, this.ramenRepository);
    }

    @Test
    void findAllRamenOfferings_EmptyCollection() {
        when(ramenRepository.findAll()).thenReturn(Collections.emptyList());
        var result = service.findAllRamenOfferings();
        assertTrue(result.isEmpty());
    }

    @Test
    void findAllRamenOfferings_NonEmptyCollection() {
        when(ramenRepository.findAll()).thenReturn(List.of(entry));

        var result = service.findAllRamenOfferings();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        var foundEntry = result.get(0);
        assertEquals(NAME, foundEntry.getName());
        assertEquals(PRICE, foundEntry.getPrice());
    }

    @Test
    void findRamenByMenuCode_Success() {
        when(ramenRepository.findById(MENU_CODE)).thenReturn(Optional.of(entry));
        var result = service.findRamenByMenuCode(MENU_CODE);
        assertNotNull(result);
    }

    @Test
    void findRamenByMenuCode_NotFound() {
        when(ramenRepository.findById(MENU_CODE)).thenReturn(Optional.empty());
        assertThrows(RamenNotFoundException.class, () -> {
            service.findRamenByMenuCode(MENU_CODE);
        });
    }

    @Test
    void createNewMenuItem_Success() {
        when(ramenRepository.save(any(Ramen.class))).thenReturn(entry);
        service.createNewMenuItem(menuItem);
        verify(ramenRepository).save(any(Ramen.class));
    }

    @Test
    void updateMenuItem_ItemDoesntExist() {
        assertThrows(RamenNotFoundException.class, () -> {
            service.updateMenuItem(MENU_CODE, menuItem);
        });
    }

    @Test
    void updateMenuItem_Sucess() {
        when(ramenRepository.findById(MENU_CODE)).thenReturn(Optional.ofNullable(entry.toBuilder()
                .name("Old name")
                .price(999.99)
                .build()));
        when(ramenRepository.save(any())).thenReturn(entry);

        var result = service.updateMenuItem(MENU_CODE, menuItem);

        assertEquals(MENU_CODE, result.getMenuCode());
        assertEquals(NAME, result.getName());
        assertEquals(PRICE, result.getPrice());
    }

    @Test
    void deleteMenuItem_Success(){
        when(ramenRepository.findById(MENU_CODE)).thenReturn(Optional.ofNullable(entry));
        service.deleteMenuItem(MENU_CODE);
        verify(ramenRepository).delete(any());
    }

    @Test
    void deleteMenuItem_ItemDoesntExist(){
        assertThrows(RamenNotFoundException.class, () -> {
            service.deleteMenuItem(MENU_CODE);
        });
    }
}

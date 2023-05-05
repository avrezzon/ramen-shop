package com.ramenshop.server.converter;

import com.ramenshop.server.dto.MenuItemDto;
import com.ramenshop.server.dto.RamenDto;
import com.ramenshop.server.model.Measurement;
import com.ramenshop.server.model.Ramen;
import com.ramenshop.server.model.Serving;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemConverterTest {
    public static final String NAME = "Tonkotsu";
    public static final double PRICE = 12.59;
    private MenuItemConverter converter;

    @BeforeEach
    void init(){
        converter = new MenuItemConverter();
    }

    @Test
    public void convertRamenToDto(){
        MenuItemDto source = MenuItemDto.builder()
                .name(NAME)
                .ingredients(List.of(
                        new Serving("Pork bone broth", 3.0, Measurement.cup),
                        new Serving("Noodles", 1.0, Measurement.lbs),
                        new Serving("Roasted pork", 0.5, Measurement.lbs)
                ))
                .suggestedPrice(PRICE)
                .build();

        Ramen result = converter.convert(source);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(PRICE, result.getPrice());
        assertEquals(3, result.getRecipe().size());
    }
}
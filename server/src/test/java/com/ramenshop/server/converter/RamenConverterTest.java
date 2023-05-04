package com.ramenshop.server.converter;

import com.ramenshop.server.dto.RamenDto;
import com.ramenshop.server.model.Measurement;
import com.ramenshop.server.model.Ramen;
import com.ramenshop.server.model.Serving;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RamenConverterTest {

    public static final String NAME = "Tonkotsu";
    public static final double PRICE = 12.59;
    public static final String MENU_CODE = "1A";
    private RamenConverter converter;

    @BeforeEach
    void init(){
        converter = new RamenConverter();
    }

    @Test
    public void convertRamenToDto(){
        Ramen source = Ramen.builder()
                .name(NAME)
                .menuCode(MENU_CODE)
                .recipe(List.of(
                        new Serving("Pork bone broth", 3.0, Measurement.cup),
                        new Serving("Noodles", 1.0, Measurement.lbs),
                        new Serving("Roasted pork", 0.5, Measurement.lbs)
                ))
                .price(PRICE)
                .build();

        RamenDto result = converter.convert(source);

        assertNotNull(result);
        assertEquals(NAME, result.getName());
        assertEquals(MENU_CODE, result.getMenuCode());
        assertEquals(PRICE, result.getPrice());
        assertEquals(3, result.getIngredients().size());
    }

}

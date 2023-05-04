package com.ramenshop.server.converter;

import com.ramenshop.server.dto.MenuItemDto;
import com.ramenshop.server.model.Ramen;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MenuItemConverter implements Converter<MenuItemDto, Ramen> {
    @Override
    public Ramen convert(MenuItemDto source) {
        return Ramen.builder()
                .name(source.getName())
                .recipe(source.getIngredients())
                .price(source.getSuggestedPrice())
                .build();
    }
}

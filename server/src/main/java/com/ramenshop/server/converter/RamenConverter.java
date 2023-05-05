package com.ramenshop.server.converter;

import com.ramenshop.server.dto.RamenDto;
import com.ramenshop.server.model.Ramen;
import com.ramenshop.server.model.Serving;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class RamenConverter implements Converter<Ramen, RamenDto> {
    @Override
    public RamenDto convert(Ramen source) {
        return RamenDto.builder()
                .name(source.getName())
                .menuCode(source.getMenuCode())
                .ingredients(source.getRecipe()
                        .stream().map(Serving::getFood)
                        .collect(Collectors.toList()))
                .price(source.getPrice())
                .build();
    }
}

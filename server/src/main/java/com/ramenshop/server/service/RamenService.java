package com.ramenshop.server.service;

import com.ramenshop.server.dto.MenuItemDto;
import com.ramenshop.server.dto.RamenDto;
import com.ramenshop.server.exception.RamenNotFoundException;
import com.ramenshop.server.model.Ramen;
import com.ramenshop.server.repository.RamenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RamenService {

    private final ConversionService converter;
    private final RamenRepository ramenRepository;

    public List<RamenDto> findAllRamenOfferings() {
        log.info("Retrieving all of the ramen from the menu...");
        return ramenRepository.findAll()
                .stream()
                .map(ramen -> converter.convert(ramen, RamenDto.class))
                .collect(Collectors.toList());
    }

    public RamenDto findRamenByMenuCode(String menuCode) {
        return converter.convert(findRamenEntry(menuCode), RamenDto.class);
    }

    public RamenDto createNewMenuItem(MenuItemDto recipe) {
        Ramen convert = converter.convert(recipe, Ramen.class);
        Ramen save = ramenRepository.save(convert);
        return converter.convert(save, RamenDto.class);
    }

    public RamenDto updateMenuItem(String menuCode, MenuItemDto recipe) {
        Ramen entry = findRamenEntry(menuCode);
        log.info("Original entry: {}", entry);
        Ramen updatedEntry = ramenRepository.save(entry.toBuilder()
                .name(recipe.getName())
                .recipe(recipe.getIngredients())
                .price(recipe.getSuggestedPrice())
                .build());
        log.info("Updated entry: {}", updatedEntry);
        return converter.convert(updatedEntry, RamenDto.class);
    }

    public void deleteMenuItem(String menuCode){
        Ramen entry = findRamenEntry(menuCode);
        log.info("Now deleting menu entry: {}", entry.getMenuCode());
        ramenRepository.delete(entry);
    }


    private Ramen findRamenEntry(String menuCode) {
        Optional<Ramen> optionalRamen = ramenRepository.findById(menuCode);
        if (optionalRamen.isEmpty())
            throw new RamenNotFoundException();
        log.info("Found ramen with menu code of {}", menuCode);
        return optionalRamen.get();
    }


}

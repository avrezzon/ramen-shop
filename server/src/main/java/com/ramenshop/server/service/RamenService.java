package com.ramenshop.server.service;

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

    public RamenDto findRamenByMenuCode(String menuCode){
        return converter.convert(findRamenEntry(menuCode), RamenDto.class);
    }

    private Ramen findRamenEntry(String menuCode){
        Optional<Ramen> optionalRamen = ramenRepository.findById(menuCode);
        if(optionalRamen.isEmpty())
            throw new RamenNotFoundException();
        log.info("Found ramen with menu code of {}", menuCode);
        return optionalRamen.get();
    }


}

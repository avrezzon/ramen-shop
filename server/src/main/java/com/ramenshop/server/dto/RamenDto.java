package com.ramenshop.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RamenDto implements Serializable {
    private String name;

    private String menuCode;

    // We don't want to necessarily expose how we make the ramen to the UI
    // so we mask it through this dto
    private List<String> ingredients;
    private Double price;
}

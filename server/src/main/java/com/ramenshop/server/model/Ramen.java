package com.ramenshop.server.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Ramen {
    private String name;
    private List<Serving> recipe;
    private Double price;

}

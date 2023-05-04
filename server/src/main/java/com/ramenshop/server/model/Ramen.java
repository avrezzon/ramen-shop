package com.ramenshop.server.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder(toBuilder = true)
@Document
public class Ramen {

    @Id
    private String menuCode;
    private String name;
    private List<Serving> recipe;
    private Double price;

}

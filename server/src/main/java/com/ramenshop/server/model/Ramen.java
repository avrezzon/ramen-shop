package com.ramenshop.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(toBuilder = true)
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Ramen implements Serializable {

    @Id
    private String menuCode;
    private String name;
    private List<Serving> recipe;
    private Double price;

}

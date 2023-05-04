package com.ramenshop.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Serving implements Serializable {
    private String food;
    private Double amount;
    private Measurement measurement;
}

package com.ramenshop.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Serving {
    private String food;
    private Double amount;
    private Measurement measurement;
}

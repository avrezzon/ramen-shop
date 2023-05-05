package com.ramenshop.server.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Serving implements Serializable {

    @NotBlank
    private String food;

    @Positive
    private Double amount;
    private Measurement measurement;
}

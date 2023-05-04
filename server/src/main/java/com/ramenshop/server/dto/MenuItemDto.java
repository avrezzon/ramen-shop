package com.ramenshop.server.dto;

import com.ramenshop.server.model.Serving;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MenuItemDto implements Serializable {
    
    @NotBlank
    private String name;

    @NotEmpty
    private List<Serving> ingredients;

    @Digits(integer = 3, fraction = 2)
    private Double suggestedPrice;

}

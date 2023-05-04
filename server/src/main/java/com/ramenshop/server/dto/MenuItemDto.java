package com.ramenshop.server.dto;

import com.ramenshop.server.model.Serving;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MenuItemDto implements Serializable {
    private String name;
    private List<Serving> ingredients;
    private Double suggestedPrice;

}

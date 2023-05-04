package com.ramenshop.server.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.ramenshop.server.dto.MenuItemDto;
import com.ramenshop.server.dto.RamenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("ramen")
public class RamenController {

    //For the status codes, I am referencing https://restfulapi.net/http-methods/

    @GetMapping
    @Operation(summary = "Get the current menu offering of all of the available ramen")
    public List<RamenDto> getAllRamen(){
        return Collections.emptyList();
    }

    @GetMapping("/{menuCode}")
    @Operation(summary = "Get the ramen offering by the menu code of the ramen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully found the ramen offering"),
            @ApiResponse(responseCode = "404", description = "Could not find ramen by specified menu code, i.e. 1. A -> Tonkotsu")
    })
    public RamenDto getRamen(@PathVariable String menuCode){
        return null;
    }

    //TODO probably will want authentication here, i.e managers should be the one to create the menu offering
    @PostMapping
    @Operation(summary = "This will create a new ramen offering on the menu, could vary with different ingredients" +
            " but will result in a new menu code each time this is called")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The new ramen recipe was created and is now included on the menu"),
            @ApiResponse(responseCode = "400", description = "Invalid request was sent, all fields must be populated"),
            @ApiResponse(responseCode = "403", description = "The current user is unable to perform this request due to privilege")
    })
    public RamenDto createNewRamenRecipe(@RequestBody MenuItemDto recipeDto){
        //TODO create the converter for the MenuItemDto
        return null;
    }

    @PutMapping("/{menuCode}")
    @Operation(summary = "This will update an existing entry of ramen, and is to be used if more than one field changes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The ramen entry was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request was sent, all fields must be populated"),
            @ApiResponse(responseCode = "403", description = "The current user is unable to perform this request due to privilege"),
            @ApiResponse(responseCode = "404", description = "Could not find ramen by specified menu code")
    })
    public RamenDto updateExistingOffering(@PathVariable String menuCode, @RequestBody MenuItemDto reviseRamenDto){
        return null;
    }

    //TODO look into the patch method for simple revisions to the resource
    //  without having to supply the entire resource https://www.baeldung.com/spring-rest-json-patch

    @PatchMapping(path ="/{menuCode}", consumes = "application/json-patch+json")
    @Operation(summary = "This will update an existing entry of ramen, and is to be used if one field needs to be changed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The ramen entry was updated successfully"),
            @ApiResponse(responseCode = "403", description = "The current user is unable to perform this request due to privilege"),
            @ApiResponse(responseCode = "404", description = "Could not find ramen by specified menu code")
    })
    public void updateExistingOfferingField(@PathVariable String menuCode, @RequestBody JsonPatch patch){
    }

    @DeleteMapping("/{menuCode}")
    @Operation(summary = "This will remove entry of ramen from the menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The ramen entry was updated successfully"),
            @ApiResponse(responseCode = "403", description = "The current user is unable to perform this request due to privilege"),
            @ApiResponse(responseCode = "404", description = "Could not find ramen by specified menu code")
    })
    public void deleteRamenOffering(@PathVariable String menuCode){}

}

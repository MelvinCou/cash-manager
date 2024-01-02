package com.cashmanager.server.product.controllers;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.product.exceptions.ProductException;
import com.cashmanager.server.product.services.interfaces.IProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop/products")
@Tag(name = "API Products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping("")
    @Operation(summary = "Get all the products available in the database", description = "Returns a list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "204", description = "No product in the database"),
            @ApiResponse(responseCode = "503", description = "Error accessing the database, please retry later")
    })
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtos ;
        try {
            productDtos = productService.getAllProducts();
            if(productDtos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                return new ResponseEntity<>(productDtos, HttpStatus.OK);
            }
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}

package com.platzi.marker.web.controller;

import com.platzi.marker.domain.Product;
import com.platzi.marker.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired //se puede usar ya que se tiene una anotacion de Spring
    private ProductService productService;
    @GetMapping("/all")
    @ApiOperation("Obtienes todos los productos del supermarket")
    @ApiResponse(code = 200,message = "OK")
    public ResponseEntity<List<Product>>getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @ApiOperation("Buscar un producto por su id")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 404,message = "Not-Found")
    })
    public ResponseEntity<Product>getById(@ApiParam(value = "es el id del productp") @PathVariable("id") int productId){
        return productService.getProduct(productId).map(product ->
                new ResponseEntity<>(product,HttpStatus.OK))
                .orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>>getByCategory(@PathVariable ("categoryId") int categoryId){
        return productService.getByCategory(categoryId).map(products ->
                new ResponseEntity<>(products,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

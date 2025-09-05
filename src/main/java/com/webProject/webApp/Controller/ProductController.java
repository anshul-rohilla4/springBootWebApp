package com.webProject.webApp.Controller;

import com.webProject.webApp.Model.Product;
import com.webProject.webApp.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId){
        return service.getProductById(productId);
    }


    @PostMapping("/product")
    public Product addProduct(@RequestBody Product prod){
        service.addProduct(prod);
        return prod;
    }


}

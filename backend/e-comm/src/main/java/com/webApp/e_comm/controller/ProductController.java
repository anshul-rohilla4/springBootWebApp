package com.webApp.e_comm.controller;


import com.webApp.e_comm.service.ProductService;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import com.webApp.e_comm.model.Product;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello world";
    }

    @GetMapping
    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>( service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){

        Product product=service.getProductById(id);

        if(product!=null){
                return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct (@RequestPart Product product,
                                         @RequestPart MultipartFile imageFile){

        Product product1=null;
        try {
            product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("product/{prodId}/image")
    public ResponseEntity<byte[]> getImageByProdId(@PathVariable int prodId){
        Product product=service.getProductById(prodId);
        byte[] imageFile=product.getImgData();

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(product.getImgType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) {
        Product product1=null;
        try{
            product1=service.updateProduct(id,product,imageFile);
        }catch (IOException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
        if(product1 !=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        else {
            return  new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }




    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> delProduct(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product!=null){
            service.delProduct(id);
            return new ResponseEntity<>("Deleted successfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products=service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}

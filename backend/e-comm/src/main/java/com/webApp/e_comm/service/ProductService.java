package com.webApp.e_comm.service;

import com.webApp.e_comm.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.webApp.e_comm.model.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.StyleSheet;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;


    public List<Product> getProducts(){
        return repo.findAll();
    }
    
    
    public Product getProductById(int id){
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImgName(imageFile.getOriginalFilename());
        product.setImgType(imageFile.getContentType());
        product.setImgData(imageFile.getBytes());
        return repo.save(product);
    }



    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImgData(imageFile.getBytes());
        product.setImgName(imageFile.getOriginalFilename());
        product.setImgType(imageFile.getContentType());
        return repo.save(product);
    }

    public void delProduct(int id){
//        repo.delete(repo.getReferenceById(id));
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword){
        return repo.searchProducts(keyword);
    }

}

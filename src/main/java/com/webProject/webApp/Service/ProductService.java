package com.webProject.webApp.Service;

import com.webProject.webApp.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> products=new ArrayList<>(
            Arrays.asList(
                    new Product(101,"iPhone",68000),
                    new Product(102,"samsung",50000),
                    new Product(103,"xiomi",28000)
            )
    );



    public List<Product> getProducts(){
        return products;
    }

    public Product getProductById(int productId) {
        return products.stream()
                .filter(p->p.getProductId()==productId)
                .findFirst().orElse(new Product(0,"No Item",0));
    }

    public void addProduct(Product prod){
        products.add(prod);
    }


}

package com.example.java_train.Services;

import com.example.java_train.Entities.Product;
import com.example.java_train.Models.CommonModel.Pagination;
import com.example.java_train.Models.ProductModel.CreateProductModel;
import com.example.java_train.Models.ProductModel.EditProductModel;
import com.example.java_train.Models.ProductModel.GetProductModel;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Page<GetProductModel>> GetProducts(Pagination pagination);
    ResponseEntity<CreateProductModel> Create(CreateProductModel createProductModel);
    ResponseEntity<EditProductModel> Edit(EditProductModel editProductModel);
    ResponseEntity<Product> GetById(int productId);
    ResponseEntity<Product> Delete(int productId);
}

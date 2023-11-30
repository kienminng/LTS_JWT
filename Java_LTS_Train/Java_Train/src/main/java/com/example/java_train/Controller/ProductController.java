package com.example.java_train.Controller;

import com.example.java_train.Entities.Product;
import com.example.java_train.Models.CommonModel.Pagination;
import com.example.java_train.Models.ProductModel.CreateProductModel;
import com.example.java_train.Models.ProductModel.EditProductModel;
import com.example.java_train.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService _productService;

    @GetMapping("/get-products")
    public ResponseEntity<?> getAllProduct(@RequestParam(value = "PageSize", required = false) Integer PageSize,
                                           @RequestParam(value = "PageNumber", required = false) Integer PageNumber) {
        Pagination pagination = new Pagination(PageSize, PageNumber);

        return _productService.GetProducts(pagination);
    }

    @PostMapping("/add-product")
    public ResponseEntity<CreateProductModel> createProduct(@RequestBody CreateProductModel createProductModel) {
        return _productService.Create(createProductModel);
    }

    @PutMapping("/edit-product")
    public ResponseEntity<EditProductModel> updateProduct(@RequestBody EditProductModel editProductModel) {
        return _productService.Edit(editProductModel);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Product> delProduct(@RequestParam int productId) {
        return _productService.Delete(productId);
    }
}

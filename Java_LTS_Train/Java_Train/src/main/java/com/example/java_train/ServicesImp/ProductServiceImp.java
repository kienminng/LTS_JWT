package com.example.java_train.ServicesImp;

import com.example.java_train.Entities.Product;
import com.example.java_train.Entities.ProductType;
import com.example.java_train.Extensions.Mapper.ConvertProductToDTOs;
import com.example.java_train.Models.CommonModel.Pagination;
import com.example.java_train.Models.ProductModel.CreateProductModel;
import com.example.java_train.Models.ProductModel.EditProductModel;
import com.example.java_train.Models.ProductModel.GetProductModel;
import com.example.java_train.Repositories.ProductRepository;
import com.example.java_train.Services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository _productRepository;

    @Override
    public ResponseEntity<Page<GetProductModel>> GetProducts(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPageNumber(), pagination.getPageSize());

        Page<GetProductModel> page = _productRepository.findALlProduct(pageable);

        _productRepository.findAll().forEach((product -> {

        }));
        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<CreateProductModel> Create(CreateProductModel createProductModel) {
        Product product = ConvertProductToDTOs.ConvertCreateModelToProduct(createProductModel);
        _productRepository.save(product);
        return ResponseEntity.ok(createProductModel);
    }

    @Override
    public ResponseEntity<EditProductModel> Edit(EditProductModel editProductModel) {
        var newProduct = ConvertProductToDTOs.ConvertEditProductModelToProduct(editProductModel);
        Optional<Product> optional = _productRepository.findById(editProductModel.getId());
        if(optional.isPresent()){
            Product existingProduct = optional.get();
            existingProduct.setProductName(newProduct.getProductName());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setDescc(newProduct.getDescc());
            existingProduct.setNgayHetHan(newProduct.getNgayHetHan());

            _productRepository.save(existingProduct);
            return ResponseEntity.ok(editProductModel);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Product> GetById(int productId) {
        Optional<Product> optional = _productRepository.findById(productId);
        if(optional.isPresent()) {
            Product product = optional.get();
            return ResponseEntity.ok(product);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Product> Delete(int productId) {
        Optional<Product> optional = _productRepository.findById(productId);
        if(optional.isPresent()) {
            Product deletedProduct = optional.get();
            _productRepository.delete(deletedProduct);
            return ResponseEntity.ok(deletedProduct);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

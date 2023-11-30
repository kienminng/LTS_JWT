package com.example.java_train.Extensions.Mapper;

import com.example.java_train.Entities.Product;
import com.example.java_train.Entities.ProductType;
import com.example.java_train.Models.ProductModel.CreateProductModel;
import com.example.java_train.Models.ProductModel.EditProductModel;
import com.example.java_train.Models.ProductModel.GetProductModel;
import com.example.java_train.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class ConvertProductToDTOs {

    public static GetProductModel ConvertToGetProductModel(Product product){
        GetProductModel getProductModel = new GetProductModel();
        getProductModel.setProductId(product.getId());
        getProductModel.setProductName(product.getProductName());
        getProductModel.setPrice(product.getPrice());
        getProductModel.setDescc(product.getDescc());
        getProductModel.setNgayHetHan(product.getNgayHetHan());

        getProductModel.setProductTypeId(product.getProductType().getId());
        getProductModel.setProductTypeName(product.getProductType().getTypeName());

        return getProductModel;
    }

    public static Product ConvertCreateModelToProduct(CreateProductModel createProductModel) {
        Product product = new Product();
        product.setProductName(createProductModel.getProductName());
        product.setPrice(createProductModel.getPrice());
        product.setDescc(createProductModel.getDescc());
        product.setNgayHetHan(createProductModel.getNgayHetHan());

        ProductType productType = new ProductType();
        productType.setId(createProductModel.getProductTypeId());

        product.setProductType(productType);
        return product;
    }

    public static Product ConvertEditProductModelToProduct(EditProductModel editProductModel) {
        Product product = new Product();
        product.setId(editProductModel.getId());
        product.setProductName(editProductModel.getProductName());
        product.setPrice(editProductModel.getPrice());
        product.setDescc(editProductModel.getDescc());
        product.setNgayHetHan(editProductModel.getNgayHetHan());

        return product;
    }
}

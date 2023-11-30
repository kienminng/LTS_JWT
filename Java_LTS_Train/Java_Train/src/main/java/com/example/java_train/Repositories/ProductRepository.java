package com.example.java_train.Repositories;

import com.example.java_train.Entities.Product;
import com.example.java_train.Models.ProductModel.GetProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select new com.example.java_train.Models.ProductModel.GetProductModel(p.Id, p.ProductName, p.Price, p.Descc, p.NgayHetHan, p.productType.TypeName, p.productType.Id) from Product p")
    Page<GetProductModel> findALlProduct(Pageable pageable);
}

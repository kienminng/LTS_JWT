package com.example.java_train.Models.ProductModel;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductModel {
    @NotBlank(message = "ProductName is Required")
    private String ProductName;
    private double Price;
    private String Descc;
    private LocalDate NgayHetHan;
    private int ProductTypeId;
}

package com.example.java_train.Models.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditProductModel {
    private int Id;
    private String ProductName;
    private double Price;
    private String Descc;
    private LocalDate NgayHetHan;
}

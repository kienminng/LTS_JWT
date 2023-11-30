package com.example.java_train.Models.CommonModel;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pagination {
    private int PageSize;
    private int PageNumber;
}

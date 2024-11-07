package com.gold_mining_app_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageDTO<T> {
    private int pageNumber;
    private int pageSize;
    private long size;
    private List<T> data;
}

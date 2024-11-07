package com.gold_mining_app_backend.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageInput {
private int pageNumber;
private int pageSize;
private String sortBy;
private String search;
}

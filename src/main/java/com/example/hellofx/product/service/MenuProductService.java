package com.example.hellofx.product.service;

import com.example.hellofx.product.dto.Menu_CategoryDTO;
import com.example.hellofx.product.dto.Menu_ProductDTO;
import com.example.hellofx.product.entity.Menu_Category;
import com.example.hellofx.product.entity.Menu_Product;
import com.example.hellofx.product.repository.MenuProductRepository;
import lombok.NoArgsConstructor;

import java.util.List;

public interface MenuProductService {

    int addCategory(Menu_CategoryDTO menuCategoryDTO); // 카테고리 추가

    List<Menu_CategoryDTO> categoryList(); // 카테고리 목록

    void addProduct(Menu_ProductDTO menuProductDTO); // 상품 등록
    
    List<Menu_ProductDTO> productList(String category); // 상품 목록
}

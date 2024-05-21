package com.example.hellofx.product.service;

import com.example.hellofx.product.dto.Menu_CategoryDTO;
import com.example.hellofx.product.dto.Menu_ProductDTO;
import com.example.hellofx.product.entity.Menu_Category;
import com.example.hellofx.product.entity.Menu_Product;
import com.example.hellofx.product.repository.MenuProductRepository;
import lombok.NoArgsConstructor;

import java.util.List;

public interface MenuProductService {

    void addCategory(Menu_CategoryDTO menuCategoryDTO);

    List<Menu_CategoryDTO> categoryList();

    void addProduct(Menu_ProductDTO menuProductDTO);
}

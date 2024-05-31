package com.example.hellofx.product.service;

import com.example.hellofx.product.dto.Menu_CategoryDTO;
import com.example.hellofx.product.dto.Menu_ProductDTO;
import com.example.hellofx.product.entity.Menu_Category;
import com.example.hellofx.product.entity.Menu_Product;
import com.example.hellofx.product.repository.MenuProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuProductServiceImpl implements MenuProductService {

    private final MenuProductRepository menuProductRepository;

    public MenuProductServiceImpl(MenuProductRepository menuProductRepository) {
        this.menuProductRepository = menuProductRepository;
    }

    @Override
    public int addCategory(Menu_CategoryDTO menuCategoryDTO) { // 카테고리추가

        Menu_Category menuCategory = menuCategoryDTO.toEntity();

        return menuProductRepository.addCategory(menuCategory);
    }

    @Override
    public List<Menu_CategoryDTO> categoryList() { // 카테고리목록

        List<Menu_Category> menuCategories = menuProductRepository.categoryList();

        List<Menu_CategoryDTO> categoryDTOList = menuCategories.stream().map(category -> {
            Menu_CategoryDTO menuCategoryDTO = category.toDTO();

            menuCategoryDTO.setCno(category.getCno());
            menuCategoryDTO.setCategory(category.getCategory());

            return menuCategoryDTO;
        }).toList();

        return categoryDTOList;
    }

    @Override
    public void addProduct(Menu_ProductDTO menuProductDTO) {
        Menu_Product menuProduct = menuProductDTO.toEntity();

        menuProductRepository.addProduct(menuProduct);
    }

    @Override
    public List<Menu_ProductDTO> productList(String category) {

        List<Menu_Product> productList = menuProductRepository.productList(category);

        List<Menu_ProductDTO> productDTOList = productList.stream()
                .map(product -> {
                Menu_ProductDTO menuProductDTO = product.toDTO();

                menuProductDTO.setPno(product.getPno());
                menuProductDTO.setProductName(product.getProductName());
                menuProductDTO.setProductPrice(product.getProductPrice());
                menuProductDTO.setCategory(product.getCategory());
                menuProductDTO.setColor(product.getColor());
                    return menuProductDTO;
                }).toList();

        return productDTOList;
    }

}

package com.example.hellofx.product.dto;

import com.example.hellofx.product.entity.Menu_Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu_ProductDTO {

    private Long pno;
    private String category;
    private String productName;
    private int productPrice;
    private String color;

    public Menu_Product toEntity(){
        Menu_Product menu_product = Menu_Product.builder()
                .pno(this.pno)
                .category(this.category)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .color(this.color)
                .build();

        return menu_product;
    }
}

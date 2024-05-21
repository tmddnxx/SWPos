package com.example.hellofx.product.entity;

import com.example.hellofx.product.dto.Menu_ProductDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Menu_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pno;

    @Column
    private String category;

    @Column
    private String productName;

    @Column
    private int productPrice;

    @Column
    private String color;

    public Menu_ProductDTO toDTO(){
        Menu_ProductDTO menuProductDTO = new Menu_ProductDTO();

        menuProductDTO.setPno(this.pno);
        menuProductDTO.setCategory(this.category);
        menuProductDTO.setProductName(this.productName);
        menuProductDTO.setProductPrice(this.productPrice);
        menuProductDTO.setColor(this.color);

        return menuProductDTO;
    }
}

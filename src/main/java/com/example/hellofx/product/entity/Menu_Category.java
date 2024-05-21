package com.example.hellofx.product.entity;

import com.example.hellofx.product.dto.Menu_CategoryDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu_Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cno;

    @Column
    private String category;

    public Menu_CategoryDTO toDTO() {
        Menu_CategoryDTO menuCategoryDTO = new Menu_CategoryDTO();

        menuCategoryDTO.setCno(this.cno);
        menuCategoryDTO.setCategory(this.category);

        return menuCategoryDTO;
    }
}

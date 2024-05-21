package com.example.hellofx.product.dto;

import com.example.hellofx.product.entity.Menu_Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu_CategoryDTO {

    private Long cno;
    private String category;


    public Menu_Category toEntity(){
        Menu_Category menuCategory = Menu_Category.builder()
                .cno(this.cno)
                .category(this.category)
                .build();

        return menuCategory;
    }

}

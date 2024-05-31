package com.example.hellofx.product.controller;

import com.example.hellofx.product.dto.Menu_CategoryDTO;
import com.example.hellofx.product.dto.Menu_ProductDTO;
import com.example.hellofx.product.repository.MenuProductRepository;
import com.example.hellofx.product.service.MenuProductService;
import com.example.hellofx.product.service.MenuProductServiceImpl;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MenuPosViewController {

    @FXML
    private Button addProductBtn;

    @FXML
    private TabPane categoryTab;

    private final MenuProductService menuProductService;

    public MenuPosViewController() { // 기본생성자 초기화
        this(new MenuProductServiceImpl(new MenuProductRepository()));
    }

    public MenuPosViewController(MenuProductService menuProductService){ // service 의존성주입
        this.menuProductService = menuProductService;
    }

    private boolean isOpened = false; // 상품추가 창 열려있는지 확인

    public void initialize(){
        getTabList();

    }
    
    public void getTabList() { // 카테고리 목록 탭에 추가

        categoryTab.getTabs().clear(); // 초기화

        List<Menu_CategoryDTO> categoryDTOList = menuProductService.categoryList();
        List<String> categoryNameList =
                categoryDTOList.stream()
                        .map(Menu_CategoryDTO::getCategory)
                        .toList();

        List<String> categoryTabList = categoryNameList;
        for(String categoryName : categoryTabList){
            Tab tab = new Tab(categoryName);
            VBox vBox = new VBox();
            tab.setContent(vBox);

            tab.setOnSelectionChanged(event -> {
                if (tab.isSelected()){
                    getProductList(categoryName, vBox);
                }
            });
            categoryTab.getTabs().add(tab);
            tab.setStyle("-fx-pref-width: 100;");
        }
    }


    public void getProductList(String category, VBox vBox){ // 상품목록
        List<Menu_ProductDTO> productDTOList = menuProductService.productList(category);

        vBox.getChildren().clear();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        int row = 0;
        int col = 0;

        for (Menu_ProductDTO menuProductDTO : productDTOList){
            VBox productContainer = new VBox(); // hbox 생성
            productContainer.setPrefWidth(100);
            productContainer.setPrefHeight(100);
            productContainer.setStyle("-fx-background-color: " + menuProductDTO.getColor() + ";");
            productContainer.setPadding(new Insets(10));
            productContainer.setAlignment(Pos.CENTER);
            productContainer.setSpacing(10);
            productContainer.setCursor(Cursor.HAND);

            Label nameLabel = new Label(menuProductDTO.getProductName()); // 상품이름 라벨 생성
            productContainer.getChildren().add(nameLabel); // hbox에 담기

            Label priceLabel = new Label(String.valueOf(menuProductDTO.getProductPrice()));
            productContainer.getChildren().add(priceLabel);

            gridPane.add(productContainer, col, row);
            col++;
            if (col == 6){
                col = 0;
                row++;
            }
        }
        vBox.getChildren().add(gridPane);
    }
    
    
    public void openAddProduct(ActionEvent actionEvent) { // 상품 추가 창 클릭

        if(!isOpened){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hellofx/addProductView.fxml"));
                Parent parent = loader.load();

                // AddProductController의 closeWindow 메서드를 설정
                AddProductController addProductController = loader.getController();
                addProductController.setOnWindowClosed(() -> {
                    getTabList();
                    isOpened = false;
                });



                Scene scene = new Scene(parent, 800, 600);
                Stage stage = new Stage();
                stage.setTitle("상품추가");
                stage.setScene(scene);
                stage.show();

                isOpened = true;

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

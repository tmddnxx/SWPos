package com.example.hellofx.product.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPosViewController {

    @FXML
    private Button addProductBtn;

    private boolean isOpened = false; // 상품추가 창 열려있는지 확인

    public void openAddProduct(ActionEvent actionEvent) {

        if(!isOpened){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hellofx/addProductView.fxml"));
                Parent parent = loader.load();

                // AddProductController의 closeWindow 메서드를 설정
                AddProductController addProductController = loader.getController();
                addProductController.setOnWindowClosed(() -> isOpened = false);

                Scene scene = new Scene(parent, 700, 400);
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

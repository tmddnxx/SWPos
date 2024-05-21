package com.example.hellofx.product.controller;

import com.example.hellofx.product.dto.Menu_CategoryDTO;
import com.example.hellofx.product.dto.Menu_ProductDTO;
import com.example.hellofx.product.repository.MenuProductRepository;
import com.example.hellofx.product.service.MenuProductService;
import com.example.hellofx.product.service.MenuProductServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;


public class AddProductController {

    private final MenuProductService menuProductService;

    public AddProductController() { // 기본생성자 초기화
        this(new MenuProductServiceImpl(new MenuProductRepository()));
    }

    public AddProductController(MenuProductService menuProductService){ // service 의존성주입
        this.menuProductService = menuProductService;
    }

    @FXML
    private ComboBox categoryBox;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private Pane thumbProduct;

    @FXML
    private Label thumbPName;

    @FXML
    private Label thumbPPrice;

    private Runnable onWindowClosed;

    public void initialize(){
        getCategoryList();
    }

    public void getCategoryList() { // 카테고리 목록

        List<Menu_CategoryDTO> categoryDTOList = menuProductService.categoryList();
        List<String> categoryNameList =
                categoryDTOList.stream()
                .map(Menu_CategoryDTO::getCategory)
                .toList();
        categoryBox.getItems().clear();
        categoryBox.getItems().addAll(categoryNameList);
    }


    public void addCategory(ActionEvent actionEvent) { // 카테고리추가 '+' 버튼
        String categoryName = categoryBox.getEditor().getText();
        Menu_CategoryDTO menuCategoryDTO = new Menu_CategoryDTO();
        menuCategoryDTO.setCategory(categoryName);

        menuProductService.addCategory(menuCategoryDTO);
        initialize();
    }

    public void insertProduct(ActionEvent actionEvent) { // 상품등록
        String category = categoryBox.getValue().toString();
        String productName = productNameField.getText();
        int productPrice = Integer.parseInt(productPriceField.getText());
        String color = extractBackgroundColor(thumbProduct.getStyle());

        Menu_ProductDTO menuProductDTO = new Menu_ProductDTO();
        menuProductDTO.setCategory(category);
        menuProductDTO.setProductName(productName);
        menuProductDTO.setProductPrice(productPrice);
        menuProductDTO.setColor(color);
        
        menuProductService.addProduct(menuProductDTO);
        closeWindow(); // 창닫기
    }

    public void setOnWindowClosed(Runnable onWindowClosed) { // 서비스 내 창 닫힘확인
        this.onWindowClosed = onWindowClosed;
    }

    public void closeWindow(){ // 창 닫기 메서드
        Stage currentStage = (Stage) categoryBox.getScene().getWindow();
        currentStage.close();

        if (onWindowClosed != null) {
            onWindowClosed.run();
        }

    }

    public void cancelBtn(ActionEvent actionEvent) { // 취소버튼
        closeWindow();
    }

    public void selectColor(MouseEvent mouseEvent) { // 색상선택
        Circle colorCircle = (Circle) mouseEvent.getSource();
        Color color = (Color) colorCircle.getFill();
        String colorName = colorToHex(color);
        thumbProduct.setStyle("-fx-background-color: " + colorName + ";");
        
    }

    private String colorToHex(Color color) { // Hex코드로 색상 변환
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public void textInput(KeyEvent keyEvent) { // 키보드 입력시 미리보기
        String thumbName = productNameField.getText();
        String thumbPrice = productPriceField.getText();

        thumbPName.setText(thumbName);
        thumbPPrice.setText(thumbPrice);
    }

    private String extractBackgroundColor(String style) { // 배경색 가져오기
        String[] styleParts = style.split(";");
        for (String part : styleParts) {
            if (part.trim().startsWith("-fx-background-color:")) {
                return part.trim().substring("-fx-background-color:".length());
            }
        }
        return null;
    }
}

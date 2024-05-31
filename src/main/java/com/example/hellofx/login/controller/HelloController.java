package com.example.hellofx.login.controller;

import com.example.hellofx.login.dto.MemberDTO;
import com.example.hellofx.login.repository.MemberRepository;
import com.example.hellofx.login.service.MemberService;
import com.example.hellofx.login.service.MemberServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    public Button menuPos;

    @FXML
    private PasswordField pwInput;

    @FXML
    private TextField idInput;

    @FXML
    private Button loginBtn;

    private final MemberService memberService;

    public HelloController(){ // 기본생성자 초기화
        this(new MemberServiceImpl(new MemberRepository()));
    }

    public HelloController(MemberService memberService) { // service 의존성주입
        this.memberService = memberService;
    }

    @FXML
    public void loginBtnClick(ActionEvent actionEvent) {
        String id = idInput.getText();
        String pw = pwInput.getText();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setPw(pw);

        Long value = memberService.login(memberDTO);
        if (value == 1){
            successLogin();
        }else{
            System.out.println("로그인 실패");
        }
    }

    public void successLogin(){

        try {
            // mainHome.fxml 파일을 로드하여 해당하는 Parent 객체를 생성합니다.
            Parent sub = FXMLLoader.load(getClass().getResource("/com/example/hellofx/mainHome.fxml"));

            // 새로운 Scene 객체를 생성하고, 로드한 Parent 객체를 이용하여 화면을 구성합니다.
            Scene scene = new Scene(sub, 1000, 700);

            // 새로운 Stage 객체를 생성하여 새 창을 엽니다.
            Stage stage = new Stage();
            stage.setTitle("로그인성공!"); // 새 창의 제목을 설정합니다.
            stage.setScene(scene); // 새 창에 화면을 설정합니다.
            stage.show(); // 새 창을 표시합니다.

            // 현재 창(로그인 창)을 닫습니다.
            Stage currentStage = (Stage) loginBtn.getScene().getWindow(); // 로그인 버튼이 속한 창의 Stage 객체를 가져옵니다.
            currentStage.close(); // 현재 창을 닫습니다.


        }catch (IOException e){
            e.printStackTrace();
            // IOException이 발생할 경우 예외 처리를 수행합니다.
            // 이 부분은 파일 로드에 실패했을 때 실행됩니다.
            // 예외가 발생한 경우에는 새 창을 열지 않고 기존 창을 닫지 않습니다.
        }
    }

    public void menuPosClick(ActionEvent actionEvent) {
        System.out.println("매장운영 클릭");
        try {
            // mainHome.fxml 파일을 로드하여 해당하는 Parent 객체를 생성합니다.
            Parent sub = FXMLLoader.load(getClass().getResource("/com/example/hellofx/menuPosView.fxml"));

            // 새로운 Scene 객체를 생성하고, 로드한 Parent 객체를 이용하여 화면을 구성합니다.
            Scene scene = new Scene(sub, 1000, 700);

            // 새로운 Stage 객체를 생성하여 새 창을 엽니다.
            Stage stage = new Stage();
            stage.setTitle("메뉴포스기!"); // 새 창의 제목을 설정합니다.
            stage.setScene(scene); // 새 창에 화면을 설정합니다.
            stage.show(); // 새 창을 표시합니다.

            // 현재 창(로그인 창)을 닫습니다.
            Stage currentStage = (Stage) menuPos.getScene().getWindow(); // 로그인 버튼이 속한 창의 Stage 객체를 가져옵니다.
            currentStage.close(); // 현재 창을 닫습니다.


        }catch (IOException e){
            e.printStackTrace();
            // IOException이 발생할 경우 예외 처리를 수행합니다.
            // 이 부분은 파일 로드에 실패했을 때 실행됩니다.
            // 예외가 발생한 경우에는 새 창을 열지 않고 기존 창을 닫지 않습니다.
        }
    }
}
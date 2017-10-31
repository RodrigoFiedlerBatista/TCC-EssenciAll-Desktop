package model;

import control.LoginController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TCC extends Application {
    
    private static String tela = "Login.fxml";
    private static Stage palco = new Stage();
    
    public void iniciaStage(String stage){
        tela = stage;
        Stage stage1 = new Stage();
        
        //stage1.initStyle(StageStyle.TRANSPARENT);
        stage1.setTitle("EssenciAll");
        stage1.getIcons().add(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        //stage1.setResizable(false);
        try {
            start(stage1);
        } catch (Exception ex) {
            Logger.getLogger(TCC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/" + tela));
        //Parent root = FXMLLoader.load(getClass().getResource("/view/" + tela));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("EssenciAll");
        stage.getIcons().add(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        //stage.setResizable(false);
        stage.setScene(scene);
        if (tela.equals("Login.fxml")) {
            LoginController login = loader.getController();
            stage.setOnHidden(e -> {
                login.close();
                //Platform.exit();
            });
        }
        
        stage.show();
        palco = stage;
    }
    
    public void fechaTela(){
        palco.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TCC;

public class ContaUsuarioController implements Initializable {
    
    @FXML
    private ImageView imgFundo;

    @FXML
    private ImageView imgPedidos;

    @FXML
    private ImageView imgPerfilUsuario;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private ImageView imgConta;

    @FXML
    private JFXButton btnPedido;

    @FXML
    private ImageView imgEncontrarRevendedores;

    @FXML
    private JFXButton btnEcontrarRevendedor;

    @FXML
    private JFXButton btnConta;

    @FXML
    private ImageView imgPedido;

    @FXML
    private ImageView imgLogout;

    @FXML
    private JFXButton btnRevendedor;
    
    @FXML
    void sair(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }

    @FXML
    void pedir(ActionEvent event) {

    }
    
    private void iniciaImagem() {
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

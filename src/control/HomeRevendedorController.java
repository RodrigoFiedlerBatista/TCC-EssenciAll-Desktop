package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TCC;

public class HomeRevendedorController implements Initializable {

    @FXML
    private ImageView imgConta;
    
    @FXML
    void contaRevendedor(ActionEvent event) {
        
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaRevendedor.fxml");
     

    }
    
    public void iniciaImagem() {
        
        imgConta.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        iniciaImagem();
    }    
    
}

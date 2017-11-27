package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.TCC;

public class ContaUsuarioController implements Initializable {

    @FXML
    void conta(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeUsuario.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

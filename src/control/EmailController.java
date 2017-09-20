package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Alertas;
import model.Email;
import model.TCC;


public class EmailController implements Initializable {
    
    @FXML
    private JFXButton enviar;

    @FXML
    private JFXSpinner spinner;
    
    @FXML
    void enviar(ActionEvent event) {
        TCC tcc = new TCC();
        Email email = new Email();
        Alertas alerta = new Alertas();
        enviar.setVisible(false);
        spinner.setVisible(true);
        
        alerta.emailEnviado();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}

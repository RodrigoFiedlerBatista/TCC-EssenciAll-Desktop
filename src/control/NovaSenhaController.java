package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Alertas;
import model.CriptografiaOtp;
import model.Email;
import model.TCC;
import model.Usuario;
import model.jdbc.UsuarioDAO;

public class NovaSenhaController implements Initializable {

    @FXML
    private TextField textEmail;

    @FXML
    void enviar(ActionEvent event) {
        Usuario.atualizaUsuarios();
        boolean foi = false;
        Alertas alertas = new Alertas();
        for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
            if (textEmail.getText().equals(Usuario.getUsuarios().get(i).getEmail())) {
                Email email = new Email();
                UsuarioDAO usuario = new UsuarioDAO();
                CriptografiaOtp criptografia = new CriptografiaOtp();
                TCC tcc = new TCC();
                String novaSenha = criptografia.genCodigo();
                tcc.fechaTela();
                tcc.iniciaStage("Email.fxml");
                email.enviaEmailNovaSenha(textEmail.getText(), novaSenha);
                String chave = criptografia.genKey(novaSenha.length());
                String criptografada = criptografia.criptografa(novaSenha, chave);
                usuario.atualizaSenha(criptografada, chave, textEmail.getText());
                foi = true;
                tcc.fechaTela();
                tcc.iniciaStage("Login.fxml");
                alertas.senhaEnviada();
            }
        }
        if (!foi) {
            alertas.erroEmailNaoEncontrado();
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

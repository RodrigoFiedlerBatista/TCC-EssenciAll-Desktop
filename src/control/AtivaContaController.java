package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.CriptografiaOtp;
import model.Email;
import model.TCC;
import model.Usuario;
import model.jdbc.UsuarioDAO;

public class AtivaContaController implements Initializable {

    @FXML
    private JFXTextField textCodigo;
    
    @FXML
    private ImageView imgVoltar;

    @FXML
    private ImageView imgEssencial;
    
    @FXML
    private JFXButton btnVoltar;
    
    @FXML
    private JFXTextField textEmail;

    @FXML
    void enviar(ActionEvent event) {
        Email email = new Email();
        Alertas alertas = new Alertas();
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Email.fxml");
        email.enviaEmail(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getEmail(), Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getCodigo());
        tcc.fechaTela();
        tcc.iniciaStage("AtivaConta.fxml");
        alertas.emailEnviado();
        
    }
    
    @FXML
    void mudarEmail(ActionEvent event) {
        Alertas alertas = new Alertas();
        if (validaEmail()) {
            Usuario.atualizaUsuarios();
            boolean foi = true;
            for (int i = 0; i < Usuario.getUsuarios().size(); i++) {
                if (Usuario.getUsuarios().get(i).getEmail().equals(textEmail.getText())) {
                    alertas.erroCadastroUsuarioEmailExistente();
                    foi = false;
                }
            }
            if (foi) {
                Email email = new Email();
                TCC tcc = new TCC();
                UsuarioDAO usuario = new UsuarioDAO();
                CriptografiaOtp criptografia = new CriptografiaOtp();
                String codigo = criptografia.genCodigo();
                usuario.atualizaEmail(textEmail.getText(), codigo);
                tcc.fechaTela();
                tcc.iniciaStage("Email.fxml");
                email.enviaEmail(textEmail.getText(), codigo);  
                tcc.fechaTela();
                tcc.iniciaStage("Login.fxml");
                alertas.emailEnviado();
            }
            
        } else {
            alertas.erroCadastroUsuarioEmail();
        }
    }
    
    private boolean validaEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(textEmail.getText());
        if (m.find() && m.group().equals(textEmail.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }
    
    @FXML
    void ativar(ActionEvent event) {
        Alertas alerta = new Alertas();
        if (textCodigo.getText().equals(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getCodigo())) {
            TCC tcc = new TCC();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.setAtivado(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getId_usuario());
            tcc.fechaTela();
            tcc.iniciaStage("Login.fxml");
            alerta.ativouConta();
        } else {
            alerta.codigoIncorreto();
        }
    }
    
    private void iniciaImagem(){
        imgVoltar.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        imgEssencial.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
    }
    
    private void acaoBotoes(){
        btnVoltar.setOnMouseEntered(event ->{
            imgVoltar.setScaleX(1.2);
            imgVoltar.setScaleY(1.2);
        });
        
        btnVoltar.setOnMouseExited(event ->{
            imgVoltar.setScaleX(1.0);
            imgVoltar.setScaleY(1.0);
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        acaoBotoes();
        textCodigo.setStyle("-fx-text-fill: #ffffff; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textEmail.setStyle("-fx-text-fill: #ffffff; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
    }    
    
}

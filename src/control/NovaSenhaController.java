package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class NovaSenhaController implements Initializable {

    @FXML
    private ImageView iconeInicio;

    @FXML
    private JFXButton btnInicio;

    @FXML
    private JFXTextField textEmail;

    @FXML
    private ImageView fundo;
    
    @FXML
    private ImageView imgEmail;
    
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    @FXML
    void enviar(ActionEvent event) {
        UsuarioDAO usuario = new UsuarioDAO();
        usuarios = usuario.selectUsuario();
        boolean foi = false;
        Alertas alertas = new Alertas();
        for (int i = 0; i < usuarios.size(); i++) {
            if (textEmail.getText().equals(usuarios.get(i).getEmail())) {
                Email email = new Email();
                CriptografiaOtp criptografia = new CriptografiaOtp();
                TCC tcc = new TCC();
                String novaSenha = criptografia.genCodigo();
                tcc.fechaTela();
                tcc.iniciaStage("Email.fxml");
                email.enviaEmailNovaSenha(textEmail.getText(), novaSenha);
                String chave = criptografia.genKey(novaSenha.length());
                String criptografada = criptografia.criptografa(novaSenha, chave);
                usuario.atualizaSenha(criptografada, chave, textEmail.getText(), usuarios);
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
    
    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }
    
    private void acaoBotoes(){
        btnInicio.setOnMouseEntered(event -> {
            iconeInicio.setScaleX(1.1);
            iconeInicio.setScaleY(1.1);
        });
        btnInicio.setOnMouseExited(event -> {
            iconeInicio.setScaleX(1.0);
            iconeInicio.setScaleY(1.0);
        });
        
    }
    
    private void iniciaImagem(){
        fundo.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\oi.jpg"));
        iconeInicio.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        textEmail.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        imgEmail.setImage(new Image("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\envelope.png"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        acaoBotoes();
    }    
    
}

package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import model.GerenciaArquivos;
import model.GerenciaImagem;
import model.TCC;
import model.Usuario;
import model.jdbc.UsuarioDAO;

public class HomeRevendedorController implements Initializable {

    @FXML
    private JFXPasswordField textSenha;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private JFXTextField textLogin;

    @FXML
    private JFXPasswordField textSenha2;

    @FXML
    private ImageView imgConta;

    @FXML
    private JFXButton btnFechar;

    @FXML
    private JFXButton btnImagem;

    @FXML
    private JFXTextField textEmail;

    @FXML
    private ImageView imgCart;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgHome;
    
    private static String url = "";
    
    @FXML
    void trocarImagem(ActionEvent event) {
        GerenciaImagem gerencia = new GerenciaImagem();
        url = gerencia.getNovaImagem();
        imgPerfil.setImage(new Image("file:///" + url));
    }

    @FXML
    void fechar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaRevendedor.fxml");
    }

    @FXML
    void salvar(ActionEvent event) {
        UsuarioDAO usuario = new UsuarioDAO();
        Alertas alertas = new Alertas();
        GerenciaArquivos gerencia = new GerenciaArquivos();
        boolean trocou = false;
        if (!textLogin.getText().equals(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getLogin())) {
            usuario.atualizaLogin(textLogin.getText());
            Usuario.atualizaUsuarios();
            usuario.atualizaUrlImagem();
            gerencia.move(System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getUrl_imagem(), System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getLogin() + ".png");
            trocou = true;
        }
        if (!textEmail.getText().equals(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getEmail())) {
            
            if (validaEmail()) {
                Email email = new Email();
                TCC tcc = new TCC();
                CriptografiaOtp criptografia = new CriptografiaOtp();
                String codigo = criptografia.genCodigo();
                usuario.atualizaEmail(textEmail.getText(), codigo);
                tcc.fechaTela();
                tcc.iniciaStage("Email.fxml");
                email.enviaEmail(textEmail.getText(), codigo);  
                tcc.fechaTela();
                tcc.iniciaStage("HomeRevendedor.fxml");
                System.out.println(codigo);
                trocou = true;
            } else {
                alertas.erroCadastroUsuarioEmail();
            }
            
        }
        if (!url.equals(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getUrl_imagem())) {
            gerencia.deleta(System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getUrl_imagem());
            gerencia.copiaCola(url, System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getLogin() + ".png");
            trocou = true;
        }
        if (!textSenha.getText().equals("")) {
            CriptografiaOtp criptografia = new CriptografiaOtp();
            String senha = criptografia.decriptografa(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getSenha(), Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getChaveSenha());
            if (senha.equals(textSenha2.getText())) {
                String chave = criptografia.genKey(textSenha.getText().length());
                String novaSenha = criptografia.criptografa(textSenha.getText(), chave);
                usuario.atualizaSenha(novaSenha, chave);
                trocou = true;
            } else {
                alertas.erroConfirmacaoSenha();
            }
        }
        if (trocou) {
            alertas.informacoesAlteradas();
            Usuario.atualizaUsuarios();
        } else {
            alertas.informacoesInalteradas();
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
    void sair(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void conta(ActionEvent event) {

    }

    @FXML
    void revendedores(ActionEvent event) {

    }

    @FXML
    void pedir(ActionEvent event) {

    }
    
    public void atualizaUrl(){
        url = Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getUrl_imagem();
    }
    
    public void iniciaImagem() {
        imgPerfil.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getUrl_imagem()));
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgConta.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgCart.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\cart.png")); 
        //imgMenu.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\menu.png"));
        //imgFechar.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\fecharBranco.png"));
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png")); 
        imgHome.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\homeBranco.png"));
    }
    
    private void acaoBotoes(){
        /*btnFechar.setOnMouseEntered(event ->{
            imgFechar.setScaleX(1.1);
            imgFechar.setScaleY(1.1);
        });
        
        btnFechar.setOnMouseExited(event ->{
            imgFechar.setScaleX(1.0);
            imgFechar.setScaleY(1.0);
        });*/
        
        btnImagem.setOnMouseEntered(event ->{
            imgPerfil.setScaleX(1.1);
            imgPerfil.setScaleY(1.1);
        });
        
        btnImagem.setOnMouseExited(event ->{
            imgPerfil.setScaleX(1.0);
            imgPerfil.setScaleY(1.0);
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        Usuario.atualizaUsuarios();
        acaoBotoes();
        textLogin.setText(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getLogin());
        textEmail.setText(Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getEmail());
        textLogin.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        textEmail.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        textSenha.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        textSenha2.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
    }    
    
}

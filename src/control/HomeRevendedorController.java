package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tooltip;
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
    private JFXButton btnConta;

    @FXML
    private JFXButton btnRevendedor;

    @FXML
    private ImageView imgFundo;

    @FXML
    private JFXPasswordField textSenha;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private JFXTextField textEmail;

    @FXML
    private ImageView imgEssencial;

    @FXML
    private JFXButton btnSair;

    @FXML
    private ImageView imgFechar;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgHome;

    @FXML
    private JFXButton btnHome;
    
    private static String url = "";
    
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    
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
        boolean verificou = true;
        usuarios = usuario.selectUsuario();
        if (!textLogin.getText().equals(usuarios.get(Usuario.getUsuarioLogado()).getLogin())) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).getLogin().equals(textLogin.getText())) {
                    verificou = false;
                }
            }
            if (verificou) {
                usuario.atualizaLogin(textLogin.getText(), usuarios);
                usuario.atualizaUrlImagem(textLogin.getText(), usuarios);
                gerencia.move(System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem(), System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + textLogin.getText() + ".png");
                usuarios = usuario.selectUsuario();
                url = usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem();
                trocou = true;
            } else {
                alertas.erroCadastroUsuarioLoginExistente();
                return;
            }
        }
        if (!textEmail.getText().equals(usuarios.get(Usuario.getUsuarioLogado()).getEmail())) {
            if (validaEmail()) {
                for (Usuario usuario1 : usuarios) {
                    if (usuario1.getEmail().equals(textEmail.getText())) {
                        verificou = false;
                    }
                }
                if (verificou) {
                    Email email = new Email();
                    TCC tcc = new TCC();
                    CriptografiaOtp criptografia = new CriptografiaOtp();
                    String codigo = criptografia.genCodigo();
                    usuario.atualizaEmail(textEmail.getText(), codigo, usuarios);
                    tcc.fechaTela();
                    tcc.iniciaStage("Email.fxml");
                    email.enviaEmail(textEmail.getText(), codigo);  
                    tcc.fechaTela();
                    
                    tcc.iniciaStage("HomeRevendedor.fxml");
                    System.out.println(codigo);
                    trocou = true;
                } else {
                    alertas.erroCadastroUsuarioEmailExistente();
                    return;
                }
            } else {
                alertas.erroCadastroUsuarioEmail();
                return;
            }
        }
        if (!url.equals(usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem())) {
            gerencia.deleta(System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem());
            gerencia.copiaCola(url, System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + ".png");
            trocou = true;
        }
        if (!textSenha.getText().equals("")) {
            CriptografiaOtp criptografia = new CriptografiaOtp();
            String senha = criptografia.decriptografa(usuarios.get(Usuario.getUsuarioLogado()).getSenha(), usuarios.get(Usuario.getUsuarioLogado()).getChaveSenha());
            if (senha.equals(textSenha2.getText())) {
                String chave = criptografia.genKey(textSenha.getText().length());
                String novaSenha = criptografia.criptografa(textSenha.getText(), chave);
                usuario.atualizaSenha(novaSenha, chave, usuarios);
                trocou = true;
            } else {
                alertas.erroConfirmacaoSenha();
                return;
            }
        }
        if (trocou) {
            alertas.informacoesAlteradas();
            usuarios = usuario.selectUsuario();
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
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaRevendedor.fxml");
    }

    @FXML
    void conta(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeRevendedor.fxml");
    }

    @FXML
    void revendedores(ActionEvent event) {

    }

    @FXML
    void pedir(ActionEvent event) {

    }
    
    public void atualizaUrl(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        url = usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem();
    }
    
    public void iniciaImagem() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        url = usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem();
        imgPerfil.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + url));
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgConta.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        //imgPerfil.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\cart.png")); 
        //imgMenu.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\menu.png"));
        //imgFechar.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\fecharBranco.png"));
        imgFundo.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\conta1.jpg"));
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
        btnConta.setOnMouseEntered(event -> {
            imgConta.setScaleX(1.1);
            imgConta.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta.setScaleX(1.0);
            imgConta.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        btnHome.setOnMouseEntered(event -> {
            imgHome.setScaleX(1.1);
            imgHome.setScaleY(1.1);
        });
        btnHome.setOnMouseExited(event -> {
            imgHome.setScaleX(1.0);
            imgHome.setScaleY(1.0);
        });
        btnHome.setTooltip(new Tooltip("Home"));
        btnRevendedor.setOnMouseEntered(event -> {
            imgRevendedores.setScaleX(1.1);
            imgRevendedores.setScaleY(1.1);
        });
        btnRevendedor.setOnMouseExited(event -> {
            imgRevendedores.setScaleX(1.0);
            imgRevendedores.setScaleY(1.0);
        });
        btnRevendedor.setTooltip(new Tooltip("Revendedor"));
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Sair"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        iniciaImagem();
        acaoBotoes();
        textLogin.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        textEmail.setText(usuarios.get(Usuario.getUsuarioLogado()).getEmail());
        textLogin.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        textEmail.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        textSenha.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
        textSenha2.setStyle("-fx-prompt-text-fill: white; -fx-text-fill: white");
    }    
    
}

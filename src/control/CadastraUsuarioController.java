package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

public class CadastraUsuarioController implements Initializable {

    @FXML
    private PasswordField textSenha;

    @FXML
    private ImageView imagemPerfil;

    @FXML
    private ImageView imagemCadastrar;

    @FXML
    private ImageView imagemIconeSair;

    @FXML
    private TextField textLogin;

    @FXML
    private PasswordField textSenha2;

    @FXML
    private ImageView imgEssencial;

    @FXML
    private TextField textEmail;
    
    @FXML
    private JFXButton btnVoltar;
    
    @FXML
    private Button btnImagem;

    @FXML
    private RadioButton checkRevendedor;

    @FXML
    private RadioButton checkRevendedor1;
    
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    private static String url = System.getProperty("user.dir") + "\\src\\imagens\\icon-clientes.png";
    
    @FXML
    void cadastra(ActionEvent event) {
        
        if (verificaValores()) {
            GerenciaArquivos gerenciaArquivos = new GerenciaArquivos();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = new Usuario();
            Email email = new Email();
            CriptografiaOtp criptografia = new CriptografiaOtp();
            TCC tcc = new TCC();
            Alertas alerta = new Alertas();
            usuario.setLogin(textLogin.getText());
            usuario.setEmail(textEmail.getText());
            if (checkRevendedor.isSelected()) {
                usuario.setRevendedor(true);
            } else {
                usuario.setRevendedor(false);
            }
            usuario.setUrl_imagem(usuario.getLogin() + ".png");
            gerenciaArquivos.copiaCola(url, System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuario.getLogin() + ".png");
            String chave = criptografia.genKey(textSenha.getText().length());
            usuario.setChaveSenha(chave);
            usuario.setSenha(criptografia.criptografa(textSenha.getText(), chave));
            String codigo = criptografia.genCodigo();
            usuario.setCodigo(codigo);
            usuario.setAtivado(false);
            usuarioDAO.addUsuario(usuario);
            usuarios = usuarioDAO.selectUsuario();
            tcc.fechaTela();
            tcc.iniciaStage("Email.fxml");
            try {
                email.enviaEmail(usuario.getEmail(), usuario.getCodigo());
            } catch (Exception e) {
                tcc.fechaTela();
                
            }
            
            tcc.fechaTela();
            
            if (checkRevendedor.isSelected()) {
                Usuario.setUsuarioLogado(usuarios.get(usuarios.size() - 1).getId_usuario());
                tcc.iniciaStage("Telaempresas.fxml");
                
            } else {
                tcc.iniciaStage("Login.fxml");
            }
            alerta.emailEnviado();
        } else {
            System.out.println("Não cadastrou!");
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }
    
    private boolean verificaValores(){
        Alertas alerta = new Alertas();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < usuarios.size(); i++) {
            if (textLogin.getText().equals(usuarios.get(i).getLogin())) {
                alerta.erroCadastroUsuarioLoginExistente();
                return false;
            } else if (textEmail.getText().equals(usuarios.get(i).getEmail())) {
                alerta.erroCadastroUsuarioEmailExistente();
                return false;
            }
        }
        if (textLogin.getText().length() > 100 || textLogin.getText().equals("")) {
            alerta.erroCadastroUsuarioLogin();
            return false;
        } else if (textSenha.getText().length() > 100 || textSenha.getText().equals("")) {
            alerta.erroCadastroUsuarioSenha();
            return false;
        } else if (validaEmail()) {
            alerta.erroCadastroUsuarioEmail();
            return false;
        } else if (!textSenha2.getText().equals(textSenha.getText())) {
            alerta.erroCadastroUsuarioSenhaDiferente();
            return false;
        } else if (!checkRevendedor.isSelected() && !checkRevendedor1.isSelected()){
            alerta.erroCadastroUsuarioCheckBox();
            return false;
        }
        
        return true;
    }
    
    @FXML
    void trocarImagem(ActionEvent event) {
        GerenciaImagem gerencia = new GerenciaImagem();
        url = gerencia.getNovaImagem();
        imagemPerfil.setImage(new Image("file:///" + url));
    }

    
    private boolean validaEmail(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(textEmail.getText());
        if (m.find() && m.group().equals(textEmail.getText())) {
            return false;
        } else {
            return true;
        }
    }
    
    private void criaGrupo(){
        ToggleGroup grupo = new ToggleGroup();
        checkRevendedor.setToggleGroup(grupo);
        checkRevendedor1.setToggleGroup(grupo);
    }
    
    private void iniciaImagem(){
        //imagemCadastrar.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\PerfumeCadastrar.jpg"));
        imagemIconeSair.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        imagemPerfil.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\user.png"));
        imgEssencial.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
    }
    
    private void acaoBotoes(){
        btnVoltar.setOnMouseEntered(event ->{
            imagemIconeSair.setScaleX(1.1);
            imagemIconeSair.setScaleY(1.1);
        });
        
        btnVoltar.setOnMouseExited(event ->{
            imagemIconeSair.setScaleX(1.0);
            imagemIconeSair.setScaleY(1.0);
        });
        
        btnImagem.setOnMouseEntered(event ->{
            imagemPerfil.setScaleX(1.1);
            imagemPerfil.setScaleY(1.1);
        });
        
        btnImagem.setOnMouseExited(event ->{
            imagemPerfil.setScaleX(1.0);
            imagemPerfil.setScaleY(1.0);
        });
    }
    
    private void textCor() {
        textLogin.setStyle("-fx-text-fill: #14fff3; -fx-prompt-text-fill: white");
        textSenha.setStyle("-fx-text-fill: #14fff3; -fx-prompt-text-fill: white");
        textSenha2.setStyle("-fx-text-fill: #14fff3; -fx-prompt-text-fill: white");
        textEmail.setStyle("-fx-text-fill: #14fff3; -fx-prompt-text-fill: white");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        iniciaImagem();
        criaGrupo();
        acaoBotoes();
        textCor();
    }    
    
}

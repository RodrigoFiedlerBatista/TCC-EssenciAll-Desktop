package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Alertas;
import model.Carrinho;
import model.CriptografiaOtp;
import model.TCC;
import model.Usuario;
import model.jdbc.UsuarioDAO;

public class LoginController implements Initializable {

    @FXML
    private PasswordField textSenha;

    @FXML
    private TextField textLogin;

    @FXML
    private ImageView ImagemIconeCadastrar;
    
    @FXML
    private ImageView img_desodorante;
    
    @FXML
    private ImageView imgEssencial;
    
    @FXML
    private ImageView imgLogin;
    
    @FXML
    private ImageView imgSenha;
    
    @FXML
    private JFXButton btnCadastrar;
    
    @FXML
    private JFXButton btnNovasenha;
    
    @FXML
    private ImageView ImagemIconeNovasenha;
    
    @FXML
    private JFXButton botaoLogin;
    
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    
    @FXML
    void novasenha(ActionEvent event) throws InterruptedException {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("NovaSenha.fxml");
        thread.interrupt();
    }
    
    private static boolean logou;
    
    @FXML
    void login(ActionEvent event) throws InterruptedException {
        CriptografiaOtp criptografia = new CriptografiaOtp();
        TCC tcc = new TCC();
        Alertas alerta = new Alertas();
        String senha;
        logou = false;
        for (int j = 0; j < usuarios.size(); j++) {
            senha = criptografia.decriptografa(usuarios.get(j).getSenha(), usuarios.get(j).getChaveSenha());
            System.out.println(Usuario.getUsuarioLogado());
            if (senha.equals(textSenha.getText()) && textLogin.getText().equals(usuarios.get(j).getLogin())) {
                
                if (usuarios.get(j).isAtivado()) {
                    if (usuarios.get(j).isRevendedor()) {
                        Usuario.setUsuarioLogado(j);
                        tcc.fechaTela();
                        tcc.iniciaStage("ContaRevendedor.fxml");
                        logou = true;
                        thread.interrupt();
                    } else {
                        Usuario.setUsuarioLogado(j);
                        tcc.fechaTela();
                        tcc.iniciaStage("ContaUsuario.fxml");
                        logou = true;
                        thread.interrupt();
                    }
                } else {
                    logou = true;
                    Usuario.setUsuarioLogado(j);
                    tcc.fechaTela();
                    tcc.iniciaStage("AtivaConta.fxml");
                    thread.interrupt();
                }
            } else {
                System.out.println("Não deu:" + j);
            }
        }
        
        if (logou == false) {
            alerta.erroLogin();
        }
        
    }
    
    @FXML
    void fechar(ActionEvent event) throws InterruptedException {
        TCC tcc = new TCC();
        tcc.fechaTela();
        thread.interrupt();
    }
    
    @FXML
    void cadastrar(ActionEvent event) throws InterruptedException {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("CadastraUsuario.fxml");
        thread.interrupt();
    }
    
    private void iniciaImagem(){
        ImagemIconeCadastrar.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\cadastrarr.png"));
        //img_desodorante.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\pefumelogo.jpg"));
        imgEssencial.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        imgLogin.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\userBranco.png"));
        imgSenha.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\keyBranco.png"));
        ImagemIconeNovasenha.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\nova senhaBranco.png"));
        
    }
    
    public void acaoBotoes(){
        btnCadastrar.setOnMouseEntered(event ->{
            ImagemIconeCadastrar.setScaleX(1.1);
            ImagemIconeCadastrar.setScaleY(1.1);
        });
        
        btnCadastrar.setOnMouseExited(event ->{
            ImagemIconeCadastrar.setScaleX(1.0);
            ImagemIconeCadastrar.setScaleY(1.0);
        });
        
        btnNovasenha.setOnMouseEntered(event ->{
            ImagemIconeNovasenha.setScaleX(1.1);
            ImagemIconeNovasenha.setScaleY(1.1);
        });
        
        btnNovasenha.setOnMouseExited(event ->{
            ImagemIconeNovasenha.setScaleX(1.0);
            ImagemIconeNovasenha.setScaleY(1.0);
        });
        
    }
    
    private void effectButton() {
        textLogin.setOnMouseEntered(s -> {
            textLogin.setOpacity(1.0);
        });
        textLogin.setOnMouseExited(s -> {
            textLogin.setOpacity(0.7);
        });
        
        textSenha.setOnMouseEntered(s -> {
            textSenha.setOpacity(1.0);
        });
        textSenha.setOnMouseExited(s -> {
            textSenha.setOpacity(0.7);
        });
        
        botaoLogin.setOnMouseEntered(s -> {
            botaoLogin.setOpacity(1.0);
        });
        botaoLogin.setOnMouseExited(s -> {
            botaoLogin.setOpacity(0.7);
        });
        
    }
    
    int i = 0;
    Thread thread;

    private void task() {
        Task t = new Task() {
            @Override
            protected Object call() throws Exception {
                while (i < 10) {
                    Thread.sleep(4000);
                    switch (i) {
                        case 0:
                            img_desodorante.setImage(new Image("/imagens/PerfumeCadastrar.jpg"));
                            fadeIn(img_desodorante);
                            i++;
                            break;
                        case 1:
                            img_desodorante.setImage(new Image("/imagens/esse preto.jpg"));
                            fadeIn(img_desodorante);
                            i++;
                            break;
                        case 2:
                            img_desodorante.setImage(new Image("/imagens/perfumelogo.jpg"));
                            fadeIn(img_desodorante);
                            i= 0;
                            break;
                        default:
                            break;
                    }
                }
                return null;
            }
        };
        thread = new Thread(t);
        thread.start();
    }
    
    public void fadeIn(Node node) {
        FadeTransition fade = new FadeTransition();
        fade.setFromValue(0.1);
        fade.setToValue(0.9);
        fade.setDuration(Duration.seconds(1.5));
        fade.setNode(node);
        fade.play();
    }
    
    public void close(){
        thread.interrupt();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        iniciaImagem();
        acaoBotoes();
        textLogin.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textSenha.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        effectButton();
        img_desodorante.setImage(new Image("/imagens/perfumelogo.jpg"));
        fadeIn(img_desodorante);
        task();
        Carrinho.clear();
    }    
    
}

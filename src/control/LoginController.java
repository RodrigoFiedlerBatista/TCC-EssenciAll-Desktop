package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import model.CriptografiaOtp;
import model.TCC;
import model.Usuario;

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
    
    @FXML
    void novasenha(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("NovaSenha.fxml");
    }
    
    private static boolean logou = false;
    
    @FXML
    void login(ActionEvent event) {
        CriptografiaOtp criptografia = new CriptografiaOtp();
        TCC tcc = new TCC();
        Alertas alerta = new Alertas();
        String senha;
        for (int j = 0; j< Usuario.getUsuarios().size(); j++) {
            senha = criptografia.decriptografa(Usuario.getUsuarios().get(j).getSenha(), Usuario.getUsuarios().get(j).getChaveSenha());
            System.out.println(Usuario.getUsuarioLogado());
            if (senha.equals(textSenha.getText()) && textLogin.getText().equals(Usuario.getUsuarios().get(j).getLogin())) {
                
                if (Usuario.getUsuarios().get(j).isAtivado()) {
                    if (Usuario.getUsuarios().get(j).isRevendedor()) {
                        Usuario.setUsuarioLogado(j);
                        tcc.fechaTela();
                        tcc.iniciaStage("ContaRevendedor.fxml");
                        logou = true;
                    } else {
                        Usuario.setUsuarioLogado(j);
                        tcc.fechaTela();
                        tcc.iniciaStage("HomeUsuario.fxml");
                        logou = true;
                    }
                } else {
                    logou = true;
                    Usuario.setUsuarioLogado(j);
                    tcc.fechaTela();
                    tcc.iniciaStage("AtivaConta.fxml");
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
    void fechar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
    }
    
    @FXML
    void cadastrar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("CadastraUsuario.fxml");
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
        
        Thread thread = new Thread(t);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Usuario.atualizaUsuarios();
        iniciaImagem();
        acaoBotoes();
        textLogin.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textSenha.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        effectButton();
        img_desodorante.setImage(new Image("/imagens/perfumelogo.jpg"));
        fadeIn(img_desodorante);
        task();
    }    
    
}

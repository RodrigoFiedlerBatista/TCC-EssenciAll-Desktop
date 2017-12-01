package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TCC;
import model.Usuario;
import model.jdbc.UsuarioDAO;

public class ContaRevendedorController implements Initializable {

    @FXML
    private ImageView imgPerfilRevendedor;
    
    @FXML
    private ImageView imgRevendedores;

    @FXML
    private ImageView imgCart;

    @FXML
    private ImageView imgLogout;
    
    @FXML
    private Label labelNome;

    @FXML
    private ImageView imgConta1;
    
    @FXML
    private ImageView imgEstoque;
    
    @FXML
    private ImageView imgProdutos;
    
    @FXML
    private JFXButton btnProdutos;
 
    @FXML
    private JFXButton btnEstoque;
    
    @FXML
    private ImageView imgFundoContRevend;
    
    @FXML
    private JFXButton btnConta;
    
    @FXML
    private JFXButton btnSair;
    
    private ObservableList<Usuario> usuarios;
    
    @FXML
    void pedir(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }

    @FXML
    void conta(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeRevendedor.fxml");
    }

    @FXML
    void clientes(ActionEvent event) {

    }

    @FXML
    void fechar(ActionEvent event) {
        

    }
    
    public void acaoBotoes() {
        TCC tcc = new TCC();
        btnProdutos.setOnMouseClicked(event -> {
            tcc.fechaTela();
            tcc.iniciaStage("CadastraProduto.fxml");
        });        
        btnEstoque.setOnMouseClicked(event -> {
            tcc.fechaTela();
            tcc.iniciaStage("Estoque.fxml"); 
        });
        btnEstoque.setOnMouseEntered(event -> {
            imgEstoque.setScaleX(1.1);
            imgEstoque.setScaleY(1.1);
        });
        btnEstoque.setOnMouseExited(event -> {
            imgEstoque.setScaleX(1.0);
            imgEstoque.setScaleY(1.0);
        });
        btnEstoque.setTooltip(new Tooltip("Estoque"));
        btnConta.setOnMouseEntered(event -> {
            imgConta1.setScaleX(1.1);
            imgConta1.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta1.setScaleX(1.0);
            imgConta1.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
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
    
    public void iniciaImagem(){
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\Imagem\\left-arrow-angle.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgCart.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\cart.png"));
        imgProdutos.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\perfumeProdutos.png"));
        imgEstoque.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\icone-produtos2.png"));
        imgRevendedores.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgLogout.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        imgFundoContRevend.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\borda.png"));
        imgPerfilRevendedor.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem()));
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuario = new UsuarioDAO();
        usuarios = usuario.selectUsuario();
        iniciaImagem();
        acaoBotoes();
    }    
    
}

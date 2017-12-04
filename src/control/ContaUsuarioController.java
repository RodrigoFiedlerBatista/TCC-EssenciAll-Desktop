package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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

public class ContaUsuarioController implements Initializable {
    
    @FXML
    private ImageView imgFundo;

    @FXML
    private ImageView imgPedidos;

    @FXML
    private ImageView imgPerfilUsuario;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private ImageView imgConta;

    @FXML
    private JFXButton btnPedido;

    @FXML
    private ImageView imgEncontrarRevendedores;

    @FXML
    private JFXButton btnEncontrarRevendedor;

    @FXML
    private JFXButton btnConta;

    @FXML
    private ImageView imgPedido;

    @FXML
    private ImageView imgLogout;
    
    @FXML
    private Label labelNome;

    @FXML
    private JFXButton btnRevendedor;
    
    @FXML
    private JFXButton btnSair;
    
    @FXML
    private JFXButton btnPedir;
    
    private ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    
    @FXML
    void sair(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }

    @FXML
    void pedir(ActionEvent event) {

    }
    
    private void iniciaImagem() {
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        imgFundo.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Avon-Red.jpg"));
        imgPedido.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\PedidosBranco.png"));
        imgEncontrarRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgPerfilUsuario.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem()));
        imgPedidos.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\PedidosBranco.png"));
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgConta.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
    }
    
    private void acaoBotoes() {
        btnPedido.setOnMouseEntered(event -> {
            imgPedido.setScaleX(1.1);
            imgPedido.setScaleY(1.1);
        });
        btnPedido.setOnMouseExited(event -> {
            imgPedido.setScaleX(1.0);
            imgPedido.setScaleY(1.0);
        });
        btnPedido.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("PedidoCliente.fxml");
        });
        btnPedido.setTooltip(new Tooltip("Ver Pedidos"));
        btnEncontrarRevendedor.setOnMouseEntered(event -> {
            imgEncontrarRevendedores.setScaleX(1.1);
            imgEncontrarRevendedores.setScaleY(1.1);
        });
        btnEncontrarRevendedor.setOnMouseExited(event -> {
            imgEncontrarRevendedores.setScaleX(1.0);
            imgEncontrarRevendedores.setScaleY(1.0);
        });
        btnEncontrarRevendedor.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("EncontrarRevendedor.fxml");
        });
        btnEncontrarRevendedor.setTooltip(new Tooltip("Encontrar Revendedor"));
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Sair"));
        btnConta.setOnMouseEntered(event -> {
            imgConta.setScaleX(1.1);
            imgConta.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta.setScaleX(1.0);
            imgConta.setScaleY(1.0);
        });
        btnConta.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("HomeUsuario.fxml");
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        btnRevendedor.setOnMouseEntered(event -> {
            imgRevendedores.setScaleX(1.1);
            imgRevendedores.setScaleY(1.1);
        });
        btnRevendedor.setOnMouseExited(event -> {
            imgRevendedores.setScaleX(1.0);
            imgRevendedores.setScaleY(1.0);
        });
        btnRevendedor.setTooltip(new Tooltip("Revendedores"));
        btnPedir.setOnMouseEntered(event -> {
            imgPedidos.setScaleX(1.1);
            imgPedidos.setScaleY(1.1);
        });
        btnPedir.setOnMouseExited(event -> {
            imgPedidos.setScaleX(1.0);
            imgPedidos.setScaleY(1.0);
        });
        btnPedir.setTooltip(new Tooltip("Realizar Pedido"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.selectUsuario();
        iniciaImagem();
        acaoBotoes();
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
    }    
    
}

package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.Pedido;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.PedidoDAO;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class PedidosController implements Initializable {
    
    @FXML
    private JFXButton btnRevendedores;

    @FXML
    private JFXRadioButton checkNaoFinalizado;

    @FXML
    private ImageView imgPesquisa;

    @FXML
    private ImageView imgConta;

    @FXML
    private Label labelNome;

    @FXML
    private JFXTextField textPesquisa;

    @FXML
    private JFXButton btnPedido;

    @FXML
    private JFXButton btnConta;

    @FXML
    private JFXRadioButton checkFinalizado;

    @FXML
    private ImageView imgPerfilRevendedor;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private JFXButton btnSair;

    @FXML
    private ImageView imgCart;
    
    @FXML
    private ImageView imgLogout;
    
    @FXML
    private JFXButton btnHome;

    @FXML
    private ImageView imgHome;
    
    @FXML
    private TableColumn<Pedido, Integer> colID;

    @FXML
    private TableView<Pedido> tbPedido;

    @FXML
    private TableColumn<Pedido, String> colStatus;
    
    private Pedido pedido;

    @FXML
    void conta(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeUsuario.fxml");
    }

    @FXML
    void revendedores(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("EncontrarRevendedor.fxml");
    }

    @FXML
    void carrinho(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Carrinho.fxml");
    }
    
    @FXML
    void home(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaUsuario.fxml");
    }

    @FXML
    void verPedido(ActionEvent event) {
        Alertas alertas = new Alertas();
        if (pedido == null) {
            alertas.erroSelecionePedido();
        } else {
            InformacaoPedidoController.setPedido(pedido);
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("InformacaoPedido.fxml");
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }
    
    private void iniciaTabela() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ObservableList<Pedido> pedidos = pedidoDAO.selectPedido();
        ObservableList<Pedido> pedidoDefinitivo = FXCollections.observableArrayList();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getCliente() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                pedidoDefinitivo.add(pedidos.get(i));
            }
        }
        tbPedido.setItems(pedidoDefinitivo);
        colID.setCellValueFactory(new PropertyValueFactory("id_pedido"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        tbPedido.setPlaceholder(new Label("Nenhum Pedido Realizado!"));
    }
    
    private void selecionaTabela() {
        tbPedido.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Pedido> observable, Pedido oldValue, Pedido newValue) -> {
            pedido = newValue;
        });
    }
    
    private void iniciaImagem() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        imgPesquisa.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\pesquisa.png"));
        imgConta.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\contaBranco.png"));
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgCart.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\PedidosBranco.png"));
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        imgHome.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\homeBranco.png"));
        imgPerfilRevendedor.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + ".png"));
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
    }   
    
    private void acaoBotoes() {
        btnConta.setOnMouseEntered(event -> {
            imgConta.setScaleX(1.1);
            imgConta.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta.setScaleX(1.0);
            imgConta.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        btnRevendedores.setOnMouseEntered(event -> {
            imgRevendedores.setScaleX(1.1);
            imgRevendedores.setScaleY(1.1);
        });
        btnRevendedores.setOnMouseExited(event -> {
            imgRevendedores.setScaleX(1.0);
            imgRevendedores.setScaleY(1.0);
        });
        btnRevendedores.setTooltip(new Tooltip("Encontrar Revendedor"));
        btnPedido.setOnMouseEntered(event -> {
            imgCart.setScaleX(1.1);
            imgCart.setScaleY(1.1);
        });
        btnPedido.setOnMouseExited(event -> {
            imgCart.setScaleX(1.0);
            imgCart.setScaleY(1.0);
        });
        btnPedido.setTooltip(new Tooltip("Carrinho"));
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Sair"));
        btnHome.setOnMouseEntered(event -> {
            imgHome.setScaleX(1.1);
            imgHome.setScaleY(1.1);
        });
        btnHome.setOnMouseExited(event -> {
            imgHome.setScaleX(1.0);
            imgHome.setScaleY(1.0);
        });
        btnHome.setTooltip(new Tooltip("Home"));
    }
    
    private void criaGrupo() {
        ToggleGroup grupo = new ToggleGroup();
        checkFinalizado.setToggleGroup(grupo);
        checkNaoFinalizado.setToggleGroup(grupo);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();
        selecionaTabela();
        iniciaImagem();
        acaoBotoes();
        criaGrupo();
    }    
    
}

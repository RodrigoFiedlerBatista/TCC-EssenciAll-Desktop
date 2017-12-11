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
    private ImageView imgEstoque;

    @FXML
    private Label labelNome;

    @FXML
    private ImageView imgFundoContRevend;

    @FXML
    private JFXButton btnConta;

    @FXML
    private JFXButton btnPedidos;

    @FXML
    private ImageView imgPedidos;

    @FXML
    private ImageView imgPerfilRevendedor;

    @FXML
    private JFXButton btnSair;

    @FXML
    private ImageView imgEssencial;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgConta1;

    @FXML
    private ImageView imgHome;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnEstoque;
    
    private ObservableList<Usuario> usuarios;

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
    
    public void acaoBotoes() {
        TCC tcc = new TCC();       
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
        /*-----------------------------------------*/       
        btnConta.setOnMouseExited(event -> {
            imgConta1.setScaleX(1.0);
            imgConta1.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        /*-----------------------------------------*/
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Sair"));     
        btnPedidos.setOnMouseClicked(event -> {
            tcc.fechaTela();
            tcc.iniciaStage("pedidosRevendedor.fxml");
        });
        btnPedidos.setOnMouseEntered(event -> {
            imgPedidos.setScaleX(1.1);
            imgPedidos.setScaleY(1.1);
        });
        btnPedidos.setOnMouseExited(event -> {
            imgPedidos.setScaleX(1.0);
            imgPedidos.setScaleY(1.0);
        });
        btnPedidos.setTooltip(new Tooltip("Pedidos"));
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
    
    public void iniciaImagem(){
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\Imagem\\left-arrow-angle.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgEstoque.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\icone-produtos2.png"));
        imgHome.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\homeBranco.png"));
        imgLogout.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        imgFundoContRevend.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\conta6.jpg"));
        imgPerfilRevendedor.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getUrl_imagem()));
        imgPedidos.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\perfumeProdutos.png"));
        imgEssencial.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
    }
    
    /*private void checaPedidos() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        ObservableList<Pedido> pedidos = pedidoDAO.selectPedido();
        ObservableList<Produto> produtosUsuario = FXCollections.observableArrayList();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                produtosUsuario.add(produtos.get(i));
            }
        }
        int notificou = 0;
        for (int i = 0; i < pedidos.size(); i++) {
            for (int j = 0; j < pedidos.get(i).getProdutos().size(); j++) {
                for (int k = 0; k < produtosUsuario.size(); k++) {
                    if (pedidos.get(i).getProdutos().get(j) == produtosUsuario.get(k).getId_produto()) {
                        if (!pedidos.get(i).getVisualizado().get(k)) {
                            notificou++;
                        }
                    }
                }
            }
        }
        if (notificou > 0) {
            labelNotificacao.setVisible(true);
            labelNotificacao.setText(String.valueOf(notificou));
            circuloNotificacao.setVisible(true);
        } else {
            labelNotificacao.setVisible(false);
            circuloNotificacao.setVisible(false);
        }
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UsuarioDAO usuario = new UsuarioDAO();
        usuarios = usuario.selectUsuario();
        iniciaImagem();
        acaoBotoes();
        //checaPedidos();
    }    
    
}

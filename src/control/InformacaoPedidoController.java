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
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pedido;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class InformacaoPedidoController implements Initializable {

    @FXML
    private JFXButton btnRevendedores;

    @FXML
    private ImageView imgPerfilRevendedor;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private JFXButton btnSair;

    @FXML
    private Label labelNome;

    @FXML
    private TextArea textPedido;

    @FXML
    private ImageView imgCart;

    @FXML
    private JFXButton btnConta;
    
    @FXML
    private JFXButton btnPedido;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgConta1;
    
    private static Pedido pedido;

    public static void setPedido(Pedido pedido) {
        InformacaoPedidoController.pedido = pedido;
    }
    
    private void iniciaImagem() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        imgPerfilRevendedor.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + ".png"));
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgCart.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\PedidosBranco.png"));
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
    }
    
    private void acaoBotoes() {
        btnRevendedores.setOnMouseEntered(event -> {
            imgRevendedores.setScaleX(1.1);
            imgRevendedores.setScaleY(1.1);
        });
        btnRevendedores.setOnMouseExited(event -> {
            imgRevendedores.setScaleX(1.0);
            imgRevendedores.setScaleY(1.0);
        });
        btnRevendedores.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("EncontrarRevendedor.fxml");
        });
        btnRevendedores.setTooltip(new Tooltip("Encontrar Revendedor"));
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Voltar"));
        btnConta.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("HomeUsuario.fxml");
        });
        btnConta.setOnMouseEntered(event -> {
            imgConta1.setScaleX(1.1);
            imgConta1.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta1.setScaleX(1.0);
            imgConta1.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        btnPedido.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("Carrinho.fxml");
        });
        btnPedido.setOnMouseEntered(event -> {
            imgCart.setScaleX(1.1);
            imgCart.setScaleY(1.1);
        });
        btnPedido.setOnMouseExited(event -> {
            imgCart.setScaleX(1.0);
            imgCart.setScaleY(1.0);
        });
        btnPedido.setTooltip(new Tooltip("Carrinho"));
    }

    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Pedidos.fxml");
    }
    
    private void iniciaTextPedido() {
        // O que vai aparecer no text area
        String id_pedido;
        ObservableList<String> status = FXCollections.observableArrayList();
        ObservableList<String> nomeProduto = FXCollections.observableArrayList();
        ObservableList<String> qtdProduto = FXCollections.observableArrayList();
        ObservableList<String> nomeRevendedor = FXCollections.observableArrayList();
        // Setando os valores
        id_pedido = String.valueOf(pedido.getId_pedido());
        // Logica para pegar o nome dos produtos no pedido
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < produtos.size(); i++) {
            for (int j = 0; j < pedido.getProdutos().size(); j++) {
                if (produtos.get(i).getId_produto() == pedido.getProdutos().get(j)) {
                    nomeProduto.add(produtos.get(i).getNome());
                    qtdProduto.add(String.valueOf(pedido.getQuantidade().get(j)));
                    status.add(pedido.getStatus().get(j));
                    // Logica para pegar o nome do revendedor do produto
                    for (int k = 0; k < usuarios.size(); k++) {
                        if (produtos.get(i).getVendedor() == usuarios.get(k).getId_usuario()) {
                            nomeRevendedor.add(usuarios.get(k).getLogin());
                        }
                    }
                }
            }
        }
        // Setando o Text Area
        textPedido.setText("ID Pedido: " + id_pedido + "\n"
                + "Produtos Comprados: \n\n");
        for (int i = 1; i <= nomeProduto.size(); i++) {
            textPedido.appendText("Produto " + i + ": " + nomeProduto.get(i - 1) + "\n"
                    + "Revendedor: " + nomeRevendedor.get(i - 1) + "\n"
                    + "Quantidade: " + qtdProduto.get(i - 1) + "\n"
                    + "Status: " + status.get(i - 1) + "\n"
                    + "------------------------\n\n");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTextPedido();
        iniciaImagem();
        acaoBotoes();
    }    
    
}

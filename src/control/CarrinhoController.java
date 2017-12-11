package control;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.Carrinho;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.PedidoDAO;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class CarrinhoController implements Initializable {

    @FXML
    private TableView<Produto> tbProduto;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private Label labelTotal;

    @FXML
    private TableColumn<Produto, ImageView> colImagem;

    @FXML
    private TableColumn<Produto, Float> colPreco;
    
    @FXML
    private TableColumn<Produto, Integer> colQuantidade;
    
    @FXML
    private JFXButton btnConta;
    
    @FXML
    private ImageView imgPerfilRevendedor;

    @FXML
    private ImageView imgRevendedores;

    @FXML
    private JFXButton btnSair;
    
     @FXML
    private ImageView imgConta1;

    @FXML
    private ImageView imgLogout;
    
    @FXML
    private Label labelNome;
    
    @FXML
    private JFXButton btnRevendedor;

    private Produto produto;
    
    @FXML
    void home(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaUsuario.fxml");
    }
    
    @FXML
    void conta(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeUsuario.fxml");
    }
    
    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }

    @FXML
    void pedido(ActionEvent event) {
        Alertas alertas = new Alertas();
        if (Carrinho.getCarrinho() == null) {
            alertas.carrinhoVazio();
        } else {
            PedidoDAO pedidoDAO = new PedidoDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ObservableList<Produto> produtos = produtoDAO.selectProduto();
            for (int i = 0; i < Carrinho.getCarrinho().size(); i++) {
                produtoDAO.editaVendidos(Carrinho.getCarrinho().get(i).getVendidos() + colQuantidade.getCellData(i), Carrinho.getCarrinho().get(i).getId_produto());
                for (int j = 0; j < produtos.size(); j++) {
                    if (produtos.get(j).getId_produto() == Carrinho.getCarrinho().get(i).getId_produto()) {
                        System.out.println("FOI-----------");
                        int novaQtd = produtos.get(j).getQuantidade() - Carrinho.getCarrinho().get(i).getQuantidade();
                        System.out.println(novaQtd);
                        produtoDAO.editaQuantidade(novaQtd, produtos.get(j).getId_produto());
                    }
                }
            }
            ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
            pedidoDAO.addPedido(usuarios.get(Usuario.getUsuarioLogado()).getId_usuario(), Carrinho.getCarrinho());
            Carrinho.clear();
            iniciaTabela();
            alertas.pedidoRealizado();
        }
    }

    @FXML
    void remover(ActionEvent event) {
        if (produto == null) {
            Alertas alertas = new Alertas();
            alertas.selecioneProduto();
        } else {
            for (int i = 0; i < Carrinho.getCarrinho().size(); i++) {
                if (Carrinho.getCarrinho().get(i) == produto) {
                    Carrinho.remove(i);
                    tbProduto.refresh();
                }
            }
            atualizaLabel();
        }
    }
    
    private void iniciaTabela() {
        tbProduto.setItems(Carrinho.getCarrinho());
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colImagem.setCellValueFactory(new PropertyValueFactory("imagem"));
        colPreco.setCellValueFactory(new PropertyValueFactory("valor"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        Label placeholder = new Label("Não há produtos no carrinho.");
        placeholder.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        tbProduto.setPlaceholder(placeholder);
    }
    
    private void atualizaLabel() {
        float total = 0;
        for (int i = 0; i < Carrinho.getCarrinho().size(); i++) {
            total += Carrinho.getCarrinho().get(i).getValor() * Carrinho.getCarrinho().get(i).getQuantidade();
        }
        labelTotal.setText("Total: " + String.valueOf(total));
        labelTotal.autosize();
    }
    
    private void selecionaTabela() {
        tbProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>(){
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                produto = newValue;
            }
        });
    }
    
    private void iniciaImagem() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\homeBranco.png"));
        imgPerfilRevendedor.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + ".png"));
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
    }
    
    private void acaoBotoes() {
        btnRevendedor.setOnMouseEntered(event -> {
            imgRevendedores.setScaleX(1.1);
            imgRevendedores.setScaleY(1.1);
        });
        btnRevendedor.setOnMouseExited(event -> {
            imgRevendedores.setScaleX(1.0);
            imgRevendedores.setScaleY(1.0);
        });
        btnRevendedor.setTooltip(new Tooltip("Home"));
        btnConta.setOnMouseEntered(event -> {
            imgConta1.setScaleX(1.1);
            imgConta1.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta1.setScaleX(1.0);
            imgConta1.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setTooltip(new Tooltip("Sair"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();
        selecionaTabela();
        atualizaLabel();
        iniciaImagem();
        acaoBotoes();
    }    
    
}

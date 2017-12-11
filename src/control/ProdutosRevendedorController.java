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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.Carrinho;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class ProdutosRevendedorController implements Initializable {

    @FXML
    private TableView<Produto> tbProduto;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private TextField textPesquisa;

    @FXML
    private TableColumn<Produto, Integer> colEstoque;

    @FXML
    private TableColumn<Produto, ImageView> colImagem;

    @FXML
    private TableColumn<Produto, Float> colPreco;
    
    @FXML
    private JFXButton btnMais;
    
    @FXML
    private Label labelQuantidade;
    
    @FXML
    private Label labelNome;

    @FXML
    private JFXButton btnMenos;
    
    @FXML
    private ImageView imgPerfil;
    
    @FXML
    private ImageView imgLogout;
    
    @FXML
    private ImageView imgConta1;
    
    @FXML
    private ImageView imgRevendedores;
    
    @FXML
    private ImageView imgCart;
    
    @FXML
    private JFXButton btnVoltar;
    
    private static Usuario usuario;
    
    private ObservableList<Produto> produtos2 = FXCollections.observableArrayList();
    
    private Produto produto;

    public static void setUsuario(Usuario usuario) {
        ProdutosRevendedorController.usuario = usuario;
    }

    @FXML
    void addCarrinho(ActionEvent event) {
        Alertas alertas = new Alertas();
        if (produto == null) {
            alertas.selecioneProduto();
        } else {
            if (Integer.valueOf(labelQuantidade.getText()) > produto.getDisponivel()) {
                alertas.erroQuantidadeProduto();
            } else {
                produto.setQuantidade(Integer.valueOf(labelQuantidade.getText()));
                Carrinho.addProduto(produto);
                alertas.produtoAdicionadoCarrinho();
            }
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("EncontrarRevendedor.fxml");
    }
    
    private void iniciaTabela() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        //ObservableList<Produto> produtos2 = FXCollections.observableArrayList();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuario.getId_usuario()) {
                ImageView imagem = new ImageView(new Image("file:///" + produtos.get(i).getUrls().get(0)));
                imagem.setFitHeight(100);
                imagem.setFitWidth(100);
                produtos.get(i).setImagem(imagem);
                produtos.get(i).setDisponivel();
                System.out.println(produtos.get(i).getDisponivel());
                produtos2.add(produtos.get(i));
            }
        }
        tbProduto.setItems(produtos2);
        tbProduto.setPlaceholder(new Label("Nenhum Produto Disponivel."));
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory("valor"));
        colImagem.setCellValueFactory(new PropertyValueFactory("imagem"));
        colEstoque.setCellValueFactory(new PropertyValueFactory("disponivel"));
    }
    
    private void selecionaTabela() {
        tbProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>() {
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                produto = newValue;
                labelQuantidade.setText("0");
            }
        });
    }
    
    private void pesquisaProduto() {
        textPesquisa.setOnKeyReleased(event -> {
            //atualizaTabela();
            ObservableList<Produto> novoProdutos = FXCollections.observableArrayList();
            for (int i = 0; i < produtos2.size(); i++) {
                if (produtos2.get(i).getNome().toLowerCase().startsWith(textPesquisa.getText().toLowerCase())) {
                    novoProdutos.add(produtos2.get(i));
                }
            }
            tbProduto.setItems(novoProdutos);
        });
    }
    
    private void trocaQuantidade() {
        Alertas alertas = new Alertas();
        btnMais.setOnMouseClicked(event -> {
            if (produto == null) {
                alertas.erroSelecioneProduto();
            } else {
                if (Integer.valueOf(labelQuantidade.getText()) == 0) {
                    alertas.erroReservaProduto();
                } else {
                    labelQuantidade.setText(String.valueOf(Integer.valueOf(labelQuantidade.getText()) - 1));
                }
            }
        });
        btnMenos.setOnMouseClicked(event -> {
            if (produto == null) {
                alertas.erroSelecioneProduto();
            } else {
                if (Integer.valueOf(labelQuantidade.getText()) >= produto.getDisponivel()) {
                    alertas.erroQuantidadeProduto();
                } else {
                    labelQuantidade.setText(String.valueOf(Integer.valueOf(labelQuantidade.getText()) + 1));
                }
            }
        });
    }
    
    private void iniciaUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
        imgPerfil.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + ".png"));
    }
    
    private void iniciaImagem() {
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgRevendedores.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\RevendedoresBranco.png"));
        imgCart.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\PedidosBranco.png"));
    }
    
    private void acaoBotoes() {
        btnVoltar.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnVoltar.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnVoltar.setTooltip(new Tooltip("Voltar"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();
        pesquisaProduto();
        selecionaTabela();
        trocaQuantidade();
        iniciaImagem();
        iniciaUsuario();
        acaoBotoes();
        textPesquisa.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
    }    
    
}

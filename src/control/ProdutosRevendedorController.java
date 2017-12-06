package control;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.Carrinho;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.ProdutoDAO;

public class ProdutosRevendedorController implements Initializable {

    @FXML
    private TableView<Produto> tbProduto;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private TextField textQuantidade;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField textPesquisa;

    @FXML
    private TableColumn<Produto, Integer> colEstoque;

    @FXML
    private TableColumn<Produto, ImageView> colImagem;

    @FXML
    private TableColumn<Produto, Float> colPreco;
    
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
            if (Integer.valueOf(textQuantidade.getText()) > produto.getDisponivel()) {
                alertas.erroQuantidadeProduto();
            } else {
                produto.setQuantidade(Integer.valueOf(textQuantidade.getText()));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();
        pesquisaProduto();
        selecionaTabela();
    }    
    
}

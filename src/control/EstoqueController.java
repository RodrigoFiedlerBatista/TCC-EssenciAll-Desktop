package control;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.GerenciaArquivos;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class EstoqueController implements Initializable {

    @FXML
    private TableView<Produto> tbProduto;

    @FXML
    private TableColumn<Produto, String> colNome;

    @FXML
    private TableColumn<Produto, ImageView> colImagem;

    @FXML
    private TableColumn<Produto, String> colPreco;
    
    @FXML
    private TableColumn<Produto, String> colQuantidade;
    
    @FXML
    private ImageView imagemIconeSair;
    
    @FXML
    private ImageView iconeEdit;
    
    @FXML
    private ImageView iconeDelete;
    
    @FXML
    private JFXButton deleteProduto;

    @FXML
    private ImageView icone_estoque;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private ImageView icone_cadastrarPerfume;
    
    @FXML
    private JFXTextField textPesquisa;
    
    @FXML
    private ImageView imgFundoEstoque;
    
    @FXML
    private ImageView imgPesquisa;
    
    @FXML
    private JFXButton editProduto;
    
    @FXML
    private JFXButton btnAdd;
    
    @FXML
    private TableColumn<Produto, String> colMarca;
    
    private Produto produto;
    
    private ObservableList<Produto> produtos;

    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaRevendedor.fxml");
    }
    
    private void atualizaTabela(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        produtos = produtoDAO.selectProduto();
        ObservableList<Produto> produtos2 = FXCollections.observableArrayList();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                ImageView imagem = new ImageView(new Image("file:///" + produtos.get(i).getUrls().get(0)));
                System.out.println(produtos.get(i).getUrls().get(0));
                imagem.setFitHeight(100);
                imagem.setFitWidth(100);
                produtos.get(i).setImagem(imagem);
                produtos2.add(produtos.get(i));
                System.out.println("foi");
            }
            
        }
        tbProduto.setItems(produtos2);
        produtos = produtos2;
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colImagem.setCellValueFactory(new PropertyValueFactory("imagem"));
        colPreco.setCellValueFactory(new PropertyValueFactory("valor"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        tbProduto.setPlaceholder(new Label("Não há produtos cadastrados."));
    }
    
    @FXML
    void cadastraProduto(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("CadastraProduto.fxml");
    }
    
    private void iniciaImagem() {
        imagemIconeSair.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        iconeEdit.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\settings.png"));
        iconeDelete.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\deletar.png"));
        icone_estoque.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\stocks.png"));
        icone_cadastrarPerfume.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\perfume_1.png"));
        imgFundoEstoque.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\PerfumeCadastrar.jpg"));
        imgPesquisa.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\pesquisa.png"));
    }
    
    private void acaoBotoes() {
        btnVoltar.setOnMouseEntered(event -> {
            imagemIconeSair.setScaleX(1.1);
            imagemIconeSair.setScaleY(1.1);
        });
        btnVoltar.setOnMouseExited(event -> {
            imagemIconeSair.setScaleX(1.0);
            imagemIconeSair.setScaleY(1.0);
        });
        deleteProduto.setOnMouseClicked(event -> {
            if (produto == null) {
                Alertas alertas = new Alertas();
                alertas.erroDeletarProduto();
            } else {
                Alert alerta = new Alert(AlertType.CONFIRMATION);
                alerta.setTitle("Confirmação");
                alerta.setHeaderText("Tem certeza que deseja excluir esse produto?");
                alerta.showAndWait().ifPresent(b -> {
                    if (b == alerta.getButtonTypes().get(0)) {
                        GerenciaArquivos gerencia = new GerenciaArquivos();
                        ProdutoDAO produtoDAO = new ProdutoDAO();
                        UsuarioDAO usuarioDAO = new UsuarioDAO();
                        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
                        produtoDAO.removeProduto(produto.getId_produto());
                        gerencia.deleta(System.getProperty("user.dir") + "\\imagensProdutos\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + "\\" + produto.getNome() + ".png");
                        atualizaTabela();
                    }
                });
            }
        });
        editProduto.setOnMouseClicked(event -> {
            if (produto == null) {
                Alertas alertas = new Alertas();
                alertas.erroEditarProduto();
            } else {
                TCC tcc = new TCC();
                EditarProdutoController.setProduto(produto);
                tcc.fechaTela();
                tcc.iniciaStage("EditarProduto.fxml");
            }
            
        });
        deleteProduto.setOnMouseEntered(event -> {
            iconeDelete.setScaleX(1.1);
            iconeDelete.setScaleY(1.1);
        });
        deleteProduto.setOnMouseExited(event -> {
            iconeDelete.setScaleX(1.0);
            iconeDelete.setScaleY(1.0);
        });
        deleteProduto.setTooltip(new Tooltip("Deletar Produto"));
        editProduto.setOnMouseEntered(event -> {
            iconeEdit.setScaleX(1.1);
            iconeEdit.setScaleY(1.1);
        });
        editProduto.setOnMouseExited(event -> {
            iconeEdit.setScaleX(1.0);
            iconeEdit.setScaleY(1.0);
        });
        editProduto.setTooltip(new Tooltip("Editar Produto"));
        btnAdd.setOnMouseEntered(event -> {
            icone_cadastrarPerfume.setScaleX(1.1);
            icone_cadastrarPerfume.setScaleY(1.1);
        });
        btnAdd.setOnMouseExited(event -> {
            icone_cadastrarPerfume.setScaleX(1.0);
            icone_cadastrarPerfume.setScaleY(1.0);
        });
        btnAdd.setTooltip(new Tooltip("Cadastrar Produto"));
    }
    
    private void pesquisaTabela(){
        textPesquisa.setOnKeyReleased(event -> {
            //atualizaTabela();
            ObservableList<Produto> novoProdutos = FXCollections.observableArrayList();
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getNome().toLowerCase().contains(textPesquisa.getText().toLowerCase())) {
                    novoProdutos.add(produtos.get(i));
                }
            }
            tbProduto.setItems(novoProdutos);
        });
    }
    
    private void selecionaProduto(){
        tbProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>(){
            @Override
            public void changed(ObservableValue<? extends Produto> observable, Produto oldValue, Produto newValue) {
                produto = newValue;
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizaTabela();
        iniciaImagem();
        acaoBotoes();
        selecionaProduto();
        pesquisaTabela();
        textPesquisa.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
    }    
    
}

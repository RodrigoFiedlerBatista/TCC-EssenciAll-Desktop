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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.Carrinho;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.PedidoDAO;
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

    private Produto produto;
    
    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaUsuario.fxml");
    }

    @FXML
    void pedido(ActionEvent event) {
        Alertas alertas = new Alertas();
        if (Carrinho.getCarrinho() == null) {
            alertas.carrinhoVazio();
        } else {
            PedidoDAO pedidoDAO = new PedidoDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
            pedidoDAO.addPedido(usuarios.get(Usuario.getUsuarioLogado()).getId_usuario(), Carrinho.getCarrinho());
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
        tbProduto.setPlaceholder(new Label("Não há produtos no carrinho."));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();
        selecionaTabela();
        atualizaLabel();
    }    
    
}

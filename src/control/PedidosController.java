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
import model.Alertas;
import model.Pedido;
import model.TCC;
import model.Usuario;
import model.jdbc.PedidoDAO;
import model.jdbc.UsuarioDAO;

public class PedidosController implements Initializable {
    
    @FXML
    private TableColumn<Pedido, Integer> colID;

    @FXML
    private TableView<Pedido> tbPedido;

    @FXML
    private TableColumn<Pedido, String> colStatus;
    
    private Pedido pedido;

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
        tcc.iniciaStage("ContaUsuario.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTabela();
        selecionaTabela();
    }    
    
}

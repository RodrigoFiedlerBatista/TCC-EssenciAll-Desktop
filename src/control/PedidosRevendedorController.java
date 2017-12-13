package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Alertas;
import model.AuxiliaTable;
import model.Pedido;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.PedidoDAO;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class PedidosRevendedorController implements Initializable {

    @FXML
    private JFXRadioButton checkNaoEntregue;

    @FXML
    private ImageView imgPesquisa;

    @FXML
    private Label labelNome;

    @FXML
    private JFXTextField textPesquisa;

    @FXML
    private JFXButton btnConta;

    @FXML
    private TableColumn<AuxiliaTable, String> colCliente;
    
    @FXML
    private TableColumn<AuxiliaTable, String> colProduto;

    @FXML
    private ImageView imgPerfilRevendedor;

    @FXML
    private JFXRadioButton checkEntregue;

    @FXML
    private JFXButton btnSair;

    @FXML
    private TableColumn<AuxiliaTable, Integer> colPedido;

    @FXML
    private ImageView imgConta1;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgHome;

    @FXML
    private JFXButton btnHome;

    @FXML
    private TableView<AuxiliaTable> tbPedido;
    
    @FXML
    private TableColumn<AuxiliaTable, String> colStatus;
    
    private AuxiliaTable pedido;
    
    private ObservableList<AuxiliaTable> auxilia = FXCollections.observableArrayList();
    
    @FXML
    void home(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("ContaRevendedor.fxml");
    }

    @FXML
    void conta(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeRevendedor.fxml");
    }

    @FXML
    void sair(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");
    }
    
    private void iniciaImagem() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        imgHome.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\homeBranco.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        imgPerfilRevendedor.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\usuario\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + ".png"));
        imgPesquisa.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\pesquisa.png"));
        labelNome.setText(usuarios.get(Usuario.getUsuarioLogado()).getLogin());
        labelNome.autosize();
    }
    
    private void selecionaTabela() {
        tbPedido.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AuxiliaTable>() {
            @Override
            public void changed(ObservableValue<? extends AuxiliaTable> observable, AuxiliaTable oldValue, AuxiliaTable newValue) {
                if (newValue != null) {
                    if (newValue.getStatus().equals("Aguardando Entrega")) {
                        checkNaoEntregue.setSelected(true);
                        checkEntregue.setSelected(false);
                    } else {
                        checkNaoEntregue.setSelected(false);
                        checkEntregue.setSelected(true);
                    }
                    pedido = newValue;
                }
                
            }
        });
    }
    
    private void selecionaRadioBotao() {
        Alertas alertas = new Alertas();
        checkEntregue.setOnMouseClicked(event -> {
            if (pedido == null) {
                alertas.erroSelecionePedido();
                checkEntregue.setSelected(false);
            } else {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.editaStatus("Pedido Entregue", pedido.getId_itenpedido());
                alertas.statusAlterado();
                iniciaTabela();
                tbPedido.refresh();
            }
        });
        checkNaoEntregue.setOnMouseClicked(event -> {
            if (pedido == null) {
                alertas.erroSelecionePedido();
                checkNaoEntregue.setSelected(false);
            } else {
                PedidoDAO pedidoDAO = new PedidoDAO();
                pedidoDAO.editaStatus("Aguardando Entrega", pedido.getId_itenpedido());
                alertas.statusAlterado();
                iniciaTabela();
                tbPedido.refresh();
            }
        });
        
    }
    
    private void criaGrupo(){
        ToggleGroup grupo = new ToggleGroup();
        checkEntregue.setToggleGroup(grupo);
        checkNaoEntregue.setToggleGroup(grupo);
    }
    
    private void iniciaTabela() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ObservableList<Pedido> pedidos = pedidoDAO.selectPedido();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        ObservableList<Produto> produtosUsuario = FXCollections.observableArrayList();
        auxilia.clear();
        // Pega os produtos do usuario logado
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                produtosUsuario.add(produtos.get(i));
            }
        }
        // Pega os pedidos que contem esse revendedor
        for (int i = 0; i < pedidos.size(); i++) {
            for (int j = 0; j < pedidos.get(i).getProdutos().size(); j++) {
                for (int k = 0; k < produtosUsuario.size(); k++) {
                    if (pedidos.get(i).getProdutos().get(j) == produtosUsuario.get(k).getId_produto()) {
                        AuxiliaTable auxiliaTable = new AuxiliaTable();
                        auxiliaTable.setPedido(pedidos.get(i));
                        auxiliaTable.setProduto(produtosUsuario.get(k));
                        auxiliaTable.setStatus(pedidos.get(i).getStatus().get(j));
                        auxiliaTable.setId_itenpedido(pedidos.get(i).getId_itenpedido().get(j));
                        auxilia.add(auxiliaTable);
                    }
                }
            }
        }
        tbPedido.setItems(auxilia);
        colPedido.setCellValueFactory(new Callback<CellDataFeatures<AuxiliaTable, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<AuxiliaTable, Integer> p) {
                return new SimpleObjectProperty<>(p.getValue().getIdPedido());
            }
        });
        colProduto.setCellValueFactory(new Callback<CellDataFeatures<AuxiliaTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<AuxiliaTable, String> p) {
                return new SimpleObjectProperty<>(p.getValue().getNomeProduto());
            }
        });
        colCliente.setCellValueFactory(new Callback<CellDataFeatures<AuxiliaTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<AuxiliaTable, String> p) {
                return new SimpleObjectProperty<>(p.getValue().getCliente());
            }
        });
        colStatus.setCellValueFactory(new Callback<CellDataFeatures<AuxiliaTable, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<AuxiliaTable, String> p) {
                return new SimpleObjectProperty<>(p.getValue().getStatus());
            }
        });
    }
    
    private void acaoBotoes() {
        btnHome.setOnMouseEntered(event -> {
            imgHome.setScaleX(1.1);
            imgHome.setScaleY(1.1);
        });
        btnHome.setOnMouseExited(event -> {
            imgHome.setScaleX(1.0);
            imgHome.setScaleY(1.0);
        });
        btnHome.setTooltip(new Tooltip("Home"));
        btnConta.setOnMouseEntered(event -> {
            imgConta1.setScaleX(1.1);
            imgConta1.setScaleY(1.1);
        });
        btnConta.setOnMouseExited(event -> {
            imgConta1.setScaleX(1.0);
            imgConta1.setScaleY(1.0);
        });
        btnConta.setTooltip(new Tooltip("Editar Perfil"));
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Sair"));
    }
    
    private void pesquisaTabela() {
        textPesquisa.setOnKeyReleased(event -> {
            //atualizaTabela();
            ObservableList<AuxiliaTable> novoPedidos = FXCollections.observableArrayList();
            for (int i = 0; i < auxilia.size(); i++) {
                if (String.valueOf(auxilia.get(i).getIdPedido()).startsWith(textPesquisa.getText().toLowerCase())) {
                    novoPedidos.add(auxilia.get(i));
                }
            }
            tbPedido.setItems(novoPedidos);
        });
    }
    
    private void textCor() {
        textPesquisa.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        iniciaTabela();
        textCor();
        acaoBotoes();
        selecionaTabela();
        criaGrupo();
        selecionaRadioBotao();
        pesquisaTabela();
    }    
    
}

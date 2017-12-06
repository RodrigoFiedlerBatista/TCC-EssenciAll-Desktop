package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Pedido;
import model.Produto;
import model.TCC;
import model.Usuario;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class InformacaoPedidoController implements Initializable {

    @FXML
    private TextArea textPedido;
    
    private static Pedido pedido;

    public static void setPedido(Pedido pedido) {
        InformacaoPedidoController.pedido = pedido;
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
        String status;
        ObservableList<String> nomeProduto = FXCollections.observableArrayList();
        ObservableList<String> qtdProduto = FXCollections.observableArrayList();
        ObservableList<String> nomeRevendedor = FXCollections.observableArrayList();
        // Setando os valores
        id_pedido = String.valueOf(pedido.getId_pedido());
        status = pedido.getStatus();
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
                + "Status Do Pedido: " + status + "\n"
                + "Produtos Comprados: \n\n");
        for (int i = 1; i <= nomeProduto.size(); i++) {
            textPedido.appendText("Produto " + i + ": " + nomeProduto.get(i - 1) + "\n"
                    + "Revendedor: " + nomeRevendedor.get(i - 1) + "\n"
                    + "Quantidade: " + qtdProduto.get(i - 1) + "\n"
                    + "------------------------\n\n");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTextPedido();
    }    
    
}

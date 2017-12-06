package teste;

import javafx.collections.ObservableList;
import model.Pedido;
import model.Produto;
import model.jdbc.PedidoDAO;
import model.jdbc.ProdutoDAO;

public class TestePedidos {
    
    public static void main(String[] args) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Pedido> pedidos = pedidoDAO.selectPedido();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        
        for (int i = 0; i < pedidos.size(); i++) {
            
            System.out.println("ID: " + pedidos.get(i).getId_pedido());
            System.out.println("Status: " + pedidos.get(i).getStatus());
            System.out.println("Comprou: " + pedidos.get(i).getCliente());
            
            for (int j = 0; j < produtos.size(); j++) {
                
                for (int k = 0; k < pedidos.get(i).getProdutos().size(); k++) {
                    
                    if (produtos.get(j).getId_produto() == pedidos.get(i).getProdutos().get(k)) {
                        System.out.println("Produto " + j + ": " + produtos.get(j).getNome());
                        //System.out.println("Vendeu produto " + j + ": " + );
                    }
                    
                }
                
                
            }
            
            System.out.println("\n---------------------------\n\n");
        }
        
        
    }
    
}

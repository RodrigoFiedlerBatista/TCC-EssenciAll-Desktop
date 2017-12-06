package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pedido;
import model.Produto;

public class PedidoDAO {
    
    public void addPedido(int cliente, ObservableList<Produto> produtos) {
        String sql = "insert into pedido (id_usuario, status) values (?, ?);";
        String sql2 = "insert into itenPedido (id_produto, id_pedido, quantidade) values (?, ?, ?);";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, cliente);
            stmt.setString(2, "Esperando Pagamento");
            stmt.execute();
            PreparedStatement stmt2 = con.getConnection().prepareStatement(sql2);
            for (int i = 0; i < produtos.size(); i++) {
                stmt2.setInt(1, produtos.get(i).getId_produto());
                stmt2.setInt(2, ultimoPedido());
                stmt2.setInt(3, produtos.get(i).getQuantidade());
                stmt2.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int ultimoPedido() {
        String sql = "select * from pedido order by id_pedido;";
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setCliente(rs.getInt("id_usuario"));
                pedidos.add(pedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos.get(pedidos.size() - 1).getId_pedido();
    }
    
    public ObservableList<Pedido> selectPedido() {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        ObservableList<Pedido> itenPedidos = FXCollections.observableArrayList();
        String sql = "select * from pedido order by id_pedido;";
        String sql2 = "select * from itenPedido order by id_itenpedido";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setStatus(rs.getString("status"));
                pedido.setCliente(rs.getInt("id_usuario"));
                pedidos.add(pedido);
            }
            PreparedStatement stmt2 = con.getConnection().prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next()) {
                Pedido pedido = new Pedido();
                pedido.setProdutos(rs2.getInt("id_produto"));
                pedido.setId_pedido(rs2.getInt("id_pedido"));
                pedido.setQuantidade(rs2.getInt("quantidade"));
                itenPedidos.add(pedido);
            }
            for (int i = 0; i < pedidos.size(); i++) {
                for (int j = 0; j < itenPedidos.size(); j++) {
                    if (pedidos.get(i).getId_pedido() == itenPedidos.get(j).getId_pedido()) {
                        pedidos.get(i).setProdutos(itenPedidos.get(j).getProdutos().get(0));
                        pedidos.get(i).setQuantidade(itenPedidos.get(j).getQuantidade().get(0));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }
    
}

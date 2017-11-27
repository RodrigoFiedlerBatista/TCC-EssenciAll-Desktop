package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Produto;
import model.Usuario;

public class ProdutoDAO {
    
    public void addProduto(Produto produto){
        String sql = "insert into produto (vendedor, nome, descricao, quantidade, valor, url1, codigo, reserva) values (?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
            stmt.setInt(1, usuarios.get(Usuario.getUsuarioLogado()).getId_usuario());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setFloat(5, produto.getValor());
            stmt.setString(6, produto.getUrls().get(0));
            stmt.setInt(7, produto.getCodigo());
            stmt.setInt(8, produto.getReserva());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Produto> selectProduto(){
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        String sql = "select * from produto order by id_produto;";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setUrls(rs.getString("url1"));
                produto.setUrls(rs.getString("url2"));
                produto.setUrls(rs.getString("url3"));
                produto.setValor(rs.getFloat("valor"));
                produto.setVendedor(rs.getInt("vendedor"));
                produto.setCodigo(rs.getInt("codigo"));
                produto.setReserva(rs.getInt("reserva"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return produtos;
    }
    
    public void removeProduto(int id) {
        String sql = "delete from produto where id_produto = ?;";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}

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
        String sql = "insert into produto (vendedor, nome, descricao, quantidade, valor, url1, codigo, reserva, marca, vendidos) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
            stmt.setString(9, produto.getMarca());
            stmt.setInt(10, 0);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Produto> selectProduto(){
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        String sql = "select * from produto order by id_produto;";
        ConnectionFactory con = new ConnectionFactory();
        
            PreparedStatement stmt;
        try {
            stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Produto produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.setId_produto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setUrls(rs.getString("url1"));
                //produto.setUrls(rs.getString("url2"));
                //produto.setUrls(rs.getString("url3"));
                produto.setValor(rs.getFloat("valor"));
                produto.setVendedor(rs.getInt("vendedor"));
                produto.setCodigo(rs.getInt("codigo"));
                produto.setReserva(rs.getInt("reserva"));
                produto.setMarca(rs.getString("marca"));
                produto.setVendidos(rs.getInt("vendidos"));
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void editaProduto(Produto produto) {
        String sql = "update produto set nome = ?, descricao = ?, quantidade = ?, valor = ?, vendedor = ?, reserva = ?, marca = ?, codigo = ? where id_produto = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setFloat(4, produto.getValor());
            stmt.setInt(5, produto.getVendedor());
            stmt.setInt(6, produto.getReserva());
            stmt.setString(7, produto.getMarca());
            stmt.setInt(8, produto.getCodigo());
            stmt.setInt(9, produto.getId_produto());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editaUrlImagem(String url, int id) {
        String sql = "update produto set url1 = ? where id_produto = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, url);
            stmt.setInt(2, id);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

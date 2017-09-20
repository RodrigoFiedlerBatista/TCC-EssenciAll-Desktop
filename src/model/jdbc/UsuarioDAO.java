package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Usuario;

public class UsuarioDAO {
    
    public void addUsuario(Usuario usuario){
        String sql = "insert into usuario (usuario_login, usuario_senha, chave_senha, email, revendedor, url_imagem, ativada, codigo) values (?, ?, ?, ?, ?, ?, ?, ?)";
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getChaveSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.setBoolean(5, usuario.isRevendedor());
            stmt.setString(6, usuario.getUrl_imagem());
            stmt.setBoolean(7, usuario.isAtivado());
            stmt.setString(8, usuario.getCodigo());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("nao foi por algum motivo");
        }
    }
    
    public void setAtivado(int i){
        String sql = "update usuario set ativada = true where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, i);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("nao foi por algum motivo");
        }
    }
    
    public void deletaUsuario(int id_usuario){
        String sql = "delete from usuario where id_usuario = ?";
        String sql2 = "delete from lixo where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql2);
            stmt.setInt(1, id_usuario);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Errou");
        }
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, id_usuario);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Errou");
        }
        
    }
    
    public ObservableList<Usuario> selectUsuario(){
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        // Escrever o SQL
        String sql = "select * from usuario order by id_usuario";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setLogin(rs.getString("usuario_login"));
                usuario.setSenha(rs.getString("usuario_senha"));
                usuario.setChaveSenha(rs.getString("chave_senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setRevendedor(rs.getBoolean("revendedor"));
                usuario.setUrl_imagem(rs.getString("url_imagem"));
                usuario.setAtivado(rs.getBoolean("ativada"));
                usuario.setCodigo(rs.getString("codigo"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
    public void atualizaLogin(String login){
        String sql = "update usuario set usuario_login = ? where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setInt(2, Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getId_usuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaSenha(String senha, String chave){
        String sql = "update usuario set usuario_senha = ?, chave_senha = ? where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, senha);
            stmt.setString(2, chave);
            stmt.setInt(3, Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getId_usuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaEmail(String email, String codigo){
        String sql = "update usuario set email = ?, ativada = false, codigo = ? where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, codigo);
            stmt.setInt(3, Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getId_usuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void atualizaUrlImagem(){
        String sql = "update usuario set url_imagem = ? where id_usuario = ?";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setString(1, Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getLogin() + ".png");
            stmt.setInt(2, Usuario.getUsuarios().get(Usuario.getUsuarioLogado()).getId_usuario());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

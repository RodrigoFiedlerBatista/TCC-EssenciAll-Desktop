package model.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Revende;
import model.Usuario;

public class RevendeDAO {
    
    public void addRevende(Revende revende){
        String sql = "insert into revende (id_usuario, eudora, hinode, jequiti, laqua, mary, natura, boticario, up, avon) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ConnectionFactory con = new ConnectionFactory();
        
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setInt(1, Usuario.getUsuarioLogado());
            stmt.setBoolean(2, revende.isEudora());
            stmt.setBoolean(3, revende.isHinode());
            stmt.setBoolean(4, revende.isJequiti());
            stmt.setBoolean(5, revende.isLaqua());
            stmt.setBoolean(6, revende.isMary());
            stmt.setBoolean(7, revende.isNatura());
            stmt.setBoolean(8, revende.isBoticario());
            stmt.setBoolean(9, revende.isUp());
            stmt.setBoolean(10, revende.isAvon());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("nao foi por algum motivo");
        }
    }
    
    public ObservableList<Usuario> selectRevende(){
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
    
    
}

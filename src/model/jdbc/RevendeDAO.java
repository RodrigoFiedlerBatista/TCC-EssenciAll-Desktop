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
    
    public ObservableList<Revende> selectRevende(){
        ObservableList<Revende> usuarios = FXCollections.observableArrayList();
        // Escrever o SQL
        String sql = "select * from revende order by id_revende";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Revende revende = new Revende();
                revende.setAvon(rs.getBoolean("avon"));
                revende.setBoticario(rs.getBoolean("boticario"));
                revende.setEudora(rs.getBoolean("eudora"));
                revende.setHinode(rs.getBoolean("hinode"));
                revende.setJequiti(rs.getBoolean("jequiti"));
                revende.setLaqua(rs.getBoolean("laqua"));
                revende.setMary(rs.getBoolean("mary"));
                revende.setNatura(rs.getBoolean("natura"));
                revende.setUp(rs.getBoolean("up"));
                revende.setId_revende(rs.getInt("id_revende"));
                revende.setId_usuario(rs.getInt("id_usuario"));
                usuarios.add(revende);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
    public void editaRevende(Revende revende) {
        String sql = "update revende set eudora = ?, hinode = ?, jequiti = ?, laqua = ?, mary = ?, natura = ?, boticario = ?, up = ?, avon = ? where id_revende = ?;";
        ConnectionFactory con = new ConnectionFactory();
        try {
            PreparedStatement stmt = con.getConnection().prepareStatement(sql);
            stmt.setBoolean(1, revende.isEudora());
            stmt.setBoolean(2, revende.isHinode());
            stmt.setBoolean(3, revende.isJequiti());
            stmt.setBoolean(4, revende.isLaqua());
            stmt.setBoolean(5, revende.isMary());
            stmt.setBoolean(6, revende.isNatura());
            stmt.setBoolean(7, revende.isBoticario());
            stmt.setBoolean(8, revende.isUp());
            stmt.setBoolean(9, revende.isAvon());
            stmt.setInt(10, revende.getId_revende());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RevendeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

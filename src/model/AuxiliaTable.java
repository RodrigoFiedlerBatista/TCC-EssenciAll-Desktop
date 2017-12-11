package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import model.jdbc.UsuarioDAO;

public class AuxiliaTable {
    
    private Pedido pedido;
    private Produto produto;
    private String status;
    private int id_itenpedido;
    
    public void setId_itenpedido(int id) {
        this.id_itenpedido = id;
    }
    
    public int getId_itenpedido() {
        return this.id_itenpedido;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdPedido() {
        return pedido.getId_pedido();
    }
    
    public String getNomeProduto() {
        return produto.getNome();
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public String getCliente() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < usuarios.size(); i++) {
            if (pedido.getCliente() == usuarios.get(i).getId_usuario()) {
                return usuarios.get(i).getLogin();
            }
        }
        return null;
    }
    
}

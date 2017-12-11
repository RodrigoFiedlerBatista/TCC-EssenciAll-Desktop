package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido {
    
    private ObservableList<Integer> produtos = FXCollections.observableArrayList();
    private int cliente;
    private int revendedor;
    private int id_pedido;
    private ObservableList<Boolean> visualizado = FXCollections.observableArrayList();
    private ObservableList<String> status = FXCollections.observableArrayList();
    private ObservableList<Integer> quantidade = FXCollections.observableArrayList();
    private ObservableList<Integer> id_itenpedido = FXCollections.observableArrayList();

    public ObservableList<Integer> getId_itenpedido() {
        return id_itenpedido;
    }

    public void setId_itenpedido(ObservableList<Integer> id_itenpedido) {
        this.id_itenpedido = id_itenpedido;
    }
    
    public void setId_itenpedido(int id) {
        this.id_itenpedido.add(id);
    }

    public ObservableList<Boolean> getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(ObservableList<Boolean> visualizado) {
        this.visualizado = visualizado;
    }
    
    public void setVisualizado(boolean visualizado) {
        this.visualizado.add(visualizado);
    }

    public int getRevendedor() {
        return revendedor;
    }

    public void setRevendedor(int revendedor) {
        this.revendedor = revendedor;
    }
    
    public ObservableList<Integer> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(ObservableList<Integer> quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setQuantidade(int i) {
        this.quantidade.add(i);
    }

    public ObservableList<String> getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status.add(status);
    }

    public void setStatus(ObservableList<String> status) {
        this.status = status;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    
    public ObservableList<Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(ObservableList<Integer> produtos) {
        this.produtos = produtos;
    }
    
    public void setProdutos(int i) {
        this.produtos.add(i);
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    /*public int getRevendedor() {
        return revendedor;
    }

    public void setRevendedor(int revendedor) {
        this.revendedor = revendedor;*/
    //}
    
}

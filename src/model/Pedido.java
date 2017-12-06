package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido {
    
    private ObservableList<Integer> produtos = FXCollections.observableArrayList();
    private int cliente;
    private int revendedor;
    private int id_pedido;
    private String status;
    private ObservableList<Integer> quantidade = FXCollections.observableArrayList();

    public ObservableList<Integer> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(ObservableList<Integer> quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setQuantidade(int i) {
        this.quantidade.add(i);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public int getRevendedor() {
        return revendedor;
    }

    public void setRevendedor(int revendedor) {
        this.revendedor = revendedor;
    }
    
}

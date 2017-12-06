package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Carrinho {
    
    private static Usuario usuario;
    private static ObservableList<Produto> carrinho = FXCollections.observableArrayList();
    
    public static void clear() {
        carrinho.clear();
        usuario = null;
    }
    
    public static void remove(int i) {
        carrinho.remove(i);
    }
    
    public static void addProduto(Produto produto) {
        carrinho.add(produto);
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Carrinho.usuario = usuario;
    }

    public static ObservableList<Produto> getCarrinho() {
        return carrinho;
    }

    public static void setCarrinho(ObservableList<Produto> carrinho) {
        Carrinho.carrinho = carrinho;
    }
    
}

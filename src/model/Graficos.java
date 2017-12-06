package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class Graficos {
    
    public XYChart.Series montaGraficoProdutosVendidos() {
        
        XYChart.Series grafico = new XYChart.Series<>();
        grafico.setName("Produtos Vendidos");
        
        ObservableList<XYChart.Data> dados = FXCollections.observableArrayList();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                dados.add(new XYChart.Data(produtos.get(i).getNome(), produtos.get(i).getVendidos()));
            }
        }
        grafico.setData(dados);
        return grafico;
    }
    
    public ObservableList<PieChart.Data> montaGraficoMarcas() {
        ObservableList<PieChart.Data> dados = FXCollections.observableArrayList();
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        
        int contadorEudora = 0;
        int contadorJequiti = 0;
        int contadorHinode = 0;
        int contadorLaqua = 0;
        int contadorMary = 0;
        int contadorNatura = 0;
        int contadorBoticario = 0;
        int contadorUp = 0;
        int contadorAvon = 0;
        
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                if (produtos.get(i).getMarca().equals("Eudora")) {
                    contadorEudora += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Jequiti")) {
                    contadorJequiti += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Hinode")) {
                    contadorHinode += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Laqua")) {
                    contadorLaqua += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Mary Kay")) {
                    contadorMary += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Natura")) {
                    contadorNatura += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Boticario")) {
                    contadorBoticario += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("UP")) {
                    contadorUp += produtos.get(i).getVendidos();
                }
                if (produtos.get(i).getMarca().equals("Avon")) {
                    contadorAvon += produtos.get(i).getVendidos();
                }
            }
        }
        
        if (contadorEudora != 0) {
            dados.add(new PieChart.Data("Eudora - " + String.valueOf(contadorEudora), contadorEudora));
        }
        if (contadorJequiti != 0) {
            dados.add(new PieChart.Data("Jequiti - " + String.valueOf(contadorJequiti), contadorJequiti));
        }
        if (contadorHinode != 0) {
            dados.add(new PieChart.Data("Hinode - " + String.valueOf(contadorHinode), contadorHinode));
        }
        if (contadorLaqua != 0) {
            dados.add(new PieChart.Data("Laqua - " + String.valueOf(contadorLaqua), contadorLaqua));
        }
        if (contadorMary != 0) {
            dados.add(new PieChart.Data("Mary Kay - " + String.valueOf(contadorMary), contadorMary));
        }
        if (contadorNatura != 0) {
            dados.add(new PieChart.Data("Natura - " + String.valueOf(contadorNatura), contadorNatura));
        }
        if (contadorBoticario != 0) {
            dados.add(new PieChart.Data("Boticario - " + String.valueOf(contadorBoticario), contadorBoticario));
        }
        if (contadorUp != 0) {
            dados.add(new PieChart.Data("UP - " + String.valueOf(contadorUp), contadorUp));
        }
        if (contadorAvon != 0) {
            dados.add(new PieChart.Data("Avon - " + String.valueOf(contadorAvon), contadorAvon));
        }
        
        return dados;
    }
    
    
}

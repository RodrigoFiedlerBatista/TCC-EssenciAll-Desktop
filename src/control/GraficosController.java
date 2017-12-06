package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import model.Graficos;
import model.TCC;

public class GraficosController implements Initializable {

    @FXML
    private BarChart graficoProdutos;
    
    @FXML
    private PieChart graficoMarcas;

    @FXML
    void produtosVendidos(ActionEvent event) {
        graficoMarcas.setVisible(false);
        graficoProdutos.setVisible(true);
    }

    @FXML
    void marcasVendidas(ActionEvent event) {
        graficoProdutos.setVisible(false);
        graficoMarcas.setVisible(true);
    }

    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Estoque.fxml");
    }
    
    private void iniciaGrafico() {
        Graficos grafico = new Graficos();
        graficoProdutos.getData().clear();
        graficoProdutos.getData().addAll(grafico.montaGraficoProdutosVendidos());
        graficoMarcas.getData().clear();
        graficoMarcas.getData().addAll(grafico.montaGraficoMarcas());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaGrafico();
    }    
    
}

package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Graficos;
import model.TCC;

public class GraficosController implements Initializable {

    @FXML
    private BarChart graficoProdutos;
    
    @FXML
    private PieChart graficoMarcas;
    
    @FXML
    private JFXButton btnSair;

    @FXML
    private ImageView imgLogout;

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
    
    private void acaoBotao() {
        btnSair.setOnMouseEntered(event -> {
            imgLogout.setScaleX(1.1);
            imgLogout.setScaleY(1.1);
        });
        btnSair.setOnMouseExited(event -> {
            imgLogout.setScaleX(1.0);
            imgLogout.setScaleY(1.0);
        });
        btnSair.setTooltip(new Tooltip("Voltar"));
    }
    
    private void iniciaImagem(){
        //graficoMarcas.setStyle(".chart-pie-label { -fx-fill: #ffffff; }");
        imgLogout.setImage(new Image ("File:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaGrafico();
        iniciaImagem();
        acaoBotao();
    }    
    
}

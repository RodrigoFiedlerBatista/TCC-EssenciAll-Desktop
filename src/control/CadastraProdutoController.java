package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.GerenciaArquivos;
import model.GerenciaImagem;
import model.Produto;
import model.TCC;
import model.jdbc.ProdutoDAO;

public class CadastraProdutoController implements Initializable {
    
    @FXML
    private JFXTextField textQuantidade;

    @FXML
    private ImageView imagemIconeSair;

    @FXML
    private ImageView imgProduto;

    @FXML
    private JFXTextArea textDescricao;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField textValor;

    @FXML
    private ImageView imgEssencial;

    @FXML
    private JFXTextField textNome;
    
    private String url = System.getProperty("user.dir") + "\\src\\imagens\\massa_1.jpg";
    
    @FXML
    void voltar(ActionEvent event) {
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Estoque.fxml");
    }

    @FXML
    void trocarImagem(ActionEvent event) {
        GerenciaImagem gerenciaImagem = new GerenciaImagem();
        url = gerenciaImagem.getNovaImagem();
        imgProduto.setImage(new Image("file:///" + url));
    }

    @FXML
    void cadastrar(ActionEvent event) {
        if (verificaValores()) {
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            TCC tcc = new TCC();
            Alertas alertas = new Alertas();
            GerenciaArquivos gerenciaArquivos = new GerenciaArquivos();
            produto.setDescricao(textDescricao.getText());
            produto.setNome(textNome.getText());
            produto.setQuantidade(Integer.valueOf(textQuantidade.getText()));
            produto.setUrls(url);
            produto.setValor(Float.valueOf(textValor.getText()));
            produtoDAO.addProduto(produto);
            gerenciaArquivos.copiaCola(url, System.getProperty("user.dir") + "\\src\\imagens\\produto\\" + textNome.getText() + ".png");
            tcc.fechaTela();
            tcc.iniciaStage("Estoque.fxml");
            alertas.produtoCadastrado();
        }
    }
    
    public boolean verificaValores(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Alertas alertas = new Alertas();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equals(textNome.getText())) {
                alertas.erroNomeProduto();
                return false;
            }
        }
        if (textDescricao.getText().length() > 200) {
            alertas.erroDescricaoProduto();
            return false;
        }
        if ("".equals(textQuantidade.getText()) || "".equals(textValor.getText()) || "".equals(textNome.getText()) || "".equals(textDescricao.getText())) {
            alertas.erroInformacoesProduto();
            return false;
        }
        if (!Pattern.matches("[0-9]+", textQuantidade.getText()) || !Pattern.matches("[0-9]+", textValor.getText())) {
            alertas.erroValorQuantidadeProduto();
            return false;
        }
        return true;
    }
    
    private void iniciaImagem() {
        imgProduto.setImage(new Image("file:///" + url));
        imagemIconeSair.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
    }
    
    private void acaoBotoes() {
        btnVoltar.setOnMouseEntered(event -> {
            imagemIconeSair.setScaleX(1.1);
            imagemIconeSair.setScaleY(1.1);
        });
        btnVoltar.setOnMouseExited(event -> {
            imagemIconeSair.setScaleX(1.0);
            imagemIconeSair.setScaleY(1.0);
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        acaoBotoes();
    }    
    
}

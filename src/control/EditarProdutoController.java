package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Produto;

public class EditarProdutoController implements Initializable {

    @FXML
    private JFXTextField textCodigo;

    @FXML
    private ImageView imgEscolherProdutos;

    @FXML
    private JFXButton btnEscolherProdutos;

    @FXML
    private JFXButton textSalvar;

    @FXML
    private JFXButton btnSomar;

    @FXML
    private JFXTextField textProduto;

    @FXML
    private JFXTextField textPreco;

    @FXML
    private JFXTextField textQuantidade;

    @FXML
    private ImageView imagemIconeSair;

    @FXML
    private JFXButton textCancelar;

    @FXML
    private ImageView imgProduto;

    @FXML
    private JFXTextField textUnidade;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnDiminuir;
    
    private static Produto produto;

    public static void setProduto(Produto produto) {
        EditarProdutoController.produto = produto;
    }
    
    @FXML
    void voltar(ActionEvent event) {

    }
    
    private void iniciaProduto(){
        textQuantidade.setText(String.valueOf(produto.getQuantidade()));
        textProduto.setText(produto.getNome());
        textPreco.setText(String.valueOf(produto.getValor()));
        textCodigo.setText(String.valueOf(produto.getCodigo()));
        textUnidade.setText(String.valueOf(produto.getReserva()));
        imgProduto.setImage(new Image("file:///" + produto.getUrls().get(0)));
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciaProduto();
    }
    
}

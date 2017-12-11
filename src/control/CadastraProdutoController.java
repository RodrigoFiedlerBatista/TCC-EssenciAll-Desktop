package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.GerenciaArquivos;
import model.GerenciaImagem;
import model.Produto;
import model.Revende;
import model.TCC;
import model.Usuario;
import model.jdbc.ProdutoDAO;
import model.jdbc.RevendeDAO;
import model.jdbc.UsuarioDAO;

public class CadastraProdutoController implements Initializable {
    
    @FXML
    private JFXTextField textCodigo;

    @FXML
    private JFXTextField textQuantidade;

    @FXML
    private ImageView imagemIconeSair;

    @FXML
    private JFXComboBox<String> cbMarca;

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
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
            GerenciaArquivos gerenciaArquivos = new GerenciaArquivos();
            produto.setDescricao(textDescricao.getText());
            produto.setNome(textNome.getText());
            produto.setQuantidade(Integer.valueOf(textQuantidade.getText()));
            produto.setUrls(System.getProperty("user.dir") + "\\imagensProdutos\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + "\\" + textNome.getText() + ".png");
            produto.setValor(Float.valueOf(textValor.getText()));
            produto.setMarca(cbMarca.getValue());
            produto.setCodigo(Integer.valueOf(textCodigo.getText()));
            produtoDAO.addProduto(produto);
            gerenciaArquivos.copiaCola(url, System.getProperty("user.dir") + "\\imagensProdutos\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + "\\" + textNome.getText() + ".png");
            tcc.fechaTela();
            tcc.iniciaStage("Estoque.fxml");
            alertas.produtoCadastrado();
        }
    }
    
    public boolean verificaValores(){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Alertas alertas = new Alertas();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                if (produtos.get(i).getNome().equals(textNome.getText())) {
                    alertas.erroNomeProduto();
                    return false;
                }
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
        if (!Pattern.matches("[0-9]+", textCodigo.getText()) || textCodigo.getText().equals("")) {
            alertas.erroCodigoProduto();
            return false;
        }
        if (cbMarca.getValue() == null) {
            alertas.erroCadastraProdutoMarca();
            return false;
        }
        return true;
    }
    
    private void iniciaImagem() {
        imgProduto.setImage(new Image("file:///" + url));
        imagemIconeSair.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
        imgEssencial.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\EssenciAll.png"));
        textNome.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textQuantidade.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textDescricao.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textValor.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textCodigo.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
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
    
    private void iniciaComboBox() {
        RevendeDAO revendeDAO = new RevendeDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Revende> revende = revendeDAO.selectRevende();
        ObservableList<String> marcas = FXCollections.observableArrayList();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < revende.size(); i++) {
            if (revende.get(i).getId_usuario() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                if (revende.get(i).isAvon()) {
                    marcas.add("Avon");
                }
                if (revende.get(i).isBoticario()) {
                    marcas.add("Boticário");
                }
                if (revende.get(i).isEudora()) {
                    marcas.add("Eudora");
                }
                if (revende.get(i).isHinode()) {
                    marcas.add("Hinode");
                }
                if (revende.get(i).isJequiti()) {
                    marcas.add("Jequiti");
                }
                if (revende.get(i).isLaqua()) {
                    marcas.add("Laqua");
                }
                if (revende.get(i).isMary()) {
                    marcas.add("Mary Kay");
                }
                if (revende.get(i).isNatura()) {
                    marcas.add("Natura");
                }
                if (revende.get(i).isUp()) {
                    marcas.add("UP");
                }
            }
        }
        cbMarca.setItems(marcas);
        cbMarca.setButtonCell(new ListCell<String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty); 
                setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
                if(!(empty || item==null)){
                    setText("");
                    setText(item.toString());
                }
            }
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        acaoBotoes();
        iniciaComboBox();
    }    
    
}

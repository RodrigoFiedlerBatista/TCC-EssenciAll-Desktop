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

public class EditarProdutoController implements Initializable {

    @FXML
    private JFXTextField textCodigo;

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
    
    @FXML
    private JFXTextArea textDescricao;
    
    @FXML
    private JFXComboBox<String> cbMarca;
    
    private static Produto produto;
    
    private String url = produto.getUrls().get(0);

    public static void setProduto(Produto produto) {
        EditarProdutoController.produto = produto;
    }
    
    @FXML
    void escolheImagem(ActionEvent event) {
        GerenciaImagem gerencia = new GerenciaImagem();
        url = gerencia.getNovaImagem();
        imgProduto.setImage(new Image("file:///" + url));
    }
    
    private void acaoBotoes(){
        btnDiminuir.setOnMouseClicked(event -> {
            Alertas alertas = new Alertas();
            Integer valor = Integer.valueOf(textUnidade.getText());
            if (valor == 0) {
                alertas.erroReservaProduto();
            } else {
                valor--;
            }
            textUnidade.setText(String.valueOf(valor));
        });
        btnSomar.setOnMouseClicked(event -> {
            Integer valor = Integer.valueOf(textUnidade.getText());
            valor++;
            textUnidade.setText(String.valueOf(valor));
        });
        btnVoltar.setOnMouseEntered(event -> {
            imagemIconeSair.setScaleX(1.1);
            imagemIconeSair.setScaleY(1.1);
        });
        btnVoltar.setOnMouseExited(event -> {
            imagemIconeSair.setScaleX(1.0);
            imagemIconeSair.setScaleY(1.0);
        });
        textSalvar.setOnMouseClicked(event -> {
            if (verificaValores()) {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produtoEditado = new Produto();
                GerenciaArquivos gerencia = new GerenciaArquivos();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
                Alertas alertas = new Alertas();
                TCC tcc = new TCC();
                if (!textProduto.getText().equals(produto.getNome())) {
                    String novaUrl = System.getProperty("user.dir") + "\\imagensProdutos\\" + usuarios.get(Usuario.getUsuarioLogado()).getLogin() + "\\" + textProduto.getText() + ".png";
                    gerencia.move(url, novaUrl);
                    produtoDAO.editaUrlImagem(novaUrl, produto.getId_produto());
                }
                produtoEditado.setCodigo(Integer.valueOf(textCodigo.getText()));
                produtoEditado.setDescricao(textDescricao.getText());
                produtoEditado.setId_produto(produto.getId_produto());
                produtoEditado.setMarca(cbMarca.getValue());
                produtoEditado.setNome(textProduto.getText());
                produtoEditado.setQuantidade(Integer.valueOf(textQuantidade.getText()));
                produtoEditado.setReserva(Integer.valueOf(textUnidade.getText()));
                //produtoEditado.setUrls(url);
                produtoEditado.setValor(Float.valueOf(textPreco.getText()));
                produtoEditado.setVendedor(produto.getVendedor());
                produtoDAO.editaProduto(produtoEditado);
                tcc.fechaTela();
                tcc.iniciaStage("Estoque.fxml");
                alertas.produtoEditado();
            }
        });
        textCancelar.setOnMouseClicked(event -> {
            TCC tcc = new TCC();
            tcc.fechaTela();
            tcc.iniciaStage("Estoque.fxml");
        });
    }
    
    private boolean verificaValores() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Alertas alertas = new Alertas();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        if (!produto.getNome().equals(textProduto.getText())) {
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                    if (produtos.get(i).getNome().equals(textProduto.getText())) {
                        alertas.erroNomeProduto();
                        return false;
                    }
                }
            }
        }
        if (textDescricao.getText().length() > 200) {
            alertas.erroDescricaoProduto();
            return false;
        }
        if ("".equals(textQuantidade.getText()) || "".equals(textPreco.getText()) || "".equals(textProduto.getText()) || "".equals(textDescricao.getText()) || "".equals(textUnidade.getText())) {
            alertas.erroInformacoesProduto();
            return false;
        }
        if (!Pattern.matches("[0-9]+", textQuantidade.getText()) || !Pattern.matches("[0-9].+", textPreco.getText()) || !Pattern.matches("[0-9]+", textUnidade.getText())) {
            alertas.erroValorQuantidadeProduto();
            return false;
        }
        if (cbMarca.getValue() == null) {
            alertas.erroCadastraProdutoMarca();
            return false;
        }
        return true;
    }
    
    private void iniciaProduto(){
        textQuantidade.setText(String.valueOf(produto.getQuantidade()));
        textQuantidade.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textProduto.setText(produto.getNome());
        textProduto.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textPreco.setText(String.valueOf(produto.getValor()));
        textPreco.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textCodigo.setText(String.valueOf(produto.getCodigo()));
        textCodigo.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textUnidade.setText(String.valueOf(produto.getReserva()));
        textUnidade.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        imgProduto.setImage(new Image("file:///" + produto.getUrls().get(0)));
        textDescricao.setStyle("-fx-text-fill: #14fff3; -fx-background-color:  transparent; -fx-prompt-text-fill: #ffffff");
        textDescricao.setText(produto.getDescricao());
        cbMarca.setValue(produto.getMarca());
    }
    
    private void iniciaImagem() {
        imagemIconeSair.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\left-arrow-angle.png"));
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
    public void initialize(URL location, ResourceBundle resources) {
        iniciaComboBox();
        iniciaProduto();
        iniciaImagem();
        acaoBotoes();
        
    }
    
}

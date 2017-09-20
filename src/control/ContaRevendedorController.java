/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.TCC;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class ContaRevendedorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView imgRevendedores;

    @FXML
    private ImageView imgCart;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgConta1;
    
    @FXML
    private ImageView imgEstoque;
    
    @FXML
    private ImageView imgProdutos;
    
    @FXML
    private JFXButton btnProdutos;
 
    @FXML
    private JFXButton btnEstoque;
    
    @FXML
    void pedir(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("Login.fxml");

    }

    @FXML
    void conta(ActionEvent event) {
        
        TCC tcc = new TCC();
        tcc.fechaTela();
        tcc.iniciaStage("HomeRevendedor.fxml");

    }

    @FXML
    void clientes(ActionEvent event) {

    }

    @FXML
    void fechar(ActionEvent event) {
        

    }
    
    public void telaEstoque() {
        
        btnEstoque.setOnMouseClicked(event -> {
        TCC tcc = new TCC();
        tcc.iniciaStage("Estoque.fxml");
         
        });
    }
    
    public void telaProdutos() {
        
        btnProdutos.setOnMouseClicked(event -> {
        TCC tcc = new TCC();
        tcc.iniciaStage("Produtos.fxml");
            
        });        
    }
    
    public void telaEditaConta() {
        
        
        
    }
    
    public void iniciaImagem(){
        
        imgLogout.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\Imagem\\sairBranco.png"));
        imgConta1.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\ContaBranco.png"));
        imgCart.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\cart.png"));
        imgProdutos.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\perfumeProdutos.png"));
        imgEstoque.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\icone-produtos.png"));
        imgRevendedores.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\revendedoresBranco.png"));
        imgLogout.setImage(new Image ("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\sairBranco.png"));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciaImagem();
        telaEstoque();
        telaProdutos();
    }    
    
}

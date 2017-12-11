package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Alertas;
import model.Revende;
import model.TCC;
import model.Usuario;
import model.jdbc.RevendeDAO;
import model.jdbc.UsuarioDAO;


public class TelaempresasController implements Initializable {

    @FXML
    private JFXCheckBox ChekEudora;

    @FXML
    private JFXCheckBox ChekHinode;

    @FXML
    private ImageView ImgAvon;

    @FXML
    private JFXCheckBox ChekBoticario;

    @FXML
    private ImageView ImgMary;

    @FXML
    private ImageView ImgBoticario;

    @FXML
    private ImageView ImgUp;

    @FXML
    private ImageView ImgNatura;

    @FXML
    private JFXCheckBox CkeckNatura;

    @FXML
    private JFXCheckBox ChekAvon;

    @FXML
    private ImageView ImgEudora;

    @FXML
    private JFXCheckBox ChekJequiti;

    @FXML
    private JFXCheckBox ChekLaqua;

    @FXML
    private ImageView ImgLaqua;

    @FXML
    private ImageView ImgHinode;
    
    @FXML
    private JFXButton btnAvon;
    
    @FXML
    private JFXButton btnUp;
    
    @FXML
    private JFXButton btnNatura;

    @FXML
    private JFXButton btnMary;
    
    @FXML
    private JFXButton btnJequiti;
    
    @FXML
    private JFXButton btnEudora;
    
    @FXML
    private JFXButton btnBoticario;
    
    @FXML
    private JFXButton btnLaqua;
    
    @FXML
    private JFXButton btnHinode;

    @FXML
    private JFXCheckBox ChekUP;

    @FXML
    private JFXCheckBox ChekMAry;

    @FXML
    private ImageView ImgJequiti;
    
    @FXML
    private ImageView imgCadastrar;
    
    @FXML
    private ImageView imgFundo;
    
    
    @FXML
    void selecionaAvon(ActionEvent event) {
        if (this.ChekAvon.isSelected()) {
            this.ChekAvon.setSelected(false);
        } else {
            this.ChekAvon.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaEudora(ActionEvent event) {
        if (this.ChekEudora.isSelected()) {
            this.ChekEudora.setSelected(false);
        } else {
            this.ChekEudora.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaHinode(ActionEvent event) {
        if (this.ChekHinode.isSelected()) {
            this.ChekHinode.setSelected(false);
        } else {
            this.ChekHinode.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaJequiti(ActionEvent event) {
        if (this.ChekJequiti.isSelected()) {
            this.ChekJequiti.setSelected(false);
        } else {
            this.ChekJequiti.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaNatura(ActionEvent event) {
        if (this.CkeckNatura.isSelected()) {
            this.CkeckNatura.setSelected(false);
        } else {
            this.CkeckNatura.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaLaqua(ActionEvent event) {
        if (this.ChekLaqua.isSelected()) {
            this.ChekLaqua.setSelected(false);
        } else {
            this.ChekLaqua.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaMaryKay(ActionEvent event) {
        if (this.ChekMAry.isSelected()) {
            this.ChekMAry.setSelected(false);
        } else {
            this.ChekMAry.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaUp(ActionEvent event) {
        if (this.ChekUP.isSelected()) {
            this.ChekUP.setSelected(false);
        } else {
            this.ChekUP.setSelected(true);
        }
        checaSelecionados();
    }

    @FXML
    void selecionaBoticario(ActionEvent event) {
        if (this.ChekBoticario.isSelected()) {
            this.ChekBoticario.setSelected(false);
        } else {
            this.ChekBoticario.setSelected(true);
        }
        checaSelecionados();
    }
    
    private boolean checaMarcas(){
        if (ChekBoticario.isSelected() == false && ChekUP.isSelected() == false && ChekMAry.isSelected() == false && ChekLaqua.isSelected() == false && CkeckNatura.isSelected() == false && ChekJequiti.isSelected() == false && ChekHinode.isSelected() == false && ChekEudora.isSelected() == false && ChekAvon.isSelected() == false) {
            Alertas alertas = new Alertas();
            alertas.erroCadastroMarcas();
            return false;
        } else {
            return true;
        }    
    }
    
    @FXML
    void cadastrar(ActionEvent event) {
        Revende revende = new Revende();
        RevendeDAO revendeDAO = new RevendeDAO();
        TCC tcc = new TCC();
        Alertas alertas = new Alertas();
        if (checaMarcas()) {
            if (this.ChekEudora.isSelected()) {
                revende.setEudora(true);
            } else {
                revende.setEudora(false);
            }
            if (this.ChekAvon.isSelected()) {
                revende.setAvon(true);
            } else {
                revende.setAvon(false);
            }
            if (this.ChekBoticario.isSelected()) {
                revende.setBoticario(true);
            } else {
                revende.setBoticario(false);
            }
            if (this.ChekHinode.isSelected()) {
                revende.setHinode(true);
            } else {
                revende.setHinode(false);
            }
            if (this.ChekJequiti.isSelected()) {
                revende.setJequiti(true);
            } else {
                revende.setJequiti(false);
            }
            if (this.ChekLaqua.isSelected()) {
                revende.setLaqua(true);
            } else {
                revende.setLaqua(false);
            }
            if (this.ChekMAry.isSelected()) {
                revende.setMary(true);
            } else {
                revende.setMary(false);
            }
            if (this.ChekUP.isSelected()) {
                revende.setUp(true);
            } else {
                revende.setUp(false);
            }
            if (this.CkeckNatura.isSelected()) {
                revende.setNatura(true);
            } else {
                revende.setNatura(false);
            }
            revendeDAO.addRevende(revende);

            tcc.fechaTela();
            tcc.iniciaStage("Login.fxml");
            alertas.revendedorCadastrado();
        }
        
        
    }
    
    private void checaSelecionados(){
        
        if (this.ChekAvon.isSelected()) {
            this.ImgAvon.setOpacity(1.0);
            
        } else {
            this.ImgAvon.setOpacity(0.1);
        }
        if (this.ChekBoticario.isSelected()) {
            this.ImgBoticario.setOpacity(1.0);
        } else {
            this.ImgBoticario.setOpacity(0.1);
        }
        if (this.ChekEudora.isSelected()) {
            this.ImgEudora.setOpacity(1.0);
        } else {
            this.ImgEudora.setOpacity(0.1);
        }
        if (this.ChekHinode.isSelected()) {
            this.ImgHinode.setOpacity(1.0);
        } else {
            this.ImgHinode.setOpacity(0.1);
        }
        if (this.ChekJequiti.isSelected()) {
            this.ImgJequiti.setOpacity(1.0);
        } else {
            this.ImgJequiti.setOpacity(0.1);
        }
        if (this.ChekLaqua.isSelected()) {
            this.ImgLaqua.setOpacity(1.0);
        } else {
            this.ImgLaqua.setOpacity(0.1);
        }
        if (this.ChekMAry.isSelected()) {
            this.ImgMary.setOpacity(1.0);
        } else {
            this.ImgMary.setOpacity(0.1);
        }
        if (this.ChekUP.isSelected()) {
            this.ImgUp.setOpacity(1.0);
        } else {
            this.ImgUp.setOpacity(0.1);
        }
        if (this.CkeckNatura.isSelected()) {
            this.ImgNatura.setOpacity(1.0);
        } else {
            this.ImgNatura.setOpacity(0.1);
        }
        
    }

    public void iniciaImagem(){
        ImgAvon.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Imagem7.png"));
        ImgMary.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Mary.png"));
        ImgUp.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\UP.png"));
        ImgNatura.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Natura.png"));
        ImgEudora.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Eudora.png"));
        ImgLaqua.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Laqua.png"));
        ImgHinode.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Hinode.png"));
        ImgJequiti.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Jequiti.png"));
        ImgBoticario.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Oboticario.png"));
        imgCadastrar.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\Cadastrarr.png"));
        imgFundo.setImage(new Image("file:///" + System.getProperty("user.dir") + "\\src\\imagens\\massa.jpg"));
    }
    
    public void acaoBotoes(){
        btnAvon.setOnMouseEntered(event ->{
            ImgAvon.setScaleX(1.1);
            ImgAvon.setScaleY(1.1);
        });
        
        btnAvon.setOnMouseExited(event ->{
            ImgAvon.setScaleX(1.0);
            ImgAvon.setScaleY(1.0);
        });
        
        btnMary.setOnMouseEntered(event ->{
            ImgMary.setScaleX(1.1);
            ImgMary.setScaleY(1.1);
        });
        
        btnMary.setOnMouseExited(event ->{
            ImgMary.setScaleX(1.0);
            ImgMary.setScaleY(1.0);
        });
        
        btnUp.setOnMouseEntered(event ->{
            ImgUp.setScaleX(1.1);
            ImgUp.setScaleY(1.1);
        });
        
        btnUp.setOnMouseExited(event ->{
            ImgUp.setScaleX(1.0);
            ImgUp.setScaleY(1.0);
        });
        
        btnNatura.setOnMouseEntered(event ->{
            ImgNatura.setScaleX(1.1);
            ImgNatura.setScaleY(1.1);
        });
        
        btnNatura.setOnMouseExited(event ->{
            ImgNatura.setScaleX(1.0);
            ImgNatura.setScaleY(1.0);
        });
        
        btnEudora.setOnMouseEntered(event ->{
            ImgEudora.setScaleX(1.1);
            ImgEudora.setScaleY(1.1);
        });
        
        btnEudora.setOnMouseExited(event ->{
            ImgEudora.setScaleX(1.0);
            ImgEudora.setScaleY(1.0);
        });
        
        btnLaqua.setOnMouseEntered(event ->{
            ImgLaqua.setScaleX(1.1);
            ImgLaqua.setScaleY(1.1);
        });
        
        btnLaqua.setOnMouseExited(event ->{
            ImgLaqua.setScaleX(1.0);
            ImgLaqua.setScaleY(1.0);
        });
        
        btnHinode.setOnMouseEntered(event ->{
            ImgHinode.setScaleX(1.1);
            ImgHinode.setScaleY(1.1);
        });
        
        btnHinode.setOnMouseExited(event ->{
            ImgHinode.setScaleX(1.0);
            ImgHinode.setScaleY(1.0);
        });
        
        btnJequiti.setOnMouseEntered(event ->{
            ImgJequiti.setScaleX(1.1);
            ImgJequiti.setScaleY(1.1);
        });
        
        btnJequiti.setOnMouseExited(event ->{
            ImgJequiti.setScaleX(1.0);
            ImgJequiti.setScaleY(1.0);
        });
        
        btnBoticario.setOnMouseEntered(event ->{
            ImgBoticario.setScaleX(1.1);
            ImgBoticario.setScaleY(1.1);
        });
        
        btnBoticario.setOnMouseExited(event ->{
            ImgBoticario.setScaleX(1.0);
            ImgBoticario.setScaleY(1.0);
        });
    }
    
    private void checaUsuario() {
        if (Usuario.getUsuarioLogado() != 0) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
            RevendeDAO revendeDAO = new RevendeDAO();
            ObservableList<Revende> revende = revendeDAO.selectRevende();
            for (int i = 0; i < usuarios.size(); i++) {
                for (int j = 0; j < revende.size(); j++) {
                    if (usuarios.get(i).getId_usuario() == revende.get(j).getId_usuario()) {
                        if (revende.get(j).isAvon()) {
                            this.ChekAvon.setSelected(true);
                        } else {
                            this.ChekAvon.setSelected(false);
                        }
                        if (revende.get(j).isBoticario()) {
                            this.ChekBoticario.setSelected(true);
                        } else {
                            this.ChekBoticario.setSelected(false);
                        }
                        if (revende.get(j).isEudora()) {
                            this.ChekEudora.setSelected(true);
                        } else {
                            this.ChekEudora.setSelected(false);
                        }
                        if (revende.get(j).isHinode()) {
                            this.ChekHinode.setSelected(true);
                        } else {
                            this.ChekHinode.setSelected(false);
                        }
                        if (revende.get(j).isJequiti()) {
                            this.ChekJequiti.setSelected(true);
                        } else {
                            this.ChekJequiti.setSelected(false);
                        }
                        if (revende.get(j).isLaqua()) {
                            this.ChekLaqua.setSelected(true);
                        } else {
                            this.ChekLaqua.setSelected(false);
                        }
                        if (revende.get(j).isMary()) {
                            this.ChekMAry.setSelected(true);
                        } else {
                            this.ChekMAry.setSelected(false);
                        }
                        if (revende.get(j).isNatura()) {
                            this.CkeckNatura.setSelected(true);
                        } else {
                            this.CkeckNatura.setSelected(false);
                        }
                        if (revende.get(j).isUp()) {
                            this.ChekUP.setSelected(true);
                        } else {
                            this.ChekUP.setSelected(false);
                        }
                        checaSelecionados();
                    }
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaImagem();
        acaoBotoes();
        checaUsuario();
    }    
    
}

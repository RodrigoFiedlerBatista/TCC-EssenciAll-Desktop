package model;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class GerenciaImagem {
    
    public static FileChooser telaEscolheImagem = new FileChooser();
    
    public String getNovaImagem(){
        String caminhoImagem = System.getProperty("user.dir") + "\\src\\imagens\\user.png";
        
        ExtensionFilter extensoes = new ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif");
        telaEscolheImagem.getExtensionFilters().add(extensoes);
        File arquivo = telaEscolheImagem.showOpenDialog(new Stage());
        
        
        if(arquivo != null){
            caminhoImagem = arquivo.getAbsolutePath();
            telaEscolheImagem.setInitialDirectory(arquivo.getParentFile());
        }
        return caminhoImagem;
        
    }
    
}

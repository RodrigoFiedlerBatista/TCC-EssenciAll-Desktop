package model;

import control.LoginController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciaArquivos {
    
    public void copiaCola(String arquivo, String destino){
        Path source = Paths.get(arquivo);
        //urlImagem = System.getProperty("user.dir") + "\\src\\imagens\\" + textLogin.getText() + ".png";
        Path dest = Paths.get(destino);
        try {
            Files.copy(source, dest);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleta(String arquivo){
        Path source = Paths.get(arquivo);
        try {
            Files.delete(source);
        } catch (IOException ex) {
            Logger.getLogger(GerenciaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void move(String arquivo, String destino){
        Path source = Paths.get(arquivo);
        //urlImagem = System.getProperty("user.dir") + "\\src\\imagens\\" + textLogin.getText() + ".png";
        Path dest = Paths.get(destino);
        try {
            Files.move(source, dest);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

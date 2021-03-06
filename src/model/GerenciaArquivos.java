package model;

import control.LoginController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    public void makeDir(String diretorio){
        new File(diretorio).mkdirs();
    }
    
    public void renameDir(String diretorio, String novoDiretorio) {
        File file1 = new File(diretorio);
        File file2 = new File(novoDiretorio);
        file1.renameTo(file2);
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

package model;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.jdbc.ProdutoDAO;
import model.jdbc.UsuarioDAO;

public class GeraPdf {
    
    private static DirectoryChooser escolheDiretorio = new DirectoryChooser();
    
    public void gerarPdf(String tabela) throws IOException{
        
        File arquivo = escolheDiretorio.showDialog(new Stage());
        String caminho = arquivo.getAbsolutePath();
        System.out.println(caminho);
        
        Document documento = new Document();
        
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(caminho + "\\" + tabela + ".pdf"));
            documento.open();
            
            if (tabela.equals("ProdutosRevendedor")) {
                documento.add(geraTabelaProdutosRevendedor());
            }
            
            documento.close();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(GeraPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private PdfPTable geraTabelaProdutosRevendedor() throws IOException, BadElementException{
        
        // Tabela de Usuarios
        PdfPTable tabela = new PdfPTable(5);
        PdfPCell linha;
        linha = new PdfPCell(new Phrase("Produtos"));
        linha.setHorizontalAlignment(Element.ALIGN_CENTER);
        linha.setColspan(5);
        tabela.addCell(linha);
        tabela.addCell("Nome");
        tabela.addCell("Marca");
        tabela.addCell("Preço");
        tabela.addCell("Quantidade");
        tabela.addCell("Imagem");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ObservableList<Produto> produtos = produtoDAO.selectProduto();
        ObservableList<Usuario> usuarios = usuarioDAO.selectUsuario();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getVendedor() == usuarios.get(Usuario.getUsuarioLogado()).getId_usuario()) {
                Image imagem = Image.getInstance(produtos.get(i).getUrls().get(0));
                tabela.addCell(produtos.get(i).getNome());
                tabela.addCell(produtos.get(i).getMarca());
                tabela.addCell(String.valueOf(produtos.get(i).getValor()));
                tabela.addCell(String.valueOf(produtos.get(i).getQuantidade()));
                tabela.addCell(imagem);
            }
        }
        return tabela;
    }
    
    
}

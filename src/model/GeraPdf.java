package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeraPdf {
    
    public void gerarPdf(){
        Document documento = new Document();
        
        
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\Rodrigo\\Desktop\\pdf.pdf"));
            documento.open();
            Paragraph paragrafo = new Paragraph();
            paragrafo.add("Esse é um paragrafo!\n");
            documento.add(paragrafo);
            documento.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeraPdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GeraPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

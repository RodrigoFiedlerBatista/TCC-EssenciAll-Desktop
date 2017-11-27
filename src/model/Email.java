package model;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    
    public void enviaEmail(String usuario, String codigo) {
        
        new Thread() {
            
            @Override
            public void run(){
                Properties props = new Properties();
                /** Parâmetros de conexão com servidor Gmail */
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {                   
                            return new PasswordAuthentication("essenciallnoresponse@gmail.com", "essencial@123"); 
                        }
                    });
                /** Ativa Debug para sessão */
                session.setDebug(true);
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("essenciallnoresponse@gmail.com")); //Remetente
                    Address[] toUser = InternetAddress //Destinatário(s)
                        .parse(usuario);  
                    message.setRecipients(Message.RecipientType.TO, toUser);
                    message.setSubject("EssenciAll - Ativação de conta");//Assunto
                    message.setText("Seu código para ativação de conta é: " + codigo);
                    /**Método para enviar a mensagem criada*/
                    Transport.send(message);
                    System.out.println("Feito!!!");
                } catch (MessagingException e) {
                    System.out.println("não enviou!");
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
    
    public void enviaEmailNovaSenha(String usuario, String mensagem){
        new Thread(){
            @Override
            public void run(){
                Properties props = new Properties();
                /** Parâmetros de conexão com servidor Gmail */
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("essenciallnoresponse@gmail.com", "essencial@123"); 
                        }
                    });
                /** Ativa Debug para sessão */
                session.setDebug(true);
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("essenciallnoresponse@gmail.com")); //Remetente
                    Address[] toUser = InternetAddress //Destinatário(s)
                        .parse(usuario);  
                    message.setRecipients(Message.RecipientType.TO, toUser);
                    message.setSubject("EssenciAll - Nova Senha");//Assunto
                    message.setText("Sua nova senha é: " + mensagem);
                    /**Método para enviar a mensagem criada*/      
                    Transport.send(message);
                    System.out.println("Feito!!!");        
                } catch (MessagingException e) {
                    System.out.println("não enviou!");
                    throw new RuntimeException(e);        
                }
            }
        }.start();
    }
    
}


    


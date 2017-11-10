package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {
    
    public void erroInformacoesProduto(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro Cadastro");
        alerta.setHeaderText("Informações");
        alerta.setContentText("Preencha todos os campos para cadastrar o produto!");
        alerta.show();
    }
    
    public void erroValorQuantidadeProduto(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro Cadastro");
        alerta.setHeaderText("Informações");
        alerta.setContentText("Digite somente números nos campos Valor e Quantidade!");
        alerta.show();
    }
    
    public void produtoCadastrado(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Cadastro Produto");
        alerta.setHeaderText("Produto");
        alerta.setContentText("Produto Cadastrado com Sucesso!");
        alerta.show();
    }
    
    public void erroDescricaoProduto(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro Cadastro");
        alerta.setHeaderText("Descrição do Produto");
        alerta.setContentText("Digite uma descrição com menos de 200 caracteres!");
        alerta.show();
    }
    
    public void erroNomeProduto(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro Cadastro");
        alerta.setHeaderText("Nome do Produto");
        alerta.setContentText("Esse nome já existe, digite um nome com menos de 100 caracteres!");
        alerta.show();
    }
    
    public void senhaEnviada(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Recuperar Senha");
        alerta.setHeaderText("Senha");
        alerta.setContentText("Sua nova senha foi enviada em seu email!");
        alerta.show();
    }
    
    public void erroEmailNaoEncontrado(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Email");
        alerta.setContentText("O email informado não está cadastrado.");
        alerta.show();
    }
    
    public void revendedorCadastrado(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Cadastro");
        alerta.setHeaderText("Revendedor");
        alerta.setContentText("Conta cadastrada, ative com o código enviado em seu email.");
        alerta.show();
    }
    
    public void informacoesInalteradas(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Conta");
        alerta.setHeaderText("Informações");
        alerta.setContentText("Suas informações não foram alteradas.");
        alerta.show();
    }
    
    public void erroConfirmacaoSenha(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Confirmação de senha");
        alerta.setContentText("Senha incorreta.");
        alerta.show();
    }
    
    public void informacoesAlteradas(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Conta");
        alerta.setHeaderText("Informações alteradas.");
        alerta.setContentText("Sucesso, se o seu email foi alterado você irá precisar ativar sua conta novamente no próximo login.");
        alerta.show();
    }
    
    public void ativouConta(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Cadastro");
        alerta.setHeaderText("Confirmação de cadastro");
        alerta.setContentText("Sua conta foi ativada!");
        alerta.show();
    }
    
    public void codigoIncorreto(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Conta");
        alerta.setHeaderText("Confirmação de código");
        alerta.setContentText("O código inserido está incorreto.");
        alerta.show();
    }
    
    public void emailEnviado(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Cadastro");
        alerta.setHeaderText("Confirmação de cadastro");
        alerta.setContentText("Um código de confirmação de cadastro foi enviado ao seu email.");
        alerta.show();
    }
    
    public void erroLogin(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Login");
        alerta.setContentText("Login ou senha incorretos");
        alerta.show();
    }
    
    public void erroCadastroUsuarioLogin(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Login");
        alerta.setContentText("Insira informações válidas no Login, seu login deve ter menos de 100 caracteres");
        alerta.show();
    }
    
    public void erroCadastroUsuarioCheckBox(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Cadastro");
        alerta.setContentText("Selecione se você é revendedor ou cliente");
        alerta.show();
    }
    
    public void erroCadastroUsuarioSenha(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Senha");
        alerta.setContentText("Insira informações válidas na Senha, sua senha deve ter menos de 100 caracteres");
        alerta.show();
    }
    
    public void erroCadastroUsuarioEmail(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Email");
        alerta.setContentText("O email inserido não é válido, insira um email válido");
        alerta.show();
    }
    
    public void erroCadastroUsuarioLoginExistente(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Login");
        alerta.setContentText("O login inserido já existe!");
        alerta.show();
    }
    
    public void erroCadastroUsuarioSenhaDiferente(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Senha");
        alerta.setContentText("Confirme a senha correta!");
        alerta.show();
    }
    
    public void usuarioCadastrado(){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Cadastro");
        alerta.setHeaderText("Usuario");
        alerta.setContentText("Usuario cadastrado com sucesso!");
        alerta.show();
    }
    
    public void erroCadastroUsuarioEmailExistente(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Email");
        alerta.setContentText("O email inserido já está cadastrado!");
        alerta.show();
    }
    
    public void erroCadastroMarcas(){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Cadastro");
        alerta.setContentText("Selecione ao menos uma marca!");
        alerta.show();
    }
    
}

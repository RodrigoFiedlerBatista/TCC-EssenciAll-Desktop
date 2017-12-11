package model;

public class Usuario {
    
    private int id_usuario;
    private String login;
    private String senha;
    private String chaveSenha;
    private String email;
    private boolean revendedor;
    private String url_imagem;
    private String codigo;
    private boolean ativado;
    private static int usuarioLogado;
    private String cidade;
    private String endereco;
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static int getUsuarioLogado() {
        return Usuario.usuarioLogado;
    }

    public static void setUsuarioLogado(int usuarioLogado) {
        Usuario.usuarioLogado = usuarioLogado;
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getChaveSenha() {
        return chaveSenha;
    }

    public void setChaveSenha(String chaveSenha) {
        this.chaveSenha = chaveSenha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRevendedor() {
        return revendedor;
    }

    public void setRevendedor(boolean revendedor) {
        this.revendedor = revendedor;
    }
    
    
    
}

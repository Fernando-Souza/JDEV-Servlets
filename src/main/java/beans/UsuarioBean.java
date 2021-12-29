package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UsuarioBean {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String sexo;
    private String perfil;
    private String fotouser;
    private String image64;
    private String extensaofoto;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private String numero;
    private LocalDate nascimento;
    private double rendamensal;
    private List<Telefone> telefones;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public UsuarioBean() {

    }

    public UsuarioBean(Long id, String nome, String email, String login, String senha, String perfil, String sexo,
            String foto) {

        this(id, nome, email, login, perfil, sexo);
        this.senha = senha;
        this.fotouser = foto;
        this.extensaofoto = foto.split(",")[0].split(";")[0].split("/")[1];
        this.image64 = foto.split(",")[1];

    }

    public UsuarioBean(Long id, String nome, String email, String login, String senha, String perfil, String sexo,
            String image64, String extensaofoto) {

        this(id, nome, email, login, perfil, sexo);
        this.senha = senha;
        this.image64 = image64;
        this.extensaofoto = extensaofoto;
        this.fotouser = "data:image/" + extensaofoto + ";base64," + image64;

    }

    public UsuarioBean(Long id, String nome, String email, String login, String senha, String perfil, String sexo) {

        this(id, nome, email, login, perfil, sexo);
        this.senha = senha;

    }

    public UsuarioBean(Long id, String nome, String email, String login, String perfil, String sexo) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.perfil = perfil;
        this.sexo = sexo;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {

        return perfil.equals("Administrador") ? true : false;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getFotouser() {
        return fotouser;
    }

    public void setFotouser(String fotouser) {
        this.fotouser = fotouser;
    }

    public void setFotouser(String baset64, String extensao) {

        this.fotouser = "data:image/" + extensaofoto + ";base64," + image64;
    }

    public String getImage64() {
        return image64;
    }

    public void setImage64(String image64) {
        this.image64 = image64;
    }

    public String getExtensaofoto() {
        return extensaofoto;
    }

    public void setExtensaofoto(String extensaofoto) {
        this.extensaofoto = extensaofoto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNascimento() {
        return this.nascimento == null ? null : nascimento.format(formatter);
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setNascimento(String nascimento) {

        this.nascimento = LocalDate.parse(nascimento, formatter);
    }

    public double getRendamensal() {
        return rendamensal;
    }

    public void setRendamensal(double rendamensal) {
        this.rendamensal = rendamensal;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }

    @Override
    public String toString() {
        return "UsuarioBean [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
                + ", perfil=" + perfil + ", telefones=" + telefones + "]";
    }

}

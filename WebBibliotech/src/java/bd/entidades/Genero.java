package bd.entidades;

import bd.dal.GeneroDAO;
import bd.util.Conexao;
import java.util.List;


public class Genero {
    private int codigo;
    private String nome;

    
    public Genero() {
        this(0, "");
    }
    public Genero(String nome) {
        this(0, nome);
    }
    public Genero(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean gravar(Conexao con) {
        return new GeneroDAO().gravar(con, this);
    }
    public boolean alterar(Conexao con) {
        return new GeneroDAO().alterar(con, this);
    }
    public boolean excluir(Conexao con) {
        return new GeneroDAO().apagar(con, codigo);
    }
    public Genero buscar(Conexao con){
        return new GeneroDAO().get(con, codigo);
    }
    public List<Genero> buscar(Conexao con, String filtro) {
        return new GeneroDAO().get(con, filtro);
    }
    public Genero buscarGen(Conexao con, String nome){
        return new GeneroDAO().getGen(con, nome);
    }
    
    @Override
    public String toString() {
        return codigo + ", " + nome;
    }
    
    
}

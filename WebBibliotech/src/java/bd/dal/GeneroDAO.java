package bd.dal;

import bd.entidades.Genero;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class GeneroDAO {
    public boolean gravar(Conexao con, Genero g){
        String sql = "insert into genero values (default, '#1')";
        sql = sql.replace("#1", g.getNome());
        return con.manipular(sql);
    }
    public boolean alterar(Conexao con, Genero g){
        String sql = "update genero set gen_nome='#1' where gen_cod="+g.getCodigo();
        sql = sql.replace("#1", g.getNome());
        return con.manipular(sql);
    }
    public boolean apagar(Conexao con, int id){
        String sql = "delete from genero where gen_cod="+id;
        return con.manipular(sql);
    }
    public Genero get(Conexao con, int id){
        Genero aux = null;
        String sql = "select * from genero where gen_cod="+id;
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                aux = new Genero(rs.getInt("gen_cod"), rs.getString("gen_nome"));
        }
        catch(Exception e){
        }
        return aux;
    }
    public List<Genero> get(Conexao con, String filtro){
        List<Genero> generos = new ArrayList<>();
        
        String sql = "select * from genero";
        if(!filtro.isEmpty())
            sql += " where " + filtro;
        ResultSet rs = con.consultar(sql);
        try{
            while(rs.next())
                generos.add( new Genero(rs.getInt("gen_cod"), rs.getString("gen_nome")) );
        }
        catch(Exception e){
        }
        return generos;
    }
    public Genero getGen(Conexao con, String nome){
        Genero aux = null;
        String sql = "select * from genero where upper(gen_nome) like '"+nome+"'";
        ResultSet rs = con.consultar(sql);
        try{
            if(rs.next())
                aux = new Genero(rs.getInt("gen_cod"), rs.getString("gen_nome"));
        }
        catch(Exception e){
        }
        return aux;
    }
}

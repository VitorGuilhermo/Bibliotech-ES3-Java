package controller;

import bd.entidades.Genero;
import bd.util.Conexao;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Vitor Guilhermo
 */
public class ControllerGerenciarGenero {
    private static ControllerGerenciarGenero instancia;
    
    private ControllerGerenciarGenero() {
    }
    public static ControllerGerenciarGenero retorna(){
        if (instancia == null)
            instancia = new ControllerGerenciarGenero();
        return instancia;
    }
    public static void removeInstancia() {
        instancia = null;
    }
    public static ControllerGerenciarGenero getInstance() {
        return instancia;
    }
    
    
    public String buscar(String txFiltrar, String chave) {
        String filtro = "upper("+chave+") like '%#%'";
        
        filtro = filtro.replace("#", txFiltrar.toUpperCase());
        
        if(txFiltrar.isEmpty())
            return "";
        else
            return filtro;
    }
    
    /*public void novo() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/bibliotech/TelaCadastrarGenero.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cadastrar Gênero");
        stage.getIcons().add(new Image("img/icone.png"));
        stage.showAndWait();

        ControllerCadastrarGenero.removeInstancia();
    }
    
    public void alterar(int cod, String nome) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/bibliotech/TelaCadastrarGenero.fxml"));
        Parent root = (Parent) loader.load();
        TelaCadastrarGeneroController ctr = loader.getController();
        ctr.setDados(cod, nome);

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Alterar Gênero");
        stage.getIcons().add(new Image("img/icone.png"));
        stage.showAndWait();

        ControllerCadastrarGenero.removeInstancia();
    }*/
    
    public boolean excluir(int cod, String nome) {
        /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exclusão de um Gênero");
        alert.setHeaderText("Confirma exclusão?");
        alert.setContentText("Tem certeza que deseja excluir o gênero: "+nome+" ?");
        Optional<ButtonType> result =  alert.showAndWait();
        
        if(result.get() == ButtonType.OK){*/
            Conexao con = new Conexao();
            
            Genero g = new Genero();
            g.setCodigo(cod);
            g.excluir(con);
            
            con.fecharConexao();
            return true;
        //}
        //return false;
    }
}

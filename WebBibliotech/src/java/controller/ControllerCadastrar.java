package controller;

import bd.util.Conexao;
import javafx.scene.control.Alert;

/**
 *
 * @author Vitor Guilhermo
 */
public abstract class ControllerCadastrar {
 
    public final void templateMethod(String txCod, String txNome){
        Conexao con = getConexao();
        
        if(txNome.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro: Campo NOME vazio");
            alert.showAndWait();
        }
        
        gravarOuAlterar(con, txCod, txNome);
    }
    
    public abstract void gravarOuAlterar(Conexao con, String txCod, String txNome);
    
    public final Conexao getConexao(){
        return new Conexao();
    }
}

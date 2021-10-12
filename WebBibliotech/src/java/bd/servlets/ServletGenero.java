package bd.servlets;

import bd.entidades.Genero;
import bd.util.Conexao;
import controller.ControllerCadastrarGenero;
import controller.ControllerGerenciarGenero;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vitor Guilhermo
 */
@WebServlet(name = "ServletGenero", urlPatterns = {"/ServletGenero"})
public class ServletGenero extends HttpServlet {

    public String buscaFilmes(String filtro) {
        String res = "";
        
        String filtroBusca = ControllerGerenciarGenero.retorna().buscar(filtro, "gen_nome");
        
        Genero gen = new Genero();
        Conexao con = new Conexao();
        
        List<Genero> generos = gen.buscar(con, filtroBusca);
        
        con.fecharConexao();
        
        for(Genero g : generos) {
          res += String.format("<tr><td>%s</td><td>%s</td>"
              + "<td onclick='apagaAltera(\"apagar\",%s)'><img src='icones/apagar.png'/></td>"
              + "<td onclick='apagaAltera(\"alterar\",%s)'><img src='icones/alterar.png'/></a></td>"
              + "</tr>", "" + g.getCodigo(), g.getNome(), "" + g.getCodigo(), "" + g.getCodigo());
        }
        
        return res;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String erro = "";
        String acao = request.getParameter("acao");
        
        int cod;
        try {
            cod = Integer.parseInt(request.getParameter("cod"));
        } catch (Exception e) {
            cod = 0;
        }
        
        switch (acao.toLowerCase()) {
            case "consultar":
                String filtro = request.getParameter("filtro");
                
                response.getWriter().print(buscaFilmes(filtro));
                break;
            case "apagar":
                if (!ControllerGerenciarGenero.retorna().excluir(cod, "")) 
                    erro = "Erro ao apagar o genero";
                response.getWriter().print(erro);
                break;
            case "alterar":
                Genero g = new Genero(cod, "");
                Conexao con = new Conexao();
                g = g.buscar(con);
                con.fecharConexao();
                response.getWriter().print(g);
                break;
            case "confirmar": //novo e alteração
                erro = "ok";
                String nome = request.getParameter("nome");
                
                if (cod == 0) {
                    ControllerCadastrarGenero.retorna().templateMethod(""+cod, nome);
                } 
                else {
                    ControllerCadastrarGenero.retorna().templateMethod(""+cod, nome);
                    //erro = "Erro ao alterar o filme";
                }
                response.getWriter().print(erro);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

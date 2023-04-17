/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import conexionSQL.ConexionBdSql;
/**
 *
 * @author nzpp5k
 */
@WebServlet(name = "CargarPiezas", urlPatterns = {"/CargarPiezas"})
public class CargarPiezas extends HttpServlet {
  ResultSet rs = null;
        ConexionBdSql cn;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("CargarInfoTabla") != null) {
                String fechaBd = request.getParameter("fecha");
                String turnobd = request.getParameter("shift");

                try {
                    System.out.println("Cargando controlador...");
                    cn = new ConexionBdSql();
                    System.out.println("Conectando a la base de datos...");

                    if (turnobd.equals("A")) {
                        rs = cn.GetConsulta("SELECT Line,[F-H Plan],[F-H Real],[F-H Performance],[L-H Plan],[L-H Real],[L-H Performance] \n"
                                + "FROM (SELECT Alias Line,Prog [F-H Plan],[Real] [F-H Real],\n"
                                + "CASE WHEN CONVERT(FLOAT,[Prog])='0' THEN '0' ELSE CONVERT(VARCHAR,ROUND((CONVERT(FLOAT,[Real])/CONVERT(FLOAT,[Prog]))*100,1))+'%' END [F-H Performance], arranque [Line Start],P.modelo_mfg [Mfg Model],\n"
                                + "Plan_dia[Daily Plan], Total_Dia[Daily Output], Dif[Difference],lh_obj [L-H Plan],lh_cant [L-H Real],CASE \n"
                                + "WHEN CONVERT(FLOAT,lh_obj)='0' THEN '0' ELSE CONVERT(VARCHAR,ROUND((CONVERT(FLOAT,lh_cant)/CONVERT(FLOAT,lh_obj))*100,1))+'%' END [L-H Performance] FROM\n"
                                + "(SELECT * FROM Empaques.[dbo].[Resumen2_TA]('" + fechaBd + "'))P \n"
                                + "GROUP BY Alias, Hora, Prog,[Real], arranque, Plan_dia, Total_Dia, Dif,P.modelo_mfg,lh_obj,lh_cant,lh_performance) A");
                        System.out.println("ENTRO AL QUERY A");
                    } else {
                        rs = cn.GetConsulta("SELECT Line,[F-H Plan],[F-H Real],[F-H Performance],[L-H Plan],[L-H Real],[L-H Performance] \n"
                                + "FROM (SELECT Alias Line,Prog [F-H Plan],[Real] [F-H Real],\n"
                                + "CASE WHEN CONVERT(FLOAT,[Prog])='0' THEN '0' ELSE CONVERT(VARCHAR,ROUND((CONVERT(FLOAT,[Real])/CONVERT(FLOAT,[Prog]))*100,1))+'%' END [F-H Performance], arranque [Line Start],P.modelo_mfg [Mfg Model],\n"
                                + "Plan_dia[Daily Plan], Total_Dia[Daily Output], Dif[Difference],lh_obj [L-H Plan],lh_cant [L-H Real],CASE \n"
                                + "WHEN CONVERT(FLOAT,lh_obj)='0' THEN '0' ELSE CONVERT(VARCHAR,ROUND((CONVERT(FLOAT,lh_cant)/CONVERT(FLOAT,lh_obj))*100,1))+'%' END [L-H Performance] FROM\n"
                                + "(SELECT * FROM Empaques.[dbo].[Resumen2_TB]('" + fechaBd + "'))P \n"
                                + "GROUP BY Alias, Hora, Prog,[Real], arranque, Plan_dia, Total_Dia, Dif,P.modelo_mfg,lh_obj,lh_cant,lh_performance) B");
                        System.out.println("ENTRO AL QUERY B");
                    }
                    while (rs.next()) {
                        out.print("<tr>"
                                + "<th scope=\"row\">"+rs.getString(1)+"</th>"
                                + "<td>"+ rs.getString(2)+"</td>"
                                + "<td>"+ rs.getString(3)+"</td>"
                                + "<td>"+ rs.getString(4)+"</td>"
                                + "<td>"+ rs.getString(5)+"</td>"
                                + "<td>"+ rs.getString(6)+"</td>"
                                + "<td>"+ rs.getString(7)+"</td>"
                                + "<tr>");                                         
                            }
                } catch (Exception e) {
                    out.print("error sql " + e);
                }
            }
                       
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

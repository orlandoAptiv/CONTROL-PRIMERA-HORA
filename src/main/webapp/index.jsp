<%-- 
    Document   : newjsp
    Created on : Jan 2, 2023, 3:11:08 PM
    Author     : nzpp5k
--%>


<%@page import="java.sql.*"%>
<%@page import="conexionSQL.ConexionBdSql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>APTIV | MONITORING FIRST HOUR</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/bootstrap.css" rel="stylesheet"/>
        <link href="CSS/bootstrap.min.css" rel="stylesheet"/>
        <link href="CSS/font-awesome.css" rel="stylesheet"/>       
        <link rel="stylesheet" href="CSS/style.css"/>

        <%--  <script language="JavaScript" type="text/javascript" src="JSS/JssIndex.js"></script> -llamar javascript --%>
        
        <style >
            .logod {
                width: 25%;
                margin: 2em auto;
            }
            #form-inicio label {
                color: #000;
                font-weight:900;
            }
        </style>
    </head>
    <%
      
    %>
    <body>
        <header>
            <div class="logod">
                <img alt="Logo" src="img/logo.png"/>
            </div>
        </header>
    <body>
      
        <h1 class="title">RESULTADO PRIMERA Y ULTIMA HORA MFG</h1>     
        <br><!-- comment -->
        <br>
      <div class="container">
        <form class="form" action="index.jsp" method="post" id="form-inicio" >
               <div class="form-group col-xs-12 col-md-3"></div>
                <div class="form-group col-xs-12 col-md-3">
                    <label for='txtFecha'><i class="fa fa-calendar"></i> Fecha</label>
                    <input type="date" class="form-control" id="txtFecha" name="fecha" required="required"/>
                </div>

                <div class="form-group col-xs-12 col-md-3">
                    <label for="cmbTurno"><i class="fa fa-flag-checkered"></i> Turno</label>
                    <select name="shift" id="cmbTurno" class="select form-control" required="required">
                        <option value="">--Seleccione turno :--</option>
                        <option value="A">A</option>
                        <option value="B">B</option>
                    </select>
                </div>

                <div class="form-group col-xs-12 col-md-3"></div>
                <center>
                    <div class="form-group col-xs-12 col-md-12">
                        <br>
                        <input type="submit" id="btn" class="btn btn-success btn-lg" name="CargarInfoTabla" value="Iniciar »"/>
                    </div>
                </center>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Linea</th>
                            <th scope="col">Plan 1er Hr.</th>
                            <th scope="col">Real 1er Hr.</th>
                            <th scope="col">% Efic.</th>                      
                            <th scope="col">Plan Ultima Hr.</th>
                            <th scope="col">Real Utlima Hr.</th>
                            <th scope="col">% Efic.</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <%--aqui mando llamar el Servlet CargarPiezas --%>
                        <jsp:include page="CargarPiezas"/>
                    </tbody>                 
                </table>                                        
      </div> 
      </form> 
      </body>
</html>
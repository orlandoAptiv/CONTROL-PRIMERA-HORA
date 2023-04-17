
package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBdSql {             
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public Connection con = null;

    public ConexionBdSql() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(getTexturl());
        } catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println(e.toString());
        }
    }
    public static String getTexturl() {
        String url="jdbc:sqlserver://dl5mccmh3:1433;database=empaques;user=sa;password=edseps03;";
        return (url);
    }
    
    public ResultSet GetConsulta(String Consulta) {
        ResultSet rsp = null;
        try {
            java.sql.PreparedStatement st = con.prepareStatement(Consulta);
            rsp = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return rsp;
    }
}

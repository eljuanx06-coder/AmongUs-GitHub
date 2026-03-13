import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // Lo que teniamos -> Cambia Connection a Instancia de la clase
    private static DBUtil instancia;

    // Lo nuevo -> Atributo tipo connection
    private Connection conexion;

    // Diferencia: La conexión se guarda en el atributo en vez de en el static
    private DBUtil(){
        final String DB_URL = "jdbc:mysql://localhost:3306/AmongUs";
        final String USER = "root";
        final String PASS = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            this.conexion = conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Comprobamos atributo static
    // - Si vacio -> Generamos y devolvemos
    // - Si lleno -> Solo devolvemos
    public static DBUtil getInstance(){
        if (instancia == null) {
            instancia = new DBUtil();
        }
        return instancia;
    }

    // Getter normal de atributo conexion

    public Connection getConexion() {
        return this.conexion;
    }


}


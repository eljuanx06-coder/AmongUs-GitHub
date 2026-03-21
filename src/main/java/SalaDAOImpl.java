
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaDAOImpl implements SalaDAO{
    @Override
    public void insertar(Sala sala) {

        String sql = "INSERT INTO sala (nombre, saboteada) VALUES (?, ?)";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sala.getNombre());
            pstmt.setBoolean(2, sala.isSaboteada());


            pstmt.executeUpdate();
            System.out.println("Sala guardada en la base de datos: " + sala.getNombre());

        } catch (SQLException e) {
            System.out.println("Error al insertar la sala en la BBDD: " + e.getMessage());
        }
    }

    @Override
    public Sala obtener(int id) {

        String sql = "SELECT * FROM sala WHERE ID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1 , id);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    Sala sala = new Sala(rs.getString("nombre"));
                    sala.setId(rs.getInt("id"));
                    sala.setSaboteada(rs.getBoolean("saboteada"));
                    return sala;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public ArrayList<Sala> obtenerTodos() {
        ArrayList <Sala> lista = new ArrayList<>();
        String sql = "SELECT * FROM sala";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                Sala sala = new Sala(rs.getString("nombre"));
                sala.setId(rs.getInt("id"));
                sala.setSaboteada(rs.getBoolean("saboteada"));
                lista.add(sala);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public void actualizar(Sala sala) {

        String sql = "UPDATE sala SET nombre = ?, saboteada = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, sala.getNombre());
            pstmt.setBoolean(2, sala.isSaboteada());
            pstmt.setInt(3, sala.getId());


            pstmt.executeUpdate();
            System.out.println(" Sala actualizada correctamente :  " + sala.getNombre());

        } catch (SQLException e) {
            System.out.println(" Error al actualizar la sala: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM sala WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1 , id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

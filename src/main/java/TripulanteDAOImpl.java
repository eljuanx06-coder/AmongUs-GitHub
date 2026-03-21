import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TripulanteDAOImpl implements TripulanteDAO {
    @Override
    public void insertar(Tripulante tripulante) {
        String sql = "INSERT INTO tripulante (nombre, rol, vivo) VALUES (?, ?, ?)";

        // ¡Magia aquí! Añadimos Statement.RETURN_GENERATED_KEYS
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1 , tripulante.getNombre());
            pstmt.setString(2 , tripulante.getRol());
            pstmt.setBoolean(3 , tripulante.isVivo());
            pstmt.executeUpdate();

            // Recogemos el ID nuevo y se lo guardamos al objeto de Java
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tripulante.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tripulante obtener(int id) {
        String sql = "SELECT * FROM tripulante WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
           pstmt.setInt(1 , id);
           try  (ResultSet rs = pstmt.executeQuery()){
               if (rs.next()){
                    return mapearTripulante(rs);
               }
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Tripulante mapearTripulante(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String rol = rs.getString("rol");

        Tripulante t = null;

        switch (rol.toLowerCase()) {
            case "ingeniero":
                t = new Ingeniero(nombre);
                break;
            case "medico":
                t = new Medico(nombre);
                break;
            case "capitan":
                t = new Capitan(nombre);
                break;
            case "impostor":
                t = new Impostor(nombre);
                break;
        }

        if (t != null) {
            t.setId(rs.getInt("id"));
            t.setVivo(rs.getBoolean("vivo"));
        }

        return t;
    }

    @Override
    public ArrayList<Tripulante> obtenerTodos() {
        ArrayList <Tripulante> lista = new ArrayList<>();
        String sql = "SELECT * FROM tripulante";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                lista.add(mapearTripulante(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    @Override
    public void actualizar(Tripulante tripulante) {
        String sql = "UPDATE tripulante SET nombre = ?, rol = ?, vivo = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, tripulante.getNombre());
            pstmt.setString(2, tripulante.getRol());
            pstmt.setBoolean(3, tripulante.isVivo());
            pstmt.setInt(4, tripulante.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tripulante WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1 , id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

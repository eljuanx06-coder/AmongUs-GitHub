import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TareaDAOImpl implements TareaDAO {


    private TripulanteDAO tripulanteDAO = new TripulanteDAOImpl();
    private SalaDAO salaDAO = new SalaDAOImpl();

    @Override
    public void insertar(Tarea tarea) {
        String sql = "INSERT INTO tarea (descripcion, completada, id_tripulante, id_sala) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarea.getDescripcion());
            pstmt.setBoolean(2, tarea.isCompletada());
            pstmt.setInt(3, tarea.getTripulanteAsignado().getId());
            pstmt.setInt(4, tarea.getSala().getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar tarea: " + e.getMessage());
        }
    }

    @Override
    public Tarea obtener(int id) {
        String sql = "SELECT * FROM tarea WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearTarea(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tarea: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Tarea> obtenerTodos() {
        ArrayList<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarea";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearTarea(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las tareas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public ArrayList<Tarea> obtenerPorTripulante(int idTrip) {
        ArrayList<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarea WHERE id_tripulante = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idTrip);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTarea(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tareas del tripulante: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Tarea tarea) {
        String sql = "UPDATE tarea SET descripcion = ?, completada = ?, id_tripulante = ?, id_sala = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarea.getDescripcion());
            pstmt.setBoolean(2, tarea.isCompletada());
            pstmt.setInt(3, tarea.getTripulanteAsignado().getId());
            pstmt.setInt(4, tarea.getSala().getId());
            pstmt.setInt(5, tarea.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM tarea WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar tarea: " + e.getMessage());
        }
    }


    private Tarea mapearTarea(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descripcion = rs.getString("descripcion");
        boolean completada = rs.getBoolean("completada");


        int idTripulante = rs.getInt("id_tripulante");
        Tripulante tripulante = tripulanteDAO.obtener(idTripulante);

        int idSala = rs.getInt("id_sala");
        Sala sala = salaDAO.obtener(idSala);


        Tarea tarea = new Tarea(id, descripcion, completada, tripulante, sala);
        return tarea;
    }
}
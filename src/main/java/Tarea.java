public class Tarea {
    private int id;
    private String descripcion;
    private boolean completada;
    private Tripulante tripulanteAsignado;
    private Sala sala;

    public Tarea (int id , String descripcion , boolean completada , Tripulante tripulanteAsignado , Sala sala){
        this.id = id;
        this.descripcion = descripcion ;
        this.completada = completada;
        this.tripulanteAsignado = tripulanteAsignado;
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public boolean isCompletada() {
        return completada;
    }

    public Tripulante getTripulanteAsignado() {
        return tripulanteAsignado;
    }

    public void setTripulanteAsignado(Tripulante tripulanteAsignado) {
        this.tripulanteAsignado = tripulanteAsignado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", completada=" + completada +
                ", tripulanteAsignado=" + tripulanteAsignado +
                ", sala=" + sala +
                '}';
    }
}

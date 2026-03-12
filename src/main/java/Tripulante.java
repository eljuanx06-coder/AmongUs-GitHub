public abstract class Tripulante implements Votable , Trabajable{
    private int id ;
    private String nombre;
    private String rol;
    private boolean vivo;


    public Tripulante (int id , String nombre , String rol , boolean vivo){
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.vivo = vivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public boolean isVivo() {
        return vivo;
    }

    @Override
    public void votar(Tripulante tripulante) {

    }

    @Override
    public void realizarTarea(Tarea tarea) {

    }

    @Override
    public String toString() {
        return "Tripulante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                ", vivo=" + vivo +
                '}';
    }
}

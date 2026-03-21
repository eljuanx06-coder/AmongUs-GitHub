public abstract class Tripulante implements Votable , Trabajable{
    private int id ;
    private String nombre;
    private String rol;
    private boolean vivo;


    public Tripulante (String nombre , String rol ){
        this.nombre = nombre;
        this.rol = rol;
        this.vivo = true;
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

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void votar(Tripulante tripulante) {

    }

    @Override
    public void realizarTarea(Tarea tarea) {

    }

    public abstract void habilidadEspecial();

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

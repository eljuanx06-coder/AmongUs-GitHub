public class Impostor extends Tripulante implements Saboteable {
    public Impostor (String nombre ){
        super(nombre , "impostor");
    }

    @Override
    public void sabotear(Sala sala) {

    }

    @Override
    public void habilidadEspecial() {

    }

    public void eliminar(Tripulante tripulante){

    }

    @Override
    public void realizarTarea(Tarea tarea) {
        System.out.println(this.getNombre() + "esta fingiendo realizar la tarea" + tarea.getDescripcion());
    }
}

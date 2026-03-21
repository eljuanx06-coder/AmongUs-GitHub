public class Impostor extends Tripulante implements Saboteable {
    public Impostor (String nombre ){
        super(nombre , "impostor");
    }

    @Override
    public void sabotear(Sala sala) {
        if (!sala.isSaboteada()){
            sala.setSaboteada(true);
            System.out.println("Has saboteado la sala : " +sala.getNombre());
        } else {
            System.out.println("La sala " +sala.getNombre() + " estaba sanoteada");
        }
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Eres impostor");
    }

    public void eliminar(Tripulante tripulante){
        if (tripulante.isVivo()){
            tripulante.setVivo(false);
            System.out.println("Has eliminado a " +tripulante.getNombre());
        } else {
            System.out.println("Ese tripulante ya esta eliminado . ");
        }
    }

    @Override
    public void realizarTarea(Tarea tarea) {
        System.out.println(this.getNombre() + "esta fingiendo realizar la tarea " + tarea.getDescripcion());
    }
}

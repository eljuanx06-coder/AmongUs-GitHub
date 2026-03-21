public class Ingeniero  extends Tripulante{
    public Ingeniero(String nombre){
        super(nombre , "ingeniero");
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Eres ingeniero : Puedes reparar salas que han sido saboteadas");
    }

    public void repasarSala (Sala sala){
        if (sala.isSaboteada()){
            sala.setSaboteada(false);
            System.out.println(getNombre() + "ha reparado la sala" + sala.getNombre());
        } else {
            System.out.println("La sala" +sala.getNombre() +"no esta saboteada");
            System.out.println("prueba");
        }
    }



}

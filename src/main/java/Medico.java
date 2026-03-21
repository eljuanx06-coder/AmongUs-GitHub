public class Medico extends Tripulante  {
    public Medico (String nombre){
        super(nombre , "medico");
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Eres medico");
    }

    public void examinar (Tripulante tripulante){
        if (tripulante.getRol().equalsIgnoreCase("impostor")){
            System.out.println("ALERTA");
        } else {
            System.out.println("Todo limpio" +tripulante.getNombre() + "es un tripulante sano y normal" );
        }
    }
}

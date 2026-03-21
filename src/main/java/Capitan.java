public class Capitan extends Tripulante {

    public Capitan (String nombre){
        super(nombre , "Capitan");
    }

    @Override
    public void habilidadEspecial() {
        System.out.println("Eres capitan");
    }

    public void convocarVotacion (Nave nave){
        System.out.println("Atencion" +getNombre() + "ha pulsado");
        nave.iniciarVotacion();
    }
}

import java.util.ArrayList;

public class Nave {
    ArrayList<Tripulante>tripulantes;
    ArrayList<Sala>salas;
    ArrayList<Tarea>tareas;

    public Nave(ArrayList<Tripulante> tripulantes, ArrayList<Sala> salas) {
        this.tripulantes = tripulantes;
        this.salas = salas;
        this.tareas = new ArrayList<>();
    }

    public ArrayList<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void agregarTarea(Tarea tarea){
     this.tareas.add(tarea);
    }
    public void limpiarPantalla(){

    }

    public void mostrarEstadoNave(){

    }
    public  void iniciarVotacion(){

    }
    public boolean verificarVictoriaTripulantes(){
        return false;
    }
    public boolean verificarVictoriaImpostor(){
        return false;
    }
    public void turno(){

    }

}

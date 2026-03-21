import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TripulanteDAO tripulanteDAO = new TripulanteDAOImpl();
        SalaDAO salaDAO = new SalaDAOImpl();
        TareaDAO tareaDAO = new TareaDAOImpl();

        System.out.println(" ---------- AMONG US ----------\n");
        System.out.println("\n Cuantos tripulante van a jugar");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();

        ArrayList <String> roles = new ArrayList<>();
        roles.add("impostor");
        roles.add("capitan");

        for (int i = 2; i < numJugadores; i++) {
            if (i % 2 == 0) {
                roles.add("medico");
            } else {
                roles.add("ingeniero");
            }
        }

        Collections.shuffle(roles);

        ArrayList <Tripulante> tripulantes = new ArrayList<>();

        for (int i = 0; i < numJugadores; i++) {
            System.out.println("Nombre del tripulante" + (i + 1) + " : ");
            String nombre = scanner.nextLine();
            String rolAsignado = roles.get(i);
            Tripulante nuevoTripulante = null;
            switch (rolAsignado){
                case "impostor" :
                    nuevoTripulante = new Impostor(nombre);
                    break;
                case "capitan" :
                    nuevoTripulante = new Capitan(nombre);
                    break;
                case "medico" :
                    nuevoTripulante = new Medico(nombre);
                    break;
                case "ingeniero" :
                    nuevoTripulante = new Ingeniero(nombre);
                    break;
            }

            tripulantes.add(nuevoTripulante);
            tripulanteDAO.insertar(nuevoTripulante);
        }

        System.out.println("Creando salas de la nave");
        String [] nomsalas = {"Reactor", "Cafeteria", "Navegacion", "Electricidad", "Armamento", "Comunicaciones"};
        ArrayList <Sala> salas = new ArrayList<>();
        for (String nombreSala : nomsalas){
            Sala sala = new Sala(nombreSala);
            salas.add(sala);
            salaDAO.insertar(sala);
        }

        Nave nave = new Nave(tripulantes , salas);

        System.out.println("Asignando tareas");
        for (Tripulante t : tripulantes){
            Tarea tarea1 = new Tarea(0 , "Revisar panel" , false , t , salas.get(0));
            Tarea tarea2 = new Tarea(0, "Limpiar conductos", false, t, salas.get(1));
            nave.agregarTarea(tarea1);
            nave.agregarTarea(tarea2);
            tareaDAO.insertar(tarea1);
            tareaDAO.insertar(tarea2);
        }

        System.out.println("Todo listo . Los roles han sido asignados" );
        System.out.println("\nPula Enter para comenzar la partida ");

        boolean finJuego = false;
        while (!finJuego){
            nave.turno();
            if (nave.verificarVictoriaTripulantes()){
                System.out.println("\n Victoria DE TRIPULANTES GG");
                finJuego = true;
            } else if (nave.verificarVictoriaImpostor()) {
                System.out.println("\n victoria DEL IMPOSTOR GG");
                System.out.println("\n");
                finJuego = true;
            }

            System.out.println("PARTIDA FINALIZADA ");
            System.out.println("ESPERO QUE OS HAYA GUSTADO EL JUEGO");
        }
    }
}

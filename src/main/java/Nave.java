import java.util.ArrayList;
import java.util.Scanner;

public class Nave {
    ArrayList<Tripulante>tripulantes;
    ArrayList<Sala>salas;
    ArrayList<Tarea>tareas;
    private Scanner scanner;

    public Nave(ArrayList<Tripulante> tripulantes, ArrayList<Sala> salas) {
        this.tripulantes = tripulantes;
        this.salas = salas;
        this.tareas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
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
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public void mostrarEstadoNave(){
        System.out.println("\n=== ESTADO DE LA NAVE ===");

        System.out.print("Tripulantes vivos: ");
        for (Tripulante t : tripulantes) {
            if (t.isVivo()) System.out.print(t.getNombre() + " ");
        }
        System.out.println();

        System.out.print("Salas saboteadas: ");
        boolean haySabotajes = false;
        for (Sala s : salas) {
            if (s.isSaboteada()) {
                System.out.print(s.getNombre() + " ");
                haySabotajes = true;
            }
        }
        if (!haySabotajes) System.out.print("Ninguna");
        System.out.println();

        // Contar tareas
        int completadas = 0;
        for (Tarea t : tareas) {
            if (t.isCompletada()) completadas++;
        }
        System.out.println("Tareas completadas: " + completadas + "/" + tareas.size());
        System.out.println("=========================\n");


    }
    public  void iniciarVotacion(){
        System.out.println("\n=== VOTACIÓN DE EMERGENCIA ===");
        // Array para contar los votos (la posición coincide con el índice del tripulante)
        int[] votos = new int[tripulantes.size()];

        for (int i = 0; i < tripulantes.size(); i++) {
            Tripulante votante = tripulantes.get(i);
            if (!votante.isVivo()) continue; // Los muertos no votan

            limpiarPantalla();
            System.out.println("¡Turno de voto de " + votante.getNombre() + "!");
            System.out.println("¿A quién votas para expulsar?");

            for (int j = 0; j < tripulantes.size(); j++) {
                if (tripulantes.get(j).isVivo()) {
                    System.out.println((j + 1) + ") " + tripulantes.get(j).getNombre());
                }
            }
            System.out.println("0) Saltar voto");

            System.out.print("Elige una opción: ");
            int voto = scanner.nextInt();

            if (voto > 0 && voto <= tripulantes.size()) {
                votos[voto - 1]++;
                System.out.println(votante.getNombre() + " ha emitido su voto.");
            } else {
                System.out.println(votante.getNombre() + " ha decidido saltar el voto.");
            }
        }

        // Calcular quién tiene más votos
        int maxVotos = 0;
        int idExpulsado = -1;
        boolean empate = false;

        System.out.println("\n=== RESULTADO DE LA VOTACIÓN ===");
        for (int i = 0; i < votos.length; i++) {
            if (tripulantes.get(i).isVivo()) {
                System.out.println(tripulantes.get(i).getNombre() + ": " + votos[i] + " votos");
                if (votos[i] > maxVotos) {
                    maxVotos = votos[i];
                    idExpulsado = i;
                    empate = false;
                } else if (votos[i] == maxVotos && maxVotos > 0) {
                    empate = true;
                }
            }
        }

        if (!empate && maxVotos > 0) {
            Tripulante expulsado = tripulantes.get(idExpulsado);
            expulsado.setVivo(false);
            System.out.println("\n" + expulsado.getNombre() + " ha sido expulsado de la nave...");
            if (expulsado.getRol().equalsIgnoreCase("impostor")) {
                System.out.println("¡" + expulsado.getNombre() + " ERA el Impostor!");
            } else {
                System.out.println(expulsado.getNombre() + " NO era el Impostor.");
            }
        } else {
            System.out.println("\nEmpate o falta de votos. Nadie ha sido expulsado.");
        }


    }
    public boolean verificarVictoriaTripulantes(){        for (Tripulante t : tripulantes) {
        if (t.getRol().equalsIgnoreCase("impostor") && !t.isVivo()) {
            return true;
        }
    }
        // O ganan si TODAS las tareas están completadas
        boolean todasCompletas = true;
        for (Tarea t : tareas) {
            if (!t.isCompletada()) {
                todasCompletas = false;
                break;
            }
        }
        return todasCompletas;


    }
    public boolean verificarVictoriaImpostor(){
        int vivosNormales = 0;
        int vivosImpostores = 0;

        for (Tripulante t : tripulantes) {
            if (t.isVivo()) {
                if (t.getRol().equalsIgnoreCase("impostor")) {
                    vivosImpostores++;
                } else {
                    vivosNormales++;
                }
            }
        }
        return vivosImpostores >= vivosNormales;


    }
    public void turno(){
        for (Tripulante jugador : tripulantes) {
            if (!jugador.isVivo()) continue; // Si está muerto, saltamos su turno

            limpiarPantalla();
            System.out.println("¡Pasa el ordenador a " + jugador.getNombre() + "!");
            System.out.println("Pulsa Enter cuando estés listo...");
            scanner.nextLine(); // Pausa
            scanner.nextLine(); // Pausa

            System.out.println("TURNO DE " + jugador.getNombre().toUpperCase());
            System.out.println("Tu rol secreto: " + jugador.getRol().toUpperCase());

            mostrarEstadoNave();

            System.out.println("¿Qué quieres hacer?");
            System.out.println("1) Realizar/Simular tarea");
            System.out.println("2) Usar habilidad especial");
            System.out.println("3) Convocar votación");
            System.out.println("4) Pasar turno");
            System.out.print("Elige opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has elegido trabajar en tus tareas.");
                    // Aquí podrías listar las tareas del jugador y marcar una,
                    // por simplicidad marcamos la primera que encontremos no completada
                    for (Tarea t : tareas) {
                        if (t.getTripulanteAsignado().getNombre().equals(jugador.getNombre()) && !t.isCompletada()) {
                            jugador.realizarTarea(t);
                            if (!jugador.getRol().equalsIgnoreCase("impostor")) {
                                t.setCompletada(true);
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    jugador.habilidadEspecial();
                    break;
                case 3:
                    // Si es el capitán usa su método, si no, es un voto normal
                    if (jugador instanceof Capitan) {
                        ((Capitan) jugador).convocarVotacion(this);
                    } else {
                        System.out.println(jugador.getNombre() + " ha pulsado el botón de emergencia.");
                        this.iniciarVotacion();
                    }
                    break;
                case 4:
                    System.out.println("Has decidido pasar el turno.");
                    break;
                default:
                    System.out.println("Opción no válida. Pierdes el turno.");
            }

            System.out.println("Pulsa Enter para finalizar tu turno...");
            scanner.nextLine(); // Limpiar buffer
            scanner.nextLine(); // Pausa real
        }


    }

}

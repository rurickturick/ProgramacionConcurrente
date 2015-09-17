
// ************* Pecusa class. Author: Simon Pickin **************

// PECUSA: = el Primero que Entra Cierra, el Último que Sale Abre
public class Pecusa {

	int contador = 0;
	Semaphore mi_semaforo;

	// Suponemos que sem es un Semáforo inicializado a 1
	public Pecusa(Semaphore sem){
		mi_semaforo = sem;
	}

	public synchronized void cerrar_al_otro_genero_si_primero() {
		contador++;
		if (contador == 1)
			try{mi_semaforo.P();} catch(Exception e) {}
	}

	public synchronized void abrir_al_otro_genero_si_ultimo() {
		contador--;
		if (contador == 0) mi_semaforo.V();
	}
}

import java.util.Random;


public class Persona extends Thread{

	// Random Number Generator
	static Random rndBath = new Random();
	static Random rndWork = new Random();
	
	private boolean esHombre;
	static Pecusa pecHombre;
	static Pecusa pecMujer;
	private int id;
	
	
	static Semaphore semLibre;
	
	public Persona(int i, boolean h, Semaphore sem){
		esHombre=h;
		id=i;
		semLibre=sem;
		if (h && (pecHombre == null)) {
			pecHombre = new Pecusa(sem);
		}
		if (!h && (pecMujer== null)) {
			pecMujer = new Pecusa(sem);
		}
	}
	
	private void utilizarServicios(){
		try {
			sleep(rndBath.nextInt(10));
		} catch (InterruptedException e) {
			System.err.println("Interrupted while sleeping");
		}
	}
	
	private void trabajar(){
		try {
			sleep(rndWork.nextInt(10));
		} catch (InterruptedException e) {
			System.err.println("Interrupted while sleeping");
		}
	}
	
	public void run(){
		while(true){
			trabajar();
			
			try{semLibre.P();} catch (Exception e) { }
			if(esHombre){
				
				pecHombre.cerrar_al_otro_genero_si_primero();
				System.out.println("El hombre con id "+id+" empieza a usar el servicio.");				
				utilizarServicios();
				
				semLibre.V();
				System.out.println("El hombre con id "+id+" se pone a trabajar.");
				pecHombre.abrir_al_otro_genero_si_ultimo();
							
				
			}
			else{
				
				pecMujer.cerrar_al_otro_genero_si_primero();
				System.out.println("La mujer con id "+id+" empieza a usar el servicio.");				
				utilizarServicios();
				
				semLibre.V();
				System.out.println("La mujer con id "+id+" se pone a trabajar.");
				pecMujer.abrir_al_otro_genero_si_ultimo();
				
			
				
			}
		}		
	}	
}

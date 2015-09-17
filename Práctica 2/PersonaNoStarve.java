
public class PersonaNoStarve extends Persona {

	private boolean esHombre;
	private int id;
	
	
	public PersonaNoStarve(int i, boolean h, Semaphore sem) {
		super(i, h, sem);
		esHombre=h;
		id=i;
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
			
			try{semLibre.P();semLibre.V();} catch (Exception e) { } //Torniquete
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

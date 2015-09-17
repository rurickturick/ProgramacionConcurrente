package parte2;

public class MonitorPrioridadEscritores implements MonitorArbitraje {

	private int numLectores = 0;
	private boolean escritor = false; 
	private int escritoresEnEspera =0 ;

	public synchronized void entrarLeer(int numHilo) throws InterruptedException {
		while(escritor || escritoresEnEspera > 0){
			 try {wait();} catch (InterruptedException e) {}
		}
		numLectores++;
		System.out.println("Lector " + numHilo + " va a empezar a leer");
	}

	public synchronized void salirLeer(int numHilo) {
		System.out.println("Lector " + numHilo + " ha terminado de leer");
		numLectores--;
		notifyAll();
	}

	public synchronized void entrarEscribir(int numHilo) throws InterruptedException {
		escritoresEnEspera++;
		while(escritor || numLectores > 0){
			 try {wait();} catch (InterruptedException e) {}
		}
		escritoresEnEspera--;
		escritor = true;
		System.out.println("Escritor " + numHilo + " va a empezar a escribir");
	}

	public synchronized void salirEscribir(int numHilo) {
		System.out.println("Escritor " + numHilo + " ha terminado de escribir");
		escritor = false;
		notifyAll();
	}
	
}

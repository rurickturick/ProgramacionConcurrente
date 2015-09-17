package parte2;

public class MonitorFIFO implements MonitorArbitraje {

	private int numLectores = 0;
	private boolean escritor = false; 
	private Secuenciador seq;
	private int turno;
	private int vez;

	public MonitorFIFO(){
		seq = new Secuenciador();
		turno = 0;
	}
	
	public synchronized void entrarLeer(int numHilo) throws InterruptedException {
		vez = seq.ticket();
		
		while(escritor && (turno!=vez)){
			 try {wait();} catch (InterruptedException e) {}
		}
		numLectores++;
		System.out.println("Lector " + numHilo + " va a empezar a leer");
	}

	public synchronized void salirLeer(int numHilo) {
		System.out.println("Lector " + numHilo + " ha terminado de leer");
		numLectores--;
		notifyAll();
		turno++;
	}

	public synchronized void entrarEscribir(int numHilo) throws InterruptedException {
		vez = seq.ticket();
		while(escritor && (turno!=vez)){
			 try {wait();} catch (InterruptedException e) {}
		}
		escritor = true;
		System.out.println("Escritor " + numHilo + " va a empezar a escribir");
	}

	public synchronized void salirEscribir(int numHilo) {
		System.out.println("Escritor " + numHilo + " ha terminado de escribir");
		escritor = false;
		notifyAll();
		turno++;
	}
	
}

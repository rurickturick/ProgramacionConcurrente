package parte2;

import java.util.HashMap;

public class ReentranteLectura implements MonitorArbitraje {

	private int numLectores = 0;
	private boolean escritor = false; 
	private int escritoresEnEspera =0 ;
	private HashMap<Long,Integer> entrado ; 
	private HashMap<Long,Integer> salido ; 

	public ReentranteLectura(){
		entrado = new HashMap<Long,Integer>();
		salido = new HashMap<Long,Integer>();
	}
	public synchronized void entrarLeer(Thread t) throws InterruptedException {
		if(entrado.get(t.getId())==salido.get(t.getId())){
			while(escritor || escritoresEnEspera > 0){
				 try {wait();} catch (InterruptedException e) {}
			}
			if(entrado.containsKey(t.getId())){
				int veces=entrado.get(t.getId());
				entrado.put(t.getId(), ++veces);
			}
			else entrado.put(t.getId(), 1);
			numLectores++;
			System.out.println("Lector " + t.getId() + " va a empezar a leer");
		}
	}

	public synchronized void salirLeer(Thread t) {
		System.out.println("Lector " + t.getId() + " ha terminado de leer");
		numLectores--;
		if(salido.containsKey(t.getId())){
			int veces=salido.get(t.getId());
			salido.put(t.getId(), ++veces);
		}
		else salido.put(t.getId(), 1);
		notifyAll();
	}

	public synchronized void entrarEscribir(Thread t) throws InterruptedException {
		escritoresEnEspera++;
		while(escritor || numLectores > 0){
			 try {wait();} catch (InterruptedException e) {}
		}
		escritoresEnEspera--;
		escritor = true;
		System.out.println("Escritor " + t.getId() + " va a empezar a escribir");
	}

	public synchronized void salirEscribir(Thread t) {
		long id = t.getId();
		System.out.println("Escritor " + id + " ha terminado de escribir");
		escritor = false;
		notifyAll();
	}
	
}

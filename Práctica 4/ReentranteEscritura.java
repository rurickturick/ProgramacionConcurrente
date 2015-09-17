package parte2;

import java.util.HashMap;

public class ReentranteEscritura implements MonitorArbitraje {

	private boolean escritor = false; 
	private HashMap<Long,Integer> entrado ; 
	private HashMap<Long,Integer> salido ;
	private long thread = -1 ;

	public ReentranteEscritura(){
		entrado = new HashMap<Long,Integer>();
		salido = new HashMap<Long,Integer>();
	}
	public synchronized void entrarLeer(Thread t) throws InterruptedException {
		long id = t.getId();
		if(entrado.get(id)==salido.get(id)){
			while(escritor){
				 try {wait();} catch (InterruptedException e) {}
			}
			if(entrado.containsKey(id)){
				int veces=entrado.get(id);
				entrado.put(id, ++veces);
			}
			else entrado.put(id, 1);
			System.out.println("Lector " + id+ " va a empezar a leer");
		}
	}

	public synchronized void salirLeer(Thread t) {
		long id = t.getId();
		System.out.println("Lector " + id + " ha terminado de leer");
		if(salido.containsKey(id)){
			int veces=salido.get(id);
			salido.put(id, ++veces);
		}
		else salido.put(id, 1);
		notifyAll();
	}

	public synchronized void entrarEscribir(Thread t) throws InterruptedException {
		long id = t.getId();
		if(thread!=id){
			while(!entrado.isEmpty() && !salido.isEmpty()){
				 try {wait();} catch (InterruptedException e) {}
			}
			thread=id;
			escritor = true;
			System.out.println("Escritor " + id + " va a empezar a escribir");
		}
	}

	public synchronized void salirEscribir(Thread t) {
		long id = t.getId();
		System.out.println("Escritor " + id + " ha terminado de escribir");
		escritor = false;
		thread=-1;
		notifyAll();
	}
	
}

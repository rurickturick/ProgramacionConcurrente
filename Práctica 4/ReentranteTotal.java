package parte2;

import java.util.HashMap;

public class ReentranteTotal implements MonitorArbitraje {

	private boolean escritor = false; 
	private HashMap<Long,Integer> entrado ; 
	private HashMap<Long,Integer> salido ;
	private long thread = -1 ;
	private int numLectores = 0;
	private int numEscritores = 0;

	public ReentranteTotal(){
		entrado = new HashMap<Long,Integer>();
		salido = new HashMap<Long,Integer>();
	}
	public synchronized void entrarLeer(Thread t) throws InterruptedException {
		long id = t.getId();
		if(puedoLeer(t)){
			while(escritor){
				 try {wait();} catch (InterruptedException e) {}
			}
			if(entrado.containsKey(id)){
				int veces=entrado.get(id);
				entrado.put(id, ++veces);
			}
			else entrado.put(id, 1);
			System.out.println("Lector " + id+ " va a empezar a leer");
			numLectores++;
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
		numLectores--;
		notifyAll();		
	}

	public synchronized void entrarEscribir(Thread t) throws InterruptedException {
		long id = t.getId();
		if(thread!=id){
			while(!puedoEscribir(t)){
				 try {wait();} catch (InterruptedException e) {}
			}
			thread=id;
			escritor = true;
			System.out.println("Escritor " + id + " va a empezar a escribir");
			numEscritores++;
		}
	}

	public synchronized void salirEscribir(Thread t) {
		long id = t.getId();
		System.out.println("Escritor " + id + " ha terminado de escribir");
		escritor = false;
		thread=-1;
		numEscritores--;
		notifyAll();
	}
	
	private boolean puedoLeer(Thread t){
		return entrado.get(t.getId())==salido.get(t.getId()) && numEscritores==0  ;
	}
	
	private boolean puedoEscribir(Thread t){
		boolean result=false;
		if (numLectores==0) result= true;
		else if(numLectores > 1) {}
		    else{ if (numLectores==1){
			
			    if(entrado.get(t.getId())==1){
				  if(salido.get(t.getId())==null) result=true;
			     }
			     if(salido.get(t.getId())!=null &&entrado.get(t.getId())!=null) 
			       if(salido.get(t.getId())+1==entrado.get(t.getId())) result=true;	
		        }
		    }
		return result;
	}
	
}

package parte2;

public class LectorReentrante extends Thread {

	private MonitorArbitraje monArb;
	private BaseDatos baseDatos;
	private int miNum;
    
    


	LectorReentrante(MonitorArbitraje mon, BaseDatos base, int num) {
		monArb = mon;
		baseDatos = base;
		miNum = num;
		
	}

	public void run() {
		try {
			while (true) {
				monArb.entrarLeer(currentThread());
				monArb.entrarLeer(currentThread());
				Thread.sleep(3500); 
				if (Math.random()<0.5) Thread.sleep(2500); 
				System.out.println("Reader " + currentThread().getId() + " reads " + baseDatos.leer());
				monArb.salirLeer(currentThread());
			}
		} catch (InterruptedException e){}
	}
	
	
	
}

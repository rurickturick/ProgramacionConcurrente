package parte2;

public class LectorEscritor extends Thread {

	private MonitorArbitraje monArb;
	private BaseDatos baseDatos;
	private int miNum;

	LectorEscritor(MonitorArbitraje mon, BaseDatos base, int num) {
		monArb = mon;
		baseDatos = base;
		miNum = num;
	}

	public void run() {
		try {
			while (true) {

				monArb.entrarEscribir(currentThread());
				monArb.entrarLeer(currentThread());
				
				Thread.sleep(3500); 
				if (Math.random()<0.5) Thread.sleep(2500); 
				baseDatos.escribir((int)currentThread().getId());
				System.out.println("Escritor " + currentThread().getId() + " ecribe su numero");

				monArb.salirLeer(currentThread());
				monArb.salirEscribir(currentThread());
			}
		} catch (InterruptedException e){}
	}
	
}

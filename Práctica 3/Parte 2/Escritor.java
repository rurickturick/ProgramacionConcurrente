package parte2;

public class Escritor extends Thread {

	private MonitorArbitraje monArb;
	private BaseDatos baseDatos;
	private int miNum;

	Escritor(MonitorArbitraje mon, BaseDatos base, int num) {
		monArb = mon;
		baseDatos = base;
		miNum = num;
	}

	public void run() {
		try {
			while (true) {
				monArb.entrarEscribir(miNum);
				Thread.sleep(3500); 
				if (Math.random()<0.5) Thread.sleep(2500); 
				baseDatos.escribir(miNum);
				System.out.println("Escritor " + miNum + " ecribe su numero");
				monArb.salirEscribir(miNum);
			}
		} catch (InterruptedException e){}
	}
	
}

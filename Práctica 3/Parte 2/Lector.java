package parte2;

public class Lector extends Thread {

	private MonitorArbitraje monArb;
	private BaseDatos baseDatos;
	private int miNum;

	Lector(MonitorArbitraje mon, BaseDatos base, int num) {
		monArb = mon;
		baseDatos = base;
		miNum = num;
	}

	public void run() {
		try {
			while (true) {
				monArb.entrarLeer(miNum);
				Thread.sleep(3500); 
				if (Math.random()<0.5) Thread.sleep(2500); 
				System.out.println("Reader " + miNum + " reads " + baseDatos.leer());
				monArb.salirLeer(miNum);
			}
		} catch (InterruptedException e){}
	}
	
}

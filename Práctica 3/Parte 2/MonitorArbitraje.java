package parte2;

public interface MonitorArbitraje {

	public void entrarLeer(int numHilo) throws InterruptedException;
	public void salirLeer(int numHilo);
	public void entrarEscribir(int numHilo) throws InterruptedException;
	public void salirEscribir(int numHilo);
  
}

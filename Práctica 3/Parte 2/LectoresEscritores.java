package parte2;

public class LectoresEscritores {

	static int numLectores = 4; 
	static int numEscritores = 2; 

	public static void main(String[] argv) {
 
		BaseDatos base = new BaseDatos();
		MonitorFIFO mon = new MonitorFIFO();

		try {
			for (int i = 0; i < numLectores; ++i) 
				new Lector(mon, base ,i).start();
			for (int i = 0; i < numEscritores; ++i) 
				new Escritor(mon, base, i+numLectores).start();
		} catch (Exception e) {System.out.println("Exception: " + e);}
	}
	
}






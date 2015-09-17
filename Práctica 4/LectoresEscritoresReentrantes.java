package parte2;

public class LectoresEscritoresReentrantes {

	static int numLectores = 4; 
	static int numEscritores = 2; 
	

	public static void main(String[] argv) {
 
		BaseDatos base = new BaseDatos();
		ReentranteLecturaAEscritura mon = new  ReentranteLecturaAEscritura();

		try {
			for (int i = 0; i < numLectores+numEscritores; ++i) {
				new LectorEscritor(mon, base ,i).start();
				
			}
		} catch (Exception e) {System.out.println("Exception: " + e);}
	}
	
}






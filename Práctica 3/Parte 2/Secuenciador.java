package parte2;

public class Secuenciador {

	private int seq;
	
	public Secuenciador(){
		seq=0;
	}
	
	public int ticket(){
		return ++seq;
	}
}

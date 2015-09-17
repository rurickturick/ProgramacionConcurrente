package Ejercicio1;


public class TareaDeCalculo implements Runnable {
	private int maxCont;
	private int cuenta;
	
	TareaDeCalculo(int cont){
		maxCont=cont;
		cuenta=0;
	}

	public void run() {
		while(cuenta<=maxCont){			
			 cuenta++;
	     }		
	}	
}
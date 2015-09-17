import java.util.Scanner;


public class ServiciosUnisex {
	
	static final Semaphore libre= new Semaphore(4);
	
	public static void main(String[] args){
		
		System.out.println("Por favor, escriba 'starve' o 'nostarve'.");
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		
		if(line.equalsIgnoreCase("starve")){		
			for(int i=0; i<4; i++){
				new Persona(i,true,libre).start();
			}
			for(int i=4; i<8; i++){
				new Persona(i,false,libre).start();
			}
		}
		else if(line.equalsIgnoreCase("nostarve")){
				for(int i=0; i<5; i++){
					new PersonaNoStarve(i,true,libre).start();
				}
				for(int i=5; i<10; i++){
					new PersonaNoStarve(i,false,libre).start();
				}				
				
			}
		else System.out.println("Comando incorrecto.");
	}
	
}

package Filosofos;
// Modificaci�n pendiente: en Java 1.7, estas dos clases tienen que estar
// en ficheros distintos.

import java.util.*;

class Filosofo extends Thread {

   // Generador de n�meros aleatorios
   static Random rnd = new Random();

   private int id = 0;
   private MonitorFilosofos monitor = null;

   public Filosofo(int id, MonitorFilosofos_equi monitor) {
      this.id = id;
      this.monitor = monitor;
      System.out.println("Fil�sofo " + this.id + " est� vivo");
   }
   
   public Filosofo(int id, MonitorFilosofos_efi monitor) {
	      this.id = id;
	      this.monitor = monitor;
	      System.out.println("Fil�sofo " + this.id + " est� vivo");
	   }

   private void think() {
      System.out.println("Fil�sofo " + this.id + " est� pensando");
	try {sleep(rnd.nextInt(5000));}
	catch (InterruptedException e) {System.err.println("Interrupted while sleeping");}
   }

   private void eat() {
      System.out.println("Fil�sofo " + this.id + " est� comiendo");
	try {sleep(rnd.nextInt(2000));}
	catch (InterruptedException e) {System.err.println("Interrupted while sleeping");}
   }
   
/*   private void starve() {
	   System.out.println("Fil�sofo " + this.id + " est� fam�lico");
		try {sleep(rnd.nextInt(2000));}
		catch (InterruptedException e) {System.err.println("Interrupted while sleeping");}
   }*/

   public void run() {
      while (true) {
         think();
         System.out.println("Fil�sofo " + this.id + " quiere comer");
         monitor.takeForks(id);
         eat();
         monitor.putForks(id);
      }
   }

}

class ComidaFilosofos {
	private static MonitorFilosofos monitor;
	
   public static void main(String[] args) {
   
   // n�mero de fil�sofos por defecto
   int numFilosofos = 5;

   // parsing de los argumentos de la l�nea de comandos
   if (args.length > 2)
	System.out.println("Uso: ComidaFilosofos <numero_de_filosofos>");
   else if (args.length == 1)
	try{
	   numFilosofos = Integer.parseInt(args[0]);
	} catch(NumberFormatException e){ // El par�metro no es un entero
	   System.out.println("Uso: ComidaFilosofos <numero_de_filosofos>");
	   System.exit(0);
	}
   else if(args.length == 2)
	   try{
	   numFilosofos = Integer.parseInt(args[0]);
	   if(args[1].equals("equ")){
		   // crear el objeto MonitorPhils
		   monitor = new MonitorFilosofos_equi(numFilosofos);
		   
		   // crear los fil�sofos y arrancar sus hilos
		   for (int i = 0; i < numFilosofos; i++)
		      new Filosofo(i, (MonitorFilosofos_equi) monitor).start();
		   System.out.println("Todos los hilos han sido arrancados");
		}	   
		
	   else if(args[1].equals("efi")){
		// crear el objeto MonitorPhils
		   monitor = new MonitorFilosofos_efi(numFilosofos);
		   
		   // crear los fil�sofos y arrancar sus hilos
		   for (int i = 0; i < numFilosofos; i++)
		      new Filosofo(i, (MonitorFilosofos_efi) monitor).start();
		   System.out.println("Todos los hilos han sido arrancados");
		}	   
	   }
		   
	   catch(NumberFormatException e){ // El par�metro no es un entero
	   System.out.println("Uso: ComidaFilosofos <numero_de_filosofos>");
	   System.exit(0);
   

   }
   }
}

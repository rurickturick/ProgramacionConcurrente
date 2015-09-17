package Ejercicio1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EjemploExecutorService {

  // El main:
	public static void main(String[] args) {
	System.out.println("Numero de procesadores disponibles: " + Runtime.getRuntime().availableProcessors());

    final int numTareas = 1000;
    final int numMaxHilos = 50;

    System.out.println("Numero de hilos \t tiempo en ns");

    // COMENZAR BUCLE. Para un número de hilos (numHilos) entre 1 y numMaxHilos:
    for(int numHilos=1;numHilos<=numMaxHilos;numHilos++){
      final long startTime = System.nanoTime();

      // Utilice un método factoría para crear un ThreadPool (implementación
      // de la interfaz ExecutorService) con el número de hilos requerido.
      ExecutorService pool = Executors.newCachedThreadPool();
      // COMENZAR BUCLE. Para un número de tareas entre 1 y numTareas:
      for(int j=1;j<=numTareas;j++){
         // Crear un nuevo objeto de la clase TareaDeCalculo (con p.e. maxCont == 1000000)
    	 TareaDeCalculo T= new TareaDeCalculo(1000000);
         // Presente esta tarea al ExecutorService para su ejecución
    	  pool.execute(T);
      // TERMINAR BUCLE
      }
      // Intente cerrar el ExecutorService de manera ordenada, es decir, dejando las
      // tareas activas terminar (¡como cuando se grita &quot;<em>last orders</em>&quot;
      // en un pub inglés!).
          pool.shutdown();
          
      // Espere que se termine el ExecutorService (o bien que pasen unos segundos)
        try{ pool.awaitTermination(2,TimeUnit.SECONDS );}
        catch(InterruptedException e){}
      // Aunque no es necesario en este ejemplo ya que no habrá tareas que tarden demasiado
      // en terminarse, ahora intente cerrar el ExecutorService de manera brusca,
      // es decir, interrumpiendo tareas activas (¡como cuando se grita &quot;<em>time</em>&quot;
      // en un pub inglés!).
        pool.shutdownNow();
      System.out.println("\t" + numHilos + "\t " + (System.nanoTime() - startTime));
    
    // TERMINAR BUCLE.
     }
	}
 }
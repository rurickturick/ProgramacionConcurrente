package Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

 
public class EjemploCompletionService {
	private static TareaLarga[] tasks;
	private static final int taskNumber = 10;
    // Defina un m�todo est�tico sin argumentos llamado crearListaTareas
    // que cree una lista de, por ejemplo, diez TareasLargas.
	private static void crearListaTareas(){
		tasks = new TareaLarga[taskNumber];
		for(int i=0;i<10;i++){
			tasks[i] = new TareaLarga();
		}
	}


    // El main:
	public static void main(String[] args) {
      // Utilice un m�todo factor�a para crear un ThreadPool (implementaci�n
      // de la interfaz ExecutorService) con el numero de hilos requerido:
      // por ejemplo, diez. 
		ExecutorService pool = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
      // Cree un nuevo CompletionService para tareas que devuelvan un String,
      // pas�ndole el ExecutorService creado en la instrucci�n anterior
		CompletionService com = new ExecutorCompletionService(pool);
      // Cree una lista de TareaLarga con el m�todo crearListaTareas
		crearListaTareas();
      // COMENZAR BUCLE (DE TIPO FOR-EACH). Para cada tarea de la lista
          
		for(int i = 0; i < taskNumber ; i++){
			// Presente esta tarea al CompletionService para su ejecuci�n
			com.submit(tasks[i]);
		}
      // TERMINAR BUCLE.
		

      // COMENZAR BUCLE. Para un n�mero de veces = el tama�o de la lista de tareas
		for(int i = 0; i < taskNumber ; i++){
			Future f=null;
          // Pida una tarea completada al CompletionService
          //   (espere si no ha terminado ninguna tarea todav�a)
			try {
				 f=com.take();				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
          // Imprima el resultado de la tarea en la salida est�ndar.
			try{System.out.println("La tarea "+i+": "+f.get());}
			catch(ExecutionException e){e.printStackTrace();}
			catch(InterruptedException e){e.printStackTrace();}
		}
      // TERMINAR BUCLE.

      // Cierre el ExecutionService
		pool.shutdown();
	}
}
package Ejercicio2;
import java.util.Random;
import java.util.concurrent.Callable;


public class TareaLarga implements Callable {

	public String call() throws Exception {
			Thread.sleep(Math.abs(new Random().nextLong() % 5000));
			return Thread.currentThread().getName();
	}

}

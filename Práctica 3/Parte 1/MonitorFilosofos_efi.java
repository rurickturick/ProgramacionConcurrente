package Filosofos;
class MonitorFilosofos_efi extends MonitorFilosofos{

   private int numFils = 0;
   private int[] estado = null;
   private /*static*/ Object[] condicion = null;
   private static final int
      THINKING = 0, HUNGRY = 1, EATING = 2, STARVING = 3;

   public MonitorFilosofos_efi(int numFils) {
	  super(numFils);
      this.numFils = numFils;
      estado = new int[numFils];
      condicion = new Object[numFils];
      for (int i = 0; i < numFils; i++) estado[i] = THINKING;
      for (int i = 0; i < numFils; i++) condicion[i] = new Object();
   }

   private final int izquierda(int i) {
	return (numFils + i - 1) % numFils;
   }

   private final int derecha(int i) {
	return (i + 1) % numFils;
   }

   private void prueba(int k) {    
	if ( estado[izquierda(k)] != EATING
           && (estado[k] == HUNGRY || estado[k] == STARVING)
           && estado[izquierda(k)] != STARVING
           && estado[derecha(k)] != STARVING
           && estado[derecha(k)] != EATING )
         estado[k] = EATING;
	if ( ((estado[izquierda(k)] == THINKING
			&& estado[k] == HUNGRY
			&& estado[derecha(k)] == EATING)
			
			||(estado[derecha(k)] == THINKING
			&& estado[k] == HUNGRY
			&& estado[izquierda(k)] == EATING))
			
			&& estado[izquierda(k)] != STARVING
			&& estado[derecha(k)] != STARVING)
		estado[k] = STARVING ;
	System.out.println("Filosofo " + k + " famélico");
   }

   public void takeForks(int i) {
      estado[i] = HUNGRY;
      prueba(i);
      
      synchronized(condicion[i]){ // sincronizacion con el filosofo
	      while (estado[i] != EATING)
	         try {condicion[i].wait();} catch (InterruptedException e) {}
	      }
   }

   public void putForks(int i) {
      estado[i] = THINKING;
      prueba(izquierda(i));
      prueba(derecha(i));
      synchronized(condicion[izquierda(i)]){ // sincronizacion con el de la izq
    	synchronized(condicion[derecha(i)]){ // sincroniacion con el de la dcha  
	      condicion[izquierda(i)].notify();
	      condicion[derecha(i)].notify();
	      //notifyAll();
        }
      }	
   
}
}

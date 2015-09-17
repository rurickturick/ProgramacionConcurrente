package Filosofos;
class MonitorFilosofos_equi extends MonitorFilosofos{

   private int numFils = 0;
   private int[] estado = null;
   private static final int
      THINKING = 0, HUNGRY = 1, EATING = 2, STARVING = 3;

   public MonitorFilosofos_equi(int numFils) {
	  super(numFils);
      this.numFils = numFils;
      estado = new int[numFils];
      for (int i = 0; i < numFils; i++) estado[i] = THINKING;
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

   public synchronized void takeForks(int i) {
      estado[i] = HUNGRY;
      prueba(i);
      while (estado[i] != EATING)
         try {wait();} catch (InterruptedException e) {}
   }

   public synchronized void putForks(int i) {
      estado[i] = THINKING;
      prueba(izquierda(i));
      prueba(derecha(i));
      notifyAll();
   }
}

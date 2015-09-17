
public class Semaphore {
  private int value = 0;
  private int waitCount = 0;
  private int notifyCount = 0;

  public Semaphore() {
  }

  public Semaphore(int initial) {
    if (initial >= 0) {
      value = initial;
    }
    else {
      throw new IllegalArgumentException("initial < 0");
    }
  }

  public synchronized void P()
        throws InterruptedException {

    if (value <= waitCount) {
      waitCount++;
      try {
        do { 
          wait(); 
        } while (notifyCount == 0);
      } catch(InterruptedException e) {
        notify();
        throw e;
      } finally { 
        waitCount--; 
      }
      notifyCount--;
    } 
    else {
      if (notifyCount > waitCount) {
        notifyCount--;
      }
    }
    value--;
  } // End of method P

  public synchronized void V() {
    value++;
    if (waitCount > notifyCount) {
      notifyCount++;
      notify();
    }
  } // End of method V
} // End of class Semaphore

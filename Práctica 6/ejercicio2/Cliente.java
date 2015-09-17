package ejercicio2;
import java.rmi.*;

public interface Cliente extends Remote {
	
	
	void notificacion(String msg,int contador) throws RemoteException;
}
 
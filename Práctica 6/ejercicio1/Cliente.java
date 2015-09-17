package ejercicio1;
import java.rmi.*;

public interface Cliente extends Remote {
	
	
	void notificacion(String nombre, String msg) throws RemoteException;
}

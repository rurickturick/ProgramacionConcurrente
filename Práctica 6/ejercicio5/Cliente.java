package ejercicio5;
import java.rmi.*;

public interface Cliente extends Remote {
	
	
	void notificacion(String apodo,String msg,int contador) throws RemoteException;
}
 
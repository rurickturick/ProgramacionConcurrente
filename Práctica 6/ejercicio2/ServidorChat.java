package ejercicio2;
import java.rmi.*;
public interface ServidorChat extends Remote{
	
	void inscribirse(Cliente c) throws RemoteException;
	void difundir (String msg) throws RemoteException;
	
	
	
	

}

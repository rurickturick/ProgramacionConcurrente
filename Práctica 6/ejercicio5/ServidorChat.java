package ejercicio5;
import java.rmi.*;
public interface ServidorChat extends Remote{
	
	void inscribirse(Cliente c) throws RemoteException;
	void difundir (String apodo,String msg) throws RemoteException;
	
	
	
	
	

}

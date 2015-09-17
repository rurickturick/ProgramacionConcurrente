package ejercicio2;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServidorChatImpl extends UnicastRemoteObject implements ServidorChat {
	
	/**
	 * 
	 */
	private int contador;
	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> listaClientes;
	
	public ServidorChatImpl() throws RemoteException{
		this.listaClientes=new ArrayList<Cliente>();
		this.contador=0;
	}

	@Override
	public void inscribirse(Cliente c) throws RemoteException {
		this.listaClientes.add(c);
		
	}

	@Override
	public void difundir(String msg) throws RemoteException {
		this.contador++;
		for(int i=0;i<listaClientes.size();i++){
			Cliente client=listaClientes.get(i);
			client.notificacion(msg,contador);
			
			
		}
	}
		
		public static void main (String[] args)	{
		try	{
		/*if (System.getSecurityManager() == null){
	         System.setSecurityManager(new SecurityManager());	
		}*/
		Naming.rebind("ServidorChat", new ServidorChatImpl());
		System.err.println("Servidor Arrancado");
		}
		catch(Exception e){
		System.err.println("Problema al arrancar el servidor de chat") ;
		e.printStackTrace();
		}
		}
		
	}
	
	



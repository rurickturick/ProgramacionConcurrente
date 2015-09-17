package ejercicio4;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


public class ServidorChatImpl extends UnicastRemoteObject implements ServidorChat, Runnable {
	
	/**
	 * 
	 */
	private int contador;
	private static final long serialVersionUID = 1L;
	private CopyOnWriteArrayList<Cliente> listaClientes;
	private String apodo;
	private String msg;
	private Cliente currentClient;
	
	
	
	public ServidorChatImpl() throws RemoteException{
		this.listaClientes=new CopyOnWriteArrayList<Cliente>();
		this.contador= 0;
		
	}

	public ServidorChatImpl(CopyOnWriteArrayList<Cliente> listaClientes,int contador,String apodo,String msg,Cliente currentClient) throws RemoteException{
		this.listaClientes=listaClientes;
		this.contador=contador;
		this.apodo=apodo;
		this.msg=msg;
		this.currentClient=currentClient;
	}
	@Override
	public synchronized void inscribirse(Cliente c) throws RemoteException {
		this.listaClientes.add(c);
		
	}

	@Override
	//No se si valdria poner aqui solo el synchronized, o tambien en inscribirse y notificacion, antes con el synchronized en todo funcionaba tambien
	public void difundir(String apodo,String msg) throws RemoteException {
		
		this.contador++;
		Iterator<Cliente> it=listaClientes.iterator();
		while(it.hasNext()){
			Cliente client=it.next();
			//Thread t=new Thread(this);
			
			this.apodo=apodo;
			this.msg=msg;
			Thread t=new Thread(new ServidorChatImpl(this.listaClientes,this.contador,apodo,msg,client));
		    t.start();
			
		//client.notificacion(apodo,msg,contador);
			
			
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

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				this.currentClient.notificacion(apodo,msg,contador);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.err.println("Error en lo que he hecho");
			//	e.printStackTrace();
			}
		}

		
		
	}
	
	

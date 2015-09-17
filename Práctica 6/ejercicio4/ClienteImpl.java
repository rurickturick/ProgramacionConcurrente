package ejercicio4;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ClienteImpl extends UnicastRemoteObject implements Cliente,Runnable {

	private ServidorChat miServidorChat;
	private static String apodo;
	private static final long serialVersionUID = 8772263557682005983L;
	private AtomicInteger cont;
//	private ConcurrentLinkedQueue<String> listoImprimir;
//	private ConcurrentLinkedQueue<String> fueraDeOrden;
	
	ClienteImpl(ServidorChat miServidorChat) throws RemoteException {
		this.miServidorChat=miServidorChat;
		
		//ojo mirar esto puede cambiar
		this.miServidorChat.inscribirse(this);
    }
	
    public void notificacion(String apodo,String m,int contador) throws RemoteException {
    //	Thread t=new Thread();
    //	t.start();
        System.out.println("\n"+apodo + "> " + m + "Mensaje numero: "+contador);
    }
    
    
    
	public void run(){
		Random r= new Random();
		String nombre= apodo;
    	for(int i=0;i<100;i++){
    		
    		try {
				Thread.sleep(r.nextInt(2000));
				
				this.miServidorChat.difundir(nombre,"Mensaje: "+i+"//");
			} catch (InterruptedException e) {
				System.err.println("Error de interrupcion");
    		//	e.printStackTrace();
			}
    		catch(Exception e){
    			System.err.println("Error al difundir");
    		//	e.printStackTrace();
    		}
    		
    	}
    	
    }
    public static void main(String[] args){
    	String url="rmi://localhost/ServidorChat";
    	
    /*	if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
   */
    	try{
    		
    		ServidorChat servChat= (ServidorChat) Naming.lookup(url);
    		ClienteImpl c =  new ClienteImpl(servChat);
    		apodo=args[0];
    		new Thread(c).start();
    		
    	}
    	catch (Exception e){
    		System.err.println("Error cliente");
    	//	e.printStackTrace();
    	}
    	
    	
    	
    }
	
	

}

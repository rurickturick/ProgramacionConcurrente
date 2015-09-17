package ejercicio1;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClienteImpl extends UnicastRemoteObject implements Cliente {
	private ServidorChat miServidorChat;
	private static final long serialVersionUID = 8772263557682005983L;
	
	ClienteImpl(ServidorChat miServidorChat) throws RemoteException {
		this.miServidorChat=miServidorChat;
		
		//ojo mirar esto puede cambiar
		this.miServidorChat.inscribirse(this);
    }
	
    public void notificacion(String apodo,String m) throws RemoteException {
        System.out.println("\n" + apodo + "> " + m);
    }
    
    
    @SuppressWarnings("resource")
	public void procesar(String apodo){
    	Scanner entrada=new Scanner(System.in);
    	String mensaje;
    	
    	System.out.println("Cliente: "+apodo+" arrancado");
    	while (true){
    		try{
    			mensaje=entrada.nextLine();
    			this.miServidorChat.difundir(apodo,mensaje); 
    			
    		}
    		catch(Exception e){
    			System.err.println("Error al difundir");
    			e.printStackTrace();
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
    		c.procesar(args[0]);
    		
    	}
    	catch (Exception e){
    		System.err.println("Error cliente");
    		e.printStackTrace();
    	}
    	
    	
    	
    }
	
	

}

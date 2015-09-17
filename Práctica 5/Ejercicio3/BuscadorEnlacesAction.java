package ejercicio3;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;


import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import ejercicio3.ProcesadorEnlaces;

public class BuscadorEnlacesAction extends RecursiveAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
    private ProcesadorEnlaces procesadorEnlaces;
    /**
     * Usado para las estadisticas
     */
    private static final long t0 = System.nanoTime();

    public BuscadorEnlacesAction(String url, ProcesadorEnlaces procesador) {
        this.url = url;
        this.procesadorEnlaces = procesador;
    }

  /*  public void run() {
        rastrearPagina(url);
    }

    private void rastrearPagina(String url) {
        // si no lo hemos visitado ya
        if (!procesadorEnlaces.visitado(url)) {
            try {
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
                ArrayList<String> urls = new ArrayList<String>();

                 for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.getLink().isEmpty()
                            && !procesadorEnlaces.visitado(extracted.getLink())) {

                        urls.add(extracted.getLink());
                    }

                }
                // hemos visitado este URL
                procesadorEnlaces.anadirVisitado(url);

                if (procesadorEnlaces.cantidad() == 1500) {
                    System.out.println("Time to visit 1500 distinct links = " + (System.nanoTime() - t0));                   
                }

                for (String l : urls) {
                    procesadorEnlaces.encolarEnlace(l);
                }

             } catch (Exception e) {
                //ignorar todos los errores de momento
            }
        }
    }*/

	@Override
	protected void compute() {
		 // si no lo hemos visitado ya
        if (!procesadorEnlaces.visitado(url)) {
            try {
                URL uriLink = new URL(url);
                Parser parser = new Parser(uriLink.openConnection());
                NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
                ArrayList<RecursiveAction> urls = new ArrayList<RecursiveAction>();

                 for (int i = 0; i < list.size(); i++) {
                    LinkTag extracted = (LinkTag) list.elementAt(i);

                    if (!extracted.getLink().isEmpty()
                            && !procesadorEnlaces.visitado(extracted.getLink())) {

                        urls.add(extracted.getLink());
                        
                    }

                }
                // hemos visitado este URL
                procesadorEnlaces.anadirVisitado(url);

                if (procesadorEnlaces.cantidad() == 1500) {
                    System.out.println("Time to visit 1500 distinct links = " + (System.nanoTime() - t0));                   
                }

                for (RecursiveAction l : urls) {
                   // procesadorEnlaces.encolarEnlace(l);
                    invokeAll(l);
                }

             } catch (Exception e) {
                //ignorar todos los errores de momento
            }
        }
		
	}
}

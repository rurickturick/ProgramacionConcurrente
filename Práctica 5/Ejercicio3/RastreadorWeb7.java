package ejercicio3;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import ejercicio3.BuscadorEnlacesAction;
import java.util.HashSet;

public class RastreadorWeb7 implements ProcesadorEnlaces {

    private final Collection<String> enlacesVisitados = Collections.synchronizedSet(new HashSet<String>());
    private String url;
    private ForkJoinPool execService;

    public RastreadorWeb7(String inicioURL, int maxHilos) {
        this.url = inicioURL;
        execService = new ForkJoinPool();;
        
    }

    @Override
    public void encolarEnlace(String link) throws Exception {
		execService.execute(new BuscadorEnlacesAction(link, this));
    }

    @Override
    public int cantidad() {
        return enlacesVisitados.size();
    }

    @Override
    public void anadirVisitado(String s) {
        enlacesVisitados.add(s);
    }

    @Override
    public boolean visitado(String s) {
        return enlacesVisitados.contains(s);
    }

    private void empezarRastreo() throws Exception {
		execService.execute(new BuscadorEnlacesAction(this.url, this));
    }

    /**
     * @param args los argumentos de la línea de comandos (debería pasar la URL)
     */
    public static void main(String[] args) throws Exception {
        new RastreadorWeb7("http://www.fdi.ucm.es/", 16).empezarRastreo();
    }
}

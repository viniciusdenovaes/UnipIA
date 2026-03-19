package estado;

import java.util.Collection;
import java.util.List;

public interface Estado {
	
	public Estado getEstadoInicial();

	boolean isObjetivo();
	
	boolean isValido();
	
	Collection<Estado> geraFilhos();
	
	Estado deepCopy();
	
	List<String> getSolution();
	void addStep(String step);
	

}

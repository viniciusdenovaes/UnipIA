package estado;

import java.util.Collection;

public interface Estado {
	
	public Estado getEstadoInicial();

	boolean isObjetivo();
	
	boolean isValido();
	
	Collection<Estado> geraFilhos();
	
	Estado deepCopy();
	

}

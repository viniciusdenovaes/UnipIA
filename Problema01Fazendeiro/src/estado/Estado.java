package estado;

import java.util.Collection;

interface Estado {
	
	public Estado getEstadoInicial();

	boolean isObjetivo();
	
	boolean isValido();
	
	Collection<Estado> geraFilhos();
	
	Estado deepCopy();
	

}

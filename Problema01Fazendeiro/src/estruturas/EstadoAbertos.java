package estruturas;

import estado.Estado;

public interface EstadoAbertos {
	
	public void push(Estado estado);
	public Estado pop();
	public int size();

}

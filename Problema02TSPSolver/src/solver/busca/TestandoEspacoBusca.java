package solver.busca;

import dao.DAO;
import estruturas_dados.listas.Fila;
import estruturas_dados.listas.Pilha;
import solver.estado.Estado;
import solver.instance.Instance;

public class TestandoEspacoBusca {
	
	public static void main(String[] args) {
		DAO.readInstance("files/instances/test4.tsp/test4.tsp");
		System.out.println(Instance.inst);
		EspacoDeBusca eb = new EspacoDeBusca(new Pilha());
		Estado sol = eb.solve();
		
		System.out.println("------------------------------");
		System.out.println("Best Solution: " + sol);
	}


}

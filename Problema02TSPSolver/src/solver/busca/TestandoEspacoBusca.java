package solver.busca;

import dao.DAO;
import solver.estado.Estado;
import solver.instance.Instance;

public class TestandoEspacoBusca {
	
	public static void main(String[] args) {
		DAO.readInstance("files/instances/test8.tsp/test8.tsp");
		System.out.println(Instance.inst);
		EspacoDeBusca eb = new EspacoDeBusca();
		Estado sol = eb.solve();
		
		System.out.println("Solution: " + sol);
	}


}

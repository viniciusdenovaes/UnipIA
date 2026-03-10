package solver.busca;

import java.util.PriorityQueue;

import solver.estado.Estado;

public class EspacoDeBusca {
	
	Estado estadoInicial = new Estado();
	
	PriorityQueue<Estado> estadosAbertos = new PriorityQueue<>(new Estado.EstadoWeightComparator());
	
	public EspacoDeBusca() {
		estadosAbertos.add(estadoInicial);
	}
	
	public Estado solve() {
		while(!estadosAbertos.isEmpty()) {
			Estado estadoAtual = estadosAbertos.poll();
			System.out.println("Testando " + estadoAtual);
			if(estadoAtual.isSolution()) return estadoAtual;
			Iterable<Estado> filhos = estadoAtual.getChildren();
			for(Estado e : filhos) {
				System.out.println("Gerou " + e);
				estadosAbertos.add(e);
			}
		}
		return null;
	}

}

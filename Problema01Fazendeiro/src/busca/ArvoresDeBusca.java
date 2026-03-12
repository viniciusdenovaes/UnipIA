package busca;

import java.util.Collection;

import estado.Estado;
import estado.EstadoVersaoConjunto;
import estruturas.EstadoAbertos;
import estruturas.Fila;
import estruturas.Pilha;

public class ArvoresDeBusca {
	
	public EstadoAbertos ea;
	
	public ArvoresDeBusca(EstadoAbertos aEstadosAbertos) {
		this.ea = aEstadosAbertos;
	}
	
	public void solveBusca(Estado estadoInicial) {
		ea.push(estadoInicial);
		while(ea.size()>0) {
			Estado e = ea.pop();
			
			if(e.isObjetivo()) {
				System.out.println("Estado objetivo!" + e);
				return;
			}else {
				System.out.println("Fechando estado " + e);
				System.out.println("Tamanho dos estados abertos" + ea.size());
			}
			
			Collection<Estado> filhos = e.geraFilhos();
			for(Estado filho: filhos) {
				ea.push(filho);
			}
		}
	}
	
	public static void main(String[] args) {
		Estado ei = new EstadoVersaoConjunto();
		Estado estadoInicial = ei.getEstadoInicial();
		ArvoresDeBusca a = new ArvoresDeBusca(new Fila());
		a.solveBusca(estadoInicial);
	}

}

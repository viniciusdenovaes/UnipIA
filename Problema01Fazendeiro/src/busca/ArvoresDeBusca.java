package busca;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import estado.Estado;
import estado.EstadoVersaoConjunto;
import estruturas.EstadoAbertos;
import estruturas.Fila;
import estruturas.Pilha;

public class ArvoresDeBusca {
	
	public EstadoAbertos ea;
	public Set<Estado> estadosFechados = new HashSet<>();
	public boolean usingEstadosFechados = false;
	public int qtdIteracoes = 0;
	
	public ArvoresDeBusca(EstadoAbertos aEstadosAbertos) {
		this.ea = aEstadosAbertos;
	}
	
	public void solveBusca(Estado estadoInicial) {
		ea.push(estadoInicial);
		while(ea.size()>0) {
			Estado e = ea.pop();
			estadosFechados.add(e);
			qtdIteracoes++;
			
			if(e.isObjetivo()) {
				System.out.println("Estado objetivo!" + e);
				return;
			}else {
				System.out.println("Fechando estado " + e);
				System.out.println("Quantidade de iteracoes: " + qtdIteracoes);
			}
			
			Collection<Estado> filhos = e.geraFilhos();
			for(Estado filho: filhos) {
				if(usingEstadosFechados)
					if (estadosFechados.contains(filho)) 
						continue;
				ea.push(filho);
			}
		}
	}
	
	public static void main(String[] args) {
		Estado ei = new EstadoVersaoConjunto();
		Estado estadoInicial = ei.getEstadoInicial();
		ArvoresDeBusca a = new ArvoresDeBusca(new Fila());
		a.usingEstadosFechados = false;
		a.solveBusca(estadoInicial);
	}

}

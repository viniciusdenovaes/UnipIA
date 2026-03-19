package estado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class EstadoAltAlgebric implements Estado{
	
	// acoes sao matrizes de operacoes que mudam elementos de lugar
	// um valor de -1 na posicao i muda o elemento i de lugar
	List<Integer[]> acoes = new ArrayList<>();
	// 1 quer dizer margem original
	// -1 quer dizer margem destino
	// posicoes: enum Elemento.java
	//     0: Fazendeiro
	//     1: Lobo
	//     2: Cabra
	//     3: Repolho
	int[] estadoMatrix = {1, 1, 1, 1}; 
	List<String> solutionStep = new ArrayList<String>();
	public EstadoAltAlgebric() {
	}

	public Estado getEstadoInicial() {
		return new EstadoAltAlgebric();
	}
	
	@Override
	public boolean isObjetivo() {
		for(int p : this.estadoMatrix)
			if(p==-1)
				return false;
		return true;
	}

	@Override
	public boolean isValido() {
		if(		(
				estadoMatrix[Elemento.FAZENDEIRO.ordinal()]!=
				estadoMatrix[Elemento.CABRA.ordinal()]
				)
				&&
				(
				estadoMatrix[Elemento.CABRA.ordinal()]==
				estadoMatrix[Elemento.LOBO.ordinal()])
				)
			return false;
		if(		(
				estadoMatrix[Elemento.FAZENDEIRO.ordinal()]!=
				estadoMatrix[Elemento.REPOLHO.ordinal()]
				)
				&&
				(
				estadoMatrix[Elemento.REPOLHO.ordinal()]==
				estadoMatrix[Elemento.CABRA.ordinal()])
				)
			return false;
		return true;
	}
	
	// Vai aplicar a acao no estado
	// ao multiplicar um elemento por 1 a posicao deste elemento se mantem
	// ao multiplica por -1 a posicao muda
	private void executaAcaoInplace(int[] acao) {
		for(int i = 0; i<Elemento.values().length; i++) {
			estadoMatrix[i]*=acao[i];
		}
	}
	
	private List<int[]> geraAcoesPossiveis(){
		List<int[]> acoesPossiveis = new ArrayList<>();
		for(int i = 0; i<Elemento.values().length; i++) {
			// cria acao apenas para os elementos que estao na mesma margem que o fazendeiro
			// entra quando o elemento i esta no mesmo lado que o fazendeiro
			if(estadoMatrix[Elemento.FAZENDEIRO.ordinal()] == estadoMatrix[i]) { 
				// cria uma acao nova
				int[] acao = {1, 1, 1, 1}; 
				// o fazendeiro sempre muda de lugar
				acao[Elemento.FAZENDEIRO.ordinal()] = -1; 
				// o elemento que esta na mesma margem que o fazendeiro muda de lugar
				acao[i] = -1; 
				acoesPossiveis.add(acao);
			}
		}
		return acoesPossiveis;
	}

	@Override
	public Collection<Estado> geraFilhos() {
		List<Estado> filhos = new ArrayList<>();
		
		for(int[] acao: geraAcoesPossiveis()) {
			EstadoAltAlgebric filho = deepCopy();
			filho.executaAcaoInplace(acao);
			if(filho.isValido()) {
				filhos.add(filho);
			}
		}
		return filhos;
	}

	@Override
	public EstadoAltAlgebric deepCopy() {
		EstadoAltAlgebric eCopy = new EstadoAltAlgebric();
		
		eCopy.acoes = new ArrayList<>(acoes);
		eCopy.estadoMatrix = estadoMatrix.clone();
		eCopy.solutionStep = new ArrayList<>(solutionStep); 
		
		return eCopy;
	}
	@Override
	public List<String> getSolution() {
		return solutionStep;
	}

	@Override
	public void addStep(String step) {
		solutionStep.add(step);
		
	}
	
	@Override
	public String toString() {
		Set<Elemento> margemOriginal = new TreeSet<>();
		Set<Elemento> margemDestino = new TreeSet<>();
		for(Elemento e: Elemento.values()) {
			if(estadoMatrix[e.ordinal()]==1)
				margemOriginal.add(e);
			else
				margemDestino.add(e);
		}
		
		
		String res = "Estado Versao Aternativa Matrizes: \n";
		res += "\t Margem Original " + margemOriginal + "\n";
		res += "\t Margem Destino" + margemDestino + "\n";
		res += "\t Solution step:\n";
		for(String s: solutionStep)
			res += "\t\t" + s + "\n";
		return res;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(estadoMatrix);
		result = prime * result + Objects.hash(acoes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoAltAlgebric other = (EstadoAltAlgebric) obj;
		return Objects.equals(acoes, other.acoes) && Arrays.equals(estadoMatrix, other.estadoMatrix);
	}

	public static void main(String[] args) {
		Estado ei = new EstadoAltAlgebric().getEstadoInicial();
		System.out.println("Estado inicial" + ei);
		
		List<Estado> filhos = (List<Estado>)ei.geraFilhos();
		
		for(Estado e: filhos) {
			System.out.println("Filho gerados: \n" + e);
		}
		
		for(var e: filhos.get(0).geraFilhos()) {
			System.out.println("Filhos do filho: \n" + e);
		}
		
		
	}
	

}

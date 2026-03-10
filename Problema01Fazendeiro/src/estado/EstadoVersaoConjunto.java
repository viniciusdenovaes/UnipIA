package estado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class EstadoVersaoConjunto implements Estado{
	
	Set<Elemento> esquerdaMargem = new TreeSet<>();
	Set<Elemento> direitaMargem = new TreeSet<>();
	
	EstadoVersaoConjunto(){
	}
	
	EstadoVersaoConjunto(Set<Elemento> esquerda, Set<Elemento> direita){
		this();
		esquerdaMargem = esquerda;
		direitaMargem = direita;
	}
	
	@Override
	public Estado getEstadoInicial() {
		Set<Elemento> esquerda = new TreeSet<>() ;
		esquerda.add(Elemento.FAZENDEIRO);
		esquerda.add(Elemento.LOBO);
		esquerda.add(Elemento.CABRA);
		esquerda.add(Elemento.REPOLHO);
		Set<Elemento> direita = new TreeSet<>();
		return new EstadoVersaoConjunto(esquerda, direita);
	}
	
	@Override
	public boolean isObjetivo() {
		return (
				direitaMargem.contains(Elemento.FAZENDEIRO)
				&&  direitaMargem.contains(Elemento.LOBO)
				&&  direitaMargem.contains(Elemento.CABRA)
				&&  direitaMargem.contains(Elemento.REPOLHO)
			);
	}
	
	@Override
	public boolean isValido() {
		Set<Elemento> outraMargem = getMargemOutra();
		if(
				outraMargem.contains(Elemento.LOBO) &&
				outraMargem.contains(Elemento.CABRA))
			return false;
		if(
				outraMargem.contains(Elemento.CABRA) &&
				outraMargem.contains(Elemento.REPOLHO))
			return false;
		return true;
	}
	
	@Override
	public Collection<Estado> geraFilhos() {
		List<Estado> filhos = new ArrayList<>();
		
		Set<Elemento> margemFazendeiro = getMargemF();
		for(Elemento elemento: margemFazendeiro) {
			var filho = deepCopy();
			Set<Elemento> margemFilhoFazendeiro = filho.getMargemF();
			Set<Elemento> margemFilhoOutra = filho.getMargemOutra();
			
			margemFilhoFazendeiro.remove(elemento.FAZENDEIRO);
			margemFilhoFazendeiro.remove(elemento);
			
			margemFilhoOutra.add(elemento.FAZENDEIRO);
			margemFilhoOutra.add(elemento);
			
			System.out.println("Filho gerado " +  filho);
			System.out.println("Filho valido? " +  filho.isValido());
			
			if(filho.isValido()) filhos.add(filho);
			
		}
		
		return filhos;
		
	}
	
	private Set<Elemento> getMargemF(){
		if(esquerdaMargem.contains(Elemento.FAZENDEIRO))
			return esquerdaMargem;
		else
			return direitaMargem;
	}
	
	private Set<Elemento> getMargemOutra(){
		if(esquerdaMargem.contains(Elemento.FAZENDEIRO))
			return direitaMargem;
		else
			return esquerdaMargem;
	}
	
	@Override
	public EstadoVersaoConjunto deepCopy() {
		var esquerda = new TreeSet<>(esquerdaMargem);
		var direita = new TreeSet<>(direitaMargem);
		return new EstadoVersaoConjunto(esquerda, direita);
		
	}
	
	@Override
	public String toString() {
		String res = "Estado Versao Conjunto: \n";
		res += "\t Esquerda " + esquerdaMargem + "\n";
		res += "\t Direita " + direitaMargem + "\n";
		return res;
	}
	
	public static void main(String[] args) {
		Estado ei = new EstadoVersaoConjunto().getEstadoInicial();
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

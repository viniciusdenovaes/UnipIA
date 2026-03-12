package estado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class EstadoVersaoConjunto implements Estado{
	
	Set<Elemento> margemOriginal = new TreeSet<>();
	Set<Elemento> margemDestino = new TreeSet<>();
	
	public EstadoVersaoConjunto(){
	}
	
	EstadoVersaoConjunto(Set<Elemento> aMargemOriginal, Set<Elemento> aMargemDestino){
		this();
		margemOriginal = aMargemOriginal;
		margemDestino = aMargemDestino;
	}
	
	@Override
	public Estado getEstadoInicial() {
		Set<Elemento> margemOriginal = new TreeSet<>() ;
		margemOriginal.add(Elemento.FAZENDEIRO);
		margemOriginal.add(Elemento.LOBO);
		margemOriginal.add(Elemento.CABRA);
		margemOriginal.add(Elemento.REPOLHO);
		Set<Elemento> margemDestino = new TreeSet<>();
		return new EstadoVersaoConjunto(margemOriginal, margemDestino);
	}
	
	@Override
	public boolean isObjetivo() {
		return (
				margemDestino.contains(Elemento.FAZENDEIRO)
				&&  margemDestino.contains(Elemento.LOBO)
				&&  margemDestino.contains(Elemento.CABRA)
				&&  margemDestino.contains(Elemento.REPOLHO)
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
			
			//System.out.println("Filho gerado " +  filho);
			//System.out.println("Filho valido? " +  filho.isValido());
			
			if(filho.isValido()) filhos.add(filho);
			
		}
		
		return filhos;
		
	}
	
	private Set<Elemento> getMargemF(){
		if(margemOriginal.contains(Elemento.FAZENDEIRO))
			return margemOriginal;
		else
			return margemDestino;
	}
	
	private Set<Elemento> getMargemOutra(){
		if(margemOriginal.contains(Elemento.FAZENDEIRO))
			return margemDestino;
		else
			return margemOriginal;
	}
	
	@Override
	public EstadoVersaoConjunto deepCopy() {
		var margemOriginalCopy = new TreeSet<>(margemOriginal);
		var margemDestinoCopy = new TreeSet<>(margemDestino);
		return new EstadoVersaoConjunto(margemOriginalCopy, margemDestinoCopy);
		
	}
	
	@Override
	public String toString() {
		String res = "Estado Versao Conjunto: \n";
		res += "\t Margem Original " + margemOriginal + "\n";
		res += "\t Margem Destino" + margemDestino + "\n";
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


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ArvoreBinaria {

	No raiz = null;
	
	public ArvoreBinaria (Character valor){
		this.raiz = new No(valor);
	}
	
	public Character codificar(No raiz, Character valor) {
		if (raiz == null)
			return ' ';

		if (valor == ' ')
			return '/';
		if (valor == raiz.esquerdo.valor)
			return '.';
		if (valor == raiz.direito.valor)
			return '-';

		if (busca(raiz.esquerdo, valor)) {
			System.out.print('.');
			return codificar(raiz.esquerdo, valor);
		}
		if (busca(raiz.direito, valor)) {
			System.out.print('-');
			return codificar(raiz.direito, valor);
		}

		return null;
	}
	
	private boolean busca(No raiz, Character valor) {
		if (raiz == null)
			return false;
		if (raiz.valor == valor)
			return true;
		return busca(raiz.esquerdo, valor) || busca(raiz.direito, valor);
	}

	public Map<Character, String> inserirDecodificador(Scanner decodificador) {
		Map<Character, String> mapaMorse = new LinkedHashMap<>();
		while (decodificador.hasNextLine()) {
			String linha = decodificador.nextLine();
			Scanner linhaScanner = new Scanner(linha);
			linhaScanner.useDelimiter(" ");
			
			Character valor = linhaScanner.next().charAt(0);
			String valorMorse = linhaScanner.next();
			
			mapaMorse.put(valor, valorMorse);
		}
		return mapaMorse;
	}
	
	public void inserir(No raiz, Character valor, String valorMorse) {
		if (raiz == null) 
			raiz = new No(' ');
		if (raiz.esquerdo == null)
			raiz.esquerdo = new No(' ');
		if (raiz.direito == null)
			raiz.direito = new No(' ');
		
		if (busca(raiz, valor))
			return;
		
		if (valorMorse.charAt(0) == '.') {
			if (raiz.esquerdo == null && valorMorse.equals(".")) {
				raiz.esquerdo = new No(valor);
				return;
			} else if (raiz.esquerdo.valor.equals(' ') && valorMorse.equals(".")) {
				raiz.esquerdo.valor = valor;
				return;
			}
			inserir(raiz.esquerdo, valor, valorMorse.substring(1));
		}
		else if (valorMorse.charAt(0) == '-') {
			if (raiz.direito == null && valorMorse.equals("-")) {
				raiz.direito = new No(valor);
				return;
			} else if (raiz.direito.valor.equals(' ') && valorMorse.equals("-")) {
				raiz.direito.valor = valor;
				return;
			}
			inserir(raiz.direito, valor, valorMorse.substring(1));
		}
	}
	
}
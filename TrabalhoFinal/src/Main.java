import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);	
		ArvoreBinaria codMorse = new ArvoreBinaria(' ');
		inserirDecodificador(codMorse, "decodificador.txt");
		
		int opcao = 0;
		while (opcao != 2) {
			menu();
			opcao = scan.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("\nInsira a frase para converter em codigo morse: ");
				scan.nextLine();
				codificar(codMorse, scan.nextLine());
				break;
			case 2:
				System.out.println("\nPrograma finalizado.");
				break;
			default:
				System.out.println("\nOpcao invalida!");
				break;
			}
		}
	}
	
	public static void inserirDecodificador(ArvoreBinaria codMorse, String file) throws FileNotFoundException {
		Scanner decodificador = new Scanner(new File(file));
		
		Map<Character, String> mapaMorse = codMorse.inserirDecodificador(decodificador);
		for (Map.Entry<Character, String> entry : mapaMorse.entrySet()) {
			codMorse.inserir(codMorse.raiz, entry.getKey(), entry.getValue());
		}
	}
	
	public static void codificar(ArvoreBinaria codMorse, String frase) {
		System.out.print("Frase '" + frase + "' codificada em morse: " );
		frase = frase.toUpperCase();
		for (int i = 0; i < frase.length(); i++) {
			System.out.print(codMorse.codificar(codMorse.raiz, frase.charAt(i)));
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static void menu() {
		System.out.println("\n1 - Codificar frase.");
		System.out.println("2 - Finalizar o programa.");
	}
	
}

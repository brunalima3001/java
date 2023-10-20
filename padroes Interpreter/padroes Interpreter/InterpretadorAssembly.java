import java.util.Scanner;

// Classe que representa o objeto AssemblyInterpreter, ponto de entrada do programa
// Utiliza o padrão Template Method para ler o arquivo de operações e executá-las
public class InterpretadorAssembly {

    public static void main(String[] args) {
         // Cria um objeto Interpretador para armazenar os valores dos registradores
        Interpretador interpretador = new Interpretador();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite as instruções uma por linha (Digite 'exit' para sair):");

        while (true) {
            String linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println("Instrução: " + linha);
            interpretador.interpretar(linha);
        }

        scanner.close();
    }
}

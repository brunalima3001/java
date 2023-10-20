import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Classe que representa o Interpreter do padrão Interpreter
public class Interpretador {
    // Mapa que associa cada operador (string) à sua respectiva operação (objeto Operation)
    private Map<String, Operacao> operacoes;
    private Registradores registradores;

    // Construtor que inicializa o mapa com os operadores conhecidos e as operações correspondentes
    public Interpretador() {
        operacoes = new HashMap<>();
        operacoes.put("MOV", new Movimentacao());
        operacoes.put("ADD", new Adicao());
        operacoes.put("SUB", new Subtracao());
        registradores = new Registradores();
    }

    // Método que recebe uma linha de código Assembly e retorna a operação correspondente
    public void interpretar(String linha) {
        String[] tokens = linha.split(" ");

        try {
            if (tokens.length < 2) {
                throw new Exception("Linha de código inválida: " + linha);
            }

            String operador = tokens[0];
            String[] argumentos = Arrays.copyOfRange(tokens, 1, tokens.length);

            if (!operacoes.containsKey(operador)) {
                throw new Exception("Operador inválido: " + operador);
            }

            int valorAnteriorAX = registradores.AX;
            int valorAnteriorBX = registradores.BX;

            Operacao operacao = operacoes.get(operador);

            if (operador.equals("ADD") || operador.equals("SUB")) {
                int valor1, valor2;

                if (argumentos[0].equals("AX")) {
                    valor1 = registradores.AX;
                } else if (argumentos[0].equals("BX")) {
                    valor1 = registradores.BX;
                } else {
                    throw new Exception("Argumento inválido para a operação " + operador + ": " + argumentos[0]);
                }

                try {
                    valor2 = Integer.parseInt(argumentos[1]);
                } catch (NumberFormatException e) {
                    if (argumentos[1].equals("AX")) {
                        valor2 = registradores.AX;
                    } else if (argumentos[1].equals("BX")) {
                        valor2 = registradores.BX;
                    } else {
                        throw new Exception("Argumento inválido para a operação " + operador + ": " + argumentos[1]);
                    }
                }

                int resultado;
                if (operador.equals("ADD")) {
                    resultado = valor1 + valor2;
                } else { // operador.equals("SUB")
                    resultado = valor1 - valor2;
                }

                if (argumentos[0].equals("AX")) {
                    registradores.AX = resultado;
                } else { // argumentos[0].equals("BX")
                    registradores.BX = resultado;
                }

                System.out.println("Resultado para " + argumentos[0] + ": " + resultado);
            } else {
                int resultado = operacao.executar(registradores, argumentos);
                String destinatario = argumentos[0];
                System.out.println("Resultado para " + destinatario + ": " + resultado);
            }

            if (operador.equals("MOV")) {
                if (argumentos[0].equals("AX")) {
                    System.out.println("Valor anterior de AX: " + valorAnteriorAX);
                } else if (argumentos[0].equals("BX")) {
                    System.out.println("Valor anterior de BX: " + valorAnteriorBX);
                }
            } else {
                System.out.println("Valor de AX: " + registradores.AX);
                System.out.println("Valor de BX: " + registradores.BX);
            }
            System.out.println("--------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        InterpretadorAssembly.main(args);
    }
}

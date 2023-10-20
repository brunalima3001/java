// Classe que representa a operação ADD do Assembly
// Implementa a interface Operation e representa uma operação composta
public class Adicao implements Operacao {
    @Override
    public int executar(Registradores registradores, String[] argumentos) throws Exception {
        if (argumentos.length != 2) {
            throw new Exception("A operação ADD requer dois argumentos.");
        }
        // Recebe os argumentos da operação (os dois registradores a serem somados)
        int valor1 = 0;
        int valor2 = 0;

        // Verifica qual o valor de cada registrador e soma os valores correspondentes
        if (argumentos[0].equals("AX")) {
            valor1 = registradores.AX;
        } else if (argumentos[0].equals("BX")) {
            valor1 = registradores.BX;
        } else {
            throw new Exception("Argumento inválido para a operação ADD: " + argumentos[0]);
        }

        try {
            valor2 = Integer.parseInt(argumentos[1]);
        } catch (NumberFormatException e) {
            if (argumentos[1].equals("AX")) {
                valor2 = registradores.AX;
            } else if (argumentos[1].equals("BX")) {
                valor2 = registradores.BX;
            } else {
                throw new Exception("Argumento inválido para a operação ADD: " + argumentos[1]);
            }
        }

        registradores.AX = valor1 + valor2;
        // Atualiza o valor do registrador AX com o resultado da operação
        return registradores.AX;
    }
}

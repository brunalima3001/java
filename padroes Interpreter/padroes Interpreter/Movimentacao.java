// Classe que representa a operação MOV do Assembly
// Implementa a interface Operation e representa uma operação simples
public class Movimentacao implements Operacao {
    @Override
    public int executar(Registradores registradores, String[] argumentos) throws Exception {
        if (argumentos.length != 2) {
            throw new Exception("A operação MOV requer dois argumentos.");
        }

        int valor = 0;

        try {
            valor = Integer.parseInt(argumentos[1]);
        } catch (NumberFormatException e) {
            if (argumentos[1].equals("AX")) {
                valor = registradores.AX;
            } else if (argumentos[1].equals("BX")) {
                valor = registradores.BX;
            } else {
                throw new Exception("Argumento inválido para a operação MOV: " + argumentos[1]);
            }
        }

        if (argumentos[0].equals("AX")) {
            registradores.AX = valor;
        } else if (argumentos[0].equals("BX")) {
            registradores.BX = valor;
        } else {
            throw new Exception("Argumento inválido para a operação MOV: " + argumentos[0]);
        }

        return valor;
    }
}

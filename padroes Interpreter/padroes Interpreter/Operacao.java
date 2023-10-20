public interface Operacao {
    // Interface que define a operação comum a todas as classes de operações (TerminalExpression e NonterminalExpression)
    int executar(Registradores registradores, String[] argumentos) throws Exception;
}

import interpreter.command.Command;
import lexical.LexicalAnalysis;
import syntatic.SyntaticAnalysis;
import lexical.TokenType;
import lexical.Lexeme;

public class miniPascal {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java mli [miniPascal file]");
            return;
        }

        try (LexicalAnalysis l = new LexicalAnalysis(args[0])) {
            // O código a seguir é dado para testar o interpretador.
            // TODO: descomentar depois que o analisador léxico estiver OK.
            SyntaticAnalysis s = new SyntaticAnalysis(l);
            s.start();
            System.out.println("Sim");

            // // O código a seguir é usado apenas para testar o analisador léxico.
            // // TODO: depois de pronto, comentar o código abaixo.

            /*
             * Lexeme lex;
             * do {
             * lex = l.nextToken();
             * System.out.printf("%02d: (\"%s\", %s)\n", l.getLine(),
             * lex.token, lex.type);
             * } while (lex.type != TokenType.END_OF_FILE &&
             * lex.type != TokenType.INVALID_TOKEN);
             * 
             * switch (lex.type) {
             * case INVALID_TOKEN:
             * System.out.printf("%02d: Lexema invalido [%s]\n", l.getLine(), lex.token);
             * break;
             * default:
             * System.out.printf("(\"%s\", %s)\n", lex.token, lex.type);
             * break;
             * 
             * }
             */

        } catch (Exception e) {
            System.err.println("Internal error: " + e.getMessage());
        }
    }

}

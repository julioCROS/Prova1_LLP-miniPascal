package syntatic;

import interpreter.command.Command;
import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;

public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;

    public SyntaticAnalysis(LexicalAnalysis lex) {
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public Command start() {
        procCode();
        eat(TokenType.END_OF_FILE);

        return null;
    }

    private void advance() {
        System.out.println("Advanced (\"" + current.token + "\", " +
                current.type + ")");
        current = lex.nextToken();
    }

    private void eat(TokenType type) {
        System.out.println("Expected (..., " + type + "), found (\"" +
                current.token + "\", " + current.type + ")");
        if (type == current.type) {
            current = lex.nextToken();
        } else {
            showError();
        }
    }

    private void showError() {
        System.out.println("Nao");
        System.exit(1);
    }

    // <code> ::= { <procedure-or-function> }
    private void procCode() {
        while (current.type == TokenType.PROCEDURE ||
                current.type == TokenType.FUNCTION) {
            procProcedureOrFunction();
        }
    }

    // <procedure-or-function> ::= (<procedure> | <function>)
    private void procProcedureOrFunction() {
        if (current.type == TokenType.PROCEDURE) {
            procProcedure();
        } else if (current.type == TokenType.FUNCTION) {
            procFunction();
        } else {
            showError();
        }
    }

    // < procedure > ::= Procedure < name > ‘ ( ’ [ < param > [ { ‘ ; ’ < param > }
    // ] ] ‘ ) ’ ‘ ; ’ [ Var < var > { ‘ ; ’ < var > } ] Begin [<
    // procedure-or-function > [ { < procedure-or-function > } ] ] End ‘ ; ’
    private void procProcedure() {
        eat(TokenType.PROCEDURE);
        procName();
        eat(TokenType.OPEN_PAR);
        if (current.type == (TokenType.ID)) {
            procParam();
            while (current.type == TokenType.SEMI_COLON) {
                advance();
                procParam();
            }
        }
        eat(TokenType.CLOSE_PAR);
        eat(TokenType.SEMI_COLON);
        if (current.type == (TokenType.VAR)) {
            advance();
            procVar();
            while (current.type == TokenType.SEMI_COLON) {
                procVar();
            }
        }
        eat(TokenType.BEGIN);
        while (current.type == TokenType.PROCEDURE || current.type == TokenType.FUNCTION) {
            if (current.type == TokenType.PROCEDURE) {
                procProcedure();
            } else if (current.type == TokenType.FUNCTION) {
                procFunction();
            }
        }
        eat(TokenType.END);
        eat(TokenType.SEMI_COLON);
    }

    // < function > ::= Function < name > ‘ ( ’ [ < param > { ‘ ; ’ < param > } ] ‘
    // ) ’ ‘ : ’ < return > ‘ ; ’ [ Var < var > { ‘ ; ’ < var > } ] Begin [ <
    // procedure-or-function > [ { < procedure-or-function > } ] ] End ‘ ; ’
    private void procFunction() {
        eat(TokenType.FUNCTION);
        procName();
        eat(TokenType.OPEN_PAR);
        if (current.type == (TokenType.ID)) {
            procParam();
            while (current.type == TokenType.SEMI_COLON) {
                advance();
                procParam();
            }
        }
        eat(TokenType.CLOSE_PAR);
        eat(TokenType.DOT2);
        procReturn();
        eat(TokenType.SEMI_COLON);
        if (current.type == (TokenType.VAR)) {
            advance();
            procVar();
            while (current.type == TokenType.SEMI_COLON) {
                advance();
                procVar();
            }
        }
        eat(TokenType.BEGIN);
        while (current.type == TokenType.PROCEDURE || current.type == TokenType.FUNCTION) {
            if (current.type == TokenType.PROCEDURE) {
                procProcedure();
            } else if (current.type == TokenType.FUNCTION) {
                procFunction();
            }
        }
        eat(TokenType.END);
        eat(TokenType.SEMI_COLON);
    }

    // <return> ::= <type>
    private void procReturn() {
        procType();
    }

    // <param> ::= { <name> [ { ',' <name> }] ':' <type> }
    private void procParam() {
        while (current.type == TokenType.ID) {
            procName();
            while (current.type == TokenType.COLON) {
                advance();
                procName();
            }
            eat(TokenType.DOT2);
            procType();
        }
    }

    // <type> ::= Integer | Real | String | Boolean
    private void procType() {
        if (current.type == TokenType.INTEGER) {
            eat(TokenType.INTEGER);
        } else if (current.type == TokenType.REAL) {
            eat(TokenType.REAL);
        } else if (current.type == TokenType.STRING) {
            eat(TokenType.STRING);
        } else if (current.type == TokenType.BOOLEAN) {
            eat(TokenType.BOOLEAN);
        } else {
            showError();
        }
    }

    // <var> ::= <name> [ { ',' <name> } ] ':' <type> ';'
    private void procVar() {
        System.out.println(" ****** Dentro de procVar: " + current.type);
        procName();
        while (current.type == TokenType.COLON) {
            advance();
            procName();
        }
        eat(TokenType.DOT2);
        procType();
        eat(TokenType.SEMI_COLON);
    }

    // <name> ::= <id>
    private void procName() {
        eat(TokenType.ID);
    }
}

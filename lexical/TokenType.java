package lexical;

public enum TokenType {
    // SPECIALS
    INVALID_TOKEN,
    END_OF_FILE,

    // SYMBOLS
    SEMI_COLON, // ;
    COLON, // ,
    DOT, // .
    DOT2, // :
    OPEN_PAR, // (
    CLOSE_PAR, // )

    // KEYWORDS
    PROCEDURE, // Procedure
    VAR, // Var
    FUNCTION, // Function
    BEGIN, // Begin
    END, // End

    // OTHERS
    ID, // identifier
    INTEGER, // integer
    STRING, // string
    REAL, // float
    BOOLEAN // boolean
};

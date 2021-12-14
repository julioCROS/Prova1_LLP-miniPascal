package lexical;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();

        // SYMBOLS
        st.put(";", TokenType.SEMI_COLON);
        st.put(",", TokenType.COLON);
        st.put(":", TokenType.DOT);
        st.put(":", TokenType.DOT2);
        st.put("(", TokenType.OPEN_PAR);
        st.put(")", TokenType.CLOSE_PAR);

        // KEYWORDS
        st.put("Procedure", TokenType.PROCEDURE);
        st.put("Function", TokenType.FUNCTION);
        st.put("Var", TokenType.VAR);
        st.put("Begin", TokenType.BEGIN);
        st.put("End", TokenType.END);
        st.put("Integer", TokenType.INTEGER);
        st.put("Real", TokenType.REAL);
        st.put("String", TokenType.STRING);
        st.put("Boolean", TokenType.BOOLEAN);
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ? st.get(token) : TokenType.ID;
    }
}

import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;



public class Scanner{


private static final Map<String , TokenType> keywords;

static{
    keywords = new HashMap<>();
    keywords.put("var" , TokenType.VAR);
    keywords.put("if" , TokenType.IF);
    keywords.put("else" , TokenType.ELSE);
    keywords.put("while" , TokenType.WHILE);
    keywords.put("true" , TokenType.TRUE);
    keywords.put("false" , TokenType.FALSE);
}

    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;

Scanner(String source){
    this.source = source;
}
List<Token> scanTokens(){
    while (!isAtEnd()) {
        start = current;
        scanToken();
    }
    tokens.add(new Token(TokenType.EOF, "", line));
    return tokens;
}
private boolean isAtEnd(){
    return current >= source.length();
}
private void scanToken(){
    char c = advance();
    switch (c) {
        case '+':
            addToken(TokenType.PLUS);
            break;
            case '-':
            addToken(TokenType.MINUS);
            break;
            case ';':
            addToken(TokenType.SEMICOLON);
            break;
            case ' ':
            case '\r':
            case '\t':
            // ignore whitescape
            break;
        case '\n':
        line++;
        break;
        case '"':
        String();
        break;
        default:
        if (Character.isDigit(c)){
            number();}
            else if (isAlpha(c)){
identifier();
            }
            
            else{
        Rune.error(line, "UNEXPECTED CHARACTER");
            }
            break;
    }

}
private char advance(){
    current++;
    return source.charAt(current - 1);
}

private void addToken(TokenType type){
    String text = source.substring(start , current);
    tokens.add(new Token(type , text, line));
}
private char peek(){
    if (isAtEnd()) return '\0';
    return source.charAt(current);
}
private void number(){
    while (Character.isDigit(peek())){
        advance();
    }
    addToken(TokenType.NUMBER);
}
private void String(){
    while (peek() != '"' && !isAtEnd()){
        if(peek() == '\n')line++;
        advance();
    }
    if (isAtEnd()) {
        Rune.error(line, "Unterminated string");
        return;
    }
    advance();

    String value = source.substring(start + 1 , current - 1);
    addToken(TokenType.STRING);

}
private void identifier(){
    while (isAlphaNumric(peek())) {
        advance();
    }
    String text = source.substring(start , current);
    TokenType type = keywords.get(text);

    if (type == null) type = TokenType.Identifier;
    addToken(type);
        
    
}
private boolean isAlpha(char c){
    return (c >= 'a' && c <= 'z') ||
    (c >= 'A' && c <= 'Z') ||
    c == '_';
}
private boolean isAlphaNumric(char c){
    return isAlpha(c) || Character.isDigit(c);
}

}
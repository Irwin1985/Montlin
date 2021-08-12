package monkey.lexer

import monkey.token.*

class Lexer private constructor(private val input: String) {
    private var position = 0
    private var readPosition = 0
    private var ch: Char = 0.toChar()

    companion object {
        fun newInstance(input: String): Lexer {
           val l = Lexer(input)
           l.readChar()
           return l
        }
    }

    fun nextToken(): Token {
        skipWhitespace()
        val tok = when (ch) {
            '=' ->
                if (peekChar() == '=') {
                    readChar()
                    Token(EQ, "==")
                }else {
                    newToken(ASSIGN, '=')
                }
            '+' ->
                newToken(PLUS, '+')
            '-' ->
                newToken(MINUS, '-')
            '*' ->
                newToken(ASTERISK, '*')
            '/' ->
                newToken(SLASH, '/')
            '<' ->
                newToken(LT, '<')
            '>' ->
                newToken(GT, '>')
            ';' ->
                newToken(SEMICOLON, ';')
            ',' ->
                newToken(COMMA, ',')
            ':' ->
                newToken(COLON, ':')
            '{' ->
                newToken(LBRACE, '{')
            '}' ->
                newToken(RBRACE, '}')
            '(' ->
                newToken(LPAREN, '(')
            ')' ->
                newToken(RPAREN, ')')
            '[' ->
                newToken(LBRACKET, '[')
            ']' ->
                newToken(RBRACKET, ']')
            '"' ->
                Token(STRING, readString())
            0.toChar() -> Token(EOF, "")
            else -> when {
                isLetter(ch) -> {
                    val literal = readIdentifier()
                    return Token(literal.lookupIdent(), literal)
                }
                isDigit(ch) -> {
                    return Token(INT, readNumber())
                }
                else -> newToken(ILLEGAL, ch)
            }

        }
        readChar()
        return tok
    }

    private fun peekChar(): Char {
        return if (readPosition >= input.length) {
            0.toChar()
        } else {
            input[readPosition]
        }
    }

    private fun readIdentifier(): String {
        val pos = position
        while (isLetter(ch)) {
            readChar()
        }
        return input.substring(pos until position)
    }

    private fun readNumber(): String {
        val pos = position
        while (isDigit(ch))
            readChar()
        return input.substring(pos until position)
    }

    private fun readString(): String {
        val pos = position + 1
        while (true) {
            readChar()
            if (ch == '"' || ch == 0.toChar())
                break
        }
        // return input[pos, position]
        return input.substring(pos until position)
    }

    fun readChar() {
        ch = if (readPosition >= input.length) {
            0.toChar()
        } else {
            input[readPosition]
        }
        position = readPosition
        readPosition += 1
    }

    private fun skipWhitespace() {
        while (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r')
            readChar()
    }

    private fun isLetter(ch: Char): Boolean {
        return ch in 'a'..'z' || ch in 'A'..'Z' || ch == '_'
    }

    private fun isDigit(ch: Char): Boolean {
        return ch in '0'..'9'
    }

    private fun newToken(tokenType: TokenType, ch: Char) = Token(tokenType, ch.toString())
}
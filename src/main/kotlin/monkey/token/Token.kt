package monkey.token

const val ILLEGAL = "ILLEGAL"
const val EOF = "EOF"

// Identifiers + literals
const val IDENT = "IDENT"   // add, foobar, x, y, ...
const val INT = "INT"       // 1343456
const val STRING = "STRING" // "foobar"

// Oerators
const val ASSIGN = "ASSIGN"
const val PLUS = "PLUS"
const val MINUS = "MINUS"
const val BANG = "BANG"
const val ASTERISK = "ASTERISK"
const val SLASH = "SLASH"

const val LT = "LT"
const val GT = "GT"

const val EQ = "EQ"
const val NOT_EQ = "NOT_EQ"

// Delimiters
const val COMMA = "COMMA"
const val SEMICOLON = "SEMICOLON"
const val COLON = "COLON"

const val LPAREN = "LPAREN"
const val RPAREN = "RPAREN"
const val LBRACE = "LBRACE"
const val RBRACE = "RBRACE"
const val LBRACKET = "LBRACKET"
const val RBRACKET = "RBRACKET"

// keywords
const val FUNCTION = "FUNCTION"
const val LET = "LET"
const val TRUE = "TRUE"
const val FALSE = "FALSE"
const val IF = "IF"
const val ELSE = "ELSE"
const val RETURN = "RETURN"

typealias TokenType = String

data class Token(val type: TokenType, val literal: String)

val keywords = mapOf(
    "fn" to FUNCTION,
    "let" to LET,
    "true" to TRUE,
    "false" to FALSE,
    "if" to IF,
    "else" to ELSE,
    "return" to RETURN,
)

fun String.lookupIdent(): TokenType = keywords[this] ?: IDENT
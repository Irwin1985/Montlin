package monkey.repl

import monkey.lexer.Lexer
import monkey.token.EOF
import java.io.InputStream
import java.io.OutputStream
import java.util.*

const val PROMPT = ">> "

class Repl {
    fun start(input: InputStream, output: OutputStream) {
        val scanner = Scanner(input)

        while (true) {
            output.write(PROMPT)
            var line = scanner.nextLine()
            val l = Lexer.newInstance(line)

            var tok = l.nextToken()

            while (tok.type != EOF) {
                println("<%s, %s>".format(tok.type, tok.literal))
                tok = l.nextToken()
            }
            println("<%s, %s>".format(tok.type, tok.literal))
        }
    }

    private fun OutputStream.write(string: String) {
        this.write(string.toByteArray())
    }
}
import monkey.repl.Repl

fun main(args: Array<String>) {
    repl()
}

fun repl() {
    val user = System.getProperty("user.name")
    println("Hello $user! This is the Monkey programming language!\n")
    println("Freel free to type in commands\n")
    Repl().start(System.`in`, System.out)
}
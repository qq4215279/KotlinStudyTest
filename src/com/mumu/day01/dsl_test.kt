/**
 * Context
 * @author 王愿生
 * @version 1.0.0 @date 2021/1/6 16:34
 */

class Context {
    var context = mutableMapOf<String, String>()
}

class Dependencies {
    fun compile(clazz: String) {
        println("add $clazz")
    }

    operator fun invoke(block: Dependencies.() -> Unit) {
        block()
    }
}

fun verticalLayout(block: () -> Unit) {
    block()
}

fun button(text: String, block: (text: String) -> Unit) {
    block(text)
}

// 声明接收者
fun kotlinDSL(block: StringBuilder.() -> Unit) {
    block(StringBuilder("Kotlin"))
}

fun main() {
    // 调用高阶函数
    kotlinDSL {
        // 这个 lambda 的接收者类型为StringBuilder
        append(" DSL")
        println(this)
    }

    verticalLayout {
        var name = "Hello World";
        println(name)
    }

    button("button1") {
        println("click $it")
    }

    var dependencies = Dependencies();
    dependencies {
        compile("clazz1")
        compile("clazz2")
    }

    var etl = ETL("2020-01-02#start sys version: deficience, err: 2#1#你好")


    etl {

        split("#")

        map("time", "logType", "playerId", "playerName")

        to_date(0, "yyyy-MM-dd")

        to_int(2)

        //regex(1, "start sys version: (\\w+),\\s*err: (\\d+)", "version", "error")
        regex(1, "start sys version: (\\w+),\\s*err: (\\d+)") {
            put(it[1], it[2])
        }

        println(context)
    }

}

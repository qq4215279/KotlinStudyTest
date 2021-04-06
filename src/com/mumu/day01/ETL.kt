import java.io.IOException
import java.lang.Error
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * ETL
 * @author 王愿生
 * @version 1.0.0 @date 2021/1/6 18:18
 */
class ETL(var source: String) {
    // contextMap
    var context = mutableListOf<Pair<String, Any>>()

    // split comma
    var comma = ","

    /**
     * 设置split
     */
    fun split(comma: String) {
        this.comma = comma
    }

    /**
     * 设置title
     */
    fun set_title(index: Int, title: String) {
        if (context.size < index) {
            throw Exception("wrong index $index, index must less than ${context.size}")
        }

        context[index] = title to context[index].second

    }

    fun to_int(index: Int) {
        check_len(index)

        var pair = context[index]
        context[index] = pair.copy(second = pair.second.toString().toInt())
    }

    fun to_date(index: Int, fmt: String) {
        check_len(index)

        var pair = context[index]
        var sdf = SimpleDateFormat(fmt)
        context[index] = pair.copy(second = sdf.parse(pair.second.toString()))
    }

    /**
     * 检查长度
     */
    fun check_len(index: Int) {
        if (context.size < index) {
            throw Exception("wrong index $index, index must less than ${context.size}")
        }
    }

    /**
     * 正则匹配
     */
    fun regex(index: Int, regex: String, vararg titles: String) {
        var text = get(index)
        var reg = Regex(regex)
        var result = reg.matchEntire(text)
        if (result != null) {
            for ((index, value) in result.groupValues.listIterator(1).withIndex()) {
                var title = if (titles.size > index) titles[index] else "UNKNOW" + index
                context.add(title to value)
            }
        }
    }

    /**
     * 获取指定内容
     */
    fun get(index: Int): String {
        check_len(index)
        return context[index].second.toString()
    }

    /**
     * 放值
     */
    fun put(title: String, value: Any) {
        context.add(title to value)
    }

    /**
     * 调用函数
     */
    operator fun invoke(block: ETL.() -> Unit) {
        block()
    }
}

fun ETL.map(vararg titles: String) {
    var values = source.split(comma)

    for ((index, value) in values.withIndex()) {
        var title = if (titles.size > index) titles[index] else "UNKNOW" + index
        context.add(title to value)
    }
}

fun ETL.get(index: Int, block: ETL.() -> Unit) {
    var etl = ETL(this.get(index))
    block(etl)
    context.addAll(etl.context)
}

fun ETL.regex(index: Int, regex: String, block: ETL.(results: List<String>) -> Unit) {
    var text = get(index)
    var reg = Regex(regex)
    var result = reg.matchEntire(text)
    if (result != null) {
        block(result.groupValues)
    }
}

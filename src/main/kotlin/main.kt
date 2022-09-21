/**
 * @param value  число
 * @param accusativeNominativeCaseSingular  вин. или имен. падеж ед. число ('минуту', 'минута', 'час', 'день')
 * @param accusativeNominativeCaseSingularPlural  вин. или имен. падеж мн. число ('минуты', 'часа', 'дня')
 * @param genitiveCasePlural  родительный падеж мн. число ('минут', 'часов', 'дней')
 * @return слово в нужном падеже
 */
fun formatWordByCase(
    value: Int,
    accusativeNominativeCaseSingular: String,
    accusativeNominativeCaseSingularPlural: String,
    genitiveCasePlural: String
): String {

    val lastDigit = value.toString().last().digitToInt()
    val lastTwoDigits = value.toString().takeLast(2).toInt()

    if (lastDigit == 0 || (lastTwoDigits in 10..19) || lastDigit in 5..9) {
        return genitiveCasePlural
    } else if (lastDigit in 2..4) {
        return accusativeNominativeCaseSingularPlural
    }

    return accusativeNominativeCaseSingular
}

fun agoToText(seconds: Int): String {
    return when (seconds) {
        in 0..60 -> "только что"
        in 61..60 * 60 -> "${seconds / 60} ${formatMinutesByCase(seconds / 60)} назад"
        in 60 * 60 + 1..24 * 60 * 60 -> "${seconds / 3600} ${formatHoursByCase(seconds / 3600)} назад"
        in 24 * 60 * 60 + 1..2 * 24 * 60 * 60 -> "вчера"
        in 2 * 24 * 60 * 60 + 1..3 * 24 * 60 * 60 -> "позавчера"
        else -> "давно"
    }
}

fun main() {
    for (sec in 0..60 step 10) println(agoToText(sec))
    for (sec in 61..60 * 60 step 60) println(agoToText(sec))
    for (sec in 60 * 60 + 1..24 * 60 * 60 step 3600) println(agoToText(sec))
    for (sec in 24 * 60 * 60 + 1..3 * 24 * 60 * 60 + 3600 * 3 step 3600) println(agoToText(sec))
}

fun formatMinutesByCase(seconds: Int): String {
    return formatWordByCase(
        seconds, "минуту", "минуты", "минут"
    )
}

fun formatHoursByCase(seconds: Int): String {
    return formatWordByCase(
        seconds, "час", "часа", "часов"
    )
}

fun formatSecondsByCase(seconds: Int): String {
    return formatWordByCase(
        seconds, "секунду", "секунды", "секунд"
    )
}
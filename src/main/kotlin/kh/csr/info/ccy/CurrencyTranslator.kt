package kh.csr.info.ccy

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import java.text.DecimalFormat

@Path("currency")
class CurrencyTranslator {
    @GET
    @Path("translator")
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@QueryParam("number") number: Long): String {
        val valueInStr = StringBuilder()
        val decimalFormatter = DecimalFormat("#,###")
        val extractDigit = number.extractDigits()
        val response = StringBuilder("* The extract digits of $number is $extractDigit \n")
        valueInStr.append(process(extractDigit))
        response.append("* Formatted currency (${extractDigit.size})digits: ៛${decimalFormatter.format(number)}\n")
        if (valueInStr.contains("រៀល")) {
            response.append("* Currency translator: $valueInStr")
        } else response.append("* Error: $valueInStr")
        return response.toString()
    }
}

fun process(extractDigit: List<Long>): String {
    val result = StringBuilder()
    val innerExtractDigit = extractDigit.toMutableList()
    while (innerExtractDigit.size > 0) {
        val number = innerExtractDigit.first().toInt()
        result.append(
            when(innerExtractDigit.size) {
                13 -> millionDigits(number)
                12 -> hundredOfThousandDigits(number)
                11 -> tenOfThousandDigits(number)
                10 -> thousandDigits(number)
                9 -> hundredDigits(number)
                8 -> tenDigits(number)
                7 -> millionDigits(number)
                6 -> hundredOfThousandDigits(number)
                5 -> tenOfThousandDigits(number)
                4 -> thousandDigits(number)
                3 -> hundredDigits(number)
                2 -> tenDigits(number)
                1 -> listDigits(number)
                else -> return "API excepts only amount of 13 digits"
            }
        )
        innerExtractDigit.removeFirst()
        process(innerExtractDigit)
    }
    return result.append("រៀល").toString()
}
fun millionDigits(number: Int): String {
    return when(number) {
        1 -> "មួយលាន"
        2 -> "ពីរលាន"
        3 -> "បីលាន"
        4 -> "បួនលាន"
        5 -> "ប្រាំលាន"
        6 -> "ប្រាំមួយលាន"
        7 -> "ប្រាំពីរលាន"
        8 -> "ប្រាំបីលាន"
        9 -> "ប្រាំបួនលាន"
        else -> "លាន"
    }
}

fun hundredOfThousandDigits(number: Int): String {
    return when(number) {
        1 -> "មួយសែន"
        2 -> "ពីរសែន"
        3 -> "បីសែន"
        4 -> "បួនសែន"
        5 -> "ប្រាំសែន"
        6 -> "ប្រាំមួយសែន"
        7 -> "ប្រាំពីរសែន"
        8 -> "ប្រាំបីសែន"
        9 -> "ប្រាំបួនសែន"
        else -> ""
    }
}

fun tenOfThousandDigits(number: Int): String {
    return when(number) {
        1 -> "មួយម៉ឺន"
        2 -> "ពីរម៉ឺន"
        3 -> "បីម៉ឺន"
        4 -> "បួនម៉ឺន"
        5 -> "ប្រាំម៉ឺន"
        6 -> "ប្រាំមួយម៉ឺន"
        7 -> "ប្រាំពីរម៉ឺន"
        8 -> "ប្រាំបីម៉ឺន"
        9 -> "ប្រាំបួនម៉ឺន"
        else -> ""
    }
}

fun thousandDigits(number: Int): String {
    return when(number) {
        1 -> "មួយពាន់"
        2 -> "ពីរពាន់"
        3 -> "បីពាន់"
        4 -> "បួនពាន់"
        5 -> "ប្រាំពាន់"
        6 -> "ប្រាំមួយពាន់"
        7 -> "ប្រាំពីរពាន់"
        8 -> "ប្រាំបីពាន់"
        9 -> "ប្រាំបួនពាន់"
        else -> ""
    }
}
fun hundredDigits(number: Int): String {
    return when(number) {
        1 -> "មួយរយ"
        2 -> "ពីររយ"
        3 -> "បីរយ"
        4 -> "បួនរយ"
        5 -> "ប្រាំរយ"
        6 -> "ប្រាំមួយរយ"
        7 -> "ប្រាំពីររយ"
        8 -> "ប្រាំបីរយ"
        9 -> "ប្រាំបួនរយ"
        else -> ""
    }
}

fun tenDigits(number: Int): String {
    return when(number) {
        1 -> "ដប់"
        2 -> "ម្ភៃ"
        3 -> "សាមសិប"
        4 -> "សែសិប"
        5 -> "ហាសិប"
        6 -> "ហុកសិប"
        7 -> "ចិតសិប"
        8 -> "ប៉ែតសិប"
        9 -> "កៅសិប"
        else -> ""
    }
}

fun listDigits(number: Int): String {
    return when(number) {
        1 -> "មួយ"
        2 -> "ពីរ"
        3 -> "បី"
        4 -> "បួន"
        5 -> "ប្រាំ"
        6 -> "ប្រាំមួយ"
        7 -> "ប្រាំពីរ"
        8 -> "ប្រាំបី"
        9 -> "ប្រាំបួន"
        else -> ""
    }
}

fun Long.extractDigits(): List<Long> {
    return this.toString().map { it.toString().toLong() }
}
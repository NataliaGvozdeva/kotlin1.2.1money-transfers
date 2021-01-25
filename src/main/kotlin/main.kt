package ru.netology.lesson2

fun main() {

    val commission: Int

    println("Введите сумму перевода в рублях")
    val currentExecutedAmount = readLine()!!.toInt() * 100
    println("Сумма перевода: $currentExecutedAmount")

    println("Введите сумму передыдущих переводов в текущем дне в рублях")
    val currentDayAmount = readLine()!!.toInt() * 100

    println("Введите сумму переводов передыдущих дней в текущем месяце в рублях")
    val currentMonthAmount = readLine()!!.toInt() * 100
    println("Сумма предыдущих переводов в текущий день и текущий месяц: $currentDayAmount, $currentMonthAmount")

    println("Введите тип перевода: \nMastercard - 'mc',\nMaestro - 'mo',\nVisa - 'v',\nMir - 'm',\nVKPay - 'vk' ")
    val typeOfCardOrAccount: String? = readLine()
    println("Тип перевода: $typeOfCardOrAccount")

    if ((currentExecutedAmount != null) && (currentDayAmount != null) &&
        (currentMonthAmount != null) && (typeOfCardOrAccount != null)
    ) {

        val fullNameOfTransferType = when (typeOfCardOrAccount) {
            "mc" -> "Mastercard"
            "mo" -> "Maestro"
            "v" -> "Visa"
            "m" -> "Mir"
            "vk" -> "VkPay"
            else -> {
                println("Неверный тип перевода")
                return
            }
        }

        if (checkTransferLimits(fullNameOfTransferType, currentDayAmount, currentMonthAmount, currentExecutedAmount)) {
            return
        }

        commission = transfer(fullNameOfTransferType, currentMonthAmount, currentExecutedAmount)

        println("Перевод c $fullNameOfTransferType выполнится с комиссий: $commission копеек")

    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
        return
    }

}

fun checkTransferLimits(
    fullNameOfTransferType: String,
    currentDayAmount: Int,
    currentMonthAmount: Int,
    currentExecutedAmount: Int
): Boolean {
    val cardDayLimit = 150_000 * 100
    val cardMonthLimit = 600_000 * 100
    val vkPayMonthLimit = 40_000 * 100
    val vkPayOnceLimit = 15_000 * 100
    val cardVisaMirMinimum = 35 * 100

    when (fullNameOfTransferType) {
        "Mastercard", "Maestro", "Visa", "Mir" -> {
            when {
                (((fullNameOfTransferType == "Visa" )||(fullNameOfTransferType == "Mir" ))&&(currentExecutedAmount < cardVisaMirMinimum)) -> {
                    println("По карте $fullNameOfTransferType сумма перевода меньше минимального лимита $cardVisaMirMinimum.")
                    return true
                }
                ((currentDayAmount + currentExecutedAmount) > cardDayLimit) -> {
                    println("По карте $fullNameOfTransferType превышен суточный лимит")
                    return true
                }
                ((currentMonthAmount + currentDayAmount + currentExecutedAmount) > cardMonthLimit) -> {
                    println("По карте $fullNameOfTransferType превышен месячный лимит")
                    return true
                }
            }
            return false
        }
        "VkPay" -> {
            when {
                (currentExecutedAmount > vkPayOnceLimit) -> {
                    println("По карте $fullNameOfTransferType превышен разовый лимит")
                    return true
                }
                ((currentMonthAmount + currentDayAmount + currentExecutedAmount) > vkPayMonthLimit) -> {
                    println("По карте $fullNameOfTransferType превышен месячный лимит")
                    return true
                }
            }
            return false
        }
        else -> return false
    }
}


fun transferMasterMaestro(
    currentMonthAmount: Int = 0,
    currentExecutedAmount: Int = 0
): Int {

    val maxAdvActionLimit = 75000 * 100

    if (currentMonthAmount <= maxAdvActionLimit) {
        return 0
    } else {
        return (currentExecutedAmount * 6 / 1000 + 20 * 100)
    }
}

fun transferVisaMir(
    currentExecutedAmount: Int = 0
): Int = (currentExecutedAmount * 75 / 10000)

fun transfer(
    typeOfCardOrAccount: String = "VkPay",
    currentMonthAmount: Int = 0,
    currentExecutedAmount: Int = 0
): Int = when (typeOfCardOrAccount) {
    "Mastercard", "Maestro" -> transferMasterMaestro(currentMonthAmount + currentExecutedAmount, currentExecutedAmount)
    "Visa", "Mir" -> transferVisaMir(currentExecutedAmount)
    else -> 0
}
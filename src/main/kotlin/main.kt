package ru.netology.lesson2

fun main() {

    val commission: Int

    println("Введите сумму перевода в рублях")
    val currentExecutedAmount = readLine()!!.toInt() * 100
    if (currentExecutedAmount <= 0){
        println("Введена некорректная сумма. Операция отклонена.")
        return
    }
    println("Сумма перевода: $currentExecutedAmount")

    println("Введите сумму передыдущих переводов в текущем дне в рублях")
    val currentDayAmount = readLine()!!.toInt() * 100

    println("Введите сумму переводов передыдущих дней в текущем месяце в рублях")
    val currentMonthAmount = readLine()!!.toInt() * 100
    println("Сумма предыдущих переводов в текущий день и текущий месяц: $currentDayAmount, $currentMonthAmount")

    println("Введите тип перевода: \nMastercard - 'mc',\nMaestro - 'mo',\nVisa - 'v',\nMir - 'm',\nVKPay - 'vk' ")
    val typeOfCardOrAccount: String? = readLine()

    if ((currentExecutedAmount != null) || (currentDayAmount != null) ||
        (currentMonthAmount != null) || (typeOfCardOrAccount != null)
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

        commission = transfer(fullNameOfTransferType, currentDayAmount, currentMonthAmount, currentExecutedAmount)

        if (commission == -1) {
            return
        }

        println("Перевод c $fullNameOfTransferType выполнится с комиссий: $commission копеек")

    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
        return
    }

}

fun checkVkPayLimits(
    fullNameOfTransferType: String,
    currentDayAmount: Int,
    currentMonthAmount: Int,
    currentExecutedAmount: Int
): Int {

    val vkPayMonthLimit = 40_000 * 100
    val vkPayOnceLimit = 15_000 * 100

    when {
        (currentExecutedAmount > vkPayOnceLimit) -> {
            println("По карте $fullNameOfTransferType превышен разовый лимит")
            return -1
        }
        ((currentMonthAmount + currentDayAmount + currentExecutedAmount) > vkPayMonthLimit) -> {
            println("По карте $fullNameOfTransferType превышен месячный лимит")
            return -1
        }
    }
    return 0
}


fun transferMasterMaestro(
    typeOfCardOrAccount: String,
    currentMonthAmount: Int = 0,
    currentDayAmount: Int = 0,
    currentExecutedAmount: Int = 0
): Int {

    val cardDayLimit = 150_000 * 100
    val cardMonthLimit = 600_000 * 100
    val maxAdvActionLimit = 75000 * 100

    when {
        ((currentDayAmount + currentExecutedAmount) > cardDayLimit) -> {
            println("По карте $typeOfCardOrAccount превышен суточный лимит")
            return -1
        }
        ((currentMonthAmount + currentDayAmount + currentExecutedAmount) > cardMonthLimit) -> {
            println("По карте $typeOfCardOrAccount превышен месячный лимит")
            return -1
        }
    }

    if ((currentMonthAmount + currentDayAmount + currentExecutedAmount) <= maxAdvActionLimit) {
        return 0
    } else {
        return (currentExecutedAmount * 6 / 1000 + 20 * 100).toInt()
    }
}

fun transferVisaMir(
    typeOfCardOrAccount: String,
    currentMonthAmount: Int = 0,
    currentDayAmount: Int = 0,
    currentExecutedAmount: Int = 0
): Int {

    val cardDayLimit = 150_000 * 100
    val cardMonthLimit = 600_000 * 100
    val cardVisaMirMinimum = 35 * 100

    val fullCurrentDayAmount = currentDayAmount + currentExecutedAmount
    val fullCurrentMonthAmount = currentMonthAmount + currentDayAmount + currentExecutedAmount

    when {
        (fullCurrentDayAmount < 0) -> {
            println("Арифметическая ошибка вычисления")
            return -1
        }
        (fullCurrentMonthAmount < 0) -> {
            println("Арифметическая ошибка вычисления")
            return -1
        }
        (currentExecutedAmount < cardVisaMirMinimum) -> {
            println("По карте $typeOfCardOrAccount сумма перевода меньше минимального лимита $cardVisaMirMinimum.")
            return -1
        }
        (fullCurrentDayAmount > cardDayLimit) -> {
            println("По карте $typeOfCardOrAccount превышен суточный лимит")
            return -1
        }
        (fullCurrentMonthAmount > cardMonthLimit) -> {
            println("По карте $typeOfCardOrAccount превышен месячный лимит")
            return -1
        }
    }

    return (currentExecutedAmount * 75 / 10000).toInt()
}

fun transfer(
    typeOfCardOrAccount: String = "VkPay",
    currentDayAmount: Int = 0,
    currentMonthAmount: Int = 0,
    currentExecutedAmount: Int = 0
): Int {
    val comission = when (typeOfCardOrAccount) {
        "Mastercard", "Maestro" -> transferMasterMaestro(
            typeOfCardOrAccount,
            currentMonthAmount,
            currentDayAmount,
            currentExecutedAmount
        )
        "Visa", "Mir" -> transferVisaMir(
            typeOfCardOrAccount,
            currentMonthAmount,
            currentDayAmount,
            currentExecutedAmount
        )
        "VkPay" -> checkVkPayLimits(
            typeOfCardOrAccount,
            currentMonthAmount,
            currentDayAmount,
            currentExecutedAmount
        )
        else -> -1
    }
    return comission
}
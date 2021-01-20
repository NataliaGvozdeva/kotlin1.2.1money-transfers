package ru.netology.lesson2

fun main() {
    val amount1 = 300 * 100    // для карты комиссия 225к
    val amount2 = 27000 * 100  // для карты комиссия 20250к, VK Pay - привышение лимита
    val amount3 = 35 * 100     // для карты комиссия 26к
    val amount4 = 15 * 100     // для карты - меньше допустимого значения
    val amount5 = 150001 * 100 // для карты комиссия - привышение лимита, VK Pay - привышение лимита
    val amount6 = 600001 * 100 // для карты комиссия - привышение лимита, VK Pay - привышение лимита
    val amount7 = 15001 * 100  // для карты комиссия 11250к, VK Pay - привышение лимита
    val amount8 = 40001 * 100  // для карты комиссия 30000к, VK Pay - привышение лимита

    val minCardAmount = 35 * 100
    val minVkpayAmount = 0

    val cardDayLimit = 150_000 * 100
    val vkPayDayLimit = 15_000 * 100

    val typeMasterMaestro = 1
    val typeVisaMir = 2
    val typeVKPay = 3

    /*!!!! Здесь можно подставлять подходящее значения !!!*/
    val transferType = 1 /*Mastercard,Maestro - 1; Visa,Мир - 2; VK Pay - 3;*/
    val currentExecutedAmount = amount1 /* <--- сумма перевода */

    if ((transferType == typeMasterMaestro)||(transferType == typeVisaMir)) {
        if (currentExecutedAmount < minCardAmount){
            println("Сумма перевода меньше допустимого значения.")
            return
        } else if (currentExecutedAmount > cardDayLimit) {
            println("Bы привысили дневную допустимую сумму при переводе картой.")
            return
        } else {
            val commission = (currentExecutedAmount * 75) / 10000
            println("Перевод картой Mastercard, Maestro, Visa, Мир выполнится с комиссий: $commission копеек")
        }
    }
    if (transferType == typeVKPay) {
        if (currentExecutedAmount < minVkpayAmount){
             println("Сумма перевода меньше допустимого значения.")
             return
        } else if (currentExecutedAmount > vkPayDayLimit) {
            println("Bы привысили дневную допустимую сумму при переводе с VK Pay.")
            return
        } else {
            println("Перевод VK Pay выполнится без комиссии.")
        }

    }

}
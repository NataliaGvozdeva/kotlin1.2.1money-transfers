package ru.netology.lesson2

enum class TypeOfCardOrAmountEnum(val typeOfCardOrAccount: String) {
    MASTERCARD("mc"),
    MAESTRO("mo"),
    VISA("v"),
    MIR("m"),
    VKPAY("vk"),
    UNDEFINED("un")
}
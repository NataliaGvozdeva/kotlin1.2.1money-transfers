package ru.netology.lesson2

import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun transfer_VkPay_Correct() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val vkPayOnceLimit = 15_000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = vkPayOnceLimit
        )

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_VkPay_Zero() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_VkPay_outLimit_OnceLimit() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val vkPayOnceLimit = 15_000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = vkPayOnceLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_VkPay_outLimit_MonthLimit() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val vkPayMonthLimit = 40_000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = vkPayMonthLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_VkPay_outLimit_MonthLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val vkPayMonthLimit = 40_000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = vkPayMonthLimit+1,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }
    @Test
    fun transfer_VkPay_outLimit_MonthLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val vkPayMonthLimit = 40_000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = vkPayMonthLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Master_Zero() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_Master_outLimit_DayLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardDayLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Master_outLimit_DayLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = cardDayLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Master_outLimit_MonthLimit() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = cardMonthLimit+1,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Master_inLimit_MoreThenMaxAdvActionLimit_Exec() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = maxAdvActionLimit+1
        )

        //assert
        assertEquals(47000,result)

    }

    @Test
    fun transfer_Master_inLimit_LowThenMaxAdvActionLimit_Exec() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = maxAdvActionLimit
        )

        //assert
        assertEquals(0,result)

    }

    //--------

    @Test
    fun transfer_Maestro_Zero() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_Maestro_outLimit_DayLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardDayLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Maestro_outLimit_DayLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = cardDayLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Maestro_outLimit_MonthLimit() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = cardMonthLimit+1,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Maestro_inLimit_MoreThenMaxAdvActionLimit_Exec() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = maxAdvActionLimit+1
        )

        //assert
        assertEquals(47000,result)

    }

    @Test
    fun transfer_Maestro_inLimit_LowThenMaxAdvActionLimit_Exec() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val maxAdvActionLimit = 75000 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = maxAdvActionLimit
        )

        //assert
        assertEquals(0,result)

    }

    //-------------------

    @Test
    fun transfer_Mir_OutLimit_LowThenCardVisaMirMinimum() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardVisaMirMinimum-1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Mir_OutLimit_MoreCardDayLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 400
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardDayLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Mir_OutLimit_MoreCardDayLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = cardDayLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = dayAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Mir_OutLimit_MoreCardMonthLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardMonthLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Mir_OutLimit_MoreCardMonthLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = cardMonthLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = dayAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Mir_OutLimit_MoreCardMonthLimit3() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = cardMonthLimit+1,
            currentExecutedAmount = dayAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Mir_InLimit_MoreCardMonthLimit() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = 150_000 * 100
        )

        //assert
        assertEquals(112500,result)

    }

    //----------------

    @Test
    fun transfer_Visa_OutLimit_LowThenCardVisaMirMinimum() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardVisaMirMinimum-1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Visa_OutLimit_MoreCardDayLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 400
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardDayLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Visa_OutLimit_MoreCardDayLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = cardDayLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = dayAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Visa_OutLimit_MoreCardMonthLimit1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = cardMonthLimit+1
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Visa_OutLimit_MoreCardMonthLimit2() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = cardMonthLimit+1,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = dayAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Visa_OutLimit_MoreCardMonthLimit3() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = cardMonthLimit+1,
            currentExecutedAmount = dayAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_Visa_InLimit() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = 150_000 * 100
        )

        //assert
        assertEquals(112500,result)

    }

//    @Test
//    fun transfer_Visa_OutLimit_MaxIntValue() {
//        //arrange
//        val type = "Visa"
//        val dayAmount = 40
//        val monthAmount = 0
//        val executedAmount = 1
//        val cardDayLimit = 150_000 * 100
//        val cardMonthLimit = 600_000 * 100
//        val cardVisaMirMinimum = 35 * 100
//
//        //act
//        val result = transfer(
//            typeOfCardOrAccount = type,
//            currentDayAmount = executedAmount,
//            currentMonthAmount = monthAmount,
//            currentExecutedAmount = 2_147_483_647
//        )
//
//        //assert
//        assertEquals(-1,result)
//
//    }



    //-----
    @Test
    fun transfer_IncorrectType_Null() {
        //arrange
        val type = TypeOfCardOrAmountEnum.UNDEFINED
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(
            typeOfCardOrAccount = type,
            currentDayAmount = dayAmount,
            currentMonthAmount = monthAmount,
            currentExecutedAmount = executedAmount
        )

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_NoParameters() {
        //arrange
        val type = TypeOfCardOrAmountEnum.UNDEFINED
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer()

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_NoParameters_VkPay() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VKPAY
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(type)

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_NoParameters_Mir() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MIR
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(type)

        //assert
        assertEquals(-1,result)

    }

    @Test
    fun transfer_NoParameters_Visa() {
        //arrange
        val type = TypeOfCardOrAmountEnum.VISA
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(type)

        //assert
        assertEquals(-1,result)

    }


    @Test
    fun transfer_NoParameters_Mastercard() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MASTERCARD
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(type)

        //assert
        assertEquals(0,result)

    }

    @Test
    fun transfer_NoParameters_Maestro() {
        //arrange
        val type = TypeOfCardOrAmountEnum.MAESTRO
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(type)

        //assert
        assertEquals(0,result)

    }


    @Test
    fun transfer_NoParameters1() {
        //arrange
        val type = TypeOfCardOrAmountEnum.UNDEFINED
        val dayAmount = 0
        val monthAmount = 0
        val executedAmount = 0
        val cardDayLimit = 150_000 * 100
        val cardMonthLimit = 600_000 * 100
        val cardVisaMirMinimum = 35 * 100

        //act
        val result = transfer(type)

        //assert
        assertEquals(-1,result)

    }

//    @Test
//    fun transfer_WithParameters_IncorrectValues() {
//        //arrange
//        val type = "ZZZ"
//        val dayAmount = 0
//        val monthAmount = 0
//        val executedAmount = 0
//        val cardDayLimit = 150_000 * 100
//        val cardMonthLimit = 600_000 * 100
//        val cardVisaMirMinimum = 35 * 100
//
//        //act
//        val result = transfer(
//            typeOfCardOrAccount = type,
//            currentDayAmount = -1,
//            currentMonthAmount = -1,
//            currentExecutedAmount = -1
//        )
//
//        //assert
//        assertEquals(-1,result)
//
//    }

}
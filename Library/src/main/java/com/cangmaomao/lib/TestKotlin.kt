package com.cangmaomao.lib

fun main(args: Array<String>) {

    TestKotlin().testK()

}

class TestKotlin {

    fun testK() {

        val list = listOf(1, 9, 5, 6)

        val list2 = listOf("6", "5", "9", "1")

        label1@ for (num in list) {
          label2@  for (str in list2) {
                if(str.equals("$num")){
                    break@label1
                }
            }
        }

    }

}
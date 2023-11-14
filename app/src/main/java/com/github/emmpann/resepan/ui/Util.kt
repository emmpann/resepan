package com.github.emmpann.resepan.ui

fun contentFormat(arrayValue: ArrayList<String>): String {
    var result = ""
    arrayValue.forEach {
        result += "- $it\n"
    }
    return result
}
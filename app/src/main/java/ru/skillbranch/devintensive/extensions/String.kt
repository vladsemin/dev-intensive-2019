package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String{
    val trimmedString = this.trim()
    return if (trimmedString.length <= length) trimmedString else trimmedString.substring(0, length).trim() + "..."
}
package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var parts: List<String>? = null

        if (fullName?.trim()?.length != 0) parts = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInit = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()?.toString() ?: ""
        val secondInit = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()?.toString() ?: ""
        return "$firstInit$secondInit".ifEmpty { null }
    }
}
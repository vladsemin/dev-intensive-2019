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

    fun transliteration(payload: String, divider: String = " "): String {
        val dictionary = getDictionary()
        val stringBuilder = StringBuilder()

        for (char in payload.trim())
            stringBuilder.append(replaceCharacter(char, dictionary))

        return stringBuilder.toString().replace(" ", divider)
    }

    private fun replaceCharacter(char: Char, dictionary: HashMap<Char, String>): String {
        val transl  = dictionary[char.toLowerCase()] ?: char.toString()

        return if (char.isUpperCase() && transl.isNotEmpty())
            transl.capitalize()
        else transl
    }

    private fun getDictionary(): HashMap<Char, String> {
        val hashMap = hashMapOf<Char, String>()
        hashMap['а'] = "a"
        hashMap['б'] = "b"
        hashMap['в'] = "v"
        hashMap['г'] = "g"
        hashMap['д'] = "d"
        hashMap['е'] = "e"
        hashMap['ё'] = "e"
        hashMap['ж'] = "zh"
        hashMap['з'] = "z"
        hashMap['и'] = "i"
        hashMap['й'] = "i"
        hashMap['к'] = "k"
        hashMap['л'] = "l"
        hashMap['м'] = "m"
        hashMap['н'] = "n"
        hashMap['о'] = "o"
        hashMap['п'] = "p"
        hashMap['р'] = "r"
        hashMap['с'] = "s"
        hashMap['т'] = "t"
        hashMap['у'] = "u"
        hashMap['ф'] = "f"
        hashMap['х'] = "h"
        hashMap['ц'] = "c"
        hashMap['ч'] = "ch"
        hashMap['ш'] = "sh"
        hashMap['щ'] = "sh'"
        hashMap['ъ'] = ""
        hashMap['ы'] = "i"
        hashMap['ь'] = ""
        hashMap['э'] = "e"
        hashMap['ю'] = "yu"
        hashMap['я'] = "ya"

        return hashMap
    }
}
package com.suleimanov.mustBe.processor

import com.google.devtools.ksp.symbol.KSAnnotation

fun KSAnnotation.getReasonArg(): String {
    return arguments.firstOrNull { it.name?.asString() == "reason" }?.value?.toString().orEmpty()
}

fun String.appendReason(annotation: KSAnnotation): String {
    return annotation.getReasonArg().takeIf { it.isNotBlank() }?.let { "$this. $it" } ?: this
}
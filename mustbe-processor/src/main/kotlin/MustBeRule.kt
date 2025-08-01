package com.suleimanov.mustBe.processor

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.*
import com.suleimanov.mustBe.annotations.*
import kotlin.reflect.KClass

enum class MustBeRule(
    val annotation: KClass<out Annotation>,
    private val requirement: (KSClassDeclaration) -> Boolean,
    private val errorMessage: (String, KSClassDeclaration) -> String
) {
    DATA_CLASS(
        annotation = MustBeDataClass::class,
        requirement = { it.modifiers.contains(Modifier.DATA) },
        errorMessage = { name, declaration -> "Element '$name' must be a data class, but '${declaration.simpleName.asString()}' is not" }
    ),

    SEALED(
        annotation = MustBeSealed::class,
        requirement = { it.modifiers.contains(Modifier.SEALED) },
        errorMessage = { name, declaration -> "Element '$name' must be a sealed class, but '${declaration.simpleName.asString()}' is not" }
    ),

    ENUM(
        annotation = MustBeEnum::class,
        requirement = { it.modifiers.contains(Modifier.ENUM) },
        errorMessage = { name, declaration -> "Element '$name' must be an enum class, but '${declaration.simpleName.asString()}' is not" }
    ),

    INTERFACE(
        annotation = MustBeInterface::class,
        requirement = { it.classKind == ClassKind.INTERFACE },
        errorMessage = { name, declaration -> "Element '$name' must be an interface, but '${declaration.simpleName.asString()}' is not" }
    ),

    FINAL(
        annotation = MustBeFinal::class,
        requirement = { it.modifiers.contains(Modifier.FINAL) },
        errorMessage = { name, declaration -> "Element '$name' must be final, but '${declaration.simpleName.asString()}' is not" }
    ),

    OPEN(
        annotation = MustBeOpen::class,
        requirement = { it.modifiers.contains(Modifier.OPEN) },
        errorMessage = { name, declaration -> "Element '$name' must be open, but '${declaration.simpleName.asString()}' is not" }
    ),

    PUBLIC(
        annotation = MustBePublic::class,
        requirement = { it.modifiers.contains(Modifier.PUBLIC) },
        errorMessage = { name, declaration -> "Element '$name' must be public, but '${declaration.simpleName.asString()}' is not" }
    ),

    ABSTRACT(
        annotation = MustBeAbstract::class,
        requirement = { it.modifiers.contains(Modifier.ABSTRACT) },
        errorMessage = { name, declaration -> "Element '$name' must be abstract, but '${declaration.simpleName.asString()}' is not" }
    );

    fun validate(type: KSType, elementName: String, element: KSAnnotated, logger: KSPLogger, annotation: KSAnnotation) {
        val declaration = type.declaration as? KSClassDeclaration ?: return
        val errorMessage = errorMessage(elementName, declaration).appendReason(annotation)
        if (!requirement(declaration)) logger.error(errorMessage, element)
    }
}
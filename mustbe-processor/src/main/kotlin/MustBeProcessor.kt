package com.suleimanov.mustBe.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.*

class MustBeProcessor(private val codeGenerator: CodeGenerator, private val logger: KSPLogger) : SymbolProcessor {

    private val rules: Map<String, MustBeRule> = MustBeRule.entries.associateBy { it.annotation.simpleName.orEmpty() }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getAllFiles().forEach { file ->
            file.declarations.forEach { declaration ->
                when (declaration) {
                    is KSClassDeclaration -> processClassDeclaration(declaration)
                    is KSFunctionDeclaration -> processFunctionDeclaration(declaration)
                }
            }
        }
        return emptyList()
    }

    private fun processClassDeclaration(classDecl: KSClassDeclaration) {
        classDecl.getAllProperties().forEach { processAnnotations(it, it.type.resolve(), it.simpleName.asString()) }
        classDecl.typeParameters.forEach { typeParam ->
            typeParam.annotations.forEach { annotation ->
                rules[annotation.shortName.asString()]?.let {
                    logger.error("Type parameter '${ typeParam.name.asString()}' is annotated with " +
                            "@${it.annotation.simpleName} but cannot be validated at compile time", typeParam)
                }
            }
        }
    }

    private fun processFunctionDeclaration(declaration: KSFunctionDeclaration) {
        declaration.parameters.forEach { param -> processAnnotations(param, param.type.resolve(), param.name?.asString() ?: "parameter") }
    }

    private fun processAnnotations(element: KSAnnotated, resolvedType: KSType, name: String) {
        val annotations = element.annotations.map { annotation -> annotation to rules[annotation.shortName.asString()] }
        annotations.forEach { (annotation, rule) -> rule?.validate(resolvedType, name, element, logger, annotation) }
    }
}
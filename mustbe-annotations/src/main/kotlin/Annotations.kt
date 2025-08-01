package com.suleimanov.mustBe.annotations

/**
 * Annotation for validating that a type must be a data class.
 * Applicable to fields, method parameters, properties, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeDataClass(val reason: String = "")

/**
 * Annotation for validating that a type must be a sealed class or interface.
 * Applicable to fields, method parameters, properties, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeSealed(val reason: String = "")

/**
 * Annotation for validating that a type must be an enum class.
 * Applicable to fields, method parameters, properties, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeEnum(val reason: String = "")

/**
 * Annotation for validating that a type must be an interface.
 * Applicable to fields, method parameters, properties, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeInterface(val reason: String = "")

/**
 * Annotation for validating that a type must be an abstract class.
 * Applicable to fields, method parameters, properties, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeAbstract(val reason: String = "")

/**
 * Annotation for validating that a type must be final (non-open, non-abstract).
 * Applicable to properties, method parameters, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeFinal(val reason: String = "")

/**
 * Annotation for validating that a type must be open (i.e., extendable).
 * Applicable to properties, method parameters, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBeOpen(val reason: String = "")

/**
 * Annotation for validating that a type must be public (i.e., accessible outside its declaring module).
 * Applicable to properties, method parameters, and type parameters.
 */
@Target(AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class MustBePublic(val reason: String = "")

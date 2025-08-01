package com.suleimanov.mustBe.sample

import com.suleimanov.mustBe.annotations.*

// Правильные типы для использования в примерах
data class ValidDataClass(val id: Int)
sealed class ValidSealedClass {
    object Loading : ValidSealedClass()
    data class Success(val data: String) : ValidSealedClass()
}
enum class ValidEnum { A, B, C }
interface ValidInterface { fun doSomething() }
abstract class ValidAbstractClass { abstract fun doSomething() }
open class ValidOpenClass
class ValidFinalClass
public class ValidPublicClass


// Неправильные типы
class InvalidDataClass(val id: Int)
class InvalidSealedClass {
    object Loading
    class Success
}
// Этот класс выглядит как enum, но не является enum
class InvalidEnum {
    val A = "A"
    val B = "B"
}
class InvalidInterface {
    fun doSomething() {}
}
class InvalidAbstractClass {
    fun doSomething() {}
}

abstract class InvalidOpenClass // не open, а abstract
open class InvalidFinalClass // не final
internal class InvalidPublicClass // не public


// Примеры правильного использования аннотаций в API
abstract class ValidBaseViewModel {
    @MustBeDataClass
    protected var validUser: ValidDataClass? = null // Valid
    
    @MustBeSealed
    protected var validState: ValidSealedClass? = null // Valid
    
    @MustBeEnum
    protected var validRole: ValidEnum = ValidEnum.A // Valid
    
    @MustBeInterface
    protected var validRepo: ValidInterface? = null // Valid
    
    @MustBeAbstract
    protected var validViewModel: ValidAbstractClass? = null // Valid

    @MustBeFinal
    protected var finalItem: ValidFinalClass? = null

    @MustBeOpen
    protected var openItem: ValidOpenClass? = null

    @MustBePublic
    protected var publicItem: ValidPublicClass? = null


    abstract fun loadData()
}

// Примеры неправильного использования аннотаций в API

// Ошибка: API пытается использовать неправильный тип для поля с аннотацией
abstract class InvalidBaseViewModel {
    @MustBeDataClass("Custom Error")
    protected var invalidUser: InvalidDataClass? = null // Compilation error

    @MustBeSealed
    protected var invalidState: InvalidSealedClass? = null // Compilation error

    @MustBeEnum
    protected var invalidRole: InvalidEnum? = null // Compilation error - InvalidEnum не является enum

    @MustBeInterface
    protected var invalidRepo: InvalidInterface? = null // Compilation error

    @MustBeAbstract
    protected var invalidViewModel: InvalidAbstractClass? = null // Compilation error

    @MustBeFinal
    protected var invalidFinal: InvalidFinalClass? = null

    @MustBeOpen
    protected var invalidOpen: InvalidOpenClass? = null

    @MustBePublic
    internal var invalidPublic: InvalidPublicClass? = null

    abstract fun loadData()
}

// Ошибка: API функция с неправильными типами параметров
fun invalidApiFunction(
    @MustBeDataClass invalidUser: InvalidDataClass, // Compilation error
    @MustBeSealed invalidState: InvalidSealedClass, // Compilation error
    @MustBeEnum invalidRole: InvalidEnum // Compilation error - InvalidEnum не является enum
) {
    // Реализация
}

// Ошибка: API класс с неправильными дженериками
class InvalidApiClient<T : Any> {
    // Дженерик помечен @MustBeDataClass, но это не поддерживается на уровне компиляции
    fun <@MustBeDataClass R> invalidGenericMethod(data: R): R { // Compilation error
        return data
    }
}
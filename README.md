# MustBe

<div align="center">
  <img src="favicon.svg" alt="MustBe Logo" width="100%" height="auto">
</div>

A Kotlin Symbol Processing (KSP) library for compile-time type validation and constraints.

## Overview

MustBe provides compile-time validation for type constraints using KSP annotations. The library ensures that types used in your code match specific requirements (data classes, sealed classes, enums, etc.) at build time. The library consists of two modules:

- `mustbe-annotations`: Contains type validation annotations
- `mustbe-processor`: KSP processor that validates type constraints during compilation

## Installation

### Gradle (Kotlin DSL)

```kotlin
plugins {
    id("com.google.devtools.ksp") version "2.0.20-1.0.24"
}

dependencies {
    implementation("com.suleimanov.kotlin:mustbe-annotations:0.0.2")
    ksp("com.suleimanov.kotlin:mustbe-processor:0.0.2")
}
```

### Gradle (Groovy)

```groovy
plugins {
    id 'com.google.devtools.ksp' version '2.0.20-1.0.24'
}

dependencies {
    implementation 'com.suleimanov.kotlin:mustbe-annotations:0.0.2'
    ksp 'com.suleimanov.kotlin:mustbe-processor:0.0.2'
}
```

## Usage

### Type Validation

MustBe provides compile-time validation for type constraints using KSP annotations:

```kotlin
import com.suleimanov.mustBe.annotations.*

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

// Example usage in API classes
abstract class BaseViewModel {
    @MustBeDataClass
    protected var user: ValidDataClass? = null
    
    @MustBeSealed
    protected var state: ValidSealedClass? = null
    
    @MustBeEnum
    protected var role: ValidEnum = ValidEnum.A
    
    @MustBeInterface
    protected var repository: ValidInterface? = null
    
    @MustBeAbstract
    protected var viewModel: ValidAbstractClass? = null
    
    @MustBeFinal
    protected var finalItem: ValidFinalClass? = null
    
    @MustBeOpen
    protected var openItem: ValidOpenClass? = null
    
    @MustBePublic
    protected var publicItem: ValidPublicClass? = null
}
```

### Available Annotations

- `@MustBeDataClass`: Ensures type is a data class
- `@MustBeSealed`: Ensures type is a sealed class or interface
- `@MustBeEnum`: Ensures type is an enum class
- `@MustBeInterface`: Ensures type is an interface
- `@MustBeAbstract`: Ensures type is an abstract class
- `@MustBeFinal`: Ensures type is final (non-open, non-abstract)
- `@MustBeOpen`: Ensures type is open (extendable)
- `@MustBePublic`: Ensures type is public

### Parameter Validation

Annotations can also be applied to function parameters:

```kotlin
fun apiFunction(
    @MustBeDataClass user: ValidDataClass,
    @MustBeSealed state: ValidSealedClass,
    @MustBeEnum role: ValidEnum
) {
    // Implementation
}
```

## Compile-Time Validation

The KSP processor validates type constraints at compile time. When you use MustBe annotations, the processor checks that the annotated types match the expected constraints:

```kotlin
// This will compile successfully
@MustBeDataClass
val user: ValidDataClass = ValidDataClass(1)

// This will cause a compilation error
@MustBeDataClass
val invalidUser: InvalidDataClass = InvalidDataClass(1) // Error: InvalidDataClass is not a data class
```

### Error Messages

When validation fails, the processor provides clear error messages:

```
e: Type 'InvalidDataClass' is not a data class, but @MustBeDataClass annotation requires it to be a data class
e: Type 'InvalidSealedClass' is not a sealed class, but @MustBeSealed annotation requires it to be a sealed class
e: Type 'InvalidEnum' is not an enum class, but @MustBeEnum annotation requires it to be an enum class
```

## Configuration

### KSP Arguments

```kotlin
ksp {
    arg("mustbe.package", "com.yourpackage.validators")
}
```

### Custom Error Messages

You can provide custom error messages for validation failures:

```kotlin
@MustBeDataClass("User must be a data class for serialization")
val user: ValidDataClass = ValidDataClass(1)

@MustBeSealed("State must be sealed for proper state management")
val state: ValidSealedClass = ValidSealedClass.Loading
```

## Build Requirements

- Kotlin 2.0.20+
- KSP 2.0.20-1.0.24+
- Java 11+
- Gradle 8.14+

## Development

### Project Structure

```
mustBe/
├── mustbe-annotations/     # Validation annotations
├── mustbe-processor/       # KSP processor
└── mustbe-sample/          # Usage examples
```

### Building

```bash
./gradlew build
```

### Running Tests

```bash
./gradlew test
```

### Publishing

```bash
./gradlew publishAllPublicationsToMavenCentralRepository
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## Issues

Report bugs and feature requests on the [GitHub Issues](https://github.com/suleimanovs/mustbe-annotations/issues) page. 
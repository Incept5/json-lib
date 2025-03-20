# JSON Library

A lightweight Kotlin library for JSON serialization and deserialization using Jackson, with convenient extension functions.

## Features

- Simple, fluent API for JSON operations
- Built on top of Jackson with pre-configured ObjectMapper
- Support for Java 8 date/time types (JSR310)
- Kotlin-friendly with full nullability support
- Pretty printing capability
- Type-safe deserialization with reified type parameters
- Support for generic type references

## Installation

### Gradle (Kotlin DSL)

Add the JitPack repository to your build file:

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

Add the dependency:

```kotlin
dependencies {
    implementation("com.github.incept5:json-lib:1.0.0") // Use the latest version
}
```

### Gradle (Groovy DSL)

Add the JitPack repository:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```

Add the dependency:

```groovy
dependencies {
    implementation 'com.github.incept5:json-lib:1.0.0' // Use the latest version
}
```

### Maven

Add the JitPack repository:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency:

```xml
<dependency>
    <groupId>com.github.incept5</groupId>
    <artifactId>json-lib</artifactId>
    <version>1.0.0</version> <!-- Use the latest version -->
</dependency>
```

## Usage

### Basic Serialization and Deserialization

```kotlin
import org.incept5.json.Json

// Define a data class
data class Person(val name: String, val age: Int)

// Serialize to JSON
val person = Person("John Doe", 30)
val json = Json.toJson(person)
// Result: {"name":"John Doe","age":30}

// Deserialize from JSON
val deserializedPerson = Json.fromJson<Person>(json)
```

### Pretty Printing

```kotlin
val prettyJson = Json.toJsonWithPrettyPrint(person)
// Result:
// {
//   "name" : "John Doe",
//   "age" : 30
// }
```

### Working with Collections and Generic Types

```kotlin
import com.fasterxml.jackson.core.type.TypeReference

// List of objects
val people = listOf(
    Person("John Doe", 30),
    Person("Jane Smith", 25)
)

// Serialize list
val jsonList = Json.toJson(people)

// Deserialize list using TypeReference
val deserializedList = Json.fromJson(jsonList, object : TypeReference<List<Person>>() {})
```

### Accessing the ObjectMapper

If you need to customize the ObjectMapper:

```kotlin
val mapper = Json.mapper()
// Now you can configure the mapper further if needed
```

## Requirements

- Java 21 or higher
- Kotlin 1.9 or higher

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/goldmensch/JALL/Verify%20state?style=for-the-badge&label=Build)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/goldmensch/JALL/Publish%20to%20Nexus?style=for-the-badge&label=Publish)
![Sonatype Nexus (Releases)](https://img.shields.io/nexus/maven-releases/io.github.goldmensch/JALL?label=Release&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)
![Sonatype Nexus (Development)](https://img.shields.io/nexus/maven-dev/io.github.goldmensch/JALL?label=DEV&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)
![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/io.github.goldmensch/JALL?color=orange&label=Snapshot&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)

# JALL

JustAnotherLocalizationLibrary - A localization library that aims to be easy to use and
customizable.

## Example Usage

A small example of using JALL:

_testBundle_en.properties_
```properties
hello=Hi {name}, how are you?
fine=Fine, thank you.
```

_testBundle_de.properties_
```properties
hello=Hi {name}, wie gehts dir?
fine=Gut, danke fürs Fragen.
```

_Code:_
```java
    var englishBundle = ResourceBundle.getBundle("testBundle", Locale.ENGLISH);
    var germanBundle = ResourceBundle.getBundle("testBundle", Locale.GERMAN);

    var localizer = Jall.createStandard(Locale.ENGLISH);
    translator.register(Localization.fromResourceBundle(englishBundle), false);
    translator.register(Localization.fromResourceBundle(germanBundle), false);

    System.out.println(translator.localize("hello", Locale.ENGLISH, Replacement.create("name", "Jeff")));
    System.out.println(translator.localize("hello", Locale.GERMAN, Replacement.create("name", "Jeff")));
```

## Docs

We provide several kind of docs: JavaDocs and our Wiki (coming soon)

The latest JavaDocs of the [development branch](https://github.com/Goldmensch/JALL/tree/development)
can be found at.  
https://goldmensch.github.io/JALL/development/.

Each release on the [master branch](https://github.com/Goldmensch/JALL/tree/masster) has its own
docs, these can be  
found under:  
https://goldmensch.github.io/JALL/version/, where "version" represents the version of the release,
for example:  
https://goldmensch.github.io/JALL/1.0/

## Dependency

### Gradle (Kotlin DSL)

#### Releases/Dev-Builds

You must replace `VERSION` with the desired version

```kotlin  
repositories {  
 maven("https://eldonexus.de/repository/maven-public")
}  
  
dependencies {  
 implementation("io.github.goldmensch", "JALL", "VERSION")
}  
```  

#### Snapshots

Attention. Snapshots were deleted after 2 weeks, because they are only created in feature branches
and are more intended  
for short-term testing than for productive use.

```kotlin  
repositories {  
 maven("https://eldonexus.de/repository/maven-snapshot")
}  
  
dependencies {  
 implementation("io.github.goldmensch", "JALL", "VERSION")
}  
```  

## Contributing

We are happy about any contribution, be it new features, typo-/style fixes or anything else. For new
features, please  
open an issue first so we can discuss it.

The library is built with Gradle, requires at least JDK 16 and uses  
the [Google Style Guide](https://google.github.io/styleguide/javaguide.html).

## License

JALL is released under the terms of
the [MIT License](https://github.com/Goldmensch/JALL/blob/master/LICENSE).

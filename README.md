# JALL

JustAnotherLocalizationLibrary - A localization library that aims to be easy to use and customizable.

**The library is currently in development for its first release.**

## Docs

We provide several kind of docs: JavaDocs and our Wiki (coming soon)

The latest JavaDocs of the [development branch](https://github.com/Goldmensch/JALL/tree/development) can be found at.
https://goldmensch.github.io/JALL/development/.

Each release on the [master branch](https://github.com/Goldmensch/JALL/tree/masster) has its own docs, these can be
found under:
https://goldmensch.github.io/JALL/version/, where "version" represents the version of the release, for example:
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

Attention. Snapshots were deleted after 2 weeks, because they are only created in feature branches and are more intended
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

We are happy about any contribution, be it new features, typo-/style fixes or anything else. For new features, please
open an issue first so we can discuss it.

The library is built with Gradle, requires at least JDK 16 and uses
the [Google Style Guide](https://google.github.io/styleguide/javaguide.html).

## License

JALL is released under the terms of the [MIT License](https://github.com/Goldmensch/JALL/blob/master/LICENSE).

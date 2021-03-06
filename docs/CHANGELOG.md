# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- Suppress `UnusedProperty` inspection for the `kotlin.stdlib.default.dependency` in `gradle.properties`
- Use Gradle `wrapper` task to handle Gradle updates
- JVM compatibility version extracted to `gradle.properties` file
- GitHub Actions: UI Tests workflow

### Changed
- Update `pluginVerifierIdeVersions` to `2020.3.4, 2021.1.3`
- Change since/until build to `203-212.*`
- JVM compatibility version goes to 11!
- Upgrade Gradle Wrapper to `7.1.1`

## [1.0.7]
### Changed
- Support 2021.2 EAP

## [1.0.6]
### Changed
- Update to Gradle 7.0.2
- Update to gradle-intellij-plugin 1.0
- Use gradle-changelog-plugin 1.1.2
- Use kotlin 1.5.10
- Use detekt and klint
- Use intellij-platform-plugin-template

## [1.0.5]
### Changed
- Support IntelliJ 2021.1 EAP
- Update to Gradle 6.8.2
- Update to gradle-intellij-plugin 0.6.5
- Use gradle-changelog-plugin 1.0.1
- Use kotlin 1.4.30

## [1.0.4]
### Changed
- Support IntelliJ 2020.3 EAP
- Update to Gradle 6.6.1
- Update to gradle-intellij-plugin 0.5.0
- Use gradle-changelog-plugin  0.5.0

## [1.0.3]
### Changed
- Support IntelliJ 2020.2 EAP
- Fix diff icon
- Update to Gradle 6.4.1
- Update to gradle-intellij-plugin 0.4.21

## [1.0.2]
### Changed
- Convert to Kotlin
- Update to Gradle 6.1.1
- Update to gradle-intellij-plugin 0.4.15

## [1.0.1]
### Changed
- A new release to test publishing from gradle

## [1.0.0]
### Changed
- Updated for Intellij 2019.2+, since some deprecated APIs were removed

[Unreleased]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.7...HEAD
[1.0.7]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.6...v1.0.7
[1.0.6]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.5...v1.0.6
[1.0.5]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.4...v1.0.5
[1.0.4]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.3...v1.0.4
[1.0.3]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.2...v1.0.3
[1.0.2]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/jbeckers/CompareTabWithEditor2/compare/v1.0.0...v1.0.1
[1.0.0]: https://github.com/jbeckers/CompareTabWithEditor2/releases/tag/v1.0.0

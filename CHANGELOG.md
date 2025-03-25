<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# selfie-intellij-plugin Changelog

## [Unreleased]
### Added
- Access to "edit injected content" action within language-injected snapshots
  + For example, it allows to preview markdown/mermaid/html ...
- Structure view
- Snapshot folding
- Navigation icon from Java test class to corresponding selfie snapshot

### Fixed
- Facet parsing was expecting a leading space and would not parse `path[facet]`
- Did not parse `╔═ [end of file] ═╗` token

## [0.0.1]
### Added
- Selfie snapshot file detection and parsing
- Language injection within snapshots
- Automatic language injection from snapshot headers

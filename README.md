# Client-Hints
> Library parses http headers and returns parsed client-hints.

[![Build](https://github.com/bgalek/client-hints/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/bgalek/client-hints/actions/workflows/ci.yml)
![Codecov](https://img.shields.io/codecov/c/github/bgalek/client-hints.svg?style=flat-square)
![GitHub Release Date](https://img.shields.io/github/release-date/bgalek/client-hints.svg?style=flat-square)
![Libraries.io dependency status for GitHub repo](https://img.shields.io/librariesio/github/bgalek/client-hints.svg?style=flat-square)


## Usage

```java
ClientHints clientHints = ClientHints.fromMap(headers);
...
clientHints.isMobile();
clientHints.getPlatform();
clientHints.getPlatformVersion();
clientHints.getArchitecture();
clientHints.getUserAgent();
clientHints.getDeviceMemory();
clientHints.getDownlink();
clientHints.getEffectiveConnectionType();
clientHints.getModel();
clientHints.getRoundTripTime();
clientHints.isSaveDataEnabled();
```

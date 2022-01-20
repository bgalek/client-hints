# Client-Hints

Library parses http headers and returns parsed client-hints.

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

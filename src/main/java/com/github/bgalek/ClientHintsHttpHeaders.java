package com.github.bgalek;

public enum ClientHintsHttpHeaders {

    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">Effective connection type</a>
     */
    ARCHITECTURE("Sec-CH-UA-Arch"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Device-Memory">Device-Memory</a>
     */
    DEVICE_MEMORY("Device-Memory"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Downlink">Downlink</a>
     */
    DOWNLINK("Downlink"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">Effective connection type</a>
     */
    EFFECTIVE_CONNECTION_TYPE("ECT"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-CH-UA-Mobile">Sec-CH-UA-Mobile</a>
     */
    MOBILE("Sec-CH-UA-Mobile"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-CH-UA-Model">Sec-CH-UA-Model</a>
     */
    MODEL("Sec-CH-UA-Model"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-CH-UA-Platform">Sec-CH-UA-Platform</a>
     */
    PLATFORM("Sec-CH-UA-Platform"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-CH-UA-Platform-Version">Sec-CH-UA-Platform-Version</a>
     */
    PLATFORM_VERSION("Sec-CH-UA-Platform-Version"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/RTT">RTT</a>
     */
    ROUND_TRIP_TIME("RTT"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Save-Data">Save-Data</a>
     */
    SAVE_DATA("Save-Data"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-CH-UA">Sec-CH-UA</a>
     */
    USER_AGENT("Sec-CH-UA");

    private final String headerName;

    ClientHintsHttpHeaders(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}

package com.github.bgalek;

public enum ClientHintsHttpHeaders {

    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    ARCHITECTURE("Sec-CH-UA-Arch"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    DEVICE_MEMORY("Device-Memory"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    DOWNLINK("Downlink"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    EFFECTIVE_CONNECTION_TYPE("ECT"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    MOBILE("Sec-CH-UA-Mobile"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    MODEL("Sec-CH-UA-Model"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    PLATFORM("Sec-CH-UA-Platform"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    PLATFORM_VERSION("Sec-CH-UA-Platform-Version"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    ROUND_TRIP_TIME("RTT"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    SAVE_DATA("Save-Data"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    USER_AGENT("Sec-CH-UA"),
    /**
     * @see <a href="https://developer.mozilla.org/en-US/docs/Glossary/Effective_connection_type">...</a>
     */
    USER_AGENT_FULL_VERSION("Sec-CH-UA-Full-Version");

    private final String headerName;

    ClientHintsHttpHeaders(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }
}

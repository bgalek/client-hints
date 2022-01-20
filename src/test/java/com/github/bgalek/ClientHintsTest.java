package com.github.bgalek;

import com.github.bgalek.ClientHints.UserAgent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@DisplayName("client hints")
class ClientHintsTest {

    @Test
    @DisplayName("should parse http headers map")
    void headersParsingTest() {
        //given
        Map<String, String> headers = Map.ofEntries(
                Map.entry("Content-Type", "application/json"),
                Map.entry("Accept-Lang", "pl"),
                Map.entry("X-Random", "42"),
                Map.entry("Sec-CH-UA-Mobile", "?1"),
                Map.entry("Sec-CH-UA-Platform", "Android"),
                Map.entry("Sec-CH-UA-Platform-Version", "11"),
                Map.entry("Sec-CH-UA-Arch", "arm"),
                Map.entry("Sec-CH-UA", "\"Chromium\";v=\"84\", \"Google Chrome\";v=\"84\""),
                Map.entry("Device-Memory", "0.25"),
                Map.entry("Downlink", "300"),
                Map.entry("ECT", "4g"),
                Map.entry("Sec-CH-UA-Model", "Pixel 3"),
                Map.entry("RTT", "125"),
                Map.entry("Save-Data", "On")
        );

        //when
        ClientHints clientHints = ClientHints.fromMap(headers);

        //then
        Assertions.assertEquals(true, clientHints.isMobile().orElseThrow());
        Assertions.assertEquals("Android", clientHints.getPlatform().orElseThrow());
        Assertions.assertEquals("11", clientHints.getPlatformVersion().orElseThrow());
        Assertions.assertEquals("arm", clientHints.getArchitecture().orElseThrow());
        Assertions.assertEquals(List.of(new UserAgent("Chromium", "84"), new UserAgent("Google Chrome", "84")), clientHints.getUserAgent());
        Assertions.assertEquals(0.25, clientHints.getDeviceMemory().orElseThrow());
        Assertions.assertEquals(300.0, clientHints.getDownlink().orElseThrow());
        Assertions.assertEquals("4g", clientHints.getEffectiveConnectionType().orElseThrow());
        Assertions.assertEquals("Pixel 3", clientHints.getModel().orElseThrow());
        Assertions.assertEquals(Duration.ofMillis(125), clientHints.getRoundTripTime().orElseThrow());
        Assertions.assertEquals(true, clientHints.isSaveDataEnabled().orElseThrow());
    }
}
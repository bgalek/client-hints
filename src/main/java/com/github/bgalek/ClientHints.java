package com.github.bgalek;

import java.time.Duration;
import java.util.AbstractMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class ClientHints {

    private final Map<ClientHintsHttpHeaders, String> clientHints;

    ClientHints(Map<ClientHintsHttpHeaders, String> clientHints) {
        this.clientHints = clientHints;
    }

    public static ClientHints fromSingleValueMap(Map<String, List<String>> httpHeaders) {
        return fromMap(httpHeaders.entrySet()
                .stream()
                .filter(entry -> !isNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().findFirst().orElse("")))
        );
    }

    public static ClientHints fromMap(Map<String, String> httpHeaders) {
        Map<String, ClientHintsHttpHeaders> clientHintsHttpHeaders = getAllClientHintsHttpHeaders();
        Map<ClientHintsHttpHeaders, String> headers = httpHeaders.entrySet()
                .stream()
                .filter(entry -> !isNull(entry.getKey()))
                .filter(entry -> !isNull(entry.getValue()))
                .filter(entry -> !entry.getKey().isEmpty())
                .filter(entry -> !entry.getValue().isEmpty())
                .filter(entry -> clientHintsHttpHeaders.containsKey(entry.getKey().toLowerCase()))
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        clientHintsHttpHeaders.get(entry.getKey().toLowerCase()),
                        entry.getValue())
                )
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue, (s1, s2) -> s1.compareTo(s2) <= 0 ? s1 : s2));
        return new ClientHints(headers);
    }

    public Optional<String> getArchitecture() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.ARCHITECTURE))
                .map(this::parseSfString);
    }

    public Optional<Double> getDeviceMemory() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.DEVICE_MEMORY))
                .map(this::parseSfDecimal)
                .filter(it -> List.of(0.25, 0.5, 1, 2, 4, 8).contains(it));
    }

    public Optional<Double> getDownlink() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.DOWNLINK))
                .map(this::parseSfDecimal);
    }

    public Optional<String> getEffectiveConnectionType() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.EFFECTIVE_CONNECTION_TYPE))
                .map(this::parseSfString)
                .filter(it -> List.of("slow-2g", "2g", "3g", "4g").contains(it));
    }

    public Optional<Boolean> isMobile() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.MOBILE))
                .map(this::parseSfBoolean);
    }

    public Optional<String> getModel() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.MODEL))
                .map(this::parseSfString);
    }

    public Optional<String> getPlatform() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.PLATFORM))
                .map(this::parseSfString);
    }

    public Optional<String> getPlatformVersion() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.PLATFORM_VERSION))
                .map(this::parseSfString);
    }

    public Optional<Duration> getRoundTripTime() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.ROUND_TRIP_TIME))
                .map(this::parseSfInteger)
                .map(Duration::ofMillis);
    }

    public Optional<Boolean> isSaveDataEnabled() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.SAVE_DATA))
                .map(this::parseSDToken);
    }

    public List<UserAgent> getUserAgent() {
        return Optional.ofNullable(clientHints.get(ClientHintsHttpHeaders.USER_AGENT))
                .map(this::parseSfList)
                .map(this::toUserAgent)
                .orElse(List.of());
    }

    private List<UserAgent> toUserAgent(List<String> input) {
        return input.stream().map(it -> {
            String[] split = it.replace("\"", "").split(";v=", 2);
            return new UserAgent(split[0].trim(), split[1].trim());
        }).collect(Collectors.toList());
    }

    /**
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8941#section-3.1">Structured Field Values for HTTP</a>
     */
    private List<String> parseSfList(String input) {
        return List.of(input.split(","));
    }

    /**
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8941#section-3.3.1">Structured Field Values for HTTP</a>
     */
    private Double parseSfDecimal(String input) {
        return Double.parseDouble(input);
    }

    /**
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8941#section-3.3.1">Structured Field Values for HTTP</a>
     */
    private Integer parseSfInteger(String input) {
        return Integer.parseInt(input);
    }

    /**
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8941#section-3.3.3">Structured Field Values for HTTP</a>
     */
    private String parseSfString(String input) {
        return input;
    }

    /**
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8941#section-3.3.6">Structured Field Values for HTTP</a>
     */
    private Boolean parseSfBoolean(String input) {
        return input.substring(1).equals("1");
    }

    /**
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8941#section-3.3.6">Structured Field Values for HTTP</a>
     */
    private Boolean parseSDToken(String input) {
        return input.equalsIgnoreCase("on");
    }

    private static Map<String, ClientHintsHttpHeaders> getAllClientHintsHttpHeaders() {
        return EnumSet.allOf(ClientHintsHttpHeaders.class)
                .stream()
                .collect(Collectors.toMap(it -> it.getHeaderName().toLowerCase(), Function.identity()));
    }

    public static class UserAgent {
        private final String name;
        private final String version;

        public UserAgent(String name, String version) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserAgent userAgent = (UserAgent) o;

            if (!Objects.equals(name, userAgent.name)) return false;
            return Objects.equals(version, userAgent.version);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (version != null ? version.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return String.format("UserAgent{name='%s', version='%s'}", name, version);
        }
    }
}

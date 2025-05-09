package io.github.kareiku;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultLang implements Lang {
    private final Map<String, String> lines;

    public DefaultLang(@NotNull String path, @NotNull String separator) throws IOException {
        this.lines = Collections.unmodifiableMap(
                Files.readAllLines(Paths.get(path)).stream()
                        .map(String::trim)
                        .filter(line -> line.contains(separator))
                        .map(line -> line.split(separator, 2))
                        .filter(pair -> pair.length == 2)
                        .collect(Collectors.toMap(
                                pair -> pair[0],
                                pair -> pair[1],
                                (oldValue, newValue) -> oldValue
                        )));
    }

    @Override
    public @NotNull String getLine(@NotNull String key) {
        return this.lines.getOrDefault(key, "");
    }

    @Override
    public @NotNull Optional<String> getOptionalLine(@Nullable String key) {
        return Optional.ofNullable(this.lines.get(key));
    }
}

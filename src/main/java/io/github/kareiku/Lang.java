package io.github.kareiku;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface Lang {
    @NotNull String getLine(@NotNull String key);

    @NotNull Optional<String> getOptionalLine(@NotNull String key);
}

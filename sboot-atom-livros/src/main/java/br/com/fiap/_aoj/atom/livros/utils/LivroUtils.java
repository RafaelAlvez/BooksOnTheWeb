package br.com.fiap._aoj.atom.livros.utils;

import java.util.Arrays;
import java.util.Objects;

public class LivroUtils {

    private LivroUtils() {
    }

    public static String replaceString(String message, String... args) {
        if (Objects.isNull(args))
            return message;

        return Arrays.stream(args)
                .reduce(message, (se, element) -> se.replaceFirst("\\{}", element)).replaceAll("\"", " ");
    }

}
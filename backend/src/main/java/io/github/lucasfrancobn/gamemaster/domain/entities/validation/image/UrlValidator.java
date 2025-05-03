package io.github.lucasfrancobn.gamemaster.domain.entities.validation.image;

import java.util.regex.Pattern;

public class UrlValidator {
    private static final String URI_REGEX = "^(https?):\\/\\/[^\\s/$.?#].[^\\s]*";
    private static final Pattern URI_PATTERN = Pattern.compile(URI_REGEX);

    public static boolean isValid(final String redirectUri) {
        return redirectUri != null && URI_PATTERN.matcher(redirectUri).matches();
    }
}

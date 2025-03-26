package io.github.lucasfrancobn.gamemaster.domain.entities.validation.client;

import java.util.regex.Pattern;

public class RedirectUriValidator {
    private static final String URI_REGEX = "^(https?|ftp):\\/\\/[^\\s/$.?#].[^\\s]*";
    private static final Pattern URI_PATTERN = Pattern.compile(URI_REGEX);

    public static boolean isValid(final String redirectUri) {
        return redirectUri != null && URI_PATTERN.matcher(redirectUri).matches();
    }
}

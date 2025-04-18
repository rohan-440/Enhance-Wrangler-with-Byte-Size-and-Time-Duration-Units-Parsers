package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.Locale;

public class TimeDuration implements Token {
    private final long millis;
    private final String original;

    public TimeDuration(String value) {
        this.original = value;
        this.millis = parse(value);
    }

    private long parse(String value) {
        value = value.trim().toLowerCase(Locale.ROOT);
        long multiplier;

        if (value.endsWith("ms")) {
            multiplier = 1;
            value = value.substring(0, value.length() - 2);
        } else if (value.endsWith("s")) {
            multiplier = 1000;
            value = value.substring(0, value.length() - 1);
        } else if (value.endsWith("m")) {
            multiplier = 60 * 1000;
            value = value.substring(0, value.length() - 1);
        } else if (value.endsWith("h")) {
            multiplier = 60 * 60 * 1000;
            value = value.substring(0, value.length() - 1);
        } else if (value.endsWith("d")) {
            multiplier = 24 * 60 * 60 * 1000;
            value = value.substring(0, value.length() - 1);
        } else {
            throw new IllegalArgumentException("Unrecognized time duration format: " + original);
        }

        return (long) (Double.parseDouble(value.trim()) * multiplier);
    }

    public long getMillis() {
        return millis;
    }

    @Override
    public Object value() {
        return millis;
    }

    @Override
    public TokenType type() {
        return TokenType.TIME_DURATION;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(millis);
    }

    @Override
    public String toString() {
        return original;
    }
}

package io.cdap.wrangler.api.parser;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.Locale;

public class ByteSize implements Token {
    private final long bytes;
    private final String original;

    public ByteSize(String value) {
        this.original = value;
        this.bytes = parse(value);
    }

    private long parse(String value) {
        value = value.trim().toUpperCase(Locale.ROOT);
        long multiplier;
        if (value.endsWith("KB")) {
            multiplier = 1024L;
            value = value.substring(0, value.length() - 2);
        } else if (value.endsWith("MB")) {
            multiplier = 1024L * 1024;
            value = value.substring(0, value.length() - 2);
        } else if (value.endsWith("GB")) {
            multiplier = 1024L * 1024 * 1024;
            value = value.substring(0, value.length() - 2);
        } else if (value.endsWith("TB")) {
            multiplier = 1024L * 1024 * 1024 * 1024;
            value = value.substring(0, value.length() - 2);
        } else if (value.endsWith("B")) {
            multiplier = 1;
            value = value.substring(0, value.length() - 1);
        } else {
            throw new IllegalArgumentException("Unrecognized byte size format: " + original);
        }

        return (long) (Double.parseDouble(value.trim()) * multiplier);
    }

    public long getBytes() {
        return bytes;
    }

    @Override
    public Object value() {
        return bytes;
    }

    @Override
    public TokenType type() {
        return TokenType.BYTE_SIZE;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(bytes);
    }

    @Override
    public String toString() {
        return original;
    }
}

package com.app.utils;

import java.text.DateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Configuration class for JSON writing operations.
 * This class provides various options to customize JSON output formatting.
 *
 * @author Edmund Tutuma
 * @version 1.0
 * @since 2024-03-19
 */
public class WriteOptions {
    private boolean prettyPrint = false;
    private boolean ignoreNull = false;
    private DateFormat dateFormat;
    private Include inclusion;

    /**
     * Checks if pretty printing is enabled.
     *
     * @return true if pretty printing is enabled, false otherwise
     */
    public boolean isPrettyPrint() {
        return prettyPrint;
    }

    /**
     * Sets the pretty printing option.
     *
     * @param prettyPrint true to enable pretty printing, false to disable
     * @return this WriteOptions instance for method chaining
     */
    public WriteOptions setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
        return this;
    }

    /**
     * Checks if null values should be ignored during serialization.
     *
     * @return true if null values should be ignored, false otherwise
     */
    public boolean isIgnoreNull() {
        return ignoreNull;
    }

    /**
     * Sets the ignore null option.
     *
     * @param ignoreNull true to ignore null values, false to include them
     * @return this WriteOptions instance for method chaining
     */
    public WriteOptions setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
        return this;
    }

    /**
     * Gets the configured date format.
     *
     * @return the current date format configuration
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Sets the date format for JSON serialization.
     *
     * @param dateFormat the date format to use
     * @return this WriteOptions instance for method chaining
     */
    public WriteOptions setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    /**
     * Gets the current inclusion strategy.
     *
     * @return the current inclusion configuration
     */
    public Include getInclusion() {
        return inclusion;
    }

    /**
     * Sets the inclusion strategy for JSON serialization.
     *
     * @param inclusion the inclusion strategy to use
     * @return this WriteOptions instance for method chaining
     */
    public WriteOptions setInclusion(Include inclusion) {
        this.inclusion = inclusion;
        return this;
    }
}

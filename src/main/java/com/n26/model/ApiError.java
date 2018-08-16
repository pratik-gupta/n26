package com.n26.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * Pojo class to wrap and transfer reset exception/error.
 * @author Pratik
 *
 */
public class ApiError {

    private String detail;
    private int status;

    private String language;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonIgnore
    public String getLanguage() {
        return language;
    }

    @JsonIgnore
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApiError apiError = (ApiError) o;

        return new EqualsBuilder()
                .append(status, apiError.status)
                .append(detail, apiError.detail)
                .append(language, apiError.language)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(detail)
                .append(status)
                .append(language)
                .toHashCode();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String language;
        private int status;
        private String detail;

        public Builder status(final int status) {
            this.status = status;
            return this;
        }

        public Builder detail(final String detail) {
            this.detail = detail;
            return this;
        }

        public Builder language(final String language) {
            this.language = language;
            return this;
        }

        public ApiError build() {

            final ApiError error = new ApiError();
            error.setStatus(this.status);
            error.setDetail(this.detail);
            error.setLanguage(this.language);
            return error;
        }
    }
}
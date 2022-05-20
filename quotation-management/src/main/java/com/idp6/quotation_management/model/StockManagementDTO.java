package com.idp6.quotation_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class StockManagementDTO {
    @JsonProperty
    public String id;
    @JsonProperty
    public String description;

    @Override
    public String toString() {
        return "StockManagementDTO{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockManagementDTO)) return false;
        StockManagementDTO that = (StockManagementDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

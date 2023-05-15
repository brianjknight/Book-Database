package com.bookdatatbase.bdapi.search;

import com.bookdatatbase.bdapi.enums.FieldType;
import com.bookdatatbase.bdapi.enums.Operator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * POJO containing fields for filtering database queries.
 * key is the field of the object to use for comparison.
 * Operator is the Operator enum for comparison. (See Operator enum in 'enums' package.)
 * FieldType is the FieldType enum for parsing the string to proper type. (See FieldType enum in 'enums' package.)
 * value, valueTo, & values are given fields to compare to for querying the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilterRequest implements Serializable {

    private static final long serialVersionUID = 6293344849078612450L;

    private String key;

    private String entity;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;

    private transient Object valueTo;

    private transient List<Object> values;

}

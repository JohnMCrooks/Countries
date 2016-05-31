package com.crooks;

/**
 * Created by johncrooks on 5/26/16.
 */
public class Country {
    String abbreviation;
    String fullName;

    public Country(String abbreviation, String fullName) {
        this.abbreviation = abbreviation;
        this.fullName = fullName;
    }

    public Country() {
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}

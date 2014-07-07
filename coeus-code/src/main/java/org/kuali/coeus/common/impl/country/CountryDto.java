package org.kuali.coeus.common.impl.country;


import org.kuali.coeus.common.api.country.CountryContract;

public class CountryDto implements CountryContract {

    private String alternateCode;
    private String code;
    private String name;

    @Override
    public String getAlternateCode() {
        return alternateCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setAlternateCode(String alternateCode) {
        this.alternateCode = alternateCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}

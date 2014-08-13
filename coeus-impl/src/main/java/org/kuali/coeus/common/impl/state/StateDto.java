package org.kuali.coeus.common.impl.state;

import org.kuali.coeus.common.api.state.StateContract;

public class StateDto implements StateContract {

    private String name;
    private String countryCode;
    private String code;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

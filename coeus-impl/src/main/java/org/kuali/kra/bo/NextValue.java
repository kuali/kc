package org.kuali.kra.bo;

public interface NextValue {

    String getPropertyName();
    
    void setPropertyName(String propertyName);

    Integer getNextValue();

    void setNextValue(Integer nextValue);

    String getDocumentKey();

    void setDocumentKey(String documentKey);
}

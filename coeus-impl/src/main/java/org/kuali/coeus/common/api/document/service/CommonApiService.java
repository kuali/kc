package org.kuali.coeus.common.api.document.service;

public interface CommonApiService {

    public void validatePerson(String personId, Integer rolodexId);

    public Object convertObject(Object input, Class clazz);

    public void clearErrors();

    }

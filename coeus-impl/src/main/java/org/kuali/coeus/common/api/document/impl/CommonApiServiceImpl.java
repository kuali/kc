package org.kuali.coeus.common.api.document.impl;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("commonApiService")
public class CommonApiServiceImpl implements CommonApiService {

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    public void validatePerson(String personId, Integer rolodexId) {
        Entity personEntity = null;
        RolodexContract rolodex = null;
        if (personId != null) {
            personEntity = identityService.getEntityByPrincipalId(personId);
        }
        else {
            rolodex = rolodexService.getRolodex(rolodexId);
        }

        if (rolodex == null && personEntity == null) {
            throw new UnprocessableEntityException("Invalid person or rolodex for person " + personId != null ? personId : rolodexId.toString() );
        }
    }

    public Object convertObject(Object input, Class clazz) {
        Configuration mooConfig = new Configuration();
        mooConfig.setSourcePropertiesRequired(false);
        Moo moo = new Moo(mooConfig);
        Object newDataObject = getNewDataObject(clazz);
        moo.update(input, newDataObject);
        return newDataObject;
    }


    public Object getNewDataObject(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("cannot create new data object", e);
        }
    }
}

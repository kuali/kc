package org.kuali.kra.s2s.depend;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.budget.personnel.TbnPerson;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component("toBeNamePersonService")
public class ToBeNamePersonServiceImpl implements ToBeNamePersonService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public TbnPerson findTbnPersonById(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("id is blank");
        }

        return businessObjectService.findByPrimaryKey(TbnPerson.class, Collections.singletonMap("tbnId", id));
    }
}

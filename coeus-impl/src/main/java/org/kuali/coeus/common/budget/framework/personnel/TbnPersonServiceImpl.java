package org.kuali.coeus.common.budget.framework.personnel;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.personnel.TbnPersonContract;
import org.kuali.coeus.common.budget.api.personnel.TbnPersonService;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("tbnPersonService")
public class TbnPersonServiceImpl implements TbnPersonService {

    @Override
    public TbnPersonContract getTbnPerson(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("id is blank");
        }

        return dataObjectService.find(TbnPerson.class, id);
    }

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}

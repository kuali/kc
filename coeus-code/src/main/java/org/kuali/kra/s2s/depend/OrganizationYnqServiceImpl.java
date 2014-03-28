package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.OrganizationYnq;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("organizationYnqService")
public class OrganizationYnqServiceImpl implements OrganizationYnqService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public List<OrganizationYnq> findOrganizationYnqByOrgId(String organizationId) {
        if (StringUtils.isBlank(organizationId)) {
            throw new IllegalArgumentException("The organizationId cannot be blank");
        }

        return ListUtils.emptyIfNull(dataObjectService.findMatching(OrganizationYnq.class,
                QueryByCriteria.Builder.andAttributes(Collections.singletonMap("organizationId", organizationId)).build()).getResults());
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}

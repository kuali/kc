package org.kuali.coeus.instprop.impl.admin;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsService;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("proposalAdminDetailsService")
public class ProposalAdminDetailsServiceImpl implements ProposalAdminDetailsService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<? extends ProposalAdminDetailsContract> findProposalAdminDetailsByPropDevNumber(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        return ListUtils.emptyIfNull((List<ProposalAdminDetails>) businessObjectService.findMatching(ProposalAdminDetails.class,
                Collections.singletonMap("devProposalNumber", proposalNumber)));
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}

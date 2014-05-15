package org.kuali.kra.s2s.depend;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.location.ProposalSiteService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalSiteService")
public class ProposalSiteServiceImpl implements ProposalSiteService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public String getProposalDunsNumber(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }


        final DevelopmentProposal pd = dataObjectService.find(
                DevelopmentProposal.class, proposalNumber);

        if (pd != null) {
            return pd.getApplicantOrganization().getOrganization().getDunsNumber();
        }
        return null;
    }
}

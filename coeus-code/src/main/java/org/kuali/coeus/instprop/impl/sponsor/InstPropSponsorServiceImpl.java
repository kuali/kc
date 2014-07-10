package org.kuali.coeus.instprop.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.instprop.api.sponsor.InstPropSponsorService;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("instPropSponsorService")
public class InstPropSponsorServiceImpl implements InstPropSponsorService {

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public void updateSponsorProposalNumber(Long institutionalProposalId, String sponsorProposalNumber) {
        if (institutionalProposalId == null) {
            throw new IllegalArgumentException("institutionalProposalId is null");
        }

        if (StringUtils.isBlank(sponsorProposalNumber)) {
            throw new IllegalArgumentException("sponsorProposalNumber is blank");
        }

        final InstitutionalProposal ip = businessObjectService.findByPrimaryKey(InstitutionalProposal.class, Collections.singletonMap("proposalId", institutionalProposalId));

        if (ip != null) {
            if (StringUtils.isBlank(ip.getSponsorProposalNumber())) {
                ip.setSponsorProposalNumber(sponsorProposalNumber);
                businessObjectService.save(ip);
            }
        }
    }
}

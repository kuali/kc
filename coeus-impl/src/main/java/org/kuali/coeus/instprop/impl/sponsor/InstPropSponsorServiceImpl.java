/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.instprop.impl.admin;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsService;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
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

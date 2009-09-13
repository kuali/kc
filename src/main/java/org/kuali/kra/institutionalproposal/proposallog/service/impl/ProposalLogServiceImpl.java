/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.institutionalproposal.proposallog.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogService;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProposalLogServiceImpl implements ProposalLogService {
    
    private BusinessObjectService businessObjectService;
    
    public void mergeProposalLog(String proposalNumber) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", proposalNumber);
        ProposalLog proposalLog = 
            (ProposalLog) this.getBusinessObjectService().findByPrimaryKey(ProposalLog.class, criteria);
        proposalLog.setLogStatus("2");
        this.getBusinessObjectService().save(proposalLog);
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
}

/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;

/**
 * 
 * This class is to be used as a read only implementation of ProposalDevelopmentDocument since
 * transactional documents can't set lookupDefinition in the DD framework.
 * 
 */
public class LookupableDevelopmentProposal extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;
    private Sponsor sponsor;
    private String sponsorCode;
    private String proposalNumber;
    private String title;
  
    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }
    
    public String getSponsorName() {
        if (getSponsor()!= null) {
            return getSponsor().getSponsorName();
        }
        return null;
    }
    
    public Sponsor getSponsor() {
        if (sponsor== null  && !StringUtils.isEmpty(sponsorCode)) {
            this.refreshReferenceObject("sponsor");
        }
        return sponsor;
    }


    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }


    public String getProposalNumber() {
        return proposalNumber;
    }


    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

}

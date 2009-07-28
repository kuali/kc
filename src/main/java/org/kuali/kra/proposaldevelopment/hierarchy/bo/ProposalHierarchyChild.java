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
package org.kuali.kra.proposaldevelopment.hierarchy.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;

public class ProposalHierarchyChild extends KraPersistableBusinessObjectBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6265373411837495670L;
    
    private String proxiedProposalNumber;
    private int proxiedProposalChildHashCode;
    private List<PropScienceKeyword> propScienceKeywords;
    private ProposalPerson principalInvestigator;
    private List<ProposalPerson> investigators;
    private List<ProposalPerson> proposalPersons;
    private List<ProposalSpecialReview> propSpecialReviews;
    
    

    public String getProxiedProposalNumber() {
        return proxiedProposalNumber;
    }



    public void setProxiedProposalNumber(String proxiedProposalNumber) {
        this.proxiedProposalNumber = proxiedProposalNumber;
    }



    public int getProxiedProposalChildHashCode() {
        return proxiedProposalChildHashCode;
    }



    public void setProxiedProposalChildHashCode(int proxiedProposalChildHashCode) {
        this.proxiedProposalChildHashCode = proxiedProposalChildHashCode;
    }



    public List<PropScienceKeyword> getPropScienceKeywords() {
        return propScienceKeywords;
    }



    public void setPropScienceKeywords(List<PropScienceKeyword> propScienceKeywords) {
        this.propScienceKeywords = propScienceKeywords;
    }



    public ProposalPerson getPrincipalInvestigator() {
        return principalInvestigator;
    }



    public void setPrincipalInvestigator(ProposalPerson principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }



    public List<ProposalPerson> getInvestigators() {
        return investigators;
    }



    public void setInvestigators(List<ProposalPerson> investigators) {
        this.investigators = investigators;
    }



    public List<ProposalPerson> getProposalPersons() {
        return proposalPersons;
    }



    public void setProposalPersons(List<ProposalPerson> proposalPersons) {
        this.proposalPersons = proposalPersons;
    }



    public List<ProposalSpecialReview> getPropSpecialReviews() {
        return propSpecialReviews;
    }



    public void setPropSpecialReviews(List<ProposalSpecialReview> propSpecialReviews) {
        this.propSpecialReviews = propSpecialReviews;
    }



    @Override
    protected LinkedHashMap toStringMapper() {
        // TODO Auto-generated method stub
        return null;
    }

}

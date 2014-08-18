/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.location;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the "add congressional district to a proposal site" event.
 */
public class AddProposalCongressionalDistrictEvent extends BasicProposalSiteEvent {
    private List<CongressionalDistrict> congressionalDistricts;
    private CongressionalDistrict congressionalDistrict;
    private String collectionId;
    private String collectionLabel;
    
    public AddProposalCongressionalDistrictEvent(ProposalDevelopmentDocument proposalDevelopmentDocument, List<CongressionalDistrict> congressionalDistricts, CongressionalDistrict congressionalDistrict,
    	String collectionId,String collectionLabel) {
        super(getEventDescription(proposalDevelopmentDocument),proposalDevelopmentDocument,congressionalDistricts);
        this.congressionalDistricts = congressionalDistricts;
        this.congressionalDistrict = congressionalDistrict;
        this.collectionId = collectionId;
        this.collectionLabel = collectionLabel;
    }

    private static String getEventDescription(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        return "adding congressional district to document " + getDocumentId(proposalDevelopmentDocument);
    }

    public List<CongressionalDistrict> getCongressionalDistricts() {
		return congressionalDistricts;
	}

	public CongressionalDistrict getCongressionalDistrict() {
		return congressionalDistrict;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public String getCollectionLabel() {
		return collectionLabel;
	}

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AddCongressionalDistrictRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AddCongressionalDistrictRule)rule).processAddCongressionalDistrictRules(this);
    }
}

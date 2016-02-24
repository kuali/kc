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
package org.kuali.coeus.propdev.impl.location;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

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

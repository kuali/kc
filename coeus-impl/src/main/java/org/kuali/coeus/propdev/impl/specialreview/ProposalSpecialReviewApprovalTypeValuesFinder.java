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
package org.kuali.coeus.propdev.impl.specialreview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.common.impl.compliance.core.SpecialReviewApprovalTypeValuesFinder;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.stereotype.Component;

/**
 * See {@link #getKeyValues()}.
 */
@Component("proposalSpecialReviewApprovalTypeValuesFinder")
public class ProposalSpecialReviewApprovalTypeValuesFinder extends SpecialReviewApprovalTypeValuesFinder {
    
    /**
     * Gets the keyvalue pair for {@link SpecialReviewApprovalType SpecialReviewApprovalType}.
     * Specific to ProposalSpecialReview
     * The key is the approvalTypeCode and the value is the description.
     * 
     * @return a list of {@link KeyValue KeyValue}
     */
    @Override
    public List<KeyValue> getKeyValues(ViewModel model, InputField field) {
    	ProposalDevelopmentDocumentForm proposalDevelopmentForm = (ProposalDevelopmentDocumentForm)model;
    	ProposalSpecialReview proposalSpecialReview = ObjectPropertyUtils.getPropertyValue(model, field.getBindingInfo().getBindingPathPrefix());
        List<KeyValue> approvalTypes = new ArrayList<KeyValue>();
    	Map<String,String> matchingCriteria = new HashMap<String,String>();
        if(proposalSpecialReview != null && proposalSpecialReview.getSpecialReviewTypeCode() != null && 
        		(isIrbSpecialReview(proposalSpecialReview, proposalDevelopmentForm) || isIacucSpecialReview(proposalSpecialReview, proposalDevelopmentForm))){
        	matchingCriteria.put("approvalTypeCode", SpecialReviewApprovalType.NOT_YET_APPLIED);
        }
    	setMatchingCriteria(matchingCriteria);
    	approvalTypes = super.getKeyValues();
        return approvalTypes;
    }

    private boolean isIrbSpecialReview(ProposalSpecialReview proposalSpecialReview, ProposalDevelopmentDocumentForm proposalDevelopmentForm) {
    	return proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.HUMAN_SUBJECTS) && 
    			proposalDevelopmentForm.getSpecialReviewHelper().isIrbProtocolLinkingEnabledForModule();
    }

    private boolean isIacucSpecialReview(ProposalSpecialReview proposalSpecialReview, ProposalDevelopmentDocumentForm proposalDevelopmentForm) {
    	return proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.ANIMAL_USAGE) && 
    			proposalDevelopmentForm.getSpecialReviewHelper().isIacucProtocolLinkingEnabledForModule();
    }

}

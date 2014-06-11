/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

/**
 * See {@link #getKeyValues()}.
 */
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
    	ProposalSpecialReview proposalSpecialReview = ObjectPropertyUtils.getPropertyValue(model, field.getBindingInfo().getBindByNamePrefix());
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

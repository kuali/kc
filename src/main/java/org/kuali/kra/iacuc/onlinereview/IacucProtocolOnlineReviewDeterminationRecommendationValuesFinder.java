/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.common.committee.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.IacucActionsKeyValuesBase;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;

/**
 * Assembles the Protocol Reviewer Types to display in the drop-down menu when
 * reviewing a protocol and selecting a recommendation.
 */
public class IacucProtocolOnlineReviewDeterminationRecommendationValuesFinder extends IacucActionsKeyValuesBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1177665298157090424L;

    public List<KeyValue> getKeyValues() {
        IacucProtocolForm iacucProtocolForm = (IacucProtocolForm)KNSGlobalVariables.getKualiForm();
        String reviewType = iacucProtocolForm.getIacucProtocolDocument().getIacucProtocol().getProtocolSubmission().getProtocolReviewTypeCode();
        Collection<IacucProtocolOnlineReviewDeterminationRecommendation> recommendations = this.getKeyValuesService().findAll(IacucProtocolOnlineReviewDeterminationRecommendation.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (IacucProtocolOnlineReviewDeterminationRecommendation recommendation : recommendations) {
            if (recommendation.getAssocReviewTypeCode() == null || recommendation.getAssocReviewTypeCode().intValue() == 0 ||
                recommendation.getAssocReviewTypeCode().intValue() == Integer.valueOf(reviewType)) {
                keyValues.add(new ConcreteKeyValue(recommendation.getProtocolOnlineReviewDeterminationRecommendationCode().toString(), recommendation.getDescription()));
            }
        }
        return keyValues;
    }
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }
    
}

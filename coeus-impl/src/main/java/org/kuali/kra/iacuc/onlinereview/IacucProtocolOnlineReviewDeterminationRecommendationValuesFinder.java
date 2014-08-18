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
package org.kuali.kra.iacuc.onlinereview;

import org.drools.core.util.StringUtils;
import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucActionsKeyValuesBase;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Assembles the Protocol Reviewer Types to display in the drop-down menu when
 * reviewing a protocol and selecting a recommendation.
 */
public class IacucProtocolOnlineReviewDeterminationRecommendationValuesFinder extends IacucActionsKeyValuesBase {
    

    private static final long serialVersionUID = -1177665298157090424L;

    @Override
    public List<KeyValue> getKeyValues() {
        IacucProtocolDocument iacucProtocolDocument = (IacucProtocolDocument) getDocument();
        String reviewType = iacucProtocolDocument.getIacucProtocol().getProtocolSubmission().getProtocolReviewTypeCode();
        Collection<IacucProtocolOnlineReviewDeterminationRecommendation> recommendations = this.getKeyValuesService().findAll(IacucProtocolOnlineReviewDeterminationRecommendation.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (IacucProtocolOnlineReviewDeterminationRecommendation recommendation : recommendations) {
            if (recommendation.getIacucProtocolReviewTypeCode() != null && recommendation.getIacucProtocolReviewTypeCode().equals(reviewType)) {
                keyValues.add(new ConcreteKeyValue(recommendation.getProtocolOnlineReviewDeterminationRecommendationCode().toString(), recommendation.getIacucProtocolActionType().getDescription()));
            }
        }
        return keyValues;
    }
    
    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return IacucCommitteeService.class;
    }
    
}

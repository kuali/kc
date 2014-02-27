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
package org.kuali.kra.iacuc.onlinereview;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewTypeBase;

public class IacucProtocolOnlineReviewDeterminationTypeRecommendation extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2274982797374476521L;
    
    
    private String iacucProtocolReviewTypeCode;
    private ProtocolReviewTypeBase iacucProtocolReviewType;
    
    private transient String newProtocolReviewTypeCode;  //for values finder on create new page
    
    public IacucProtocolOnlineReviewDeterminationTypeRecommendation() {
        
    }
    
    public String getIacucProtocolReviewTypeCode() {         
        return iacucProtocolReviewTypeCode;
    }

    public void setIacucProtocolReviewTypeCode(String iacucProtocolReviewTypeCode) {
        this.iacucProtocolReviewTypeCode = iacucProtocolReviewTypeCode;
    }

    public ProtocolReviewTypeBase getIacucProtocolReviewType() {
        if (iacucProtocolReviewType == null || !iacucProtocolReviewType.getReviewTypeCode().equals(iacucProtocolReviewTypeCode)) {
            refreshReferenceObject("iacucProtocolReviewType");
        }
        return iacucProtocolReviewType;
    }

    public void setIacucProtocolReviewType(ProtocolReviewTypeBase iacucProtocolReviewType) {
        this.iacucProtocolReviewType = iacucProtocolReviewType;
    }
    
    public String getNewProtocolReviewTypeCode() {         
        return newProtocolReviewTypeCode;
    }
    
    public void setNewProtocolReviewTypeCode(String newProtocolReviewTypeCode) {         
        this.newProtocolReviewTypeCode = newProtocolReviewTypeCode;
    }
    
}

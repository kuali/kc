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

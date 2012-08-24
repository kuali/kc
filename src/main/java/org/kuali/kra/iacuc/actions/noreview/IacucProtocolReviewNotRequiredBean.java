/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.noreview;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.protocol.actions.ActionHelper;
import org.kuali.kra.protocol.actions.noreview.ProtocolReviewNotRequiredBean;

/**
 * This class manages the HTML Elements needed for the review not required panel.
 */
public class IacucProtocolReviewNotRequiredBean extends ProtocolReviewNotRequiredBean {

    private static final long serialVersionUID = -8686091412369007790L;

    /**
     * Constructs a ProtocolReviewNotRequiredBean.
     * @param actionHelper Reference back to the action helper for this bean
     */
    public IacucProtocolReviewNotRequiredBean(ActionHelper actionHelper) {
        super(actionHelper);
    }

    public IacucProtocol getIacucProtocol() {
        return (IacucProtocol)getProtocol();
    }
    
}

/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.irb.onlinereview;

import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewLookupableHelperServiceImplBase;

public class ProtocolOnlineReviewLookupableHelperServiceImpl extends ProtocolOnlineReviewLookupableHelperServiceImplBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8740814934249836927L;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolOnlineReviewLookupableHelperServiceImpl.class);

    @Override
    protected String getProtocolOLRSavedStatusCodeHook() {
        return ProtocolOnlineReviewStatus.SAVED_STATUS_CD;
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "ProtocolOnlineReviewDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "protocolOnlineReviewRedirect.do";
    }
}

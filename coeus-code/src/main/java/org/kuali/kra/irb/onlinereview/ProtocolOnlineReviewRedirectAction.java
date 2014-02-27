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
package org.kuali.kra.irb.onlinereview;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewRedirectActionBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewService;

public class ProtocolOnlineReviewRedirectAction extends ProtocolOnlineReviewRedirectActionBase  {

    @Override
    protected Class<? extends ProtocolBase> getProtocolClass() {
        return Protocol.class;
    }

    @Override
    protected Class<? extends ProtocolOnlineReviewService> getProtocolOnlineReviewServiceClassHook() {
        return org.kuali.kra.irb.onlinereview.ProtocolOnlineReviewService.class;
    }

    protected String getAdminRoleName() {
        return "IRB Administrator";
    }

    @Override
    protected String getProtocolOnlineReviewActionIdHook() {
        return "protocolOnlineReview";
    }
}

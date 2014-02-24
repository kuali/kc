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
package org.kuali.kra.iacuc.specialreview;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.specialreview.impl.lookup.keyvalue.SpecialReviewTypeValuesFinder;

/**
 * Provides a value finder for the Protocol-specific Special Review Types.
 */
public class IacucProtocolSpecialReviewTypeValuesFinder extends SpecialReviewTypeValuesFinder {

    @Override
    public String getModuleCode() {
        return CoeusModule.IRB_MODULE_CODE;
    }

}
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
package org.kuali.kra.iacuc.committee.print;

import org.kuali.kra.common.committee.print.ScheduleTemplatePrintBase;
import org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondenceTemplateService;

public class IacucScheduleTemplatePrint extends ScheduleTemplatePrintBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -9175459734372306511L;
    
    public void setIacucProtocolCorrespondenceTemplateService(IacucProtocolCorrespondenceTemplateService iacucProtocolCorrespondenceTemplateService) {
        this.setProtocolCorrespondenceTemplateService(iacucProtocolCorrespondenceTemplateService);
    }

}

/*
 * Copyright 2005-2014 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;

/**
 * This abstract class has methods that are common to all the versions of EDSF424Supplement form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class EDSF424SupplementBaseGenerator extends S2SBaseFormGenerator {

    protected static final String PROPOSAL_YNQ_NOVICE_APPLICANT = "133";
    protected static final String SPECIAL_REVIEW_CODE = "1";
    protected static final String APPROVAL_TYPE_CODE = "4";
    protected static final int NARRATIVE_TYPE_ED_SF424_SUPPLIMENT = 54;
    protected S2SUtilService s2sUtilService;


    public EDSF424SupplementBaseGenerator() {
        s2sUtilService = KcServiceLocator.getService(S2SUtilService.class);
    }
}

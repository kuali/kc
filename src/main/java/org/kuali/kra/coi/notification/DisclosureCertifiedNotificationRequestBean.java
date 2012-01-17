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
package org.kuali.kra.coi.notification;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.Constants;

public class DisclosureCertifiedNotificationRequestBean extends CoiNotificationRequestBean {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1675582315061417093L;
    public List<CoiUserRole> coiDisclosureReviewers;

    public DisclosureCertifiedNotificationRequestBean(CoiDisclosure coiDisclosure, List<CoiUserRole> coiDisclosureReviewers) {
        super(coiDisclosure, Constants.DISCLOSURE_CERTIFIED_NOTIFICATION, "Disclosure Certified");        
        this.coiDisclosureReviewers = coiDisclosureReviewers;
    }

    public List<CoiUserRole> getCoiDisclosureReviewers() {
        return coiDisclosureReviewers;
    }

    public void setCoiDisclosureReviewers(List<CoiUserRole> coiDisclosureReviewers) {
        this.coiDisclosureReviewers = coiDisclosureReviewers;
    }

}

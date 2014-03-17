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
package org.kuali.kra.coi.notification;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.infrastructure.Constants;

import java.util.List;

public class DisclosureCertifiedNotificationRequestBean extends CoiNotificationRequestBean {


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

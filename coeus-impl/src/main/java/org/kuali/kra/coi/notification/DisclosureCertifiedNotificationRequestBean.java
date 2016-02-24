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

/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.service;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.bo.AdHocRoutePerson;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.AdHocRouteWorkgroup;
import org.kuali.rice.krad.service.impl.DocumentAdHocServiceImpl;

import java.util.List;

/**
 *
 */
public class KcDocumentAdHocServiceImpl extends DocumentAdHocServiceImpl {

    @Override
    public void replaceAdHocsForDocument(String documentNumber, List<AdHocRouteRecipient> adHocRoutingRecipients) {
        if (StringUtils.isBlank(documentNumber)) {
            return;
        }


        QueryResults<AdHocRoutePerson> dbAdHocRoutePersons = dataObjectService.findMatching(AdHocRoutePerson.class,
                        QueryByCriteria.Builder.fromPredicates(
                                PredicateFactory.equal("documentNumber", documentNumber),
                                PredicateFactory.equal("type", AdHocRoutePerson.PERSON_TYPE)));

        QueryResults<AdHocRouteWorkgroup> dbAdHocRouteWorkgroups = dataObjectService.findMatching(AdHocRouteWorkgroup.class,
                QueryByCriteria.Builder.fromPredicates(
                        PredicateFactory.equal("documentNumber", documentNumber),
                        PredicateFactory.equal("type", AdHocRoutePerson.WORKGROUP_TYPE)));

        // Delete persons not included in the current list
        for (AdHocRoutePerson dbAdHocRoutePerson : dbAdHocRoutePersons.getResults()) {
            boolean remove = true;

            if (adHocRoutingRecipients != null) {
                for (AdHocRouteRecipient adHocRouteRecipient : adHocRoutingRecipients) {
                    // if it exists in both lists don't delete
                    if (adHocRouteRecipient.getId().equals(dbAdHocRoutePerson.getId())) {
                        remove = false;
                    }
                }
            }

            if (remove) {
                dataObjectService.delete(dbAdHocRoutePerson);
            }
        }

        // Delete workgroups not included in the current list
        for (AdHocRouteWorkgroup dbAdHocRouteWorkgroup : dbAdHocRouteWorkgroups.getResults()) {
            boolean remove = true;

            if (adHocRoutingRecipients != null) {
                for (AdHocRouteRecipient adHocRouteRecipient : adHocRoutingRecipients) {
                    // if it exists in both lists don't delete
                    if (adHocRouteRecipient.getId().equals(dbAdHocRouteWorkgroup.getId())) {
                        remove = false;
                    }
                }
            }

            if (remove) {
                dataObjectService.delete(dbAdHocRouteWorkgroup);
            }
        }

        if (adHocRoutingRecipients != null) {
            for (AdHocRouteRecipient recipient : adHocRoutingRecipients) {
                recipient.setdocumentNumber(documentNumber);
                dataObjectService.save(recipient);
            }
        }

    }
}

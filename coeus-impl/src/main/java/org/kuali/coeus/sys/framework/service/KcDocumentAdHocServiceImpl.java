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

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
package org.kuali.coeus.propdev.impl.core;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDao;
import org.kuali.coeus.propdev.impl.auth.ProposalDevelopmentDocumentAuthorizer;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("lookupableDevelopmentProposalLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LookupableDevelopmentProposalLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    private static final List <String> VALID_LOOKUP_FIELDS = Arrays.asList("proposalNumber","title","sponsorCode","ownedByUnitNumber","ownedByUnitName","proposalTypeCode");

    private static final Log LOG = LogFactory.getLog(LookupableDevelopmentProposalLookupableHelperServiceImpl.class);

    @Autowired
    @Qualifier("proposalPersonDao")
    private ProposalPersonDao proposalPersonDao;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        // find matching names for potential PI's
        List<String>matchingProposals = new ArrayList<String>();
        Map<String, Object>proposalFields = new HashMap<String, Object>();
        for (String key: fieldValues.keySet()) {
            String value = fieldValues.get(key);
            if (StringUtils.isNotEmpty(value)) {
                if (StringUtils.equals(key, "investigator")) {
                    List<ProposalPerson> proposalPersons = proposalPersonDao.getProposalPersonsByName(value);
                    for (ProposalPerson potentialPerson: proposalPersons) {
                        matchingProposals.add(potentialPerson.getDevelopmentProposal().getProposalNumber());
                    }
                    proposalFields.put("proposalNumber",matchingProposals);
                } else if (VALID_LOOKUP_FIELDS.contains(key)){
                    proposalFields.put(key, fieldValues.get(key));
                }
            }
        }
        List<LookupableDevelopmentProposal> unboundedResults = 
            (List<LookupableDevelopmentProposal>) businessObjectService.findMatching(LookupableDevelopmentProposal.class,proposalFields);
        List<LookupableDevelopmentProposal> filteredResults = filterForPermissionsAndDates(fieldValues, unboundedResults);
        return filteredResults;
    }

    // filter by lower and upper limits on deadline date, and by user permissions
    private List<LookupableDevelopmentProposal> filterForPermissionsAndDates(Map<String, String> fieldValues, List<LookupableDevelopmentProposal> unboundedResults) {
        String deadlineDate = fieldValues.get("deadlineDate");
        Date lowerLimit = null;
        Date upperLimit = null;
        boolean checkDates = true;
        if (StringUtils.isEmpty(deadlineDate)) {
            checkDates = false;
        } else {
            try {
                if (deadlineDate.indexOf("..") > -1) {
                    String temp = deadlineDate.substring(0,10);
                    lowerLimit = new Date((new java.util.Date(temp)).getTime());
                    temp = deadlineDate.substring(12);
                    upperLimit = new Date((new java.util.Date(temp)).getTime());
                } else if (deadlineDate.indexOf("<=") > -1) {
                    String temp = deadlineDate.substring(2);
                    upperLimit = new Date((new java.util.Date(temp)).getTime());
                } else {
                    String temp = deadlineDate.substring(2);
                    lowerLimit = new Date((new java.util.Date(temp)).getTime());
                }
            } catch (Exception e) {
                LOG.warn("Error formatting dates. Original string = [" + deadlineDate + "]");
                lowerLimit = null;
                upperLimit = null;
            }
        }
        ProposalDevelopmentDocumentAuthorizer authorizer = new ProposalDevelopmentDocumentAuthorizer();
        Person user = GlobalVariables.getUserSession().getPerson();
        List<LookupableDevelopmentProposal> results = new ArrayList<LookupableDevelopmentProposal>();
        for (LookupableDevelopmentProposal potentialProposal: unboundedResults) {
            if (!checkDates || dateInRange(potentialProposal.getDeadlineDate(), lowerLimit, upperLimit)) {
                String documentNumber = potentialProposal.getDocumentNumber();
                try {
                    ProposalDevelopmentDocument document = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(documentNumber);
                    if (authorizer.canOpen(document, user)) {
                        results.add(potentialProposal);
                    }
                } catch (WorkflowException e) {
                    LOG.warn("Cannot find Document with header id " + documentNumber);
                }
            }
        }
        return results;
    }  

    private boolean dateInRange(Date checkDate, Date lowerLimit, Date upperLimit) {
        if (checkDate == null) {
            return true;
        }
        if (lowerLimit != null && checkDate.before(lowerLimit)) {
            return false;
        }
        if (upperLimit != null && checkDate.after(upperLimit)) {
            return false;
        }
        return true;
    }
    
    public void setProposalPersonDao(ProposalPersonDao proposalPersonDao) {
        this.proposalPersonDao = proposalPersonDao;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }
}

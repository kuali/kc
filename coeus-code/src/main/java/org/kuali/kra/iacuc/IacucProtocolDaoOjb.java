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
package org.kuali.kra.iacuc;

import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolUnit;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSource;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocation;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchArea;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.protocol.CriteriaFieldHelper;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDaoOjbBase;
import org.kuali.kra.protocol.ProtocolLookupConstants;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * 
 * This class is the implementation for IacucProtocolDao interface.
 */
public class IacucProtocolDaoOjb extends ProtocolDaoOjbBase<IacucProtocol> implements OjbCollectionAware, IacucProtocolDao {
    
    /**
     * The ACTIVE_PROTOCOL_STATUS_CODES contains the various active status codes for a protocol. 
     */
    private static final Collection<String> ACTIVE_PROTOCOL_STATUS_CODES = Arrays.asList(new String[] {"200", "201"});
    
    /**
     * The REVISION_REQUESTED_PROTOCOL_STATUS_CODES contains the various status codes for protocol revision requests.
     */
    private static final Collection<String> REVISION_REQUESTED_PROTOCOL_STATUS_CODES = Arrays.asList(new String[] {"104", "107"});
    
    /**
     * The APPROVED_SUBMISSION_STATUS_CODE contains the status code of approved protocol submissions.
     */
    private static final Collection<String> APPROVED_SUBMISSION_STATUS_CODES = Arrays.asList(new String[] {"200", "213"});
    
    /**
     * The REVISION_REQUESTED_PROTOCOL_ACTION_TYPE_CODES contains the protocol action codes for the protocol revision requests. 
     */
    private static final Collection<String> REVISION_REQUESTED_PROTOCOL_ACTION_TYPE_CODES = Arrays.asList(new String[] {"209", "211", "213"});
    
   
    private static final Collection<String> PENDING_AMENDMENT_RENEWALS_STATUS_CODES = Arrays.asList(new String[] {"100", "101", "103", "104", "107", "108", "205", "206", "207"});
    
    
    
    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return IacucProtocol.class;
    }

    @Override
    protected Class<? extends ProtocolPersonBase> getProtocolPersonBOClassHook() {
        return IacucProtocolPerson.class;
    }

    @Override
    protected Class<? extends ProtocolUnitBase> getProtocolUnitBOClassHook() {
        return IacucProtocolUnit.class;
    }

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return IacucProtocolSubmission.class;
    }

    @Override
    protected List<CriteriaFieldHelper> getCriteriaFields() {
        List<CriteriaFieldHelper> criteriaFields = new ArrayList<CriteriaFieldHelper>();
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.KEY_PERSON, 
                ProtocolLookupConstants.Property.PERSON_NAME, 
                IacucProtocolPerson.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.INVESTIGATOR, 
                ProtocolLookupConstants.Property.PERSON_NAME, 
                IacucProtocolPerson.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.FUNDING_SOURCE, 
                ProtocolLookupConstants.Property.FUNDING_SOURCE_NUMBER, 
                IacucProtocolFundingSource.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID,
                ProtocolLookupConstants.Property.ORGANIZATION_ID, 
                IacucProtocolLocation.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE,
                ProtocolLookupConstants.Property.RESEARCH_AREA_CODE, 
                IacucProtocolResearchArea.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.SPECIES_CODE,
                ProtocolLookupConstants.Property.SPECIES_CODE, 
                IacucProtocolSpecies.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.EXCEPTION_CATEGORY_CODE,
                ProtocolLookupConstants.Property.EXCEPTION_CATEGORY_CODE, 
                IacucProtocolException.class));
        return criteriaFields;
    }

    @Override
    protected Collection<String> getApprovedSubmissionStatusCodesHook() {
        return APPROVED_SUBMISSION_STATUS_CODES;
    }

    @Override
    protected Collection<String> getActiveProtocolStatusCodesHook() {
        return ACTIVE_PROTOCOL_STATUS_CODES;
    }

    @Override
    protected Collection<String> getRevisionRequestedProtocolActionTypeCodesHook() {
        return REVISION_REQUESTED_PROTOCOL_ACTION_TYPE_CODES;
    }

    @Override
    protected Collection<String> getRevisionRequestedProtocolStatusCodesHook() {
        return REVISION_REQUESTED_PROTOCOL_STATUS_CODES;
    }

    @Override
    protected Class<? extends ProtocolActionBase> getProtocolActionBOClassHoook() {
        return IacucProtocolAction.class;
    }

    @Override
    protected void initRoleListsHook(List<String> investigatorRoles, List<String> personRoles) {
        investigatorRoles.add("PI");
        investigatorRoles.add("COI");
        personRoles.add("SP");
        personRoles.add("CRC");    
    }

    @Override
    protected Collection<String> getPendingAmendmentRenewalsProtocolStatusCodesHook() {
        return PENDING_AMENDMENT_RENEWALS_STATUS_CODES;
    }
    
    
}

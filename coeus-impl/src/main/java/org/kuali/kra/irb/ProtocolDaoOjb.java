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
package org.kuali.kra.irb;

import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.personnel.ProtocolUnit;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.irb.protocol.location.ProtocolLocation;
import org.kuali.kra.irb.protocol.research.ProtocolResearchArea;
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
 * This class is the implementation for ProtocolDao interface.
 */
class ProtocolDaoOjb extends ProtocolDaoOjbBase<Protocol> implements OjbCollectionAware, ProtocolDao {
    /**
     * The APPROVED_SUBMISSION_STATUS_CODE contains the status code of approved protocol submissions (i.e. 203).
     */
    private static final Collection<String> APPROVED_SUBMISSION_STATUS_CODES = Arrays.asList(new String[] {"203"});
    /**
     * The ACTIVE_PROTOCOL_STATUS_CODES contains the various active status codes for a protocol.
     *   <li> 200 - Active, open to enrollment
     *   <li> 201 - Active, closed to enrollment
     *   <li> 202 - Active, data analysis only 
     */
    private static final Collection<String> ACTIVE_PROTOCOL_STATUS_CODES = Arrays.asList(new String[] {"200", "201", "202"});
    /**
     * The REVISION_REQUESTED_PROTOCOL_ACTION_TYPE_CODES contains the protocol action codes for the protocol revision requests.
     *   <li> 202 - Specific Minor Revision
     *   <li> 203 - Substantive Revision Requested 
     */
    private static final Collection<String> REVISION_REQUESTED_PROTOCOL_ACTION_TYPE_CODES = Arrays.asList(new String[] {"202", "203"});

    /**
     * The REVISION_REQUESTED_PROTOCOL_STATUS_CODES contains the various status codes for protocol revision requests.
     *   <li> 102 - Specific Minor Revision
     *   <li> 104 - Substantive Revision Requested
     */
    private static final Collection<String> REVISION_REQUESTED_PROTOCOL_STATUS_CODES = Arrays.asList(new String[] {"102", "104"});

    private static final Collection<String> PENDING_AMENDMENT_RENEWALS_STATUS_CODES = Arrays.asList(new String[]{"100", "101", "102", "103", "104", "105", "106"});
    
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
        return org.kuali.kra.irb.actions.ProtocolAction.class;
    }

    @Override
    protected void initRoleListsHook(List<String> investigatorRoles, List<String> personRoles) {
        investigatorRoles.add("PI");
        investigatorRoles.add("COI");
        personRoles.add("SP");
        personRoles.add("CA");
        personRoles.add("CRC");        
    }

    @Override
    protected Collection<String> getPendingAmendmentRenewalsProtocolStatusCodesHook() {
        return PENDING_AMENDMENT_RENEWALS_STATUS_CODES;
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return Protocol.class;
    }

    @Override
    protected Class<? extends ProtocolPersonBase> getProtocolPersonBOClassHook() {
        return ProtocolPerson.class;
    }

    @Override
    protected Class<? extends ProtocolUnitBase> getProtocolUnitBOClassHook() {
        return ProtocolUnit.class;
    }

    @Override
    protected Class<? extends ProtocolSubmissionBase> getProtocolSubmissionBOClassHook() {
        return ProtocolSubmission.class;
    }

    @Override
    protected List<CriteriaFieldHelper> getCriteriaFields() {
        List<CriteriaFieldHelper> criteriaFields = new ArrayList<CriteriaFieldHelper>();
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.KEY_PERSON, 
                ProtocolLookupConstants.Property.PERSON_NAME, 
                ProtocolPerson.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.INVESTIGATOR, 
                ProtocolLookupConstants.Property.PERSON_NAME, 
                ProtocolPerson.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.FUNDING_SOURCE, 
                ProtocolLookupConstants.Property.FUNDING_SOURCE_NUMBER, 
                ProtocolFundingSource.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.PERFORMING_ORGANIZATION_ID,
                ProtocolLookupConstants.Property.ORGANIZATION_ID, 
                ProtocolLocation.class));
        criteriaFields.add(new CriteriaFieldHelper(ProtocolLookupConstants.Property.RESEARCH_AREA_CODE,
                ProtocolLookupConstants.Property.RESEARCH_AREA_CODE, 
                ProtocolResearchArea.class));
        return criteriaFields;
    }
}

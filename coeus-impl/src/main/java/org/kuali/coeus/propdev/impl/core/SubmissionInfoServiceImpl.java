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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.propdev.api.core.SubmissionInfoService;
import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("submissionInfoService")
public class SubmissionInfoServiceImpl implements SubmissionInfoService {

    private static final String FEDERAL_ID_COMES_FROM_CURRENT_AWARD = "FEDERAL_ID_COMES_FROM_CURRENT_AWARD";

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("versionHistoryService")
    private VersionHistoryService versionHistoryService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;

    @Autowired
    @Qualifier("legacyDataAdapter")
    private LegacyDataAdapter legacyDataAdapter;

    @Autowired
    @Qualifier("proposalTypeService")
    private ProposalTypeService proposalTypeService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;


    @Override
    public String getFederalId(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        String federalIdComesFromAwardStr = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class,
                FEDERAL_ID_COMES_FROM_CURRENT_AWARD);
        Boolean federalIdComesFromAward = federalIdComesFromAwardStr != null && federalIdComesFromAwardStr.equalsIgnoreCase("Y");
        DevelopmentProposal proposal = getDataObjectService().find(DevelopmentProposal.class, proposalNumber);
        Award currentAward = null;
        String federalId = null;
        if (StringUtils.isNotBlank(proposal.getCurrentAwardNumber())) {
            currentAward = getProposalCurrentAwardVersion(proposal.getCurrentAwardNumber());
        }
        InstitutionalProposal institutionalProposal = null;
        if (StringUtils.isNotBlank(proposal.getContinuedFrom())) {
            institutionalProposal = getProposalContinuedFromVersion(proposal.getContinuedFrom());
        }
        if (isProposalTypeRenewalRevisionContinuation(proposal.getProposalTypeCode())) {
            if (!StringUtils.isBlank(proposal.getSponsorProposalNumber())) {
                federalId = proposal.getSponsorProposalNumber();
            }
            else if (currentAward != null && !StringUtils.isBlank(currentAward.getSponsorAwardNumber()) && federalIdComesFromAward) {
                federalId = currentAward.getSponsorAwardNumber();
            }
            else {
                return null;
            }
        }
        else if (isProposalTypeNew(proposal.getProposalTypeCode())
                && (proposal.getS2sOpportunity() != null && isSubmissionTypeChangeCorrected(proposal.getS2sOpportunity()
                .getS2sSubmissionTypeCode()))) {
            if (!StringUtils.isBlank(proposal.getSponsorProposalNumber())) {
                federalId = proposal.getSponsorProposalNumber();
            }
            else if (institutionalProposal != null) {
                federalId = getGgTrackingIdFromProposal(institutionalProposal.getProposalId());
            }
        }
        else if (isProposalTypeResubmission(proposal.getProposalTypeCode())) {
            if (!StringUtils.isBlank(proposal.getSponsorProposalNumber())) {
                federalId = proposal.getSponsorProposalNumber();
            }
            else if (institutionalProposal != null && !StringUtils.isBlank(institutionalProposal.getSponsorProposalNumber())) {
                federalId = institutionalProposal.getSponsorProposalNumber();
            }
            if (isProposalTypeResubmission(proposal.getProposalTypeCode())) {
                if (proposal.getSponsorCode().equals(
                        this.getParameterService().getParameterValueAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                                ParameterConstants.ALL_COMPONENT, KeyConstants.NSF_SPONSOR_CODE))) {
                    return null;
                }
            }
        }
        if (federalId != null && getSponsorHierarchyService().isSponsorNihMultiplePi(proposal)) {
            return fromatFederalId(federalId);
        }
        return federalId;
    }

    @Override
    public String getGgTrackingIdFromProposal(Long proposalId) {
        if (proposalId == null) {
            throw new IllegalArgumentException("proposalId is null");
        }

        DevelopmentProposal newestDevProp = getNewestDevPropFromInstProp(proposalId);
        if (newestDevProp != null && newestDevProp.getS2sOpportunity() != null) {
            S2sAppSubmission appSubmission = null;
            int submissionNo = 0;
            for (S2sAppSubmission s2AppSubmission : newestDevProp.getS2sAppSubmission()) {
                if (s2AppSubmission.getSubmissionNumber() > submissionNo
                        && StringUtils.isNotBlank(s2AppSubmission.getGgTrackingId())) {
                    appSubmission = s2AppSubmission;
                    submissionNo = s2AppSubmission.getSubmissionNumber();
                }
            }
            if (appSubmission != null) {
                return appSubmission.getGgTrackingId();
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getProposalCurrentAwardSponsorAwardNumber(String currentAwardNumber) {
        if (StringUtils.isBlank(currentAwardNumber)) {
            throw new IllegalArgumentException("currentAwardNumber is blank");
        }

        Award award = getProposalCurrentAwardVersion(currentAwardNumber);
        return award != null ? award.getSponsorAwardNumber() : null;
    }

    protected DevelopmentProposal getNewestDevPropFromInstProp(Long proposalId) {
        final List<ProposalAdminDetails> details = getDataObjectService().findMatching(ProposalAdminDetails.class,
                QueryByCriteria.Builder
                        .andAttributes(Collections.singletonMap("instProposalId", proposalId))
                        .setOrderByAscending("devProposalNumber")
                        .build()).getResults();

        if (details.size() > 1) {
            ProposalAdminDetails curDetail = details.get(details.size() - 2);
            DevelopmentProposal proposal = getDataObjectService().find(DevelopmentProposal.class, curDetail.getDevProposalNumber());
            List<S2sAppSubmission> s2sSubmissionDetails = getDataObjectService().findMatching(S2sAppSubmission.class,
                    QueryByCriteria.Builder
                            .andAttributes(Collections.singletonMap("proposalNumber", proposal.getProposalNumber()))
                            .setOrderByAscending("proposalNumber")
                            .build()).getResults();
            proposal.setS2sAppSubmission(new ArrayList<>(s2sSubmissionDetails));
            return proposal;
        }
        if (details.size() == 1) {
            ProposalAdminDetails curDetail = details.get(0);
            DevelopmentProposal proposal = getDataObjectService().find(DevelopmentProposal.class, curDetail.getDevProposalNumber());
            List<S2sAppSubmission> s2sSubmissionDetails = getDataObjectService().findMatching(S2sAppSubmission.class,
                    QueryByCriteria.Builder
                            .andAttributes(Collections.singletonMap("proposalNumber", proposal.getProposalNumber()))
                            .setOrderByAscending("proposalNumber")
                            .build()).getResults();
            proposal.setS2sAppSubmission(new ArrayList<>(s2sSubmissionDetails));
            return proposal;

        }
        return null;
    }

    public InstitutionalProposal getProposalContinuedFromVersion(String continuedFromProposalNumber) {
        VersionHistory vh = getVersionHistoryService().findActiveVersion(InstitutionalProposal.class, continuedFromProposalNumber);
        InstitutionalProposal ip = null;

        if (vh != null) {
            ip = (InstitutionalProposal) vh.getSequenceOwner();
        }
        else if (StringUtils.isNotEmpty(continuedFromProposalNumber)) {
            HashMap<String, String> valueMap = new HashMap<String, String>();
            valueMap.put("proposalNumber", continuedFromProposalNumber);
            List<InstitutionalProposal> proposals = (List<InstitutionalProposal>) getLegacyDataAdapter().findMatching(InstitutionalProposal.class,valueMap);
            if (proposals != null && !proposals.isEmpty()) {
                ip = proposals.get(0);
            }
        }
        return ip;
    }

    @Override
    public String getProposalContinuedFromVersionSponsorProposalNumber(String continuedFromProposalNumber) {
        if (StringUtils.isBlank(continuedFromProposalNumber)) {
            throw new IllegalArgumentException("continuedFromProposalNumber is blank");
        }

        InstitutionalProposal ip = getProposalContinuedFromVersion(continuedFromProposalNumber);

        return ip != null ? ip.getSponsorProposalNumber() : null;
    }

    @Override
    public Long getProposalContinuedFromVersionProposalId(String continuedFromProposalNumber) {
        if (StringUtils.isBlank(continuedFromProposalNumber)) {
            throw new IllegalArgumentException("continuedFromProposalNumber is blank");
        }

        InstitutionalProposal ip = getProposalContinuedFromVersion(continuedFromProposalNumber);

        return ip != null ? ip.getProposalId() : null;
    }

    protected boolean isSubmissionTypeChangeCorrected(String submissionTypeCode) {
        return StringUtils
                .equalsIgnoreCase(submissionTypeCode, this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.S2S_SUBMISSIONTYPE_CHANGEDCORRECTED));
    }

    protected boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
    	return getProposalTypeService().isProposalTypeRenewalRevisionContinuation(proposalTypeCode);
    }

    /**
     * Is the Proposal Type set to Resubmission?
     *
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    protected boolean isProposalTypeResubmission(String proposalTypeCode) {
        return !StringUtils.isEmpty(proposalTypeCode) && (proposalTypeCode.equals(getProposalTypeService().getResubmissionProposalTypeCode()));
    }

    /**
     * Is the Proposal Type set to New?
     *
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    protected boolean isProposalTypeNew(String proposalTypeCode) {
        return !StringUtils.isEmpty(proposalTypeCode) && (proposalTypeCode.equals(getProposalTypeService().getNewProposalTypeCode()));
    }


    /**
     *
     * This method is to format sponsor award number assume sponsor award number format is like this : 5-P01-ES05622-09, it should
     * be formatted to ES05622
     *
     * @param federalId
     * @return
     */
    protected String fromatFederalId(String federalId) {
        if (federalId.length() > 7) {
            int in = federalId.indexOf('-', 8);
            if (in != -1)
                federalId = federalId.substring(6, in);
        }
        return federalId;
    }

    protected Award getProposalCurrentAwardVersion(String currentAwardNumber) {
        VersionHistory vh = getVersionHistoryService().findActiveVersion(Award.class, currentAwardNumber);
        Award award = null;

        if (vh != null) {
            award = (Award) vh.getSequenceOwner();
        }
        else {
            HashMap<String, String> valueMap = new HashMap<String, String>();
            valueMap.put("awardNumber", currentAwardNumber);
            List<Award> awards = (List) getBusinessObjectService().findMatching(Award.class, valueMap);
            if (awards != null && !awards.isEmpty()) {
                award = awards.get(0);
            }
        }
        return award;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

	public ProposalTypeService getProposalTypeService() {
		return proposalTypeService;
	}

	public void setProposalTypeService(ProposalTypeService proposalTypeService) {
		this.proposalTypeService = proposalTypeService;
	}

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}

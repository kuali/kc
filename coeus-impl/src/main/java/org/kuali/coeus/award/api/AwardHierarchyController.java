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
package org.kuali.coeus.award.api;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyBean;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposalBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSessionUtils;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping(value="/api/v2")
@Controller("awardHierarchyController")
public class AwardHierarchyController extends AwardControllerBase {

    @Autowired
    @Qualifier("awardDao")
    private AwardDao awardDao;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("versionHistoryService")
    private VersionHistoryService versionHistoryService;

    @Autowired
    @Qualifier("institutionalProposalService")
    private InstitutionalProposalService institutionalProposalService;

    @RequestMapping(method= RequestMethod.POST, value="/awards/{awardId}/child",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardDto createChild(@RequestBody AwardDto awardDto, @PathVariable Long awardId) throws Exception {
        commonApiService.clearErrors();
        Award award = getAwardDao().getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }

        VersionHistory activeVersionHistory = versionHistoryService.findActiveVersion(Award.class, award.getAwardNumber());
        if(activeVersionHistory == null) {
            throw new UnprocessableEntityException("A child cannot be copied from an award that has no active versions.");
        }
        AwardHierarchyBean awardHierarchyBean = new AwardHierarchyBean();
        awardHierarchyBean.init(award.getAwardNumber());
        AwardHierarchy rootNode = awardHierarchyBean.getRootNode(award.getAwardNumber());
        AwardHierarchy newChildNode = awardHierarchyBean.getNewNodeBasedOnParent(award.getAwardNumber(), rootNode);
        AwardDocument awardDocument = createDocument(newChildNode, awardDto);
        translateCollections(awardDto, awardDocument);
        changeDates(awardDocument.getAward(), awardDto);
        addFundingProposals(awardDto, awardDocument.getAward());
        awardDocument.getAward().setAwardInMultipleNodeHierarchy(Boolean.TRUE);

        commonApiService.saveDocument(awardDocument);
        return commonApiService.convertObject(awardDocument.getAward(), AwardDto.class);
    }

    protected AwardDocument createDocument(AwardHierarchy awardNode, AwardDto awardDto) throws WorkflowException {
        businessObjectService.save(awardNode);
        AwardDocument awardDocument = (AwardDocument) documentService.getNewDocument(AwardDocument.class);
        UserSessionUtils.addWorkflowDocument(GlobalVariables.getUserSession(),
                awardDocument.getDocumentHeader().getWorkflowDocument());
        Award newChildAward = awardNode.getAward();
        commonApiService.updateDataObjectFromDto(newChildAward, awardDto);
        defaultValues(newChildAward, awardDto);

        awardDocument.setAward(newChildAward);
        return awardDocument;
    }

    protected AwardDocument getAwardDocument(Long documentNumber) {
        Document document = getDocument(documentNumber);
        AwardDocument awardDocument;
        if (document instanceof AwardDocument) {
            awardDocument = (AwardDocument) document;
        } else {
            throw new ResourceNotFoundException("The docId used in the request was not of type " + document.getClass().getName());
        }
        return awardDocument;
    }

    public void addFundingProposals(AwardDto awardDto, Award award) {
        award.setFundingProposals(new ArrayList<>());
        if(CollectionUtils.isNotEmpty(awardDto.getFundingProposals())) {
            awardDto.getFundingProposals().stream().forEach(awardFundingProposalDto -> {
                AwardFundingProposalBean fundingProposalBean = new AwardFundingProposalBean();
                fundingProposalBean.setMergeTypeCode(awardFundingProposalDto.getMergeTypeCode());
                InstitutionalProposal institutionalProposal = institutionalProposalService.getInstitutionalProposal(awardFundingProposalDto.getProposalId().toString());
                if(institutionalProposal == null) {
                    throw new UnprocessableEntityException("The funding proposal with proposal id " + awardFundingProposalDto.getProposalId() + " cannot be located");
                }
                fundingProposalBean.setNewFundingProposal(institutionalProposal);
                fundingProposalBean.validateAndPerformFeed(new ArrayList<>(), award);
            });
        }
    }

    protected Document getDocument(Long documentNumber) {
        return commonApiService.getDocumentFromDocId(documentNumber);
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setCommonApiService(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected void defaultValues(Award award, AwardDto awardDto) {
        if(award.getAwardAmountInfos() == null) award.initializeAwardAmountInfoObjects();
        if(award.getAwardCostShares() == null) award.setAwardCostShares(new ArrayList<>());
        if(award.getApprovedForeignTravelTrips() == null) award.setApprovedForeignTravelTrips(new ArrayList<>());
        if(award.getFundingProposals() == null) award.setFundingProposals(new ArrayList<>());
        if(award.getAllFundingProposals() == null) award.setAllFundingProposals(new ArrayList<>());
        if(award.getAwardComments() == null)  award.setAwardComments(new ArrayList<>());
        if(award.getAwardReportTermItems() == null) award.setAwardReportTermItems(new ArrayList<>());
        if(award.getAwardSponsorTerms() == null) award.setAwardSponsorTerms(new ArrayList<>());
        if(award.getSponsorContacts() == null) award.setSponsorContacts(new ArrayList<>());
        if(award.getAwardFandaRate() == null) award.setAwardFandaRate(new ArrayList<>());
        if(award.getAwardDirectFandADistributions() == null) award.setAwardDirectFandADistributions(new ArrayList<>());
        if(award.getAwardApprovedSubawards() == null) award.setAwardApprovedSubawards(new ArrayList<>());
        if(award.getKeywords() == null) award.setKeywords(new ArrayList<>());
        if(award.getProjectPersons() == null) award.setProjectPersons(new ArrayList<>());
        if(award.getAwardUnitContacts() == null) award.setAwardUnitContacts(new ArrayList<>());
        if(award.getSpecialReviews() == null) award.setSpecialReviews(new ArrayList<>());
        if(award.getApprovedEquipmentItems() == null) award.setApprovedEquipmentItems(new ArrayList<>());
        if(award.getPaymentScheduleItems() == null) award.setPaymentScheduleItems(new ArrayList<>());
        if(award.getAwardTransferringSponsors() == null) award.setAwardTransferringSponsors(new ArrayList<>());
        if(award.getAwardCloseoutItems() == null) award.setAwardCloseoutItems(new ArrayList<>());
        if(award.getAwardCloseoutNewItems() == null) award.setAwardCloseoutNewItems(new ArrayList<>());
        if(award.getAwardNotepads() == null) award.setAwardNotepads(new ArrayList<>());
        if(award.getAwardAttachments() == null) award.setAttachments(new ArrayList<>());
        if(award.getAwardBudgetLimits() == null) award.setAwardBudgetLimits(new ArrayList<>());
        if(award.getAwardCustomDataList() == null) award.setAwardCustomDataList(new ArrayList<>());

        award.setProjectEndDate(awardDto.getProjectEndDate());
        if(award.getAwardNumber() == null) award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
        award.setSequenceNumber(1);
        award.setApprovedEquipmentIndicator(Award.NO_FLAG);
        award.setApprovedForeignTripIndicator(Award.NO_FLAG);
        award.setSubContractIndicator(Award.NO_FLAG);
        award.setCostSharingIndicator(Award.NO_FLAG);
        award.setIdcIndicator(Award.NO_FLAG);
        award.setPaymentScheduleIndicator(Award.NO_FLAG);
        award.setScienceCodeIndicator(Award.NO_FLAG);
        award.setSpecialReviewIndicator(Award.NO_FLAG);
        award.setTransferSponsorIndicator(Award.NO_FLAG);
        award.setCurrentActionComments("");
        award.setNewVersion(false);
        award.setAwardSequenceStatus(VersionStatus.PENDING.name());
    }

    public AwardDao getAwardDao() {
        return awardDao;
    }

}

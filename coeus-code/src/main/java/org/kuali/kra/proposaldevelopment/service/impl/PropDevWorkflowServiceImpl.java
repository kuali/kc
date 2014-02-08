package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.proposaldevelopment.bo.CoPiInfoDO;
import org.kuali.kra.proposaldevelopment.bo.CostShareInfoDO;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalDevelopmentApproverViewDO;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.PropDevWorkflowService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PropDevWorkflowServiceImpl implements PropDevWorkflowService {

    private ProposalDevelopmentService proposalService;
    private DocumentHelperService documentHelperService;

    public ProposalDevelopmentApproverViewDO populateApproverViewDO (ProposalDevelopmentForm proposalDevelopmentForm) {

        ProposalDevelopmentApproverViewDO approverViewDO = new ProposalDevelopmentApproverViewDO();
        DevelopmentProposal proposal = proposalDevelopmentForm.getProposalDevelopmentDocument().getDevelopmentProposal();
        Budget budget = proposalService.getFinalBudget(proposal);

        int numberOfCostShare = 0;
        int numberOfCoPI = 0;

        /* populate PI info */
        List<CoPiInfoDO> coPiInfos = new ArrayList<CoPiInfoDO>();
        coPiInfos = proposalService.getCoPiPiInfo(proposal);
        approverViewDO.setCoPiInfos(coPiInfos);
        if (coPiInfos != null) {
            numberOfCoPI = coPiInfos.size();
        }

        /* populate cost share info */
        List<CostShareInfoDO> costShareInfos = new ArrayList<CostShareInfoDO>();
        if (budget != null) {
            costShareInfos = proposalService.getCostShareInfo(budget);
            approverViewDO.setCostShareInfos(costShareInfos);
            if (costShareInfos != null) {
                numberOfCostShare = costShareInfos.size();
            }
        }

        /* populate budget cost info */
        if (budget != null) {
            approverViewDO.setDirectCost(budget.getTotalDirectCost());
            approverViewDO.setIndirectCost(budget.getTotalIndirectCost());
            approverViewDO.setTotalCost(budget.getTotalCost());
        }

        /* Fill the gap between CoPI number and Cost Share Number for JSP rendering purpose */
        if (numberOfCoPI > numberOfCostShare) {
            for (int i=0; i < numberOfCoPI - numberOfCostShare; i++) {
                CostShareInfoDO costShareInfo = new CostShareInfoDO();
                costShareInfos.add(costShareInfo);
            }
        } else if (numberOfCoPI < numberOfCostShare) {
            for (int i=0; i < numberOfCostShare - numberOfCoPI; i++) {
                CoPiInfoDO coPiInfo = new CoPiInfoDO();
                coPiInfos.add(coPiInfo);
            }
        }

        /* populate proposal info */
        approverViewDO.setActivityType(proposal.getActivityType().getDescription());
        approverViewDO.setDueDate(proposal.getDeadlineDate());
        approverViewDO.setStartDate(proposal.getRequestedStartDateInitial());
        approverViewDO.setEndDate(proposal.getRequestedEndDateInitial());
        approverViewDO.setProjectTitle(proposal.getTitle());
        approverViewDO.setLeadUnit(proposal.getOwnedByUnitNumber());
        approverViewDO.setProposalNumber(proposal.getProposalNumber());
        approverViewDO.setProposalType(proposal.getProposalType().getDescription());
        approverViewDO.setSponsorName(proposal.getSponsorName());
        approverViewDO.setPiName(proposal.getPrincipalInvestigatorName());

        return approverViewDO;
    }

    /* Check to see if the current user can perform workflow action in proposal development document */
    public boolean canPerformWorkflowAction(ProposalDevelopmentDocument document) {

        // Not from the doc handler, don't show the approver view
        if (document.getDocumentHeader().getDocumentNumber() == null) {
            return false;
        }

        DocumentAuthorizer documentAuthorizer = documentHelperService.getDocumentAuthorizer(document);

        Person user = GlobalVariables.getUserSession().getPerson();
        Set<String> documentActions = documentAuthorizer.getDocumentActions(document, user, null);

        boolean canApprove= documentActions.contains(KRADConstants.KUALI_ACTION_CAN_APPROVE);
        boolean canDisapprove = documentActions.contains(KRADConstants.KUALI_ACTION_CAN_DISAPPROVE);

        return canApprove || canDisapprove;
    }

    public ProposalDevelopmentService getProposalService() {
        return proposalService;
    }

    public void setProposalService(ProposalDevelopmentService proposalService) {
        this.proposalService = proposalService;
    }

    public DocumentHelperService getDocumentHelperService() {
        return documentHelperService;
    }

    public void setDocumentHelperService(DocumentHelperService documentHelperService) {
        this.documentHelperService = documentHelperService;
    }
}

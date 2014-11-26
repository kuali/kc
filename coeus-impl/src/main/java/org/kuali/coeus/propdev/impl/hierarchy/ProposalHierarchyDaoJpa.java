package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatus;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.DataObjectService;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

public class ProposalHierarchyDaoJpa implements ProposalHierarchyDao {

    private DataObjectService dataObjectService;

    /*
        select * from EPS_PROP_PERSON_BIO where PROPOSAL_NUMBER IN (
    select PROPOSAL_NUMBER from EPS_PROPOSAL where HIERARCHY_PROPOSAL_NUMBER =
    (select HIERARCHY_PROPOSAL_NUMBER from EPS_PROPOSAL where PROPOSAL_NUMBER = '13'))
    AND PERSON_ID = '10000000018';
         */
    public boolean personInMultipleChildProposals(String personId, String hierarchyProposalNumber) {
        List<String> childProposalNumbers = getHierarchyChildProposalNumbers(hierarchyProposalNumber);
        if (childProposalNumbers.isEmpty()) {
            return false;
        }
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(equal("personId", personId));
        predicates.add(in("developmentProposal.proposalNumber", getHierarchyChildProposalNumbers(hierarchyProposalNumber)));

        Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setCountFlag(CountFlag.ONLY);
        builder.setPredicates(preds);

        return getDataObjectService().findMatching(ProposalPerson.class, builder.build()).getTotalRowCount() > 1;
    }

    //select PROPOSAL_NUMBER from EPS_PROPOSAL where HIERARCHY_PROPOSAL_NUMBER =18
    public List<DevelopmentProposal> getHierarchyChildProposals(String parentProposalNumber) {
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();

        predicates.add(equal("hierarchyParentProposalNumber", parentProposalNumber));

        Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setPredicates(preds);

        QueryResults<DevelopmentProposal> results = getDataObjectService().findMatching(DevelopmentProposal.class,
                builder.build());
        return results.getResults();
    }

    @Override
    public List<String> getHierarchyChildProposalNumbers(String parentProposalNumber) {
        List<DevelopmentProposal> proposals = getHierarchyChildProposals(parentProposalNumber);

        List<String> proposalNumbers = new ArrayList<String>();
        for(DevelopmentProposal proposal : proposals) {
            proposalNumbers.add(proposal.getProposalNumber());
        }

        return proposalNumbers;
    }

    public List<ProposalBudgetStatus> getHierarchyChildProposalBudgetStatuses(String parentProposalNumber) {
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(in("proposalNumber", getHierarchyChildProposals(parentProposalNumber)));

        Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setPredicates(preds);

        QueryResults<ProposalBudgetStatus> results = getDataObjectService().findMatching(ProposalBudgetStatus.class,
                builder.build());
        return results.getResults();
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

}

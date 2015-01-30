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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.budget.ProposalBudgetStatus;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.rice.core.api.criteria.CountFlag;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kuali.rice.core.api.criteria.PredicateFactory.*;

@Component("proposalHierarchyDao")
public class ProposalHierarchyDaoJpa implements ProposalHierarchyDao {

	@Autowired
	@Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("kcEntityManager")
    private EntityManager entityManager;

    private String PROPOSAL_STATE_QUERY = "SELECT a.proposalState from DevelopmentProposal a where a.proposalNumber = :proposalNumber";

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
        predicates.add(in("developmentProposal.proposalNumber", childProposalNumbers));

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

    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, String> pk = new HashMap<String, String>();
        pk.put("proposalNumber", proposalNumber);
        return (DevelopmentProposal) (dataObjectService.findUnique(DevelopmentProposal.class, QueryByCriteria.Builder.forAttribute("proposalNumber", proposalNumber).build()));
    }

    public String getProposalState(String proposalNumber) {
        ProposalState state = (ProposalState) entityManager.createQuery(PROPOSAL_STATE_QUERY).setParameter("proposalNumber", proposalNumber).getSingleResult();
        return state == null ? "" : state.getDescription();
     }

    public List<ProposalPerson> isPersonOnProposal(String proposalNumber, String personId) {
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(equal("developmentProposal.proposalNumber", proposalNumber));
        predicates.add(equal("personId", personId));

        Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setPredicates(preds);

        QueryResults<ProposalPerson> list= getDataObjectService().findMatching(ProposalPerson.class, builder.build());
        return list.getResults();
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

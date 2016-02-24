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
package org.kuali.coeus.propdev.impl.hierarchy;

import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
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

    private static final String PROPOSAL_STATE_QUERY = "SELECT a.proposalState from DevelopmentProposal a where a.proposalNumber = :proposalNumber";
    private static final String PERSON_ID = "personId";
    private static final String ROLODEX_ID = "rolodexId";
    private static final String DEVELOPMENT_PROPOSAL_PROPOSAL_NUMBER = "developmentProposal.proposalNumber";
    private static final String HIERARCHY_PARENT_PROPOSAL_NUMBER = "hierarchyParentProposalNumber";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String PROPOSAL_PERSON_DEVELOPMENT_PROPOSAL_PROPOSAL_NUMBER = "proposalPerson.developmentProposal.proposalNumber";
    private static final String PROPOSAL_PERSON_PROPOSAL_PERSON_NUMBER = "proposalPerson.proposalPersonNumber";

    @Autowired
	@Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("kcEntityManager")
    private EntityManager entityManager;

    @Override
    public boolean employeePersonInMultipleChildProposals(String personId, String hierarchyProposalNumber) {
        return personInMultipleChildProposals(equal(PERSON_ID, personId), hierarchyProposalNumber);
    }

    @Override
    public boolean nonEmployeePersonInMultipleChildProposals(Integer rolodexId, String hierarchyProposalNumber) {
        return personInMultipleChildProposals(equal(ROLODEX_ID, rolodexId), hierarchyProposalNumber);
    }

    protected boolean personInMultipleChildProposals(Predicate personPredicate, String hierarchyProposalNumber) {
        final List<String> childProposalNumbers = getHierarchyChildProposalNumbers(hierarchyProposalNumber);
        if (childProposalNumbers.isEmpty()) {
            return false;
        }

        final QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        final List<Predicate> predicates = new ArrayList<>();
        predicates.add(personPredicate);
        predicates.add(in(DEVELOPMENT_PROPOSAL_PROPOSAL_NUMBER, childProposalNumbers));

        final Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setCountFlag(CountFlag.ONLY);
        builder.setPredicates(preds);

        return getDataObjectService().findMatching(ProposalPerson.class, builder.build()).getTotalRowCount() > 1;
    }

    //select PROPOSAL_NUMBER from EPS_PROPOSAL where HIERARCHY_PROPOSAL_NUMBER =18
    @Override
    public List<DevelopmentProposal> getHierarchyChildProposals(String parentProposalNumber) {
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(equal(HIERARCHY_PARENT_PROPOSAL_NUMBER, parentProposalNumber));

        Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setPredicates(preds);

        QueryResults<DevelopmentProposal> results = getDataObjectService().findMatching(DevelopmentProposal.class,
                builder.build());
        return results.getResults();
    }

    @Override
    public DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        return (dataObjectService.findUnique(DevelopmentProposal.class, QueryByCriteria.Builder.forAttribute(PROPOSAL_NUMBER, proposalNumber).build()));
    }

    @Override
    public ProposalState getProposalState(String proposalNumber) {
        return (ProposalState) entityManager.createQuery(PROPOSAL_STATE_QUERY).setParameter(PROPOSAL_NUMBER, proposalNumber).getSingleResult();
     }

    @Override
    public List<ProposalPerson> isEmployeePersonOnProposal(String proposalNumber, String personId) {
        return isPersonOnProposal(proposalNumber, equal(PERSON_ID, personId));
    }

    @Override
    public List<ProposalPerson> isNonEmployeePersonOnProposal(String proposalNumber, Integer rolodexId) {
        return isPersonOnProposal(proposalNumber, equal(ROLODEX_ID, rolodexId));
    }

    protected List<ProposalPerson> isPersonOnProposal(String proposalNumber, Predicate personPredicate) {
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(equal(DEVELOPMENT_PROPOSAL_PROPOSAL_NUMBER, proposalNumber));
        predicates.add(personPredicate);

        Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]);
        builder.setPredicates(preds);

        QueryResults<ProposalPerson> list= getDataObjectService().findMatching(ProposalPerson.class, builder.build());
        return list.getResults();
    }

    @Override
    public List<String> getHierarchyChildProposalNumbers(String parentProposalNumber) {
        List<DevelopmentProposal> proposals = getHierarchyChildProposals(parentProposalNumber);

        List<String> proposalNumbers = new ArrayList<>();
        for(DevelopmentProposal proposal : proposals) {
            proposalNumbers.add(proposal.getProposalNumber());
        }

        return proposalNumbers;
    }

    @Override
    public void deleteDegreeInfo(String proposalNumber, Integer proposalPersonNumber, ProposalPerson person) {
        Map<String, String> param = new HashMap<>();
        param.put(PROPOSAL_PERSON_DEVELOPMENT_PROPOSAL_PROPOSAL_NUMBER, proposalNumber);
        param.put(PROPOSAL_PERSON_PROPOSAL_PERSON_NUMBER, proposalPersonNumber.toString());
        getDataObjectService().deleteMatching(ProposalPersonDegree.class, QueryByCriteria.Builder.andAttributes(param).build());
    }

    @Override
    public List<ProposalPersonDegree> getDegreeInformation(String proposalNumber, ProposalPerson person) {
        Map<String, String> param = new HashMap<>();
        param.put(PROPOSAL_PERSON_DEVELOPMENT_PROPOSAL_PROPOSAL_NUMBER, proposalNumber);
        param.put(PROPOSAL_PERSON_PROPOSAL_PERSON_NUMBER, person.getProposalPersonNumber().toString());
        return getDataObjectService().findMatching(ProposalPersonDegree.class, QueryByCriteria.Builder.andAttributes(param).build()).getResults();
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

}

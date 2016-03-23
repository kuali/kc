package org.kuali.coeus.s2sgen.impl.generate.support;


import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.core.api.criteria.QueryByCriteria;

import org.kuali.rice.krad.data.DataObjectService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;

public class PHS398TrainingBudgetV1_0GeneratorTest extends S2SModularBudgetTestBase {

    @Override
    protected String getFormGeneratorName() {
        return PHS398TrainingBudgetV1_0Generator.class.getSimpleName();
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document) throws Exception {
        Organization organization = getService(DataObjectService.class).findUnique(Organization.class,
                QueryByCriteria.Builder.forAttribute("organizationId", "000001").build());
        document.getDevelopmentProposal().getApplicantOrganization().setOrganization(organization);

        List<Narrative> naList = new ArrayList<>();

        Narrative narrative1 = createNarrative("130");

        narrative1.setDevelopmentProposal(document.getDevelopmentProposal());
        naList.add(narrative1);
        document.getDevelopmentProposal().setNarratives(naList);

        List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
        ProposalPerson principalInvestigator = new ProposalPerson();
        principalInvestigator.setFirstName("ALAN");
        principalInvestigator.setLastName("MCAFEE");
        principalInvestigator.setProposalPersonRoleId("PI");
        principalInvestigator.setPersonId("0001");
        principalInvestigator.setRolodexId(0010);
        principalInvestigator.setProposalPersonNumber(1);
        principalInvestigator.setDevelopmentProposal(document.getDevelopmentProposal());
        proposalPersons.add(principalInvestigator);
        document.getDevelopmentProposal().setProposalPersons(proposalPersons);

        ProposalDevelopmentBudgetExt proposalDevelopmentBudgetExt = new ProposalDevelopmentBudgetExt();
        proposalDevelopmentBudgetExt.setDevelopmentProposal(document.getDevelopmentProposal());
        proposalDevelopmentBudgetExt.setBudgetVersionNumber(1);
        proposalDevelopmentBudgetExt.setBudgetStatus("1");
        proposalDevelopmentBudgetExt.setBudgetId(1L);
        proposalDevelopmentBudgetExt
                .setName("test Document Description");
        proposalDevelopmentBudgetExt.setOnOffCampusFlag("Y");
        proposalDevelopmentBudgetExt.setStartDate(new Date(new Long(
                "1183316613046")));
        proposalDevelopmentBudgetExt.setEndDate(new Date(new Long(
                "1214852613046")));
        proposalDevelopmentBudgetExt.setOhRateTypeCode("1");
        proposalDevelopmentBudgetExt.setOhRateClassCode("1");
        proposalDevelopmentBudgetExt.setModularBudgetFlag(false);
        proposalDevelopmentBudgetExt.setUrRateClassCode("1");

        List<BudgetPeriod> budgetPeriods = new ArrayList<BudgetPeriod>();
        BudgetPeriod budgetPeriod = new BudgetPeriod();
        budgetPeriod.setBudgetPeriodId(1L);
        budgetPeriod.setStartDate(new Date(new Long("1183316613046")));
        budgetPeriod.setEndDate(new Date(new Long("1214852613046")));
        budgetPeriod.setBudgetPeriod(1);
        budgetPeriod.setBudget(proposalDevelopmentBudgetExt);
        budgetPeriods.add(budgetPeriod);
        proposalDevelopmentBudgetExt.setBudgetPeriods(budgetPeriods);

        List<ProposalDevelopmentBudgetExt> proposalDevelopmentBudgetExtList = new ArrayList<ProposalDevelopmentBudgetExt>();
        proposalDevelopmentBudgetExtList.add(proposalDevelopmentBudgetExt);
        document.getDevelopmentProposal().setBudgets(proposalDevelopmentBudgetExtList);
        document.getDevelopmentProposal().setFinalBudget(proposalDevelopmentBudgetExt);

    }
}

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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.system.metaGrantApplication.GrantApplicationDocument;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.data.DataObjectService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RRFedNonFedSubAwardBudget5_30V1_3GeneratorTest extends RRSubAwardBudgetBaseGeneratorTest {

    private static final String RR_FED_NON_FED_BUDGET30_12_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_FedNonFedBudget_1_2-V1.2";

    @Override
    protected String getFormGeneratorName() {
        return "RRFedNonFedSubAwardBudget5_30V1_3Generator";
    }

    @Override
    protected String getBudgetFormGeneratorName() {
        return RRFedNonFedBudgetV1_2Generator.class.getSimpleName();
    }

    @Override
    protected String getBudgetJustificationNarrativeType() {
        return "131";
    }

    @Override
    protected void prepareData(ProposalDevelopmentDocument document)
            throws Exception {
        ProposalDevelopmentBudgetExt proposalDevelopmentBudgetExt = new ProposalDevelopmentBudgetExt();
        proposalDevelopmentBudgetExt.setBudgetVersionNumber(1);
        proposalDevelopmentBudgetExt.setDevelopmentProposal(document
                .getDevelopmentProposal());
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

        BudgetSubAwards budgetSubAwards = new BudgetSubAwards();
        budgetSubAwards.setSubAwardNumber(1);
        budgetSubAwards.setBudgetId(1L);
        budgetSubAwards.setBudget(proposalDevelopmentBudgetExt);

        Organization testOrg = new Organization();
        testOrg.setOrganizationName("University of Maine");
        testOrg.setOrganizationId("000040");
        budgetSubAwards.setOrganization(testOrg);
        budgetSubAwards.setSubAwardStatusCode(1);
        budgetSubAwards.setHiddenInHierarchy(false);

        S2SBaseFormGenerator generatorObject1;
        generatorObject1 = KcServiceLocator
                .getService(getBudgetFormGeneratorName());

        budgetSubAwards
                .setNamespace(generatorObject1.getNamespace());

        generatorObject1.setAttachments(new ArrayList<>());
        ProposalDevelopmentDocument doc = initializeDocument();
        initializeDevelopmentProposal(doc);
        prepareDatas(doc);

        Narrative narrative1 = createNarrative("131");
        narrative1.setDevelopmentProposal(document.getDevelopmentProposal());
        doc.getDevelopmentProposal().getNarratives().add(narrative1);

        XmlObject object = generatorObject1.getFormObject(doc);
        GrantApplicationDocument.GrantApplication.Forms forms = GrantApplicationDocument.GrantApplication.Forms.Factory.newInstance();
        setFormObject(forms, object);
        GrantApplicationDocument.GrantApplication grantApplication = GrantApplicationDocument.GrantApplication.Factory
                .newInstance();
        grantApplication.setForms(forms);

        budgetSubAwards.setSubAwardXmlFileData(grantApplication.xmlText());
        List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<>();
        budgetSubAwardsList.add(budgetSubAwards);

        proposalDevelopmentBudgetExt.setBudgetSubAwards(budgetSubAwardsList);
        proposalDevelopmentBudgetExt.setOhRateClassCode("1");
        proposalDevelopmentBudgetExt.setModularBudgetFlag(false);
        proposalDevelopmentBudgetExt.setUrRateClassCode("1");
        proposalDevelopmentBudgetExt = KcServiceLocator.getService(DataObjectService.class)
                .save(proposalDevelopmentBudgetExt);

        List<ProposalDevelopmentBudgetExt> proposalDevelopmentBudgetExtList = new ArrayList<>();
        proposalDevelopmentBudgetExtList.add(proposalDevelopmentBudgetExt);

        document.getDevelopmentProposal().setBudgets(
                proposalDevelopmentBudgetExtList);
        document.getDevelopmentProposal().setFinalBudget(proposalDevelopmentBudgetExt);

        document.getDevelopmentProposal().getFinalBudget().getBudgetSubAwards().forEach(budgetSubAward -> {
            budgetSubAward.setNamespace(RR_FED_NON_FED_BUDGET30_12_NAMESPACE_URI);
        });
    }

}

package org.kuali.kra.s2s.generator.impl;


import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetDocument;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.S2SModularBudgetTestBase;
import org.kuali.kra.s2s.phs398modularbudget.PHS398ModularBudgetV1_2Generator;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;

public class PHS398ModularBudgetV1_2GeneratorTest extends
		S2SModularBudgetTestBase<PHS398ModularBudgetV1_2Generator> {

	@Override
	protected Class<PHS398ModularBudgetV1_2Generator> getFormGeneratorClass() {
		return PHS398ModularBudgetV1_2Generator.class;
	}

	@Override
	protected void prepareData(ProposalDevelopmentDocument document)
			throws Exception {

		BudgetDocument bd = (BudgetDocument) KRADServiceLocatorWeb
				.getDocumentService().getNewDocument("BudgetDocument");
		Budget budget = bd.getBudget();
		budget.setBudgetId(new Long("000001"));
		budget.setDocumentNumber(bd.getDocumentNumber());
		budget.setBudgetStatus("1");
		budget.setFinalVersionFlag(true);
		budget.setStartDate(new Date(new Long("1183316613046")));
		budget.setEndDate(new Date(new Long("1214852613046")));
		budget.setFinalVersionFlag(true);
		budget.setOnOffCampusFlag("Y");
		budget.setOhRateClassCode("1");
		budget.setUrRateClassCode("1");
		budget.setModularBudgetFlag(false);

		bd.setParentDocument(document);
		bd.getDocumentHeader().setDocumentDescription(
				"test document description");
		bd.getDocumentHeader().setDocumentNumber(bd.getDocumentNumber());

		getService(DataObjectService.class).save(bd);
		getService(DataObjectService.class).save(bd.getDocumentHeader());

		List<BudgetDocumentVersion> budgetDocumentVersions = new ArrayList<BudgetDocumentVersion>();
		BudgetDocumentVersion budgetDocumentVersion = new BudgetDocumentVersion();
		budgetDocumentVersion.setDocumentNumber(bd.getDocumentNumber());

		BudgetVersionOverview budgetVersionOverview = new BudgetVersionOverview();
		budgetVersionOverview.setBudgetVersionNumber(1);
		budgetVersionOverview.setFinalVersionFlag(true);
		budgetVersionOverview.setBudgetStatus("1");
		budgetVersionOverview
				.setDocumentDescription("test Document Description");
		budgetVersionOverview.setOnOffCampusFlag("Y");
		budgetVersionOverview.setDocumentNumber(budgetDocumentVersion
				.getDocumentNumber());
		budgetVersionOverview.setStartDate(new Date(new Long("1183316613046")));
		budgetVersionOverview.setEndDate(new Date(new Long("1214852613046")));
		budgetVersionOverview.setOhRateTypeCode("1");
		budgetVersionOverview.setOhRateClassCode("1");
		budgetVersionOverview.setModularBudgetFlag(false);
		budgetVersionOverview.setUrRateClassCode("1");
		budgetDocumentVersion.setBudgetVersionOverview(budgetVersionOverview);
		budgetDocumentVersions.add(budgetDocumentVersion);
		document.setBudgetDocumentVersions(budgetDocumentVersions);
	}
}

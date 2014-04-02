package org.kuali.kra.s2s.phs398modularbudget;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModularIdc;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.impl.PHS398ModularBudgetBaseGenerator;
import org.kuali.kra.s2s.generator.impl.PHS398ModularBudgetV1_1Generator;
import org.kuali.kra.s2s.util.S2SConstants;

import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.CummulativeBudgetInfo;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.Periods;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.CummulativeBudgetInfo.BudgetJustifications;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.CummulativeBudgetInfo.EntirePeriodTotalCost;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.CummulativeBudgetInfo.BudgetJustifications.AdditionalNarrativeJustification;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.CummulativeBudgetInfo.BudgetJustifications.ConsortiumJustification;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.CummulativeBudgetInfo.BudgetJustifications.PersonnelJustification;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.Periods.DirectCost;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.Periods.IndirectCost;
import gov.grants.apply.forms.phs398ModularBudget12V12.PHS398ModularBudget12Document.PHS398ModularBudget12.Periods.IndirectCost.IndirectCostItems;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import org.kuali.rice.kew.api.exception.WorkflowException;

public class PHS398ModularBudgetV1_2Generator extends
PHS398ModularBudgetBaseGenerator{

	private static final Log LOG = LogFactory
	.getLog(PHS398ModularBudgetV1_1Generator.class);

	private ScaleTwoDecimal cumulativeConsortiumFandA = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeDirectCostLessConsortiumFandA = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeTotalFundsRequestedDirectCosts = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeTotalFundsRequestedDirectIndirectCosts = ScaleTwoDecimal.ZERO;
	private ScaleTwoDecimal cumulativeTotalFundsRequestedIndirectCost = ScaleTwoDecimal.ZERO;

	/**
	 * 
	 * This method gives modular budget information for PHS398ModularBudget form
	 * 
	 * @return modularBudgetDocument {@link XmlObject} of type
	 *         PHS398ModularBudgetDocument.
	 */
	private PHS398ModularBudget12Document getPHS398ModularBudget() {

		PHS398ModularBudget12Document modularBudgetDocument = PHS398ModularBudget12Document.Factory
		.newInstance();
		PHS398ModularBudget12 modularBudget = PHS398ModularBudget12.Factory
		.newInstance();
		modularBudget.setFormVersion(S2SConstants.FORMVERSION_1_2);

		Budget budget = null;
		try {
			BudgetDocument budgetDocument = proposalBudgetService
			.getFinalBudgetVersion(pdDoc);
			budget = budgetDocument == null ? null : budgetDocument.getBudget();
		} catch (WorkflowException e) {
			LOG.error(e.getMessage(), e);
			return modularBudgetDocument;
		}
		if (budget != null) {
			
			modularBudget.setPeriodsArray(getPeriods(budget));
			
			modularBudget.setCummulativeBudgetInfo(getCummBudget());
		}
		modularBudgetDocument.setPHS398ModularBudget12(modularBudget);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(modularBudgetDocument.toString().getBytes());            
        sortAttachments(byteArrayInputStream);
		return modularBudgetDocument;
	}

	/**
	 * 
	 * This method is used to get Cummulative Budget information from
	 * ModularBudget form
	 * 
	 * @return CummulativeBudgetInfo containing cummulative cost details.
	 */
	private CummulativeBudgetInfo getCummBudget() {
		CummulativeBudgetInfo cummulativeBudgetInfo = CummulativeBudgetInfo.Factory
		.newInstance();
		EntirePeriodTotalCost entireCost = EntirePeriodTotalCost.Factory
		.newInstance();
		// Set default values to mandatory fields
		entireCost.setCumulativeDirectCostLessConsortiumFandA(BigDecimal.ZERO);
		entireCost.setCumulativeTotalFundsRequestedDirectCosts(BigDecimal.ZERO);
		entireCost
		.setCumulativeTotalFundsRequestedDirectIndirectCosts(BigDecimal.ZERO);

		BudgetJustifications budgetJustifications = BudgetJustifications.Factory
		.newInstance();
		if (!cumulativeTotalFundsRequestedDirectIndirectCosts.toString()
				.equals("0")) {
			entireCost
			.setCumulativeDirectCostLessConsortiumFandA(cumulativeDirectCostLessConsortiumFandA.bigDecimalValue());
			entireCost
			.setCumulativeTotalFundsRequestedDirectCosts(cumulativeTotalFundsRequestedDirectCosts
					.bigDecimalValue());
			entireCost.setCumulativeConsortiumFandA(cumulativeConsortiumFandA
					.bigDecimalValue());
			entireCost
			.setCumulativeTotalFundsRequestedDirectIndirectCosts(cumulativeTotalFundsRequestedDirectIndirectCosts
					.bigDecimalValue());
			entireCost
			.setCumulativeTotalFundsRequestedIndirectCost(cumulativeTotalFundsRequestedIndirectCost
					.bigDecimalValue());
			budgetJustifications = getBudgetJustifications();
			if (budgetJustifications.getAdditionalNarrativeJustification() != null
					|| budgetJustifications.getConsortiumJustification() != null
					|| budgetJustifications.getPersonnelJustification() != null) {
				cummulativeBudgetInfo
				.setBudgetJustifications(budgetJustifications);
			}
		}
		cummulativeBudgetInfo.setEntirePeriodTotalCost(entireCost);
		return cummulativeBudgetInfo;
	}

	/**
	 * 
	 * This method gives list of attachments that are used for ModularBudget
	 * form
	 * 
	 * @return BudgetJustifications object containing budget justification
	 *         attachments based on the narrative type code.
	 */
	private BudgetJustifications getBudgetJustifications() {
		BudgetJustifications budgetJustifications = BudgetJustifications.Factory
		.newInstance();
		AttachedFileDataType attachedFileDataType = null;
		for (Narrative narrative : pdDoc.getDevelopmentProposal()
				.getNarratives()) {
			if (narrative.getNarrativeTypeCode() != null) {
				if (Integer.parseInt(narrative.getNarrativeTypeCode()) == PERSONNEL_JUSTIFICATION_CODE) {
					attachedFileDataType = getAttachedFileType(narrative);
					if(attachedFileDataType == null){
						continue;
					}
					PersonnelJustification personnelJustification = PersonnelJustification.Factory
					.newInstance();
					personnelJustification
					.setAttFile(attachedFileDataType);
					budgetJustifications
					.setPersonnelJustification(personnelJustification);
				}
				if (Integer.parseInt(narrative.getNarrativeTypeCode()) == CONSORTIUM_JUSTIFICATION_CODE) {
					attachedFileDataType = getAttachedFileType(narrative);
					if(attachedFileDataType == null){
						continue;
					}
					ConsortiumJustification consortiumJustification = ConsortiumJustification.Factory
					.newInstance();
					consortiumJustification
					.setAttFile(attachedFileDataType);
					budgetJustifications
					.setConsortiumJustification(consortiumJustification);
				}
				if (Integer.parseInt(narrative.getNarrativeTypeCode()) == NARRATIVE_JUSTIFICATION_CODE) {
					attachedFileDataType = getAttachedFileType(narrative);
					if(attachedFileDataType == null){
						continue;
					}
					AdditionalNarrativeJustification narrativeJustification = AdditionalNarrativeJustification.Factory
					.newInstance();
					narrativeJustification
					.setAttFile(attachedFileDataType);
					budgetJustifications
					.setAdditionalNarrativeJustification(narrativeJustification);
				}
			}
		}
		return budgetJustifications;
	}

	/**
	 * 
	 * This method is used to get 1st BudgetPeriod for Modular Budget form
	 * 
	 * @param budgetPeriod
	 *            budget period 1.
	 * @return Periods object containing modular budget details for the
	 *         corresponding budget period.
	 */
	private Periods[] getPeriods(Budget budget) {
	    List <Periods> periods =new ArrayList<Periods>();
	    for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()){
	        if (budgetPeriod.getBudgetPeriod() <= S2SConstants.BUDGET_PERIOD_5) {
	            Periods period = Periods.Factory.newInstance();
	            DirectCost directCost = DirectCost.Factory.newInstance();
	            IndirectCost indirectCost = IndirectCost.Factory.newInstance();

	            ScaleTwoDecimal consortiumFandA = ScaleTwoDecimal.ZERO;
	            ScaleTwoDecimal directCostLessConsortiumFandA = ScaleTwoDecimal.ZERO;
	            ScaleTwoDecimal totalDirectCosts = ScaleTwoDecimal.ZERO;
	            ScaleTwoDecimal bdTotalIndirectCost = ScaleTwoDecimal.ZERO;
	            ScaleTwoDecimal bdCost = ScaleTwoDecimal.ZERO;
	            ScaleTwoDecimal bdBaseCost = ScaleTwoDecimal.ZERO;
	            ScaleTwoDecimal bdRate = ScaleTwoDecimal.ZERO;
	            String costType = null;


	            // StartDate And EndDate
	            if (budgetPeriod.getStartDate() != null) {
	                period.setBudgetPeriodStartDate(s2sUtilService
	                        .convertDateToCalendar(budgetPeriod.getStartDate()));
	            }
	            if (budgetPeriod.getEndDate() != null) {
	                period.setBudgetPeriodEndDate(s2sUtilService
	                        .convertDateToCalendar(budgetPeriod.getEndDate()));
	            }

	            // Set dfault values for mandatory fields.
	            directCost.setDirectCostLessConsortiumFandA(BigDecimal.ZERO);
	            directCost.setTotalFundsRequestedDirectCosts(BigDecimal.ZERO);
	            period.setTotalFundsRequestedDirectIndirectCosts(BigDecimal.ZERO);

	            // TotalDirectAndIndirectCost
	            BudgetModular budgetModular = budgetPeriod.getBudgetModular();
	            if (budgetModular != null) {
	                ScaleTwoDecimal totalCost = getTotalCost(budgetModular);
	                period.setTotalFundsRequestedDirectIndirectCosts(totalCost
	                        .bigDecimalValue());
	                cumulativeTotalFundsRequestedDirectIndirectCosts = cumulativeTotalFundsRequestedDirectIndirectCosts
	                .add(totalCost);
	                // DirectCosts
	                if (budgetModular.getConsortiumFna() != null) {
	                    consortiumFandA = budgetModular.getConsortiumFna();
	                    directCost
	                    .setConsortiumFandA(consortiumFandA.bigDecimalValue());
	                    cumulativeConsortiumFandA = cumulativeConsortiumFandA
	                    .add(consortiumFandA);
	                }
	                if (budgetModular.getDirectCostLessConsortiumFna() != null) {
	                    directCostLessConsortiumFandA = budgetModular
	                    .getDirectCostLessConsortiumFna();
	                    directCost
	                    .setDirectCostLessConsortiumFandA(directCostLessConsortiumFandA
	                            .bigDecimalValue());
	                    cumulativeDirectCostLessConsortiumFandA = cumulativeDirectCostLessConsortiumFandA
	                    .add(directCostLessConsortiumFandA);
	                }
	                if (budgetModular.getTotalDirectCost() != null) {
	                    totalDirectCosts = budgetModular.getTotalDirectCost();
	                    directCost.setTotalFundsRequestedDirectCosts(totalDirectCosts
	                            .bigDecimalValue());
	                    cumulativeTotalFundsRequestedDirectCosts = cumulativeTotalFundsRequestedDirectCosts
	                    .add(totalDirectCosts);
	                }

	                List<IndirectCostItems> indirectCostItemsList = new ArrayList<IndirectCostItems>();
	                for (BudgetModularIdc budgetModularIdc : budgetModular
	                        .getBudgetModularIdcs()) {
	                    IndirectCostItems indirectCostItems = IndirectCostItems.Factory
	                    .newInstance();
	                    if (budgetModularIdc.getFundsRequested() != null) {
	                        bdCost = budgetModularIdc.getFundsRequested();
	                        indirectCostItems.setIndirectCostFundsRequested(bdCost
	                                .bigDecimalValue());
	                        bdTotalIndirectCost = bdTotalIndirectCost.add(bdCost);
	                    }
	                    if (budgetModularIdc.getIdcBase() != null) {
	                        bdBaseCost = budgetModularIdc.getIdcBase();
	                        indirectCostItems.setIndirectCostBase(bdBaseCost
	                                .bigDecimalValue());
	                    }
	                    if (budgetModularIdc.getIdcRate() != null) {
	                        bdRate = budgetModularIdc.getIdcRate();
	                        indirectCostItems.setIndirectCostRate(bdRate
	                                .bigDecimalValue());
	                    }
	                    if (budgetModularIdc.getDescription() != null) {
	                    	 if (budgetModularIdc.getRateClass() != null) {

		                            costType = budgetModularIdc.getRateClass().getDescription();
		                            }else{
		                                 costType = budgetModularIdc.getDescription();
		                                 }
	                        indirectCostItems.setIndirectCostTypeDescription(costType);
	                    }
	                    indirectCostItemsList.add(indirectCostItems);
	                }
	                IndirectCostItems[] indirectCostItemsArray = new IndirectCostItems[0];
	                indirectCostItemsArray = indirectCostItemsList
	                .toArray(indirectCostItemsArray);
	                indirectCost.setIndirectCostItemsArray(indirectCostItemsArray);
	            }
	            period.setDirectCost(directCost);

	            // CognizantFederalAgency
	            Organization organization = pdDoc.getDevelopmentProposal()
	            .getApplicantOrganization().getOrganization();
	            if (organization != null) {
	                Rolodex rolodex = organization.getCognizantAuditorRolodex();
	                if (rolodex != null) {
	                    indirectCost
	                    .setCognizantFederalAgency(getCognizantFederalAgency(rolodex));
	                }

	                if (organization.getIndirectCostRateAgreement() != null) {
	                    indirectCost.setIndirectCostAgreementDate(s2sUtilService
	                            .convertDateStringToCalendar(organization
	                                    .getIndirectCostRateAgreement()));
	                }

	            }
	            // TotalFundsRequestedIndirectCost
	            indirectCost.setTotalFundsRequestedIndirectCost(bdTotalIndirectCost
	                    .bigDecimalValue());
	            cumulativeTotalFundsRequestedIndirectCost = cumulativeTotalFundsRequestedIndirectCost
	            .add(bdTotalIndirectCost);
	            period.setIndirectCost(indirectCost);
	            periods.add(period);
	        }
	    }
	    return periods.toArray(new Periods[0]);
	}
	/**
	 * This method creates {@link XmlObject} of type
	 * {@link PHS398ModularBudgetDocument} by populating data from the given
	 * {@link ProposalDevelopmentDocument}
	 * 
	 * @param proposalDevelopmentDocument
	 *            for which the {@link XmlObject} needs to be created
	 * @return {@link XmlObject} which is generated using the given
	 *         {@link ProposalDevelopmentDocument}
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
	 */
	public XmlObject getFormObject(
			ProposalDevelopmentDocument proposalDevelopmentDocument) {
		this.pdDoc = proposalDevelopmentDocument;
		return getPHS398ModularBudget();
	}

	/**
	 * This method typecasts the given {@link XmlObject} to the required
	 * generator type and returns back the document of that generator type.
	 * 
	 * @param xmlObject
	 *            which needs to be converted to the document type of the
	 *            required generator
	 * @return {@link XmlObject} document of the required generator type
	 * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
	 */
	public XmlObject getFormObject(XmlObject xmlObject) {

		PHS398ModularBudget12 modularBudget = (PHS398ModularBudget12) xmlObject;
		PHS398ModularBudget12Document modularBudgetDocument = PHS398ModularBudget12Document.Factory
		.newInstance();
		modularBudgetDocument.setPHS398ModularBudget12(modularBudget);
		return modularBudgetDocument;
	}

}

package org.kuali.kra.award.budget;

import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class AwardBudgetServiceImplTest extends KcIntegrationTestBase{

    class AwardBudgetServiceImplMock extends AwardBudgetServiceImpl {
        protected String getAwardPostedStatusCode() {
            return "9";
        }

        protected boolean isValidBudgetVersion(String documentDescription, Award award) throws WorkflowException {
            return true;
        }

        @Override
        public DocumentService getDocumentService() {
            return KcServiceLocator.getService(DocumentService.class);
        }

        @Override
        public ParameterService getParameterService() {
            return KcServiceLocator.getService(ParameterService.class);
        }

        @Override
        public BusinessObjectService getBusinessObjectService() {
            return KcServiceLocator.getService(BusinessObjectService.class);
        }

        protected AwardBudgetCalculationService getAwardBudgetCalculationService() {
            return KcServiceLocator.getService(AwardBudgetCalculationService.class);
        }
    }

    @Test
    public void testCreateNewBudgetVersionAfterPostingAndAwardDatesChanged() throws WorkflowException {
        AwardBudgetServiceImplMock awardBudgetService = new AwardBudgetServiceImplMock();

        LocalDateTime awardBudgetStartDate = LocalDateTime.now();
        LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(2);

        Award award = getNewAward();

        AwardDocument awardDocument = getNewAwardDocument();
        awardDocument.setAward(award);
        award.setAwardDocument(awardDocument);

        AwardAmountInfo awardAmountInfo = getAwardAmountInfo(awardBudgetStartDate, awardBudgetEndDate, award);
        List<AwardAmountInfo> amountInfos = new ArrayList<>();
        amountInfos.add(awardAmountInfo);
        award.setAwardAmountInfos(amountInfos);
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        documentService.saveDocument(awardDocument);

        AwardBudgetExt awardBudget = getNewAwardBudget(awardBudgetStartDate, awardBudgetEndDate, award, awardBudgetService.getAwardPostedStatusCode());
        AwardBudgetDocument abDocument = getNewAwardbudgetDocument();
        List<AwardBudgetExt> budgets = new ArrayList<>();
        budgets.add(awardBudget);
        abDocument.setBudgets(budgets);
        abDocument.getBudgets().add(awardBudget);
        awardBudget.setBudgetDocument(abDocument);
        documentService.saveDocument(abDocument);

        award.getBudgets().add(awardBudget);
        awardBudgetService.createNewBudgetDocument("description", award, Boolean.FALSE);

        // post budget
        award.getBudgets().get(0).setAwardBudgetStatusCode(awardBudgetService.getAwardPostedStatusCode());
        // change dates on award, move earlier end date
        awardAmountInfo = award.getAwardAmountInfos().get(0);
        awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
        awardAmountInfo.setObligationExpirationDate(convertToSqlDate(awardBudgetEndDate));


        awardBudgetService.createNewBudgetDocument("description", award, Boolean.FALSE);

        Assert.assertFalse(GlobalVariables.getMessageMap().hasErrors());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().size() == 0);
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().get("document.budget.budgetPeriods[0].endDate") == null);

    }

    @Test
    public void testCreateNewBudgetVersionAfterAwardDatesChanged() throws WorkflowException {
        AwardBudgetServiceImplMock awardBudgetService = new AwardBudgetServiceImplMock();

        LocalDateTime awardBudgetStartDate = LocalDateTime.now();
        LocalDateTime awardBudgetEndDate = awardBudgetStartDate.plusYears(2);

        Award award = getNewAward();

        AwardDocument awardDocument = getNewAwardDocument();
        awardDocument.setAward(award);
        award.setAwardDocument(awardDocument);

        AwardAmountInfo awardAmountInfo = getAwardAmountInfo(awardBudgetStartDate, awardBudgetEndDate, award);
        List<AwardAmountInfo> amountInfos = new ArrayList<>();
        amountInfos.add(awardAmountInfo);
        award.setAwardAmountInfos(amountInfos);
        DocumentService documentService = KcServiceLocator.getService(DocumentService.class);
        documentService.saveDocument(awardDocument);

        AwardBudgetExt awardBudget = getNewAwardBudget(awardBudgetStartDate, awardBudgetEndDate, award, "1");
        AwardBudgetDocument abDocument = getNewAwardbudgetDocument();
        List<AwardBudgetExt> budgets = new ArrayList<>();
        budgets.add(awardBudget);
        abDocument.setBudgets(budgets);
        abDocument.getBudgets().add(awardBudget);
        awardBudget.setBudgetDocument(abDocument);
        documentService.saveDocument(abDocument);

        award.getBudgets().add(awardBudget);
        awardBudgetService.createNewBudgetDocument("description", award, Boolean.FALSE);

        // change dates on award, move earlier end date
        awardAmountInfo = award.getAwardAmountInfos().get(0);
        awardBudgetEndDate = awardBudgetStartDate.plusYears(1);
        awardAmountInfo.setObligationExpirationDate(convertToSqlDate(awardBudgetEndDate));

        awardBudgetService.createNewBudgetDocument("description", award, Boolean.FALSE);

        Assert.assertFalse(GlobalVariables.getMessageMap().hasErrors());
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().size() == 0);
        Assert.assertTrue(GlobalVariables.getMessageMap().getErrorMessages().get("document.budget.budgetPeriods[0].endDate") == null);

    }


    protected Award getNewAward() {
        Award award = new Award();
        award.setAccountTypeCode(2);
        award.setLeadUnit(getUnit("000001"));
        award.setSponsorCode("000162");
        award.setStatusCode(1);
        award.setAwardTransactionTypeCode(1);
        award.setActivityTypeCode("1");
        award.setAwardTypeCode(1);
        award.setTitle("Sample Award: ");
        award.setSequenceNumber(1);
        return award;
    }

    protected AwardAmountInfo getAwardAmountInfo(LocalDateTime awardBudgetStartDate, LocalDateTime awardBudgetEndDate, Award award) {
        AwardAmountInfo awardAmountInfo = new AwardAmountInfo();
        awardAmountInfo.setCurrentFundEffectiveDate(convertToSqlDate(awardBudgetStartDate));
        awardAmountInfo.setObligationExpirationDate(convertToSqlDate(awardBudgetEndDate));
        awardAmountInfo.setFinalExpirationDate(convertToSqlDate(awardBudgetEndDate));
        awardAmountInfo.setObliDistributableAmount(ScaleTwoDecimal.ONE_HUNDRED);
        awardAmountInfo.setAward(award);
        awardAmountInfo.setAwardNumber(award.getAwardNumber());
        return awardAmountInfo;
    }

    protected AwardBudgetExt getNewAwardBudget(LocalDateTime awardBudgetStartDate, LocalDateTime awardBudgetEndDate, Award award, String awardPostedStatusCode) {
        AwardBudgetExt awardBudget = new AwardBudgetExt();
        awardBudget.setStartDate(convertToSqlDate(awardBudgetStartDate));
        awardBudget.setEndDate(convertToSqlDate(awardBudgetEndDate));
        awardBudget.setAwardBudgetStatusCode(awardPostedStatusCode);
        AwardBudgetPeriodExt awardBudgetPeriod1 = prepareAwardBudgetPeriod(awardBudgetStartDate, awardBudgetEndDate);
        awardBudgetPeriod1.setBudget(awardBudget);
        awardBudget.getBudgetPeriods().add(awardBudgetPeriod1);
        awardBudget.setAward(award);
        awardBudget.setAwardId(award.getAwardId());
        awardBudget.setBudgetVersionNumber(1);
        awardBudget.setOhRateClassCode("1");
        awardBudget.setOhRateTypeCode("1");
        awardBudget.setUrRateClassCode("1");
        awardBudget.setName("test budget");
        awardBudget.setAwardBudgetTypeCode("1");
        return awardBudget;
    }

    protected AwardBudgetPeriodExt prepareAwardBudgetPeriod(LocalDateTime awardBudgetStartDate, LocalDateTime awardBudgetEndDate) {
        AwardBudgetDocument awardBudgetDoc = new AwardBudgetDocument();
        AwardBudgetExt awardBudget = new AwardBudgetExt();
        awardBudget.setBudgetDocument(awardBudgetDoc);
        awardBudget.setStartDate(convertToSqlDate(awardBudgetStartDate));
        AwardBudgetPeriodExt awardBudgetPeriod1 = new AwardBudgetPeriodExt();
        awardBudgetPeriod1.setBudget(awardBudget);
        awardBudget.getBudgetPeriods().add(awardBudgetPeriod1);
        awardBudgetPeriod1.setStartDate(convertToSqlDate(awardBudgetStartDate));
        awardBudgetPeriod1.setEndDate(convertToSqlDate(awardBudgetEndDate));
        awardBudgetPeriod1.setBudgetPeriod(1);
        return awardBudgetPeriod1;
    }

    protected Unit getUnit(String unitNumber) {
        UnitService unitService = KcServiceLocator.getService(UnitService.class);
        Unit unit = unitService.getUnit(unitNumber);
        return unit;
    }

    protected Date convertToSqlDate(LocalDateTime awardBudgetStartDate) {
        return new java.sql.Date(awardBudgetStartDate.toInstant(ZoneOffset.ofHours(0)).toEpochMilli());
    }

    protected AwardBudgetDocument getNewAwardbudgetDocument() throws WorkflowException {
        return (AwardBudgetDocument) KcServiceLocator.getService(DocumentService.class).getNewDocument("AwardBudgetDocument");
    }

    protected AwardDocument getNewAwardDocument() throws WorkflowException {
        return (AwardDocument) KcServiceLocator.getService(DocumentService.class).getNewDocument("AwardDocument");
    }
}

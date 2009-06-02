/*
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetLineItemCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelCalculatedAmount;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetPrintForm;
import org.kuali.kra.budget.calculator.RateClassType;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetPrintService;
import org.kuali.kra.budget.service.CoeusBean2KraBoConverterService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.WebUtils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import edu.mit.coeus.bean.CoeusReportGroupBean;
import edu.mit.coeus.bean.CoeusReportGroupBean.Report;
import edu.mit.coeus.budget.bean.BudgetDetailBean;
import edu.mit.coeus.budget.bean.BudgetDetailCalAmountsBean;
import edu.mit.coeus.budget.bean.BudgetPeriodBean;
import edu.mit.coeus.budget.bean.BudgetPersonnelDetailsBean;
import edu.mit.coeus.budget.bean.BudgetTotalBean;
import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.utils.CoeusProperties;
import edu.mit.coeus.utils.CoeusPropertyKeys;
import edu.mit.coeus.utils.CoeusVector;
import edu.mit.coeus.utils.query.And;
import edu.mit.coeus.utils.query.DataType;
import edu.mit.coeus.utils.query.Equals;
import edu.mit.coeus.utils.query.NotEquals;
import edu.mit.coeus.utils.query.Or;
import edu.mit.coeus.xml.conf.ReportConfigEngine;
import edu.mit.coeus.xml.generator.ReportReader;
import edu.mit.coeus.xml.generator.ReportReaderConstants;

/**
 * This class is to process and read the streams of all proposal budget reports
 */
public class BudgetPrintServiceImpl implements BudgetPrintService {
    private String queryKey;
//    private BudgetTotalBean budgetTotalBean;
    private static final String EMPTY_STRING = "";
    private static final String LINE_ITEM_COST_ELEMENT = "costElement";
    private static final String LINE_ITEM_NUMBER = "lineItemNumber";
    private static final String COST_ELEMENT_DESCRIPTION = "costElementDescription";
    private static final String LINE_ITEM_COST = "lineItemCost";
    private static final String BUDGET_PERIOD = "budgetPeriod";
    private static final String RATE_CLASS_CODE = "rateClassCode";
    private static final String RATE_TYPE_CODE = "rateTypeCode";
    private static final String CALCULATED_COST = "calculatedCost";
    private int budgetPeriods = 0;
    private CoeusVector cvCEExists = new CoeusVector();
    private int insertIndex = 0;
    private double value;

    private static final String OH = "OH - ";

    private static final String COST_ELEMENT_VALUE = "costElementValue";
    private static final String PERSON_ID = "personId";
    private int initialVecSize = 0;
    private static final String CALCULATED_AMTS = "Calculated Amounts";
    private static final String TOTAL = "Total";
    private static final String PERIOD_COST_VALUE = "periodCostValue";
    private static final Log LOG = LogFactory.getLog(BudgetPrintServiceImpl.class);
    private CoeusBean2KraBoConverterService coeusBean2KraBoConverterService;
    private static final String CONTENT_TYPE = "application/pdf";
    private static final Map<String, String> REPT_PAGESIZE_MAP = new HashMap<String, String>();
    static {
        REPT_PAGESIZE_MAP.put("ProposalBudget/budgetsummary", "0");
        REPT_PAGESIZE_MAP.put("ProposalBudget/costsharesummarybyperiod", "1");
        REPT_PAGESIZE_MAP.put("ProposalBudget/cumulativebudget", "0");
        REPT_PAGESIZE_MAP.put("ProposalBudget/budgettotalbyperiod", "1");
        REPT_PAGESIZE_MAP.put("ProposalBudget/industrialbudget", "0");
        REPT_PAGESIZE_MAP.put("ProposalBudget/indsrlcumbudget", "1");
        REPT_PAGESIZE_MAP.put("ProposalBudget/Salaries", "1");       
    }

    @SuppressWarnings("unchecked")
    private AttachmentDataSource readStream(String reportId,String reportName,Map params){
        Map reportParams = new HashMap(); 
        try {
            String reportPath = CoeusProperties.getProperty(CoeusPropertyKeys.REPORT_GENERATED_PATH);
            reportParams.put(ReportReaderConstants.REPOORT_PATH, reportPath);
        }
        catch (IOException e1) {}
        reportParams.put(ReportReaderConstants.REPORT_ID,reportId);
        reportParams.put(ReportReaderConstants.REPORT_NAME,reportName);
        reportParams.put(ReportReaderConstants.REPORT_PARAMS,params);
        ReportReader reportReader = new ReportReader();
        try {
            final byte[] streamData = reportReader.read(reportParams).getDocumentData();
            AttachmentDataSource attachment = new AttachmentDataSource(){
                public byte[] getContent(){
                    return streamData;
                }
            };
            attachment.setFileName(reportName.concat(".pdf"));
            attachment.setContentType(CONTENT_TYPE);
            return attachment;
        }
        catch (CoeusException e) {
            LOG.error(e);
        }
        return null;
        
    }
    @SuppressWarnings("unchecked")
    private AttachmentDataSource readBudgetTotal(BudgetDocument budgetDocument) {
        String reportName = "BudgetTotal"+budgetDocument.getProposalNumber()+"V"+budgetDocument.getBudgetVersionNumber();
        Hashtable printParams = new Hashtable();
        printParams.put("PROPOSAL_NUM",budgetDocument.getProposalNumber());
        printParams.put("HEADER_TITLE","Budget Total");
        printParams.put("VERSION_NUM",""+budgetDocument.getBudgetVersionNumber());
        printParams.put("START_DATE",budgetDocument.getStartDate());
        printParams.put("END_DATE",budgetDocument.getEndDate());
        printParams.put("DATA",getBudgetTotalBeanList(budgetDocument));
        
        return readStream("ProposalBudget/budgettotalbyperiod",reportName,printParams);
    }

    @SuppressWarnings("unchecked")
    private AttachmentDataSource readIndustrialCumulativeBudget(BudgetDocument budgetDocument) {
        String reportName = "IndustrialCumulativeBudget"+budgetDocument.getProposalNumber()+"V"+budgetDocument.getBudgetVersionNumber();
        Hashtable printParams = new Hashtable();
        printParams.put("PROPOSAL_NUM",budgetDocument.getProposalNumber());
        printParams.put("HEADER_TITLE","Industrial Cumulative Budget");
        printParams.put("VERSION_NUM",""+budgetDocument.getBudgetVersionNumber());
        printParams.put("START_DATE",budgetDocument.getStartDate());
        printParams.put("END_DATE",budgetDocument.getEndDate());
        printParams.put("DATA",getIndsrlCumulativeData(budgetDocument));
        return readStream("ProposalBudget/indsrlcumbudget",reportName,printParams);
    }
    @SuppressWarnings("unchecked")
    private AttachmentDataSource readBudgetSalaries(BudgetDocument budgetDocument) {
        String reportName = "BudgetSalaries"+budgetDocument.getProposalNumber()+"V"+budgetDocument.getBudgetVersionNumber();
        Hashtable htPrintParams = new Hashtable(); 
        htPrintParams.put("PROPOSAL_NUM",budgetDocument.getProposalNumber());
        htPrintParams.put("VERSION_NUM",""+budgetDocument.getBudgetVersionNumber());
        htPrintParams.put("START_DATE",budgetDocument.getStartDate());
        htPrintParams.put("END_DATE",budgetDocument.getEndDate());
        htPrintParams.put("DATA",getBudgetSalaryList(budgetDocument));
        return readStream("Budget/Salaries",reportName,htPrintParams);
    }
    private Vector getBudgetSalaryList(BudgetDocument budgetDocument) {
        String queryKey = budgetDocument.getProposalNumber() + budgetDocument.getVersionNumber();
        BudgetPeriodBean budgetPeriodBean = new BudgetPeriodBean();
        BudgetTotalBean budgetTotalBean = new BudgetTotalBean();
        
        //Operators used to filter and retrieve unique data for the table
        Equals equalsBudgetPeriod, equalsCostElmDesc, equalsCostElm, equalsPersonName,
                equalsLineItemNum, equalsRateClassCode, equalsRateTypeCode;
        NotEquals notEqualsCostElmDesc, notEqualsLineItemNum, notEqualsCostElm,
            notEqualsRateClassCode, notEqualsRateTypeCode, notEqualsPersonName;
        And andRateClassType, andRateClassNotRateType;
        Or orOperator;
        
        CoeusVector cvPersonnelDetails = new CoeusVector();
        
        /** If personnel line items exist then proceed... */

        int budgetPeriod = 0;
        int rateClassCode = 0, rateTypeCode = 0, lineItemNumber = 0;
        String costElement, rateClassType, costElementDesc;

        CoeusVector cvPeriodLineItems = new CoeusVector();
        CoeusVector cvCostElements = new CoeusVector();
        CoeusVector cvPersonnelLineItems = new CoeusVector();
        CoeusVector cvBudgetPersons = new CoeusVector();
        CoeusVector cvPeriodCalAmts = new CoeusVector();
        CoeusVector cvSortedCEData = new CoeusVector();
        CoeusVector cvSortedPersons = new CoeusVector();
        CoeusVector cvCalAmts = new CoeusVector();
        CoeusVector cvCalAmtsExists = new CoeusVector();
        CoeusVector cvRateClassElements = new CoeusVector();
        CoeusVector cvTableData = new CoeusVector();
        CoeusVector cvSortedTableData = new CoeusVector();
        double existingSalary = 0;
        BudgetDetailBean budgetDetailBean = new BudgetDetailBean();
        BudgetPersonnelDetailsBean budgetPersonnelDetailsBean = new BudgetPersonnelDetailsBean ();
        BudgetDetailCalAmountsBean budgetDetailCalAmountsBean = new BudgetDetailCalAmountsBean ();

        try{
//            CoeusVector cvBudgetPeriods = queryEngine.executeQuery(queryKey, BudgetPeriodBean.class, notEqualsDeleteOrEqualsNull);
//            CoeusVector cvBudgetDetails = queryEngine.executeQuery(queryKey, BudgetDetailBean.class, notEqualsDeleteOrEqualsNull);
//            CoeusVector cvBudgetPersonnelDetails = queryEngine.executeQuery(queryKey, BudgetPersonnelDetailsBean.class, notEqualsDeleteOrEqualsNull);
//            CoeusVector cvBudgetPersonnelCalAmts = queryEngine.executeQuery(queryKey, BudgetPersonnelCalAmountsBean.class, notEqualsDeleteOrEqualsNull);
            HashMap budgetData = getBudgetData(budgetDocument);
            CoeusVector cvBudgetPeriods = (CoeusVector)budgetData.get("budgetPeriods");
            CoeusVector cvBudgetDetails = (CoeusVector)budgetData.get("budgetDetails");
            CoeusVector cvBudgetDetailCalAmts = (CoeusVector)budgetData.get("budgetDetailCalAmts");
            CoeusVector cvBudgetPersonnelDetails = (CoeusVector)budgetData.get("budgetPersonnelDetails");
            CoeusVector cvBudgetPersonnelCalAmts = (CoeusVector)budgetData.get("budgetPersonnelCalAmts");            

            budgetPeriods = cvBudgetPeriods.size();
            if( cvBudgetPeriods != null && cvBudgetPeriods.size() > 0){
                /** Table Data for budget personnel line items */
                /** Loop through each budget period and get the unique cost element details */
                for( int periodIndex = 0; periodIndex < cvBudgetPeriods.size(); periodIndex++){
                    budgetPeriodBean = (BudgetPeriodBean) cvBudgetPeriods.get(periodIndex);
                    budgetPeriod = budgetPeriodBean.getBudgetPeriod();
                    equalsBudgetPeriod = new Equals(BUDGET_PERIOD, new Integer(budgetPeriod));
                    /** Filter and get all budget personnel line item details for each period */
                    cvPersonnelDetails = cvBudgetPersonnelDetails.filter(equalsBudgetPeriod);
                    cvPeriodLineItems = cvBudgetDetails.filter(equalsBudgetPeriod);
                    
                    if( cvPersonnelDetails != null && cvPersonnelDetails.size() > 0){
                        while (true) {
                            /** Get the first element of the cvPeriodLineItems
                             * since it contains only one element at a time, as it is filtered
                             * after creating a new budgetTotalBean of unique cost element */
                            budgetPersonnelDetailsBean = ( BudgetPersonnelDetailsBean )cvPersonnelDetails.get(0);
                            lineItemNumber = budgetPersonnelDetailsBean.getLineItemNumber();
                            equalsLineItemNum = new Equals(LINE_ITEM_NUMBER, new Integer(lineItemNumber));
                            notEqualsLineItemNum = new NotEquals(LINE_ITEM_NUMBER, new Integer(lineItemNumber));

                            cvCostElements = cvPeriodLineItems.filter(equalsLineItemNum);

                            budgetDetailBean = (BudgetDetailBean)cvCostElements.get(0);
                            costElementDesc = budgetDetailBean.getCostElementDescription();
                            costElement = budgetDetailBean.getCostElement();
                            equalsCostElm = new Equals(COST_ELEMENT_DESCRIPTION, costElement);
                            notEqualsCostElm = new NotEquals(COST_ELEMENT_DESCRIPTION, costElement);
                            
                            equalsCostElmDesc = new Equals(LINE_ITEM_COST_ELEMENT, costElementDesc);
                            notEqualsCostElmDesc = new NotEquals(LINE_ITEM_COST_ELEMENT, costElementDesc);

                            if (cvTableData != null && cvTableData.size() > 0) {
                                /** Filter the budgetTotalBean from cvTableData 
                                 * corresponding to the given cost element and store
                                 * it into the vector cvCEExists */
                                cvCEExists = cvTableData.filter(equalsCostElmDesc);
                                if (cvCEExists.size() > 0) {
                                    /** Remove the budgetTotalBean from cvTableData
                                     * corresponding to the given cost element */
                                    for( int index = 0; index < cvTableData.size(); index ++ ) {
                                        budgetTotalBean = (BudgetTotalBean) cvTableData.get(index);
                                        String existingCEDesc = budgetTotalBean.getCostElement();
                                        if( costElementDesc.equals(existingCEDesc) ){
                                            insertIndex = index + 1;
                                            break;
                                        }
                                    }
                                }else {
                                    /** Create a new budgetTotalBean and set the required
                                     * properties other than the period cost */
                                    budgetTotalBean = new BudgetTotalBean();
                                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                    budgetTotalBean.setCostElement(costElementDesc);
                                    budgetTotalBean.setCostElementValue(costElement);
                                    budgetTotalBean.setCostElementDescription(EMPTY_STRING);
                                    cvTableData.addElement(budgetTotalBean);
                                }
                                cvPersonnelLineItems = cvPersonnelDetails.filter(equalsLineItemNum);

                                if( cvPersonnelLineItems.size() > 0 ){
                                    for( int personIndex = 0; personIndex < cvPersonnelLineItems.size(); personIndex++ ){
                                        budgetPersonnelDetailsBean = ( BudgetPersonnelDetailsBean )
                                            cvPersonnelLineItems.get(personIndex);
                                        
                                        lineItemNumber = budgetPersonnelDetailsBean.getLineItemNumber();
                                        equalsLineItemNum = new Equals(LINE_ITEM_NUMBER, new Integer(lineItemNumber));
                                        notEqualsLineItemNum = new NotEquals(LINE_ITEM_NUMBER, new Integer(lineItemNumber));
                                        
                                        CoeusVector cvCEDesc = cvPeriodLineItems.filter(equalsLineItemNum);

                                        budgetDetailBean = (BudgetDetailBean)cvCEDesc.get(0);
                                        String CEDesc = ":"+budgetDetailBean.getCostElementDescription();
                                        Equals eqCEDesc = new Equals(LINE_ITEM_COST_ELEMENT, CEDesc);
                                        
                                        equalsPersonName = new Equals(COST_ELEMENT_DESCRIPTION,
                                            budgetPersonnelDetailsBean.getFullName());
                                        notEqualsPersonName = new NotEquals(COST_ELEMENT_DESCRIPTION,
                                            budgetPersonnelDetailsBean.getFullName());
                                        And eqCEDescAndEqPerson = new And(eqCEDesc, equalsPersonName );
                                        cvBudgetPersons = cvTableData.filter(eqCEDescAndEqPerson);
                                        int newInsertIndex = 0;
                                        if (cvBudgetPersons.size() > 0 ){
                                            budgetTotalBean = (BudgetTotalBean)cvBudgetPersons.get(0);
                                            budgetTotalBean = (BudgetTotalBean)cvBudgetPersons.get(0);
                                            String existingCEDesc = budgetTotalBean.getCostElement();
                                            String existingPerson = budgetTotalBean.getCostElementDescription();
                                            for( int tableIndex = 0; tableIndex < cvTableData.size(); tableIndex ++ ) {
                                                budgetTotalBean = (BudgetTotalBean) cvTableData.get(tableIndex);
                                                existingSalary = budgetTotalBean.getPeriodCost(periodIndex);
                                                if( budgetTotalBean.getCostElement().equals(existingCEDesc) &&
                                                budgetTotalBean.getCostElementDescription().equals(existingPerson)){
                                                    newInsertIndex = tableIndex;
                                                    break;
                                                }
                                            }
                                            budgetTotalBean.setPeriodCost(periodIndex, existingSalary + 
                                                budgetPersonnelDetailsBean.getSalaryRequested());
                                            existingSalary = 0;
                                        }else{
                                            boolean hasCEDesc = false;
                                            int existingIndex = 0;
                                            for( int tableIndex = 0; tableIndex < cvTableData.size(); tableIndex ++ ) {
                                                budgetTotalBean = (BudgetTotalBean) cvTableData.get(tableIndex);
                                                if( budgetTotalBean.getCostElement().equals(":"+costElementDesc) ){
                                                    hasCEDesc = true;
                                                    existingIndex = tableIndex;
                                                    break;
                                                }
                                            }

                                            budgetTotalBean = new BudgetTotalBean();
                                            budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                            budgetTotalBean.setCostElement(":" + costElementDesc);
                                            budgetTotalBean.setCostElementDescription(
                                                budgetPersonnelDetailsBean.getFullName());
                                            budgetTotalBean.setCostElementValue(costElement);
                                            budgetTotalBean.setPersonId(budgetPersonnelDetailsBean.getPersonId());
                                            budgetTotalBean.setPeriodCost(periodIndex,
                                                budgetPersonnelDetailsBean.getSalaryRequested());
                                            if ( hasCEDesc ){
                                                cvTableData.add(existingIndex, budgetTotalBean);
                                                hasCEDesc = false;
                                            }else{
                                                cvTableData.addElement(budgetTotalBean);
                                            }
                                        }
                                    }
                                }
                                cvPersonnelDetails = cvPersonnelDetails.filter(notEqualsLineItemNum);                                
                            }else {
                                /** Create a new budgetTotalBean and set the required
                                 * properties other than the period cost */
                                budgetTotalBean = new BudgetTotalBean();
                                budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                budgetTotalBean.setCostElement(costElementDesc);
                                budgetTotalBean.setCostElementValue(costElement);
                                budgetTotalBean.setCostElementDescription(EMPTY_STRING);
                                cvTableData.addElement(budgetTotalBean);
                            }
                            /** Remove all the budgetDetailBeans from cvPeriodLineItems
                             * corresponding to the given cost element */
                            if (cvPersonnelDetails.size() == 0) {
                                break;
                            }
                        }
                    }
                }

                /** Table Data for personnel budget line item calculated amounts */
                /* Loop through each budget period and get the unique
                 * line item calculated amounts */
                for( int periodIndex = 0; periodIndex < cvBudgetPeriods.size(); periodIndex++){
                    budgetPeriodBean = (BudgetPeriodBean) cvBudgetPeriods.get(periodIndex);
                    budgetPeriod = budgetPeriodBean.getBudgetPeriod();
                    equalsBudgetPeriod = new Equals(BUDGET_PERIOD, new Integer(budgetPeriod));
                    cvPeriodCalAmts = cvBudgetPersonnelCalAmts.filter(equalsBudgetPeriod);
                    if( cvPeriodCalAmts != null && cvPeriodCalAmts.size() > 0){
                        while (true) {
                            budgetDetailCalAmountsBean = (BudgetDetailCalAmountsBean)cvPeriodCalAmts.get(0);
                            rateClassCode = budgetDetailCalAmountsBean.getRateClassCode();
                            rateTypeCode = budgetDetailCalAmountsBean.getRateTypeCode();
                            equalsRateClassCode = new Equals(RATE_CLASS_CODE, new Integer(rateClassCode));
                            notEqualsRateClassCode = new NotEquals(RATE_CLASS_CODE, new Integer(rateClassCode));
                            equalsRateTypeCode = new Equals(RATE_TYPE_CODE, new Integer(rateTypeCode));
                            notEqualsRateTypeCode = new NotEquals(RATE_TYPE_CODE, new Integer(rateTypeCode));
                            andRateClassType = new And(equalsRateClassCode, equalsRateTypeCode);
                            andRateClassNotRateType = new And(equalsRateClassCode, notEqualsRateTypeCode);
                            /** The orOperator corresponds to the following condition
                             * ( rateClassCode == <rateClassCode> && rateTypeCode != <rateTypeCode> ) || 
                             * ( rateClassCode != <rateClassCode> ) */
                            orOperator = new Or(andRateClassNotRateType, notEqualsRateClassCode);
                            cvRateClassElements = cvPeriodCalAmts.filter(andRateClassType);
                            /** Get the sum of Calculated Cost of all budgetCalAmtsBeans
                             * in cvRateClassElements corresponding to the given rate class code
                             * and rate type code */
                            value = cvRateClassElements.sum(CALCULATED_COST, andRateClassType);

                            if (cvCalAmts != null && cvCalAmts.size() > 0) {
                                /** Filter the budgetTotalBean from cvTableData 
                                 * corresponding to the given rate class code 
                                 * and rate type code. Store it into cvCalAmtsExists */
                                cvCalAmtsExists = cvCalAmts.filter(andRateClassType);
                                if (cvCalAmtsExists.size() > 0) {
                                    budgetTotalBean = (BudgetTotalBean) cvCalAmtsExists.get(0);
                                    /** Remove the budgetTotalBean from cvTableData
                                     * corresponding to the given condition in orOperator */
                                    cvCalAmts = cvCalAmts.filter(orOperator);
                                    
                                } else {
                                    /** Create a new budgetTotalBean and set the required
                                     * properties other than the period cost */
                                    budgetTotalBean = new BudgetTotalBean();
                                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                    budgetTotalBean.setCostElement(EMPTY_STRING);
                                    rateClassType = budgetDetailCalAmountsBean.getRateClassType();
                                    /** If rate class type is overhead then display
                                     * the rate class description as OH */
                                    if( rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ){
                                        budgetTotalBean.setCostElementDescription(OH +
                                                budgetDetailCalAmountsBean.getRateTypeDescription());
                                    }else{
                                        budgetTotalBean.setCostElementDescription(
                                                budgetDetailCalAmountsBean.getRateClassDescription() + " - " +
                                                budgetDetailCalAmountsBean.getRateTypeDescription());
                                    }
                                    budgetTotalBean.setRateClassCode(budgetDetailCalAmountsBean.getRateClassCode());
                                    budgetTotalBean.setRateTypeCode(budgetDetailCalAmountsBean.getRateTypeCode());
                                }
                            } else {
                                /** Create a new budgetTotalBean and set the required
                                 * properties other than the period cost */
                                budgetTotalBean = new BudgetTotalBean();
                                budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                budgetTotalBean.setCostElement(EMPTY_STRING);//setCostElementDescription
                                rateClassType = budgetDetailCalAmountsBean.getRateClassType();
                                /** If rate class type is overhead then display
                                 * the rate class description as OH */
                                if( rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ){
                                    budgetTotalBean.setCostElementDescription(OH +
                                            budgetDetailCalAmountsBean.getRateTypeDescription());
                                }else{
                                    budgetTotalBean.setCostElementDescription(
                                            budgetDetailCalAmountsBean.getRateClassDescription() + " - " +
                                            budgetDetailCalAmountsBean.getRateTypeDescription());
                                }
                                budgetTotalBean.setRateClassCode(budgetDetailCalAmountsBean.getRateClassCode());
                                budgetTotalBean.setRateTypeCode(budgetDetailCalAmountsBean.getRateTypeCode());
                            }
                            /** Set the period cost property for the existing budgetTotalBean */
                            budgetTotalBean.setPeriodCost(periodIndex, value);
                            cvCalAmts.addElement(budgetTotalBean);

                            /** Remove all the budgetCalAmtsBeans from cvPeriodCalAmts
                             * corresponding to the given operator */
                            cvPeriodCalAmts = cvPeriodCalAmts.filter(orOperator);

                            if (cvPeriodCalAmts.size() == 0) {
                                break;
                            }
                        }
                    }
                }
                
                if( cvTableData != null && cvTableData.size() > 0){
                    /** Filter and get all the cost element descriptions
                     * and sort it in ascending order */
                    Equals eqEmptyString = new Equals(COST_ELEMENT_DESCRIPTION, EMPTY_STRING);
                    cvSortedCEData = cvTableData.filter(eqEmptyString);
                    cvSortedCEData.sort(COST_ELEMENT_VALUE, true);
                    for( int CEIndex = 0; CEIndex < cvSortedCEData.size(); CEIndex++){
                        budgetTotalBean = (BudgetTotalBean)cvSortedCEData.get(CEIndex);
                        cvSortedTableData.addElement(budgetTotalBean);
                        String CEDesc = ":" + budgetTotalBean.getCostElement();
                        Equals eqSortedCE = new Equals(LINE_ITEM_COST_ELEMENT, CEDesc);
                        cvSortedPersons = cvTableData.filter(eqSortedCE);
                        cvSortedPersons.sort(PERSON_ID, true);
                        /** Sort all the persons in each cost element in
                         * ascending order of personId */
                        for( int personIndex = 0; personIndex < cvSortedPersons.size(); personIndex++){
                            cvSortedTableData.addElement(cvSortedPersons.get(personIndex));
                        }
                    }
                    budgetTotalBean = new BudgetTotalBean();
                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                    budgetTotalBean.setCostElement(CALCULATED_AMTS);
                    budgetTotalBean.setCostElementDescription(EMPTY_STRING);
                    cvSortedTableData.addElement(budgetTotalBean);
                    initialVecSize = cvSortedTableData.size();
                }
                if( cvCalAmts != null && cvCalAmts.size() > 0){
                    /** Sort the vector based on <CODE>rateClassCode</CODE> */
                    cvCalAmts.sort(RATE_CLASS_CODE, true);
                }
                for( int index = 0; index < cvCalAmts.size(); index++){
                    cvSortedTableData.addElement(cvCalAmts.get(index));
                }
                
                if( cvBudgetDetails != null &&  cvBudgetDetails.size() > 0 ){
                    boolean hasCostElement = false;
                    /** Check if the budgetDetailBean contains Cost Element */
                    for( int budgetDetailIndex = 0; budgetDetailIndex < cvBudgetDetails.size();
                    budgetDetailIndex++){
                        budgetDetailBean = (BudgetDetailBean)cvBudgetDetails.get(budgetDetailIndex);
                        if( !budgetDetailBean.getCostElement().equals(EMPTY_STRING) ){
                            hasCostElement = true;
                        }
                    }
                    
                    if( hasCostElement ){
                        /** Data for the last row of the table */
                        budgetTotalBean = new BudgetTotalBean();
                        budgetTotalBean.setCostElement(EMPTY_STRING);
                        budgetTotalBean.setCostElementDescription(TOTAL);
                        budgetTotalBean.setBudgetPeriods(budgetPeriods);
                        for(int index = 0; index < budgetPeriods; index++ ){
                            value = cvSortedTableData.sum(PERIOD_COST_VALUE , 
                                DataType.getClass(DataType.INT) , new Integer(index));
                            // Bug Id #1910 - Start
                            value = (double)Math.round (value*Math.pow(10.0, 2) ) / 100;
                            // Bug Id #1910 - End
                            budgetTotalBean.setPeriodCost(index, value);
                        }
                        cvSortedTableData.addElement(budgetTotalBean);
                    }

                }
                /** Data for the Total column of the table */
                /** Set the total property for all the budgetTotalBeans */
                for( int totalIndex = 0; totalIndex < cvSortedTableData.size(); totalIndex ++){
                    budgetTotalBean = ( BudgetTotalBean )cvSortedTableData.get(totalIndex);
                    budgetTotalBean.setTotal();
                }                
            }
        }catch (NumberFormatException numberFormatException ){
            numberFormatException.getMessage();
        }
        return cvSortedTableData;
    }
    public void populateBudgetPrintForms(BudgetDocument budgetDocument) {
        List<BudgetPrintForm> budgetPrintForms = budgetDocument.getBudgetPrintForms();
        CoeusReportGroupBean reportGroup = null;
        try {
            reportGroup = ReportConfigEngine.get("ProposalBudget");
        }
        catch (CoeusException e) {
            e.printStackTrace();
        }
        Map reports =  reportGroup.getReports();
        Iterator<Report> it = reports.values().iterator();
        while (it.hasNext()) {
            Report report = it.next();
            BudgetPrintForm printForm = new BudgetPrintForm();
            printForm.setBudgetReportId(report.getId());
            printForm.setBudgetReportName(report.getDispValue());
            budgetPrintForms.add(printForm);
        }
    }
    public AttachmentDataSource readBudgetPrintStream(BudgetDocument budgetDocument, String selectedBudgetPrintFormId) {
        AttachmentDataSource reportStream;
        if(selectedBudgetPrintFormId.equals("ProposalBudget/budgettotalbyperiod")){
            reportStream = readBudgetTotal(budgetDocument);
        }else if(selectedBudgetPrintFormId.equals("ProposalBudget/indsrlcumbudget")){
            reportStream = readIndustrialCumulativeBudget(budgetDocument);
        }else if(selectedBudgetPrintFormId.equals("ProposalBudget/Salaries")){
            reportStream = readBudgetSalaries(budgetDocument);
        }else{
            Hashtable repParams = new Hashtable();
            List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
            CoeusVector cvBudgetPeriods = new CoeusVector();
            for(BudgetPeriod budgetPeriod : budgetPeriods) {
                cvBudgetPeriods.add(coeusBean2KraBoConverterService.convert(budgetPeriod));
            }
            repParams.put("USER_ID", GlobalVariables.getUserSession().getPrincipalName());
            repParams.put("REPORT_ID", selectedBudgetPrintFormId);
            repParams.put("BUDGET_PERIODS", cvBudgetPeriods);
            repParams.put("BUDGET_INFO", coeusBean2KraBoConverterService.convert(budgetDocument));
            CoeusReportGroupBean reportGroup = null;
            try {
                reportGroup = ReportConfigEngine.get("ProposalBudget");
            }catch (CoeusException e) {
                e.printStackTrace();
            }
            String reportName = StringUtils.deleteWhitespace(reportGroup.getReport(selectedBudgetPrintFormId).getDispValue())+
                "P"+budgetDocument.getProposalNumber()+"V"+budgetDocument.getBudgetVersionNumber();
            reportStream = readStream(selectedBudgetPrintFormId,reportName,repParams);
        }
        return reportStream;
    }
    
    private HashMap getBudgetData(BudgetDocument budgetDocument) {
        List<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();
        List<BudgetLineItem> budgetLineItems = new ArrayList<BudgetLineItem>();
        HashMap budgetData = new HashMap();
        CoeusVector cvBudgetPeriods = new CoeusVector();
        CoeusVector cvBudgetDetails = new CoeusVector();
        CoeusVector cvBudgetDetailCalAmts = new CoeusVector();
        CoeusVector cvBudgetPersonnelDetails = new CoeusVector();
        CoeusVector cvBudgetPersonnelCalAmts = new CoeusVector();
        for(BudgetPeriod budgetPeriod : budgetPeriods) {
            cvBudgetPeriods.add(coeusBean2KraBoConverterService.convert(budgetPeriod));
            budgetLineItems = budgetPeriod.getBudgetLineItems();
            for(BudgetLineItem periodLineItem: budgetLineItems) {
                cvBudgetDetails.add(coeusBean2KraBoConverterService.convert(periodLineItem));
                List<BudgetLineItemCalculatedAmount> lineItemCalcAmts = periodLineItem.getBudgetCalculatedAmounts();
                for (BudgetLineItemCalculatedAmount object : lineItemCalcAmts) {
                    cvBudgetDetailCalAmts.add(coeusBean2KraBoConverterService.convert(object));
                }
                List<BudgetPersonnelDetails> budgetPersonnelDetails = periodLineItem.getBudgetPersonnelDetailsList();
                for(BudgetPersonnelDetails periodOnePersonnelDetail: budgetPersonnelDetails) {
                    cvBudgetPersonnelDetails.add(coeusBean2KraBoConverterService.convert(periodOnePersonnelDetail));
                    List<BudgetPersonnelCalculatedAmount> budgetPersonnelDetCalcAmounts = periodOnePersonnelDetail.getBudgetPersonnelCalculatedAmounts();
                    for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmount : budgetPersonnelDetCalcAmounts) {
                        cvBudgetPersonnelCalAmts.add(coeusBean2KraBoConverterService.convert(budgetPersonnelCalculatedAmount));
                    }
                }
            }
        }
        budgetData.put("budgetPeriods", cvBudgetPeriods);
        budgetData.put("budgetDetails", cvBudgetDetails);
        budgetData.put("budgetDetailCalAmts", cvBudgetDetailCalAmts);
        budgetData.put("budgetPersonnelDetails", cvBudgetPersonnelDetails);
        budgetData.put("budgetPersonnelCalAmts", cvBudgetPersonnelCalAmts);
        
        return budgetData;
    }
    
    private Vector getBudgetTotalBeanList(BudgetDocument budgetDocument){
        String queryKey = budgetDocument.getProposalNumber() + budgetDocument.getBudgetVersionNumber();
        BudgetPeriodBean budgetPeriodBean = new BudgetPeriodBean();
        BudgetTotalBean budgetTotalBean = new BudgetTotalBean();
        
        //Operators used to filter and retrieve unique data for the table
        Equals equalsBudgetPeriod, equalsCostElm, equalsRateClassCode, equalsRateTypeCode;
        NotEquals notEqualsCostElm, notEqualsRateClassCode, notEqualsRateTypeCode;
        And andRateClassType, andRateClassNotRateType;
        Or orOperator;
        
        int budgetPeriod = 0;
        int rateClassCode = 0, rateTypeCode = 0;
        String costElement, rateClassType;
        CoeusVector cvPeriodLineItems = new CoeusVector();
        CoeusVector cvCostElements = new CoeusVector();
        CoeusVector cvPeriodCalAmts = new CoeusVector();
        CoeusVector cvCEExists = new CoeusVector();
        CoeusVector cvCalAmts = new CoeusVector();
        CoeusVector cvCalAmtsExists = new CoeusVector();
        CoeusVector cvRateClassElements = new CoeusVector();
        CoeusVector cvTableData = new CoeusVector();
        BudgetDetailBean budgetDetailBean = new BudgetDetailBean ();
        BudgetDetailCalAmountsBean budgetDetailCalAmountsBean = new BudgetDetailCalAmountsBean ();
        
        
        try{
            HashMap budgetData = getBudgetData(budgetDocument);
            CoeusVector cvBudgetPeriods = (CoeusVector)budgetData.get("budgetPeriods");
            CoeusVector cvBudgetDetails = (CoeusVector)budgetData.get("budgetDetails");
            CoeusVector cvBudgetDetailCalAmts = (CoeusVector)budgetData.get("budgetDetailCalAmts");
            CoeusVector cvBudgetPersonnelDetails = (CoeusVector)budgetData.get("budgetPersonnelDetails");
            CoeusVector cvBudgetPersonnelCalAmts = (CoeusVector)budgetData.get("budgetPersonnelCalAmts");            
            
            budgetPeriods = cvBudgetPeriods.size();
            if( cvBudgetPeriods != null && cvBudgetPeriods.size() > 0){
                /** Table Data for budget line items */
                /** Loop through each budget period and get the unique cost element details */
                for( int periodIndex = 0; periodIndex < cvBudgetPeriods.size(); periodIndex++){
                    budgetPeriodBean = (BudgetPeriodBean) cvBudgetPeriods.get(periodIndex);
                    budgetPeriod = budgetPeriodBean.getBudgetPeriod();
                    equalsBudgetPeriod = new Equals(BUDGET_PERIOD, new Integer(budgetPeriod));
                    /** Filter and get all budget line item details for each period */
                    cvPeriodLineItems = cvBudgetDetails.filter(equalsBudgetPeriod);
                    if( cvPeriodLineItems != null && cvPeriodLineItems.size() > 0){
                       
                        while (true) {
                            /** Get the first element of the cvPeriodLineItems
                             * since it contains only one element at a time, as it is filtered
                             * after creating a new budgetTotalBean of unique cost element */
                            budgetDetailBean = ( BudgetDetailBean )cvPeriodLineItems.get(0);
                            costElement = budgetDetailBean.getCostElement();
                            equalsCostElm = new Equals(LINE_ITEM_COST_ELEMENT, costElement);
                            notEqualsCostElm = new NotEquals(LINE_ITEM_COST_ELEMENT, costElement);
                            cvCostElements = cvPeriodLineItems.filter(equalsCostElm);
                            /** Get the sum of Line Item Cost of all budgetDetailBeans
                             * in cvCostElements corresponding to the given cost element */
                            
                            //value = cvCostElements.sum(LINE_ITEM_COST, equalsCostElm);
                            // Bug fix #1835 - start
                            value = ((double)Math.round(cvCostElements.sum(LINE_ITEM_COST, equalsCostElm)*
                            Math.pow(10.0, 2) )) / 100;
                            // Bug fix #1835 - End
                            /** Checking if cost element is Empty or null */
                            if(costElement == null || costElement.equals(EMPTY_STRING)) {
                                cvPeriodLineItems = cvPeriodLineItems.filter(notEqualsCostElm);
                                if(cvPeriodLineItems.size() > 0) {
                                    continue;
                                }else {
                                    break;
                                }
                            }
                            
                            if (cvTableData != null && cvTableData.size() > 0) {
                                /** Filter the budgetTotalBean from cvTableData 
                                 * corresponding to the given cost element and store
                                 * it into the vector cvCEExists */
                                cvCEExists = cvTableData.filter(equalsCostElm);
                                if (cvCEExists.size() > 0) {
                                    budgetTotalBean = (BudgetTotalBean) cvCEExists.get(0);
                                    /** Remove the budgetTotalBean from cvTableData
                                     * corresponding to the given cost element */
                                    cvTableData = cvTableData.filter(notEqualsCostElm);
                                } else {
                                    /** create a new budgetTotalBean and set the required
                                     * properties other than the period cost */
                                    budgetTotalBean = new BudgetTotalBean();
                                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                    budgetTotalBean.setCostElement(budgetDetailBean.getCostElement());
                                    budgetTotalBean.setCostElementDescription(budgetDetailBean.getCostElementDescription());
                                }
                            } else {
                                /** Create a new budgetTotalBean and set the required
                                 * properties other than the period cost */
                                budgetTotalBean = new BudgetTotalBean();
                                budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                budgetTotalBean.setCostElement(budgetDetailBean.getCostElement());
                                budgetTotalBean.setCostElementDescription(budgetDetailBean.getCostElementDescription());
                            }
                            /** Set the period cost property for the existing budgetTotalBean */
                            budgetTotalBean.setPeriodCost(periodIndex, value);
                            cvTableData.addElement(budgetTotalBean);

                            /** Remove all the budgetDetailBeans from cvPeriodLineItems
                             * corresponding to the given cost element */
                            cvPeriodLineItems = cvPeriodLineItems.filter(notEqualsCostElm);

                            if (cvPeriodLineItems.size() == 0) {
                                break;
                            }
                        }
                    }
                }
                /** Table Data for budget line item calculated amounts */
                /* Loop through each budget period and get the unique
                 * line item calculated amounts */
                for( int periodIndex = 0; periodIndex < cvBudgetPeriods.size(); periodIndex++){
                    budgetPeriodBean = (BudgetPeriodBean) cvBudgetPeriods.get(periodIndex);
                    budgetPeriod = budgetPeriodBean.getBudgetPeriod();
                    equalsBudgetPeriod = new Equals(BUDGET_PERIOD, new Integer(budgetPeriod));
                    cvPeriodCalAmts = cvBudgetDetailCalAmts.filter(equalsBudgetPeriod);
                    if( cvPeriodCalAmts != null && cvPeriodCalAmts.size() > 0){
                        while (true) {
                            budgetDetailCalAmountsBean = (BudgetDetailCalAmountsBean)cvPeriodCalAmts.get(0);
                            rateClassCode = budgetDetailCalAmountsBean.getRateClassCode();
                            rateTypeCode = budgetDetailCalAmountsBean.getRateTypeCode();
                            equalsRateClassCode = new Equals(RATE_CLASS_CODE, new Integer(rateClassCode));
                            notEqualsRateClassCode = new NotEquals(RATE_CLASS_CODE, new Integer(rateClassCode));
                            equalsRateTypeCode = new Equals(RATE_TYPE_CODE, new Integer(rateTypeCode));
                            notEqualsRateTypeCode = new NotEquals(RATE_TYPE_CODE, new Integer(rateTypeCode));
                            andRateClassType = new And(equalsRateClassCode, equalsRateTypeCode);
                            andRateClassNotRateType = new And(equalsRateClassCode, notEqualsRateTypeCode);
                            /** The orOperator corresponds to the following condition
                             * ( rateClassCode == <rateClassCode> && rateTypeCode != <rateTypeCode> ) || 
                             * ( rateClassCode != <rateClassCode> ) */
                            orOperator = new Or(andRateClassNotRateType, notEqualsRateClassCode);
                            cvRateClassElements = cvPeriodCalAmts.filter(andRateClassType);
                            /** Get the sum of Calculated Cost of all budgetCalAmtsBeans
                             * in cvRateClassElements corresponding to the given rate class code
                             * and rate type code */
                           
//                            value = cvRateClassElements.sum(CALCULATED_COST, andRateClassType);
                            // Bug fix #1835 - start
                            value = ((double)Math.round(cvRateClassElements.sum(CALCULATED_COST, andRateClassType)*
                            Math.pow(10.0, 2) )) / 100;
                            // Bug fix #1835 - End
                            if (cvCalAmts != null && cvCalAmts.size() > 0) {
                                /** Filter the budgetTotalBean from cvTableData 
                                 * corresponding to the given rate class code 
                                 * and rate type code. Store it into cvCalAmtsExists */
                                cvCalAmtsExists = cvCalAmts.filter(andRateClassType);
                                if (cvCalAmtsExists.size() > 0) {
                                    budgetTotalBean = (BudgetTotalBean) cvCalAmtsExists.get(0);
                                    /** Remove the budgetTotalBean from cvTableData
                                     * corresponding to the given condition in orOperator */
                                    cvCalAmts = cvCalAmts.filter(orOperator);
                                    
                                } else {
                                    /** Create a new budgetTotalBean and set the required
                                     * properties other than the period cost */
                                    budgetTotalBean = new BudgetTotalBean();
                                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                    budgetTotalBean.setCostElement(EMPTY_STRING);
                                    rateClassType = budgetDetailCalAmountsBean.getRateClassType();
                                    /** If rate class type is overhead then display
                                     * the rate class description as OH */
                                    if( rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ){
                                        budgetTotalBean.setCostElementDescription(OH +
                                                budgetDetailCalAmountsBean.getRateTypeDescription());
                                    }else{
                                        budgetTotalBean.setCostElementDescription(
                                                budgetDetailCalAmountsBean.getRateClassDescription() + " - " +
                                                budgetDetailCalAmountsBean.getRateTypeDescription());
                                    }
                                    budgetTotalBean.setRateClassCode(budgetDetailCalAmountsBean.getRateClassCode());
                                    budgetTotalBean.setRateTypeCode(budgetDetailCalAmountsBean.getRateTypeCode());
                                }
                            } else {
                                /** Create a new budgetTotalBean and set the required
                                 * properties other than the period cost */
                                budgetTotalBean = new BudgetTotalBean();
                                budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                budgetTotalBean.setCostElement(EMPTY_STRING);
                                rateClassType = budgetDetailCalAmountsBean.getRateClassType();
                                /** If rate class type is overhead then display
                                 * the rate class description as OH */
                                if( rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ){
                                    budgetTotalBean.setCostElementDescription(OH +
                                            budgetDetailCalAmountsBean.getRateTypeDescription());
                                }else{
                                    budgetTotalBean.setCostElementDescription(
                                            budgetDetailCalAmountsBean.getRateClassDescription() + " - " +
                                            budgetDetailCalAmountsBean.getRateTypeDescription());
                                }
                                budgetTotalBean.setRateClassCode(budgetDetailCalAmountsBean.getRateClassCode());
                                budgetTotalBean.setRateTypeCode(budgetDetailCalAmountsBean.getRateTypeCode());

                            }
                            /** Set the period cost property for the existing budgetTotalBean */
                            budgetTotalBean.setPeriodCost(periodIndex, value);
                            cvCalAmts.addElement(budgetTotalBean);

                            /** Remove all the budgetCalAmtsBeans from cvPeriodCalAmts
                             * corresponding to the given operator */
                            cvPeriodCalAmts = cvPeriodCalAmts.filter(orOperator);

                            if (cvPeriodCalAmts.size() == 0) {
                                break;
                            }
                        }
                    }
                }
                if( cvTableData != null && cvTableData.size() > 0){
                    /** Sort the vector based on <CODE>costElement</CODE>  */
                    cvTableData.sort(LINE_ITEM_COST_ELEMENT, true);
                    initialVecSize = cvTableData.size();
                }
                if( cvCalAmts != null && cvCalAmts.size() > 0){
                    /** Sort the vector based on <CODE>rateClassCode</CODE> */
                    cvCalAmts.sort(RATE_CLASS_CODE, true);
                }
                for( int index = 0; index < cvCalAmts.size(); index++){
                    cvTableData.addElement(cvCalAmts.get(index));
                }
                
                if( cvBudgetDetails != null &&  cvBudgetDetails.size() > 0 ){
                    boolean hasCostElement = false;
                    /** Check if the budgetDetailBean contains Cost Element */
                    for( int budgetDetailIndex = 0; budgetDetailIndex < cvBudgetDetails.size();
                    budgetDetailIndex++){
                        budgetDetailBean = (BudgetDetailBean)cvBudgetDetails.get(budgetDetailIndex);
                        if( !budgetDetailBean.getCostElement().equals(EMPTY_STRING) ) {
                            hasCostElement = true;
                        }
                    }
                    
                    if( hasCostElement ){
                        budgetTotalBean = new BudgetTotalBean();
                        budgetTotalBean.setCostElement(EMPTY_STRING);
                        budgetTotalBean.setCostElementDescription(TOTAL);
                        budgetTotalBean.setBudgetPeriods(budgetPeriods);
                        for(int index = 0; index < budgetPeriods; index++ ){
                            // Bug fix #1835 - start
                            //value = cvTableData.sum(PERIOD_COST_VALUE , DataType.getClass(DataType.INT) , new Integer(index));
                            value = ((double)Math.round(cvTableData.sum(PERIOD_COST_VALUE , DataType.getClass(DataType.INT) , new Integer(index))*Math.pow(10.0, 2) )) / 100;
                            // Bug fix #1835 - End
                            budgetTotalBean.setPeriodCost(index, value);
                        }
                        cvTableData.addElement(budgetTotalBean);
                    }

                }
                /** Data for the Total column of the table */
                /** Set the total property for all the budgetTotalBeans */
                for( int totalIndex = 0; totalIndex < cvTableData.size(); totalIndex ++){
                    budgetTotalBean = ( BudgetTotalBean )cvTableData.get(totalIndex);
                    budgetTotalBean.setTotal();
                }                
            }
        }catch (NumberFormatException numberFormatException ){
            numberFormatException.getMessage();
        }
        return cvTableData;
    }

    private Vector getIndsrlCumulativeData(BudgetDocument budgetDocument) {
        String queryKey = budgetDocument.getProposalNumber() + budgetDocument.getBudgetVersionNumber();
        BudgetPeriodBean budgetPeriodBean = new BudgetPeriodBean();
        BudgetTotalBean budgetTotalBean = new BudgetTotalBean();
        
        //Operators used to filter and retrieve unique data for the table
        Equals equalsBudgetPeriod, equalsCostElm, equalsRateClassCode, equalsRateTypeCode;
        NotEquals notEqualsCostElm, notEqualsRateClassCode, notEqualsRateTypeCode;
        And andRateClassType, andRateClassNotRateType;
        Or orOperator;
        
        int budgetPeriod = 0;
        int rateClassCode = 0, rateTypeCode = 0;
        String costElement, rateClassType;
        CoeusVector cvPeriodLineItems = new CoeusVector();
        CoeusVector cvCostElements = new CoeusVector();
        CoeusVector cvPeriodCalAmts = new CoeusVector();
        CoeusVector cvCEExists = new CoeusVector();
        CoeusVector cvCalAmts = new CoeusVector();
        CoeusVector cvCalAmtsExists = new CoeusVector();
        CoeusVector cvRateClassElements = new CoeusVector();
        CoeusVector indsrlCumData = new CoeusVector();
        BudgetDetailBean budgetDetailBean = new BudgetDetailBean ();
        BudgetDetailCalAmountsBean budgetDetailCalAmountsBean = new BudgetDetailCalAmountsBean ();
        
        
        try{
            HashMap budgetData = getBudgetData(budgetDocument);
            CoeusVector cvBudgetPeriods = (CoeusVector)budgetData.get("budgetPeriods");
            CoeusVector cvBudgetDetails = (CoeusVector)budgetData.get("budgetDetails");
            CoeusVector cvBudgetDetailCalAmts = (CoeusVector)budgetData.get("budgetDetailCalAmts");
            CoeusVector cvBudgetPersonnelDetails = (CoeusVector)budgetData.get("budgetPersonnelDetails");
            CoeusVector cvBudgetPersonnelCalAmts = (CoeusVector)budgetData.get("budgetPersonnelCalAmts");            

            budgetPeriods = cvBudgetPeriods.size();
            if( cvBudgetPeriods != null && cvBudgetPeriods.size() > 0){
                /** Table Data for budget line items */
                /** Loop through each budget period and get the unique cost element details */
                for( int periodIndex = 0; periodIndex < cvBudgetPeriods.size(); periodIndex++){
                    budgetPeriodBean = (BudgetPeriodBean) cvBudgetPeriods.get(periodIndex);
                    budgetPeriod = budgetPeriodBean.getBudgetPeriod();
                    equalsBudgetPeriod = new Equals(BUDGET_PERIOD, new Integer(budgetPeriod));
                    /*
                     *Added by Geo as an enhancement to Industrial Cumulative budget on 02/06/2006
                     */
                    //BEGIN #1
                    cvPeriodCalAmts = cvBudgetDetailCalAmts.filter(equalsBudgetPeriod);
                    //END
                    
                    /** Filter and get all budget line item details for each period */
                    cvPeriodLineItems = cvBudgetDetails.filter(equalsBudgetPeriod);
                    if( cvPeriodLineItems != null && cvPeriodLineItems.size() > 0){
                       
                        while (true) {
                            /** Get the first element of the cvPeriodLineItems
                             * since it contains only one element at a time, as it is filtered
                             * after creating a new budgetTotalBean of unique cost element */
                            budgetDetailBean = ( BudgetDetailBean )cvPeriodLineItems.get(0);
                            costElement = budgetDetailBean.getCostElement();
                            equalsCostElm = new Equals(LINE_ITEM_COST_ELEMENT, costElement);
                            notEqualsCostElm = new NotEquals(LINE_ITEM_COST_ELEMENT, costElement);
                            cvCostElements = cvPeriodLineItems.filter(equalsCostElm);
                            /** Get the sum of Line Item Cost of all budgetDetailBeans
                             * in cvCostElements corresponding to the given cost element */
                            
                            //value = cvCostElements.sum(LINE_ITEM_COST, equalsCostElm);
                            // Bug fix #1835 - start
                            value = ((double)Math.round(cvCostElements.sum(LINE_ITEM_COST, equalsCostElm)*
                            Math.pow(10.0, 2) )) / 100;
                            /*
                             *Added by Geo as an enhancement to Industrial Cumulative budget on 02/06/2006
                             */
                            //BEGIN #1
//                            if(indCum){
                                int ceListSize = cvCostElements.size();
                                for(int ceIndex=0;ceIndex<ceListSize;ceIndex++){
                                    BudgetDetailBean detBean = ( BudgetDetailBean )cvCostElements.get(ceIndex);
                                    Equals equalsLiNum = new Equals("lineItemNumber",new Integer(detBean.getLineItemNumber()));
                                    Equals equalsOhType = new Equals("rateClassType",RateClassType.OVERHEAD.getRateClassType());
                                    Equals equalsEbType = new Equals("rateClassType",RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
                                    Or ohOrEb = new Or(equalsOhType,equalsEbType);
                                    And liItemNumAndEbOrOh = new And(ohOrEb, equalsLiNum);
                                    CoeusVector cvLICalAmounts = cvPeriodCalAmts.filter(liItemNumAndEbOrOh);
                                    double ohCost = cvLICalAmounts.sum("calculatedCost");
                                    ohCost = ((double)Math.round(ohCost*Math.pow(10.0, 2) )) / 100;
    //                                System.out.println("ohcost for costElement =>"+ohCost);
                                    value +=ohCost;
                                }
//                            }
                            //END 
                            // Bug fix #1835 - End
                            /** Checking if cost element is Empty or null */
                            if(costElement == null || costElement.equals(EMPTY_STRING)) {
                                cvPeriodLineItems = cvPeriodLineItems.filter(notEqualsCostElm);
                                if(cvPeriodLineItems.size() > 0) {
                                    continue;
                                }else {
                                    break;
                                }
                            }
                            
                            if (indsrlCumData != null && indsrlCumData.size() > 0) {
                                /** Filter the budgetTotalBean from indsrlCumData 
                                 * corresponding to the given cost element and store
                                 * it into the vector cvCEExists */
                                cvCEExists = indsrlCumData.filter(equalsCostElm);
                                if (cvCEExists.size() > 0) {
                                    budgetTotalBean = (BudgetTotalBean) cvCEExists.get(0);
                                    /** Remove the budgetTotalBean from indsrlCumData
                                     * corresponding to the given cost element */
                                    indsrlCumData = indsrlCumData.filter(notEqualsCostElm);
                                } else {
                                    /** create a new budgetTotalBean and set the required
                                     * properties other than the period cost */
                                    budgetTotalBean = new BudgetTotalBean();
                                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                    budgetTotalBean.setCostElement(budgetDetailBean.getCostElement());
                                    budgetTotalBean.setCostElementDescription(budgetDetailBean.getCostElementDescription());
                                }
                            } else {
                                /** Create a new budgetTotalBean and set the required
                                 * properties other than the period cost */
                                budgetTotalBean = new BudgetTotalBean();
                                budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                budgetTotalBean.setCostElement(budgetDetailBean.getCostElement());
                                budgetTotalBean.setCostElementDescription(budgetDetailBean.getCostElementDescription());
                            }
                            /** Set the period cost property for the existing budgetTotalBean */
                            budgetTotalBean.setPeriodCost(periodIndex, value);
                            indsrlCumData.addElement(budgetTotalBean);

                            /** Remove all the budgetDetailBeans from cvPeriodLineItems
                             * corresponding to the given cost element */
                            cvPeriodLineItems = cvPeriodLineItems.filter(notEqualsCostElm);

                            if (cvPeriodLineItems.size() == 0) {
                                break;
                            }
                        }
                    }
                }
                /** Table Data for budget line item calculated amounts */
                /* Loop through each budget period and get the unique
                 * line item calculated amounts */
                for( int periodIndex = 0; periodIndex < cvBudgetPeriods.size(); periodIndex++){
                    budgetPeriodBean = (BudgetPeriodBean) cvBudgetPeriods.get(periodIndex);
                    budgetPeriod = budgetPeriodBean.getBudgetPeriod();
                    equalsBudgetPeriod = new Equals(BUDGET_PERIOD, new Integer(budgetPeriod));
                    cvPeriodCalAmts = cvBudgetDetailCalAmts.filter(equalsBudgetPeriod);
                    boolean ohORebFlag = false;
                    if( cvPeriodCalAmts != null && cvPeriodCalAmts.size() > 0){
                        while (true) {
                            budgetDetailCalAmountsBean = (BudgetDetailCalAmountsBean)cvPeriodCalAmts.get(0);
                            rateClassCode = budgetDetailCalAmountsBean.getRateClassCode();
                            rateTypeCode = budgetDetailCalAmountsBean.getRateTypeCode();
                            equalsRateClassCode = new Equals(RATE_CLASS_CODE, new Integer(rateClassCode));
                            notEqualsRateClassCode = new NotEquals(RATE_CLASS_CODE, new Integer(rateClassCode));
                            equalsRateTypeCode = new Equals(RATE_TYPE_CODE, new Integer(rateTypeCode));
                            notEqualsRateTypeCode = new NotEquals(RATE_TYPE_CODE, new Integer(rateTypeCode));
                            andRateClassType = new And(equalsRateClassCode, equalsRateTypeCode);
                            andRateClassNotRateType = new And(equalsRateClassCode, notEqualsRateTypeCode);
                            /** The orOperator corresponds to the following condition
                             * ( rateClassCode == <rateClassCode> && rateTypeCode != <rateTypeCode> ) || 
                             * ( rateClassCode != <rateClassCode> ) */
                            orOperator = new Or(andRateClassNotRateType, notEqualsRateClassCode);
                            cvRateClassElements = cvPeriodCalAmts.filter(andRateClassType);
                            /** Get the sum of Calculated Cost of all budgetCalAmtsBeans
                             * in cvRateClassElements corresponding to the given rate class code
                             * and rate type code */
                           
//                            value = cvRateClassElements.sum(CALCULATED_COST, andRateClassType);
                            // Bug fix #1835 - start
                            value = ((double)Math.round(cvRateClassElements.sum(CALCULATED_COST, andRateClassType)*
                            Math.pow(10.0, 2) )) / 100;
                            
                            // Bug fix #1835 - End
                            if (cvCalAmts != null && cvCalAmts.size() > 0) {
                                /** Filter the budgetTotalBean from indsrlCumData 
                                 * corresponding to the given rate class code 
                                 * and rate type code. Store it into cvCalAmtsExists */
                                cvCalAmtsExists = cvCalAmts.filter(andRateClassType);
                                if (cvCalAmtsExists.size() > 0) {
                                    budgetTotalBean = (BudgetTotalBean) cvCalAmtsExists.get(0);
                                    /** Remove the budgetTotalBean from indsrlCumData
                                     * corresponding to the given condition in orOperator */
                                    cvCalAmts = cvCalAmts.filter(orOperator);
                                    
                                } else {
                                    /** Create a new budgetTotalBean and set the required
                                     * properties other than the period cost */
                                    budgetTotalBean = new BudgetTotalBean();
                                    budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                    budgetTotalBean.setCostElement(EMPTY_STRING);
                                    rateClassType = budgetDetailCalAmountsBean.getRateClassType();
                                    /*
                                     *Added by Geo as an enhancement to Industrial Cumulative budget on 02/06/2006
                                     */
                                    //BEGIN #1
                                    if( (rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ||
                                        rateClassType.equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType())) ){
                                        ohORebFlag = true;
                                    }
                                    //END #1
                                    if(  (rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ||
                                        rateClassType.equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType()) )){
                                        ohORebFlag = true;
                                    }
                                /** If rate class type is overhead then display
                                     * the rate class description as OH */
                                    if( rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ){
                                        budgetTotalBean.setCostElementDescription(OH +
                                                budgetDetailCalAmountsBean.getRateTypeDescription());
                                    }else{
                                        budgetTotalBean.setCostElementDescription(
                                                budgetDetailCalAmountsBean.getRateClassDescription() + " - " +
                                                budgetDetailCalAmountsBean.getRateTypeDescription());
                                    }
                                    budgetTotalBean.setRateClassCode(budgetDetailCalAmountsBean.getRateClassCode());
                                    budgetTotalBean.setRateTypeCode(budgetDetailCalAmountsBean.getRateTypeCode());
                                }
                            } else {
                                /** Create a new budgetTotalBean and set the required
                                 * properties other than the period cost */
                                budgetTotalBean = new BudgetTotalBean();
                                budgetTotalBean.setBudgetPeriods(budgetPeriods);
                                budgetTotalBean.setCostElement(EMPTY_STRING);
                                rateClassType = budgetDetailCalAmountsBean.getRateClassType();
                                /*
                                 *Added by Geo as an enhancement to Industrial Cumulative budget on 02/06/2006
                                 */
                                //BEGIN #1
                                if( (rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ||
                                    rateClassType.equals(RateClassType.EMPLOYEE_BENEFITS.getRateClassType())) ){
                                    ohORebFlag = true;
                                }
                                //END #1

                                /** If rate class type is overhead then display
                                 * the rate class description as OH */
                                if( rateClassType.equals(RateClassType.OVERHEAD.getRateClassType()) ){
                                    
                                    budgetTotalBean.setCostElementDescription(OH +
                                            budgetDetailCalAmountsBean.getRateTypeDescription());
                                }else{
                                    budgetTotalBean.setCostElementDescription(
                                            budgetDetailCalAmountsBean.getRateClassDescription() + " - " +
                                            budgetDetailCalAmountsBean.getRateTypeDescription());
                                }
                                budgetTotalBean.setRateClassCode(budgetDetailCalAmountsBean.getRateClassCode());
                                budgetTotalBean.setRateTypeCode(budgetDetailCalAmountsBean.getRateTypeCode());

                            }
                            /** Set the period cost property for the existing budgetTotalBean */
                            budgetTotalBean.setPeriodCost(periodIndex, value);
                            
                            if( !ohORebFlag ){
                                 cvCalAmts.addElement(budgetTotalBean);
                            }

                            /** Remove all the budgetCalAmtsBeans from cvPeriodCalAmts
                             * corresponding to the given operator */
                            cvPeriodCalAmts = cvPeriodCalAmts.filter(orOperator);

                            if (cvPeriodCalAmts.size() == 0) {
                                break;
                            }
                        }
                    }
                }
                if( indsrlCumData != null && indsrlCumData.size() > 0){
                    /** Sort the vector based on <CODE>costElement</CODE>  */
                    indsrlCumData.sort(LINE_ITEM_COST_ELEMENT, true);
                    initialVecSize = indsrlCumData.size();
                }
                if( cvCalAmts != null && cvCalAmts.size() > 0){
                    /** Sort the vector based on <CODE>rateClassCode</CODE> */
                    cvCalAmts.sort(RATE_CLASS_CODE, true);
                }
                for( int index = 0; index < cvCalAmts.size(); index++){
                    indsrlCumData.addElement(cvCalAmts.get(index));
                }
                
                if( cvBudgetDetails != null &&  cvBudgetDetails.size() > 0 ){
                    boolean hasCostElement = false;
                    /** Check if the budgetDetailBean contains Cost Element */
                    for( int budgetDetailIndex = 0; budgetDetailIndex < cvBudgetDetails.size();
                    budgetDetailIndex++){
                        budgetDetailBean = (BudgetDetailBean)cvBudgetDetails.get(budgetDetailIndex);
                        if( !budgetDetailBean.getCostElement().equals(EMPTY_STRING) ) {
                            hasCostElement = true;
                        }
                    }
                    
                    if( hasCostElement ){
                        budgetTotalBean = new BudgetTotalBean();
                        budgetTotalBean.setCostElement(EMPTY_STRING);
                        budgetTotalBean.setCostElementDescription(TOTAL);
                        budgetTotalBean.setBudgetPeriods(budgetPeriods);
                        for(int index = 0; index < budgetPeriods; index++ ){
                            // Bug fix #1835 - start
                            //value = indsrlCumData.sum(PERIOD_COST_VALUE , DataType.getClass(DataType.INT) , new Integer(index));
                            value = ((double)Math.round(indsrlCumData.sum(PERIOD_COST_VALUE , DataType.getClass(DataType.INT) , new Integer(index))*Math.pow(10.0, 2) )) / 100;
                            // Bug fix #1835 - End
                            budgetTotalBean.setPeriodCost(index, value);
                        }
                        indsrlCumData.addElement(budgetTotalBean);
                    }

                }
                /** Data for the Total column of the table */
                /** Set the total property for all the budgetTotalBeans */
                for( int totalIndex = 0; totalIndex < indsrlCumData.size(); totalIndex ++){
                    budgetTotalBean = ( BudgetTotalBean )indsrlCumData.get(totalIndex);
                    budgetTotalBean.setTotal();
                }                
            }
        }catch (NumberFormatException numberFormatException ){
            numberFormatException.getMessage();
        }
        return indsrlCumData;        
    }
    /**
     * Gets the coeusBean2KraBoConverterService attribute. 
     * @return Returns the coeusBean2KraBoConverterService.
     */
    public CoeusBean2KraBoConverterService getCoeusBean2KraBoConverterService() {
        return coeusBean2KraBoConverterService;
    }
    /**
     * Sets the coeusBean2KraBoConverterService attribute value.
     * @param coeusBean2KraBoConverterService The coeusBean2KraBoConverterService to set.
     */
    public void setCoeusBean2KraBoConverterService(CoeusBean2KraBoConverterService coeusBean2KraBoConverterService) {
        this.coeusBean2KraBoConverterService = coeusBean2KraBoConverterService;
    }
    
    
    public boolean printBudgetForms(BudgetDocument budgetDocument, String[] selectedBudgetPrintFormId, HttpServletResponse response) {
        List<InputStream> pdfs = new ArrayList<InputStream>();
        String fileName = "merge";
        String contentType = null;
        List<String> pageSize = new ArrayList();
        boolean reportGenerated = true;
        int k = 0;

        try {
            for (int i = 0; i < selectedBudgetPrintFormId.length; i++) {
                AttachmentDataSource dataStream = readBudgetPrintStream(budgetDocument, selectedBudgetPrintFormId[i]);
                // EX : if no personnel set up, then salary report will be null
                if (dataStream != null) {
                    pdfs.add(new ByteArrayInputStream(dataStream.getContent()));
                    //printPDF(dataStream.getContent(),PrintServiceLookup.lookupDefaultPrintService());
                    String reptFileName = dataStream.getFileName();
                    fileName = fileName + "_" + reptFileName.substring(0, reptFileName.indexOf(".pdf"));
                    contentType = dataStream.getContentType();
                    pageSize.add(k++, REPT_PAGESIZE_MAP.get(selectedBudgetPrintFormId[i]));
                }
            }

            if (!pdfs.isEmpty()) {
                OutputStream output = new ByteArrayOutputStream();
                concatPDFs(pdfs, output, pageSize, true);
    
                try {
                    WebUtils.saveMimeOutputStreamAsFile(response, contentType, (ByteArrayOutputStream) output, fileName + ".pdf");
    
                }
                finally {
                    try {
                        if (output != null) {
                            output.close();
                            output = null;
                        }
                    }
                    catch (IOException ioEx) {
                        reportGenerated = false;
                        LOG.warn(ioEx.getMessage(), ioEx);
                    }
    
                }
            } else {
                reportGenerated = false;
            }
        }
        catch (Exception ex) {
            reportGenerated = false;
            LOG.warn(ex);
        }
        
        return reportGenerated;
        
    }
    
    private void concatPDFs(List<InputStream> streamOfPDFFiles, OutputStream outputStream, List <String> pageSize, boolean paginate) {

        Document document = new Document();
        document.setPageSize(PageSize.A4);
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            if (pageSize.get(0).equals("1")) {
                // need to set it before document.open(); otherwise, the first page is always the default size
                document.setPageSize(PageSize.A4.rotate());                
            }
            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            int k = 0;
            while (iteratorPDFReader.hasNext()) {
                if (pageSize.get(k++).equals("0")) {
                    document.setPageSize(PageSize.A4);                    
                } else {
                    document.setPageSize(PageSize.A4.rotate());
                }
                PdfReader pdfReader = iteratorPDFReader.next();

                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);

                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        //cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            //outputStream.flush();
            document.close();
            //outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (document.isOpen())
                document.close();
            //try {
            //    if (outputStream != null)
            //        outputStream.close();
            //}
            //catch (IOException ioe) {
            //    ioe.printStackTrace();
            //}
        }
    }
    
//    private void printPDF(byte[] data, PrintService printService) throws PdfException, PrinterException {
//        try {
//            // Open & decode the pdf file
//            PdfDecoder decode_pdf = new PdfDecoder(true);
//            decode_pdf.openPdfArray(data); // can use openPdfArray(byte[] data)
//
//            // Get the total number of pages in the pdf file
//            int pageCount = decode_pdf.getPageCount();
//            // set to print all pages
//            decode_pdf.setPagePrintRange(1, pageCount);
//            // Auto-rotate and scale flag
//            decode_pdf.setPrintAutoRotateAndCenter(false);
//            // Are we printing the current area only
//            decode_pdf.setPrintCurrentView(false);
//            // set mode - see org.jpedal.objects.contstants.PrinterOptions for all values
//            // the pdf file already is in the desired format. So turn off scaling
//            decode_pdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_NONE);
//            // by default scaling will center on page as well.
//            decode_pdf.setCenterOnScaling(false);
//            // flag if we use paper size or PDF size.
//            // Use PDF size as it already has the desired paper size
//            decode_pdf.setUsePDFPaperSize(true);
//            // setup print job and objects
//            PrinterJob printJob = PrinterJob.getPrinterJob();
//            printJob.setPrintService(printService);
//            // setup Java Print Service (JPS) to use JPedal
//            printJob.setPageable(decode_pdf);
//            // Print the file to the desired printer
//            printJob.print();
//        }
//        catch (Exception e) {
//        }
//    }
//

    
    
}

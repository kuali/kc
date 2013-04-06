/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.budget.service.impl;


import gov.grants.apply.system.globalV10.HashValueDocument.HashValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.XPathAPI;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItem;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardPeriodDetail;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardsRule;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lowagie.text.pdf.PRStream;
import com.lowagie.text.pdf.PdfArray;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNameTree;
import com.lowagie.text.pdf.PdfObject;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.XfaForm;

/**
 * This class...
 */
public class BudgetSubAwardServiceImpl implements BudgetSubAwardService {
    private static final String DUPLICATE_FILE_NAMES =  "Duplicate PDF Attachment File Names"; 
    private static final String XFA_NS = "http://www.xfa.org/schema/xfa-data/1.0/";
    private static final Log LOG = LogFactory.getLog(BudgetSubAwardServiceImpl.class);
    
    private ParameterService parameterService;
    private BudgetService budgetService;
    private BusinessObjectService businessObjectService;


    /**
     * @see org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService#populateBudgetSubAwardFiles(org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards)
     */
    public void populateBudgetSubAwardFiles(Budget budget, BudgetSubAwards subAward, String newFileName, byte[] newFileData) {
        subAward.setSubAwardStatusCode(1);
        BudgetSubAwardFiles newSubAwardFile = new BudgetSubAwardFiles();
        newSubAwardFile.setSubAwardXfdFileData(newFileData);
        subAward.getBudgetSubAwardAttachments().clear();
        subAward.getBudgetSubAwardFiles().clear();
        subAward.getBudgetSubAwardFiles().add(newSubAwardFile);
        
        boolean subawardBudgetExtracted  = false;
        
        try {
            byte[] pdfFileContents = newSubAwardFile.getSubAwardXfdFileData();
            subAward.setSubAwardXfdFileData(pdfFileContents);
            PdfReader  reader = new PdfReader(pdfFileContents);
            byte[] xmlContents=getXMLFromPDF(reader);
            subawardBudgetExtracted = (xmlContents!=null && xmlContents.length>0);
            if(subawardBudgetExtracted){
                Map fileMap = extractAttachments(reader);
                updateXML(xmlContents, fileMap, subAward, budget);
            }
        }catch (Exception e) {
            LOG.error("Not able to extract xml from pdf",e);
            subawardBudgetExtracted = false;
        }
        
        newSubAwardFile.setSubAwardXfdFileData(subAward.getSubAwardXfdFileData());
        if (subawardBudgetExtracted) {
            newSubAwardFile.setSubAwardXmlFileData(new String(subAward.getSubAwardXmlFileData()));
        }
        newSubAwardFile.setSubAwardXfdFileName(newFileName);
        newSubAwardFile.setBudgetId(subAward.getBudgetId());
        newSubAwardFile.setSubAwardNumber(subAward.getSubAwardNumber());
        subAward.setSubAwardXfdFileName(newFileName);
        subAward.setXfdUpdateUser(getLoggedInUserNetworkId());
        subAward.setXfdUpdateTimestamp(CoreApiServiceLocator.getDateTimeService().getCurrentTimestamp());
        subAward.setXmlUpdateUser(getLoggedInUserNetworkId());
        subAward.setXmlUpdateTimestamp(CoreApiServiceLocator.getDateTimeService().getCurrentTimestamp());
    }
    
    public void removeSubAwardAttachment(BudgetSubAwards subAward) {
        subAward.setFormName(null);
        subAward.setNamespace(null);
        subAward.setSubAwardXfdFileData(null);
        subAward.setSubAwardXfdFileName(null);
        subAward.setSubAwardXmlFileData(null);
        subAward.setXfdUpdateUser(null);
        subAward.getBudgetSubAwardAttachments().clear();
        subAward.getBudgetSubAwardFiles().clear();
        subAward.setXfdUpdateUser(getLoggedInUserNetworkId());
        subAward.setXfdUpdateTimestamp(CoreApiServiceLocator.getDateTimeService().getCurrentTimestamp());
        subAward.setXmlUpdateUser(getLoggedInUserNetworkId());
        subAward.setXmlUpdateTimestamp(CoreApiServiceLocator.getDateTimeService().getCurrentTimestamp());        
    }
    
    public void prepareBudgetSubAwards(Budget budget) {
        populateBudgetSubAwardAttachments(budget);
        for (BudgetSubAwards subAward : budget.getBudgetSubAwards()) {
            for (BudgetPeriod period : budget.getBudgetPeriods()) {
                BudgetSubAwardPeriodDetail detail = null;
                for (BudgetSubAwardPeriodDetail curDetail : subAward.getBudgetSubAwardPeriodDetails()) {
                    if (ObjectUtils.equals(curDetail.getBudgetPeriod(), period.getBudgetPeriod())) {
                        detail = curDetail;
                        break;
                    }
                }
                if (detail == null) {
                    subAward.getBudgetSubAwardPeriodDetails().add(new BudgetSubAwardPeriodDetail(subAward, period));
                }
            }
        }
    }
    
    public void generateSubAwardLineItems(BudgetSubAwards subAward, Budget budget) {
        BudgetDecimal amountChargeFA = new BudgetDecimal(25000);
        String directLtCostElement = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_DIRECT_LT_25K_PARAM);
        String directGtCostElement = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_DIRECT_GT_25K_PARAM);
        String inDirectLtCostElement = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_F_AND_A_LT_25K_PARAM);
        String inDirectGtCostElement = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.SUBCONTRACTOR_F_AND_A_GT_25K_PARAM);
        for (BudgetSubAwardPeriodDetail detail : subAward.getBudgetSubAwardPeriodDetails()) {
            BudgetPeriod budgetPeriod = findBudgetPeriod(detail, budget);
            List<BudgetLineItem> currentLineItems = findSubAwardLineItems(budgetPeriod, subAward.getSubAwardNumber());
            //zero out existing line items before recalculating
            for (BudgetLineItem item : currentLineItems) {
                item.setDirectCost(BudgetDecimal.ZERO);
                item.setCostSharingAmount(BudgetDecimal.ZERO);
                item.setSubAwardNumber(subAward.getSubAwardNumber());
                item.setLineItemDescription(subAward.getOrganizationName());
            }
            if (BudgetDecimal.returnZeroIfNull(detail.getDirectCost()).isNonZero() || hasBeenChanged(detail, true)) {
                BudgetDecimal ltValue = lesserValue(detail.getDirectCost(), amountChargeFA);
                BudgetDecimal gtValue = detail.getDirectCost().subtract(ltValue);
                BudgetLineItem lt = findOrCreateLineItem(currentLineItems, detail, subAward, budgetPeriod, directLtCostElement);
                lt.setLineItemCost(ltValue);
                BudgetLineItem gt = findOrCreateLineItem(currentLineItems, detail, subAward, budgetPeriod, directGtCostElement);
                gt.setLineItemCost(gtValue);
                amountChargeFA = amountChargeFA.subtract(ltValue);
            }
            if (BudgetDecimal.returnZeroIfNull(detail.getIndirectCost()).isNonZero() || hasBeenChanged(detail, false)) {
                BudgetDecimal ltValue = lesserValue(detail.getIndirectCost(), amountChargeFA);
                BudgetDecimal gtValue = detail.getIndirectCost().subtract(ltValue);
                BudgetLineItem lt = findOrCreateLineItem(currentLineItems, detail, subAward, budgetPeriod, inDirectLtCostElement);
                lt.setLineItemCost(ltValue);
                BudgetLineItem gt = findOrCreateLineItem(currentLineItems, detail, subAward, budgetPeriod, inDirectGtCostElement);
                gt.setLineItemCost(gtValue);
                amountChargeFA = amountChargeFA.subtract(ltValue);
            }
            Collections.sort(currentLineItems, new Comparator<BudgetLineItem>() {
                public int compare(BudgetLineItem arg0, BudgetLineItem arg1) {
                    return arg0.getLineItemNumber().compareTo(arg1.getLineItemNumber());
                }
            });
            Iterator<BudgetLineItem> iter = currentLineItems.iterator();
            while (iter.hasNext()) {
                BudgetLineItem lineItem = iter.next();
                if (BudgetDecimal.returnZeroIfNull(lineItem.getLineItemCost()).isZero()) {
                    budgetPeriod.getBudgetLineItems().remove(lineItem);
                    iter.remove();
                } else {
                    if (!budgetPeriod.getBudgetLineItems().contains(lineItem)) {
                        budgetPeriod.getBudgetLineItems().add(lineItem);
                    }
                }
            }
            if (!currentLineItems.isEmpty() && BudgetDecimal.returnZeroIfNull(detail.getCostShare()).isNonZero()) {
                currentLineItems.get(0).setCostSharingAmount(detail.getCostShare());
            }
        }
    }
    
    /**
     * 
     * This method checks to see if the BudgetSubAwardPeriodDetail has changed from the database version.  If checkDirect is true then it checks on the value of direct cost.
     * If checkDirect is false, it is checked based on the value of indirect cost.
     * @param detail
     * @param checkDirect
     * @return
     */
    private boolean hasBeenChanged(BudgetSubAwardPeriodDetail detail, boolean checkDirect) {
        boolean changed = false;
        if (detail != null && detail.getBudgetSubAwardDetailId() != null) {
            Map primaryKeys = new HashMap();
            primaryKeys.put("SUBAWARD_PERIOD_DETAIL_ID", detail.getBudgetSubAwardDetailId());
            BudgetSubAwardPeriodDetail dbDetail = KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(BudgetSubAwardPeriodDetail.class, primaryKeys);
            if (checkDirect) {
                changed = !BudgetDecimal.returnZeroIfNull(detail.getDirectCost()).equals(BudgetDecimal.returnZeroIfNull(dbDetail.getDirectCost()));
            } else {
                changed = !BudgetDecimal.returnZeroIfNull(detail.getIndirectCost()).equals(BudgetDecimal.returnZeroIfNull(dbDetail.getIndirectCost()));
            }
        }
        return changed;
    }
    
    protected BudgetPeriod findBudgetPeriod(BudgetSubAwardPeriodDetail detail, Budget budget) {
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            if (ObjectUtils.equals(detail.getBudgetPeriod(), period.getBudgetPeriod())) {
                return period;
            }
        }
        return null;
    }
    
    protected BudgetDecimal lesserValue(BudgetDecimal num1, BudgetDecimal num2) {
        if (num1.isLessThan(num2)) {
            return num1;
        } else {
            return num2;
        }
    }
    
    protected BudgetLineItem findOrCreateLineItem(List<BudgetLineItem> lineItems, BudgetSubAwardPeriodDetail subAwardDetail, BudgetSubAwards subAward, BudgetPeriod budgetPeriod, String costElement) {
        for (BudgetLineItem curLineItem : lineItems) {
            if (StringUtils.equals(curLineItem.getCostElement(), costElement)) {
                return curLineItem;
            }
        }

        //if we didn't find one already
        BudgetLineItem newLineItem = new BudgetLineItem();        
        newLineItem.setCostElement(costElement);
        newLineItem.setSubAwardNumber(subAwardDetail.getSubAwardNumber());
        newLineItem.setLineItemDescription(subAward.getOrganizationName());
        getBudgetService().populateNewBudgetLineItem(newLineItem, budgetPeriod);
        lineItems.add(newLineItem);
        return newLineItem;
    }
    
    protected List<BudgetLineItem> findSubAwardLineItems(BudgetPeriod budgetPeriod, Integer subAwardNumber) {
        List<BudgetLineItem> lineItems = new ArrayList<BudgetLineItem>();
        if (budgetPeriod.getBudgetLineItems() != null) {
            for (BudgetLineItem item : budgetPeriod.getBudgetLineItems()) {
                if (ObjectUtils.equals(item.getSubAwardNumber(), subAwardNumber)) {
                    lineItems.add(item);
                }
            }
        }
        return lineItems;
    }
    
    /**
     * This method return loggedin user id
     */
    protected String getLoggedInUserNetworkId() {
        return GlobalVariables.getUserSession().getPrincipalName();
    }
//    private List<BudgetSubAwardAttachment> getSubAwardAttachments(BudgetSubAwards budgetSubAwardBean) {
//        List<BudgetSubAwardAttachment> budgetSubAwardBeanAttachments = (List<BudgetSubAwardAttachment>) budgetSubAwardBean.getBudgetSubAwardAttachments();
//        List<BudgetSubAwardAttachment> budgetSubAwardAttachments =  new ArrayList<BudgetSubAwardAttachment>();
//        if(budgetSubAwardBeanAttachments!=null)
//        for(BudgetSubAwardAttachment budgetSubAwardAttachmentBean: budgetSubAwardBeanAttachments) {
//            BudgetSubAwardAttachment budgetSubAwardAttachment = new BudgetSubAwardAttachment();
//            try {
//                BeanUtils.copyProperties(budgetSubAwardAttachment, budgetSubAwardAttachmentBean);
//                budgetSubAwardAttachment.setBudgetId(budgetSubAwardBean.getBudgetId());
//                budgetSubAwardAttachment.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());
//            }
//            catch (IllegalAccessException e) {
//                LOG.warn(e);
//            }
//            catch (InvocationTargetException e) {
//                LOG.warn(e);
//            }
//            budgetSubAwardAttachment.setBudgetId(budgetSubAwardAttachmentBean.getBudgetId());
//            budgetSubAwardAttachments.add(budgetSubAwardAttachment);            
//        }
//        return budgetSubAwardAttachments;
//    }
    
    /**
     * extracts XML from PDF
     */
    protected byte[] getXMLFromPDF(PdfReader reader)throws Exception {
        XfaForm xfaForm = reader.getAcroFields().getXfa();
        Node domDocument = xfaForm.getDomDocument();
        if(domDocument==null)
            return null;
        Element documentElement = ((Document) domDocument).getDocumentElement();

        Element datasetsElement = (Element) documentElement.getElementsByTagNameNS(XFA_NS, "datasets").item(0);
        Element dataElement = (Element) datasetsElement.getElementsByTagNameNS(XFA_NS, "data").item(0);

        Element xmlElement = (Element) dataElement.getChildNodes().item(0);
        
        Node budgetElement = getBudgetElement(xmlElement);
        
        byte[] serializedXML = XfaForm.serializeDoc(budgetElement);

        return serializedXML;
    }

    private Node getBudgetElement(Element xmlElement) throws Exception{
        Node budgetNode = (Node)xmlElement;
        NodeList budgetAttachments =  XPathAPI.selectNodeList(xmlElement,"//*[local-name(.) = 'BudgetAttachments']");
        if(budgetAttachments!=null && budgetAttachments.getLength()>0){
            Element budgetAttachment = (Element)budgetAttachments.item(0);
            if(budgetAttachment.hasChildNodes()){
                budgetNode = budgetAttachment.getFirstChild();
            }
        }
        return budgetNode;
    }
    /**
     * extracts attachments from PDF File
     */       

    @SuppressWarnings("unchecked")
    protected Map extractAttachments(PdfReader reader)throws IOException{
        Map fileMap = new HashMap();
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary names = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.NAMES));
        if (names != null) {
            PdfDictionary embFiles = (PdfDictionary)
            PdfReader.getPdfObject(names.get(new PdfName("EmbeddedFiles")));
            if (embFiles != null) {
                HashMap embMap = PdfNameTree.readTree(embFiles);
                for (Iterator i = embMap.values().iterator(); i.hasNext();) {
                    PdfDictionary filespec = (PdfDictionary) PdfReader.getPdfObject((PdfObject) i.next());
                    Object fileInfo[] = unpackFile(reader, filespec);
                    if(fileMap.containsKey(fileInfo[0])) {
                        throw new RuntimeException(DUPLICATE_FILE_NAMES);
                    }
                    fileMap.put(fileInfo[0], fileInfo[1]);
                }
            }
        }
        for (int k = 1; k <= reader.getNumberOfPages(); ++k) {
            PdfArray annots = (PdfArray) PdfReader.getPdfObject(reader.getPageN(k).get(PdfName.ANNOTS));
            if (annots == null) {
                continue;
            }
            for (Iterator i = annots.getArrayList().listIterator(); i.hasNext();) {
                PdfDictionary annot = (PdfDictionary) PdfReader.getPdfObject((PdfObject) i.next());
                PdfName subType = (PdfName) PdfReader.getPdfObject(annot.get(PdfName.SUBTYPE));
                if (!PdfName.FILEATTACHMENT.equals(subType)) {
                    continue;
                }
                PdfDictionary filespec = (PdfDictionary)
                PdfReader.getPdfObject(annot.get(PdfName.FS));
                Object fileInfo[] = unpackFile(reader, filespec);
                if(fileMap.containsKey(fileInfo[0])) {
                    throw new RuntimeException(DUPLICATE_FILE_NAMES);
                }

                fileMap.put(fileInfo[0], fileInfo[1]);
            }
        }

        return fileMap;
    }

    /**
     * Unpacks a file attachment.
     * @param reader The object that reads the PDF document
     * @param filespec The dictonary containing the file specifications
     * @throws IOException
     */

    protected static Object[] unpackFile(PdfReader reader, PdfDictionary filespec)throws IOException  {
        Object arr[] = new Object[2]; //use to store name and file bytes
        if (filespec == null) {
            return null;
        }
        
        PdfName type = (PdfName) PdfReader.getPdfObject(filespec.get(PdfName.TYPE));
        if (!PdfName.F.equals(type) && !PdfName.FILESPEC.equals(type)) {
            return null;
        }

        PdfDictionary ef = (PdfDictionary) PdfReader.getPdfObject(filespec.get(PdfName.EF));
        if (ef == null) {
            return null;
        }
        
        PdfString fn = (PdfString) PdfReader.getPdfObject(filespec.get(PdfName.F));
        if (fn == null) {
            return null;
        }

        File fLast = new File(fn.toUnicodeString());
        PRStream prs = (PRStream) PdfReader.getPdfObject(ef.get(PdfName.F));
        if (prs == null) {
            return null;
        }

        byte attachmentByte[] = PdfReader.getStreamBytes(prs);
        arr[0] = fLast.getName();
        arr[1] = attachmentByte;


        return arr;

    }
    
    /**
     * 
     * @see org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService#updateSubAwardBudgetDetails(org.kuali.kra.budget.core.Budget, org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards, java.util.List)
     */
    public boolean updateSubAwardBudgetDetails(Budget budget, BudgetSubAwards budgetSubAward, List<String[]> errors) throws Exception {
        boolean result = true;
        //extarct xml from the pdf because the stored xml has been modified
        if (budgetSubAward.getSubAwardXfdFileData() == null || budgetSubAward.getSubAwardXfdFileData().length == 0) {
            errors.add(new String[]{Constants.SUBAWARD_FILE_NOT_EXTRACTED});
            return false;
        }
        PdfReader reader = new PdfReader(budgetSubAward.getSubAwardXfdFileData());
        byte[] xmlContents = getXMLFromPDF(reader);
        if (xmlContents == null) {
            return false;
        }
        javax.xml.parsers.DocumentBuilderFactory domParserFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContents);
        org.w3c.dom.Document document = domParser.parse(byteArrayInputStream);
        NodeList budgetYearList =  XPathAPI.selectNodeList(document,"//*[local-name(.) = 'BudgetYear']");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean fnfForm = StringUtils.contains(budgetSubAward.getFormName(), "RR_FedNonFedBudget");
        for (int i = 0; i < budgetYearList.getLength(); i++) {
            Node budgetYear = budgetYearList.item(i);
            Node startDateNode = XPathAPI.selectSingleNode(budgetYear, "BudgetPeriodStartDate");
            Node endDateNode = XPathAPI.selectSingleNode(budgetYear, "BudgetPeriodEndDate");
            Date startDate = dateFormat.parse(startDateNode.getTextContent());
            Date endDate = dateFormat.parse(endDateNode.getTextContent());
            //attempt to find a matching budget period
            BudgetSubAwardPeriodDetail periodDetail = findBudgetSubAwardPeriodDetail(budget, budgetSubAward, startDate, endDate);
            if (periodDetail != null) {
                Node directCostNode, indirectCostNode, costShareNode = null;
                if (fnfForm) {
                    directCostNode = XPathAPI.selectSingleNode(budgetYear, "DirectCosts/FederalSummary");
                    indirectCostNode = XPathAPI.selectSingleNode(budgetYear, "IndirectCosts/TotalIndirectCosts/FederalSummary");
                    costShareNode = XPathAPI.selectSingleNode(budgetYear, "TotalCosts/NonFederalSummary");
                } else {
                    directCostNode = XPathAPI.selectSingleNode(budgetYear, "DirectCosts");
                    indirectCostNode = XPathAPI.selectSingleNode(budgetYear, "IndirectCosts/TotalIndirectCosts");
                }
                if (directCostNode != null) {
                    periodDetail.setDirectCost(new BudgetDecimal(Float.parseFloat(directCostNode.getTextContent())));
                }
                if (indirectCostNode != null) {
                    periodDetail.setIndirectCost(new BudgetDecimal(Float.parseFloat(indirectCostNode.getTextContent())));
                }
                if (costShareNode != null) {
                    periodDetail.setCostShare(new BudgetDecimal(Float.parseFloat(costShareNode.getTextContent())));
                } else {
                    periodDetail.setCostShare(BudgetDecimal.ZERO);
                }
                periodDetail.computeTotal();
            } else {
                Node budgetPeriodNode = XPathAPI.selectSingleNode(budgetYear, "BudgetPeriod");
                String budgetPeriod = null;
                if (budgetPeriodNode != null) {
                    budgetPeriod = budgetPeriodNode.getTextContent();
                }
                LOG.debug("Unable to find matching period for uploaded period '" + budgetPeriod + "' -- " + startDateNode.getTextContent() + " - " + endDateNode.getTextContent());
                errors.add(new String[]{Constants.SUBAWARD_FILE_PERIOD_NOT_FOUND, budgetPeriod, startDateNode.getTextContent(), endDateNode.getTextContent()});
            }
        }
        return result;
    }
    
    /**
     * First find a budget period that matches the start and end date. If that is found, find a subaward period detail with the same
     * budget period number.
     * @param budget
     * @param budgetSubAward
     * @param startDate
     * @param endDate
     * @return
     */
    protected BudgetSubAwardPeriodDetail findBudgetSubAwardPeriodDetail(Budget budget, BudgetSubAwards budgetSubAward, Date startDate, Date endDate) {
        BudgetPeriod matchingPeriod = null;
        BudgetSubAwardPeriodDetail matchingDetail = null;
        for (BudgetPeriod period : budget.getBudgetPeriods()) {
            if (startDate.getTime() == period.getStartDate().getTime()
                    && endDate.getTime() == period.getEndDate().getTime()) {
                matchingPeriod = period;
                break;
            }
        }
        if (matchingPeriod != null) {

            for (BudgetSubAwardPeriodDetail detail : budgetSubAward.getBudgetSubAwardPeriodDetails()) {
                if (ObjectUtils.equals(detail.getBudgetPeriod(), matchingPeriod.getBudgetPeriod())) {
                    matchingDetail = detail;
                    break;
                }
            }
        }
        return matchingDetail;
    }  

    /**
     * updates the XMl with hashcode for the files
     */

  protected BudgetSubAwards updateXML(byte xmlContents[], Map fileMap, BudgetSubAwards budgetSubAwardBean, Budget budget) throws Exception {

        javax.xml.parsers.DocumentBuilderFactory domParserFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContents);

        org.w3c.dom.Document document = domParser.parse(byteArrayInputStream);
        byteArrayInputStream.close();
        String namespace=null;
        String formName = null;
        if (document != null) {
            Node node;
            Element element = document.getDocumentElement();
            NamedNodeMap map = element.getAttributes();
            String namespaceHolder = element.getNodeName().substring(0, element.getNodeName().indexOf(':'));
            node = map.getNamedItem("xmlns:" + namespaceHolder);
            namespace = node.getNodeValue();
            FormMappingInfo formMappingInfo = new FormMappingLoader().getFormInfo(namespace);
            formName = formMappingInfo.getFormName();
            budgetSubAwardBean.setNamespace(namespace);
            budgetSubAwardBean.setFormName(formName);
        }

        String xpathEmptyNodes = "//*[not(node()) and local-name(.) != 'FileLocation' and local-name(.) != 'HashValue']";
        String xpathOtherPers = "//*[local-name(.)='ProjectRole' and local-name(../../.)='OtherPersonnel' and count(../NumberOfPersonnel)=0]";
        removeAllEmptyNodes(document,xpathEmptyNodes,0);
        removeAllEmptyNodes(document,xpathOtherPers,1);
        removeAllEmptyNodes(document,xpathEmptyNodes,0);
        changeDataTypeForNumberOfOtherPersons(document);
        
        List<String> fedNonFedSubAwardForms=getFedNonFedSubawardForms();
        NodeList budgetYearList =  XPathAPI.selectNodeList(document,"//*[local-name(.) = 'BudgetYear']");
        for(int i=0;i<budgetYearList.getLength();i++){
            Node bgtYearNode = budgetYearList.item(i);
            String period = getValue(XPathAPI.selectSingleNode(bgtYearNode,"BudgetPeriod"));
            if(fedNonFedSubAwardForms.contains(namespace)){
                Element newBudgetYearElement = copyElementToName((Element)bgtYearNode,bgtYearNode.getNodeName());
                bgtYearNode.getParentNode().replaceChild(newBudgetYearElement,bgtYearNode);
            }else{
                Element newBudgetYearElement = copyElementToName((Element)bgtYearNode,bgtYearNode.getNodeName()+period);
                bgtYearNode.getParentNode().replaceChild(newBudgetYearElement,bgtYearNode);
            }
        }
        
        Node oldroot = document.removeChild(document.getDocumentElement());
        Node newroot = document.appendChild(document.createElement("Forms"));
        newroot.appendChild(oldroot);
        
        org.w3c.dom.NodeList lstFileName = document.getElementsByTagName("att:FileName");
        org.w3c.dom.NodeList lstFileLocation = document.getElementsByTagName("att:FileLocation");
        org.w3c.dom.NodeList lstMimeType = document.getElementsByTagName("att:MimeType");
        org.w3c.dom.NodeList lstHashValue = document.getElementsByTagName("glob:HashValue");

        if((lstFileName.getLength() != lstFileLocation.getLength()) || (lstFileLocation.getLength() != lstHashValue.getLength())) {
//            throw new RuntimeException("Tag occurances mismatch in XML File");
        }

        org.w3c.dom.Node fileNode, hashNode, mimeTypeNode;
        org.w3c.dom.NamedNodeMap fileNodeMap, hashNodeMap;
        String fileName;
        byte fileBytes[];
        String contentId;
        List attachmentList = new ArrayList();

        for(int index = 0; index < lstFileName.getLength(); index++) {
            fileNode = lstFileName.item(index);
                
            Node fileNameNode = fileNode.getFirstChild(); 
            fileName = fileNameNode.getNodeValue();

            fileBytes = (byte[])fileMap.get(fileName);

            if(fileBytes == null) {
                throw new RuntimeException("FileName mismatch in XML and PDF extracted file");
            }
            String hashVal = GrantApplicationHash.computeAttachmentHash(fileBytes);

            hashNode = lstHashValue.item(index);
            hashNodeMap = hashNode.getAttributes();

            Node temp = document.createTextNode(hashVal);
            hashNode.appendChild(temp);

            hashNode = hashNodeMap.getNamedItem("glob:hashAlgorithm");

            hashNode.setNodeValue(S2SConstants.HASH_ALGORITHM);

            fileNode = lstFileLocation.item(index);
            fileNodeMap = fileNode.getAttributes();
            fileNode = fileNodeMap.getNamedItem("att:href");

            contentId = fileNode.getNodeValue();
            String encodedContentId = cleanContentId(contentId);
            fileNode.setNodeValue(encodedContentId);

            mimeTypeNode = lstMimeType.item(0);
            String contentType = mimeTypeNode.getFirstChild().getNodeValue();
                
            BudgetSubAwardAttachment budgetSubAwardAttachmentBean = new BudgetSubAwardAttachment();
            budgetSubAwardAttachmentBean.setAttachment(fileBytes);
            budgetSubAwardAttachmentBean.setContentId(encodedContentId);

            budgetSubAwardAttachmentBean.setContentType(contentType);
            budgetSubAwardAttachmentBean.setBudgetId(budgetSubAwardBean.getBudgetId());
            budgetSubAwardAttachmentBean.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());

            attachmentList.add(budgetSubAwardAttachmentBean);
        }

        budgetSubAwardBean.setBudgetSubAwardAttachments(attachmentList);
        
        javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(bos);
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
        
        transformer.transform(source, result);            
        
        budgetSubAwardBean.setSubAwardXmlFileData(new String(bos.toByteArray()));
        
        bos.close();
        
        return budgetSubAwardBean;
    }


    protected String cleanContentId(String contentId) {
        return StringUtils.replaceChars(contentId, " .%-_", "");
    }
    public void populateBudgetSubAwardAttachments(Budget budget) {
        List<BudgetSubAwards> subAwards = budget.getBudgetSubAwards();
        for (BudgetSubAwards budgetSubAwards : subAwards) {
            budgetSubAwards.refreshReferenceObject("budgetSubAwardAttachments");
            List<BudgetSubAwardAttachment> attList = budgetSubAwards.getBudgetSubAwardAttachments();
            for (BudgetSubAwardAttachment budgetSubAwardAttachment : attList) {
                //budgetSubAwardAttachment.setAttachment(null);
            }
        }        
    }   
    protected void removeAllEmptyNodes(Document document,String xpath,int parentLevel) throws TransformerException {
        NodeList emptyElements =  XPathAPI.selectNodeList(document,xpath);
        
        for (int i = emptyElements.getLength()-1; i > -1; i--){
              Node nodeToBeRemoved = emptyElements.item(i);
              int hierLevel = parentLevel;
              while(hierLevel-- > 0){
                  nodeToBeRemoved = nodeToBeRemoved.getParentNode();
              }
              nodeToBeRemoved.getParentNode().removeChild(nodeToBeRemoved);
        }
        NodeList moreEmptyElements =  XPathAPI.selectNodeList(document,xpath);
        if(moreEmptyElements.getLength()>0){
            removeAllEmptyNodes(document,xpath,parentLevel);
        }
    }

    protected Element copyElementToName(Element element,String tagName) {
        Element newElement = element.getOwnerDocument().createElement(tagName);
        NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Node attribute = attrs.item(i);
            newElement.setAttribute(attribute.getNodeName(),attribute.getNodeValue());
        }
        for (int i = 0; i < element.getChildNodes().getLength(); i++) {
            newElement.appendChild(element.getChildNodes().item(i).cloneNode(true));
        }
        return newElement;
    }
    protected HashValue getValue(byte[] fileBytes) throws Exception{
        return createHashValueType(GrantApplicationHash.computeAttachmentHash(fileBytes));
    }
    protected HashValue createHashValueType(String hashValueStr) throws Exception{
        HashValue hashValue = HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(S2SConstants.HASH_ALGORITHM);
        hashValue.setStringValue(hashValueStr);
        return hashValue;
    }
    private void changeDataTypeForNumberOfOtherPersons(Document document) throws Exception{
        NodeList otherPesronsCountNodes =  XPathAPI.selectNodeList(document,"//*[local-name(.)='OtherPersonnelTotalNumber']");
        for (int i = 0; i < otherPesronsCountNodes.getLength(); i++) {
            Node countNode = otherPesronsCountNodes.item(i);
            String value = getValue(countNode);

            if(value!=null && value.length()>0 && value.indexOf('.')!=-1){
                int intVal = Double.valueOf(value).intValue();
                setValue(countNode,""+intVal);
            }
        }
    }
    private void setValue(Node node, String value) {
        Node child = null;
        for (child = node.getFirstChild(); child != null;
             child = child.getNextSibling()) {
             if(child.getNodeType()==Node.TEXT_NODE){
                child.setNodeValue(value);
                break;
             }
        }
    }
    private static String getValue(Node node){
        String textValue = "";
        Node child = null;
        if(node!=null)
        for (child = node.getFirstChild(); child != null;
             child = child.getNextSibling()) {
             if(child.getNodeType()==Node.TEXT_NODE){
                textValue = child.getNodeValue();
                break;
             }
        }
        return textValue.trim();
    }
    private  List<String> getFedNonFedSubawardForms(){
        List<String> forms=new ArrayList<String>();
        forms.add("http://apply.grants.gov/forms/RR_FedNonFedBudget10-V1.1");
        return forms;
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected BudgetService getBudgetService() {
        return budgetService;
    }

    public void setBudgetService(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}

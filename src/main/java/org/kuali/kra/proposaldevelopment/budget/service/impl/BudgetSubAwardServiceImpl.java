/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.XPathAPI;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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


    /**
     * @see org.kuali.kra.proposaldevelopment.budget.service.BudgetSubAwardService#populateBudgetSubAwardFiles(org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards)
     */
    public void populateBudgetSubAwardFiles(BudgetSubAwards budgetSubAwardBean) {
        BudgetSubAwardFiles budgetSubAwardFiles = budgetSubAwardBean.getBudgetSubAwardFiles().get(0);
        
        boolean subawardBudgetExtracted  = false;
        
        try {
            byte[] pdfFileContents = budgetSubAwardFiles.getSubAwardXfdFileData();
            budgetSubAwardBean.setSubAwardXfdFileData(pdfFileContents);
            PdfReader  reader = new PdfReader(pdfFileContents);
            byte[] xmlContents=getXMLFromPDF(reader);
            subawardBudgetExtracted = (xmlContents!=null && xmlContents.length>0);
            if(subawardBudgetExtracted){
                Map fileMap = extractAttachments(reader);
                updateXML(xmlContents, fileMap, budgetSubAwardBean);
            }
        }catch (Exception e) {
            LOG.error("Not able to extract xml from pdf",e);
            subawardBudgetExtracted = false;
        }
        
        budgetSubAwardFiles.setSubAwardXfdFileData(budgetSubAwardBean.getSubAwardXfdFileData());
        if (subawardBudgetExtracted) {
            budgetSubAwardFiles.setSubAwardXmlFileData(new String(budgetSubAwardBean.getSubAwardXmlFileData()));
        }
        budgetSubAwardFiles.setSubAwardXfdFileName(budgetSubAwardBean.getSubAwardXfdFileName());
        budgetSubAwardFiles.setBudgetId(budgetSubAwardBean.getBudgetId());
        budgetSubAwardFiles.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());
        budgetSubAwardBean.setSubAwardXfdFileName(budgetSubAwardBean.getSubAwardXfdFileName());
        budgetSubAwardBean.setXfdUpdateUser(getLoggedInUserNetworkId());
        budgetSubAwardBean.setXfdUpdateTimestamp(CoreApiServiceLocator.getDateTimeService().getCurrentTimestamp());
        budgetSubAwardBean.setXmlUpdateUser(getLoggedInUserNetworkId());
        budgetSubAwardBean.setXmlUpdateTimestamp(CoreApiServiceLocator.getDateTimeService().getCurrentTimestamp());
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
            throw new Exception("Not a valid pdf form");
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
     * updates the XMl with hashcode for the files
     */

  protected BudgetSubAwards updateXML(byte xmlContents[], Map fileMap, BudgetSubAwards budgetSubAwardBean) throws Exception {

        javax.xml.parsers.DocumentBuilderFactory domParserFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContents);

        org.w3c.dom.Document document = domParser.parse(byteArrayInputStream);
        byteArrayInputStream.close();
        String namespace=null;
        if (document != null) {
            Node node;
            String formName;
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
                budgetSubAwardAttachment.setAttachment(null);
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

}

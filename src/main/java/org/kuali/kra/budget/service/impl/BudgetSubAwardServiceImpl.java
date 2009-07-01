/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.budget.service.impl;


import gov.grants.apply.system.globalV10.HashValueDocument.HashValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.XPathAPI;
import org.kuali.kra.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetSubAwardService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.rice.shim.UniversalUserService;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.GlobalVariables;
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
import com.lowagie.text.pdf.codec.Base64;

/**
 * This class...
 */
public class BudgetSubAwardServiceImpl implements BudgetSubAwardService {
    private static final String DUPLICATE_FILE_NAMES =  "Duplicate PDF Attachment File Names"; 
    private static final String XFA_NS = "http://www.xfa.org/schema/xfa-data/1.0/";
    private static final Log LOG = LogFactory.getLog(BudgetSubAwardServiceImpl.class);


    /**
     * @see org.kuali.kra.budget.service.BudgetSubAwardService#populateBudgetSubAwardFiles(org.kuali.kra.budget.bo.BudgetSubAwards)
     */
    public void populateBudgetSubAwardFiles(BudgetSubAwards budgetSubAwardBean) {
        BudgetSubAwardFiles budgetSubAwardFiles = budgetSubAwardBean.getBudgetSubAwardFiles().get(0);
        try {
            byte[] pdfFileContents = budgetSubAwardBean.getSubAwardXfdFileData();
            PdfReader  reader = new PdfReader(pdfFileContents);
            byte[] xmlContents=getXMLFromPDF(reader);
            Map fileMap = extractAttachments(reader);
            updateXML(xmlContents, fileMap, budgetSubAwardBean);
        }catch (Exception e) {
            LOG.error("Not able to extract xml from pdf",e);
        }
        budgetSubAwardFiles.setSubAwardXfdFileData(budgetSubAwardBean.getSubAwardXfdFileData());
        budgetSubAwardFiles.setSubAwardXmlFileData(new String(budgetSubAwardBean.getSubAwardXmlFileData()));
        budgetSubAwardFiles.setSubAwardXfdFileName(budgetSubAwardBean.getSubAwardXfdFileName());
        budgetSubAwardFiles.setProposalNumber(budgetSubAwardBean.getProposalNumber());
        budgetSubAwardFiles.setBudgetVersionNumber(budgetSubAwardBean.getBudgetVersionNumber());
        budgetSubAwardFiles.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());
        budgetSubAwardBean.setSubAwardXfdFileName(budgetSubAwardBean.getSubAwardXfdFileName());
        budgetSubAwardBean.setXfdUpdateUser(getLoggedInUserNetworkId());
        budgetSubAwardBean.setXfdUpdateTimestamp(KNSServiceLocator.getDateTimeService().getCurrentTimestamp());
        budgetSubAwardBean.setXmlUpdateUser(getLoggedInUserNetworkId());
        budgetSubAwardBean.setXmlUpdateTimestamp(KNSServiceLocator.getDateTimeService().getCurrentTimestamp());
        budgetSubAwardBean.setBudgetSubAwardAttachments(getSubAwardAttachments(budgetSubAwardBean));
    }
    /**
     * This method return loggedin user id
     */
    private String getLoggedInUserNetworkId() {
        return GlobalVariables.getUserSession().getPrincipalName();
    }
    private List<BudgetSubAwardAttachment> getSubAwardAttachments(BudgetSubAwards budgetSubAwardBean) {
        List<BudgetSubAwardAttachment> budgetSubAwardBeanAttachments = (List<BudgetSubAwardAttachment>) budgetSubAwardBean.getBudgetSubAwardAttachments();
        List<BudgetSubAwardAttachment> budgetSubAwardAttachments =  new ArrayList<BudgetSubAwardAttachment>();
        
        for(BudgetSubAwardAttachment budgetSubAwardAttachmentBean: budgetSubAwardBeanAttachments) {
            BudgetSubAwardAttachment budgetSubAwardAttachment = new BudgetSubAwardAttachment();
            try {
                BeanUtils.copyProperties(budgetSubAwardAttachment, budgetSubAwardAttachmentBean);
                budgetSubAwardAttachment.setProposalNumber(budgetSubAwardBean.getProposalNumber());
                budgetSubAwardAttachment.setBudgetVersionNumber(budgetSubAwardBean.getBudgetVersionNumber());
                budgetSubAwardAttachment.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            budgetSubAwardAttachment.setBudgetVersionNumber(budgetSubAwardAttachmentBean.getBudgetVersionNumber());
            budgetSubAwardAttachments.add(budgetSubAwardAttachment);            
        }
        return budgetSubAwardAttachments;
    }
    
    /**
     * extracts XML from PDF
     */
    private byte[] getXMLFromPDF(PdfReader reader)throws Exception {
        XfaForm xfaForm = reader.getAcroFields().getXfa();
        Node domDocument = xfaForm.getDomDocument();
        Element documentElement = ((Document) domDocument).getDocumentElement();

        Element datasetsElement = (Element) documentElement.getElementsByTagNameNS(XFA_NS, "datasets").item(0);
        Element dataElement = (Element) datasetsElement.getElementsByTagNameNS(XFA_NS, "data").item(0);

        Element grantApplicationElement = (Element) dataElement.getChildNodes().item(0);

        byte[] serializedXML = XfaForm.serializeDoc(grantApplicationElement);

        return serializedXML;
    }

    /**
     * extracts attachments from PDF File
     */       

    @SuppressWarnings("unchecked")
    private Map extractAttachments(PdfReader reader)throws IOException{//, CoeusException {
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

    private static Object[] unpackFile(PdfReader reader, PdfDictionary filespec)throws IOException  {
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

  private BudgetSubAwards updateXML(byte xmlContents[], Map fileMap, BudgetSubAwards budgetSubAwardBean) throws Exception {
        String globhashValue = "glob:HashValue";
        String globHashAlgorithm = "glob:hashAlgorithm";
        String attFileName = "att:FileName";
        String fileLocation = "att:FileLocation";
        String fileContentId = "att:href";

        javax.xml.parsers.DocumentBuilderFactory domParserFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContents);

        org.w3c.dom.Document document = domParser.parse(byteArrayInputStream);
        byteArrayInputStream.close();

        
        String xpathEmptyNodes = "//*[not(node()) and local-name(.) != 'FileLocation' and local-name(.) != 'HashValue']";// and not(FileLocation[@href])]";// and string-length(normalize-space(@*)) = 0 ]";
        String xpathOtherPers = "//*[local-name(.)='ProjectRole' and local-name(../../.)='OtherPersonnel' and count(../NumberOfPersonnel)=0]";
        removeAllEmptyNodes(document,xpathEmptyNodes,0);
        removeAllEmptyNodes(document,xpathOtherPers,1);
        removeAllEmptyNodes(document,xpathEmptyNodes,0);
        
        
        NodeList budgetYearList =  XPathAPI.selectNodeList(document,"//*[local-name(.) = 'BudgetYear']");
        for(int i=0;i<budgetYearList.getLength();i++){
            Node bgtYearNode = budgetYearList.item(i);
            String period = XPathAPI.selectSingleNode(bgtYearNode,"BudgetPeriod").getTextContent();
            Element newBudgetYearElement = copyElementToName((Element)bgtYearNode,bgtYearNode.getNodeName()+period);
            bgtYearNode.getParentNode().replaceChild(newBudgetYearElement,bgtYearNode);
        }
        
        Node oldroot = document.removeChild(document.getDocumentElement());
        Node newroot = document.appendChild(document.createElement("Forms"));
        newroot.appendChild(oldroot);
        
        org.w3c.dom.NodeList lstFileName = document.getElementsByTagName(attFileName);
        org.w3c.dom.NodeList lstFileLocation = document.getElementsByTagName(fileLocation);
        org.w3c.dom.NodeList lstHashValue = document.getElementsByTagName(globhashValue);

        if((lstFileName.getLength() != lstFileLocation.getLength()) || (lstFileLocation.getLength() != lstHashValue.getLength())) {
            throw new RuntimeException("Tag occurances mismatch in XML File");
        }

        org.w3c.dom.Node fileNode, hashNode;
        org.w3c.dom.NamedNodeMap fileNodeMap, hashNodeMap;
        String fileName;
        byte fileBytes[];
        String hashAlgorithm;
        HashValue hashValueType;
        String hashValue;
        String contentId;
        List attachmentList = new ArrayList();

        for(int index = 0; index < lstFileName.getLength(); index++) {
            fileNode = lstFileName.item(index);
            
            fileName = fileNode.getFirstChild().getNodeValue();

            fileBytes = (byte[])fileMap.get(fileName);

            if(fileBytes == null) {
                throw new RuntimeException("FileName mismatch in XML and PDF extracted file");
            }
            hashValueType = getValue(fileBytes);
            hashAlgorithm = hashValueType.getHashAlgorithm();
            byte hashBytes[] = hashValueType.getByteArrayValue();
            hashValue = Base64.encodeBytes(hashBytes);

            hashNode = lstHashValue.item(index);
            hashNodeMap = hashNode.getAttributes();

            Node temp = document.createTextNode(hashValue);
            hashNode.appendChild(temp);

            hashNode = hashNodeMap.getNamedItem(globHashAlgorithm);

            hashNode.setNodeValue(hashAlgorithm);

            fileNode = lstFileLocation.item(index);
            fileNodeMap = fileNode.getAttributes();
            fileNode = fileNodeMap.getNamedItem(fileContentId);

            contentId = fileNode.getNodeValue();

            BudgetSubAwardAttachment budgetSubAwardAttachmentBean = new BudgetSubAwardAttachment();
            budgetSubAwardAttachmentBean.setAttachment(fileBytes);

            budgetSubAwardAttachmentBean.setContentId(contentId);

            budgetSubAwardAttachmentBean.setContentType("application/octet-stream");
            budgetSubAwardAttachmentBean.setProposalNumber(budgetSubAwardBean.getProposalNumber());
            budgetSubAwardAttachmentBean.setVersionNumber(budgetSubAwardBean.getVersionNumber());
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


    public void populateBudgetSubAwardAttachments(BudgetDocument budgetDocument) {
        List<BudgetSubAwards> subAwards = budgetDocument.getBudgetSubAwards();
        for (BudgetSubAwards budgetSubAwards : subAwards) {
            budgetSubAwards.refreshReferenceObject("budgetSubAwardAttachments");
            List<BudgetSubAwardAttachment> attList = budgetSubAwards.getBudgetSubAwardAttachments();
            for (BudgetSubAwardAttachment budgetSubAwardAttachment : attList) {
                budgetSubAwardAttachment.setAttachment(null);
            }
        }        
    }   
    private void removeAllEmptyNodes(Document document,String xpath,int parentLevel) throws TransformerException {
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

    private Element copyElementToName(Element element,String tagName) {
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
    private HashValue getValue(byte[] fileBytes) throws Exception{
        return createHashValueType(GrantApplicationHash.computeAttachmentHash(fileBytes));
    }
    private HashValue createHashValueType(String hashValueStr) throws Exception{
        String hashVal = GrantApplicationHash.computeGrantFormsHash(hashValueStr);
        HashValue hashValue = HashValue.Factory.newInstance();
        hashValue.setHashAlgorithm(S2SConstants.HASH_ALGORITHM);
        hashValue.setStringValue(hashVal);
        return hashValue;
    }

}

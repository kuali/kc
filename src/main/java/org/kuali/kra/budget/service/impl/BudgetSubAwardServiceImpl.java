/*
 * Copyright 2006-2009 The Kuali Foundation
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
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.xpath.XPathAPI;
import org.kuali.kra.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.service.BudgetSubAwardService;
import org.kuali.kra.budget.web.struts.action.BudgetActionsAction;
import org.kuali.kra.budget.web.struts.form.BudgetForm;
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

import edu.mit.coeus.budget.bean.BudgetSubAwardAttachmentBean;
import edu.mit.coeus.budget.bean.BudgetSubAwardBean;
import edu.mit.coeus.exception.CoeusException;
import edu.mit.coeus.s2s.util.S2SHashValue;
import edu.mit.coeus.utils.UtilFactory;
import edu.mit.coeus.utils.documenttype.DocumentTypeChecker;
import gov.grants.apply.system.global_v1.HashValueType;

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
    public void populateBudgetSubAwardFiles(BudgetSubAwards budgetSubAwards) {
        BudgetSubAwardFiles budgetSubAwardFiles = budgetSubAwards.getBudgetSubAwardFiles().get(0);
        BudgetSubAwardBean budgetSubAwardBean = new BudgetSubAwardBean();
        boolean subawardBudgetExtracted  = false;
        try {
            ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
            BeanUtils.copyProperties(budgetSubAwardBean, budgetSubAwards);
            budgetSubAwardBean.setSubAwardXFD(budgetSubAwardFiles.getSubAwardXfdFileData());
            budgetSubAwardBean.setVersionNumber(budgetSubAwards.getBudgetVersionNumber());
            byte[] pdfFileContents = budgetSubAwardBean.getSubAwardXFD();
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
        budgetSubAwardFiles.setSubAwardXfdFileData(budgetSubAwardBean.getSubAwardXFD());
        if(subawardBudgetExtracted && budgetSubAwardBean.getSubAwardXML()!=null){
            budgetSubAwardFiles.setSubAwardXmlFileData(new String(budgetSubAwardBean.getSubAwardXML()));
        }
        budgetSubAwardFiles.setSubAwardXfdFileName(budgetSubAwardBean.getXfdFileName());
        budgetSubAwardFiles.setProposalNumber(budgetSubAwards.getProposalNumber());
        budgetSubAwardFiles.setBudgetVersionNumber(budgetSubAwards.getBudgetVersionNumber());
        budgetSubAwardFiles.setSubAwardNumber(budgetSubAwards.getSubAwardNumber());
        budgetSubAwards.setSubAwardXfdFileName(budgetSubAwardBean.getXfdFileName());
        budgetSubAwards.setXfdUpdateUser(GlobalVariables.getUserSession().getPrincipalId());
        budgetSubAwards.setXfdUpdateTimestamp(KNSServiceLocator.getDateTimeService().getCurrentTimestamp());
        budgetSubAwards.setXmlUpdateUser(GlobalVariables.getUserSession().getPrincipalId());
        budgetSubAwards.setXmlUpdateTimestamp(KNSServiceLocator.getDateTimeService().getCurrentTimestamp());
        budgetSubAwards.setBudgetSubAwardAttachments(getSubAwardAttachments(budgetSubAwardBean));
    }
    
    
    @SuppressWarnings("unchecked")
    private List<BudgetSubAwardAttachment> getSubAwardAttachments(BudgetSubAwardBean budgetSubAwardBean) {
        List<BudgetSubAwardAttachmentBean> budgetSubAwardBeanAttachments = (List<BudgetSubAwardAttachmentBean>) budgetSubAwardBean.getAttachments();
        List<BudgetSubAwardAttachment> budgetSubAwardAttachments =  new ArrayList<BudgetSubAwardAttachment>();
        if(budgetSubAwardBeanAttachments!=null)
        for(BudgetSubAwardAttachmentBean budgetSubAwardAttachmentBean: budgetSubAwardBeanAttachments) {
            BudgetSubAwardAttachment budgetSubAwardAttachment = new BudgetSubAwardAttachment();
            try {
                BeanUtils.copyProperties(budgetSubAwardAttachment, budgetSubAwardAttachmentBean);
                budgetSubAwardAttachment.setProposalNumber(budgetSubAwardBean.getProposalNumber());
                budgetSubAwardAttachment.setBudgetVersionNumber(budgetSubAwardBean.getVersionNumber());
                budgetSubAwardAttachment.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());
            }
            catch (IllegalAccessException e) {
                LOG.warn(e);
            }
            catch (InvocationTargetException e) {
                LOG.warn(e);
            }
            budgetSubAwardAttachment.setBudgetVersionNumber(budgetSubAwardAttachmentBean.getVersionNumber());
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
        if(domDocument==null)
            throw new Exception("Not a valid pdf form");
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
    private Map extractAttachments(PdfReader reader)throws IOException, CoeusException {
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
                        throw new CoeusException(DUPLICATE_FILE_NAMES);
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
                    throw new CoeusException(DUPLICATE_FILE_NAMES);
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

        UtilFactory.log("file "+arr[0]+ " unpacked...");

        return arr;

    }

    /**
     * updates the XMl with hashcode for the files
     */

    private BudgetSubAwardBean updateXML(byte xmlContents[], Map fileMap, BudgetSubAwardBean budgetSubAwardBean) throws Exception {
        String globhashValue = "glob:HashValue";
        String globHashAlgorithm = "glob:hashAlgorithm";
        String algorithm = "SHA-1";
        String attFileName = "att:FileName";
        String fileLocation = "att:FileLocation";
        String fileContentId = "att:href";

        javax.xml.parsers.DocumentBuilderFactory domParserFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        //javax.xml.parsers.DocumentBuilderFactory domParserFactory = new com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl();
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContents);

        org.w3c.dom.Document document = domParser.parse(byteArrayInputStream);
        byteArrayInputStream.close();

        
        String xpathEmptyNodes = "//*[not(node()) and local-name(.) != 'FileLocation' and local-name(.) != 'HashValue']";// and not(FileLocation[@href])]";// and string-length(normalize-space(@*)) = 0 ]";
        String xpathOtherPers = "//*[local-name(.)='ProjectRole' and local-name(../../.)='OtherPersonnel' and count(../NumberOfPersonnel)=0]";
        removeAllEmptyNodes(document,xpathEmptyNodes,0);
        //remove otherPersonnel nodes with only project role value
        removeAllEmptyNodes(document,xpathOtherPers,1);
        //check and remove all empty nodes after removing other nodes
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
        
        //getElementsByTagName retreives elements in the order in which they are encountered.

        org.w3c.dom.NodeList lstFileName = document.getElementsByTagName(attFileName);
        org.w3c.dom.NodeList lstFileLocation = document.getElementsByTagName(fileLocation);
        org.w3c.dom.NodeList lstHashValue = document.getElementsByTagName(globhashValue);

        //check if all nodelist have same size, else something wrong.

        if((lstFileName.getLength() != lstFileLocation.getLength()) || (lstFileLocation.getLength() != lstHashValue.getLength())) {
            throw new CoeusException("Tag occurances mismatch in XML File");
        }

        org.w3c.dom.Node fileNode, hashNode;
        org.w3c.dom.NamedNodeMap fileNodeMap, hashNodeMap;
        String fileName;
        byte fileBytes[];
        String hashAlgorithm;
        HashValueType hashValueType;
        String hashValue;
        String contentId;
        List attachmentList = new ArrayList();
        DocumentTypeChecker documentTypeChecker = new DocumentTypeChecker();

        for(int index = 0; index < lstFileName.getLength(); index++) {
            fileNode = lstFileName.item(index);
            
            //fileName = fileNode.getTextContent();
            fileName = fileNode.getFirstChild().getNodeValue();

            //Get the File from fileMap
            fileBytes = (byte[])fileMap.get(fileName);

            if(fileBytes == null) {
                throw new CoeusException("FileName mismatch in XML and PDF extracted file");
            }

            //Generate hash value for the file contents
            hashValueType = S2SHashValue.getValue(fileBytes);
            hashAlgorithm = hashValueType.getHashAlgorithm();
            byte hashBytes[] = hashValueType.getValue();

            //hashValue = new String(hashBytes);

            // hashValue = Base64.encode(hashBytes);
            hashValue = Base64.encodeBytes(hashBytes);

            //hashValue = new String("Hash value"+index);
            //include the hashvalue in xml document

            hashNode = lstHashValue.item(index);
            hashNodeMap = hashNode.getAttributes();

            //hashNode.setTextContent(hashValue);

            Node temp = document.createTextNode(hashValue);
            hashNode.appendChild(temp);

            hashNode = hashNodeMap.getNamedItem(globHashAlgorithm);

            //hashNode.setTextContent(hashAlgorithm);
            hashNode.setNodeValue(hashAlgorithm);

            //retreive content Id
            fileNode = lstFileLocation.item(index);
            fileNodeMap = fileNode.getAttributes();
            fileNode = fileNodeMap.getNamedItem(fileContentId);

            //contentId = fileNode.getTextContent();
            //contentId = fileNode.getFirstChild().getNodeValue();
            contentId = fileNode.getNodeValue();

            //Update Budget Subaward Bean
            BudgetSubAwardAttachmentBean budgetSubAwardAttachmentBean = new BudgetSubAwardAttachmentBean();
            budgetSubAwardAttachmentBean.setAttachment(fileBytes);

            budgetSubAwardAttachmentBean.setContentId(contentId);

            edu.mit.coeus.utils.documenttype.DocumentType documentType = null;
            try {
                documentType = documentTypeChecker.getDocumentType(fileBytes);
            } catch(Exception exception) {
                // Could Not Determine Document Type
                UtilFactory.log(exception.getMessage(), exception, "BudgetSubAwardTxnBean","checkAndUpdate");
                budgetSubAwardAttachmentBean.setContentType("text/xml");
            }

            if(documentType != null) {
                budgetSubAwardAttachmentBean.setContentType(documentType.getMimeType());
            }
     
            budgetSubAwardAttachmentBean.setProposalNumber(budgetSubAwardBean.getProposalNumber());
            budgetSubAwardAttachmentBean.setVersionNumber(budgetSubAwardBean.getVersionNumber());
            budgetSubAwardAttachmentBean.setSubAwardNumber(budgetSubAwardBean.getSubAwardNumber());
//            budgetSubAwardAttachmentBean.setAcType(TypeConstants.INSERT_RECORD);

            attachmentList.add(budgetSubAwardAttachmentBean);
        }

        budgetSubAwardBean.setAttachments(attachmentList);
        
        //org.w3c.dom.Node node = nodeList.item(0);     
        //node.setTextContent("hash Goes here");        
        //org.w3c.dom.NamedNodeMap namedNodeMap = node.getAttributes();        
        //node = namedNodeMap.getNamedItem(globHashAlgorithm);        
        //node.setTextContent(algorithm);        
        //Transform Document
        
        javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        
        //initialize StreamResult with File object to save to file
        
        //javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.StringWriter());
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(bos);
        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
        
        transformer.transform(source, result);            
        
        budgetSubAwardBean.setSubAwardXML(new String(bos.toByteArray()).toCharArray());
        
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
}

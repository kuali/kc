/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.s2s;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.UnmarshalException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xmlbeans.XmlObject;
import org.apache.xpath.XPathAPI;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.s2sgen.api.core.InfastructureConstants;
import org.kuali.coeus.s2sgen.api.hash.GrantApplicationHashService;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.kuali.coeus.s2sgen.impl.validate.S2SValidatorService;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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

@Component("s2sUserAttachedFormService")
public class S2sUserAttachedFormServiceImpl implements S2sUserAttachedFormService {

    private static final String DUPLICATE_FILE_NAMES = "Attachments contain duplicate file names";
    private static final String XFA_NS = "http://www.xfa.org/schema/xfa-data/1.0/";

    @Autowired
    @Qualifier("s2SValidatorService")
    private S2SValidatorService s2SValidatorService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

    @Autowired
    @Qualifier("grantApplicationHashService")
    private GrantApplicationHashService grantApplicationHashService;

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<S2sUserAttachedForm> extractNSaveUserAttachedForms(DevelopmentProposal developmentProposal, 
                                                                        S2sUserAttachedForm s2sUserAttachedForm) throws Exception{
        PdfReader reader = null;
        List<S2sUserAttachedForm> formBeans = new ArrayList<S2sUserAttachedForm>();
        try{
            byte pdfFileContents[] = s2sUserAttachedForm.getNewFormFileBytes();
            if(pdfFileContents==null || pdfFileContents.length==0){
                S2SException s2sException = new S2SException(KeyConstants.S2S_USER_ATTACHED_FORM_EMPTY,"Uploaded file is empty");
              s2sException.setTabErrorKey("userAttachedFormsErrors");
              throw s2sException;
            }else{
                try{
                    reader = new PdfReader(pdfFileContents);
                }catch(IOException ioex){
                    S2SException s2sException = new S2SException(KeyConstants.S2S_USER_ATTACHED_FORM_NOT_PDF,"Uploaded file is not Grants.Gov fillable form",ioex.getMessage());
                    s2sException.setTabErrorKey("userAttachedFormsErrors");
                    throw s2sException;
                }
                Map attachments = extractAttachments(reader);
                formBeans = extractAndPopulateXml(developmentProposal,reader,s2sUserAttachedForm,attachments);
            }
            resetFormsAvailability(developmentProposal,formBeans);
        }finally{
            if(reader!=null) reader.close();
        }
        return formBeans;
    }

    private void resetFormsAvailability(DevelopmentProposal developmentProposal, List<S2sUserAttachedForm> savedFormBeans) {
        S2sOpportunity opportunity = developmentProposal.getS2sOpportunity();
        if(opportunity!=null){
            List<S2sOppForms> oppForms = opportunity.getS2sOppForms(); 
            if(oppForms!=null){
                for (S2sUserAttachedForm s2sUserAttachedForm : savedFormBeans) {
                    for (S2sOppForms s2sOppForms : oppForms) {
                        if(s2sOppForms.getS2sOppFormsId().getOppNameSpace().equals(s2sUserAttachedForm.getNamespace())){
                            s2sOppForms.setAvailable(true);
                            s2sOppForms.setUserAttachedForm(true);
                        }
                    }
                }
            }
        }
        
    }

    private Map extractAttachments(PdfReader reader)throws IOException{
        Map fileMap = new HashMap();
        
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary names = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.NAMES));
        if (names != null) {
            PdfDictionary embFiles = (PdfDictionary) PdfReader.getPdfObject(names.get(new PdfName("EmbeddedFiles")));
            if (embFiles != null) {
                HashMap embMap = PdfNameTree.readTree(embFiles);
                
                for (Iterator i = embMap.values().iterator(); i.hasNext();) {
                    PdfDictionary filespec = (PdfDictionary) PdfReader.getPdfObject((PdfObject) i.next());
                    Object[] fileInfo = unpackFile(reader, filespec);
                    if(!fileMap.containsKey(fileInfo[0])) {
                        fileMap.put(fileInfo[0], fileInfo[1]);
                    }
                }
            }
        }
        for (int k = 1; k <= reader.getNumberOfPages(); ++k) {
            PdfArray annots = (PdfArray) PdfReader.getPdfObject(reader.getPageN(k).get(PdfName.ANNOTS));
            if (annots == null)
                continue;
            for (Iterator i = annots.listIterator(); i.hasNext();) {
                PdfDictionary annot = (PdfDictionary) PdfReader.getPdfObject((PdfObject) i.next());
                PdfName subType = (PdfName) PdfReader.getPdfObject(annot.get(PdfName.SUBTYPE));
                if (!PdfName.FILEATTACHMENT.equals(subType))
                    continue;
                PdfDictionary filespec = (PdfDictionary) PdfReader.getPdfObject(annot.get(PdfName.FS));
                Object[] fileInfo = unpackFile(reader, filespec);
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
     *
     * @param reader
     *            The object that reads the PDF document
     * @param filespec
     *            The dictonary containing the file specifications
     * @throws IOException
     */
    private Object[] unpackFile(PdfReader reader, PdfDictionary filespec)throws IOException  {
        S2sUserAttachedFormAtt userAttachedS2SFormAttachmentBean = new S2sUserAttachedFormAtt();
        
        if (filespec == null)
            return null;
        
        PdfName type = (PdfName) PdfReader.getPdfObject(filespec.get(PdfName.TYPE));
        
        if (!PdfName.F.equals(type) && !PdfName.FILESPEC.equals(type))
            return null;
        
        PdfDictionary ef = (PdfDictionary) PdfReader.getPdfObject(filespec.get(PdfName.EF));
        if (ef == null)
            return null;
        
        PdfString fn = (PdfString) PdfReader.getPdfObject(filespec.get(PdfName.F));
        if (fn == null)
            return null;
        
        File fLast = new File(fn.toUnicodeString());
        
        PRStream prs = (PRStream) PdfReader.getPdfObject(ef.get(PdfName.F));
        if (prs == null)
            return null;
        
        
        
        byte attachmentByte[] = PdfReader.getStreamBytes(prs);
        Object[] fileInfo = new Object[2];
        fileInfo[0]=fLast.getName();
        fileInfo[1]=attachmentByte;
        return fileInfo;
    }
    
    private List<S2sUserAttachedForm> extractAndPopulateXml(DevelopmentProposal developmentProposal, PdfReader reader, S2sUserAttachedForm userAttachedForm, Map attachments) throws Exception {
        List<S2sUserAttachedForm> formBeans = new ArrayList<S2sUserAttachedForm>();
        XfaForm xfaForm = reader.getAcroFields().getXfa();
        Node domDocument = xfaForm.getDomDocument();
        if(domDocument==null){
            S2SException s2sException = new S2SException(KeyConstants.S2S_USER_ATTACHED_FORM_WRONG_FILE_TYPE,"Uploaded file is not Grants.Gov fillable form");
            s2sException.setTabErrorKey("userAttachedFormsErrors");
            throw s2sException;
        }
        Element documentElement = ((Document) domDocument).getDocumentElement();

        Element datasetsElement = (Element) documentElement.getElementsByTagNameNS(XFA_NS, "datasets").item(0);
        Element dataElement = (Element) datasetsElement.getElementsByTagNameNS(XFA_NS, "data").item(0);
        Element grantApplicationElement = (Element) dataElement.getChildNodes().item(0);

        byte[] serializedXML = XfaForm.serializeDoc(grantApplicationElement);
        DocumentBuilderFactory domParserFactory = DocumentBuilderFactory.newInstance();
        domParserFactory.setNamespaceAware(true);
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        domParserFactory.setIgnoringElementContentWhitespace(true);
        ByteArrayInputStream byteArrayInputStream=null;
        org.w3c.dom.Document document = null;
        try{
            byteArrayInputStream = new ByteArrayInputStream(serializedXML);
            document = domParser.parse(byteArrayInputStream);
        }finally{
            if(byteArrayInputStream!=null) byteArrayInputStream.close();
        }
        if (document != null) {
            Element form = null;
            NodeList elements = document.getElementsByTagNameNS("http://apply.grants.gov/system/MetaGrantApplication", "Forms");
            Element element = (Element)elements.item(0);
            if(element!=null){
                NodeList formChildren = element.getChildNodes();
                int formsCount = formChildren.getLength();
                if(formsCount>1){
                     NodeList selectedOptionalFormElements = document.getElementsByTagNameNS("http://apply.grants.gov/system/MetaGrantApplicationWrapper", "SelectedOptionalForms");
                    int selectedOptionalFormsCount = selectedOptionalFormElements==null?0:selectedOptionalFormElements.getLength();
                    if (selectedOptionalFormsCount > 0) {
                        Element selectedFormNode = (Element) selectedOptionalFormElements.item(0);
                        NodeList selectedForms = selectedFormNode.getElementsByTagNameNS("http://apply.grants.gov/system/MetaGrantApplicationWrapper","FormTagName");
                        int selectedFormsCount = selectedForms == null ? 0 : selectedForms.getLength();
                        if (selectedFormsCount > 0) {
                            List seletctedForms = new ArrayList();
                            for (int j = 0; j < selectedFormsCount; j++) {
                                Element selectedForm = (Element) selectedForms.item(j);
                                String formName = selectedForm.getTextContent();
                                seletctedForms.add(formName);

                            }
                            List exceptions = new ArrayList();
                            for (int i = 0; i < formsCount; i++) {
                                form = (Element) formChildren.item(i);
                                String formNodeName = form.getLocalName();
                                if (seletctedForms.contains(formNodeName)) {
                                    try{
                                        addForm(developmentProposal,formBeans,form,userAttachedForm, attachments);
                                    }catch(UnmarshalException ume){
                                        exceptions.add("Not able to create xml for the form "+formNodeName+" Root Cause:"+ume.getMessage()+"<br>");
                                    }

                                }
                            }
                            if(!exceptions.isEmpty()) throw new S2SException(exceptions.toString());
                        }
                    }
                }else{
                    form = (Element) formChildren.item(0);
                    addForm(developmentProposal,formBeans,form,userAttachedForm, attachments);
                }
            }else{
                form = document.getDocumentElement();
                addForm(developmentProposal,formBeans,form,userAttachedForm, attachments);
            }
        }
        return formBeans;
    }
    private void addForm(DevelopmentProposal developmentProposal, List formBeans, Element form,
            S2sUserAttachedForm userAttachedFormBean, Map attachments) throws Exception {
        S2sUserAttachedForm userAttachedForm = processForm(form,userAttachedFormBean,attachments);
        if(userAttachedForm!=null){
            validateForm(developmentProposal,userAttachedForm);
            formBeans.add(userAttachedForm);
        }
    }
    private void validateForm(DevelopmentProposal developmentProposal, S2sUserAttachedForm userAttachedForm) throws S2SException{
        S2sOpportunity opportunity = developmentProposal.getS2sOpportunity(); 
        if(opportunity!=null){
            List<S2sUserAttachedForm> userAttachedForms = developmentProposal.getS2sUserAttachedForms();
            for (S2sUserAttachedForm s2sForm : userAttachedForms) {
                if(userAttachedForm.getNamespace().equals(s2sForm.getNamespace())){
                    S2SException s2sException  = new S2SException(KeyConstants.S2S_DUPLICATE_USER_ATTACHED_FORM,
                                "The form is already available in the forms list",userAttachedForm.getFormName());
                    s2sException.setTabErrorKey("userAttachedFormsErrors");
                    throw s2sException;
                }
            }
        }
    }

    private S2sUserAttachedForm processForm(Element form,S2sUserAttachedForm userAttachedForm, Map attachments) throws Exception {
        
        String formname = null;
        String namespaceUri = null;
        String formXML = null;
        namespaceUri = form.getNamespaceURI();
        formname = form.getLocalName();
        FormMappingInfo bindingInfoBean = formMappingService.getFormInfo(namespaceUri);
        if(bindingInfoBean != null) {
            return null;
        }
        Document doc = node2Dom(form);
        String xpathEmptyNodes = "//*[not(node()) and local-name(.) != 'FileLocation' and local-name(.) != 'HashValue' and local-name(.) != 'FileName']";// and not(FileLocation[@href])]";// and string-length(normalize-space(@*)) = 0 ]";
        String xpathOtherPers = "//*[local-name(.)='ProjectRole' and local-name(../../.)='OtherPersonnel' and count(../NumberOfPersonnel)=0]";
        removeAllEmptyNodes(doc,xpathEmptyNodes,0);
        removeAllEmptyNodes(doc,xpathOtherPers,1);
        removeAllEmptyNodes(doc,xpathEmptyNodes,0);
        NodeList hashValueNodes =  doc.getElementsByTagName("glob:HashValue");
        for (int i = 0; i < hashValueNodes.getLength(); i++) {
            Node hashValue = hashValueNodes.item(i);
            ((Element)hashValue).setAttribute("xmlns:glob", "http://apply.grants.gov/system/Global-V1.0");
        }
        if(!validateForm(doc,namespaceUri))
            return null;
        S2sUserAttachedForm newUserAttachedForm = cloneUserAttachedForm(userAttachedForm);
        newUserAttachedForm.setNamespace(namespaceUri);
        newUserAttachedForm.setFormName(formname);
        updateAttachmentNodes(doc,newUserAttachedForm,attachments);
        formXML = docToString(doc);
        
        S2sUserAttachedFormFile newUserAttachedFormFile = new S2sUserAttachedFormFile();
        newUserAttachedFormFile.setXmlFile(formXML);
        newUserAttachedFormFile.setFormFile(userAttachedForm.getNewFormFileBytes());
        newUserAttachedFormFile.setS2sUserAttachedFormId(userAttachedForm.getId());
        newUserAttachedFormFile.setUpdateUser(userAttachedForm.getUpdateUser());
        newUserAttachedForm.getS2sUserAttachedFormFileList().add(newUserAttachedFormFile);
        return newUserAttachedForm;
        
    }
    public synchronized static Document node2Dom(org.w3c.dom.Node n) throws Exception{
            javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer xf = tf.newTransformer();
            javax.xml.transform.dom.DOMResult dr = new javax.xml.transform.dom.DOMResult();
            xf.transform(new javax.xml.transform.dom.DOMSource(n),dr);
            return (Document)dr.getNode();
    }
    public void removeAllEmptyNodes(Document document,String xpath,int parentLevel) throws TransformerException {
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

    private boolean validateForm(Document userAttachedFormDocument, String namespace) throws Exception{
        XmlObject xmlObject = XmlObject.Factory.parse(userAttachedFormDocument);
        List<AuditError> errors = new ArrayList<AuditError>();
        if(!s2SValidatorService.validate(xmlObject, errors)) {
            setValidationErrorMessage(errors);
            return false;
        }else{
            return true;
        }
        
    }
    protected void setValidationErrorMessage(List<AuditError> errors) {
        for (AuditError error : errors) {
            GlobalVariables.getMessageMap().putError("userAttachedFormsErrors", KeyConstants.S2S_USER_ATTACHED_FORM_NOT_VALID, error.getMessageKey());
        }
    }
    public String createPackageName(String namespace) {
        String namespaceLowercase = namespace.toLowerCase();
        StringBuffer packageName = new StringBuffer();
        int beginIndex = namespaceLowercase.indexOf("://")+3;
        int endIndex = namespaceLowercase.length();
        String packageSubString = namespaceLowercase.subSequence(beginIndex, endIndex).toString();
        String[] tokens = packageSubString.split("/");
        for (int i = 0; i < tokens.length; i++) {
            String packageToken = tokens[i];
            String[] subTokens = packageToken.split("\\.");
            if(subTokens.length>1){
                int subTokensLength = (i==(tokens.length-1))?subTokens.length-2:subTokens.length-1;
                for (int j = subTokensLength; j >=0 ; j--) {
                    String subToken = subTokens[j];
                    packageName.append(subToken);
                    packageName.append(".");
                }
            }else{
                packageName.append(packageToken);
                packageName.append(".");
            }
        }
        String finalPackageName = packageName.charAt(packageName.length()-1)=='.'?packageName.substring(0, packageName.length()-1):packageName.toString();
        return finalPackageName.toString().replaceAll("-", "_");
    }

    /**
     * This method convert Document to a byte Array
     * 
     * @param node {Document} node entry.
     * @return byte Array containing doc information
     */
    public byte[] docToBytes(Document node) throws S2SException {
        return docToString(node).getBytes();
    }

    /**
     * This method convert Document to a String
     * 
     * @param node {Document} node entry.
     * @return String containing doc information
     */
    public String docToString(Document node) throws S2SException {
        try {
            DOMSource domSource = new DOMSource(node);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch (Exception e) {
            throw new S2SException(e.getMessage());
        }
    }
    private S2sUserAttachedForm cloneUserAttachedForm(
            S2sUserAttachedForm userAttachedForm) {
        S2sUserAttachedForm newUserAttachedForm = new S2sUserAttachedForm();
        newUserAttachedForm.setDescription(userAttachedForm.getDescription());
        newUserAttachedForm.setFormFileName(userAttachedForm.getFormFileName());
        newUserAttachedForm.setNamespace(userAttachedForm.getNamespace());
        newUserAttachedForm.setFormName(userAttachedForm.getFormName());
        newUserAttachedForm.setS2sUserAttachedFormAtts(userAttachedForm.getS2sUserAttachedFormAtts());
        newUserAttachedForm.setProposalNumber(userAttachedForm.getProposalNumber());
        newUserAttachedForm.setUpdateUser(userAttachedForm.getUpdateUser());
        newUserAttachedForm.setUpdateTimestamp(userAttachedForm.getUpdateTimestamp());
        return newUserAttachedForm;
    }

    /**
     * Gets the s2SValidatorService attribute. 
     * @return Returns the s2SValidatorService.
     */
    public S2SValidatorService getS2SValidatorService() {
        return s2SValidatorService;
    }

    /**
     * Sets the s2SValidatorService attribute value.
     * @param s2sValidatorService The s2SValidatorService to set.
     */
    public void setS2SValidatorService(S2SValidatorService s2sValidatorService) {
        s2SValidatorService = s2sValidatorService;
    }

    private void updateAttachmentNodes(Document document, S2sUserAttachedForm userAttachedFormBean, Map attachments) throws Exception{
        NodeList lstFileName = document.getElementsByTagNameNS("http://apply.grants.gov/system/Attachments-V1.0", "FileName");
        NodeList lstFileLocation = document.getElementsByTagNameNS("http://apply.grants.gov/system/Attachments-V1.0", "FileLocation");
        NodeList lstHashValue = document.getElementsByTagNameNS("http://apply.grants.gov/system/Global-V1.0", "HashValue");
        NodeList lstMimeType = document.getElementsByTagNameNS("http://apply.grants.gov/system/Attachments-V1.0","MimeType");


        if ( (lstFileName.getLength() != lstFileLocation.getLength()) || 
                (lstFileLocation.getLength() != lstHashValue.getLength())) {
            throw new S2SException("Attachment node occurances and number of attachments mismatch in PDF File");
        }

        org.w3c.dom.Node fileNode, hashNode, mimeTypeNode;
        org.w3c.dom.NamedNodeMap fileNodeMap;
        String fileName; 
        byte fileBytes[];
        String hashValue;
        String contentId;
        List<S2sUserAttachedFormAtt> attachmentList = new ArrayList<S2sUserAttachedFormAtt>();
        for (int index = 0; index < lstFileName.getLength(); index++) {
            fileNode = lstFileName.item(index);
            if (fileNode.getFirstChild() == null) {
                continue;//no attachments
            }
            fileName = fileNode.getFirstChild().getNodeValue(); 
            fileBytes = (byte[]) attachments.get(fileName);
            if (fileBytes == null) {
                throw new S2SException("FileName mismatch in XML and PDF extracted file");
            }

            hashValue = grantApplicationHashService.computeAttachmentHash(fileBytes);
            hashNode = lstHashValue.item(index);
            NamedNodeMap hashNodeMap = hashNode.getAttributes();
            Node temp = document.createTextNode(hashValue);
            hashNode.appendChild(temp);

            Node hashAlgorithmNode = hashNodeMap.getNamedItemNS("http://apply.grants.gov/system/Global-V1.0", "hashAlgorithm");
            hashAlgorithmNode.setNodeValue(InfastructureConstants.HASH_ALGORITHM);
            hashNodeMap.setNamedItemNS(hashAlgorithmNode);
            
            fileNode = lstFileLocation.item(index);
            fileNodeMap = fileNode.getAttributes();
            fileNode = fileNodeMap.getNamedItemNS("http://apply.grants.gov/system/Attachments-V1.0", "href");
            contentId = fileNode.getNodeValue();

            S2sUserAttachedFormAtt userAttachedFormAttachment = new S2sUserAttachedFormAtt();
            S2sUserAttachedFormAttFile userAttachedFormAttachmentFile = new S2sUserAttachedFormAttFile();
            userAttachedFormAttachmentFile.setAttachment(fileBytes);
            userAttachedFormAttachment.getS2sUserAttachedFormAttFiles().add(userAttachedFormAttachmentFile);
            userAttachedFormAttachment.setContentId(contentId);
            userAttachedFormAttachment.setName(fileName);
            
            mimeTypeNode = lstMimeType.item(0);
            String contentType = mimeTypeNode.getFirstChild().getNodeValue();
            userAttachedFormAttachment.setType(contentType);

            userAttachedFormAttachment.setProposalNumber(userAttachedFormBean.getProposalNumber());
            userAttachedFormAttachment.setS2sUserAttachedFormId(userAttachedFormBean.getId());
            attachmentList.add(userAttachedFormAttachment);
        }
        userAttachedFormBean.setS2sUserAttachedFormAtts(attachmentList);
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    @Override
    public void resetFormAvailability(DevelopmentProposal developmentProposal, String namespace) {
        S2sOpportunity opportunity = developmentProposal.getS2sOpportunity(); 
        if(opportunity!=null){
            List<S2sOppForms> oppForms = opportunity.getS2sOppForms(); 
            if(oppForms!=null)
            for (S2sOppForms s2sOppForms : oppForms) {
                if(s2sOppForms.getS2sOppFormsId().getOppNameSpace().equals(namespace)){
                    s2sOppForms.setInclude(false);
                    s2sOppForms.setAvailable(false);
                    s2sOppForms.setUserAttachedForm(false);
                }
            }
        }
    }

    public FormMappingService getFormMappingService() {
        return formMappingService;
    }

    public void setFormMappingService(FormMappingService formMappingService) {
        this.formMappingService = formMappingService;
    }

    public GrantApplicationHashService getGrantApplicationHashService() {
        return grantApplicationHashService;
    }

    public void setGrantApplicationHashService(GrantApplicationHashService grantApplicationHashService) {
        this.grantApplicationHashService = grantApplicationHashService;
    }
}

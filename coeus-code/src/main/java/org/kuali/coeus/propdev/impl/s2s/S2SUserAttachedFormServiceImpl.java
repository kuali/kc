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
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.service.S2SValidatorService;
import org.kuali.kra.s2s.util.AuditError;
import org.kuali.kra.s2s.util.GrantApplicationHash;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.krad.data.DataObjectService;
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

@Component("s2SUserAttachedFormService")
public class S2SUserAttachedFormServiceImpl implements S2SUserAttachedFormService {
    private static final String DUPLICATE_FILE_NAMES = "Attachments contain duplicate file names";
    private static final String XFA_NS = "http://www.xfa.org/schema/xfa-data/1.0/";

    @Autowired
    @Qualifier("s2SValidatorService")
    private S2SValidatorService s2SValidatorService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    @Override
    public List<S2sUserAttachedForm> extractNSaveUserAttachedForms(DevelopmentProposal developmentProposal,
                                                                        S2sUserAttachedForm s2sUserAttachedForm) throws Exception{
        PdfReader reader = null;
        List<S2sUserAttachedForm> formBeans = new ArrayList<S2sUserAttachedForm>();
        List<S2sUserAttachedForm> savedFormBeans;
        try{
            byte pdfFileContents[] = s2sUserAttachedForm.getFormFile();
            if(pdfFileContents==null || pdfFileContents.length==0){
                throw new S2SException("Uploaded file is empty");
            }else{
                reader = new PdfReader(pdfFileContents);
                Map attachments = extractAttachments(reader);
                formBeans = extractAndPopulateXml(reader,s2sUserAttachedForm,attachments);
            }
            savedFormBeans = dataObjectService.save(formBeans);
            resetFormsAvailability(developmentProposal,savedFormBeans);
        }finally{
            if(reader!=null) reader.close();
        }
        return savedFormBeans;
    }

    private void resetFormsAvailability(DevelopmentProposal developmentProposal, List<S2sUserAttachedForm> savedFormBeans) {
        S2sOpportunity opportunity = developmentProposal.getS2sOpportunity();
        if(opportunity!=null){
            List<S2sOppForms> oppForms = opportunity.getS2sOppForms(); 
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

    private Map extractAttachments(PdfReader reader)throws IOException{
        Map<Object, Object> fileMap = new HashMap<>();
        
        PdfDictionary catalog = reader.getCatalog();
        PdfDictionary names = (PdfDictionary) PdfReader.getPdfObject(catalog.get(PdfName.NAMES));
        if (names != null) {
            PdfDictionary embFiles = (PdfDictionary) PdfReader.getPdfObject(names.get(new PdfName("EmbeddedFiles")));
            if (embFiles != null) {
                HashMap embMap = PdfNameTree.readTree(embFiles);
                
                for (Map.Entry<Object, Object> entry : fileMap.entrySet()) {
                    PdfDictionary filespec = (PdfDictionary) PdfReader.getPdfObject((PdfObject) entry.getValue());
                    Object[] fileInfo = unpackFile(filespec);
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
                Object[] fileInfo = unpackFile(filespec);
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
     * @param filespec
     *            The dictonary containing the file specifications
     * @throws IOException
     */
    private Object[] unpackFile(PdfDictionary filespec)throws IOException  {

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
    
    private List<S2sUserAttachedForm> extractAndPopulateXml(PdfReader reader, S2sUserAttachedForm userAttachedFormBean, Map attachments) throws Exception {
        List<S2sUserAttachedForm> formBeans = new ArrayList<>();
        XfaForm xfaForm = reader.getAcroFields().getXfa();
        Node domDocument = xfaForm.getDomDocument();
        if(domDocument==null){
            return formBeans; 
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
            Element form;
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
                            List<String> exceptions = new ArrayList<>();
                            for (int i = 0; i < formsCount; i++) {
                                form = (Element) formChildren.item(i);
                                String formNodeName = form.getLocalName();
                                if (seletctedForms.contains(formNodeName)) {
                                    try{
                                        addForm(formBeans,form,userAttachedFormBean, attachments);
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
                    addForm(formBeans,form,userAttachedFormBean, attachments);
                }
            }else{
                form = document.getDocumentElement();
                addForm(formBeans,form,userAttachedFormBean, attachments);
            }
        }
        return formBeans;
    }
    private void addForm(List<S2sUserAttachedForm> formBeans, Element form,
            S2sUserAttachedForm userAttachedFormBean, Map attachments) throws Exception {
        S2sUserAttachedForm userAttachedForm = processForm(form,userAttachedFormBean,attachments);
        if(userAttachedForm!=null){
            userAttachedForm.setXmlDataExists(userAttachedForm.getXmlFile()!=null);
            userAttachedForm.setFormFileDataExists(userAttachedForm.getFormFile()!=null);
            formBeans.add(userAttachedForm);
        }
    }
    private S2sUserAttachedForm processForm(Element form,S2sUserAttachedForm userAttachedForm, Map attachments) throws Exception {
        
        
        String formname;
        String namespaceUri;
        String formXML;
        namespaceUri = form.getNamespaceURI();
        formname = form.getLocalName();
        FormMappingInfo bindingInfoBean = new FormMappingLoader().getFormInfo(namespaceUri);
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
        validateForm(doc);
        
        S2sUserAttachedForm newUserAttachedFormBean = cloneUserAttachedForm(userAttachedForm);
        newUserAttachedFormBean.setNamespace(namespaceUri);
        newUserAttachedFormBean.setFormName(formname);
        updateAttachmentNodes(doc,newUserAttachedFormBean,attachments);
        formXML = docToString(doc);
        newUserAttachedFormBean.setXmlFile(formXML);
        return newUserAttachedFormBean;
        
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
    private void validateForm(Document userAttachedFormDocument) throws Exception{
        XmlObject xmlObject = XmlObject.Factory.parse(userAttachedFormDocument);
        List<AuditError> errors = new ArrayList<AuditError>();
        s2SValidatorService.validate(xmlObject, errors) ;
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
            S2sUserAttachedForm userAttachedFormBean) {
        S2sUserAttachedForm newUserAttachedFormBean = new S2sUserAttachedForm();
        newUserAttachedFormBean.setUserAttachedFormNumber(userAttachedFormBean.getUserAttachedFormNumber());
        newUserAttachedFormBean.setDescription(userAttachedFormBean.getDescription());
        newUserAttachedFormBean.setFormFile(userAttachedFormBean.getFormFile());
        newUserAttachedFormBean.setFormFileName(userAttachedFormBean.getFormFileName());
        newUserAttachedFormBean.setXmlFile(userAttachedFormBean.getXmlFile());
        newUserAttachedFormBean.setNamespace(userAttachedFormBean.getNamespace());
        newUserAttachedFormBean.setFormName(userAttachedFormBean.getFormName());
        newUserAttachedFormBean.setS2sUserAttachedFormAtts(userAttachedFormBean.getS2sUserAttachedFormAtts());
        newUserAttachedFormBean.setProposalNumber(userAttachedFormBean.getProposalNumber());
        newUserAttachedFormBean.setUpdateUser(userAttachedFormBean.getUpdateUser());
        newUserAttachedFormBean.setUpdateTimestamp(userAttachedFormBean.getUpdateTimestamp());
        return newUserAttachedFormBean;
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
        List<S2sUserAttachedFormAtt> attachmentList = new ArrayList<>();
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

            hashValue = GrantApplicationHash.computeAttachmentHash(fileBytes);
            hashNode = lstHashValue.item(index);
            NamedNodeMap hashNodeMap = hashNode.getAttributes();
            Node temp = document.createTextNode(hashValue);
            hashNode.appendChild(temp);

            Node hashAlgorithmNode = hashNodeMap.getNamedItemNS("http://apply.grants.gov/system/Global-V1.0", "hashAlgorithm");
            hashAlgorithmNode.setNodeValue(S2SConstants.HASH_ALGORITHM);
            hashNodeMap.setNamedItemNS(hashAlgorithmNode);
            
            fileNode = lstFileLocation.item(index);
            fileNodeMap = fileNode.getAttributes();
            fileNode = fileNodeMap.getNamedItemNS("http://apply.grants.gov/system/Attachments-V1.0", "href");
            contentId = fileNode.getNodeValue();
            S2sUserAttachedFormAtt userAttachedFormAttachmentBean = new S2sUserAttachedFormAtt();
            userAttachedFormAttachmentBean.setAttachment(fileBytes);
            userAttachedFormAttachmentBean.setContentId(contentId);
            userAttachedFormAttachmentBean.setFileName(fileName);
            
            mimeTypeNode = lstMimeType.item(0);
            String contentType = mimeTypeNode.getFirstChild().getNodeValue();
            userAttachedFormAttachmentBean.setContentType(contentType);

            userAttachedFormAttachmentBean.setProposalNumber(userAttachedFormBean.getProposalNumber());
            userAttachedFormAttachmentBean.setS2sUserAttachedFormId(userAttachedFormBean.getS2sUserAttachedFormId());
            attachmentList.add(userAttachedFormAttachmentBean);
        }
        userAttachedFormBean.setS2sUserAttachedFormAtts(attachmentList);
    }

    @Override
    public void resetFormAvailability(DevelopmentProposal developmentProposal, String namespace) {
        S2sOpportunity opportunity = developmentProposal.getS2sOpportunity(); 
        if(opportunity!=null){
            List<S2sOppForms> oppForms = opportunity.getS2sOppForms(); 
            for (S2sOppForms s2sOppForms : oppForms) {
                if(s2sOppForms.getS2sOppFormsId().getOppNameSpace().equals(namespace)){
                    s2sOppForms.setAvailable(false);
                    s2sOppForms.setUserAttachedForm(false);
                }
            }
        }
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public S2SValidatorService getS2SValidatorService() {
        return s2SValidatorService;
    }

    public void setS2SValidatorService(S2SValidatorService s2SValidatorService) {
        this.s2SValidatorService = s2SValidatorService;
    }
}

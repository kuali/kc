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
package org.kuali.kra.budget.bo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
import edu.mit.coeus.utils.TypeConstants;
import edu.mit.coeus.utils.UtilFactory;
import edu.mit.coeus.utils.documenttype.DocumentTypeChecker;
import gov.grants.apply.system.global_v1.HashValueType;

public class BudgetSubAwardReader {

    private static final String DUPLICATE_FILE_NAMES =  "Duplicate PDF Attachment File Names"; 
    private static final String XFA_NS = "http://www.xfa.org/schema/xfa-data/1.0/";
    
    /**
     * updates the subawawd by extracting the xml, attachments from pdf and 
     * modifying the xml to include hashvalues for the attachments
     * 
     * JF: Changed signature from
     *      public BudgetSubAwardBean populateSubAward(BudgetSubAwardBean budgetSubAwardBean)throws Exception
     * to
     *      public byte[] populateSubAward(BudgetSubAwardBean budgetSubAwardBean)throws Exception
     *  so we can return XML as bytes
     */

    @SuppressWarnings("unchecked")
    public byte[] populateSubAward(BudgetSubAwardBean budgetSubAwardBean)throws Exception {
        byte[] pdfFileContents = budgetSubAwardBean.getSubAwardXFD();
        PdfReader reader = new PdfReader(pdfFileContents);
        UtilFactory.log("Before getXMLFromPDF..");
        byte[] xmlContents = getXMLFromPDF(reader);
        UtilFactory.log("Got extracted XML..");
        UtilFactory.log("extract attachments...");
        Map fileMap = extractAttachments(reader);
        UtilFactory.log("extracted attachments...");
        UtilFactory.log("update XML....");

        budgetSubAwardBean = updateXML(xmlContents, fileMap, budgetSubAwardBean);
        UtilFactory.log("updated XML....");

        return xmlContents;

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
        UtilFactory.log("extracted Xml from pdf..");

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

        //javax.xml.parsers.DocumentBuilderFactory domParserFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        javax.xml.parsers.DocumentBuilderFactory domParserFactory = new com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl();
        javax.xml.parsers.DocumentBuilder domParser = domParserFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlContents);

        org.w3c.dom.Document document = domParser.parse(byteArrayInputStream);
        byteArrayInputStream.close();

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
            budgetSubAwardAttachmentBean.setAcType(TypeConstants.INSERT_RECORD);

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
}

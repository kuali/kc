/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.impl.person.signature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.kuali.coeus.common.framework.person.signature.PersonSignature;
import org.kuali.coeus.common.framework.person.signature.PersonSignatureLocationHelper;
import org.kuali.coeus.common.framework.person.signature.PersonSignatureModule;
import org.kuali.coeus.common.framework.person.signature.PersonSignaturePrintHelper;
import org.kuali.coeus.common.framework.person.signature.PersonSignatureService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

public abstract class PersonSignatureServiceImpl implements PersonSignatureService {
    
    private static final String CORRESPONDENCE_SIGNATURE_TYPE = "CORRESPONDENCE_SIGNATURE_TYPE";
    private static final String CORRESPONDENCE_SIGNATURE_TAG = "CORRESPONDENCE_SIGNATURE_TAG";
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;

    private static final float ADDITIONAL_SPACE_BETWEEN_TAG_AND_IMAGE = 10f;

    protected static final String PERSON_SIGNATURE_ACTIVE = "signatureActive";
    protected static final String PERSON_SIGNATURE_PERSON_ID = "personId";
    protected static final String DEFAULT_SIGNATURE = "defaultSignature";
    protected static final String MODULE_CODE = "moduleCode";
    
    private static final Log LOG = LogFactory.getLog(PersonSignatureServiceImpl.class);
    
    /**
     * Configure signature type required
     * D - Always use Default signature
     * S - Always use signed in user signature, if not found use the default signature
     * N - No signature is required
     */
    public enum SignatureTypes {
        DEFAULT_SIGNATURE("D"), 
        SIGNED_IN_USER_SIGNATURE("S"), 
        NO_SIGNATURE_REQURIED("N");

        private String signatureType;

        private SignatureTypes(String signatureType) {
            this.signatureType = signatureType;
        }

        public String getSignatureType() {
            return signatureType;
        }

        public static SignatureTypes getSignatureMode(String signatureType) {
            for (SignatureTypes sType : values() ){
                if (sType.signatureType.equals(signatureType)) {
                    return sType;
                }
            }
            return null;
        }
        
    }    

    @Override
    public byte[] applySignature(byte[] pdfBytes) throws Exception {
        byte[] pdfFileData = pdfBytes;
        ByteArrayOutputStream byteArrayOutputStream = getOriginalPdfDocumentAsOutputsStream(pdfBytes); //getFlattenedPdfForm(pdfFileData);
        byteArrayOutputStream = identifyModeAndApplySignature(byteArrayOutputStream);
        if(ObjectUtils.isNotNull(byteArrayOutputStream)) {
            pdfFileData = byteArrayOutputStream.toByteArray();
        }
        return pdfFileData;
    }
    
    /**
     * This method is to identify signature mode and invoke appropriate method
     * If signature is not available, return the original.
     * to sign the document.
     * @param originalByteArrayOutputStream
     * @return
     * @throws Exception
     */
    protected ByteArrayOutputStream identifyModeAndApplySignature(ByteArrayOutputStream originalByteArrayOutputStream) throws Exception {
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        String signatureTypeParam = getSignatureTypeParameter();
        SignatureTypes signatureType = SignatureTypes.NO_SIGNATURE_REQURIED;
        if(ObjectUtils.isNotNull(signatureTypeParam)) {
            signatureType = SignatureTypes.getSignatureMode(signatureTypeParam);
        }
        
        if(signatureType != null) {
            switch(signatureType) {
                case DEFAULT_SIGNATURE :
                    outputStream = printDefaultSignature(outputStream);
                    break;
                case SIGNED_IN_USER_SIGNATURE :
                    String personId = GlobalVariables.getUserSession().getPerson().getPrincipalId();
                    outputStream = printLoggedInUserSignature(personId, outputStream);
                    break;
                case NO_SIGNATURE_REQURIED :
                    break;
            }
        }else {
            LOG.warn("Invalid signature type defined in parameter");
        }
        
        return outputStream;
    }
    
    /**
     * This method is to print default module authorized signature.
     * Original document is returned if signature is not available.
     * @param originalByteArrayOutputStream
     * @return
     * @throws Exception
     */
    protected ByteArrayOutputStream printDefaultSignature(ByteArrayOutputStream originalByteArrayOutputStream) throws Exception{
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        PersonSignature authorizedSignature = getDefaultSignature();
        if(ObjectUtils.isNotNull(authorizedSignature)) {
            if(ObjectUtils.isNotNull(authorizedSignature.getAttachmentContent())) {
                outputStream = applyAutographInDocument(authorizedSignature, outputStream);
            }
        }
        return outputStream;
    }

    /**
     * This method is to print logged in user signature.
     * If logged in user signature is not available, get the default signature for applicable module.
     * Original document is returned if signature is not available.
     * @param personId
     * @param originalByteArrayOutputStream
     * @return
     * @throws Exception
     */
    protected ByteArrayOutputStream printLoggedInUserSignature(String personId, ByteArrayOutputStream originalByteArrayOutputStream) throws Exception{
        PersonSignature userSignature = getLoggedinPersonSignature(personId);
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        if(ObjectUtils.isNull(userSignature)) {
            userSignature = getDefaultSignature();
        }
        if(ObjectUtils.isNotNull(userSignature)) {
            if(ObjectUtils.isNotNull(userSignature.getAttachmentContent())) {
                outputStream = applyAutographInDocument(userSignature, outputStream);
            }
        }
        return outputStream;
    }
    
    /**
     * This method is to apply signature in the document.
     * @param personSignature
     * @param originalByteArrayOutputStream
     * @return
     */
    protected ByteArrayOutputStream applyAutographInDocument(PersonSignature personSignature, ByteArrayOutputStream originalByteArrayOutputStream) {
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        try {
            if (personSignature.getAttachmentContent() != null) {
                outputStream = scanAndApplyAutographInEachPage(personSignature.getAttachmentContent(), outputStream);
            }
        }catch (Exception ex) {
                LOG.error("Exception while applying signature : Method applyAutographInDocument : " + ex.getMessage());
                LOG.error(ex.getMessage(), ex);
        }
        return outputStream;
    }
    
    /**
     * This method is to scan for signature tag in each page and apply the signature
     * at desired location.
     * @param imageData
     * @param originalByteArrayOutputStream
     */
    @SuppressWarnings("unchecked")
    protected ByteArrayOutputStream scanAndApplyAutographInEachPage(byte[] imageData, ByteArrayOutputStream originalByteArrayOutputStream) throws Exception {
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        byte[] pdfFileData = originalByteArrayOutputStream.toByteArray();
        PDDocument originalDocument = getPdfDocument(pdfFileData); //PDDocument.load(is);
        PDDocument signatureDocument = new PDDocument();
        List<PDPage> originalDocumentPages = originalDocument.getDocumentCatalog().getAllPages();
        for (PDPage page: originalDocumentPages) {
            List<String> signatureTags = new ArrayList<String>(getSignatureTagParameter());
            PersonSignatureLocationHelper printer = new PersonSignatureLocationHelper(signatureTags);
            PDStream contents = page.getContents();
            if( contents != null ) {
                printer.processStream( page, page.findResources(), page.getContents().getStream() );
            }
            PDPage signaturePage = new PDPage();
            if(printer.isSignatureTagExists()) {
                PDJpeg signatureImage = new PDJpeg(signatureDocument, getBufferedImage(imageData));
                PDPageContentStream stream = new PDPageContentStream( signatureDocument, signaturePage, true, true);
                for(PersonSignaturePrintHelper signatureHelper : printer.getPersonSignatureLocations()) {
                    float coordinateX = signatureHelper.getCoordinateX();
                    float coordinateY = signatureHelper.getCoordinateY() - signatureImage.getHeight() - ADDITIONAL_SPACE_BETWEEN_TAG_AND_IMAGE;
                    stream.drawImage(signatureImage, coordinateX, coordinateY);
                    stream.close();
                }
            }else {
                signaturePage = page;
            }
            signatureDocument.addPage(signaturePage);
        }
        
        Overlay overlay = new Overlay();
        overlay.overlay(signatureDocument, originalDocument);
        
        originalDocument.save(outputStream);
        originalDocument.close();
        signatureDocument.close();
        return outputStream;
    }
    
    private PDDocument getPdfDocument(byte[] pdfFileData) throws Exception{
        InputStream is = new ByteArrayInputStream(pdfFileData);
        PDDocument originalDocument = PDDocument.load(is);
        return originalDocument;
    }
    
    private ByteArrayOutputStream getOriginalPdfDocumentAsOutputsStream(byte[] pdfFileData) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream is = new ByteArrayInputStream(pdfFileData);
        PDDocument originalDocument = PDDocument.load(is);
        originalDocument.save(outputStream);
        originalDocument.close();
        return outputStream;
    }
    
    /**
     * This method is to get buffered image from image data
     * @param imageData
     * @return
     * @throws Exception
     */
    protected BufferedImage getBufferedImage(byte[] imageData) throws Exception {
        InputStream in = new ByteArrayInputStream(imageData);
        BufferedImage image = ImageIO.read(in);
        return image;
    }
    
    /**
     * This method is to get the default signature for module
     * @return
     */
    protected PersonSignature getDefaultSignature() {
        List<PersonSignatureModule> moduleSignatures = getAuthorizedDefaultSignatory();
        PersonSignature authorizedSignature = null;
        if(!moduleSignatures.isEmpty()) {
            authorizedSignature = moduleSignatures.get(0).getPersonSignature();
        }
        return authorizedSignature;
    }
    
    /**
     * This method is to get logged in person signature
     * Check whether user is authorized to sign in given module
     * @param personId
     * @return
     */
    protected PersonSignature getLoggedinPersonSignature(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        boolean isAuthorized = false;
        fieldValues.put(PERSON_SIGNATURE_PERSON_ID, personId);
        fieldValues.put(PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        PersonSignature personSignature = (PersonSignature) getBusinessObjectService().findByPrimaryKey(PersonSignature.class, fieldValues);
        if(ObjectUtils.isNotNull(personSignature)) {
            for(PersonSignatureModule personSignatureModule : personSignature.getPersonSignatureModules()) {
                if(personSignatureModule.getModuleCode().equalsIgnoreCase(getModuleCodeHook()) && personSignatureModule.isSignatureActive()) {
                    isAuthorized = true;
                    break;
                }
            }
        }
        return isAuthorized ? personSignature : null;
    }
    
    
    /**
     * This method is to get authorized default signatory for a given module.
     * get all active signatures.
     * @return
     */
    protected List<PersonSignatureModule> getAuthorizedDefaultSignatory() {    
        Map<String, Object> matchingKey = new HashMap<String, Object>();
        matchingKey.put(MODULE_CODE, getModuleCodeHook());
        matchingKey.put(PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        matchingKey.put(DEFAULT_SIGNATURE, Boolean.TRUE);
        return (List<PersonSignatureModule>)getBusinessObjectService().findMatching(PersonSignatureModule.class, matchingKey);
    }  
    
    protected String getSignatureTypeParameter() {
        return getParameterService().getParameterValueAsString(getModuleNameSpaceHook(), ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TYPE);
    }

    protected Collection<String> getSignatureTagParameter() {
        return getParameterService().getParameterValuesAsString(getModuleNameSpaceHook(), ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TAG);
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected abstract String getModuleCodeHook();
    protected abstract String getModuleNameSpaceHook();

}

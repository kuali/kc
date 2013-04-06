/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.printing.service.impl;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.Overlay;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.util.ImageParameters;
import org.kuali.kra.bo.PersonSignature;
import org.kuali.kra.dao.PersonSignatureDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.printing.PersonSignatureLocationHelper;
import org.kuali.kra.printing.PersonSignaturePrintHelper;
import org.kuali.kra.printing.service.PersonSignatureService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class PersonSignatureServiceImpl implements PersonSignatureService {
    
    private static final String CORRESPONDENCE_SIGNATURE_TYPE = "CORRESPONDENCE_SIGNATURE_TYPE";
    private static final String CORRESPONDENCE_SIGNATURE_TAG = "CORRESPONDENCE_SIGNATURE_TAG";
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private RoleService roleService;
    private PersonSignatureDao personSignatureDao;

    private static final float ADDITIONAL_SPACE_BETWEEN_TAG_AND_IMAGE = 10f;
    
    private static final Log LOG = LogFactory.getLog(PersonSignatureServiceImpl.class);
    
    private String leadUnitNumber; 
    private String administratorType;
    private String moduleNameSpace;
    
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

    /**
     * @see org.kuali.kra.printing.service.PersonSignatureService#applySignature(java.lang.String, java.lang.String, byte[])
     */
    public byte[] applySignature(String leadUnitNumber, String administratorType, String moduleNameSpace,
            byte[] pdfBytes) throws Exception {
        this.leadUnitNumber = leadUnitNumber;
        this.administratorType = administratorType;
        this.moduleNameSpace = moduleNameSpace;
        byte[] pdfFileData = pdfBytes;
        ByteArrayOutputStream byteArrayOutputStream = getOriginalPdfDocumentAsOutputsStream(pdfBytes); //getFlattenedPdfForm(pdfFileData);
        byteArrayOutputStream = identifyModeAndApplySignature(byteArrayOutputStream);
        if(ObjectUtils.isNotNull(byteArrayOutputStream)) {
            pdfFileData = byteArrayOutputStream.toByteArray();
        }
        return pdfFileData;
    }
    
    /**
     * This method is to remove interactive fields from the form.
     * @param pdfBytes
     * @return
     * @throws Exception
     */
    protected ByteArrayOutputStream getFlattenedPdfForm(byte[] pdfBytes) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream is = new ByteArrayInputStream(pdfBytes);
        PDDocument pdDoc = PDDocument.load(is);
        PDDocumentCatalog pdCatalog = pdDoc.getDocumentCatalog();
        PDAcroForm acroForm = pdCatalog.getAcroForm();
        COSDictionary acroFormDict = acroForm.getDictionary();
        COSArray fields = (COSArray) acroFormDict.getDictionaryObject("Fields");
        fields.clear();
        pdDoc.save(byteArrayOutputStream);
        return byteArrayOutputStream;
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
     * This method is to print default module administrator signature.
     * Original document is returned if signature is not available.
     * @param originalByteArrayOutputStream
     * @return
     * @throws Exception
     */
    protected ByteArrayOutputStream printDefaultSignature(ByteArrayOutputStream originalByteArrayOutputStream) throws Exception{
        ByteArrayOutputStream outputStream = originalByteArrayOutputStream;
        PersonSignature adminSignature = getAdminSignature();
        if(ObjectUtils.isNotNull(adminSignature)) {
            if(ObjectUtils.isNotNull(adminSignature.getAttachmentContent())) {
                outputStream = applyAutographInDocument(adminSignature, outputStream);
            }
        }
        return outputStream;
    }

    /**
     * This method is to print logged in user signature.
     * If logged in user signature is not available, get the administrator signature.
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
            userSignature = getAdminSignature();
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
                ex.printStackTrace();
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
     * This method is to get buffered image based on predefined parameters
     * @param params
     * @param imageData
     * @return
     */
    protected BufferedImage getBufferedImage(ImageParameters params, byte[] imageData) {
        byte[] transparentColors = new byte[]{(byte)0xFF,(byte)0xFF};
        byte[] colors=new byte[]{0, (byte)0xFF};
        IndexColorModel colorModel = new IndexColorModel( 1, 2, colors, colors, colors, transparentColors );
        BufferedImage image = new BufferedImage(
            params.getWidth(),
            params.getHeight(),
            BufferedImage.TYPE_BYTE_BINARY,
            colorModel );
        DataBufferByte buffer = new DataBufferByte(imageData, 1 );
        WritableRaster raster =
            Raster.createPackedRaster(
                buffer,
                params.getWidth(),
                params.getHeight(),
                params.getBitsPerComponent(),
                new Point(0,0) );
        image.setData( raster );
        return image;
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
     * This method is to get the default administrator signature
     * @return
     */
    protected PersonSignature getAdminSignature() {
        List<RoleMembership> moduleAdministrators = getAdministrators();
        Set<String> personIdsForSignature = new HashSet<String>();
        for(RoleMembership roleMembership : moduleAdministrators) {
            personIdsForSignature.add(roleMembership.getMemberId());
        }
        List<PersonSignature> personSignatures = getPersonSignatureDao().getPersonSignatureForPersonIds(personIdsForSignature);
        PersonSignature adminSignature = null;
        for(PersonSignature personSignature : personSignatures) {
            if(personSignature.isDefaultAdminSignature()) {
                adminSignature = personSignature;
                break;
            }
        }
        return adminSignature;
    }
    
    /**
     * This method is to get logged in person signature
     * @param personId
     * @return
     */
    protected PersonSignature getLoggedinPersonSignature(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(Constants.PERSON_SIGNATURE_PERSON_ID, personId);
        fieldValues.put(Constants.PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        PersonSignature personSignature = (PersonSignature) getBusinessObjectService().findByPrimaryKey(PersonSignature.class, fieldValues);
        return personSignature;
    }
    
    
    protected List<RoleMembership> getAdministrators() {    
        List<String> roleIds = new ArrayList<String>();
        String roleId = getRoleService().getRoleIdByNamespaceCodeAndName(RoleConstants.DEPARTMENT_ROLE_TYPE, getAdministratorType());
        roleIds.add(roleId);
        Map<String,String> attrSet =new HashMap<String,String>();
        attrSet.put(KcKimAttributes.UNIT_NUMBER, getLeadUnitNumber());
        return getRoleService().getRoleMembers(roleIds, attrSet);
    }  
    
    protected String getSignatureTypeParameter() {
        return getParameterService().getParameterValueAsString(getModuleNameSpace(), ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TYPE);
    }

    protected Collection<String> getSignatureTagParameter() {
        return getParameterService().getParameterValuesAsString(getModuleNameSpace(), ParameterConstants.DOCUMENT_COMPONENT, CORRESPONDENCE_SIGNATURE_TAG);
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public PersonSignatureDao getPersonSignatureDao() {
        return personSignatureDao;
    }

    public void setPersonSignatureDao(PersonSignatureDao personSignatureDao) {
        this.personSignatureDao = personSignatureDao;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    protected String getAdministratorType() {
        return administratorType;
    }

    public String getModuleNameSpace() {
        return moduleNameSpace;
    }

}

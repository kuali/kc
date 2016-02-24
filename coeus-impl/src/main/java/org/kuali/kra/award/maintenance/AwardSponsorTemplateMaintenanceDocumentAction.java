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
package org.kuali.kra.award.maintenance;

import org.apache.commons.lang3.StringUtils;
import org.apache.ojb.broker.metadata.ClassNotPersistenceCapableException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.KualiMaintenanceDocumentAction;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;


public class AwardSponsorTemplateMaintenanceDocumentAction extends KualiMaintenanceDocumentAction{

    public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        AwardTemplate awardTemplate = getAwardTemplateMaintenanceObject(mapping, form, request, response);
        AttachmentDataSource attachmentDataSource = getAwardPrntingService().printAwardReport(awardTemplate, AwardPrintType.AWARD_TEMPLATE, null);
        streamToResponse(attachmentDataSource, response);
        return null;
    }

    private AwardTemplate getAwardTemplateMaintenanceObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        KualiMaintenanceForm maintenanceForm = (KualiMaintenanceForm) form;
        MaintenanceDocument document = null;

        // create a new document object, if required (on NEW object, or other reasons)
        if (maintenanceForm.getDocument() == null) {
            if (StringUtils.isEmpty(maintenanceForm.getBusinessObjectClassName()) && StringUtils.isEmpty(maintenanceForm.getDocTypeName())) {
                throw new IllegalArgumentException("Document type name or bo class not given!");
            }

            String documentTypeName = maintenanceForm.getDocTypeName();
            // get document type if not passed
            if (StringUtils.isEmpty(documentTypeName)) {
                documentTypeName = maintenanceDocumentDictionaryService.getDocumentTypeName(Class.forName(maintenanceForm.getBusinessObjectClassName()));
                maintenanceForm.setDocTypeName(documentTypeName);
            }

            if (StringUtils.isEmpty(documentTypeName)) {
                throw new RuntimeException("documentTypeName is empty; does this Business Object have a maintenance document definition? " + maintenanceForm.getBusinessObjectClassName());
            }
            document = (MaintenanceDocument) getDocumentService().getNewDocument(maintenanceForm.getDocTypeName());
            maintenanceForm.setDocument(document);
        }
        
        Map requestParameters = buildKeyMapFromRequest(document.getNewMaintainableObject(), request);
        AwardTemplate awardTemplate = null;
        try {
            awardTemplate = (AwardTemplate) getLookupService().findObjectBySearch(Class.forName(maintenanceForm.getBusinessObjectClassName()), requestParameters);
        } catch ( ClassNotPersistenceCapableException ex ) {
            if ( !document.getOldMaintainableObject().isExternalBusinessObject() ) {
                throw new RuntimeException( "BO Class: " + maintenanceForm.getBusinessObjectClassName() + " is not persistable and is not externalizable - configuration error" );
            }
            // otherwise, let fall through
        }
        if (awardTemplate == null && !document.getOldMaintainableObject().isExternalBusinessObject()) {
            throw new RuntimeException("Cannot retrieve old record for maintenance document, incorrect parameters passed on maint url: " + requestParameters );
        } 
        return awardTemplate;
    }
    protected void streamToResponse(AttachmentDataSource attachmentDataSource,
            HttpServletResponse response) throws Exception {
        byte[] xbts = attachmentDataSource.getData();
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);

            WebUtils
                    .saveMimeOutputStreamAsFile(response, attachmentDataSource
                            .getType(), baos, attachmentDataSource
                            .getName());

        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (IOException ioEx) {
                // LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }

    private AwardPrintingService getAwardPrntingService() {
        return KcServiceLocator.getService(AwardPrintingService.class);
    }
}

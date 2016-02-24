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
package org.kuali.coeus.propdev.impl.attachment.institute;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

import static org.kuali.kra.infrastructure.KeyConstants.*;


public class ProposalDevelopmentInstituteAttachmentRule extends KcTransactionalDocumentRuleBase implements AddInstituteAttachmentRule, ReplaceInstituteAttachmentRule { 
    private static final String INSTITUTE = "Institute";
    private static final String NEW_INSTITUTE_ATTACHMENT = "newInstituteAttachment";
    private static final String NARRATIVE_TYPE_CODE = "code";
    private static final String NARRATIVE_FILE = ".narrativeFile";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentInstituteAttachmentRule.class);
    private ParameterService parameterService;
    private KcAttachmentService kcAttachmentService;
    private DictionaryValidationService dictionaryValidationService;
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    /**
     * This method is to validate :
     *   attachment type code, status code exist. 
     *   file name is required.
     *   If attachment type does not allow multiple entries, then ensure one entry only for that type.
     * @see org.kuali.coeus.propdev.impl.attachment.institute.AddInstituteAttachmentRule#processAddInstituteAttachmentBusinessRules(org.kuali.coeus.propdev.impl.attachment.institute.AddInstituteAttachmentEvent)
     */
    public boolean processAddInstituteAttachmentBusinessRules(AddInstituteAttachmentEvent addInstituteAttachmentEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)addInstituteAttachmentEvent.getDocument();
        Narrative narrative = addInstituteAttachmentEvent.getNarrative();
        boolean rulePassed = true;
        populateNarrativeType(narrative);
        String errorPath = NEW_INSTITUTE_ATTACHMENT;
        if(narrative.getNarrativeType()==null)
            rulePassed = false;
        
        GlobalVariables.getMessageMap().addToErrorPath(NEW_INSTITUTE_ATTACHMENT);
        DictionaryValidationService dictionaryValidationService = getKnsDictionaryValidationService();
        dictionaryValidationService.validateAttributeFormat(narrative.getClass().getName(), "moduleTitle", narrative.getModuleTitle(), "moduleTitle");

        if (GlobalVariables.getMessageMap().getPropertiesWithErrors().size() > 0) rulePassed = false;
        GlobalVariables.getMessageMap().removeFromErrorPath(NEW_INSTITUTE_ATTACHMENT);
        
        if(StringUtils.isBlank(narrative.getNarrativeTypeCode())){
            rulePassed = false;
            reportError(errorPath+".institutionalAttachmentTypeCode", ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }
        if(StringUtils.isBlank(narrative.getModuleStatusCode())){
            rulePassed = false;
            reportError(errorPath+".moduleStatusCode", ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
        }
        if (rulePassed) {
            populateNarrativeType(narrative);
            String[] param = {INSTITUTE, narrative.getNarrativeType().getDescription()};
            if (!narrative.getNarrativeType().isAllowMultiple()) {
                for (Narrative narr : document.getDevelopmentProposal().getInstituteAttachments()) {
                    if (narr!=null && StringUtils.equals(narr.getNarrativeTypeCode(),narrative.getNarrativeTypeCode())) {
                        LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
                        reportError(errorPath+".institutionalAttachmentTypeCode", ERROR_NARRATIVE_TYPE_DUPLICATE, param);
                        rulePassed = false;
                    }
                }
            } else if (StringUtils.isBlank(narrative.getModuleTitle())) {
                /* if type='other', then desc is required.  
                 * This is just following the example from narrative rule
                 * It looks like if allowmultiple, then it's 'other' ??
                 */
                reportError(errorPath, ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED, param);
                rulePassed = false;
            }
        }
        if (StringUtils.isBlank(narrative.getName())) {
            rulePassed = false;
            reportError(errorPath + NARRATIVE_FILE, KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME, "File Name");
        }
    
        rulePassed &= validFileNameCharacters(narrative, errorPath);
        rulePassed &= getKcFileService().validPDFFile(narrative, getErrorReporter(), errorPath);
        
        return rulePassed;
  }
    
    @Override
    public boolean processReplaceInstituteAttachmentBusinessRules(ReplaceInstituteAttachmentEvent event) {
        return validFileNameCharacters(event.getNarrative(), event.getErrorPathPrefix()) &&
               getKcFileService().validPDFFile(event.getNarrative(), getErrorReporter(), event.getErrorPathPrefix());
    }
    
    private boolean validFileNameCharacters(Narrative narrative, String errorPath) {
        boolean rulePassed = true;
        
        KcAttachmentService attachmentService = getKcFileService();
        // Checking attachment file name for invalid characters.
        String attachmentFileName = narrative.getName();
        String invalidCharacters = attachmentService.getInvalidCharacters(attachmentFileName);
        if (ObjectUtils.isNotNull(invalidCharacters)) {
            String parameter = getParameterService().
                getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);
            if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
                rulePassed &= false;
                reportError(errorPath + NARRATIVE_FILE, KeyConstants.INVALID_FILE_NAME, 
                        attachmentFileName, invalidCharacters);
            } else {
                rulePassed &= true;
                reportWarning(errorPath + NARRATIVE_FILE, KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
            }
        }
        
        return rulePassed;
    }

    /**
     * 
     * This method is get narrative type reference which will be used to retrieve allow multiple indicator for rule checking.
     * @param narrative
     */
    private void populateNarrativeType(Narrative narrative) {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(NARRATIVE_TYPE_CODE, narrative.getNarrativeTypeCode());
        BusinessObjectService service = getBusinessObjectService();
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrativeTypeMap);
        if (narrType != null)
            narrative.setNarrativeType(narrType);
        
    }

    /**
     * This method returns the kc attachment service
     * @return
     */
    protected KcAttachmentService getKcFileService() {
        if(this.kcAttachmentService == null) {
            this.kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        }
        return this.kcAttachmentService;
    }

    protected DictionaryValidationService getKnsDictionaryValidationService() {
        if (this.dictionaryValidationService == null) {
            this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return this.dictionaryValidationService;
    }
}

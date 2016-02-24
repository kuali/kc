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
package org.kuali.kra.irb;

import java.util.List;

import org.kuali.kra.irb.actions.submit.ProtocolExemptStudiesCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolExpeditedReviewCheckListItem;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.questionnaire.ProtocolModuleQuestionnaireBean;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolVersionServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.questionnaire.ProtocolModuleQuestionnaireBeanBase;


/**
 * Protocol Version Service Implementation.
 */
public class ProtocolVersionServiceImpl extends ProtocolVersionServiceImplBase implements ProtocolVersionService {

    @Override
    protected String getProtocolDocumentTypeHook() {
        return "ProtocolDocument";
    }

    @Override
    protected ProtocolDocumentBase createNewProtocolDocumentInstanceHook() {
        return new ProtocolDocument();
    }

    @Override
    protected ProtocolBase createProtocolNewVersionHook(ProtocolBase protocol) throws Exception {
        Protocol irbProtocol = (Protocol)protocol;
        irbProtocol = versioningService.createNewVersion(irbProtocol);
        return irbProtocol;
    }

    @Override
    protected String getProtocolSequenceIdHook() {
        return "SEQ_PROTOCOL_ID";
    }

    @Override
    protected Class<? extends ProtocolBase> getProtocolBOClassHook() {
        return Protocol.class;
    }

    @Override
    protected ProtocolModuleQuestionnaireBeanBase getNewInstanceProtocolModuleQuestionnaireBeanHook(ProtocolBase protocol) {
        return new ProtocolModuleQuestionnaireBean((Protocol) protocol);
    }

    @Override
    protected ProtocolBase getNewProtocolVersion(ProtocolDocumentBase protocolDocument) throws Exception {
    	Protocol newProtocol = (Protocol)super.getNewProtocolVersion(protocolDocument);
    	setExpeditedAndExemptCheckListReferences(newProtocol.getProtocolSubmissions(), newProtocol);
    	return newProtocol;
    }
    
    public void setExpeditedAndExemptCheckListReferences(List<ProtocolSubmissionBase> protocolSubmissions, ProtocolBase newProtocol) {
    	protocolSubmissions.forEach(protocolSubmissionBase -> {
    		ProtocolSubmission protocolSubmission = (ProtocolSubmission)protocolSubmissionBase;
    		protocolSubmission.getExpeditedReviewCheckList().forEach(expeditedCheckList -> {
    			expeditedCheckList.resetPersistenceState();
    			expeditedCheckList.setProtocolId(newProtocol.getProtocolId());
            });
    		protocolSubmission.getExemptStudiesCheckList().forEach(exemptCheckList -> {
    			exemptCheckList.resetPersistenceState();
    			exemptCheckList.setProtocolId(newProtocol.getProtocolId());
            });
        });
        
    }
    	  
}

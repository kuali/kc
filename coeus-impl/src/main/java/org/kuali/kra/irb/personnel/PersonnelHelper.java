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
package org.kuali.kra.irb.personnel;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.personnel.PersonnelHelperBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;

public class PersonnelHelper extends PersonnelHelperBase {
    

    private static final long serialVersionUID = -8864871204975643125L;

    public PersonnelHelper(ProtocolForm form) {
        super(form);
        setNewProtocolPerson(new ProtocolPerson());
    }    
    
    protected void initializeModifyProtocolPermission(ProtocolBase protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_PERSONNEL, (Protocol) protocol);
        modifyPersonnel = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }
    
    protected void initializeTrainingSection() {
        setPersonTrainingSectionRequired(Boolean.parseBoolean(getParameterValue(Constants.PARAMETER_PROTOCOL_PERSON_TRAINING_SECTION)));
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return ProtocolDocument.class;
    }

    @Override
    public ProtocolUnitBase createNewProtocolUnitInstanceHook() {
        return new ProtocolUnit();
    }

    @Override
    public ProtocolAttachmentPersonnelBase createNewProtocolAttachmentPersonnelInstanceHook() {
        return new ProtocolAttachmentPersonnel();
    }

}

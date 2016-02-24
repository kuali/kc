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
package org.kuali.kra.irb.actions.correspondence;

import org.kuali.coeus.common.framework.person.signature.PersonSignatureService;
import org.kuali.kra.irb.actions.print.IrbPersonSignatureService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationServiceImplBase;

/**
 * 
 * This class deals with making a protocol attachment from a template based on an action.
 */
public class ProtocolActionCorrespondenceGenerationServiceImpl extends ProtocolActionCorrespondenceGenerationServiceImplBase implements ProtocolActionCorrespondenceGenerationService {

    private IrbPersonSignatureService personSignatureService;
    
    @Override
    protected ProtocolCorrespondence getNewProtocolCorrespondenceHook() {
        return new ProtocolCorrespondence();
    }

    @Override
    protected PersonSignatureService getPersonSignatureServiceHook() {
        return getPersonSignatureService();
    }

    public IrbPersonSignatureService getPersonSignatureService() {
        return personSignatureService;
    }

    public void setPersonSignatureService(IrbPersonSignatureService personSignatureService) {
        this.personSignatureService = personSignatureService;
    }
}

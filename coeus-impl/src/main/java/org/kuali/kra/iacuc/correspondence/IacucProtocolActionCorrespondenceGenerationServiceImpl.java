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
package org.kuali.kra.iacuc.correspondence;

import org.kuali.coeus.common.framework.person.signature.PersonSignatureService;
import org.kuali.kra.iacuc.actions.print.IacucPersonSignatureService;
import org.kuali.kra.protocol.actions.correspondence.ProtocolActionCorrespondenceGenerationServiceImplBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;


/**
 * 
 * This class deals with making a protocol attachment from a template based on an action.
 */
public class IacucProtocolActionCorrespondenceGenerationServiceImpl 
    extends ProtocolActionCorrespondenceGenerationServiceImplBase implements IacucProtocolActionCorrespondenceGenerationService {

    private IacucPersonSignatureService personSignatureService;
    
    @Override
    protected ProtocolCorrespondence getNewProtocolCorrespondenceHook() {
        return new IacucProtocolCorrespondence();
    }

    @Override
    protected PersonSignatureService getPersonSignatureServiceHook() {
        return getPersonSignatureService();
    }

    public IacucPersonSignatureService getPersonSignatureService() {
        return personSignatureService;
    }

    public void setPersonSignatureService(IacucPersonSignatureService personSignatureService) {
        this.personSignatureService = personSignatureService;
    }
    
}

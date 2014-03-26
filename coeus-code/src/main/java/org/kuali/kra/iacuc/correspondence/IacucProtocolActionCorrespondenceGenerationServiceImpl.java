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

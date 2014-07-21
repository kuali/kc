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
package org.kuali.kra.irb.actions.correspondence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.withdraw.WithdrawCorrespondence;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
public class ProtocolActionCorrespondenceGenerationServiceTest extends KcIntegrationTestBase {
    
    ProtocolActionCorrespondenceGenerationService protocolActionCorrespondenceGenerationService;
    BusinessObjectService businessObjectService;
    Protocol protocol;

    @Before
    public void setUp() throws Exception {
        protocolActionCorrespondenceGenerationService = KcServiceLocator.getService(ProtocolActionCorrespondenceGenerationService.class);
        businessObjectService  = KcServiceLocator.getService(BusinessObjectService.class);
        protocol = ProtocolFactory.createProtocolDocument().getProtocol();
        createActionHistory();
    }

    private void createActionHistory() {
        ProtocolAction protocolAction = new ProtocolAction(protocol, null, ProtocolActionType.PROTOCOL_CREATED);
        protocolAction.setProtocolNumber(protocol.getProtocolNumber());
        protocolAction.setSequenceNumber(protocol.getSequenceNumber());
        protocolAction.setProtocolId(protocol.getProtocolId());
        protocolAction.setActionId(protocol.getNextValue("actionId"));
        protocolAction.setComments("Protocol created");
        protocol.getProtocolActions().add(protocolAction);
        KNSServiceLocator.getBusinessObjectService().save(protocol);
    }
    
    @After
    public void tearDown() throws Exception {
        protocolActionCorrespondenceGenerationService = null;
        protocol = null;
    }
    
    @Test
    public void testGenerateCorrespondenceDocumentAndAttach() throws WorkflowException{
        try{
            WithdrawCorrespondence correspondence = new WithdrawCorrespondence();
            Protocol prot = ProtocolFactory.createProtocolDocument().getProtocol();
            ProtocolAction pa = new ProtocolAction();
            ProtocolActionType type = getActionType();
            pa.setProtocolActionTypeCode(type.getProtocolActionTypeCode());
            pa.setProtocolActionType(type);
            prot.getProtocolActions().add(pa);
            prot.setProtocolNumber("123");
            pa.setProtocol(prot);
            pa.setActionId(123456);
            pa.setProtocolNumber(prot.getProtocolNumber());
            this.businessObjectService.save(prot);
            correspondence.setProtocol(prot);
            protocolActionCorrespondenceGenerationService.generateCorrespondenceDocumentAndAttach(correspondence);
            assertTrue(true);
        } catch (PrintingException e){
            assertTrue(false);
        }
          
    }
    
    private ProtocolActionType getActionType() {
        Map fieldValues = new HashMap();
        fieldValues.put("PROTOCOL_ACTION_TYPE_CODE", "100");
        return (ProtocolActionType)this.businessObjectService.findByPrimaryKey(ProtocolActionType.class, fieldValues);
    }
}

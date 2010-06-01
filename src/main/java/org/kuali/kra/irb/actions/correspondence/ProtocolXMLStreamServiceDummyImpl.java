/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
//import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.rice.kew.exception.WorkflowException;

/**
 * 
 * This class is only a dummy implementation of the ProtocolXMLStreamService so we can move forward with other implementations.
 */
public class ProtocolXMLStreamServiceDummyImpl implements ProtocolXMLStreamService {

    @Override
    public Printable getPrintableXMLStream(Protocol protocol, ProtocolCorrespondenceTemplate template) {
        Printable printable = new DummyPrint();
        return printable;
    }
    
    private class DummyPrint extends AbstractPrint {
        /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 1L;

        public ResearchDocumentBase getDocument(){
            return null;
            /*
            try {
                ResearchDocumentBase base = ProtocolFactory.createProtocolDocument();
                return base;
            } catch (WorkflowException e) {
                return null;
            }*/
        }
        
        protected byte[] getBytes(XmlObject xmlObject) {
            byte[] data = {'a', 'b', 'c','d'};
            return data;
        }
        
        public Map<String, byte[]> getAttachments() {
            Map<String, byte[]> map = new HashMap<String, byte[]>();
            byte[] bytes = {'a', 'b', 'c','d'};
            map.put("val1", bytes);
            return map;
        }
        
        public Map<String, Object> getReportParameters() {
            return new HashMap<String, Object>();
        }
        public Map<String, byte[]> renderXML() throws PrintingException {
            return getAttachments();
        }
    }

}

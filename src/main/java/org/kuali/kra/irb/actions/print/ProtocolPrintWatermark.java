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
package org.kuali.kra.irb.actions.print;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.kra.util.watermark.WatermarkBean;
import org.kuali.kra.util.watermark.WatermarkParser;
import org.kuali.kra.util.watermark.Watermarkable;

public class ProtocolPrintWatermark implements Watermarkable {
    
    private KraPersistableBusinessObjectBase persistableBusinessObject;
    private static final Log LOG = LogFactory.getLog(PrintingServiceImpl.class);
    private WatermarkBean watermarkBean;
    
    /**
     * This method for return appropriate watermark...
     * @return watermark bean
     * @see org.kuali.kra.util.watermark.Watermarkable#getWatermark()
     */
    public WatermarkBean getWatermark() {
        Protocol protocol = (Protocol)getPersistableBusinessObject();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        if(protocolStatusCode!=null){          
            
            WatermarkBean waterMarkBean = null;         
            try {
                waterMarkBean = find(protocolStatusCode);
            }
            catch (Exception e) {
                LOG.error("Exception Occuring in (ProtocolPrintWatermark) :",e);
               
            }
            return waterMarkBean;          
            
        }     
        else{
            return null;
        }
    }
    /**
     * 
     * This method for find the appropriate watermark...
     * @param status of the protocol
     * @return watermark bean
     * @throws Exception
     */
    public WatermarkBean find(String status)throws Exception {
        WatermarkParser watermarkParser = new WatermarkParser();
        return watermarkParser.find(status);
    }

    
    public WatermarkBean getWatermarkBean() {
        return watermarkBean;
    }
    public void setWatermarkBean(WatermarkBean watermarkBean) {
        this.watermarkBean = watermarkBean;
    }
    public KraPersistableBusinessObjectBase getPersistableBusinessObject() {
        return persistableBusinessObject;
    }
    public void setPersistableBusinessObject(KraPersistableBusinessObjectBase persistableBusinessObject) {
        this.persistableBusinessObject = persistableBusinessObject;
    }
    
    
   
    

}

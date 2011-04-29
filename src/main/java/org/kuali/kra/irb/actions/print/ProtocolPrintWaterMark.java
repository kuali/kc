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

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.printing.WaterMarkCriteria;
import org.kuali.kra.printing.WaterMarkType;
import org.kuali.kra.printing.WaterMarkable;

public class ProtocolPrintWaterMark implements WaterMarkable {
    private KraPersistableBusinessObjectBase persistableBusinessObject;
    public Object getWaterMark() {
        Protocol protocol = (Protocol)getPersistableBusinessObject();
        if(protocol.getProtocolStatusCode().equals("301")){
            return "IRB Approval Expired";
        }else{
            return "";
        }
    }

//    public WaterMarkCriteria getWaterMarkCriteria(KraPersistableBusinessObjectBase kraPersistableBusinessObject) {
//        return new ProtocolWaterMarkCriteria().getWaterMark(kraPersistableBusinessObject);
//    }

    public WaterMarkType getWaterMarkType() {
        return WaterMarkType.TEXT;
    }

    public KraPersistableBusinessObjectBase getPersistableBusinessObject() {
        return persistableBusinessObject;
    }
    public void setPersistableBusinessObject(KraPersistableBusinessObjectBase persistableBusinessObject) {
        this.persistableBusinessObject = persistableBusinessObject;
    }

}

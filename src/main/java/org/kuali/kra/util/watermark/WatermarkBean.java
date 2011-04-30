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
/*
 * WatermarkBean.java
 * For set the watermark text,font, status etc.
 */

package org.kuali.kra.util.watermark;


import java.util.ArrayList;
import java.util.List;

public class WatermarkBean {
    
    /**
     * status code for the watermark
     */
    private String status;    
 
    /**
     * watermark decoration collection
     */
    private List watermark;

    public List getWatermark() {
        return watermark;
    }

    public void setWatermark(CommonBean commonBean) {
        if(watermark == null){
            watermark = new ArrayList(3);
        }
        watermark.add(commonBean);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

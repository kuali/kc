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
 * WatermarkCache.java
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.kuali.kra.util.watermark;



import java.util.HashMap;
import java.util.Map;


public class WatermarkCache {
    
    private static WatermarkCache watermarkCache;
    private static Map mapCache;
    
    /** Creates a new instance of DecoratorCache */
    private WatermarkCache() {
    }
    
    public synchronized static WatermarkCache getInstance() {
        if(watermarkCache == null) {
            watermarkCache = new WatermarkCache();
        }
        return watermarkCache;
    }
    /**
     * 
     * This method for store the watermark to the cache....
     * @param status
     * @param watermarkBean
     */
    public synchronized void cacheDecoration(int status, WatermarkBean watermarkBean) {
        if(mapCache == null) {
            mapCache = new HashMap();
        }
        mapCache.put(new Integer(status), watermarkBean);
    }
    /**
     * 
     * This method for find the watermark from cache...
     * @param status
     * @return
     * @throws Exception
     */
    public synchronized WatermarkBean findDecoration(int status)throws Exception{
        if(mapCache == null) return null;
        WatermarkBean watermarkBean = (WatermarkBean)mapCache.get(new Integer(status));
        return watermarkBean;
    }
    
}


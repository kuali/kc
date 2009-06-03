/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.printing;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.transform.Source;

/**
 * 
 * This interface marks reports, notifications, BOs and Documents as printable in Kuali-Coeus. 
 * KC Docs & BOs that will be printed via KC printing services should implement this interface.
 */
public interface Printable extends Serializable {
    

    /**
     * 
     * This method provides a way to get the XSL Transform(s) for the KC generated XML. This XSLT will create a
     * transformed XML-FO stream that will be converted to PDF. Note that multiple transforms are possible on this data.
     */
    public ArrayList<Source> getXSLT();
    
    /**
     * 
     * This method will provide the either reflected or XML-Bean based XML for input to the 
     * Transform into XML-FO.
     */
    public InputStream renderXML();
}

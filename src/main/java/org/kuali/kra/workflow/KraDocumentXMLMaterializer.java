/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.workflow;

import java.util.List;

import org.kuali.core.workflow.KualiDocumentXmlMaterializer;
import org.kuali.kra.bo.RolePersons;

public class KraDocumentXMLMaterializer extends KualiDocumentXmlMaterializer{
    

    List<RolePersons> rolepersons;

    public List<RolePersons> getRolepersons() {
        return rolepersons;
    }

    public void setRolepersons(List<RolePersons> rolepersons) {
        this.rolepersons = rolepersons;
    }
    

}

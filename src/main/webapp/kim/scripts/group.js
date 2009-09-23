/*
 * Copyright 2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

function groupTypeChanged(groupMaintDocForm) {
    
    methodToCallElement=document.createElement("input");
    methodToCallElement.setAttribute("type","hidden");
    methodToCallElement.setAttribute("name","methodToCall");
    methodToCallElement.setAttribute("value","refresh");
    document.forms[0].appendChild(methodToCallElement);
    
    refreshCallerElement=document.createElement("input");
    refreshCallerElement.setAttribute("type","hidden");
    refreshCallerElement.setAttribute("name","refreshCaller");
    refreshCallerElement.setAttribute("value","groupTypeChanged");
    document.forms[0].appendChild(refreshCallerElement);
    
    /**
    if(document.forms[0].elements["document.documentHeader.documentDescription"].value=="") {
    	docDescriptionElement=document.createElement("input");
    	docDescriptionElement.setAttribute("type","hidden");
    	docDescriptionElement.setAttribute("name","document.documentHeader.documentDescription");
    	docDescriptionElement.setAttribute("value","CHANGE ME! AUTO-DESCRIPTION DUE TO DOCUMENT TYPE CHANGE.");
    	document.forms[0].appendChild(docDescriptionElement);
    }
    **/
	document.forms[0].submit();
}

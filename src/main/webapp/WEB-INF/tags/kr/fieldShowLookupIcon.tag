<%--
 Copyright 2007 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="isReadOnly" required="true"
              description="Is the view for this field readOnly?" %>
<%@ attribute name="field" required="true" type="org.kuali.rice.kns.web.ui.Field"
              description="The field for which to show the lookup icon." %>
<%@ attribute name="anchor" required="false"
              description="The anchor (i.e. tab index) of the tab in which these icons will be displayed (primarily for lookups to return to the original section)" %>
              
<c:if test="${isReadOnly ne true && field.fieldType ne field.KUALIUSER && field.fieldType ne field.HIDDEN}">
	
    <c:if test="${!(empty field.quickFinderClassNameImpl)}">
        
        <kul:lookup
        	boClassName="${field.quickFinderClassNameImpl}"
            fieldConversions="${field.fieldConversions}"
            lookupParameters="${field.lookupParameters}"
            fieldLabel="${field.fieldLabel}"
            referencesToRefresh="${field.referencesToRefresh}"
            anchor="${anchor}"
            searchIconOverride="${field.imageSrc}" 
            baseLookupUrl="${field.baseLookupUrl}" />
                
    </c:if>

</c:if>

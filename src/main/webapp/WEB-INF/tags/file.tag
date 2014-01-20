 <%--
 Copyright 2005-2014 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.osedu.org/licenses/ECL-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="property" required="true" %>

<%-- THIS TAG IS NOT A STANDARD PART OF RICE.  THIS IS A KC EXTENSION --%>

<%-- attachment file error handling logic start--%>
    <kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
    <%-- highlighting does not work in firefox but does in ie... --%>
    <c:set var="textStyle" value="${hasErrors == true ? 'background-color:#FFD5D5' : ''}"/>
<%-- attachment file error handling logic end--%>
                        
<html:file property="${property}" style="${textStyle}" />

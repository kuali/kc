<%--
 Copyright 2005-2007 The Kuali Foundation
 
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
<%@ attribute name="resourceKey" required="false"%>
<%@ attribute name="businessObjectClassName" required="false"%>
<%@ attribute name="attributeName" required="false"%>
<%@ attribute name="documentTypeName" required="false"%>
<%@ attribute name="pageName" required="false"%>
<%@ attribute name="altText" required="false"%>
<%@ attribute name="securityGroupName" required="false"%>
<%@ attribute name="parameterName" required="false"%>
<%@ attribute name="searchDocumentTypeName" required="false"%>
<%@ attribute name="lookupBusinessObjectClassName" required="false"%>

<%--
  this tag is formatted to prevent any spaces occuring between the <a>/</a> tags and the <img> tag representing the help icon.  Internet
  explorer sometimes renders an underline (i.e. a hyperlinked space character) if there are extra spaces, and the formatting of this tag prevents those
  spaces from occuring.  
--%>
<c:choose><
  c:when test="${! empty resourceKey }"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getResourceHelpText&amp;resourceKey=${resourceKey}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow" title="[Help]${altText}"></c:when
  ><
  c:when test="${(! empty businessObjectClassName) && (! empty attributeName) }"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getAttributeHelpText&amp;businessObjectClassName=${businessObjectClassName}&amp;attributeName=${attributeName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow"  title="[Help]${altText}"></c:when
  ><
  c:when test="${(! empty businessObjectClassName) && ( empty attributeName) }"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getBusinessObjectHelpText&amp;businessObjectClassName=${businessObjectClassName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow" title="[Help]${altText}"></c:when
  ><
  c:when test="${(! empty documentTypeName) && (! empty pageName) }"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getPageHelpText&amp;documentTypeName=${documentTypeName}&amp;pageName=${pageName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow"  title="[Help]${altText}"></c:when
  ><
  c:when test="${! empty documentTypeName }"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getDocumentHelpText&amp;documentTypeName=${documentTypeName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow"  title="[Help]${altText}"></c:when
  ><
  c:when test="${(! empty securityGroupName) && (! empty parameterName)}"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getStoredHelpUrl&amp;helpSecurityGroupName=${securityGroupName}&amp;helpParameterName=${parameterName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow"></c:when
><
  c:when test="${(!empty searchDocumentTypeName)}"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getLookupHelpText&amp;searchDocumentTypeName=${searchDocumentTypeName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow" title="[Help]${altText}"></c:when
><
  c:when test="${(!empty lookupBusinessObjectClassName)}"
    ><a href="${ConfigProperties.application.url}/kr/help.do?methodToCall=getLookupHelpText&amp;lookupBusinessObjectClassName=${lookupBusinessObjectClassName}" tabindex="${KualiForm.nextArbitrarilyHighIndex}" target="helpWindow" title="[Help]${altText}"></c:when
></c:choose
><img src="${ConfigProperties.kr.externalizable.images.url}my_cp_inf.gif" alt="[Help]${altText}" hspace=5 border=0  align="middle"></a>

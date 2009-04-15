<%--
 Copyright 2006-2009 The Kuali Foundation

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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="property" required="true" %>
<%@ attribute name="attributeEntry" required="true" type="java.util.Map" %>
<%@ attribute name="onblur" required="false" %>
<%@ attribute name="readOnly" required="false" %>
<%@ attribute name="datePicker" required="false" %>
<%@ attribute name="disabled" required="false" %>
<%@ attribute name="onchange" required="false" %>
<%@ attribute name="onclick" required="false" %>
<%@ attribute name="tabindexOverride" required="false" %>
<%@ attribute name="readOnlyBody" required="false"
              description="when readOnly, use the tag body instead of a written hidden field.
              This allows mixing in module-specific inquiries." %>
<%@ attribute name="extraReadOnlyProperty" required="false"
			  description="when readOnly, you can specify extra properties to display alongside of
			  the main property.  The readOnlyBody attribute takes precedence." %>
<%@ attribute name="readOnlyAlternateDisplay" required="false"
              description="when readOnly, you can specify a String value to display instead of
              the main property.  The readOnlyBody and extraReadOnlyProperty attributes take precedence." %>
<%@ attribute name="encryptValue" required="false"
			  description="when readOnly or hidden field, boolean to indicate whether the value should
			  be encrypted and display masked. Defaults to false." %>
<%@ attribute name="displayMask" required="false"
			  description="when a field is not to be displayed in clear text and encrypted as hidden, the
			  string to display." %>
<%@ attribute name="styleClass" required="false"
			  description="When a field has a css class applied to it, make sure that
			  we carry it through."%>

<c:set var="readOnlyAlternateDisplay" />

<c:choose>
<c:when test="${attributeEntry.control.select == true}">
<c:if test="${readOnly}">
	<c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/> 
	<c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>
  
	  <c:choose>
	  	<c:when test="${not empty businessObjectClass}">
	      <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
	  	</c:when>
	  	<c:otherwise>
	      <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}"/>
	  	</c:otherwise>
	  </c:choose>
  
  <jsp:useBean id="methodAndParms" type="java.lang.String"/>  
  
  <%    
  		java.lang.String selectedOptionDescription = "";
  		
  		javax.servlet.jsp.PageContext pageCtx = (javax.servlet.jsp.PageContext) jspContext;
  		org.apache.struts.taglib.TagUtils TagUtils = org.apache.struts.taglib.TagUtils.getInstance();
  		String propertyValue = (String) TagUtils.lookup(pageCtx, "org.apache.struts.taglib.html.BEAN", property, null);
  		java.util.List collection = (java.util.List) TagUtils.lookup(pageCtx, "org.apache.struts.taglib.html.BEAN", methodAndParms, null);
  		if(collection != null && collection.size() > 0) {
  			for(Object obj : collection) {
  				org.kuali.core.web.ui.KeyLabelPair pair = (org.kuali.core.web.ui.KeyLabelPair) obj;
  				if(pair.getKey().equals(propertyValue)) {
  					selectedOptionDescription = pair.getLabel(); 
  					pageCtx.setAttribute("readOnlyAlternateDisplay", selectedOptionDescription);
  					break;
  				}
  			}
  		}
  %>
 </c:if>
 </c:when>
 </c:choose>
 
 <kul:htmlControlAttribute property="${property}" readOnly="${readOnly}" readOnlyAlternateDisplay="${readOnlyAlternateDisplay}" attributeEntry="${attributeEntry}" /> 

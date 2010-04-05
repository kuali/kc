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
<%@ attribute name="property" required="true" description="The property being rendered." %>
<%@ attribute name="attributeEntry" required="true" type="java.util.Map" description="The Map of data dictionary attributes about the property to render a control for." %>
<%@ attribute name="onblur" required="false" description="If set, this will be used as the onblur method on the control." %>
<%@ attribute name="readOnly" required="false" description="Whether this control should be rendered as read only (ie, not a control but rather text) or not." %>
<%@ attribute name="datePicker" required="false" description="Whether this control should be rendered with a date picker." %>
<%@ attribute name="expandedTextArea" required="false" description="whether to render an expanded textarea control.  Only applicable for textareas. "%>
<%@ attribute name="disabled" required="false" description="Whether this control should be rendered as disabled or not." %>
<%@ attribute name="onchange" required="false" description="If set, this will be used as the onchange method on the control." %>
<%@ attribute name="onclick" required="false" description="If set, this will be used as the onclick method on the control." %>
<%@ attribute name="tabindexOverride" required="false" description="If set, this will be used as the text index on the control." %>
<%@ attribute name="readOnlyBody" required="false"
              description="when readOnly, use the tag body instead of a written hidden field.
              This allows mixing in module-specific inquiries." %>
<%@ attribute name="extraReadOnlyProperty" required="false"
			  description="when readOnly, you can specify extra properties to display alongside of
			  the main property.  The readOnlyBody attribute takes precedence." %>
<%@ attribute name="readOnlyAlternateDisplay" required="false"
              description="when readOnly, you can specify a String value to display instead of
              the main property.  The readOnlyBody and extraReadOnlyProperty attributes take precedence.
              THIS VALUE WILL BE DISPLAYED WITHOUT ANY XML FILTERING/ESCAPING, AND NEEDS TO BE PROPERLY ESCAPED TO PREVENT CROSS-SITE SCRIPTING VULNERABILITIES" %>
<%@ attribute name="displayMask" required="false"
              description="Specify whether to mask the given field using the displayMaskValue rather than showing the actual value." %>
<%@ attribute name="displayMaskValue" required="false"
			  description="when a field is not to be displayed in clear text and encrypted as hidden, the
			  string to display." %>
<%@ attribute name="styleClass" required="false"
			  description="When a field has a css class applied to it, make sure that
			  we carry it through."%>
<%@ attribute name="accessibilityHint" required="false"
        description="Use this to attach further information to the title attribute of a field
        if present"%>
<%@ attribute name="forceRequired" required="false" description="Whether this control should be rendered as required, no matter the information from the data dictionary about the required state of the attribute." %>
<%@ attribute name="kimTypeId" required="false" description="If the rendered attribute is a KIM attribute, the ID of the type of that KIM attribute." %>
<%-- Do not remove session check in this tag file since it is used by other type of files (not MD or TD) --%>
<c:set var="sessionDocument" value="${requestScope['sessionDoc']}" />
<c:if test="${empty readOnly}">
    <c:set var="readOnly" value="false"/>
</c:if>

<c:if test="${!empty attributeEntry.attributeSecurityMask && attributeEntry.attributeSecurityMask == true  }">
	<c:set var="className" value ="${attributeEntry.fullClassName}" />
	<c:set var="fieldName" value ="${attributeEntry.name}" />
	<c:set var="displayMask" value="${kfunc:canFullyUnmaskField(className, fieldName,KualiForm)? 'false' : 'true'}" />
	<c:set var="readOnly" value="${displayMask || readOnly}" />
	<c:if test="${displayMask}">
		<c:set var="displayMaskValue" value="${kfunc:getFullyMaskedValue(className, fieldName, KualiForm, property)}" />
	</c:if>
</c:if>


<c:if test="${!displayMask && !empty attributeEntry.attributeSecurityPartialMask && attributeEntry.attributeSecurityPartialMask == true  }">
	<c:set var="className" value ="${attributeEntry.fullClassName}" />
	<c:set var="fieldName" value ="${attributeEntry.name}" />
    <c:set var="displayMask" value="${kfunc:canPartiallyUnmaskField(className, fieldName,KualiForm)? 'false' : 'true'}" />
	<c:set var="readOnly" value="${displayMask || readOnly}"/>
	<c:set var="displayMaskValue" value="${kfunc:getPartiallyMaskedValue(className, fieldName, KualiForm, property)}" />
	<c:if test="${displayMask}">
		<c:set var="displayMaskValue" value="${kfunc:getFullyMaskedValue(className, fieldName, KualiForm, property)}" />
	</c:if>
</c:if>


<%-- Define variable that will hold the Title of the html control --%>
<c:set var="accessibleTitle" value="${attributeEntry.label}"/>
<c:if test="${(attributeEntry.required == true || forceRequired) && readOnly != true}">
<c:set var="accessibleTitle" value="${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle}"/>
  </c:if>
  <c:if test="${!(empty accessibilityHint)}">
<c:set var="accessibleTitle" value="${accessibleTitle} ${accessibilityHint}"/>
</c:if>

<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>

<c:set var="disableField" value="false" />
<c:if test="${disabled}">
  <c:set var="disableField" value="true" />
</c:if>

<c:if test="${empty styleClass}">
	<c:set var="styleClass" value=""/>
</c:if>
<c:choose>
  <c:when test="${!empty tabindexOverride}">
    <c:set var="tabindex" value="${tabindexOverride}"/>
  </c:when>
  <c:otherwise>
    <%-- Can't "not" assign a tab index, so if there is no override we set it to 0 per default --%>
    <c:set var="tabindex" value="0"/>
  </c:otherwise>
</c:choose>

<c:if test="${readOnly}">
   
   <c:choose>
     <c:when test="${readOnlyBody}">
         <jsp:doBody/>
     </c:when>

     <c:otherwise>
        <c:choose>
		<c:when test="${displayMask}" >
			${displayMaskValue}
		</c:when>
         <c:otherwise>
         <logic:empty name="KualiForm" property="${property}">
         &nbsp;
         </logic:empty>
         <c:if test="${!empty extraReadOnlyProperty}">
			<c:choose>
			<c:when test="${sessionDocument}">
				<bean:write name="KualiForm" property="${extraReadOnlyProperty}"/>
			</c:when>
			<c:otherwise>
				<html:hidden write="false" property="${property}" style="${textStyle}" />
           		<html:hidden write="true" property="${extraReadOnlyProperty}" style="${textStyle}" />
			</c:otherwise>
		  </c:choose>
         </c:if>
         <c:if test="${empty extraReadOnlyProperty}">
         <c:choose>
			<c:when test="${sessionDocument}">
			  <c:if test="${attributeEntry.control.select == true || attributeEntry.control.multiselect == true}">
			     <c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/>
				 <c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>
				   	     
				 <c:choose>
	               <c:when test="${not empty businessObjectClass and empty kimTypeId}">
	                 <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
	               </c:when>
	               <c:when test="${not empty kimTypeId}">
	                 <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.name}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${kimTypeId}"/>
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
			   	    java.util.List propertyValue = new java.util.ArrayList();
			   	    Object value = TagUtils.lookup(pageCtx, "org.apache.struts.taglib.html.BEAN", property, null);
			   	    if (value instanceof String) {
			   		  propertyValue.add(value);
			   	    } else if (value instanceof java.util.Collection) {
			   		  propertyValue.addAll((java.util.Collection)value);
			   	    }
			   	    java.util.List collection = (java.util.List) TagUtils.lookup(pageCtx, "org.apache.struts.taglib.html.BEAN", methodAndParms, null);
			   	
			   	    if(collection != null && collection.size() > 0) {
			   		  for(Object obj : collection) {
				   	    org.kuali.rice.core.util.KeyLabelPair pair = (org.kuali.rice.core.util.KeyLabelPair) obj;
				   	    for (Object val : propertyValue) {
					   	  if(pair.getKey() != null && pair.getKey().toString().equals(val)) {
					   	    if (!selectedOptionDescription.trim().equals("")) {
					   	      selectedOptionDescription += "<br />";
					   	    }
					   	    selectedOptionDescription += pair.getLabel();
					   	    break;
					   	  }
				   	    }
				   	  }
				   	  pageCtx.setAttribute("readOnlyAlternateDisplay", selectedOptionDescription);
			   	    } 
			   	 %>
 			 </c:if>
			 <c:if test="${empty readOnlyAlternateDisplay}">
		       <bean:write name="KualiForm" property="${property}"/>
             </c:if>
             ${readOnlyAlternateDisplay}
		    </c:when>
			<c:otherwise>
              <html:hidden write="${empty readOnlyAlternateDisplay ? 'true' : 'false'}" property="${property}" style="${textStyle}" />
              ${readOnlyAlternateDisplay}
            </c:otherwise>
		  </c:choose>
         </c:if>
     </c:otherwise>
   </c:choose>
     </c:otherwise>
   </c:choose>
</c:if>
<c:if test="${!readOnly}">
  <c:choose>
    <%-- text --%>
    <c:when test="${attributeEntry.control.text == true}">
           <html:text property="${property}" style="${textStyle}" title="${accessibleTitle}" tabindex="${tabindex}"
                           size="${attributeEntry.control.size}" maxlength="${attributeEntry.maxLength}"
                           onblur="${onblur}" onchange="${onchange}" styleId="${property}" disabled="${disableField}"
                           styleClass="${styleClass}"/>
    </c:when>

    <%-- textarea --%>
    <c:when test="${attributeEntry.control.textarea == true}">
            <html:textarea property="${property}" style="${textStyle}" title="${accessibleTitle}" tabindex="${tabindex}"
                           rows="${attributeEntry.control.rows}" cols="${attributeEntry.control.cols}"
                           styleId="${property}" disabled="${disableField}" styleClass="${styleClass}"
                           onkeyup="textLimit(this, ${attributeEntry.maxLength});" />

    </c:when>

    <%-- select --%>
    <c:when test="${attributeEntry.control.select == true}">
            <c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/>
            <c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>

            <html:select styleId="${property}" property="${property}" title="${accessibleTitle}" tabindex="${tabindex}" style="${textStyle}" disabled="${disableField}" onblur="${onblur}" onchange="${onchange}" styleClass="${styleClass}">
              <c:choose>
              	<c:when test="${not empty businessObjectClass and empty kimTypeId}">
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
              	</c:when>
              	<c:when test="${not empty kimTypeId}">
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.name}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${kimTypeId}"/>
              	</c:when>
              	<c:otherwise>
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}"/>
              	</c:otherwise>
           	  </c:choose>
              <html:optionsCollection property="${methodAndParms}" label="label" value="key"/>
            </html:select>
    </c:when>

    <%-- multiselect --%>
    <c:when test="${attributeEntry.control.multiselect == true}">
            <c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/>
            <c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>
			<html:select styleId="${property}" property="${property}" title="${accessibleTitle}" tabindex="${tabindex}" style="${textStyle}" size="${attributeEntry.control.size}" disabled="${disableField}" onblur="${onblur}" onchange="${onchange}" styleClass="${styleClass}" multiple="multiple" >
			  <c:choose>
              	<c:when test="${not empty businessObjectClass and empty kimTypeId}">
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
              	</c:when>
              	<c:when test="${not empty kimTypeId}">
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.name}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${kimTypeId}"/>
              	</c:when>
              	<c:otherwise>
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}"/>
              	</c:otherwise>
           	  </c:choose>
              <html:optionsCollection property="${methodAndParms}" label="label" value="key"/>
            </html:select>
	</c:when>
    <%-- radio --%>
    <c:when test="${attributeEntry.control.radio == true}">
        <c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/>
        <c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>

		<c:choose>
      		<c:when test="${not empty businessObjectClass and empty kimTypeId}">
            	<c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
      	  	</c:when>
      		<c:when test="${not empty kimTypeId}">
                <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.name}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}IGNORED${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${kimTypeId}"/>
      	  	</c:when>
      	  	<c:otherwise>
            	<c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}"/>
      	  	</c:otherwise>
   	    </c:choose>

       	<logic:iterate name="KualiForm" property="${methodAndParms}" id="KeyValue">
       		<c:set var="accessibleRadioTitle" value="${accessibleTitle} - ${KeyValue.label}"/>
            <html:radio property="${property}" style="${textStyle}" title="${accessibleRadioTitle}" tabindex="${tabindex}"
            	value="key" idName="KeyValue" disabled="${disableField}" onclick="${onchange}"
            	styleClass="${styleClass}"/>${KeyValue.label}
        </logic:iterate>
    </c:when>

    <%-- checkbox --%>
    <c:when test="${attributeEntry.control.checkbox == true}">
            <html:checkbox property="${property}" style="${textStyle}" title="${accessibleTitle}" tabindex="${tabindex}" disabled="${disableField}" onblur="${onblur}"
            	onchange="${onchange}" onclick="${onclick}" styleId="${property}"
            	styleClass="${styleClass}"/>
            <c:if test="${disableField == false}">
                <input type="hidden" name="checkboxToReset" value="${property}"/> </c:if>
    </c:when>

    <%-- hidden --%>
    <c:when test="${attributeEntry.control.hidden == true}">
		<c:if test="${!sessionDocument}">
            <html:hidden property="${property}" />
        </c:if>
    </c:when>

    <%-- currency --%>
    <c:when test="${attributeEntry.control.currency == true}">
          <html:text property="${property}" style="${textStyle}" title="${accessibleTitle}" tabindex="${tabindex}"
                           size="${attributeEntry.control.size}" maxlength="${attributeEntry.control.formattedMaxLength}"
                           onblur="${onblur}" onchange="${onchange}" styleId="${property}" disabled="${disableField}"
                           styleClass="${styleClass}" />
    </c:when>
  </c:choose>
  <%-- error icon --%>
  <c:if test="${hasErrors}">
	 		<kul:fieldShowErrorIcon />
  </c:if>
  <%-- datePicker icon --%>
  	<c:if test="${attributeEntry.control.text == true && (datePicker == true || (attributeEntry.control.datePicker == true && datePicker != false))}">
        <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="${property}_datepicker" style="cursor: pointer;"
             title="Date selector" alt="Date selector"
             onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
             <script type="text/javascript">
             	//<![CDATA[
             	Calendar.setup(
                          {
                            inputField : "${property}", // ID of the input field
                            ifFormat : "%m/%d/%Y", // the date format
                            button : "${property}_datepicker" // ID of the button
                          }
                );
                //]]>
              </script>
    </c:if>
</c:if>
<%-- always display even when readOnly --%>
<%-- expanded textarea icon --%>
<c:if test="${attributeEntry.control.textarea == true && (expandedTextArea == true || (attributeEntry.control.expandedTextArea == true && expandedTextArea != false))}">
	<%-- so that the JS can grab the value from the opener...got to be a better way to do this...--%>
	<c:if test="${readOnly}">
		<html:hidden property="${property}" write="false" styleId="${property}" />
	</c:if>
	<kul:expandedTextArea textAreaFieldName="${property}" action="${fn:substringBefore(fn:substring(requestScope['org.apache.struts.taglib.html.FORM'].action, 1, -1),'.do')}" textAreaLabel="${attributeEntry.label}" disabled="${disabled}" title="${attributeEntry.label}" readOnly="${readOnly}" maxLength="${attributeEntry.maxLength}"/>
</c:if>

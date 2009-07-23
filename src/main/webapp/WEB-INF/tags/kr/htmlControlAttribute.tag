<%--
 Copyright 2006-2009 The Kuali Foundation.

 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl1.php

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
<%@ attribute name="displayMaskValue" required="false"
			  description="when a field is not to be displayed in clear text and encrypted as hidden, the
			  string to display." %>
<%@ attribute name="styleClass" required="false"
			  description="When a field has a css class applied to it, make sure that
			  we carry it through."%>
<%@ attribute name="accessibilityHint" required="false"
        description="Use this to attach further information to the title attribute of a field
        if present"%>
<%@ attribute name="forceRequired" required="false" %>
<%@ attribute name="kimTypeName" required="false" %>
<!-- Do not remove session check in this tag file since it is used by other type of files (not MD or TD) -->
<c:set var="sessionDocument" value="${requestScope['sessionDoc']}" />
<c:if test="${!empty attributeEntry.attributeSecurityMask && attributeEntry.attributeSecurityMask == true  }">
	<c:set var="className" value ="${attributeEntry.fullClassName}" /> 
	<c:set var="fieldName" value ="${attributeEntry.name}" />
	<c:set var="readOnly" value="${kfunc:canFullyUnmaskField(className, fieldName)? 'false' : 'true'}" />
	<c:set var="displayMask" value="${kfunc:canFullyUnmaskField(className, fieldName)? 'false' : 'true'}" />
	<c:set var="displayMaskValue" value="${kfunc:getDisplayMaskValue(className, fieldName, KualiForm, property)}" />
</c:if>
 
<c:if test="${!empty attributeEntry.attributeSecurityPartialMask && attributeEntry.attributeSecurityPartialMask == true  }">
	<c:set var="className" value ="${attributeEntry.fullClassName}" /> 
	<c:set var="fieldName" value ="${attributeEntry.name}" />
	<c:set var="readOnly" value="${kfunc:canPartiallyUnmaskField(attributeEntry.fullClassName, attributeEntry.name) ? 'false' : 'true'}"/>
    <c:set var="displayMask" value="${kfunc:canFullyUnmaskField(className, fieldName)? 'false' : 'true'}" />
	<c:set var="displayMaskValue" value="${kfunc:getDisplayMaskValue(className, fieldName, KualiForm, property)}" />
</c:if>

<%-- Define variable that will hold the Title of the html control --%>
<c:set var="accessibleTitle" value="${attributeEntry.label}"/>
<c:if test="${(attributeEntry.required == true || forceRequired) && readOnly != true}">
<c:set var="accessibleTitle" value="${Constants.REQUIRED_FIELD_SYMBOL} ${accessibleTitle}"/>
  </c:if>
  <c:if test="${!(empty accessibilityHint)}">
<c:set var="accessibleTitle" value="${accessibleTitle} ${accessibilityHint}"/>
</c:if>


<%--These multi-select attributes are control specific and really should be defined in the DataDictionary files as control attributes--%>
<%@ attribute name="isMultiSelect" required="false" type="java.lang.Boolean"
			  description="When (attributeEntry.control.select == true), this attribute specifies whether to use a multi-select style control."%>
<%@ attribute name="multiSelectSize" required="false" type="java.lang.Integer"
			  description="When (attributeEntry.control.select == true && isMultiSelect == true), this attribute specifies the size of the control and is required for multi-select types."%>

<kul:checkErrors keyMatch="${property}" auditMatch="${property}"/>
<c:choose>
  <%-- border color not supported for select controls, so make background highlighted instead --%>
  <c:when test="${hasErrors==true && !attributeEntry.control.select}">
    <c:set var="textStyle" value="border-color: red"/>
  </c:when>
  <c:when test="${hasErrors==true && attributeEntry.control.select}">
    <c:set var="textStyle" value="background-color:#FFD5D5"/>
  </c:when>
  <c:when test="${readOnly && !hasErrors}">
    <c:set var="textStyle" value="border-color: black"/>
  </c:when>
</c:choose>

<c:set var="disableField" value="false" />
<c:if test="${disabled}">
  <c:set var="disableField" value="true" />
</c:if>

<c:if test="${empty encryptValue}">
    <c:set var="encryptValue" value="false"/>
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
         <c:when test="${encryptValue}">
            <%-- mask and encrypt --%>
            <%
              ((org.kuali.rice.kns.web.struts.form.KualiForm) request.getAttribute("KualiForm")).setFormatterType((String) property, org.kuali.rice.kns.web.format.EncryptionFormatter.class);
            %>
            <html:hidden property="encryptedProperties('${fn:replace(property,'.','_')}')" value="true"/>
            <html:hidden write="false" property="${property}" style="${textStyle}"/>
            ${displayMaskValue}
         </c:when>
		<c:when test="${displayMask}" >
			${displayMaskValue}
		</c:when>
         <c:otherwise>
         <logic:empty name="KualiForm" property="${property}">
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                           onkeyup="textLimit(${attributeEntry.maxLength});" /> 
            <script type="text/javascript"> 
              function textLimit(maxlen) { 
              var field=window.document.forms[0].elements['${property}']; 
                if (field.value.length > maxlen) { 
                  field.value = field.value.substr(0, maxlen); 
                } 
              }; 
            </script>
    </c:when> 

    <%-- select --%>
    <c:when test="${attributeEntry.control.select == true}">
            <c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/>
            <c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>

			<c:choose>
              	<c:when test="${not empty businessObjectClass and empty kimTypeName}">
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
              	</c:when>
              	<c:when test="${not empty businessObjectClass and not empty kimTypeName}">
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${kimTypeName}"/>
              	</c:when>
              	<c:otherwise>
                  <c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}"/>
              	</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${isMultiSelect}">
					<%-- makes no sense that the multiple attribute is not boolean --%>
					<html:select styleId="${property}" property="${property}" title="${accessibleTitle}" tabindex="${tabindex}" style="${textStyle}" disabled="${disableField}" onblur="${onblur}" onchange="${onchange}" styleClass="${styleClass}" multiple="${multiSelect}" size="${multiSelectSize}">
						<html:optionsCollection property="${methodAndParms}" label="label" value="key"/>
					</html:select>
					<%-- this field may be needed on non-multi-select in the future. --%>
					<c:if test="${disableField == false}">
						<input type="hidden" name="elementsToReset" value="${property}"/>
					</c:if>
				</c:when>
				<c:otherwise>
					<html:select styleId="${property}" property="${property}" title="${accessibleTitle}" tabindex="${tabindex}" style="${textStyle}" disabled="${disableField}" onblur="${onblur}" onchange="${onchange}" styleClass="${styleClass}">
						<html:optionsCollection property="${methodAndParms}" label="label" value="key"/>
					</html:select>
				</c:otherwise>
			</c:choose>
	    </c:when>

    <%-- radio --%>
    <c:when test="${attributeEntry.control.radio == true}">
        <c:set var="finderClass" value="${fn:replace(attributeEntry.control.valuesFinder,'.','|')}"/>
        <c:set var="businessObjectClass" value="${fn:replace(attributeEntry.control.businessObject,'.','|')}"/>

		<c:choose>
      		<c:when test="${not empty businessObjectClass and empty kimTypeName}">
            	<c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}"/>
      	  	</c:when>
      		<c:when test="${not empty businessObjectClass and not empty kimTypeName}">
            	<c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${businessObjectClass}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.keyAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.labelAttribute}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeBlankRow}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${attributeEntry.control.includeKeyInLabel}${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${kimTypeName}"/>
      	  	</c:when>
      	  	<c:otherwise>
            	<c:set var="methodAndParms" value="actionFormUtilMap.getOptionsMap${Constants.ACTION_FORM_UTIL_MAP_METHOD_PARM_DELIMITER}${finderClass}"/>
      	  	</c:otherwise>
   	    </c:choose>

       	<logic:iterate name="KualiForm" property="${methodAndParms}" id="KeyValue">
       		<c:set var="accessibleRadioTitle" value="${accessibleTitle} - ${KeyValue.label}"/>
            <html:radio property="${property}" style="${textStyle}" title="${accessibleRadioTitle}" tabindex="${tabindex}"
            	value="key" idName="KeyValue" disabled="${disableField}" onchange="${onchange}"
            	styleClass="${styleClass}"/>${KeyValue.label}
        </logic:iterate>
    </c:when>

    <%-- checkbox --%>
    <c:when test="${attributeEntry.control.checkbox == true}">
            <html:checkbox property="${property}" style="${textStyle}" title="${accessibleTitle}" tabindex="${tabindex}" disabled="${disableField}" onblur="${onblur}"
            	onchange="${onchange}" onclick="${onclick}" styleId="${property}"
            	styleClass="${styleClass}"/>
            <c:if test="${disableField == false}">
            	<input type="hidden" name="elementsToReset" value="${property}"/> </c:if>
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
  <!-- error icon -->
  <c:if test="${hasErrors}">
	 		<kul:fieldShowErrorIcon />
  </c:if>
  <!-- datePicker icon -->			
  	<c:if test="${attributeEntry.control.text == true && datePicker==true}">
        <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="${property}_datepicker" style="cursor: pointer;"
             title="Date selector" alt="Date selector"
             onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
             <script type="text/javascript">
             	Calendar.setup(
                          {
                            inputField : "${property}", // ID of the input field
                            ifFormat : "%m/%d/%Y", // the date format
                            button : "${property}_datepicker" // ID of the button
                          }
                  );
              </script>
    </c:if>
</c:if>

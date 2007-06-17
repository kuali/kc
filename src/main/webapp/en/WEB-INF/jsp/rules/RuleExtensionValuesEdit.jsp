<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters:
  rule
  ruleProperty
--%>
<c:set var="ruleProperty" value="${param['ruleProperty']}"/>

  <c:if test="${rule.hasExtensionValueErrors}">
    <tr>
	  <td width="20%" colspan="2" class="thnormal" align="right"><font color="red">Rule Value Errors:</font></td>
      <td width="30%" class="datacell">
        <div class="error-message">
          <html-el:messages property="${ruleProperty}.ruleExtensionValues" id="msg">
            <c:out value="${msg}"/>&nbsp;&nbsp;<br>
          </html-el:messages>
        </div>
      </td>
    </tr>
  </c:if>

  <c:set var="fieldIndex" value="${0}"/>
  <c:set var="conversionFields" value=""/>
  <c:set var="isRowLabel" value="" />	
	<c:set var="previousRow" value="NoPrevious" />

  <c:forEach items="${rule.rows}" var="row">

	<c:choose>
		<c:when test="${row.rowsGroupLabel != null && ! empty row.rowsGroupLabel && row.rowsGroupLabel != previousRow}">
			<c:set var="isRowLabel" value="true" />
   		<c:set var="previousRow" value="${row.rowsGroupLabel}" />
		</c:when>
		<c:otherwise>
			<c:set var="isRowLabel" value="false" />	
		</c:otherwise>
	</c:choose>
	  
    <c:set var="drawFirstCell" value="true" />
	<c:forEach items="${row.fields}" var="field">
   	  <c:set var="fieldValue" value="${ruleProperty}.field[${fieldIndex}].value"/>
	  <c:if test="${field.hasLookupable && !empty field.defaultLookupableName}">
        <c:if test="${!empty conversionFields}">
		  <c:set var="conversionFields" value="${conversionFields},"/>
		</c:if>
		<c:set var="conversionFields" value="${conversionFields}${field.defaultLookupableName}:${fieldValue}"/>
	  </c:if>
	  <html-el:hidden property="${ruleProperty}.field[${fieldIndex}].key" value="${field.propertyName}"/>
 	  <html-el:hidden property="${ruleProperty}.field[${fieldIndex}].id" />
	  <c:choose>
	    <c:when test="${field.fieldType==field.HIDDEN}" >
		  <html-el:hidden property="${ruleProperty}.field[${fieldIndex}].value"/>
		</c:when>
	  <c:otherwise>
	  <c:if test="${drawFirstCell==true}">
		<tr>
			<c:if test="${isRowLabel == true}">
				 <td height="40" class="thnormal" width="15%" align="right" rowspan="<c:out value="${row.numberOfGroupRows}" />"><c:out value="${row.rowsGroupLabel}" /></td>
			</c:if>
		  <c:if test="${IsLookupDisplay != null && IsLookupDisplay == true}">
	        <c:choose>
	          <c:when test="${row.rowsGroupLabel == previousRow}">
			        <td height="40" class="thnormal" width="5%" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
	          </c:when>
						<c:otherwise>
						  <td height="40" width="20%" class="thnormal" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>																			          
						</c:otherwise>
	        </c:choose>
		  </c:if>
		  <c:if test="${IsLookupDisplay == null}">
	        <c:choose>
	          <c:when test="${row.rowsGroupLabel == previousRow}">
							<td class="thnormal" align="right" width="5%" >&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
	          </c:when>
						<c:otherwise>
							<td class="thnormal" width="20%" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
						</c:otherwise>
	        </c:choose>
		  </c:if>
          <td width="30%" class="datacell">&nbsp;<c:set var="drawFirstCell" value="false" />
		</c:if>
		<c:choose>
  		  <c:when test="${FieldsReadOnly == true}">
			<c:out value="${rule.fields[fieldIndex].value}" />
		  </c:when>
		  <c:when test="${field.fieldType==field.TEXT}" >
			<html-el:text property="${ruleProperty}.field[${fieldIndex}].value"/>
		  </c:when>
		  <c:when test="${field.fieldType==field.DROPDOWN}" >
			<html-el:select property="${ruleProperty}.field[${fieldIndex}].value" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
 			  <c:set var="fieldValidValues" value="${field.fieldValidValues}" />
			  <html-el:options collection="fieldValidValues" property="key" labelProperty="label" />
			</html-el:select>
		  </c:when>
		  <c:when test="${field.fieldType==field.DROPDOWN_REFRESH}" >
  			<html-el:select property="${ruleProperty}.field[${fieldIndex}].value" onchange="document.forms[0].methodToCall.value='noOp';document.forms[0].submit();" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
  			  <c:set var="fieldValidValues" value="${field.fieldValidValues}" />
  			  <html-el:options collection="fieldValidValues" property="key" labelProperty="label" />
			</html-el:select>
		  </c:when>										
		  <c:when test="${field.fieldType==field.RADIO}" >
			<c:forEach items="${field.fieldValidValues}" var="radio">
			  <html-el:radio property="${ruleProperty}.field[${fieldIndex}].value" value="${radio.key}"/><c:out value="${radio.label}" />
			</c:forEach>
		  </c:when>
		  <c:when test="${field.fieldType==field.QUICKFINDER}" >
			<c:if test="${field.propertyValue!=null && field.propertyValue!=''}">
			  <c:out value="${field.propertyValue}" />
			</c:if>
			<a href="javascript:lookup('<c:out value="${field.quickFinderClassNameImpl}"/>', '<c:out value="${conversionFields}"/>', '<c:out value="${actionName}" />')"><img src="images/searchicon.gif" alt="search" align="absmiddle"></a>
			<c:set var="conversionFields" value=""/>
		  </c:when>
		</c:choose>
		<c:choose>
		  <c:when test="${field.fieldHelpUrl==''}" >&nbsp;</c:when>
 		  <c:otherwise>
			<a href='<c:out value="${field.fieldHelpUrl}" />' target="helpWindow"><img src="images/my_cp_inf.gif" alt="more info" hspace=5 border=0 align=absmiddle></a>
		  </c:otherwise>
		</c:choose>
	  </c:otherwise>
	</c:choose>
    <span class="error-message"><html-el:errors property="${ruleProperty}.field[${fieldIndex}].value"/></span>
  	<c:set var="fieldIndex" value="${fieldIndex+1}"/>
  </c:forEach>
</td>
</tr>
</c:forEach>

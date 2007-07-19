<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters:
  extensions - should be an object of type org.kuali.workflow.attribute.web.WebExtensions
  extensionsProperty - the property name of extensions object
  actionName - the name of the struts action to execute for lookups
--%>
<c:set var="extensionsProperty" value="${param['extensionsProperty']}"/>

<c:set var="isRowLabel" value="" />
<c:set var="previousRow" value="NoPrevious" />
<c:set var="conversionFields" value=""/>

<c:forEach items="${extensions.rows}" var="extensionsRows">
<c:forEach items="${extensionsRows}" var="row">
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
				<c:forEach items="${row.fields}" var="field" >
				    <c:set var="fieldPropertyName" value="${extensionsProperty}.data(${field.propertyName})"/>
	                <c:if test="${field.hasLookupable && !empty field.defaultLookupableName}">
                      <c:if test="${!empty conversionFields}">
		                <c:set var="conversionFields" value="${conversionFields},"/>
		              </c:if>
		              <c:set var="conversionFields" value="${conversionFields}${field.defaultLookupableName}:${fieldPropertyName}"/>
	                </c:if>
					<c:choose>
							<c:when test="${field.fieldType==field.HIDDEN}" >
							    <html-el:hidden property="${fieldPropertyName}"/>
							</c:when>
							<c:otherwise>
								<c:if test="${drawFirstCell==true}">
										<tr>
										<c:if test="${isRowLabel == true}">
											 <td height="40" class="thnormal" align="right" rowspan="<c:out value="${row.numberOfGroupRows}" />"><c:out value="${row.rowsGroupLabel}" /></td>
										</c:if>
										<c:if test="${IsLookupDisplay != null && IsLookupDisplay == true}">
									        <c:choose>
									          <c:when test="${row.rowsGroupLabel == previousRow}">
											        <td height="40" class="thnormal" width="5%" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
									          </c:when>
														<c:otherwise>
														  <td height="40" class="thnormal" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
														</c:otherwise>
									        </c:choose>
										</c:if>
										<c:if test="${IsLookupDisplay == null}">
									        <c:choose>
									          <c:when test="${row.rowsGroupLabel == previousRow}">
															<td class="thnormal" align="right" width="5%" >&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
									          </c:when>
														<c:otherwise>
															<td class="thnormal" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
														</c:otherwise>
									        </c:choose>
										</c:if>
									    <td class="datacell"><c:set var="drawFirstCell" value="false" />
								</c:if>
								<c:choose>
										<c:when test="${FieldsReadOnly == true}">
											<c:out value="${field.propertyValue}" />
										</c:when>
										<c:when test="${field.fieldType==field.TEXT}" >
												<html-el:text property="${fieldPropertyName}" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);"/>
										</c:when>
										<c:when test="${field.fieldType==field.DROPDOWN}" >
												<html-el:select property="${fieldPropertyName}" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
												    <c:set var="fieldValidValues" value="${field.fieldValidValues}" />
									   			    <html-el:options collection="fieldValidValues" property="key" labelProperty="label" />
												</html-el:select>
										</c:when>
										<c:when test="${field.fieldType==field.DROPDOWN_REFRESH}" >
												<html-el:select property="${fieldPropertyName}" onchange="document.forms[0].methodToCall.value='noOp';document.forms[0].submit();" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
  			  										<c:set var="fieldValidValues" value="${field.fieldValidValues}" />
  			  										<html-el:options collection="fieldValidValues" property="key" labelProperty="label" />
												</html-el:select>
										</c:when>
										<c:when test="${field.fieldType==field.RADIO}" >
										  <c:forEach items="${field.fieldValidValues}" var="radio">
			  								<html-el:radio property="${fieldPropertyName}" value="${radio.key}"/><c:out value="${radio.label}" />
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
										<c:when test="${field.fieldHelpUrl==''}" >
												&nbsp;
										</c:when>
										<c:otherwise>
								             <a href='<c:out value="${field.fieldHelpUrl}" />' target="helpWindow"><img src="images/my_cp_inf.gif" alt="more info" hspace=5 border=0 align=absmiddle></a>
										</c:otherwise>
								</c:choose>
						</c:otherwise>
					</c:choose>
			</c:forEach>
			<span class="error-message"><html-el:errors property="${fieldPropertyName}"/></span>
		</td>
     </tr>
</c:forEach>
</c:forEach>

<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:forEach items="${FieldRows}" var="row">
				<c:set var="drawFirstCell" value="true" />
				<c:forEach items="${row.fields}" var="field" >
					<c:choose>
							<c:when test="${field.fieldType==field.HIDDEN}" >
									<input type="hidden" name='<c:out value="${field.propertyName}"/>' value='<c:out value="${field.propertyValue}"/>'/>
							</c:when>
							<c:otherwise>
								<c:if test="${drawFirstCell==true}">
										<tr>
										<c:if test="${IsLookupDisplay != null && IsLookupDisplay == true}">
									        <td height="40" class="thnormal" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>										
										</c:if>
										<c:if test="${IsLookupDisplay == null}">
											<td class="thnormal" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
										</c:if>
									    <td class="datacell">&nbsp;<c:set var="drawFirstCell" value="false" />
								</c:if>
								<c:choose>
										<c:when test="${FieldsReadOnly == true}">
											<c:out value="${field.propertyValue}" />
										</c:when>
										<c:when test="${field.fieldType==field.TEXT}" >
												<input type="text" name='<c:out value="${field.propertyName}"/>' value='<c:out value="${field.propertyValue}" />' style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);"/>
										</c:when>
										<c:when test="${field.fieldType==field.DROPDOWN}" >
												<select name='<c:out value="${field.propertyName}"/>' style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
													<c:forEach items="${field.fieldValidValues}" var="select">
													  <c:choose>
															<c:when test="${field.propertyValue==select.key}" >
																<option value='<c:out value="${select.key}"/>' selected="selected"><c:out value="${select.label}" /></option>
															</c:when>
															<c:otherwise>
																<option value='<c:out value="${select.key}" />'><c:out value="${select.label}" /></option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
										</c:when>
										<c:when test="${field.fieldType==field.DROPDOWN_REFRESH}" >
												<select name='<c:out value="${field.propertyName}"/>' onchange="document.forms[0].submit();" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
													<c:forEach items="${field.fieldValidValues}" var="select">
													  <c:choose>
															<c:when test="${field.propertyValue==select.key}" >
																<option value='<c:out value="${select.key}"/>' selected="selected"><c:out value="${select.label}" /></option>
															</c:when>
															<c:otherwise>
																<option value='<c:out value="${select.key}" />'><c:out value="${select.label}" /></option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select>
										</c:when>										
										<c:when test="${field.fieldType==field.RADIO}" >
												<c:forEach items="${field.fieldValidValues}" var="radio">
													<c:choose>
														<c:when test="${field.propertyValue==radio.key}" >
															<input type="radio" checked="checked" name='<c:out value="${field.propertyName}" />' value='<c:out value="${radio.key}"/>'/><c:out value="${radio.label}" />
														</c:when>
														<c:otherwise>
															<input type="radio" name='<c:out value="${field.propertyName}" />' value='<c:out value="${radio.key}"/>'/><c:out value="${radio.label}" />
														</c:otherwise>												
													</c:choose>
												</c:forEach>
										</c:when>
										<c:when test="${field.fieldType==field.QUICKFINDER}" >
								  	        <c:if test="${field.propertyValue!=null && field.propertyValue!=''}">
												<c:out value="${field.propertyValue}" />
											</c:if>
											<c:if test="${FormName != null}" >
									            <a href="javascript:quick_finder('<c:out value="${field.quickFinderClassNameImpl}" />','<c:out value="${FormName}" />', '<c:out value="${ActionName}" />')"><img src="images/searchicon.gif" alt="search" align="absmiddle"></a>
									        </c:if>
									      	<c:if test="${FormName == null}">  
											     <a href="javascript:lookup('<c:out value="${field.quickFinderClassNameImpl}"/>', '<c:out value="${ActionName}" />')"><img src="images/searchicon.gif" alt="search" align="absmiddle"></a>
											</c:if>
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
		</td>
     </tr>
</c:forEach>
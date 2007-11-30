<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

  <c:choose>
      <c:when test="${(DocumentSearchForm.isAdvancedSearch == 'YES') and (not priorToSearchAttributes)}">
          <c:set var="fieldContainerList" value="${DocumentSearchForm.criteria.advancedFieldsPostSearchAttributes}" />
      </c:when>
      <c:when test="${(DocumentSearchForm.isAdvancedSearch == 'YES') and (priorToSearchAttributes)}">
          <c:set var="fieldContainerList" value="${DocumentSearchForm.criteria.advancedFieldsPreSearchAttributes}" />
      </c:when>
      <c:when test="${(DocumentSearchForm.isAdvancedSearch != 'YES') and (not priorToSearchAttributes)}">
          <c:set var="fieldContainerList" value="${DocumentSearchForm.criteria.basicFieldsPostSearchAttributes}" />
      </c:when>
      <c:otherwise>
          <c:set var="fieldContainerList" value="${DocumentSearchForm.criteria.basicFieldsPreSearchAttributes}" />
      </c:otherwise>
  </c:choose>

  <c:forEach items="${fieldContainerList}" var="fieldContainer">

        <tr>
          <th align="right" valign="center" class="thnormal" colspan="2"<c:if test="${not empty fieldContainer.labelFieldWidthValue}"> width="${fieldContainer.labelFieldWidthValue}"</c:if><c:if test="${not empty fieldContainer.labelFieldHeightValue}"> height="${fieldContainer.labelFieldHeightValue}"</c:if>>
            <div align="right">
              <c:choose>
                <c:when test="${not empty fieldContainer.labelMessageKey}">
                  <bean-el:message key="${fieldContainer.labelMessageKey}"/>:
                </c:when>
                <c:otherwise>&nbsp;</c:otherwise>
              </c:choose>
            </div>
          </th>
          <td class="datacell" valign="top"<c:if test="${not empty fieldContainer.dataFieldWidthValue}"> width="${fieldContainer.dataFieldWidthValue}"</c:if><c:if test="${not empty fieldContainer.dataFieldHeightValue}"> height="${fieldContainer.dataFieldHeightValue}"</c:if>>
            <table border="0" cellspacing="0" cellpadding="1">
              <c:set var="fieldListSize" value="${fn:length(fieldContainer.fields)}"/>
              <c:forEach items="${fieldContainer.fields}" var="field">
                <c:choose>
                  <c:when test="${field.hidden}"> 
                    <html-el:hidden name="DocumentSearchForm" property="${field.property}"/>
                  </c:when>
                  <c:otherwise>
                    <tr>
                      <c:if test="${not empty field.labelMessageKey}">
                        <td align="right" nowrap="nowrap"><bean-el:message key="${field.labelMessageKey}"/>:</td>
                      </c:if>
                      <td nowrap="nowrap">
                        <c:choose>
                          <c:when test="${not empty field.displayOnlyPropertyName}">
	    		            <%
			                  Object form =  (Object)request.getAttribute("DocumentSearchForm");
			                  edu.iu.uis.eden.docsearch.StandardSearchCriteriaField field = (edu.iu.uis.eden.docsearch.StandardSearchCriteriaField)pageContext.getAttribute("field");
			                  Object value = org.apache.commons.beanutils.BeanUtils.getProperty(form,field.getDisplayOnlyPropertyName());
			                  pageContext.setAttribute("displayValue",value != null ? value : "");
			                %>
    				        <c:out escapeXml="false" value="${displayValue}"  />&nbsp;
                            <html-el:hidden name="DocumentSearchForm" property="${field.property}"/>
                          </c:when>
                          <c:when test="${field.fieldType==field.TEXT}" >
                            <html-el:text name="DocumentSearchForm" property="${field.property}" size="16" maxlength="30"/>
                          </c:when>
                          <c:otherwise>
                            &nbsp;
                          </c:otherwise>
                        </c:choose>
                        <c:if test="${not empty field.lookupableImplServiceName}">
                          <c:set var="lookupTypeText" value=""/>
                          <c:if test="${field.lookupTypeRequired}">
                            <c:set var="lookupTypeText" value=" document.forms[0].elements['lookupType'].value = '${field.property}';"/>
                          </c:if>
                          <html-el:image property="methodToCall.performLookup" src="images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = '${field.lookupableImplServiceName}';${lookupTypeText}"/>
                        </c:if>
                        <c:if test="${not empty field.datePickerKey}">
                          <a href="javascript:showCal('${field.datePickerKey}');"><img src="images/cal.gif" width="16" height="16" align="absmiddle" alt="Click Here to pick a date"></a>
                        </c:if>
 		    		  </td>
 			    	  <c:if test="${not empty field.helpMessageKeyArgument}">
                        <td width="40" rowspan="${fieldListSize}" align="right" valign="middle" nowrap="nowrap">
                          <bean-el:message key="general.help.generic" arg0="${field.helpMessageKeyArgument}"/>
                        </td>
 	    			  </c:if>
                    </tr>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </table>
          </td>
        </tr>

  </c:forEach>
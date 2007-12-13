<%@ page import="edu.iu.uis.eden.docsearch.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:forEach items="${maxRowsList}" varStatus="rowStatus">
  <c:set var="labelFieldColspan" value="2"/>
  <c:set var="dataFieldColspan" value="${totalColumnCount - (fn:length(criteriaColumns) * 2)}"/>
    <tr>
      <c:set var="rowIndex" value="${rowStatus.index}"/>
      <%
      Integer index = (Integer) pageContext.getAttribute("rowIndex");
      List<List<StandardDocSearchCriteriaFieldContainer>> columns = (List<List<StandardDocSearchCriteriaFieldContainer>>) request.getAttribute("criteriaColumns");
      List<StandardDocSearchCriteriaFieldContainer> fieldContainers = new ArrayList<StandardDocSearchCriteriaFieldContainer>();
      for (Iterator iter = columns.iterator(); iter.hasNext();) {
          List<StandardDocSearchCriteriaFieldContainer> containerList = (List<StandardDocSearchCriteriaFieldContainer>) iter.next();
          if (containerList.size() >= (index.intValue() + 1)) {
              fieldContainers.add(containerList.get(index.intValue()));
          } else {
              fieldContainers.add(new StandardDocSearchCriteriaFieldContainer());
          }
      }
      pageContext.setAttribute("fieldContainerList",fieldContainers);
      %>
      <c:forEach items="${fieldContainerList}" var="fieldContainer">
        <c:choose>
          <c:when test="${(not DocumentSearchForm.showSearchCriteria) or (fieldContainer.hidden)}">
            <c:forEach items="${fieldContainer.fields}" var="field">
              <html-el:hidden name="DocumentSearchForm" property="${field.property}"/>
            </c:forEach>
          </c:when>
          <c:otherwise>
          <td align="right" nowrap="nowrap" valign="middle" class="thnormal" colspan="${labelFieldColspan}"<c:if test="${not empty fieldContainer.labelFieldWidthValue}"> width="${fieldContainer.labelFieldWidthValue}"</c:if><c:if test="${not empty fieldContainer.labelFieldHeightValue}"> height="${fieldContainer.labelFieldHeightValue}"</c:if>>
            <div align="right">
              <c:choose>
                <c:when test="${not empty fieldContainer.labelMessageKey}">
                  <bean-el:message key="${fieldContainer.labelMessageKey}"/>:
                </c:when>
                <c:otherwise>&nbsp;</c:otherwise>
              </c:choose>
            </div>
          </td>
          <c:set var="labelFieldColspan" value="1"/>
          <td class="datacell" valign="middle" colspan="${dataFieldColspan}"<c:if test="${not empty fieldContainer.dataFieldWidthValue}"> width="${fieldContainer.dataFieldWidthValue}"</c:if><c:if test="${not empty fieldContainer.dataFieldHeightValue}"> height="${fieldContainer.dataFieldHeightValue}"</c:if>>
            <c:if test="${empty fieldContainer.fields}">&nbsp;</c:if>
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
			                  StandardSearchCriteriaField field = (StandardSearchCriteriaField)pageContext.getAttribute("field");
			                  Object value = org.apache.commons.beanutils.BeanUtils.getProperty(form,field.getDisplayOnlyPropertyName());
			                  pageContext.setAttribute("displayValue",value != null ? value : "");
			                %>
    				        <c:out escapeXml="false" value="${displayValue}"  />&nbsp;
                            <html-el:hidden name="DocumentSearchForm" property="${field.property}"/>
                          </c:when>
                          <c:when test="${field.fieldType==field.TEXT}" >
                            <html-el:text name="DocumentSearchForm" property="${field.property}" size="16" maxlength="30"/>
                          </c:when>
                          <c:when test="${field.fieldType==field.DROPDOWN}" >
                            <html-el:select name="DocumentSearchForm" property="${field.property}" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
                              <c:if test="${not empty field.optionsCollectionProperty}">
                                <html-el:options collection="${field.optionsCollectionProperty}" labelProperty="${field.collectionLabelProperty}" property="${field.collectionKeyProperty}"/>
                              </c:if>
                            </html-el:select>
                          </c:when>
                          <c:when test="${field.fieldType==field.DROPDOWN_HIDE_EMPTY}" >
                            <c:choose>
                              <c:when test="${not empty field.optionsCollectionProperty}">
                                <%
  			                      StandardSearchCriteriaField field2 = (StandardSearchCriteriaField)pageContext.getAttribute("field");
                                  Object optionsList = (Object) request.getAttribute(field2.getOptionsCollectionProperty());
                                  if (optionsList != null) {
                                %>
                                  <html-el:select name="DocumentSearchForm" property="${field.property}" style="background: rgb(255, 255, 255) none repeat scroll 0%; font-family: verdana,arial,helvetica,sans-serif; font-size: 10px; -moz-background-clip: initial; -moz-background-inline-policy: initial; -moz-background-origin: initial; color: rgb(51, 51, 153);">
                                    <html-el:options collection="${field.optionsCollectionProperty}" labelProperty="${field.collectionLabelProperty}" property="${field.collectionKeyProperty}"/>
                                  </html-el:select>
                                <%
                                  } else {
                                %>
                                  ${field.emptyCollectionMessage}
                                <%
                                  }
                                %>
                              </c:when>
                              <c:otherwise>
                                ${field.emptyCollectionMessage}
                              </c:otherwise>
                            </c:choose>
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
                        <td rowspan="${fieldListSize}" align="right" valign="middle" nowrap="nowrap">
                          <bean-el:message key="general.help.generic" arg0="${field.helpMessageKeyArgument}"/>
                        </td>
 	    			  </c:if>
                    </tr>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </table>
          </td>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </tr>

  </c:forEach>
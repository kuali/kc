<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters:
  rule
--%>
  <c:set var="fieldIndex" value="${0}"/>
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
	  <c:choose>
	    <c:when test="${field.fieldType==field.HIDDEN}" >
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
          <td width="30%" class="datacell"><c:set var="drawFirstCell" value="false" />
		</c:if>
			<c:out value="${rule.fields[fieldIndex].value}" />
	  </c:otherwise>
	</c:choose>
  	<c:set var="fieldIndex" value="${fieldIndex+1}"/>
  </c:forEach>
</td>
</tr>
</c:forEach>

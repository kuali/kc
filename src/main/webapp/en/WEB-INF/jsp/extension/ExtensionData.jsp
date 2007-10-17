<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<%-- Parameters:
  extensions - should be an object of type org.kuali.workflow.attribute.web.WebExtensions
--%>
  <c:set var="isRowLabel" value="" />
  <c:set var="previousRow" value="NoPrevious" />

<c:set var="existingExtEmpty" value="${existingExtensions == null || existingExtensions.emptyExtensions}"/>
<c:set var="extEmpty" value="${extensions == null || extenstions.emptyExtensions}"/>
<c:set var="compareView" value="${!existingExtEmpty}"/>
<c:set var="extensionsColumn1" value="${extensions}"/>

<c:if test="${!existingExtEmpty || !extEmpty}">

<c:if test="${!existingExtEmpty}">
  <c:set var="extensionsColumn1" value="${existingExtensions}" />
  <c:set var="extensionsColumn2" value="${extensions}" />
</c:if>

<c:if test="${compareView}">
 <tr>
   <td class="datacell" colspan="3">
     <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
</c:if>

<c:forEach items="${extensionsColumn1.rows}" var="extensionsRows">
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
	<c:forEach items="${row.fields}" var="field">
	  <c:choose>
	    <c:when test="${field.fieldType == field.HIDDEN}" >
		</c:when>
	  <c:otherwise>
	  <c:if test="${drawFirstCell == true}">
		<tr>
			<c:if test="${isRowLabel == true}">
				 <td height="40" class="thnormal" width="15%" align="right" rowspan="<c:out value="${row.numberOfGroupRows}" />"><c:out value="${row.rowsGroupLabel}" /></td>
			</c:if>
		  <c:if test="${IsLookupDisplay != null && IsLookupDisplay == true}">
	        <c:choose>
	          <c:when test="${row.rowsGroupLabel == previousRow}">
			        <td height="40" class="thnormal" width="10%" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
	          </c:when>
						<c:otherwise>
						  <td height="40" width="25%" class="thnormal" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
						</c:otherwise>
	        </c:choose>
		  </c:if>
		  <c:if test="${IsLookupDisplay == null}">
	        <c:choose>
	          <c:when test="${row.rowsGroupLabel == previousRow}">
							<td class="thnormal" align="right" width="10%" >&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
	          </c:when>
						<c:otherwise>
							<td class="thnormal" width="25%" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
						</c:otherwise>
	        </c:choose>
		  </c:if>
          <td class="datacell"><c:set var="drawFirstCell" value="false" />
		</c:if>
		<c:out value="${extensionsColumn1.data[field.propertyName]}"/>
	  </c:otherwise>
	</c:choose>
  </c:forEach>
</td>
</tr>
</c:forEach>
</c:forEach>

<c:set var="isRowLabel" value="" />
<c:set var="previousRow" value="NoPrevious" />

<c:if test="${compareView}">
  </table>
</td>
<td class="datacell">
     <table align="center" width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
     <c:forEach items="${extensionsColumn2.rows}" var="extensionsRows">
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
	<c:forEach items="${row.fields}" var="field">
	  <c:choose>
	    <c:when test="${field.fieldType == field.HIDDEN}" >
		</c:when>
	  <c:otherwise>
	  <c:if test="${drawFirstCell == true}">
		<tr>
			<c:if test="${isRowLabel == true}">
				 <td height="40" class="thnormal" width="15%" align="right" rowspan="<c:out value="${row.numberOfGroupRows}" />"><c:out value="${row.rowsGroupLabel}" /></td>
			</c:if>
		  <c:if test="${IsLookupDisplay != null && IsLookupDisplay == true}">
	        <c:choose>
	          <c:when test="${row.rowsGroupLabel == previousRow}">
			        <td height="40" class="thnormal" width="10%" align="right">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
	          </c:when>
						<c:otherwise>
						  <td height="40" width="25%" class="thnormal" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
						</c:otherwise>
	        </c:choose>
		  </c:if>
		  <c:if test="${IsLookupDisplay == null}">
	        <c:choose>
	          <c:when test="${row.rowsGroupLabel == previousRow}">
							<td class="thnormal" align="right" width="10%" >&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
	          </c:when>
						<c:otherwise>
							<td class="thnormal" width="25%" align="right" colspan="2">&nbsp;&nbsp;<c:out value="${field.fieldLabel}" />:</td>
						</c:otherwise>
	        </c:choose>
		  </c:if>
          <td class="datacell"><c:set var="drawFirstCell" value="false" />
		</c:if>
		<c:out value="${extensionsColumn2.data[field.propertyName]}"/>
	  </c:otherwise>
	</c:choose>
  </c:forEach>
</td>
</tr>
</c:forEach>
</c:forEach>
</table>
</td>
</tr>
</c:if>
</c:if>
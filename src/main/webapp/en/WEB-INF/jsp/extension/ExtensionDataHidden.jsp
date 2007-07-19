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

<c:set var="extensionsProperty" value="${param['extensionsProperty']}"/>
<c:forEach items="${extensions.rows}" var="extensionsRows">
<c:forEach items="${extensionsRows}" var="row">
<c:forEach items="${row.fields}" var="field" >
    <c:if test="${!empty field.propertyName}">
      <c:set var="fieldPropertyName" value="${extensionsProperty}.data(${field.propertyName})"/>
	  <html-el:hidden property="${fieldPropertyName}"/>
	</c:if>
</c:forEach>
</c:forEach>
</c:forEach>

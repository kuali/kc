<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:forEach var="rule" items="${Rule2Form.rules}" varStatus="status">
  <tr>
    <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr valign="top">
          <c:if test="${showPreviousVersion && rule.previousVersion != null}">
          <td>
            <p>&nbsp;</p>
            <c:set var="rule" value="${rule.previousVersion}" scope="request"/>
            <c:set var="ruleIndex" value="${status.index}" scope="request"/>
            <c:set var="extraId" value="prev" scope="request"/>
            <c:set var="showHide" value="${Rule2Form.parentShowHide}" scope="request"/>
            <c:set var="showHideProperty" value="parentShowHide" scope="request"/>
            <c:set var="title" value="Rule ${ruleIndex+1} - Previous Version" scope="request"/>
            <c:set var="actionName" value="Rule.do" scope="request"/>
            <jsp:include page="RulePanel.jsp"/>
          </td>
          <td width="20"><img src="images/pixel_clear.gif" alt="" height="20" width="20"></td>
          </c:if>
          <td>
            <p>&nbsp;</p>
            <c:set var="rule" value="${rule}" scope="request"/>
            <c:set var="ruleIndex" value="${status.index}" scope="request"/>
            <c:set var="extraId" value="curr" scope="request"/>
            <c:set var="showHide" value="${Rule2Form.showHide}" scope="request"/>
            <c:set var="showHideProperty" value="showHide" scope="request"/>
            <c:set var="actionName" value="Rule.do" scope="request"/>
            <c:choose>
              <c:when test="${showPreviousVersion && rule.previousVersion != null}">
                <c:set var="title" value="Rule ${ruleIndex+1} - New Version" scope="request"/>
              </c:when>
              <c:otherwise>
                <c:set var="title" value="Rule ${ruleIndex+1}" scope="request"/>
              </c:otherwise>
            </c:choose>
            <jsp:include page="RulePanel.jsp"/>
          </td>
        </tr>
      </table>
    </td>
    <td width=20><img src="images/pixel_clear.gif" alt="" height=20 width=20></td>
  </tr>
</c:forEach>
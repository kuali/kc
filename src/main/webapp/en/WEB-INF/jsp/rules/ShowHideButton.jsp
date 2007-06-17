<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<c:set var="idVal" value="${param['idVal']}"/>

<c:if test="${ruleIndex != null}">
  <c:set var="showHide" value="${requestScope.showHide.children[ruleIndex]}"/>
  <c:set var="showHideProperty" value="${requestScope.showHideProperty}.child[${ruleIndex}]"/>
  <c:set var="anchorValue" value="rule${ruleIndex}"/>
</c:if>
<c:if test="${respIndex != null}">
  <c:set var="showHide" value="${showHide.children[respIndex]}"/>
  <c:set var="showHideProperty" value="${showHideProperty}.child[${respIndex}]"/>
  <c:set var="anchorValue" value="${anchorValue}resp${respIndex}"/>
</c:if>
<c:if test="${delIndex != null}">
  <c:set var="showHide" value="${showHide.children[delIndex]}"/>
  <c:set var="showHideProperty" value="${showHideProperty}.child[${delIndex}]"/>
  <c:set var="anchorValue" value="${anchorValue}del${delIndex}"/>
</c:if>
<c:if test="${delRespIndex != null}">
  <c:set var="showHide" value="${showHide.children[delRespIndex]}"/>
  <c:set var="showHideProperty" value="${showHideProperty}.child[${delRespIndex}]"/>
  <c:set var="anchorValue" value="${anchorValue}delResp${delRespIndex}"/>
</c:if>
<c:set var="showHideProperty" value="${showHideProperty}.show"/>

<a name="<c:out value="${anchorValue}"/>"></a>
<html-el:hidden styleId="<%= (String)pageContext.getAttribute("showHideProperty") %>" property="<%= (String)pageContext.getAttribute("showHideProperty") %>"/>
<c:set var="showButtonValue">
  <c:if test="${showHide == null || showHide.show}">hide1</c:if>
  <c:if test="${showHide != null && !showHide.show}">show</c:if>
</c:set>
<a id="<c:out value="A${idVal}"/>" onclick="rend(this, false, '<c:out value="${showHideProperty}"/>')"><img src="images/tinybutton-<c:out value="${showButtonValue}"/>.gif" alt="<c:out value="${showButtonValue}"/>" id="<c:out value="F${idVal}"/>" align="middle border="0" height="15" width="45"></a>

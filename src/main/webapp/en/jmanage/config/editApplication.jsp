<!--    /config/editApplication.jsp  -->
<%@ page errorPage="/error.jsp" %>
<%@ page import="org.jmanage.core.config.ApplicationConfig"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jmanage/html.tld" prefix="jmhtml"%>
<%@ taglib uri="/WEB-INF/jmanage/tlds/jstl/c.tld" prefix="c"%>

<jmhtml:errors />
<jmhtml:javascript formName="applicationForm" />

<jmhtml:form action="/config/editApplication" method="post"
                                onsubmit="return validateApplicationForm(this)">
  <jmhtml:hidden property="applicationId" />
  <jmhtml:hidden property="refreshApps" value="true" />

<table class="table" border="0" cellspacing="0" cellpadding="5" width="500">
    <tr class="tableHeader">
    <td colspan="2">Edit Application</td>
    </tr>
    <tr>
      <td class="headtext1">Type:</td>
      <td class="plaintext"><c:out value="${requestScope.applicationForm.type}" />
        <jmhtml:hidden property="type" />
      </td>
    </tr>
    <tr>
      <td class="headtext1">Name:</td>
      <td><jmhtml:text property="name" size="50"/></td>
    </tr>
    <c:if test="${requestScope.metaAppConfig.displayHost}">
    <tr>
      <td class="headtext1">Host:</td>
      <td><jmhtml:text property="host" size="50" /></td>
    </tr>
    </c:if>
    <c:if test="${requestScope.metaAppConfig.displayPort}">
    <tr>
      <td class="headtext1">Port:</td>
      <td><jmhtml:text property="port" size="50" /></td>
    </tr>
    </c:if>
    <c:if test="${requestScope.metaAppConfig.displayURL}">
    <tr>
      <td class="headtext1">URL:</td>
      <td><jmhtml:text property="URL" size="50" /></td>
    </tr>
    </c:if>
    <c:if test="${requestScope.metaAppConfig.displayUsername}">
    <tr>
      <td class="headtext1">Username:</td>
      <td><jmhtml:text property="username" size="50" /></td>
    </tr>
    </c:if>
    <c:if test="${requestScope.metaAppConfig.displayPassword}">
    <tr>
      <td class="headtext1">Password:</td>
      <td><jmhtml:password property="password" size="50" /></td>
    </tr>
    </c:if>
    <c:if test="${requestScope.applicationForm.type == 'jsr160'}">
    <tr>
        <td class="headtext1">java.naming.factory.initial:</td>
        <td><jmhtml:text property="jndiFactory" size="50" /></td>
    </tr>
    <tr>
        <td class="headtext1">java.naming.provider.url:</td>
        <td><jmhtml:text property="jndiURL" size="50" /></td>
    </tr>
    </c:if>
    <tr>
        <td>&nbsp;</td>
        <td>
          <jmhtml:submit value="Save" styleClass="Inside3d" />&nbsp;&nbsp;&nbsp;
          <jmhtml:button property="" value="Cancel" onclick="JavaScript:history.back();" styleClass="Inside3d" />
        </td>
    </tr>
  </table>
</jmhtml:form>

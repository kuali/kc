
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:forEach items="${KualiForm.medusaBean.parentNodes}" var="parentNode">
  <kra-m:medusaTreeNode node="${parentNode}"/>
</c:forEach>
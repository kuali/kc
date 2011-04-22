<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="node" required="true" type="org.kuali.kra.medusa.MedusaNode"%>

<li class="open" style="text-align: left;">
<c:set var="showOpen" value="1"/>
<c:set var="currentDoc" value="false"/>
<c:choose>
  <c:when test="${node.type == 'IP'}">
    <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.proposalId}">
      <c:set var="showOpen" value="0"/>
      <c:set var="currentDoc" value="true"/>
    </c:if>
    <span class="medusaNode"><a name="${node.type}-${node.bo.proposalId}-${showOpen}"><img src="static/images/instituteproposal12.gif"/>Institutional Proposal ${node.bo.proposalNumber}</a></span><!-- hack for treeview --> <a></a>
  </c:when>
  <c:when test="${node.type == 'award'}">
    <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.awardId}">
      <c:set var="showOpen" value="0"/>
      <c:set var="currentDoc" value="true"/>
    </c:if>
    <span class="medusaNode"><a name="${node.type}-${node.bo.awardId}-${showOpen}"><img src="static/images/sponsor12.gif"/>Award ${node.bo.awardNumber}</a></span><!-- hack for treeview --><a></a>
  </c:when>
  <c:when test="${node.type == 'DP'}">
    <c:if test="${KualiForm.medusaBean.moduleName == node.type && KualiForm.medusaBean.moduleIdentifier == node.bo.proposalNumber}">
      <c:set var="showOpen" value="0"/>
      <c:set var="currentDoc" value="1"/>
    </c:if>  
    <span class="medusaNode"><a name="${node.type}-${node.bo.proposalNumber}-${showOpen}"><img src="static/images/developmentproposal12.gif" />Development Proposal ${node.bo.proposalNumber}</a></span><!-- hack for treeview --><a></a>
  </c:when>
</c:choose>
<c:choose>
	<c:when test="${currentDoc}">
  		<div class="medusaDetails medusaDetailsLoaded">
    		 <kra-m:medusaNodeView node="${node}"/>
  		</div>
	</c:when>
	<c:otherwise>
		<div class="medusaDetails" style="display:none;"></div>
	</c:otherwise>
</c:choose>


<c:if test="${not empty node.childNodes}">
<ul>
<c:forEach items="${node.childNodes}" var="childNode">
  <kra-m:medusaTreeNode node="${childNode}"/>
</c:forEach>
</ul>
</c:if>
</li>


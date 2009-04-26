<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>


<kul:page headerTitle="Routing & Identity Management Document Type Hierarchy" transactionalDocument="false"
	showDocumentInfo="false" htmlFormAction="RuleQuickLinks" docTitle="Routing & Identity Management Document Type Hierarchy">

		<c:if test="${!empty KualiForm.documentTypeQuickLinksStructures}">			
        	<c:forEach var="documentTypeStruct" items="${KualiForm.documentTypeQuickLinksStructures}" varStatus="docStatus">
            <c:if test="${documentTypeStruct.shouldDisplay}">
        	  <c:set var="documentType" value="${documentTypeStruct.documentType}" />
              <c:choose>
                <c:when test="${docStatus.count == 1}">
                  <kul:tabTop
                    tabTitle="${documentType.label}"
                    defaultOpen="${renderOpened}"
                    boClassName="org.kuali.rice.kew.doctype.bo.DocumentType"
                    keyValues="documentTypeId=${documentType.documentTypeId}">
                      <div class="tab-container" style="width:auto;">
                            <c:set var="documentTypeStruct" value="${documentTypeStruct}" scope="request"/>
                            <c:set var="excludeDocId" value="${documentType.documentTypeId}" scope="request" />
                            <c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
                      </div>
                  </kul:tabTop>
                </c:when>
                <c:otherwise>
                  <kul:tab
                    tabTitle="${documentType.label}"
                    defaultOpen="false"
                    boClassName="org.kuali.rice.kew.doctype.bo.DocumentType"
                    keyValues="documentTypeId=${documentType.documentTypeId}">
                      <div class="tab-container" style="width:auto;">
                            <c:set var="documentTypeStruct" value="${documentTypeStruct}" scope="request"/>
                            <c:set var="excludeDocId" value="${documentType.documentTypeId}" scope="request" />
                            <c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
                      </div>
                    </kul:tab>
                </c:otherwise>
              </c:choose>
        	</c:if>
        	</c:forEach>
           	<kul:panelFooter />
         </c:if>	
		<c:if test="${empty KualiForm.documentTypeQuickLinksStructures}">
			No Documents Match			
		</c:if>

</kul:page>
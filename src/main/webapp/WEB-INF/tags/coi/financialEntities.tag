<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />

    <c:choose>
        <c:when test="${KualiForm.financialEntityHelper.editType == 'active'}">
            <c:set var="errorkey" value="financialEntityHelper.activeFinancialEntities*" />                                           
        </c:when>
        <c:otherwise>
            <c:set var="errorkey" value="financialEntityHelper.inactiveFinancialEntities*" />                                           
        </c:otherwise>
    </c:choose>


<kul:tab defaultOpen="true" tabTitle="Financial Entities"  transparentBackground="true"
    tabErrorKey="${errorkey}">
    <div class="tab-container" align="center">

        <kra-coi:financialEntityList  activeFlag="active" />

  <%-- Inactive --%>
  
        <kra-coi:financialEntityList  activeFlag="inactive" />
    
            <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" />
            <input type="hidden" name="editType" id="editType" value="${KualiForm.financialEntityHelper.editType}" />
        
    </div>

</kul:tab>
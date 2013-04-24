<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="personFinIntDisclAttribute" value="${DataDictionary.PersonFinIntDisclosure.attributes}" />
<c:set var="entityContactInfoAttribute" value="${DataDictionary.FinancialEntityContactInfo.attributes}" />


<div align="right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="financialEntityMyHelp" altText="help"/></div>

<kul:tab defaultOpen="true" tabTitle="Financial Entities"  transparentBackground="true">
    <div class="tab-container" align="center">

        <kra-coi:financialEntityList  activeFlag="active" />

  <%-- Inactive --%>
  
        <kra-coi:financialEntityList  activeFlag="inactive" />
    
            <input type="hidden" name="editIndex" id="editIndex" value="${KualiForm.financialEntityHelper.editEntityIndex}" />
            <input type="hidden" name="editType" id="editType" value="${KualiForm.financialEntityHelper.editType}" />
        
    </div>

</kul:tab>
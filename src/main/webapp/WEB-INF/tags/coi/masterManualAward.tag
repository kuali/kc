<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />              
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

        	<c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                     <kra-coi:projectStyle disclProjectBean="${disclProjectBean}"/>                    
                     <kra-coi:manualAwardHeader disclProject="${disclProjectBean.disclosureProject}" idx = "0"/>                    
                     <kra-coi:masterProjectQuestionnaires disclProjectBean="${disclProjectBean}" parentTab="Manual Awards" />                    
                     <kra-coi:masterProjectFE disclProjectBean="${disclProjectBean}" projectDivNamePrefix="masterManualAwardFE"  idx="${status.index}" projectListName="manualAwardProjects"/>                    
        	</c:forEach> 
            <%-- Existing data --%>


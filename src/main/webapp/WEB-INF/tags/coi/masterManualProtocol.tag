<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
              
                                  
        	<c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                     <kra-coi:projectStyle disclProjectBean="${disclProjectBean}"/>                    
                     <kra-coi:manualProtocolHeader disclProject="${disclProjectBean.disclosureProject}" idx = "0"/>                    
                     <kra-coi:masterProjectQuestionnaires disclProjectBean="${disclProjectBean}" parentTab="Manual Protocols" />                    
                     <kra-coi:masterProjectFE disclProjectBean="${disclProjectBean}" projectDivNamePrefix="masterManualProtocolFE"  idx="${status.index}" projectListName="manualProtocolProjects" />                    
        	</c:forEach> 

<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />              
<%@ attribute name="projectDivNamePrefix" required="true" description="name for project related div" %>
<%@ attribute name="projectListName" required="true" description="Project list name in master bean" %>
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

<c:set var="hidden" value="${not masterDisclosureProjects[0].coiDisclProject.coiDisclosure.approvedDisclosure and masterDisclosureProjects[0].coiDisclProject.coiDisclosureEventType.excludeFromMasterDisclosure}" />

<div <c:if test="${hidden}">style="display:none;"</c:if>>                                  
            <c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                     <kra-coi:projectStyle disclProjectBean="${disclProjectBean}"/>                    
                     <kra-coi:manualProjectHeader disclProject="${disclProjectBean.coiDisclProject}" idx = "0"/>                    
                     <kra-coi:masterProjectQuestionnaires disclProjectBean="${disclProjectBean}" parentTab="Manual Awards" />                    
                     <kra-coi:masterProjectFE disclProjectBean="${disclProjectBean}" projectDivNamePrefix="${projectDivNamePrefix}"  idx="${status.index}" projectListName="${projectListName}"/>                    
            </c:forEach> 
 </div>         
            <%-- Existing data --%>


<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<script type="text/javascript">
	var saveButtonClicked = false;
	jq(document).ready(function() {
		jq("#viewQualificationsLink").fancybox();
    });
</script>

<c:set var="parentTabName" value="" />

<c:set var="summaryBySpecies" value="${KualiForm.iacucProtocolProceduresHelper.summaryGroupedBySpecies}"/>
<c:set var="currentSummaryDisplay" value="(Summary Displayed by Group)" />
<c:if test="${summaryBySpecies}">
	<c:set var="currentSummaryDisplay" value="(Summary Displayed by Species)" />
</c:if>

	<kul:innerTab tabTitle="Summary" parentTab="${parentTabName}" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
  			<b>View Summary by : </b>
			    <html:image property="methodToCall.summaryByGroup" title="Summary by group"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-group.gif' styleClass="tinybutton"/>
			    <html:image property="methodToCall.summaryBySpecies" title="Summary by species"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-species.gif' styleClass="tinybutton"/>
            <br>
	        	<c:out value="${currentSummaryDisplay}"/>
  			<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document"
                                                  parameterName="iacucProtocolProcedureSummaryHelp" altText="Help"/>
            </span>
		    <c:forEach items="${KualiForm.document.protocolList[0].iacucProtocolStudyGroupSpeciesList}" var="procedureSpecies" varStatus="status">
			    <c:set var="tabTitle" value="${procedureSpecies.iacucSpecies.speciesName}" />
			    <c:set var="studyProcedureCollectionReference" value="${procedureSpecies.responsibleProcedures}" />
			    <c:set var="totalSpeciesCount" value="${procedureSpecies.totalSpeciesCount}" />
  			    <c:choose>
  				    <c:when test="${summaryBySpecies}">
					    <c:set var="tabTitle" value="${procedureSpecies.iacucSpecies.speciesName}" />
  				    </c:when>
  				    <c:otherwise>
						<c:set var="tabTitle" value="${procedureSpecies.groupAndSpecies}" />
  				    </c:otherwise>
  			    </c:choose>
 			    <kra-iacuc:procedureSummaryGrouped
 				    groupIndex="${status.index}"
            	    tabTitle="${tabTitle}"
            	    totalSpeciesCount="${totalSpeciesCount}"
                    studyProcedureCollectionReference="${studyProcedureCollectionReference}"/>
		    </c:forEach>
	</kul:innerTab>


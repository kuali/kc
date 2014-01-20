<%--
 Copyright 2005-2014 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
		<h3>
  			<span class="subhead-left">View Summary by : </span>
	        <span class="subhead-left">&nbsp;&nbsp;&nbsp;&nbsp;</span>
	        <span class="subhead-left">
			    <html:image property="methodToCall.summaryByGroup" title="Summary by group"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-group.gif' styleClass="tinybutton"/>
	        </span>
	        <span class="subhead-left">&nbsp;&nbsp;&nbsp;&nbsp;</span>
	        <span class="subhead-left">
			    <html:image property="methodToCall.summaryBySpecies" title="Summary by species"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-species.gif' styleClass="tinybutton"/>
	        </span>
	        <span class="subhead-left">&nbsp;&nbsp;&nbsp;&nbsp;</span>
	        <span class="subhead-left">
	        	<c:out value="${currentSummaryDisplay}"/>
	        </span>
  			<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolIncludedCategoriesHelp" altText="Help"/></span>
     	</h3>
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


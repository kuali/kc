<%--
 Copyright 2005-2013 The Kuali Foundation
 
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

	<kul:innerTab tabTitle="Summary" parentTab="${parentTabName}" defaultOpen="true" tabErrorKey="" useCurrentTabIndexAsKey="true">
		<h3>
  			<span class="subhead-left">Summary</span>
  			<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="iacucProtocolIncludedCategoriesHelp" altText="Help"/></span>
     	</h3>
		<c:forEach items="${KualiForm.document.protocolList[0].iacucProtocolStudyGroupSpeciesList}" var="procedureSpecies" varStatus="status">
			<kul:innerTab tabTitle="${procedureSpecies.iacucSpecies.speciesName}" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
				<c:forEach items="${procedureSpecies.protocolStudyProcedures}" var="studyProcedure" varStatus="procedureIndex">
				<div style="border:1px solid #999; padding:0px; margin:15px; margin-left:30px;">
                	<div align="left"  style="width:100%; background-color:#CCC; padding:0px; margin:0px; border-bottom:1px solid #999; height:21px; min-width:750px;">
                    	<div style="float:left; width:600px; padding:3px; height:15px;">
                    		<strong>
	                    		<c:out value="${studyProcedure.iacucProcedureCategory.procedureCategory}"/> : 
	                    		<c:out value="${studyProcedure.iacucProcedure.procedureDescription}"/> 
                    		</strong>
                    	</div>
                        <div style="float:right; width:125px; border-left:1px solid #999; padding:3px; height:15px;">
                        	Count: 
                        </div>
					</div>
                    <div align="left" style="border:1px solid #999; padding:10px; margin-top:10px; margin-bottom:10px; margin-right:10px; margin-left:20px; background-image:url('../BFN/images/black05.png');">
                        <strong>Custom Data</strong><br />
						<c:forEach items="${studyProcedure.iacucProtocolStudyCustomDataList}" var="procedureCustomData" varStatus="customDataIndex">
                    		<c:out value="${procedureCustomData.iacucProcedureCategoryCustomData.label}"/> :  
                    		<c:out value="${procedureCustomData.value}"/> <br/> 
                        </c:forEach>
                    </div>
                    <div align="left" style="border:1px solid #999; padding:10px; margin-top:10px; margin-bottom:10px; margin-right:10px; margin-left:20px;">
                        <strong>Procedure Personnel</strong><br />
						<c:forEach items="${studyProcedure.iacucProtocolStudyGroupPersons}" var="procedurePerson" varStatus="personIndex">
                    		<c:out value="${procedurePerson.protocolPerson.personName}"/> <br /> 
                        	Trained: <br/>
							<a href="#qualification-div${status.index}${personIndex.index}" id="viewQualificationsLink" >
							    <img src="${ConfigProperties.kra.externalizable.images.url}tinybutton-viewqualifications.gif" alt="View Qualifications" class="tinybutton addButton" />
							</a>		               	
							<br/><br/>		               	
							<div id="qualification-div${status.index}${personIndex.index}" style="display: none;">
								<div style="text-align:center; background-color:#666; color:#FFF;" align="center"><strong>Qualification</strong></div></br>
	                    		<c:out value="${procedurePerson.protocolPerson.procedureQualificationDescription}"/> <br /> 
							</div>
                        </c:forEach>
                    </div>
                    <div align="left" style="border:1px solid #999; padding:10px; margin-top:10px; margin-bottom:10px; margin-right:10px; margin-left:20px;">
                        <strong>Locations</strong><br />
						<c:forEach items="${studyProcedure.iacucProtocolStudyGroupLocations}" var="procedureLocationDetail" varStatus="locationIndex">
                    		Location Type: <c:out value="${procedureLocationDetail.iacucProtocolStudyGroupLocation.iacucLocationType.location}"/> <br /> 
                    		Location Name: <c:out value="${procedureLocationDetail.iacucProtocolStudyGroupLocation.iacucLocationName.locationName}"/> <br /> 
                    		Room: <c:out value="${procedureLocationDetail.iacucProtocolStudyGroupLocation.locationRoom}"/> <br /> 
                    		Description: <c:out value="${procedureLocationDetail.iacucProtocolStudyGroupLocation.studyGroupLocationDescription}"/> <br /> 
                        </c:forEach>
                    </div>
                </div>
                </c:forEach>
			</kul:innerTab>
		</c:forEach>
	</kul:innerTab>


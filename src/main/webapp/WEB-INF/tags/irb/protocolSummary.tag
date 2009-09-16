<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolProtocolActions" />
<c:set var="textAreaFieldName" value="document.protocolList[0].protocolSubmission.votingComments" />
<c:set var="protocol" value="${KualiForm.document.protocolList[0]}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="protocolUnitAttributes" value="${DataDictionary.ProtocolUnit.attributes}" />
<c:set var="protocolResearchAreaAttributes" value="${DataDictionary.ProtocolResearchArea.attributes}" />
<c:set var="researchAreaAttributes" value="${DataDictionary.ResearchArea.attributes}" />
<c:set var="protocolSubmissionAttributes" value="${DataDictionary.ProtocolSubmission.attributes}" />
<c:set var="committeeAttributes" value="${DataDictionary.Committee.attributes}" />
<c:set var="protocolSubmissionTypeAttributes" value="${DataDictionary.ProtocolSubmissionType.attributes}" />
<c:set var="protocolReviewTypeAttributes" value="${DataDictionary.ProtocolReviewType.attributes}" />
<c:set var="protocolSubmissionQualifierTypeAttributes" value="${DataDictionary.ProtocolSubmissionQualifierType.attributes}" />
<c:set var="protocolSubmissionStatusAttributes" value="${DataDictionary.ProtocolSubmissionStatus.attributes}" />
<c:set var="protocolStatusAttributes" value="${DataDictionary.ProtocolStatus.attributes}" />
<c:set var="protocolTypeAttributes" value="${DataDictionary.ProtocolType.attributes}" />
<c:set var="committeeMembershipTypeAttributes" value="${DataDictionary.CommitteeMembershipType.attributes}" />
<c:set var="committeeMembershipAttributes" value="${DataDictionary.CommitteeMembership.attributes}" />
<c:set var="personAttributes" value="${DataDictionary.Person.attributes}" />
<c:set var="commentDisplayLength" value="<%=org.kuali.kra.infrastructure.Constants.PROTOCOL_SUMMARY_VOTINGCOMMENTS%>" />

    	<kra:innerTab parentTab="Summary, History, & Print" defaultOpen="false" tabTitle="View Summary (Notified Committee dd/mm/yyyy)">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <th style="text-align:right; width:135px;">
                        Protocol Number:
                    </th>
                    <td>
                    	<kul:htmlControlAttribute property="document.protocolList[0].protocolNumber" 
									                							readOnly="true"	attributeEntry="${protocolAttributes.protocolNumber}"  />
                    </td>
                    <th style="text-align:right">
                        Application Date:
                    </th>
                    <td>
                    	<kul:htmlControlAttribute property="document.protocolList[0].applicationDate" 
									                							readOnly="true"	attributeEntry="${protocolAttributes.applicationDate}"  />                  
                    </td>
                    <th rowspan="5">
                        <a href="#"><img src="../images/tinybutton-previous3.gif" alt="close" width="70" height="15" border="0" style="padding:2px;" /></a><br />
                        <a href="#"><img src="../images/tinybutton-next3.gif" alt="close" width="70" height="15" border="0" style="padding:2px;" /></a>
                    </th>
                </tr>
                <tr>
                    <th style="text-align:right">
                        Approval Date:
                    </th>
                    <td>
                    	<kul:htmlControlAttribute property="document.protocolList[0].approvalDate" 
									                							readOnly="true"	attributeEntry="${protocolAttributes.approvalDate}"  />      
                    </td>
                    <th style="text-align:right">
                        Expiration Date:
                    </th>
                    <td>
                    	<kul:htmlControlAttribute property="document.protocolList[0].expirationDate" 
									                							readOnly="true"	attributeEntry="${protocolAttributes.expirationDate}"  />                       
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right">
                        Status:
                    </th>
                    <td>
                    	<kul:htmlControlAttribute property="document.protocolList[0].protocolStatus.description" 
									                							readOnly="true"	attributeEntry="${protocolStatusAttributes.description}"  />
                    </td>
                    <th style="text-align:right">&nbsp;
                        Type:
                    </th>
                    <td>
                    	<kul:htmlControlAttribute property="document.protocolList[0].protocolType.description" 
									                							readOnly="true"	attributeEntry="${protocolTypeAttributes.description}"  />
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right; height:50px;">
                        Title:
                    </th>
                    <td colspan="3" style="text-align:left; vertical-align:top;">
                    	<kul:htmlControlAttribute property="document.protocolList[0].title" 
									                							readOnly="true"	attributeEntry="${protocolAttributes.title}"  />  
                    </td>

                </tr>
            </table>
    		
            <!-- Investigators -->
                <table  cellpadding="0" cellspacing="0"  summary="">
                    <tr>
                        <td class="tab-subhead" colspan="4">
                           Personnel
                      </td>
                    </tr>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Affiliation </th>
                            <th>Unit(s)</th>
                        </tr>   		
			        	<c:forEach var="protocolPerson" items="${protocol.protocolPersons}" varStatus="status">
				             <tr>
			                          <td><kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].personName" 
				                									readOnly="true"	attributeEntry="${protocolPersonAttributes.personName}"  /></td>
			                          <td><kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].protocolPersonRole.description" 
				                									readOnly="true"	attributeEntry="${protocolPersonAttributes.protocolPersonRole.description}"  /></td>
			                          <td><kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].affiliationType.description" 
				                									readOnly="true"	attributeEntry="${protocolPersonAttributes.affiliationType.description}"  /></td>
			                          <td> 
								        	<c:forEach var="protocolUnit" items="${protocolPerson.protocolUnits}" varStatus="it">						        
								            	<kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].protocolUnits[${it.index}].protocolUnitsId" 
									                							readOnly="true"	attributeEntry="${protocolUnitAttributes.protocolUnitsId}"  />	
									        &nbsp;:&nbsp;	
									        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].protocolUnits[${it.index}].unitName" 
									                							readOnly="true"	attributeEntry="${protocolUnitAttributes.unitName}"  />	        										                 
								        	</c:forEach>                                                    
			                          </td>
				            </tr>     
			        	</c:forEach>
    			</table>
    			
	          <!-- Areas of Research -->
              <table  cellpadding="0" cellspacing="0"  summary="">
                    <tr>
                        <td class="tab-subhead" >Areas of Research</td>
                    </tr>
			        	<c:forEach var="protocolPerson" items="${protocol.protocolResearchAreas}" varStatus="status">
				             <tr>
			                     <td>
			                     	<kul:htmlControlAttribute property="document.protocolList[0].protocolResearchAreas[${status.index}].researchAreas.researchAreaCode" 
				                									readOnly="true"	attributeEntry="${researchAreaAttributes.researchAreas.researchAreaCode}"  />
				                	:&nbsp;
				                	<kul:htmlControlAttribute property="document.protocolList[0].protocolResearchAreas[${status.index}].researchAreas.description" 
				                									readOnly="true"	attributeEntry="${researchAreaAttributes.researchAreas.description}"  />
				                 </td>
				             </tr>     
			        	</c:forEach>
			  </table>

			<!-- Submission Details -->
                <table  cellpadding="0" cellspacing="0"  summary="">
                    <tr>
                        <td class="tab-subhead" colspan="2">
                            Submission Details
                        </td>

                    </tr>
                         <tr>
                            <th style="text-align:right; width:135px;">
                                Committee Id:
                            </th>
                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committee.committeeId" 
									                							readOnly="true"	attributeEntry="${committeeAttributes.committeeId}"  />
                            </td>
                        </tr>

                        <tr>
                            <th style="text-align:right;">
                                Committee Name:
                            </th>
                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committee.committeeName" 
									                							readOnly="true"	attributeEntry="${committeeAttributes.committeeName}"  />
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;">
                                Scheduled Date:
                            </th>
                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.submissionDate" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.submissionDate}"  />
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;">
                                Submission Type:
                            </th>

                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.protocolSubmissionType.description" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionTypeAttributes.description}"  />
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;">
                                Review Type:
                            </th>
                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.protocolReviewType.description" 
									                							readOnly="true"	attributeEntry="${protocolReviewTypeAttributes.description}"  />
                            </td>

                        </tr>
                        <tr>
                            <th style="text-align:right;">
                                Type Qualifier:
                            </th>
                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.protocolSubmissionQualifierType.description" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionQualifierTypeAttributes.description}"  />
                            </td>
                        </tr>
                        <tr>

                            <th style="text-align:right;">
                                Submission Status:
                            </th>
                            <td>
                            	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.submissionStatus.description" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionStatusAttributes.description}"  />
                            </td>
                        </tr>
                </table>

			<!-- Reviewers -->
                <table  cellpadding="0" cellspacing="0"  summary="">
                    <tr>
                        <td class="tab-subhead" colspan="2">
                             Reviewers
                        </td>
                    </tr>
                    <!--<tbody id="G700" style="display: none;">-->
                        <tr>

                            <th>
                                Name
                            </th>
                            <th>
                                Type
                            </th>
                        </tr>
			        	<c:forEach var="committeeMembership" items="${protocol.protocolSubmission.committee.committeeMemberships}" varStatus="status">
				             <tr>
			                    <td>
			                     	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committee.committeeMemberships[${status.index}].personName" 
				                									readOnly="true"	attributeEntry="${committeeMembershipAttributes.personName}"  />
				                </td>
                            	<td>
				                	<kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committee.committeeMemberships[${status.index}].membershipType.description" 
				                									readOnly="true"	attributeEntry="${committeeMembershipTypeAttributes.description}"  />
				                </td>
				             </tr>     
			        	</c:forEach>                        
                </table>

              <table  cellpadding="0" cellspacing="0"  summary="">
                <tr>
                  <td class="tab-subhead" colspan="4">
                    Vote Summary </td>
                </tr>
                <tr>
                  <th style="text-align:right;">Yes:</th>

                  <td><kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.yesVoteCount" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.yesVoteCount}"/></td>
                  <th style="text-align:right;">Abstain:</th>
                  <td><kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.abstainerCount" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.abstainerCount}"/></td>
                </tr>
                <tr>
                  <th style="text-align:right;">No:</th>
                  <td><kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.noVoteCount" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.noVoteCount}"/></td>
                  <th style="text-align:right;">Abstainers:</th>
                  <td>
                  	<c:forEach var="committeeScheduleAttendance" items="${protocol.protocolSubmission.committeeSchedule.committeeScheduleAttendances}" varStatus="status">
                  	
			                 <kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committeeSchedule.committeeScheduleAttendances[${status.index}].person.lastName" 
				                									readOnly="true"	attributeEntry="${personAttributes.lastName}"/>
				                									,
				             <kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committeeSchedule.committeeScheduleAttendances[${status.index}].person.firstName" 
				                									readOnly="true"	attributeEntry="${personAttributes.firstName}"/>
    																&nbsp;	
			        </c:forEach>                  
                  </td>
                </tr>
                <tr>
                  <th style="text-align:right;">Comments:</th>
                  <td colspan="3"><span style="border:none;">                  
                    <kra:truncateComment textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolSubmissionAttributes.votingComments.label}" 
    	                       	textValue="${KualiForm.document.protocolList[0].protocolSubmission.votingComments}" displaySize="${commentDisplayLength}"/>                  		                    			
                    <span style="border:none; width:20px; vertical-align:bottom;">                    	
                  	</span>
                  </span></td>
                </tr>
              </table>

<p>-- or --</p>

              <table  cellpadding="0" cellspacing="0"  summary="">
                  <tr>
                    <td class="tab-subhead" colspan="8"><!--<a href="#" id="A700" onclick="rend(this, false)"><img src="../images/tinybutton-show.gif" alt="show/hide this panel" width=45 height=15 border=0 align="absmiddle" id="F700"></a>-->
                      Vote Summary </td>

                </tr>
                  <!--<tbody id="G700" style="display: none;">-->
                  <tr>
                    <th> No: </th>
                    <td><kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.noVoteCount" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.noVoteCount}"/></td>
                    <th> Yes: </th>
                    <td><kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.yesVoteCount" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.yesVoteCount}"/></td>
                    <th> Abstain: </th>
                    <td><kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.abstainerCount" 
									                							readOnly="true"	attributeEntry="${protocolSubmissionAttributes.abstainerCount}"/></td>
                    <th style="text-align:right;"> Comments: </th>
                    <td><table style="border:none; width:100%;" cellpadding="0" cellspacing="0">

                      <tr>
                        <td style="border:none;"> 
							<kra:truncateComment textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolSubmissionAttributes.votingComments.label}" 
    	                       	textValue="${KualiForm.document.protocolList[0].protocolSubmission.votingComments}" displaySize="${commentDisplayLength}"/> 
						</td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <th colspan="7" style="text-align:right;">Abstainers:</th>
                    <td>
                  	<c:forEach var="committeeScheduleAttendance" items="${protocol.protocolSubmission.committeeSchedule.committeeScheduleAttendances}" varStatus="status">
                  	
			                 <kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committeeSchedule.committeeScheduleAttendances[${status.index}].person.lastName" 
				                									readOnly="true"	attributeEntry="${personAttributes.lastName}"  />
				                									,
				             <kul:htmlControlAttribute property="document.protocolList[0].protocolSubmission.committeeSchedule.committeeScheduleAttendances[${status.index}].person.firstName" 
				                									readOnly="true"	attributeEntry="${personAttributes.firstName}"  />
				                									&nbsp;
    
			        </c:forEach>                     
                    </td>
                  </tr>
                  <!--</tbody>-->
              </table>
              <p/>
    			
    	</kra:innerTab>


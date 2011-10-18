<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css" type="text/css" />
<c:set var="financialEntityVersions" value= "${KualiForm.financialEntityHelper.versions}" />
<c:set var="entityName" value="${KualiForm.financialEntityHelper.versions[0].entityName}"/>
                                        <div class="innerTab-container" align="left">
                       					 <table id="activeEntities-table" cellpadding=0 cellspacing="0"  style="border-collapse:collapse;" class="elementhead_table">
                                                <tr>
                                                	<th class="elementhead_left">${entityName}</th>
                                                	<th class="elementhead_right">                    
                                                	<span class="subhead-right"> 
                                                	<kul:help businessObjectClassName="org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure" altText="help"/>
                                                	 </span>
                                                	
                                                	</th>
                                                </tr>
                                                </table>
                                                <table class="content_table">
                                                <tr>
                                                   <td class="content_grey">&nbsp;</td>
                                                   <td class="content_grey" style="text-align: center;">Updated</td>
                                                </tr>
                                               <c:forEach var="finEntityVersion" items="${financialEntityVersions}">
                                                    <tr>
                                                    <td class="content_white">
                                                    	${finEntityVersion.sequenceNumber}
                                                    </td>
                                                    <td class="content_white">
                                                    	<fmt:formatDate value="${finEntityVersion.updateTimestamp}" pattern="MM/dd/yyyy KK:mm a" />
                                                        &nbsp;
                                                        ${finEntityVersion.updateUser}
                                                    </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    
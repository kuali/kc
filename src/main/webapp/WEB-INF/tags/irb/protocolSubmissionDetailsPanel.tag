<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="prevDisabled" value="false" />
<c:set var="nextDisabled" value="false" />
<c:set var="tabSubmissionDetails" value="submissionDetails" scope="request"/>
<c:if test="${KualiForm.actionHelper.currentSubmissionNumber == 0}">
    <c:set var="prevDisabled" value="true" />
</c:if>

<c:if test="${KualiForm.actionHelper.currentSubmissionNumber + 1 == fn:length(KualiForm.document.protocol.protocolSubmissions)}">
    <c:set var="nextDisabled" value="true" />
</c:if>

<kra:innerTab parentTab="Summary, History, & Print" defaultOpen="false" tabTitle="Submission Details">
    <div class="innerTab-container" align="left">
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
            <tr>
                <th align="right" width="50%" >Submission Navigation:
                </th>
                <th align="left" width="50%"> 
                    <div align="left">
                        <c:if test="${!prevDisabled}">
                            <html:image property="methodToCall.viewPreviousSubmission.line${KualiForm.actionHelper.currentSubmissionNumber - 1}.anchor${currentTabIndex}"
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-previous3.gif"
                                        styleClass="tinybutton"
                                        alt="View Previous Submission" 
                                        onclick="excludeSubmitRestriction = true;" />
                        </c:if>
                        <c:if test="${!nextDisabled}">
                            <html:image property="methodToCall.viewNextSubmission.line${KualiForm.actionHelper.currentSubmissionNumber + 1}.anchor${currentTabIndex}"
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-next3.gif"
                                        styleClass="tinybutton"
                                        alt="View Next Submission" 
                                        onclick="excludeSubmitRestriction = true;" />
                        </c:if>
                     </div>
                </th>
            </tr>
		    <kra-irb:submissionDetails />
 		    <kra-irb:reviewers />
		    <kra-irb:voteSummary />
		    <kra-irb:checklistItems />
		    <kra-irb:protocolReviewComments />
            </tbody>
        </table>
    </div>
    			
</kra:innerTab>


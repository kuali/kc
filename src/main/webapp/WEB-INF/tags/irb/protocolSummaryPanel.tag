<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="prevDisabled" value="false" />
<c:if test="${KualiForm.actionHelper.currentSequenceNumber == 0}">
    <c:set var="prevDisabled" value="true" />
</c:if>

<c:set var="nextDisabled" value="false" />
<c:if test="${KualiForm.actionHelper.currentSequenceNumber + 1 == KualiForm.actionHelper.sequenceCount}">
    <c:set var="nextDisabled" value="true" />
</c:if>

<kul:innerTab tabTitle="Summary" parentTab="" defaultOpen="false">
    <div class="innerTab-container">
    
        <table cellpadding="0" cellspacing="0">
            <tbody>
                <tr>
                    <th colspan="4" style="text-align:center">
                        <span id="summarySequence">Sequence ${KualiForm.actionHelper.currentSequenceNumber + 1}/${KualiForm.actionHelper.sequenceCount}:&nbsp;
                        ${KualiForm.actionHelper.protocolSummary.lastProtocolActionDescription}</span>
                        <c:if test="${!prevDisabled}">
                            <html:image property="methodToCall.viewPreviousProtocolSummary.line${status.index}.anchor${currentTabIndex}"
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-previous3.gif"
                                        styleClass="tinybutton"
                                        alt="View Previous Summary" 
                                        onclick="excludeSubmitRestriction = true;" />
                        </c:if>
                        <c:if test="${!nextDisabled}">
                            <html:image property="methodToCall.viewNextProtocolSummary.line${status.index}.anchor${currentTabIndex}"
                                        src="${ConfigProperties.kra.externalizable.images.url}tinybutton-next3.gif"
                                        styleClass="tinybutton"
                                        alt="View Next Summary" 
                                        onclick="excludeSubmitRestriction = true;" />
                        </c:if>
                    </th>
                </tr>
            </tbody>
        </table>
    
        <kra-irb:protocolSummary prefix="actionHelper.protocolSummary" protocolSummary="${KualiForm.actionHelper.protocolSummary}" />
        
        <c:if test="${!prevDisabled}">
             <table cellpadding="0" cellspacing="0">
                <tbody>
                    <tr>
                        <td style="background-color: rgb(195, 195, 195); font-weight: bold; height: 35px;">
                            <img style="border: medium none ; width: 45px; height: 15px; vertical-align: middle;" alt="show/hide this panel" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif" id="Csequence01"/>
                            <script type="text/javascript">
                                jq(document).ready(function(){
                                    // initial state
                                    jq("#Dsequence01").hide(0);
                                    // trigger
                                    jq("#Csequence01").toggle(
                                        function() {
                                            jq("#Dsequence01").slideDown(700);
                                            jq("#Csequence01").attr("src","${ConfigProperties.kra.externalizable.images.url}tinybutton-hide.gif");
                                            jq(".sequence").addClass("compare"); 
                                            jq(".sequencetd").css("color","#666666"); 
                                            jq(".changed").css({'color' : '#FF0000', 'font-weight' : 'bold'}); 
                                        },
                                        function() {
                                            jq("#Dsequence01").slideUp(600);
                                            jq("#Csequence01").attr("src","${ConfigProperties.kra.externalizable.images.url}tinybutton-show.gif");
                                            jq(".sequence").removeClass("compare"); 
                                            jq(".sequencetd").css("color","#333333"); 
                                            jq(".changed").css({'color' : '#000000', 'font-weight' : 'normal'}); 
                                        });  
                                });
                            </script>
                            Compare to Previous Sequence
                        </td>
                    </tr>
                </tbody>
            </table>
        
            <div id="Dsequence01">
                <table cellpadding="0" cellspacing="0">
                    <tbody>
                        <tr>
                            <th colspan="4" style="text-align:center">
                                Sequence ${KualiForm.actionHelper.currentSequenceNumber}/${KualiForm.actionHelper.sequenceCount}:&nbsp;
                                ${KualiForm.actionHelper.prevProtocolSummary.lastProtocolActionDescription}
                            </th>
                        </tr>
                    </tbody>
                </table>
        
                <kra-irb:protocolSummary prefix="actionHelper.prevProtocolSummary" protocolSummary="${KualiForm.actionHelper.prevProtocolSummary}" />
            </div>
        </c:if>
    </div>
</kul:innerTab>
   

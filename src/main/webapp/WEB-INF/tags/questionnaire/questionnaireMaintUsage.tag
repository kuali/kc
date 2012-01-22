<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<c:set var="readOnly" value="false"  scope="request"/>
<c:set var="questionnaireUsageAttributes" value="${DataDictionary.QuestionnaireUsage.attributes}" />
<c:set var="vers" value="${KualiForm.document.newMaintainableObject.businessObject.sequenceNumber}" />

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"><a href="#" class="usagepanelcontrol"><img src='kr/images/tinybutton-show.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a>
          Usage </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.questionnaire.Questionnaire" altText="help"/> </span>
    </h3>
        
      <div id="usagepanelcontent">
        <table id = "usage-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <th><div align="left">&nbsp;</div></th> 
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.moduleItemCode}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.moduleSubItemCode}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.mandatory}" noColon="true" /></div></th>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" noColon="true" /></div></th>
                <kul:htmlAttributeHeaderCell literalLabel="Version" scope="col"/>
                <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
            
            </tr>     
            
           <c:if test="${!KualiForm.readOnly}">
             <tr>
                <th class="infoline">
                    <c:out value="Add:" />
                </th>

                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newQuestionnaireUsage.moduleItemCode" attributeEntry="${questionnaireUsageAttributes.moduleItemCode}" onchange="moduleCodeChange(this)" styleClass="fixed-size-select"/>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div id="submodulediv" align="center">
                <%--
							                <html:select property="newQuestionnaireUsage.moduleSubItemCode" tabindex="0">
							                <c:forEach items="${krafn:getOptionList('org.kuali.kra.irb.personnel.ProtocolPersonRoleValuesFinder', paramMap)}" var="option">
							                <c:choose>
							                    <c:when test="${KualiForm.document.protocol.protocolPersons[personIndex].protocolPersonRoleId == option.key}">
							                    <option value="${option.key}" selected>${option.value}</option>
							                    </c:when>
							                    <c:otherwise>
							                    <option value="${option.key}">${option.value}</option>
							                    </c:otherwise>
							                </c:choose>
							                </c:forEach>
							                </html:select> --%>
                </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newQuestionnaireUsage.mandatory" attributeEntry="${questionnaireUsageAttributes.mandatory}"/>
                </div>
                </td>
                <td class="infoline">
                <div align="center">
                    <kul:htmlControlAttribute property="newQuestionnaireUsage.questionnaireLabel" attributeEntry="${questionnaireUsageAttributes.questionnaireLabel}" />
                <div align="center">
                </td>
                <td class="infoline">   
                <div align="center">   
                     
                        ${vers}
                </div>
                </td>
                
                
                <td class="infoline">
                    <div align=center>&nbsp;
                        <input type="image" id="addUsage" name="addUsage" alt="Add a Usage" title="Add a Usage" src="static/images/tinybutton-add1.gif" class="tinybutton">
                    </div>
                </td>
            </tr>
          </c:if>
            
        </table>
    </div>
</div>

  <script>

                jq("#usagepanelcontent").hide();
                jq("a.usagepanelcontrol").toggle(
                    function()
                    {
                        jq("#usagepanelcontent").slideDown(500);
                        jq("a.usagepanelcontrol").html("<img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                    },function(){
                        jq("#usagepanelcontent").slideUp(500);
                        jq("a.usagepanelcontrol").html("<img src='kr/images/tinybutton-show.gif' alt='show/hide panel' title='show/hide panel' width='45' height='15' border='0' align='absmiddle'>");
                    }
                );
   </script>
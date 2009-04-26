<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<script language="JavaScript" src="scripts/en-common.js"></script>
<script language="JavaScript" src="scripts/cal2.js">
    /*
    Xin's Popup calendar script-  Xin Yang (http://www.yxscripts.com/) Script
    featured on/available at http://www.dynamicdrive.com/
    This notice must stay intact for use */
</script>
<script language="JavaScript" src="scripts/cal_conf2.js"></script>
<script language="JavaScript" src="scripts/rule-common.js"></script>
<kul:page headerTitle="Rule QuickLinks" transactionalDocument="false"
	showDocumentInfo="false" htmlFormAction="RuleQuickLinks" docTitle="Rule QuickLinks">	
<table width="95%" border=0 cellspacing=0 cellpadding=0 align="center">
	<c:forEach var="documentTypeStruct" items="${KualiForm.documentTypeQuickLinksStructures}" varStatus="docStatus">
	<c:if test="${documentTypeStruct.shouldDisplay}">
		<c:set var="documentType" value="${documentTypeStruct.documentType}" />
		<tr>
			<c:choose>
				<c:when test="${docStatus.count == 1}">
					<td width=12><img src="images/tab-topleft.gif" alt="" width=12 height=29></td>
				</c:when>
				<c:otherwise>
					<td width=12><img src="images/tab-topleft1.gif" alt="" width=12 height=27></td>
				</c:otherwise>
			</c:choose>
            <td width=200 nowrap background="images/tab-back.gif">
              <table width="100%" border=0 cellspacing=0 cellpadding=0>
                <tr>
                		<c:choose>
                			<c:when test="${renderOpened}">
								<kul:htmlAttributeHeaderCell   scope="col" align="left"><a href="<c:url value="DocumentType.do">
								<c:param name="docTypeId" value="${documentType.documentTypeId}" />
								<c:param name="methodToCall" value="report"/>
								</c:url>"><c:out value="${documentType.label}" />&nbsp;
								</kul:htmlAttributeHeaderCell>	
								<kul:htmlAttributeHeaderCell  scope="col" align="right">
								<a id="A<c:out value="${docStatus.count}" />" onclick="rend(this, false)">
			                    <img src="images/tinybutton-hide.gif" alt="show" width=45 height=15 border=0
			                         align=absmiddle id="F<c:out value="${docStatus.count}" />"></a>
			                    </kul:htmlAttributeHeaderCell>	
							</c:when>
							<c:otherwise>
								<kul:htmlAttributeHeaderCell  scope="col" align="left"><a href="<c:url value="DocumentType.do">
								<c:param name="docTypeId" value="${documentType.documentTypeId}" />
								<c:param name="methodToCall" value="report"/>
								</c:url>"><c:out value="${documentType.label}" />
								&nbsp;
								</kul:htmlAttributeHeaderCell>	
								<kul:htmlAttributeHeaderCell  scope="col" align="right">
								<a id="A<c:out value="${docStatus.count}" />" onclick="rend(this, false)">
			                         <img src="images/tinybutton-show.gif" alt="show" width=45 height=15 border=0
			                         align=absmiddle id="F<c:out value="${docStatus.count}" />"></a>
			                    </kul:htmlAttributeHeaderCell>			                      
							</c:otherwise>
						</c:choose>
					</a>
                </tr>
              </table>
            </td>
			<c:choose>
				<c:when test="${docStatus.count == 1}">
        		    <td width=15><img src="images/tab-bevel.gif" alt="" width=15 height=29></td>
					<td width="95%" align=right valign=top background="images/tab-rightback.gif"><img src="images/tab-topright.gif" alt="" width=20 height=29 align=top></td>
				</c:when>
				<c:otherwise>
		            <td width=15><img src="images/tab-bevel1.gif" alt="" width=15 height=27></td>
		            <td width="95%" align=right background="images/tab-rightback1.gif"><img src="images/tab-topright1.gif" alt="" width=20 height=27></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<c:choose>
			<c:when test="${renderOpened}">
				<tr id="G<c:out value="${docStatus.count}" />">
					<td colspan=4>
						<table width=100% cellspacing=0 cellpadding=0>
							<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
							<td colspan="4" style="border-style: solid; border-width: thin; ">
								<c:set var="documentTypeStruct" value="${documentTypeStruct}" scope="request"/>
								<c:set var="excludeDocId" value="${documentType.documentTypeId}" scope="request" />
								<c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
							</td>
							<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>		                    
						</table>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr id="G<c:out value="${docStatus.count}" />" style="display:none">
					<td colspan=4>
						<table width=100% cellspacing=0 cellpadding=0>
							<td width=8 class="bordercell-left"><img src="images/pixel_clear.gif" alt="" width=8 height=8></td>
							<td colspan="4" style="border-style: solid; border-width: thin; ">
								<c:set var="documentTypeStruct" value="${documentTypeStruct}" scope="request"/>
								<c:set var="excludeDocId" value="${documentType.documentTypeId}" scope="request" />
								<c:import url="RuleQuickLinksDocumentTypeLinks.jsp" />
							</td>		                    
						</table>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>		
	</c:if>
	</c:forEach>
	<tr>
		<td colspan=4>			
				<%-- Page Footer --%>
		        <table width="100%" border=0 cellpadding=0 cellspacing=0 background="images/tabfoot-back.gif">
		          <tr>
		            <td><img src="images/tabfoot-left.gif" alt="" width=12 height=14></td>
		            <td>&nbsp;</td>
		            <td align=right><img src="images/tabfoot-right.gif" alt="" width=12 height=14></td>
		          </tr>
		        </table>
        </td>
     </tr>
</table>

</kul:page>
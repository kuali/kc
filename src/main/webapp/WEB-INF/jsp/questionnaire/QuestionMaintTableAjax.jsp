<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ page language="java" %> 
<c:set var="qidx" value="${KualiForm.qidx}"  scope="request"/>
<c:set var="moveupstyle" value=""  scope="request"/>
<c:set var="movednstyle" value=""  scope="request"/>
<c:if test="${KualiForm.moveup == '0'}">
    <c:set var="moveupstyle" value="display: none;"  scope="request"/>
</c:if>
<c:if test="${KualiForm.movedn == '0'}">
    <c:set var="movednstyle" value="display: none;"  scope="request"/>
</c:if>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Questionnaire Ajax for questionnaire question maintain table UI html</title>

</head>

<body>
<html:form styleId="kualiForm" method="post"
    action="/maintenanceQn.do" enctype=""
    onsubmit="return hasFormAlreadyBeenSubmitted();">
	<h3>
	<%--<div style="margin-top: 2px; display: block;" class="hierarchydetail"
		id="listcontent${qidx}"> --%>
	<table width="100%" cellspacing="0" cellpadding="0" class="subelement">
		<thead>
			<tr>
				<th align="left"
					style="background: none repeat scroll 0% 0% rgb(147, 147, 147); height: 18px; color: rgb(255, 255, 255); text-align: left; padding-left: 4px;">
					<%--<a
					class="hidedetail" href="#"><img height="15" width="45"
					border="0" align="absmiddle"
					src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif"></a> --%>${KualiForm.question.question}(${KualiForm.question.sequenceNumber})</th>
			</tr>
		</thead>
		<tbody>
		  <c:if test="${not KualiForm.readOnly}">
			<tr>
				<td class="subelementcontent">
				<table width="100%" cellspacing="0" cellpadding="0"
					class="elementtable" id="tbl80${qidx}">
					<tbody>
						<tr>
							<th style="text-align: right; width: 80px;">Node:</th>
							<td><a href="#" id="moveup${qidx}" style="${moveupstyle}" onClick="clickMoveup(${qidx})" >
							        <img type="image" title="Move up" alt="move up" 
								          src="static/images/jquery/arrow-up.gif">
								</a>
								<a href="#" id="movedn${qidx}" style="${movednstyle}" onClick="clickMovedn(${qidx})">
								    <img type="image" title="Move Down" alt="move down" 
								        src="static/images/jquery/arrow-down.gif">
							    </a>
								<a href="#" id="remove${qidx}" onClick="clickRemove(${qidx})">
								    <img height="15" width="79" border="0" title="Remove this node and its child groups/sponsors"
								        alt="Remove Node" src="static/images/tinybutton-removenode.gif">
								</a>
								<a href="#" id="cut${qidx}" onClick="clickCut(${qidx})">
								    <img height="15" width="79" border="0" title="Cut this node and its child groups/sponsors (Node will not be removed until you paste it)"
								         alt="Cut Node" src="static/images/tinybutton-cutnode.gif">
								</a>
								<a href="#" id="copy${qidx}" onClick="clickCopy(${qidx})">
								    <img height="15" width="79" border="0" title="Copy this node and its child" alt="Copy Node"
								         src="static/images/jquery/tinybutton-copynode.gif">
								</a>
								<a href="#" id="paste${qidx}" onClick="clickPaste(${qidx})">
								    <img height="15" width="79" border="0" title="Paste your previously cut node structure under this node"
								        alt="Paste Node" src="static/images/tinybutton-pastenode.gif">
								</a>
                                <a href="#" id="modify${qidx}" onClick="clickModify(${qidx})">
                                    <img height="15" width="79" border="0" title="Modify the current contents of this node"
                                        alt="Modify Node" src="static/images/tinybutton-modifynode.gif">
                                </a>
                                <c:if test="${not KualiForm.questionCurrentVersion}">
	                                <a href="#" id="update${qidx}" onClick="clickUpdateQuestionVersion(${qidx})">
	                                    <img height="15" width="79" border="0" title="Update the current version of this node"
	                                        alt="Update Version" src="static/images/tinybutton-updateversion.gif">
	                                </a>
                                </c:if>								
						    </td>
							<th style="text-align: right;">Add Question:</th>
							<td>
							<table cellspacing="0" cellpadding="0"
								style="border: medium none; width: 100%;" id="tbl95${qidx}">
								<tbody>
									<tr>
										<td style="border: medium none; width: 170px;">
										<table cellspacing="0" cellpadding="0"
											style="border: medium none; width: 100%;">
											<tbody>
												<tr>
													<td style="border: medium none;"><input type="radio"
														value="sibling" checked="checked" name="radioQn${qidx}"
														class="radioQn${qidx}"><span>as
													sibling&nbsp;&nbsp;&nbsp;</span></td>
													<td style="border: medium none;"><input type="radio"
														value="child" name="radioQn${qidx}" class="radioQn${qidx}"><span>as
													child</span></td>
												</tr>
											</tbody>
										</table>
										</td>
										<td style="border: medium none;"><input type="text"
											readonly="true" value="" size="50" name="newqdesc"
											id="newqdesc${qidx}"></td>
										<td
											style="border: medium none; width: 30px; text-align: center;"><input
											type="hidden" name="newqid${qidx}" id="newqid${qidx}"><input
											type="hidden" name="newqtypeid${qidx}" id="newqtypeid${qidx}"><input
											type="hidden" name="newqvers${qidx}" id="newqvers${qidx}"><input
											type="hidden" name="newqdispans${qidx}" id="newqdispans${qidx}"><input
											type="hidden" name="newqmaxans${qidx}" id="newqmaxans${qidx}"><input
											type="hidden" name="newqmaxlength${qidx}" id="newqmaxlength${qidx}"><a
											href="#"><img border="0" title="Search Question"
											alt="Search Question" class="tinybutton" name="search${qidx}"
											id="search${qidx}" src="static/images/searchicon.gif"  onClick="clickSearch(${qidx})"></a></td>
										<td
											style="border: medium none; width: 30px; text-align: center;"><input
											type="image" alt="add" style="border: medium none;"
											src="${ConfigProperties.kr.externalizable.images.url}tinybutton-add1.gif"
											name="addquestionnaire" id="addQn${qidx}"  title="Add a question" onClick="clickAdd(${qidx}); return false;"></td>
									</tr>
								</c:if>	
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="0"
					style="border-top: 1px solid rgb(228, 227, 228);">
					<tbody>
						<tr>
							 <th colspan="3"
								style="background: none repeat scroll 0% 0% rgb(147, 147, 147); height: 18px; color: rgb(255, 255, 255); text-align: left; padding-left: 4px;">
								<img
								  style="width: 45px; height: 15px; border: medium none; cursor: pointer; padding: 2px; vertical-align: middle;"
								  title="show/hide this panel" alt="show/hide this panel"
								  src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" id="HSReqcontrol${qidx}">Requirements for Display
							</th>
						</tr>
					</tbody>
				</table>
				<div id="HSReqdiv${qidx}">
				    <c:choose>
				        <c:when test="${KualiForm.childNode == 'true'}">
				            <kra-questionnaire:qnQuestionResponse response="${KualiForm.response}" value = "${KualiForm.value}"/>
				        </c:when>
				        <c:otherwise>
                           There can be no Requirements for Display on root-level questions.				
				        </c:otherwise>
				    </c:choose>
				</div>
				<table width="100%" cellspacing="0" cellpadding="0"
					style="border-top: 1px solid rgb(228, 227, 228);">
					<tbody>
						<tr>
							<th colspan="3"
								style="background: none repeat scroll 0% 0% rgb(147, 147, 147); height: 18px; color: rgb(255, 255, 255); text-align: left; padding-left: 4px;"><img
								style="width: 45px; height: 15px; border: medium none; cursor: pointer; padding: 2px; vertical-align: middle;"
								title="show/hide this panel" alt="show/hide this panel"
								src="${ConfigProperties.kr.externalizable.images.url}tinybutton-hide.gif" id="HScontrol${qidx}">Response</th>
						</tr>
					</tbody>
				</table>
				<div id="HSdiv${qidx}">
				      <kra-questionnaire:qnQuestionType question="${KualiForm.question}" />
				</div>
				</td>
			</tr>
		</tbody>
	</table>
	<%-- </div> --%>
	</h3>
</html:form>
</body>
</html>	
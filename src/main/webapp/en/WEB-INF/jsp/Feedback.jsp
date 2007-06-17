<%@ taglib uri="../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../tld/c.tld" prefix="c" %>
<%@ taglib uri="../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../tld/displaytag.tld" prefix="display-el" %>

<html>

	<head>
		<title>
			Feedback
		</title>
		<link href="css/screen.css" rel="stylesheet" type="text/css">

    <script language="JavaScript" src="scripts/feedback.js"></script>

	</head>
	<body>

		<table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
			<tr>
				<td>
					<img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5>
				</td>
			</tr>
		</table>

		<br>
		<jsp:include page="WorkflowMessages.jsp" flush="true" />

		<html-el:form action="Feedback.do">

			<table width="100%" border=0 cellspacing=0 cellpadding=0>
				<tr>
					<td width="20" height="30">
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td width="20">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">

							<!-- fixed-value properties for Falcon contact - not available in Feedback Form -->
							<!-- properties available in Feedback Form -->
							<html-el:hidden name="FeedbackForm" property="timeDate" />
							<html-el:hidden name="FeedbackForm" property="pageUrl" />
							<html-el:hidden name="FeedbackForm" property="exception" />
							<html-el:hidden name="FeedbackForm" property="methodToCall" />
							<html-el:hidden name="FeedbackForm" property="networkId" />
							<html-el:hidden name="FeedbackForm" property="userEmail" />
							<html-el:hidden name="FeedbackForm" property="userName" />

							<tr>
								<td align="right" valign="top" nowrap class="thnormal">
									Name:
								</td>
								<td class="datacell">
								  <c:out value="${FeedbackForm.userName}" default=""/>
      							</td>
  							</tr>

							<tr>
								<td align="right" valign="top" nowrap class="thnormal">
									E-mail:
								</td>
								<td class="datacell">
									<c:out value="${FeedbackForm.userEmail}" default="" />
							    </td>
  							</tr>

							<tr>
								<td align="right" valign="top" nowrap class="thnormal">
									Phone:
								</td>
								<td class="datacell">
									<html-el:text name="FeedbackForm" property="phone" />
								</td>
							</tr>

							<tr>
								<td align="right" valign="top" nowrap class="thnormal">
									Document Id:
								</td>
								<td class="datacell">

									<!-- Document Id in Request -->
									<c:choose>
										<c:when test="${FeedbackForm.routeHeaderId != ''}">
											<c:out value="${FeedbackForm.routeHeaderId}" />
											<html-el:hidden name="FeedbackForm" property="routeHeaderId" />
										</c:when>
            							<c:otherwise>
              								<html-el:text name="FeedbackForm" property="routeHeaderId" />
            							</c:otherwise>
          							</c:choose>
    							</td>
  							</tr>

							<tr>
								<td align="right" valign="top" nowrap class="thnormal">
									Document Type:
								</td>
								<td class="datacell">

									<!-- Document Type in Request -->
									<c:choose>
										<c:when test="${FeedbackForm.documentType != ''}">
											<c:out value="${FeedbackForm.documentType}" />
											<html-el:hidden name="FeedbackForm" property="documentType" value="${FeedbackForm.documentType}" />
										</c:when>

              
            <c:otherwise>
              <html-el:text name="FeedbackForm" property="documentType" value="" />
            </c:otherwise>
          </c:choose>
    </td>
  </tr>
  <tr>
    <td align="right" valign="top" nowrap class="thnormal">Category:</td>
    <td nowrap class="datacell">
          <html-el:select name="FeedbackForm" property="edenCategory">
              <html-el:option value="suggestion">Suggestion</html-el:option>
              <html-el:option value="problem">Problem or Technical Question</html-el:option>
          </html-el:select>
    </td>
  </tr>

							<tr>
								<td align="right" valign="top" nowrap class="thnormal">
									Comments:
								</td>
								<td nowrap class="datacell">
									<html-el:textarea name="FeedbackForm" property="comments" cols="23" rows="4"></html-el:textarea>
								</td>
							</tr>
							<logic-el:present name="FeedbackForm" property="exception">
								<tr>
									<td align="right" valign="top" nowrap class="thnormal">
										Exception:
									</td>
									<td class="datacell">
										<c:out value="${FeedbackForm.exception}" />
									</td>
								</tr>
							</logic-el:present>

							<tr>
								<td class="thnormal" colspan="2" align="center">
									<div align="center">
										<html-el:image property="methodToCall.sendFeedback" src="images/buttonsmall_submit.gif" align="absmiddle" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<html-el:img src="images/buttonsmall_cancel.gif" align="absmiddle" onclick="javascript:window.close()" />
									</div>
								</td>
							</tr>

						</table>

					</td>
					<td></td>
				</tr>
			</table>

		</html-el:form>

	</body>
</html>



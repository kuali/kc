<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%@ attribute name="node" required="true" type="org.kuali.kra.medusa.MedusaNode"%>
  <table style="border: 1px solid rgb(147, 147, 147); padding: 0px; width: 97%; border-collapse: collapse;">
    <tr>
      <th colspan="4" style="border-style: solid; text-align: left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Award ${node.bo.awardNumber}</th>
    </tr>
    <tr>
      <td style="text-align: center;" colspan="4">
	  <a href="${ConfigProperties.application.url}/awardHome.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.awardDocument.documentNumber}&medusaOpenedDoc=true"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Award" 
	          alt="Open Award" style="border: medium none ;" 
	          src="static/images/tinybutton-openaward.gif"/>
	  </a>      
	  <a href="${ConfigProperties.application.url}/awardNotesAndAttachments.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.awardDocument.documentNumber}&medusaOpenedDoc=true&tabStates(Notes)=OPEN#Notes"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Award Notes" 
	          alt="Open Award Notes" style="border: medium none ;" 
	          src="static/images/tinybutton-notes.gif"/>
	  </a> 
	  <a href="${ConfigProperties.application.url}/awardActions.do?methodToCall=docHandler&command=displayDocSearchView&docId=${node.bo.awardDocument.documentNumber}&medusaOpenedDoc=true&tabStates(Hierarchy Actions)=OPEN#Hierarchy Actions"
	     target="_blank" class="medusaOpenLink">
	    <img title="Open Award Hierarchy Actions" 
	          alt="Open Award Notes" style="border: medium none ;" 
	          src="static/images/tinybutton-hier_actions.jpg"/>
	  </a> 	 	  
      </td>
    </tr>
    <tr>
      <th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Summary</th>
    </tr>
    <tr>
      <th style="text-align: right;">Award ID:</th>
      <td><c:out value="${node.bo.awardNumber}"/></td>
      <th style="text-align: right;">Award Type:</th>
      <td><c:out value="${node.bo.awardType.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Sponsor Award ID:</th>
      <td><c:out value="${node.bo.sponsorAwardNumber}"/></td>
      <th style="text-align: right;">Activity Type:</th>
      <td><c:out value="${node.bo.activityType.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Account ID:</th>
      <td><c:out value="${node.bo.accountNumber}"/></td>
      <th style="text-align: right;">Account Type:</th>
      <td><c:out value="${node.bo.accountTypeDescription}"/></td>
    </tr>    
    <tr>
      <th style="text-align: right;">Award Status:</th>
      <td colspan="3"><c:out value="${node.bo.awardStatus.description}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Title:</th>
      <td colspan="3"><c:out value="${node.bo.title}"/></td>
    </tr>
    <tr>
      <th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Dates &amp; Amounts</th>
    </tr>
    <tr>
      <th style="text-align: right;">Sponsor:</th>
      <td colspan="3"><c:out value="${node.bo.sponsorCode} ${node.bo.sponsorName}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Project Start Date:</th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.bo.awardEffectiveDate}"/></td>
      <th style="text-align: right;">Obligation Start Date:</th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.extraInfo.currentFundEffectiveDate}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Project End Date:</th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.extraInfo.finalExpirationDate}"/></td>
      <th style="text-align: right;">Obligation End Date:</th>
      <td><fmt:formatDate pattern="MM/dd/yyyy" value="${node.extraInfo.obligationExpirationDate}"/></td>
    </tr>
    <tr>
      <th style="text-align: right;">Anticipated Cumulative:</th>
      <td><fmt:formatNumber type="currency" value="${node.extraInfo.anticipatedTotalAmount}"/></td>
      <th style="text-align: right;">Obligated Cumulative:</th>
      <td><fmt:formatNumber type="currency" value="${node.extraInfo.amountObligatedToDate}"/></td>
    </tr>
    <tr>
      <th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Award Details Recorded</th>
    </tr>
    <tr>
      <th style="text-align: right;">Approved Subaward?</th>
      <td>${not empty node.bo.awardApprovedSubawards ? "Yes" : "No"}</td>
      <th style="text-align: right;">Payment Schedule?</th>
      <td>${not empty node.bo.paymentScheduleItems ? "Yes" : "No"}</td>
    </tr>
    <tr>
      <th style="text-align: right;">Approved Equipment?</th>
      <td>${not empty node.bo.approvedEquipmentItems ? "Yes" : "No"}</td>
      <th style="text-align: right;">Sponsor Funding Transferred?</th>
      <td>${not empty node.bo.awardTransferringSponsors ? "Yes" : "No"}</td>
    </tr>
    <tr>
      <th style="text-align: right;">Approved Foreign Travel?</th>
      <td>${not empty node.bo.approvedForeignTravelTrips ? "Yes" : "No"}</td>
      <th style="text-align: right;">Cost Share?</th>
      <td>${not empty node.bo.awardCostShares ? "Yes" : "No"}</td>
    </tr>
    <tr>
      <th style="text-align: right;">F&A?</th>
      <td colspan="3">${not empty node.bo.awardFandaRate ? "Yes" : "No"}</td>
    </tr>
    <tr>
      <th colspan="4" style="border-style: solid; text-align:left; border-color: rgb(230, 230, 230) rgb(147, 147, 147) rgb(147, 147, 147); border-width: 1px; padding: 3px; border-collapse: collapse; background-color: rgb(184, 184, 184); background-image: none;">Investigators</th>
    </tr>
    <tr>
      <th colspan="2">Investigators</th>
      <th colspan="2">Units</th>
    </tr>
    
      <tr>
        <td style="text-align: center;" colspan="2">
           <c:out value="${node.bo.principalInvestigatorName}"/> (Principal Investigator)
        </td>
        <td style="text-align: center;" colspan="2">
            <c:out value="${node.bo.leadUnit.unitNumber} : ${node.bo.leadUnit.unitName}"/>(Lead Unit)
            <br/>
        </td>
      </tr>
    <c:forEach items="${node.bo.projectPersons}" var="person">
      <c:if test="${!person.principalInvestigator}">
      <tr>
        <td style="text-align: center;" colspan="2">
            <c:out value="${person.fullName}"/>
        </td>
        <td style="text-align: center;" colspan="2">
          <c:forEach items="${person.units}" var="unit">
            <c:out value="${unit.unitNumber} : ${unit.unitName}"/>
            <br/>
          </c:forEach>
        </td>
      </tr>
      </c:if>
    </c:forEach>
  </table>

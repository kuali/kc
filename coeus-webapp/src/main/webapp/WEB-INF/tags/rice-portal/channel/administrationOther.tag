<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<channel:portalChannelTop channelTitle="Other" />
<div class="body">
<strong>Shared</strong>
<ul class="chan">
  <li>Activity Type</li>
  <li><portal:portalLink displayTitle="true" title="Special Review Approval Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.compliance.core.SpecialReviewApprovalType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Comment</li>
  <li>Cost Sharing Type</li>
  <li><portal:portalLink displayTitle="true" title="Credit Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.type.InvestigatorCreditType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Custom Attribute" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.custom.attr.CustomAttribute&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Custom Attribute Document" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Degree Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.person.attr.DegreeType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Exemption Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.compliance.exemption.ExemptionType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>IDC Rate Types</li>
  <li><portal:portalLink displayTitle="true" title="Institute Rate" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.budget.framework.rate.InstituteRate&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Notice of Opportunity" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.noo.NoticeOfOpportunity&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="NSF Science Code" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.NsfCode&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Organization" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.org.Organization&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Person Degree" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.person.attr.PersonDegree&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Person Document Type</li>  
  <li><portal:portalLink displayTitle="true" title="Proposal Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.type.ProposalType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Science Code</li>
  <li><portal:portalLink displayTitle="true" title="Special Review" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.SpecialReview&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Questions" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.ynq.Ynq&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Valid Special Review Approval" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.compliance.core.ValidSpecialReviewApproval&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Unit" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.unit.Unit&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
</ul>

<strong>Other</strong>
<ul class="chan">
  <li>Argument Values</li>
  <li>Closeout Type</li>
  <li>Coeus Module Names</li>
  <li><portal:portalLink displayTitle="true" title="Country" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.Country&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>EDI Enabled Sponsors</li>
  <li>Investigators Credit Type</li>
  <li>Negotiation Activity Type</li>
  <li>Negotiation Status</li>
  <li><portal:portalLink displayTitle="true" title="Organization Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.org.type.OrganizationTypeList&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Rule Functions</li>
  <li>Rule Functions Argument</li>
  <li>Rule Variables</li>
  <li>School Code</li>
  <li>Sponsor Contact Type</li>
  <li><portal:portalLink displayTitle="true" title="Sponsor Type" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.sponsor.SponsorType&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="State" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.bo.State&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li>Subcontract Status</li>
  <li>Training</li>
  <li>User Preference Variables</li>
  <li>User Administrator Type</li>
  <li>Valid Rates</li>
  <li><portal:portalLink displayTitle="true" title="Document Lock" url="${ConfigProperties.kr.url}/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.krad.document.authorization.PessimisticLock&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true&docFormKey=88888888" /></li>
  <li><portal:portalLink displayTitle="true" title="Unit Hierarchy" url="unitHierarchy.do" /></li>
</ul>
</div>
<channel:portalChannelBottom />

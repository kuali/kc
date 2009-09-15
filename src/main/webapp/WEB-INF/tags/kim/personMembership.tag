<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="personAttributes" value="${DataDictionary.IdentityManagementPersonDocument.attributes}" />

<kul:tab tabTitle="Membership" defaultOpen="false" tabErrorKey="document.groups*,document.roles*,newGroup.*,newRole.*">
	<div class="tab-container" align="center">
		<kim:personGroup />
		<kim:personRole />
	</div>
</kul:tab>

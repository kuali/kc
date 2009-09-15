<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="personAttributes" value="${DataDictionary.IdentityManagementPersonDocument.attributes}" />

<kul:tab tabTitle="Contact" defaultOpen="false" tabErrorKey="document.names*,document.phones*,newName.*,newPhone.*,document.addrs*,newAddr.*,document.emails*,newEmail.*">
	<div class="tab-container" align="center">
		<kim:personName />
		<kim:personAddress />
		<kim:personPhone />
		<kim:personEmail />		
	</div>
</kul:tab>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="budgetCostSharingAttributes" value="${DataDictionary.BudgetCostSharing.attributes}" />

<kul:tab tabTitle="Unrecovered F&A (#)" defaultOpen="true" tabErrorKey="budget.projectIncome*">
	<div class="tab-container" align="center">
		<div class="h2-container">
			<span class="subhead-left"><h2>Unrecovered F&A Distribution List</h2></span>
			<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
		</div>
	</div>
</kul:tab>
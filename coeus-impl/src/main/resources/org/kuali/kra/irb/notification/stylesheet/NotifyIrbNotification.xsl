<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:variable name="documentNumber">
			<xsl:value-of select="Protocol/ProtocolMasterData/DocumentNumber" />
		</xsl:variable>

		The IRB protocol number
		<a title="" target="_self"
			href="../protocolProtocol.do?viewDocument=false$amp;docId={$documentNumber}$amp;submissionId=$amp;docTypeName=ProtocolDocument$amp;methodToCall=docHandler$amp;command=displayDocSearchView">
			<xsl:value-of select="Protocol/ProtocolMasterData/ProtocolNumber" />
		</a>

		, Principal Investigator
		<xsl:for-each select="Protocol/Investigator[PI_flag = 'true']">
			<xsl:value-of select="Person/Firstname" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="Person/LastName" />
		</xsl:for-each>
		has had the action "Notify IRB" performed on it.
		<br />
		The action was executed by
		<xsl:value-of select="Protocol/user/firstName" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="Protocol/user/lastName" />. 
		<br />
		The Submission Type Qualifier is '<xsl:value-of select="Protocol/Submissions/SubmissionDetails/SubmissionTypeQualifierDesc" />'.
		<br />
		The Submission Review Type is '<xsl:value-of select="Protocol/Submissions/SubmissionDetails/ProtocolReviewTypeDesc" />'.
		<br />
		The Committee name is '<xsl:value-of select="Protocol/Submissions/CommitteeMasterData/CommitteeName" />'.
		<br />
		The comment on the action is '<xsl:value-of select="Protocol/Submissions/SubmissionDetails/ActionType/ActionComments" />'.
		<br />
		Additional information and further actions can be accessed through the Kuali Coeus system.
		<br />
	</xsl:template>
</xsl:stylesheet>
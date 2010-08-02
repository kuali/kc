<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:for-each select="protocols/protocol">
			<xsl:variable name="documentNumber">
				<xsl:value-of select="documentNumber" />
			</xsl:variable>

			The IRB protocol number
			<a title="" target="_self"
				href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={$documentNumber}">
				<xsl:value-of select="protocolNumber" />
			</a>
			, Principal Investigator
			<xsl:value-of select="pi/firstName" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="pi/lastName" />
			has had the action "Request For Data Analysis" performed on it. 
			<br />
			The action was executed by 
						<xsl:value-of select="user/firstName" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="user/lastName" />. Additional information and further actions can be accessed through the Kuali Coeus system. 
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>

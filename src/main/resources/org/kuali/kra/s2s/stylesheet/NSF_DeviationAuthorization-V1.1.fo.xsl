<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:NSF_DeviationAuthorization="http://apply.grants.gov/forms/NSF_DeviationAuthorization-V1.1" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<!--    -->
	<xsl:param name="l10n.gentext.default.language" select="'en'"/>
	<!--   -->
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.34in" margin-right="0.34in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="8pt"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	
	<xsl:template match="NSF_DeviationAuthorization:NSF_DeviationAuthorization">
		<fo:root>
			<!-- -->
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					
						<fo:table width="100%">
						<fo:table-column width="50%"/>
						<fo:table-column width="50%"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell line-height="11pt" display-align="before">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell line-height="11pt" display-align="after" text-align="right" >
									<fo:block>
										<fo:inline text-align="right" font-size="6px" >OMB Number 3145-0058</fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<!-- -->
				<!-- -->
				<fo:flow flow-name="xsl-region-body">
					<fo:block text-align="center" font-family="Helvetica,Times,Courier" font-size="11pt" font-weight="bold">Deviation Authorization (If Applicable)</fo:block>
					<fo:table width="100%">
						<fo:table-column/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block>&#160;</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<!-- -->
							<fo:table-row font-size="8pt">
								<fo:table-cell font-weight="bold">
									<fo:block>Enter text for the Deviation Authorization information in the box below (if applicable):</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row font-size="8pt">
								<fo:table-cell   border-style="solid" border-color="grey" hyphenate="true">
									<fo:block><xsl:value-of select="NSF_DeviationAuthorization:DeviationAuthorization"/></fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>

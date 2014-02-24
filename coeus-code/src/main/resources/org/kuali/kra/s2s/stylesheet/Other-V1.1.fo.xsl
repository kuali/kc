<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:Other="http://apply.grants.gov/forms/Other-V1.1" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
				<fo:region-body margin-top="0.79in" margin-bottom="0.79in" font-family="Helvetica,Times,Courier" font-size="14pt"/>
				<fo:region-after extent=".79in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="Other:OtherNarrativeAttachments">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">
   Tracking Number: 
  <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<fo:block>
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<fo:block space-before.optimum="1pt" space-after.optimum="2pt">
							<fo:block>
								<fo:inline font-size="inherited-property-value(&apos;font-size&apos;) + 4pt" font-weight="bold">Other Attachment File(s)</fo:inline>
							</fo:block>
						</fo:block>
						<fo:block>
							<fo:leader leader-pattern="space"/>
						</fo:block>
						<xsl:for-each select="Other:Attachments">
							<xsl:for-each select="att:AttachedFile">
								<xsl:if test="position()=1">
									<fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
										<fo:table-column/>
										<fo:table-column/>
										<fo:table-header>
											<fo:table-row>
												<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
													<fo:block>
														<fo:inline font-weight="bold">FileName</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
													<fo:block>
														<fo:inline font-weight="bold">MimeType</fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-header>
										<fo:table-body>
											<xsl:for-each select="../att:AttachedFile">
												<fo:table-row>
													<fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
														<fo:block>
															<xsl:for-each select="att:FileName">
																<xsl:apply-templates/>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
													<fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black" hyphenate="true">
														<fo:block>
															<xsl:for-each select="att:MimeType">
																<xsl:apply-templates/>
															</xsl:for-each>
														</fo:block>
													</fo:table-cell>
												</fo:table-row>
											</xsl:for-each>
										</fo:table-body>
									</fo:table>
								</xsl:if>
							</xsl:for-each>
						</xsl:for-each>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>

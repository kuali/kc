<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
xmlns:Attachments="http://apply.grants.gov/forms/Attachments-V1.1" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.34in" margin-right="0.34in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="8pt"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="Attachments:Attachments">
		<fo:root>
			<!-- -->
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<!-- -->
				<!-- -->
				<fo:flow flow-name="xsl-region-body">
				
					<fo:table width="100%">	
<fo:table-column column-width="0.2in"/>
<fo:table-column/>
<fo:table-column column-width="0.2in"/>
						<fo:table-body>				
													<fo:table-row>
	<fo:table-cell width="0.2in"><fo:block>&#160;</fo:block></fo:table-cell><fo:table-cell width="9.0in">
	<fo:block text-align="center" font-family="Helvetica,Times,Courier" font-size="11pt" font-weight="bold">
        Attachments Form</fo:block>
        <fo:block>&#160;</fo:block>
         <fo:block font-size="8pt" hyphenate="true">
       <fo:inline font-weight="bold" font-size="8pt">Instructions:</fo:inline>  On this form, you will attach the various files that make up your grant application.  Please consult with the appropriate Agency Guidelines for more information about each needed file.  Please remember that any files you attach must be in the document format and named as specified in the Guidelines.
</fo:block>
<fo:block line-height="4pt">&#160;</fo:block>
<fo:block font-size="8pt" hyphenate="true"><fo:inline font-weight="bold" font-size="8pt">Important:</fo:inline>  Please attach your files in the proper sequence.  See the appropriate Agency Guidelines for details.
					</fo:block>
					        <fo:block>&#160;</fo:block>
</fo:table-cell>
<fo:table-cell width="0.2in"><fo:block>&#160;</fo:block></fo:table-cell>
</fo:table-row>
</fo:table-body>
</fo:table>
					<fo:table width="100%">
						<fo:table-column/>
						<fo:table-body>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">1) Please attach Attachment 1</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT1/Attachments:ATT1File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT1/Attachments:ATT1File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">2) Please attach Attachment 2</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT2/Attachments:ATT2File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT2/Attachments:ATT2File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">3) Please attach Attachment 3</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT3/Attachments:ATT3File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT3/Attachments:ATT3File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">4) Please attach Attachment 4</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT4/Attachments:ATT4File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT4/Attachments:ATT4File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">5) Please attach Attachment 5</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT5/Attachments:ATT5File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT5/Attachments:ATT5File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">6) Please attach Attachment 6</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT6/Attachments:ATT6File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT6/Attachments:ATT6File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">7) Please attach Attachment 7</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT7/Attachments:ATT7File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT7/Attachments:ATT7File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">8) Please attach Attachment 8</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT8/Attachments:ATT8File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT8/Attachments:ATT8File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">9) Please attach Attachment 9</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT9/Attachments:ATT9File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT9/Attachments:ATT9File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">10) Please attach Attachment 10</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT10/Attachments:ATT10File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT10/Attachments:ATT10File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">11) Please attach Attachment 11</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT11/Attachments:ATT11File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT11/Attachments:ATT11File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">12) Please attach Attachment 12</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT12/Attachments:ATT12File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT12/Attachments:ATT12File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">13) Please attach Attachment 13</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT13/Attachments:ATT13File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT13/Attachments:ATT13File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">14) Please attach Attachment 14</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT14/Attachments:ATT14File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT14/Attachments:ATT14File/att:MimeType"/>
							</xsl:call-template>
							<xsl:call-template name="attach_block">
								<xsl:with-param name="block_num"></xsl:with-param>
								<xsl:with-param name="block_title">15) Please attach Attachment 15</xsl:with-param>
								<xsl:with-param name="filename">
									<xsl:value-of select="Attachments:ATT15/Attachments:ATT15File/att:FileName"/>
								</xsl:with-param>
								<xsl:with-param name="mimetype" select="Attachments:ATT15/Attachments:ATT15File/att:MimeType"/>
							</xsl:call-template>
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="attach_block">
		<xsl:param name="block_num"/>
		<xsl:param name="block_title"/>
		<xsl:param name="filename"/>
		<xsl:param name="mimetype"/>
		<xsl:element name="fo:table-row">
			<xsl:element name="fo:table-cell">
				<xsl:attribute name="font-size">8pt</xsl:attribute>
				<xsl:element name="fo:table">
					<xsl:attribute name="border-color">black</xsl:attribute>
					<xsl:attribute name="width">100%</xsl:attribute>
					<xsl:element name="fo:table-column"/>
					<xsl:element name="fo:table-body">
						<xsl:element name="fo:table-row">
							<xsl:element name="fo:table-cell">
								<xsl:element name="fo:table">
									<xsl:attribute name="width">100%</xsl:attribute>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">0.2in</xsl:attribute>
									</xsl:element>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">2.0in</xsl:attribute>
									</xsl:element>
									<xsl:element name="fo:table-column">
										<xsl:attribute name="column-width">2.0in</xsl:attribute>
									</xsl:element>
									<fo:table-column column-width="2.0in"/>
									<xsl:element name="fo:table-body">
										<xsl:element name="fo:table-row">
											<xsl:element name="fo:table-cell">
											<xsl:attribute name="line-height">15pt</xsl:attribute>
												<xsl:attribute name="hyphenate">true</xsl:attribute>
												<xsl:attribute name="font-weight">bold</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$block_num"/>
												</xsl:element>
											</xsl:element>
											<xsl:element name="fo:table-cell">
											<xsl:attribute name="line-height">15pt</xsl:attribute>
											<xsl:attribute name="hyphenate">true</xsl:attribute>
												<xsl:attribute name="font-weight">bold</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$block_title"/>
												</xsl:element>
											</xsl:element>
											<xsl:element name="fo:table-cell">
											<xsl:attribute name="line-height">15pt</xsl:attribute>
											<xsl:attribute name="hyphenate">true</xsl:attribute>
												<xsl:element name="fo:block">
													<xsl:value-of select="$filename"/>
												</xsl:element>
											</xsl:element>
											<fo:table-cell line-height="15pt" hyphenate="true">
												<fo:block>
													<xsl:if test=" $filename  != '' ">Mime Type: <xsl:value-of select="$mimetype"/>
													</xsl:if>
												</fo:block>
											</fo:table-cell>
										</xsl:element>
									</xsl:element>
								</xsl:element>
							</xsl:element>
						</xsl:element>
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- Block 7 End -->
	<!--                                     NEW NEW NEW NENW -->
</xsl:stylesheet>

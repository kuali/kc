<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:PHS_Inclusion_Enrollment_Report="http://apply.grants.gov/forms/PHS_Inclusion_Enrollment_Report-V1.0"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">

	<xsl:template match="PHS_Inclusion_Enrollment_Report:PHS_Inclusion_Enrollment_Report">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="first"
					page-height="8.5in" page-width="11in" margin-top="0.5in" margin-left="0.4in" 	margin-right="0.4in">
					<fo:region-body margin-top="0.8in" margin-bottom="0.3in" />
					<fo:region-before region-name="headerall" margin-top="0.5in" extent="0.3in" />
					<fo:region-after region-name="region-after-all" extent="0.3in" />
				</fo:simple-page-master>

				<fo:simple-page-master master-name="rest"
					page-height="8.5in" page-width="11in" margin-top="0.5in" margin-left="0.4in" 	margin-right="0.4in">
					<fo:region-body margin-top="0.8in" margin-bottom="0.2in" />
					<fo:region-before region-name="headerall" margin-top="0.5in"  extent="0.3in" />
					<fo:region-after region-name="region-after-all" extent="0.3in" />
				</fo:simple-page-master>

				<fo:page-sequence-master master-name="all-pages">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference
							master-reference="first" page-position="first" />
						<fo:conditional-page-master-reference
							master-reference="rest" page-position="rest" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>

			<!-- Study iteration -->
			<xsl:for-each select="PHS_Inclusion_Enrollment_Report:Study">
				<xsl:variable name="nodeCount" select="position()"/>
				<fo:page-sequence master-reference="first" format="1">

					<fo:static-content flow-name="headerall">
						<!-- Header PHS Inclusion Enrollment Report with the static text and OMB Number -->

						<fo:table width="100%" space-before.optimum="3pt"
							space-after.optimum="1pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="6" text-align="center" padding-start="2pt" 
										padding-end="2pt" padding-before="1pt" padding-after="1pt"
										display-align="center" border-style="solid" border-width="1pt"
										border-color="white">
										<fo:block font-size="12px" font-weight="bold">
											PHS Inclusion Enrollment Report
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								
								<fo:table-row>
									<fo:table-cell number-columns-spanned="6" text-align="center" padding-start="2pt"
										padding-end="2pt" padding-before="1pt" padding-after="1pt"
										display-align="center" border-style="solid" border-width="0pt"
										border-color="white">
										<fo:block font-size="9px" font-weight="bold">
											This report format should NOT be used for collecting data from study participants.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								
								<fo:table-row>
									<fo:table-cell number-columns-spanned="6"  hyphenate="true" language="en"
										line-height="0pt" padding-start="0pt" padding-end="0pt"
										padding-before="10pt" padding-after="0pt" display-align="before"
										text-align="right" border-style="solid" border-width="0pt"
										border-color="white">
										<fo:block>
											<fo:inline font-size="6px">OMB Number:0925-0001 and 0925-0002
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell number-columns-spanned="6"  hyphenate="true" language="en"
										line-height="0pt" padding-start="0pt" padding-end="0pt"
										padding-before="10pt" padding-after="0pt" display-align="before"
										text-align="right" border-style="solid" border-width="0pt"
										border-color="white">
										<fo:block>
											<fo:inline font-size="6px">Expiration Date: 10/31/2018
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
								<fo:table-cell padding="0" number-columns-spanned="6" height="30" display-align="center">
                                <fo:block>
                                    <fo:inline>
                                        <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</xsl:text>
                                    </fo:inline>
                                </fo:block>
								</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>
					
            <fo:static-content flow-name="region-after-all">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                              <fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                                     <fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>

					<fo:flow flow-name="xsl-region-body">
						<xsl:call-template name="incEnrollmentReport" />

						<!-- ============================ -->
						<!-- Study tags count -->
						<!-- ============================ -->
						<fo:block>
							<fo:table>
								<fo:table-column column-width="proportional-column-width(1)" />
								<fo:table-body>
									<fo:table-row height="10px">
										<fo:table-cell hyphenate="true" language="en"
											number-columns-spanned="1" padding-start="0pt" padding-end="0pt"
											padding-before="1pt" padding-after="1pt" display-align="center"
											text-align="center" border-style="solid" border-width="0pt"
											border-color="white">
											<fo:block>
												<fo:inline font-size="8px">
													Report <xsl:value-of select="$nodeCount"/> of <xsl:value-of select="count(//PHS_Inclusion_Enrollment_Report:PHS_Inclusion_Enrollment_Report/PHS_Inclusion_Enrollment_Report:Study)"/>
												</fo:inline>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-body>
							</fo:table>
						</fo:block>
					</fo:flow>
				</fo:page-sequence>
			</xsl:for-each>
		</fo:root>
	</xsl:template>

	<xsl:template name="incEnrollmentReport">
		<fo:block>
			<fo:table font-size="12pt" width="100%"
				space-before.optimum="1pt" space-after.optimum="3pt" table-layout="fixed"
				height="100%">
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-column column-width="proportional-column-width(1)" />

				<fo:table-body>
					<!-- ============================ -->
					<!-- Study Title -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">*Study Title:
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline  font-family="arialuni" font-size="9px">
									<xsl:value-of select="PHS_Inclusion_Enrollment_Report:StudyTitle" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
					<!-- ============================ -->
					<!-- Delayed Onset Study -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">*Delayed Onset Study?
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<!-- Below block is to get checkbox image Yes -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose> 
												<xsl:when test="not(PHS_Inclusion_Enrollment_Report:DelayedOnsetStudy) or PHS_Inclusion_Enrollment_Report:DelayedOnsetStudy = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkbox">
													<xsl:with-param name="value"
													select="PHS_Inclusion_Enrollment_Report:DelayedOnsetStudy" />
													<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end checboxY-->
												
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="20px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
									<fo:inline font-family="arialuni" font-size="9px">
										<xsl:text>Yes</xsl:text>
									</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
												
								<fo:block>
								<!-- Below block is to get checkbox image for No -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="50px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="50px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose> 
												<xsl:when
												test="not(PHS_Inclusion_Enrollment_Report:DelayedOnsetStudy) or (PHS_Inclusion_Enrollment_Report:DelayedOnsetStudy = '')">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxN">
													<xsl:with-param name="valueN" select="PHS_Inclusion_Enrollment_Report:DelayedOnsetStudy" />
													<xsl:with-param name="schemaChoiceN">N: No</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end -->
							<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="60px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
								<xsl:text>No</xsl:text>
								</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
					</fo:table-cell>
					</fo:table-row>
					
					<fo:table-row>
					<fo:table-cell number-columns-spanned="6"
								   padding="2pt" display-align="center">
							<fo:block>
									<fo:inline font-weight="bold" font-size="10px">
											<xsl:text>If study is not delayed onset, the following selections are required:</xsl:text>
									</fo:inline>
							</fo:block>
					</fo:table-cell>
					</fo:table-row>
					
					<!-- ============================ -->
					<!-- Enrollment Type -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Enrollment Type
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">

							<fo:block>
								<!-- Below block is to get checkbox image Planned -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when test="not(PHS_Inclusion_Enrollment_Report:EnrollmentType) or PHS_Inclusion_Enrollment_Report:EnrollmentType = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxEnrollTypeP">
													<xsl:with-param name="valueP"
													select="PHS_Inclusion_Enrollment_Report:EnrollmentType" />
													<xsl:with-param name="enrollTypePlanned">Planned</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end checboxY-->
												
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="20px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
									<fo:inline font-family="arialuni" font-size="9px">
										<xsl:text>Planned</xsl:text>
									</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
												
								<fo:block>
								<!-- Below block is to get checkbox image for Cumulative -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when
												test="not(PHS_Inclusion_Enrollment_Report:EnrollmentType) or PHS_Inclusion_Enrollment_Report:EnrollmentType = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxEnrollTypeC">
													<xsl:with-param name="valueC"
													select="PHS_Inclusion_Enrollment_Report:EnrollmentType" />
													<xsl:with-param name="enrollTypeCumulative">Cumulative (Actual)</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end -->
							<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="100px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
								<xsl:text>Cumulative (Actual)</xsl:text>
								</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
					<!-- ====================================== -->
					<!-- Using an Existing Dataset or Resource -->
					<!-- ====================================== -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Using an Existing Dataset or Resource
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">

							<fo:block>
								<!-- Below block is to get checkbox image Yes -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when test="not(PHS_Inclusion_Enrollment_Report:ExistingDatasetOrResource) or PHS_Inclusion_Enrollment_Report:ExistingDatasetOrResource = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkbox">
													<xsl:with-param name="value"
													select="PHS_Inclusion_Enrollment_Report:ExistingDatasetOrResource" />
													<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end checboxY-->
												
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="20px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
									<fo:inline font-family="arialuni" font-size="9px">
										<xsl:text>Yes</xsl:text>
									</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
												
								<fo:block>
								<!-- Below block is to get checkbox image for No -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when
												test="not(PHS_Inclusion_Enrollment_Report:ExistingDatasetOrResource) or PHS_Inclusion_Enrollment_Report:ExistingDatasetOrResource = 'Y: Yes'">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxN">
													<xsl:with-param name="valueN"
													select="PHS_Inclusion_Enrollment_Report:ExistingDatasetOrResource" />
													<xsl:with-param name="schemaChoiceN">N: No</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end -->
							<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="100px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
								<xsl:text>No</xsl:text>
								</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
					<!-- ============================ -->
					<!-- Enrollment Location -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Enrollment Location
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">

							<fo:block>
								<!-- Below block is to get checkbox image Domestic -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

												<xsl:choose>
												<xsl:when test="not(PHS_Inclusion_Enrollment_Report:EnrollmentLocation) or PHS_Inclusion_Enrollment_Report:EnrollmentLocation = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxEnrollLocD">
													<xsl:with-param name="valueD"
													select="PHS_Inclusion_Enrollment_Report:EnrollmentLocation" />
													<xsl:with-param name="enrollLocD">Domestic</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>

										</fo:block>
								</fo:block-container>
								<!-- end checboxY-->
												
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="20px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
									<fo:inline font-family="arialuni" font-size="9px">
										<xsl:text>Domestic</xsl:text>
									</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
												
								<fo:block>
								<!-- Below block is to get checkbox image for Foreign -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when
												test="not(PHS_Inclusion_Enrollment_Report:EnrollmentLocation) or PHS_Inclusion_Enrollment_Report:EnrollmentLocation = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxEnrollLocF">
													<xsl:with-param name="valueF"
													select="PHS_Inclusion_Enrollment_Report:EnrollmentLocation" />
													<xsl:with-param name="enrollLocF">Foreign</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>

										</fo:block>
								</fo:block-container>
								<!-- end -->
							<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="100px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
								<xsl:text>Foreign</xsl:text>
								</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
					<!-- ============================ -->
					<!-- Clinical Trial -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Clinical Trial
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white"> 
							<fo:block>
								<!-- Below block is to get checkbox image Yes -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when test="not(PHS_Inclusion_Enrollment_Report:ClinicalTrial) or PHS_Inclusion_Enrollment_Report:ClinicalTrial = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkbox">
													<xsl:with-param name="value"
													select="PHS_Inclusion_Enrollment_Report:ClinicalTrial" />
													<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>

										</fo:block>
								</fo:block-container>
								<!-- end checboxY-->
												
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="20px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
									<fo:inline font-family="arialuni" font-size="9px">
										<xsl:text>Yes</xsl:text>
									</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
												
								<fo:block>
								<!-- Below block is to get checkbox image for No -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when
												test="not(PHS_Inclusion_Enrollment_Report:ClinicalTrial) or PHS_Inclusion_Enrollment_Report:ClinicalTrial = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxN">
													<xsl:with-param name="valueN"
													select="PHS_Inclusion_Enrollment_Report:ClinicalTrial" />
													<xsl:with-param name="schemaChoiceN">N: No</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end -->
							<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="100px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
								<xsl:text>No</xsl:text>
								</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!-- ===================================== -->
					<!-- NIH-Defined Phase III Clinical Trial -->
					<!-- ===================================== -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">NIH-Defined Phase III Clinical Trial
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">

							<fo:block>
								<!-- Below block is to get checkbox image Yes -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="10px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when test="not(PHS_Inclusion_Enrollment_Report:PhaseIIIClinicalTrial) or PHS_Inclusion_Enrollment_Report:PhaseIIIClinicalTrial = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkbox">
													<xsl:with-param name="value"
													select="PHS_Inclusion_Enrollment_Report:PhaseIIIClinicalTrial" />
													<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>

										</fo:block>
								</fo:block-container>
								<!-- end checboxY-->
												
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="20px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
									<fo:inline font-family="arialuni" font-size="9px">
										<xsl:text>Yes</xsl:text>
									</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
												
								<fo:block>
								<!-- Below block is to get checkbox image for No -->
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always"
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">

										<xsl:choose>
											<xsl:when test="boolean(0)">
												<fo:inline color="#FFFFFF">&#160;</fo:inline>
											</xsl:when>
											<xsl:otherwise>
												<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
												</fo:inline>
											</xsl:otherwise>
										</xsl:choose>
									</fo:block>
								</fo:block-container>
								<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="90px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block background-color="transparent" color="#000000"
										font-size="8pt" font-style="normal" font-family="ZapfDingbats"
										font-weight="normal">
												<xsl:choose>
												<xsl:when
												test="not(PHS_Inclusion_Enrollment_Report:PhaseIIIClinicalTrial) or PHS_Inclusion_Enrollment_Report:PhaseIIIClinicalTrial = ''">
													<fo:inline color="#FFFFFF">&#160;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
													<xsl:call-template name="checkboxN">
													<xsl:with-param name="valueN"
													select="PHS_Inclusion_Enrollment_Report:PhaseIIIClinicalTrial" />
													<xsl:with-param name="schemaChoiceN">N: No</xsl:with-param>
												</xsl:call-template>
												</xsl:otherwise>
												</xsl:choose>
										</fo:block>
								</fo:block-container>
								<!-- end -->
							<fo:block-container background-color="transparent"
									border-style="none" position="absolute" left="100px"
									hyphenate="true" language="en" keep-together="always" 
									height="18.333333333333334px" width="15.303030303030303px">
									<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
								<xsl:text>No</xsl:text>
								</fo:inline>
								</fo:block>
								</fo:block-container>
								
								</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Comments -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Comments:
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="10" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-family="arialuni" font-size="9px">
									<xsl:value-of select="PHS_Inclusion_Enrollment_Report:Comments" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- Empty line -->
					<fo:table-row>
						<fo:table-cell>
							<fo:block>
								<fo:leader leader-pattern="space" />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Racial Categories, Ethnic Categories etc. -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Racial Categories -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block font-size="9px" font-weight="bold">Racial
								Categories
							</fo:block>
							<fo:block>
								<fo:table width="100%" space-before.optimum="0pt"
									space-after.optimum="0pt" table-layout="fixed">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												number-columns-spanned="1" padding-start="0pt" padding-end="0pt"
												padding-before="1pt" padding-after="0pt" display-align="before"
												text-align="left" border-style="solid" border-width="0pt"
												border-color="white">
												<fo:block font-size="8pt">
													<fo:leader leader-pattern="space" />
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>

						<!-- ============================ -->
						<!-- Ethnic Categories -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="10" padding-start="0pt"
							padding-end="0pt" padding-before="0pt" padding-after="0pt"
							display-align="center" text-align="center"
                            border-top-style="solid" border-top-width="1pt" border-top-color="black" 
                            border-right-style="solid" border-right-width="1pt" border-right-color="black">
							<fo:block>
                            	<fo:table>
                                	<fo:table-column column-width="90%" />
									<fo:table-column column-width="10%" />
                                    	<fo:table-body>
                                        	<fo:table-row>	
                                                <fo:table-cell>
                            						<fo:block font-size="9px" font-weight="bold">Ethnic	Categories</fo:block>
                            					</fo:table-cell>
                                               <fo:table-cell border-left-color="black" border-left-style="solid" border-left-width="1px" background-color="#EEEEEE">
                                               		<fo:block>&#160;</fo:block>
                            				</fo:table-cell>
                                         </fo:table-row>
                                     </fo:table-body>
                               	</fo:table>             
							</fo:block>
							<fo:block>
								<fo:table width="100%" space-before.optimum="0pt"
									space-after.optimum="0pt" table-layout="fixed">
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body>
										<fo:table-row>
											<!-- ============================ -->
											<!-- Not Hispanic or Latino -->
											<!-- ============================ -->
											<fo:table-cell hyphenate="true" language="en"
												padding-start="0pt" padding-end="0pt" padding-before="1pt"
												padding-after="0pt" display-align="center" text-align="center"
												border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                border-right-style="solid" border-right-width="1pt" border-right-color="black"		
												number-columns-spanned="3"> <fo:block>&#160;</fo:block> 
												<fo:block font-size="9px" font-weight="bold">Not
													Hispanic
													or Latino
												</fo:block>
                                              
												<fo:block>
													<fo:table width="100%" space-before.optimum="0pt"
														space-after.optimum="0pt" table-layout="fixed">
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
                            				 						border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"
                                                                    number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Female
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
                             										border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Male
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
                             										border-top-style="solid" border-top-width="1pt" border-top-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Unknown/Not
																		Reported
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
											<!-- ============================ -->
											<!-- Hispanic or Latino -->
											<!-- ============================ -->
											<fo:table-cell hyphenate="true" language="en"
												padding-start="0pt" padding-end="0pt" padding-before="1pt"
												padding-after="0pt" display-align="center" text-align="center"
												border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                border-right-style="solid" border-right-width="1pt" border-right-color="black"	
												number-columns-spanned="3"> <fo:block>&#160;</fo:block>
												<fo:block font-size="9px" font-weight="bold">Hispanic
													or
													Latino
												</fo:block>
                                               
												<fo:block>
													<fo:table width="100%" space-before.optimum="0pt"
														space-after.optimum="0pt" table-layout="fixed">
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
                             										border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Female
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
																	border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Male
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
																	border-top-style="solid" border-top-width="1pt" border-top-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Unknown/Not
																		Reported
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
											<!-- ============================ -->
											<!-- Unknown/Not Reported Ethnicity -->
											<!-- ============================ -->
											<fo:table-cell hyphenate="true" language="en"
												padding-start="0pt" padding-end="0pt" padding-before="1pt"
												padding-after="0pt" display-align="center" text-align="center"
                                                border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                border-right-style="solid" border-right-width="1pt" border-right-color="black"	
												number-columns-spanned="3">
												<fo:block font-size="9px" font-weight="bold">Unknown/Not</fo:block>
													<fo:block font-size="9px" font-weight="bold">Reported Ethnicity
												</fo:block>
												<fo:block>
													<fo:table width="100%" space-before.optimum="0pt"
														space-after.optimum="0pt" table-layout="fixed">
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
																	border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"																										
                                                					number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Female
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
                                                                    border-top-style="solid" border-top-width="1pt" border-top-color="black"
                                                                    border-right-style="solid" border-right-width="1pt" border-right-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Male
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell hyphenate="true" language="en"
																	padding-start="0pt" padding-end="0pt" padding-before="1pt"
																	padding-after="0pt" display-align="center" text-align="center"
                                                                    border-top-style="solid" border-top-width="1pt" border-top-color="black"	
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Unknown/Not
																		Reported
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
											<!-- ============================ -->
											<!-- Total -->
											<!-- ============================ -->
											<fo:table-cell hyphenate="true" language="en"
												padding-start="0pt" padding-end="0pt" padding-before="1pt"
												padding-after="0pt" display-align="center" text-align="center" 	
												number-columns-spanned="1" background-color="#EEEEEE">
												<fo:block font-size="9px" font-weight="bold">Total
												</fo:block>
												<fo:block>
													<fo:table width="100%" space-before.optimum="0pt"
														space-after.optimum="0pt" table-layout="fixed">
														<fo:table-column column-width="proportional-column-width(1)" />
														<fo:table-body>
															<fo:table-row>
																<fo:table-cell hyphenate="true" language="en"
																	number-columns-spanned="1" padding-start="0pt"
																	padding-end="0pt" padding-before="1pt" padding-after="0pt"
																	display-align="before" text-align="center"
																	border-style="solid" border-width="0pt" border-color="black">
																	<fo:block font-size="8pt">
																		<fo:leader leader-pattern="space" />
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
														</fo:table-body>
													</fo:table>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<!-- ============================ -->
					<!-- American Indian / Alaska Native..... First row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- American Indian / Alaska Native label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">American Indian/Alaska Native
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:AmericanIndian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Asian..... Second row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Asian label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">Asian
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:Asian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Native Hawaiian or Other Pacific Islander..... Third row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Native Hawaiian or Other Pacific Islander label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">Native Hawaiian or Other
									Pacific
									Islander
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:Hawaiian" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Black or African American..... Fourth row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Black or African American label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">Black or African American
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- White..... Fifth row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- White -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">White
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:White" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- More than One Race..... Sixth row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- More than One Race label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">More than One Race
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Unknown or Not Reported..... Seventh row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Unknown or Not Reported label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="8pt">Unknown or Not Reported
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:UnknownRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Total..... Eighth row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Unknown or Not Reported label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Total
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Not Hispanic or Latino/Unknown not Reported -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:NotHispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Hispanic or Latino/unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Hispanic/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Female -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Female/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/male -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:Male/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Unknown Not Reported Ethnicity/Unknown gender -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:UnknownEthnicity/PHS_Inclusion_Enrollment_Report:UnknownGender/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<!-- ============================ -->
						<!-- Total -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="1" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black" background-color="#EEEEEE">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">
									<xsl:value-of
										select="PHS_Inclusion_Enrollment_Report:Total/PHS_Inclusion_Enrollment_Report:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>
	
	<xsl:template name="checkbox">
		<xsl:param name="value" />
		<xsl:param name="schemaChoice">Y: Yes</xsl:param>
		<xsl:if test="normalize-space($value) = normalize-space($schemaChoice)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	
		<xsl:template name="checkboxN">
		<xsl:param name="valueN" />
		<xsl:param name="schemaChoiceN">N: No</xsl:param>
		<xsl:if test="normalize-space($valueN) = normalize-space($schemaChoiceN)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="checkboxEnrollTypeP">
		<xsl:param name="valueP" />
		<xsl:param name="enrollTypePlanned">Planned</xsl:param>
		<xsl:if test="normalize-space($valueP) = normalize-space($enrollTypePlanned)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="checkboxEnrollTypeC">
		<xsl:param name="valueC" />
		<xsl:param name="enrollTypeCumulative">Cumulative (Actual)</xsl:param>
		<xsl:if test="normalize-space($valueC) = normalize-space($enrollTypeCumulative)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="checkboxEnrollLocF">
		<xsl:param name="valueF" />
		<xsl:param name="enrollLocF">Foreign</xsl:param>
		<xsl:if test="normalize-space($valueF) = normalize-space($enrollLocF)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="checkboxEnrollLocD">
		<xsl:param name="valueD" />
		<xsl:param name="enrollLocD">Domestic</xsl:param>
		<xsl:if test="normalize-space($valueD) = normalize-space($enrollLocD)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:PlannedReport="http://apply.grants.gov/forms/PlannedReport-V1.0"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
	xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
	xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:header="http://apply.grants.gov/system/Header-V1.0"
	xmlns:xs="http://www.w3.org/2001/XMLSchema">

	<xsl:template
		match="PlannedReport:PlannedReport">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master master-name="first"
					page-height="8.5in" page-width="11in" margin-left="0.6in"
					margin-right="0.6in">
					<fo:region-body margin-top="0.5in" margin-bottom="0.1in" />
					<fo:region-before region-name="region-before-first"
						extent="0.2in" />
					<fo:region-after region-name="region-after-all"
						extent="0.4in" />
				</fo:simple-page-master>

				<fo:simple-page-master master-name="rest"
					page-height="8.5in" page-width="11in" margin-left="0.6in"
					margin-right="0.6in">
					<fo:region-body margin-top="0.5in" margin-bottom="0.2in" />
					<fo:region-after region-name="region-after-all"
						extent="0.3in" />
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
			<xsl:for-each select="PlannedReport:Study">
				<xsl:variable name="nodeCount" select="position()"/>
				<fo:page-sequence master-reference="first" format="1">

					<fo:static-content flow-name="region-before-first">
						<fo:table width="100%" space-before.optimum="0pt"
							space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en"
										line-height="9pt" padding-start="0pt" padding-end="0pt"
										padding-before="10pt" padding-after="0pt" display-align="before"
										text-align="right" border-style="solid" border-width="0pt"
										border-color="white">
										<fo:block>
											<fo:inline font-size="6px">OMB Number:
												0925-0002
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>

					<fo:static-content flow-name="region-after-all">
						<fo:table width="100%" space-before.optimum="0pt"
							space-after.optimum="0pt" table-layout="fixed">
							<fo:table-column column-width="proportional-column-width(1)" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en"
										number-columns-spanned="1" padding-start="0pt" padding-end="0pt"
										padding-before="1pt" padding-after="1pt" display-align="center"
										text-align="center" border-style="solid" border-width="0pt"
										border-color="white">
										<fo:block>
											<fo:inline font-size="8px">&#160;
											</fo:inline>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:static-content>

					<fo:flow flow-name="xsl-region-body">
						<xsl:call-template name="plannedEnrollmentReport" />

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
													Study <xsl:value-of select="$nodeCount"/> of <xsl:value-of select="count(//PlannedReport:PlannedReport/PlannedReport:Study)"/>
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

	<xsl:template name="plannedEnrollmentReport">
		<fo:block>
			<!-- Planned Enrollment Report -->
			<fo:table font-size="12pt" font-weight="bold" width="100%"
				space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" border-style="solid" border-width="1pt"
							border-color="white">
							<fo:block font-size="12px" font-weight="bold">
								Planned Enrollment Report
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			<fo:table font-size="12pt" font-weight="bold" width="100%"
				space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
				<fo:table-column column-width="proportional-column-width(1)" />
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell text-align="center" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" border-style="solid" border-width="1pt"
							border-color="white">
							<fo:block font-size="9px" font-weight="bold" color="white">
								This report format should NOT be used for collecting data from study participants.
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>

			<fo:table font-size="12pt" width="100%"
				space-before.optimum="3pt" space-after.optimum="3pt" table-layout="fixed"
				height="100%">
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
								<fo:inline font-size="9px" font-weight="bold">Study
									Title:
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="5" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-family="Georgia" font-size="9px">
									<xsl:value-of select="PlannedReport:StudyTitle" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Domestic/Foreign -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="2" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-size="9px" font-weight="bold">Domestic/Foreign:
								</fo:inline>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en"
							line-height="11pt" number-columns-spanned="5" padding-start="2pt"
							padding-end="2pt" padding-before="2pt" padding-after="2pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-family="Georgia" font-size="9px">
									<xsl:value-of select="PlannedReport:DomesticForeignIndicator" />
								</fo:inline>
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
							line-height="9pt" number-columns-spanned="5" padding-start="2pt"
							padding-end="2pt" padding-before="1pt" padding-after="1pt"
							display-align="center" text-align="start" border-style="solid"
							border-width="1pt" border-color="white">
							<fo:block>
								<fo:inline font-family="Georgia" font-size="9px">
									<xsl:value-of select="PlannedReport:Comments" />
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
									<fo:table-column column-width="proportional-column-width(1)" />
									<fo:table-body>
										<fo:table-row>
											<fo:table-cell hyphenate="true" language="en"
												number-columns-spanned="2" padding-start="0pt" padding-end="0pt"
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
							line-height="9pt" number-columns-spanned="5" padding-start="0pt"
							padding-end="0pt" padding-before="0pt" padding-after="0pt"
							display-align="center" text-align="center" 
                            border-top-style="solid" border-top-width="1pt" border-top-color="black"
                            border-right-style="solid" border-right-width="1pt" border-right-color="black">
							<fo:block>
                           		<fo:table>
                                	<fo:table-column column-width="80%" />
									<fo:table-column column-width="20%" />
									<fo:table-body>
										<fo:table-row>
                           					<fo:table-cell>
                                            	<fo:block font-size="9px" font-weight="bold">Ethnic Categories</fo:block> 
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
												number-columns-spanned="2">
												<fo:block font-size="9px" font-weight="bold">Not
													Hispanic
													or Latino
												</fo:block>
												<fo:block>
													<fo:table width="100%" space-before.optimum="0pt"
														space-after.optimum="0pt" table-layout="fixed">
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
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Male
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
												number-columns-spanned="2">
												<fo:block font-size="9px" font-weight="bold">Hispanic
													or
													Latino
												</fo:block>
												<fo:block>
													<fo:table width="100%" space-before.optimum="0pt"
														space-after.optimum="0pt" table-layout="fixed">
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
																	number-columns-spanned="1">
																	<fo:block font-size="9px" font-weight="bold">Male
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
												padding-after="0pt" display-align="before" text-align="center"
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
																	border-style="solid" border-width="0pt" border-color="white">
																	<fo:block font-size="9px">
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
								<fo:inline font-size="9px">American Indian/Alaska Native
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:AmericanIndian" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:AmericanIndian" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:AmericanIndian" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:AmericanIndian" />
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
										select="PlannedReport:Total/PlannedReport:AmericanIndian" />
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
								<fo:inline font-size="9px">Asian
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:Asian" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:Asian" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:Asian" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:Asian" />
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
										select="PlannedReport:Total/PlannedReport:Asian" />
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
								<fo:inline font-size="9px">Native Hawaiian or Other Pacific Islander
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:Hawaiian" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:Hawaiian" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:Hawaiian" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:Hawaiian" />
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
										select="PlannedReport:Total/PlannedReport:Hawaiian" />
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
								<fo:inline font-size="9px">Black or African American
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:Black" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:Black" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:Black" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:Black" />
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
										select="PlannedReport:Total/PlannedReport:Black" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- White..... Fifth row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- White label -->
						<!-- ============================ -->
						<fo:table-cell hyphenate="true" language="en"
							line-height="9pt" number-columns-spanned="2" padding-start="0pt"
							padding-end="0pt" padding-before="1pt" padding-after="0pt"
							display-align="center" text-align="center" border-style="solid"
							border-width="1pt" border-color="black">
							<fo:block>
								<fo:inline font-size="9px">White
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:White" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:White" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:White" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:White" />
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
										select="PlannedReport:Total/PlannedReport:White" />
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
								<fo:inline font-size="9px">More than One Race
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:MultipleRace" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:MultipleRace" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:MultipleRace" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:MultipleRace" />
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
										select="PlannedReport:Total/PlannedReport:MultipleRace" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

					<!-- ============================ -->
					<!-- Total..... Sevent row filling -->
					<!-- ============================ -->
					<fo:table-row height="20px">
						<!-- ============================ -->
						<!-- Asian label -->
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
										select="PlannedReport:NotHispanic/PlannedReport:Female/PlannedReport:Total" />
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
										select="PlannedReport:NotHispanic/PlannedReport:Male/PlannedReport:Total" />
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
										select="PlannedReport:Hispanic/PlannedReport:Female/PlannedReport:Total" />
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
										select="PlannedReport:Hispanic/PlannedReport:Male/PlannedReport:Total" />
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
										select="PlannedReport:Total/PlannedReport:Total" />
								</fo:inline>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

				</fo:table-body>
			</fo:table>
		</fo:block>
	</xsl:template>
</xsl:stylesheet>
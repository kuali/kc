<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="c:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="budget.xml" templatexmlfile="" xsltversion="1" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<styles font-family="Verdana" font-size="7pt" line-height="10pt"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<newline/>
			<text fixtext="Program Director/Principal Investigator (Last, First, Middle): ">
				<styles font-family="Verdana" font-size="8pt"/>
			</text>
			<text fixtext="  ">
				<styles font-family="Verdana" font-size="9pt"/>
			</text>
			<template>
				<match match="n1:ResearchAndRelatedProject"/>
				<children>
					<template>
						<match match="ProposalPerson"/>
						<children>
							<choice>
								<children>
									<choiceoption>
										<testexpression>
											<xpath value="ProjectRole =&apos;Principal Investigator&apos;"/>
										</testexpression>
										<children>
											<template>
												<match match="Name"/>
												<children>
													<template>
														<match match="LastName"/>
														<children>
															<xpath allchildren="1">
																<styles font-family="Verdana" font-size="8pt"/>
															</xpath>
														</children>
													</template>
													<text fixtext=", ">
														<styles font-family="Verdana" font-size="8pt"/>
													</text>
													<template>
														<match match="FirstName"/>
														<children>
															<xpath allchildren="1">
																<styles font-family="Verdana" font-size="8pt"/>
															</xpath>
														</children>
													</template>
													<text fixtext=" ">
														<styles font-family="Verdana" font-size="8pt"/>
													</text>
													<template>
														<match match="MiddleName"/>
														<children>
															<xpath allchildren="1">
																<styles font-family="Verdana" font-size="8pt"/>
															</xpath>
														</children>
													</template>
													<text fixtext=" ">
														<styles font-family="Verdana" font-size="8pt"/>
													</text>
													<template>
														<match match="NameSuffix"/>
														<children>
															<xpath allchildren="1">
																<styles font-family="Verdana" font-size="8pt"/>
															</xpath>
														</children>
													</template>
												</children>
											</template>
										</children>
									</choiceoption>
								</children>
							</choice>
						</children>
					</template>
				</children>
			</template>
			<table>
				<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="10pt" font-weight="bold"/>
										<properties align="center" valign="center" width="5in"/>
										<children>
											<text fixtext="BUDGET JUSTIFICATION"/>
										</children>
									</tablecol>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="solid" border-right-style="none" font-family="Verdana" font-size="9pt"/>
										<properties align="left" valign="top" width="2.5in"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="GRANT NUMBER">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="nihPriorGrantNumber"/>
														<children>
															<xpath allchildren="1"/>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="8pt"/>
										<properties align="left" colspan="2" height="4in" valign="top"/>
										<children>
											<text fixtext="Provide a detailed budget justification for those line items and amounts that represent a significant change from that previously recommended.  Use continuation pages if necessary."/>
											<newline/>
											<newline/>
											<text fixtext="SEE ATTACHED"/>
											<newline/>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<table>
				<properties border="1" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<styles border-left-style="none" font-family="Verdana" font-size="8pt"/>
										<children>
											<text fixtext="CURRENT BUDGET PERIOD">
												<styles font-weight="bold"/>
											</text>
										</children>
									</tablecol>
									<tablecol>
										<styles font-family="Verdana" font-size="8pt"/>
										<properties valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="FROM"/>
												</children>
											</paragraph>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="ProjectDates"/>
																<children>
																	<template>
																		<match match="ProjectStartDate"/>
																		<children>
																			<xpath allchildren="1">
																				<format string="MM/DD/YYYY" xslt="1"/>
																			</xpath>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
									<tablecol>
										<styles border-right-style="none" font-family="Verdana" font-size="8pt"/>
										<properties valign="top"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="THROUGH"/>
												</children>
											</paragraph>
											<template>
												<match match="n1:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="n1:ResearchCoverPage"/>
														<children>
															<template>
																<match match="ProjectDates"/>
																<children>
																	<template>
																		<match match="ProjectEndDate"/>
																		<children>
																			<xpath allchildren="1">
																				<format string="MM/DD/YYYY" xslt="1"/>
																			</xpath>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</template>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<properties height="3in" valign="top"/>
								<children>
									<tablecol>
										<styles border-bottom-style="none" border-left-style="none" border-right-style="none" font-family="Verdana" font-size="8pt"/>
										<properties colspan="3" height="3in" valign="top"/>
										<children>
											<text fixtext="Explain any estimated unobligated balance (including prior year carryover) that is greater than 25% of the current year&apos;s total budget."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.6in" papermarginleft="0.3in" papermarginright="0.3in" papermargintop="0.5in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="3" height="4"/>
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<properties width="286"/>
												<children>
													<text fixtext="PHS 2590 ">
														<styles font-family="Verdana" font-size="9pt"/>
													</text>
													<text fixtext="(Rev. 11/07)">
														<styles font-family="Verdana" font-size="9pt"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<children>
													<text fixtext="Page:">
														<styles font-family="Verdana" font-size="9pt"/>
													</text>
												</children>
											</tablecol>
											<tablecol>
												<properties align="right"/>
												<children>
													<text fixtext="Form Page 3">
														<styles font-family="Verdana" font-size="9pt" font-weight="bold"/>
													</text>
												</children>
											</tablecol>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</template>
		</footerall>
	</pagelayout>
</structure>

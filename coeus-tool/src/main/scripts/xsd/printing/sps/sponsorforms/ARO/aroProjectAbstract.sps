<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="C:\Proposal printing\xml files\nihproposal.xml" templatexmlfile="">
	<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<styles font-family="Verdana"/>
		<match overwrittenxslmatch="/"/>
		<children>
			<table>
				<properties border="0" width="100%"/>
				<children>
					<tablebody>
						<children>
							<tablerow>
								<children>
									<tablecol>
										<properties align="center"/>
										<children>
											<paragraph paragraphtag="p">
												<children>
													<line>
														<properties color="black" size="1"/>
													</line>
												</children>
											</paragraph>
											<paragraph paragraphtag="p">
												<children>
													<text fixtext="PROJECT ABSTRACT">
														<styles font-weight="bold"/>
													</text>
												</children>
											</paragraph>
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
										<children>
											<text fixtext="The Project Abstract shall include a statement of objectives, methods to be employed, and the significance of the proposed activity to the advancement of knowledge or education. Avoid use of the first person to complete this summary. DO NOT EXCEED ONE PAGE. The abstract should be suitable for release under the Freedom of Information Act. 5 U.S.C. 552, as amended."/>
										</children>
									</tablecol>
								</children>
							</tablerow>
							<tablerow>
								<children>
									<tablecol>
										<styles padding-bottom="0pt" padding-left="12pt" padding-right="12pt" padding-top="0pt"/>
										<properties colspan="3" valign="top"/>
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="string-length(nih:ResearchAndRelatedProject/Abstract [AbstractTypeCode = 1]/AbstractText ) &lt;= 0"/>
														</testexpression>
														<children>
															<newline/>
															<newline/>
														</children>
													</choiceoption>
												</children>
											</choice>
											<template>
												<match match="nih:ResearchAndRelatedProject"/>
												<children>
													<template>
														<match match="Abstract"/>
														<children>
															<template>
																<match match="AbstractText"/>
																<children>
																	<choice>
																		<children>
																			<choiceoption>
																				<testexpression>
																					<xpath value="../AbstractTypeCode = 1"/>
																				</testexpression>
																				<children>
																					<xpath allchildren="1">
																						<styles font-family="Verdana" font-size="10pt"/>
																					</xpath>
																				</children>
																			</choiceoption>
																		</children>
																	</choice>
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
						</children>
					</tablebody>
				</children>
			</table>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.6in" papermarginleft="0.4in" papermarginright="0.4in" papermargintop="0.6in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties border="0" width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="center" colspan="2"/>
												<children>
													<text fixtext="Page C-1"/>
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

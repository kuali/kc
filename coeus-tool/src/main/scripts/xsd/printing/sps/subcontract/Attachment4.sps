<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<predefinedformats>
		<format string="MM/DD/YYYY" datatype="date"/>
	</predefinedformats>
	<predefinedformats>
		<format string="MM/DD/YYYY" datatype="dateTime"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="award" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award"/>
			<nspair prefix="subcontract" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="SubcontractFdpReports.xsd" workingxmlfile="C:\Documents and Settings\coeusdev\Desktop\Stylesheets on nibc606\Subcontract_Fdp_Report.xml">
				<xmltablesupport/>
				<textstateicons/>
			</xsdschemasource>
		</schemasources>
	</schemasources>
	<modules/>
	<flags>
		<scripts/>
		<globalparts/>
		<designfragments/>
		<pagelayouts/>
	</flags>
	<scripts>
		<script language="javascript"/>
	</scripts>
	<globalstyles/>
	<mainparts>
		<children>
			<globaltemplate match="/" matchtype="named" parttype="main">
				<children>
					<newline/>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<properties valign="top"/>
												<styles font-family="Arial"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" height="32" width="90%"/>
														<styles font-family="Arial"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="ATTACHMENT 4"/>
																		</children>
																	</paragraph>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="SUBAWARD AGREEMENT"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" width="90%"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Reporting Requirements"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="9" width="100%"/>
														<styles font-family="Arial" font-size="9pt"/>
														<children>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="9" width="100%"/>
														<children>
															<table>
																<properties align="center" border="1" cellpadding="0" cellspacing="0" width="80%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="100%"/>
																						<children>
																							<text fixtext="Report Type"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="subcontract:SubcontractReports" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="100%"/>
																										<children>
																											<template match="subcontract:ReportTypeDescription" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablebody>
																</children>
															</table>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientation="landscape" pageorientationportrait="portrait" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.5in" paperwidth="8.5in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>

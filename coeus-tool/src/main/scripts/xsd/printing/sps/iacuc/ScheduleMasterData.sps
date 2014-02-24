<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\Coeus\4.4\Printing\iacuc\schema\iacuc.xsd">
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
					<paragraph paragraphtag="h2">
						<properties align="center"/>
						<children>
							<paragraph paragraphtag="center">
								<children>
									<text fixtext="Schedule Details">
										<styles font-family="Arial" font-size="20pt"/>
									</text>
								</children>
							</paragraph>
						</children>
					</paragraph>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="5"/>
						<children>
							<template match="n1:Schedule" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<newline/>
									<newline/>
									<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<newline/>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="11pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Schedule Id:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:ScheduleId" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Committee Name:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:CommitteeName" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Meeting Date:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:MeetingDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
														</children>
													</tablebody>
												</children>
											</table>
										</children>
									</template>
									<newline/>
									<newline/>
									<newline/>
								</children>
							</template>
						</children>
					</template>
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="landscape" paperheight="8.5in" papermarginbottom="0.2in" papermarginleft="1.2in" papermarginright="0.65in" papermargintop="2.0in" paperwidth="11in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall"/>
		</children>
	</pagelayout>
	<designfragments/>
</structure>

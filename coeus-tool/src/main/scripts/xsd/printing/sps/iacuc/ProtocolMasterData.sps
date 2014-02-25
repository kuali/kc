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
									<text fixtext="IACUC Protocol Details">
										<styles font-family="Arial" font-size="20pt"/>
									</text>
								</children>
							</paragraph>
						</children>
					</paragraph>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="5"/>
						<children>
							<template match="n1:Protocol" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
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
																			<text fixtext="Protocol Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
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
																			<text fixtext="Sequence Number:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:SequenceNumber" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="integer"/>
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
																			<text fixtext="Status:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:ProtocolStatusDesc" matchtype="schemagraphitem">
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
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<paragraph paragraphtag="p"/>
										</children>
									</template>
									<newline/>
									<text fixtext="Investigator">
										<styles font-family="Arial" font-size="16pt" font-weight="bold"/>
									</text>
									<paragraph paragraphtag="p"/>
									<table>
										<properties border="1" cellpadding="0" cellspacing="0"/>
										<styles font-family="Arial" font-size="11pt"/>
										<children>
											<tableheader>
												<children>
													<tablerow>
														<children>
															<tablecell headercell="1">
																<children>
																	<text fixtext="Person Name"/>
																</children>
															</tablecell>
															<tablecell headercell="1">
																<children>
																	<text fixtext="Affiliation"/>
																</children>
															</tablecell>
															<tablecell headercell="1">
																<children>
																	<text fixtext="Units"/>
																</children>
															</tablecell>
															<tablecell headercell="1">
																<children>
																	<text fixtext="Lead Unit Flag"/>
																</children>
															</tablecell>
														</children>
													</tablerow>
												</children>
											</tableheader>
											<tablebody>
												<children>
													<template match="n1:Investigator" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<children>
																			<template match="n1:Person" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Fullname" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<format datatype="string"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:AffiliationDesc" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:Unit" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<table>
																						<properties border="0" width="100%"/>
																						<children>
																							<tablebody>
																								<children>
																									<tablerow>
																										<children>
																											<tablecell>
																												<children>
																													<template match="n1:UnitNumber" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</tablecell>
																											<tablecell>
																												<children>
																													<template match="n1:UnitName" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
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
																	</tablecell>
																	<tablecell>
																		<children>
																			<newline/>
																			<template match="n1:LeadUnit_Flag" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<paragraph paragraphtag="p">
																						<children>
																							<content>
																								<format datatype="boolean"/>
																							</content>
																							<text fixtext=" "/>
																						</children>
																					</paragraph>
																					<text fixtext=" "/>
																				</children>
																			</template>
																			<text fixtext="   "/>
																			<newline/>
																			<newline/>
																			<text fixtext=" "/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</template>
												</children>
											</tablebody>
										</children>
									</table>
									<newline/>
									<newline/>
									<newline/>
									<newline/>
								</children>
							</template>
						</children>
					</template>
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
			<globaltemplate match="#footerall" matchtype="named" parttype="footerall">
				<children>
					<table>
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2" height="30"/>
												<styles padding="0"/>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2"/>
												<styles padding="0"/>
												<children>
													<line>
														<properties color="black" size="1"/>
														<styles top="-37pt"/>
													</line>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left"/>
												<styles font-size="10pt" padding="0"/>
											</tablecell>
											<tablecell>
												<properties align="right" width="150"/>
												<styles font-family="Arial" font-size="9pt" padding="0"/>
												<children>
													<text fixtext="Page: ">
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
													</text>
													<field>
														<styles font-weight="bold"/>
													</field>
												</children>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>

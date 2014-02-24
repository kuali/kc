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
									<text fixtext="Committee Details">
										<styles font-family="Arial" font-size="20pt"/>
									</text>
								</children>
							</paragraph>
							<text fixtext=" "/>
						</children>
					</paragraph>
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="5"/>
						<children>
							<template match="n1:Committee" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<newline/>
									<newline/>
									<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
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
																			<text fixtext="Committee Id:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:CommitteeId" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content/>
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
																					<content/>
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
																			<text fixtext="Unit Name:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:HomeUnitName" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content/>
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
																			<text fixtext="Committee Type:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:CommitteeTypeDesc" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content/>
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
																			<text fixtext="Minimum Members Required:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:MinimumMembersRequired" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content/>
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
																			<text fixtext="Max Protocols:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:MaxProtocols" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content/>
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
										</children>
									</template>
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

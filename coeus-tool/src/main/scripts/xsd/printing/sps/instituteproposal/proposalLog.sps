<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces/>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="proposalLog.xsd" workingxmlfile="proposalLog2.xml">
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
					<template match="$XML" matchtype="schemasource">
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="10pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center" colspan="6" height="19" width="1704"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Proposal Log">
																<styles font-weight="bold" text-decoration="underline"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Prop. No.:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="20%"/>
														<styles font-size="10pt" padding="0"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="proposalNumber" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<text fixtext=" "/>
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
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Prop. Type:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="20%"/>
														<styles font-size="10pt" padding-left="0" padding-right="0"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="proposalType" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="proposalTypeDesc" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<text fixtext=" "/>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="12%"/>
														<styles font-size="10pt" padding-left="0" padding-right="0"/>
														<children>
															<text fixtext="Status:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties width="18%"/>
														<styles font-size="10pt" padding="0"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="status" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<text fixtext=" "/>
																			<content>
																				<format datatype="string"/>
																			</content>
																		</children>
																	</template>
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
							<condition>
								<children>
									<conditionbranch xpath="starts-with( proposalLog/proposalNumber , &apos;T&apos;)   or starts-with( proposalLog/proposalNumber , &apos;D&apos;)">
										<children>
											<newline/>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties align="right" width="15%"/>
																		<children>
																			<text fixtext="Merged With:">
																				<styles font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="3" width="80%"/>
																		<children>
																			<paragraph paragraphtag="pre-wrap">
																				<children>
																					<template match="proposalLog" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="mergedWith" matchtype="schemagraphitem">
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
																			</paragraph>
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
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="10pt" line-height="10pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="P.Investigator:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3" valign="top" width="55%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="PI" matchtype="schemagraphitem">
																		<children>
																			<template match="FullName" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="12%"/>
														<styles font-size="10pt" padding-left="0" padding-right="0"/>
														<children>
															<text fixtext="Deadline Date:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="18%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="deadlinedate" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Lead Unit:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="5" valign="top" width="85%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="leadUnit" matchtype="schemagraphitem">
																		<children>
																			<template match="unitNumber" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																			<text fixtext="    "/>
																			<template match="unitName" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Sponsor:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="5" valign="top" width="85%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="sponsor" matchtype="schemagraphitem">
																		<children>
																			<template match="sponsorCode" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																			<text fixtext="    "/>
																			<template match="sponsorName" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext="    "/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Title:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="5" valign="top" width="85%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="proposalTitle" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="top" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Comments:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="5" valign="top" width="85%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="comments" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="center" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Create User:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="35%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="createUser" matchtype="schemagraphitem">
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
														<properties align="right" colspan="3" valign="top" width="32%"/>
														<styles font-size="10pt" padding-right="0"/>
														<children>
															<text fixtext="Create Date:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="18%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="createTimestamp" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="right" valign="center" width="15%"/>
														<styles font-size="10pt"/>
														<children>
															<text fixtext="Update User:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="35%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="updateUser" matchtype="schemagraphitem">
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
														<properties align="right" colspan="3" valign="top" width="32%"/>
														<styles font-size="10pt" padding-right="0"/>
														<children>
															<text fixtext="Update Timestamp:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="top" width="18%"/>
														<styles font-size="10pt"/>
														<children>
															<template match="proposalLog" matchtype="schemagraphitem">
																<children>
																	<template match="updateTimeStamp" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="6" width="1704"/>
														<children>
															<line>
																<properties color="black" size="1"/>
															</line>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.1in" papermarginright="0.4in" papermargintop="0.3in" paperwidth="8.5in"/>
	</pagelayout>
	<designfragments/>
</structure>

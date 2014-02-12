<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="ps" uri="http://www.w3.org/2001/ProtocolSummarySchema"/>
			<nspair prefix="qs" uri="http://www.w3.org/2001/QuesionnaireSchema"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="ProtocolSummary.xsd">
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
							<newline/>
							<paragraph paragraphtag="center">
								<children>
									<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="ps:SchoolInfo" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="ps:SchoolName" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<content>
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																<format datatype="string"/>
															</content>
														</children>
													</template>
												</children>
											</template>
										</children>
									</template>
								</children>
							</paragraph>
							<paragraph paragraphtag="center">
								<children>
									<text fixtext="Protocol Summary">
										<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
									</text>
								</children>
							</paragraph>
							<paragraph paragraphtag="p"/>
							<table>
								<properties border="0"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Protocol Number:"/>
														</children>
													</tablecell>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" margin-left="-185pt"/>
																		<children>
																			<template match="ps:ProtocolNumber" matchtype="schemagraphitem">
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
															</template>
														</children>
													</template>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Sequence Number:"/>
														</children>
													</tablecell>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" margin-left="-185pt"/>
																		<children>
																			<template match="ps:SequenceNumber" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format datatype="int"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																</children>
															</template>
														</children>
													</template>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Status:"/>
														</children>
													</tablecell>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" margin-left="-185pt"/>
																		<children>
																			<template match="ps:ProtocolStatusDesc" matchtype="schemagraphitem">
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
															</template>
														</children>
													</template>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Expiration Date:"/>
														</children>
													</tablecell>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" margin-left="-185pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="ps:ExpirationDate  = &quot;&quot;"/>
																					<conditionbranch>
																						<children>
																							<template match="ps:ExpirationDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																									<button>
																										<action>
																											<datepicker/>
																										</action>
																									</button>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</template>
														</children>
													</template>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Last Approval Date:"/>
														</children>
													</tablecell>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" margin-left="-185pt"/>
																		<children>
																			<condition>
																				<children>
																					<conditionbranch xpath="ps:LastApprovalDate  = &quot;&quot;"/>
																					<conditionbranch>
																						<children>
																							<template match="ps:LastApprovalDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																									<button>
																										<action>
																											<datepicker/>
																										</action>
																									</button>
																								</children>
																							</template>
																						</children>
																					</conditionbranch>
																				</children>
																			</condition>
																		</children>
																	</tablecell>
																</children>
															</template>
														</children>
													</template>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell headercell="1">
														<properties align="left"/>
														<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
														<children>
															<text fixtext="Investigator:"/>
														</children>
													</tablecell>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<tablecell>
																		<styles font-family="Arial" font-size="9pt" margin-left="-185pt"/>
																		<children>
																			<template match="ps:Investigator" matchtype="schemagraphitem">
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
															</template>
														</children>
													</template>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:ProtocolDetailsRequired  = 1">
										<children>
											<table>
												<properties border="0" width="100%"/>
												<styles padding-top="10pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties height="11"/>
																		<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Protocol Details ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="11"/>
																		<styles font-weight="normal"/>
																	</tablecell>
																	<tablecell>
																		<properties height="11"/>
																	</tablecell>
																	<tablecell>
																		<properties height="11"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Type: ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-family="Arial" font-size="10pt" margin-left="-10pt"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:ProtocolTypeDesc" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Description:">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles margin-left="-10pt"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:Description" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Application Date: ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-weight="inherit" margin-left="-10pt"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:ApplicationDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																									<button>
																										<action>
																											<datepicker/>
																										</action>
																									</button>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Reference Num 1: ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-weight="inherit" margin-left="-10pt"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:ReferenceNumber1" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</tablecell>
																	<tablecell>
																		<styles font-weight="bold"/>
																	</tablecell>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Reference Num 2:">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles margin-left="-10pt"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:ReferenceNumber2" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="FDA Application No: ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles margin-left="-10pt"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:FdaApplicationNumber" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</tablecell>
																	<tablecell/>
																	<tablecell/>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt"/>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Title:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="80%"/>
																		<styles font-size="10pt" padding="0"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:Title" matchtype="schemagraphitem">
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
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:OrganizationRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0"/>
																<children>
																	<tablecell>
																		<properties align="left" height="10" width="25%"/>
																		<styles border="0" font-family="Arial" font-weight="bold"/>
																		<children>
																			<text fixtext="Organizations">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10" width="25%"/>
																		<styles border="0" font-family="Arial" font-weight="bold"/>
																	</tablecell>
																	<tablecell>
																		<properties height="10" width="50%"/>
																		<styles border="0" font-family="Arial" font-weight="bold"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="25%"/>
																		<styles font-family="Arial" font-weight="bold"/>
																		<children>
																			<text fixtext="Type">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="25%"/>
																		<styles font-family="Arial" font-weight="bold"/>
																		<children>
																			<text fixtext="Organization">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties width="50%"/>
																		<styles font-family="Arial" font-weight="bold"/>
																		<children>
																			<text fixtext="Address">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolOrganization" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="25%"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:ProtocolOrgTypeDesc" matchtype="schemagraphitem">
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
																						<properties width="25%"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:OrgName" matchtype="schemagraphitem">
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
																						<properties width="50%"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:Address" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:InvestigatorsRequired  = 1">
										<children>
											<newline/>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10" width="20%"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Investigators">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10" width="50%"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10" width="15%"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10" width="15%"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties width="20%"/>
																		<children>
																			<text fixtext="Person Name"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="50%"/>
																		<children>
																			<text fixtext="Units"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="15%"/>
																		<children>
																			<text fixtext="Affiliate"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties width="15%"/>
																		<children>
																			<text fixtext="Training Flag"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolInvestigators" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<template match="ps:PersonName" matchtype="schemagraphitem">
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
																							<table>
																								<properties border="0" width="80%"/>
																								<children>
																									<tablebody>
																										<children>
																											<template match="ps:ProtocolUnits" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<tablerow>
																														<styles font-family="Arial" font-size="10pt"/>
																														<children>
																															<tablecell>
																																<children>
																																	<template match="ps:UnitNumber" matchtype="schemagraphitem">
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
																																	<template match="ps:UnitName" matchtype="schemagraphitem">
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
																											</template>
																										</children>
																									</tablebody>
																								</children>
																							</table>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="15%"/>
																						<children>
																							<template match="ps:AffiliationTypeDesc" matchtype="schemagraphitem">
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
																						<properties width="15%"/>
																						<children>
																							<template match="ps:TrainingFlag" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:KeyPersonsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="Study Personnel">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0" font-family="Arial" font-size="10pt"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																		<children>
																			<text fixtext="PersonName">
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Role">
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<styles font-family="Arial" font-size="10pt"/>
																		<children>
																			<text fixtext="Affiliation">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Training">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolKeyPersons" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:PersonName" matchtype="schemagraphitem">
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
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:PersonRole" matchtype="schemagraphitem">
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
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:AffiliationTypeDesc" matchtype="schemagraphitem">
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
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<template match="ps:TrainingFlag" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:CorrespondentsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Correspondents ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Type">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Name">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Comments">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolCorrespondents" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:CorrespondentTypeDesc" matchtype="schemagraphitem">
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
																							<template match="ps:PersonName" matchtype="schemagraphitem">
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
																							<template match="ps:Comments" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:ResearchAreasRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Areas of Research ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Code">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Description">
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolResearchAreas" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:ResearchAreaCode" matchtype="schemagraphitem">
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
																							<template match="ps:ResearchAreaDesc" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:FundingSourcesRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0" font-family="Arial" font-size="10pt"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Funding Source">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Type">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Number/Code">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Name/Title">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolFundingSources" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:FundingSourceTypeDesc" matchtype="schemagraphitem">
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
																							<template match="ps:FundingSource" matchtype="schemagraphitem">
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
																							<template match="ps:Title" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:ActionsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Actions">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Description">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Comments">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Action Date">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolActions" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:ProtocolActionTypeDesc" matchtype="schemagraphitem">
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
																							<template match="ps:Comments" matchtype="schemagraphitem">
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
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:ActionDate  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:ActionDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
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
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:SubjectsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Subjects">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Subject">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Count">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolSubjects" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:VulnerableSubjectTypeDesc" matchtype="schemagraphitem">
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
																							<template match="ps:SubjectCount" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="int"/>
																									</content>
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
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:DocumentsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" colspan="2" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="New/Changed Attachments">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Description">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Last Updated">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Updated By">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolDocuments" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ps:ProtocolDocument" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<tablerow>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<tablecell>
																								<children>
																									<template match="ps:Description" matchtype="schemagraphitem">
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
																									<template match="ps:UpdateTimestamp" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format string="MM/DD/YYYY HH:mm:ss AM" datatype="dateTime"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<template match="ps:UpdateUser" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Other Attachments ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Description"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Last Updated">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Updated By">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolDocuments" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ps:ProtocolOtherDocuments" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<tablerow>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<tablecell>
																								<children>
																									<template match="ps:Description" matchtype="schemagraphitem">
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
																									<template match="ps:UpdateTimestamp" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format string="MM/DD/YYYY HH:mm:ss AM" datatype="dateTime"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</tablecell>
																							<tablecell>
																								<children>
																									<template match="ps:UpdateUser" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:SpecialReviewRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Special Review ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Special Review">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Approval">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Protocol No">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Application Date">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Approval Date">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Comments">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolSpecialReview" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:SpecialReviewDesc" matchtype="schemagraphitem">
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
																							<template match="ps:ApprovalTypeDesc" matchtype="schemagraphitem">
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
																							<template match="ps:SpRevProtocolNumber" matchtype="schemagraphitem">
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
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:ApplicationDate  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:ApplicationDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:ApprovalDate  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:ApprovalDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="ps:Comments" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:RiskLevelsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Risk Levels">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Risk Level">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Date Assigned">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Date Updated">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Status">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Comments">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolRiskLevels" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:RiskLevelDesc" matchtype="schemagraphitem">
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
																							<template match="ps:DateAssigned" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:DateUpdated  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:DateUpdated" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="ps:Status" matchtype="schemagraphitem">
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
																							<template match="ps:Comments" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:NotesRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Protocol Notes">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																			<text fixtext=" ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Comment">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="By">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Time ">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolNotes" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:Comments" matchtype="schemagraphitem">
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
																							<template match="ps:UpdateUser" matchtype="schemagraphitem">
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
																							<template match="ps:UpdateTimestamp" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY HH:mm:ss AM" datatype="dateTime"/>
																									</content>
																									<button>
																										<action>
																											<datepicker/>
																										</action>
																									</button>
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
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:UserRolesRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Roles">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="ps:ProtocolUserRoles" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<condition>
																<children>
																	<conditionbranch xpath="string-length(  ps:UserRoles )  = 0"/>
																	<conditionbranch>
																		<children>
																			<template match="ps:RoleName" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</conditionbranch>
																</children>
															</condition>
															<newline/>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<styles border="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties height="10"/>
																						<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="10"/>
																						<styles border="0"/>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="10"/>
																						<styles border="0"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties height="20"/>
																						<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																						<children>
																							<text fixtext="User Id"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="20"/>
																						<children>
																							<text fixtext="User Name"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="20"/>
																						<children>
																							<text fixtext="Unit Name"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="ps:UserRoles" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<tablerow>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<tablecell>
																								<styles font-family="Arial" font-size="10pt"/>
																								<children>
																									<template match="ps:UserId" matchtype="schemagraphitem">
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
																									<template match="ps:UserName" matchtype="schemagraphitem">
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
																									<template match="ps:UnitName" matchtype="schemagraphitem">
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
																			</template>
																		</children>
																	</tablebody>
																</children>
															</table>
															<newline/>
														</children>
													</template>
												</children>
											</template>
											<newline/>
											<newline/>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:ReferencesRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Reference List ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Reference Type">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Reference Number">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Application Date">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Approval Date">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Comments">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolReferences" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:ProtocolReferenceTypeDesc" matchtype="schemagraphitem">
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
																							<template match="ps:ProtocolReferenceNumber" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format datatype="int"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:ApplicationDate  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:ApplicationDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:ApprovalDate  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:ApprovalDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="ps:Comments" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<paragraph paragraphtag="p"/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:AmendRenewSRequired =1">
										<children>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Amendment/Renewal">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<styles border="0"/>
																	</tablecell>
																	<tablecell headercell="1">
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Type"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Version"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Status"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Created Date"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Summary"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolAmenRenewal" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:Type" matchtype="schemagraphitem">
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
																							<template match="ps:Version" matchtype="schemagraphitem">
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
																							<template match="ps:ProtocolStatusDesc" matchtype="schemagraphitem">
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
																							<condition>
																								<children>
																									<conditionbranch xpath="ps:DateCreated  = &quot;&quot;"/>
																									<conditionbranch>
																										<children>
																											<template match="ps:DateCreated" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<children>
																							<template match="ps:Summary" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<paragraph paragraphtag="p"/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:AmendRenewModulesRequired  = 1">
										<children>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" colspan="2" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Amendment/Renewal Summary">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Summary"/>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Editable Modules"/>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolAmenRenewal" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:Summary" matchtype="schemagraphitem">
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
																							<template match="ps:ProtocolModules" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="ps:Description" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<content>
																												<format datatype="string"/>
																											</content>
																											<text fixtext=","/>
																										</children>
																									</template>
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
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="ps:ProtocolSummary/ps:PrintRequirement/ps:OtherDataRequired  = 1">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="ps:ProtocolSummary/ps:ProtocolOthersData &gt; 0">
														<children>
															<paragraph paragraphtag="p">
																<styles padding-top="10pt"/>
																<children>
																	<text fixtext=" ">
																		<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<table>
												<properties border="1" cellpadding="0" cellspacing="0"/>
												<styles border="0"/>
												<children>
													<tableheader>
														<children>
															<tablerow>
																<styles border="0" font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<properties align="left" height="10"/>
																		<styles border="0"/>
																		<children>
																			<text fixtext="Others ">
																				<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<properties height="10"/>
																		<styles border="0"/>
																	</tablecell>
																</children>
															</tablerow>
															<tablerow>
																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																<children>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Name">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																	<tablecell headercell="1">
																		<children>
																			<text fixtext="Value">
																				<styles font-family="Arial" font-size="10pt"/>
																			</text>
																		</children>
																	</tablecell>
																</children>
															</tablerow>
														</children>
													</tableheader>
													<tablebody>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolOthersData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="10pt"/>
																				<children>
																					<tablecell>
																						<children>
																							<template match="ps:ColumnName" matchtype="schemagraphitem">
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
																							<template match="ps:ColumnValue" matchtype="schemagraphitem">
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
																	</template>
																</children>
															</template>
														</children>
													</tablebody>
												</children>
											</table>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
						</children>
					</template>
					<newline/>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties pageorientation="landscape" pageorientationportrait="portrait" paperheight="8.5in" papermarginbottom="1.2in" papermarginleft="1.65in" papermarginright="0.79in" papermargintop="1.5in" paperwidth="11in"/>
		<children>
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
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
												<properties align="left" width="50%"/>
												<styles font-size="10pt" padding="0"/>
												<children>
													<template match="$XML" matchtype="schemasource">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:SchoolInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ps:Acronym" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" width="50%"/>
												<styles font-size="10pt" padding="0"/>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left" width="50%"/>
												<styles font-size="10pt" padding="0"/>
												<children>
													<text fixtext="Protocol #: ">
														<styles font-family="Arial"/>
													</text>
													<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolNumber" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<content>
																				<styles font-family="Arial"/>
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
												<properties align="left" width="50%"/>
												<styles font-size="10pt" padding="0" padding-left="160pt"/>
												<children>
													<text fixtext="Expiration Date:">
														<styles font-family="Arial" padding-right="10pt" text-align="left"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="$XML/ps:ProtocolSummary/ps:ProtocolDetails/ps:ExpirationDate  = &quot;&quot;"/>
															<conditionbranch>
																<children>
																	<template match="$XML" matchtype="schemasource">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:ExpirationDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Arial"/>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																									<button>
																										<action>
																											<datepicker/>
																										</action>
																									</button>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=" "/>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties align="left" width="50%"/>
												<styles font-size="10pt" padding="0"/>
												<children>
													<text fixtext="Investigator: ">
														<styles font-family="Arial"/>
													</text>
													<template match="$XML" matchtype="schemasource">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ps:Investigator" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<styles font-family="Arial"/>
																						<format datatype="string"/>
																					</content>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
														</children>
													</template>
												</children>
											</tablecell>
											<tablecell>
												<properties align="left" width="50%"/>
												<styles font-size="10pt" padding="0" padding-left="160pt"/>
												<children>
													<text fixtext="Last Approval Date:">
														<styles font-family="Arial" padding-right="10pt" text-align="left"/>
													</text>
													<condition>
														<children>
															<conditionbranch xpath="$XML/ps:ProtocolSummary/ps:ProtocolDetails/ps:LastApprovalDate  = &quot;&quot;"/>
															<conditionbranch>
																<children>
																	<template match="$XML" matchtype="schemasource">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="ps:ProtocolSummary" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="ps:ProtocolDetails" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="ps:LastApprovalDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Arial"/>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																									<button>
																										<action>
																											<datepicker/>
																										</action>
																									</button>
																								</children>
																							</template>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</conditionbranch>
														</children>
													</condition>
													<text fixtext=" "/>
												</children>
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
			</globaltemplate>
		</children>
	</pagelayout>
	<designfragments/>
</structure>

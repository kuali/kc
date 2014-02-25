<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<predefinedformats>
		<format string="###,##0" datatype="decimal"/>
	</predefinedformats>
	<predefinedformats>
		<format string="MM / DD / YYYY" datatype="date"/>
	</predefinedformats>
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="iacuc.xsd">
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
									<template match="n1:Protocol" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="n1:SchoolInfo" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="n1:SchoolName" matchtype="schemagraphitem">
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
							<newline/>
							<table>
								<properties width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablecell headercell="1">
														<properties align="left" width="20%"/>
														<styles font-weight="bold"/>
														<children>
															<text fixtext="Protocol Number:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="80%"/>
														<styles font-family="Arial"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
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
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablecell headercell="1">
														<properties align="left" width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Sequence Number:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="80%"/>
														<styles font-family="Arial" font-size="9pt"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
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
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablecell headercell="1">
														<properties align="left" width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Status:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="80%"/>
														<styles font-family="Arial"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
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
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablecell headercell="1">
														<properties align="left" width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Expiration Date:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="80%"/>
														<styles font-family="Arial"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
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
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablecell headercell="1">
														<properties align="left" width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Last Approval Date:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="80%"/>
														<styles font-family="Arial"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:ApprovalDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
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
												<styles font-family="Arial" font-size="10pt"/>
												<children>
													<tablecell headercell="1">
														<properties align="left" width="20%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
														<children>
															<text fixtext="Investigator:"/>
														</children>
													</tablecell>
													<tablecell>
														<properties width="80%"/>
														<styles font-family="Arial"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:PrincipleInvestigatorName" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:ProtocolDetailsRequired  = 1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<newline/>
											<template match="n1:Protocol" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<newline/>
															<text fixtext="Protocol Details ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Protocol Type:">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" margin-left="-10pt"/>
																						<children>
																							<text fixtext="    "/>
																							<template match="n1:ProtocolTypeDesc" matchtype="schemagraphitem">
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Project Type:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" margin-left="-10pt"/>
																						<children>
																							<text fixtext="    "/>
																							<template match="n1:ProjectTypeDesc" matchtype="schemagraphitem">
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Description:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" margin-left="-10pt"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="n1:ProtocolDescription" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext="    "/>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Application Date:">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" margin-left="-10pt"/>
																						<children>
																							<text fixtext="    "/>
																							<template match="n1:ApplicationDate" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<format string="MM/DD/YYYY" datatype="date"/>
																									</content>
																								</children>
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Reference Num 1:">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" margin-left="-10pt"/>
																						<children>
																							<text fixtext="    "/>
																							<template match="n1:RefNumber1" matchtype="schemagraphitem">
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Reference Num 2:">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<styles font-family="Arial" font-size="9pt" font-weight="normal" margin-left="-10pt"/>
																						<children>
																							<text fixtext="    "/>
																							<template match="n1:RefNumber2" matchtype="schemagraphitem">
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
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Lay Statement 1:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="n1:LayStatement1" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext=" "/>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-family="Arial" font-weight="bold"/>
																						<children>
																							<text fixtext="Lay Statement 2:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="80%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="n1:LayStatement2" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext=" "/>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="20%"/>
																						<styles font-weight="bold"/>
																						<children>
																							<text fixtext="Title:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" valign="top" width="80%"/>
																						<children>
																							<paragraph paragraphtag="pre-wrap">
																								<children>
																									<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<text fixtext=" "/>
																											<content>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</template>
																								</children>
																							</paragraph>
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
											</template>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:OrganizationRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Organization  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Organizations">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Organization" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:OrgTypeDesc" matchtype="schemagraphitem">
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
																											<template match="n1:OrgName" matchtype="schemagraphitem">
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
																											<template match="n1:Address" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																											<newline/>
																											<text fixtext="Animal Welfare Assurance #:">
																												<styles font-weight="bold"/>
																											</text>
																											<text fixtext=" "/>
																											<template match="n1:AnimalWelfareAssurance" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																												</children>
																											</template>
																											<newline/>
																											<newline/>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:InvestigatorsRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Investigator  )  &gt;0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Investigators">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<text fixtext=" ">
																<styles font-family="Arial" font-size="10pt"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Person Name"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="45%"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Investigator" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
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
																															<condition>
																																<children>
																																	<conditionbranch xpath="../../n1:PIFlag = &apos;true&apos;">
																																		<children>
																																			<text fixtext="(PI)"/>
																																		</children>
																																	</conditionbranch>
																																</children>
																															</condition>
																														</children>
																													</template>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<table>
																												<properties border="0"/>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<children>
																																	<tablecell headercell="1">
																																		<properties colspan="2"/>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="n1:Unit" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="20%"/>
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
																																							<condition>
																																								<children>
																																									<conditionbranch xpath="../n1:LeadUnitFlag = &apos;true&apos;">
																																										<children>
																																											<text fixtext="(LU)"/>
																																										</children>
																																									</conditionbranch>
																																								</children>
																																							</condition>
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
																											<template match="n1:TrainingFlag" matchtype="schemagraphitem">
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:KeyPersonsRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:KeyStudyPerson  )  &gt;0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Study Personnel">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																						<children>
																							<text fixtext="PersonName">
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Role">
																								<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<styles font-family="Arial" font-size="10pt"/>
																						<children>
																							<text fixtext="Affiliation">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="25%"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:KeyStudyPerson" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
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
																										<properties width="25%"/>
																										<children>
																											<template match="n1:Role" matchtype="schemagraphitem">
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
																										<children>
																											<template match="n1:Affiliation" matchtype="schemagraphitem">
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
																										<children>
																											<template match="n1:TrainingFlag" matchtype="schemagraphitem">
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:CorrespondentsRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Correspondent  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Correspondents ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Type">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Person"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="50%"/>
																						<children>
																							<text fixtext="Comments"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Correspondent" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<template match="n1:CorrespondentTypeDesc" matchtype="schemagraphitem">
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
																										<properties width="50%"/>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:Comments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:ResearchAreasRequired  = 1">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:ResearchArea  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Areas of Research ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:ResearchArea" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:ResearchAreaCode" matchtype="schemagraphitem">
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
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:ResearchAreaDescription" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:FundingSourcesRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:FundingSource  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Funding Source">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Type">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Number/Code">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="50%"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:FundingSource" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<properties width="25%"/>
																										<children>
																											<template match="n1:TypeOfFundingSource" matchtype="schemagraphitem">
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
																										<children>
																											<template match="n1:FundingSource" matchtype="schemagraphitem">
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
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:FundingSourceName" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:SpeciesGroupRequired = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Species  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Species / Groups">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:Species" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<styles font-family="Arial" font-size="9pt"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Group:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:SpeciesGroup" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Species:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:SpeciesDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Species Strain:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:Strain" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Pain Category:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:PainCategoryDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="USDA Covered Type:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:IsUsdaCovered" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Count Type :"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:CountTypeDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Count:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:SpeciesCount" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="21%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<text fixtext="Exception:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:ExceptionPresent" matchtype="schemagraphitem">
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
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-weight="bold"/>
																								<children>
																									<tablecell>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  n1:Exception  )  &gt; 0">
																														<children>
																															<text fixtext="Exceptions: ">
																																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell/>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="5%"/>
																									</tablecell>
																									<tablecell>
																										<children>
																											<table>
																												<properties border="1" cellpadding="0" cellspacing="0"/>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<styles font-weight="bold"/>
																																<children>
																																	<tablecell headercell="1">
																																		<properties width="25%"/>
																																		<styles font-weight="bold"/>
																																		<children>
																																			<text fixtext="Exception Category"/>
																																		</children>
																																	</tablecell>
																																	<tablecell headercell="1">
																																		<properties width="75%"/>
																																		<children>
																																			<text fixtext="Description"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="n1:Exception" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<children>
																																					<template match="n1:ExceptionCategoryDesc" matchtype="schemagraphitem">
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
																																				<properties width="75%"/>
																																				<children>
																																					<template match="n1:Description" matchtype="schemagraphitem">
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
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:ProceduresRequired  =1">
										<children>
											<newline/>
											<paragraph paragraphtag="p"/>
											<text fixtext="Procedures">
												<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
											</text>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:StudyGroup  )  &gt; 0">
														<children>
															<newline/>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:StudyGroup" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Group:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" width="85%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:SpeciesGroup" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Species:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" width="85%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:SpeciesDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Procedure Category:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" width="85%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:ProcedureCategoryDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Procedure:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" width="85%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:ProcedureDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Pain Category:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" width="85%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:PainCategoryDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell headercell="1">
																										<properties align="left" width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Count :"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties align="left" width="85%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:Count" matchtype="schemagraphitem">
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
																					</tablebody>
																				</children>
																			</table>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<properties colspan="2" width="100%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  n1:Location  )  &gt; 0">
																														<children>
																															<text fixtext="Locations ">
																																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="2%"/>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="5%"/>
																									</tablecell>
																									<tablecell>
																										<children>
																											<table>
																												<properties border="1" cellpadding="0" cellspacing="0" width="95%"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																<children>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Location Type"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="25%"/>
																																		<children>
																																			<text fixtext="Location Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="20%"/>
																																		<children>
																																			<text fixtext="Room"/>
																																		</children>
																																	</tablecell>
																																	<tablecell>
																																		<properties width="30%"/>
																																		<children>
																																			<text fixtext="Description"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="n1:Location" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<styles font-family="Arial" font-size="9pt"/>
																																		<children>
																																			<tablecell>
																																				<properties width="25%"/>
																																				<children>
																																					<template match="n1:LocationTypeDesc" matchtype="schemagraphitem">
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
																																				<children>
																																					<template match="n1:LocationName" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																						</children>
																																					</template>
																																					<newline/>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="20%"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="n1:LocationRoom" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="5"/>
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</paragraph>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="30%"/>
																																				<children>
																																					<paragraph paragraphtag="pre-wrap">
																																						<children>
																																							<template match="n1:Description" matchtype="schemagraphitem">
																																								<editorproperties elementstodisplay="5"/>
																																								<children>
																																									<content>
																																										<format datatype="string"/>
																																									</content>
																																								</children>
																																							</template>
																																						</children>
																																					</paragraph>
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
																									<tablecell/>
																								</children>
																							</tablerow>
																							<tablerow>
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<properties colspan="2" width="100%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  n1:PersonResponsible  )  &gt; 0">
																														<children>
																															<text fixtext="Persons Responsible">
																																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell/>
																								</children>
																							</tablerow>
																							<tablerow>
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<properties width="5%"/>
																									</tablecell>
																									<tablecell>
																										<properties width="100%"/>
																										<children>
																											<table>
																												<properties border="1" cellpadding="0" cellspacing="0" width="47.5%"/>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																<children>
																																	<tablecell headercell="1">
																																		<properties width="85%"/>
																																		<styles font-weight="normal"/>
																																		<children>
																																			<text fixtext="Investigators/Study Personnel">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																	<tablecell headercell="1">
																																		<properties width="15%"/>
																																		<styles font-weight="normal"/>
																																		<children>
																																			<text fixtext="Trained">
																																				<styles font-weight="bold"/>
																																			</text>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="n1:PersonResponsible" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="85%"/>
																																				<styles font-weight="normal"/>
																																				<children>
																																					<template match="n1:InvestigatorKeyPerson" matchtype="schemagraphitem">
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
																																				<styles font-weight="normal"/>
																																				<children>
																																					<template match="n1:PersonTrainedFlag" matchtype="schemagraphitem">
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
																									<tablecell/>
																								</children>
																							</tablerow>
																							<tablerow>
																								<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<properties colspan="2" width="100%"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="count(  n1:CustomData  ) &gt;0">
																														<children>
																															<text fixtext="Others">
																																<styles font-family="Arial" font-size="10pt" font-weight="bold"/>
																															</text>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell/>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="5%"/>
																									</tablecell>
																									<tablecell>
																										<children>
																											<table>
																												<styles font-family="Arial" font-size="9pt"/>
																												<children>
																													<tablebody>
																														<children>
																															<template match="n1:CustomData" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<children>
																																			<tablecell>
																																				<properties width="40%"/>
																																				<styles font-weight="bold"/>
																																				<children>
																																					<template match="n1:ColumnName" matchtype="schemagraphitem">
																																						<editorproperties elementstodisplay="5"/>
																																						<children>
																																							<content>
																																								<format datatype="string"/>
																																							</content>
																																							<text fixtext=":"/>
																																						</children>
																																					</template>
																																				</children>
																																			</tablecell>
																																			<tablecell>
																																				<properties width="60%"/>
																																				<children>
																																					<template match="n1:ColumnValue" matchtype="schemagraphitem">
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
																									<tablecell/>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<paragraph paragraphtag="p"/>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<table>
												<properties border="0" width="100%"/>
												<styles font-family="Arial" font-size="9pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties width="20%"/>
																		<styles font-weight="bold"/>
																		<children>
																			<text fixtext="Overview and Timeline:"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="n1:OverviewTimeline" matchtype="schemagraphitem">
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
																</children>
															</tablerow>
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
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:PrinciplesRequired =1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Principles  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Scientific Justification ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:Principles" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																								<children>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Principles of Reduction:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="80%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:ReductionPrinciple" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Principles of Refinement: "/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="80%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:RefinementPrinciple" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Principles of Replacement: "/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="80%"/>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:ReplacementPrinciple" matchtype="schemagraphitem">
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
															</template>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:AlternativeSearchesRequired =1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:AlternateDbSearch  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Alternative Searches">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:AlternateDbSearch" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<newline/>
																			<table>
																				<properties border="0" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="20%"/>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Search Date:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:SearchDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="dateTime"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Database Searched:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<template match="n1:DatabasDesc" matchtype="schemagraphitem">
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
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Years Searched: "/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:YearsSearched" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Keywords Searched:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:KeywordsSearched" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																										<children>
																											<text fixtext="Comments:"/>
																										</children>
																									</tablecell>
																									<tablecell>
																										<styles font-family="Arial" font-size="9pt"/>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:Comments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																						</children>
																					</tablebody>
																				</children>
																			</table>
																			<paragraph paragraphtag="p"/>
																		</children>
																	</template>
																</children>
															</template>
															<newline/>
															<newline/>
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:ActionsRequired  =1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Actions  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Actions">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Actions" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:ActionTypeDesc" matchtype="schemagraphitem">
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
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:Comments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:ActionDate" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="date"/>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:DocumentsRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Documents  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="New/Changed Attachments">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Documents" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:DocumentTypeDesc" matchtype="schemagraphitem">
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
																											<template match="n1:UpdateTimestamp" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="dateTime"/>
																													</content>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:UpdateUser" matchtype="schemagraphitem">
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
															<paragraph paragraphtag="p"/>
														</children>
													</conditionbranch>
												</children>
											</condition>
											<newline/>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:SpecialReviewRequired =1">
										<children>
											<newline/>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:SpecialReview  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Special Review ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties height="18"/>
																						<children>
																							<text fixtext="Special Review">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="18"/>
																						<children>
																							<text fixtext="Approval">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="18"/>
																						<children>
																							<text fixtext="Protocol No">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="18"/>
																						<children>
																							<text fixtext="Application Date">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="18"/>
																						<children>
																							<text fixtext="Approval Date">
																								<styles font-family="Arial" font-size="10pt"/>
																							</text>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties height="18"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:SpecialReview" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:SpecialReviewTypeDesc" matchtype="schemagraphitem">
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
																											<template match="n1:SpecialReviewApprovalTypeDesc" matchtype="schemagraphitem">
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
																											<template match="n1:SpecialReviewProtocolNumber" matchtype="schemagraphitem">
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
																											<template match="n1:SpecialReviewApplicationDate" matchtype="schemagraphitem">
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
																											<template match="n1:SpecialReviewApprovalDate" matchtype="schemagraphitem">
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
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:SpecialReviewComments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:NotesRequired = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:Notes  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Protocol Notes">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:Notes" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:Comments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:UpdateUser" matchtype="schemagraphitem">
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
																											<template match="n1:UpdateTimestamp" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format string="MM/DD/YYYY" datatype="dateTime"/>
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
											<text fixtext=" ">
												<styles font-family="Arial" font-size="10pt"/>
											</text>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:UserRolesRequired =1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:UserRoles  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Roles">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<table>
																<properties border="0" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell headercell="1">
																						<properties colspan="4"/>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:UserRoles" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																								<children>
																									<tablecell>
																										<properties colspan="4"/>
																										<children>
																											<paragraph paragraphtag="p"/>
																											<condition>
																												<children>
																													<conditionbranch xpath="string-length(  n1:UserRoles  )  = 0"/>
																													<conditionbranch>
																														<children>
																															<template match="n1:RoleName" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<styles font-weight="bold"/>
																																		<format datatype="string"/>
																																	</content>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																											<text fixtext=" "/>
																											<newline/>
																											<table>
																												<properties border="1" cellpadding="0" cellspacing="0"/>
																												<children>
																													<tableheader>
																														<children>
																															<tablerow>
																																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																																<children>
																																	<tablecell headercell="1">
																																		<children>
																																			<text fixtext="User Id"/>
																																		</children>
																																	</tablecell>
																																	<tablecell headercell="1">
																																		<children>
																																			<text fixtext="Unit Name"/>
																																		</children>
																																	</tablecell>
																																	<tablecell headercell="1">
																																		<children>
																																			<text fixtext="User Name"/>
																																		</children>
																																	</tablecell>
																																</children>
																															</tablerow>
																														</children>
																													</tableheader>
																													<tablebody>
																														<children>
																															<template match="n1:UserRoles" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<tablerow>
																																		<styles font-family="Arial" font-size="9pt" font-weight="normal"/>
																																		<children>
																																			<tablecell>
																																				<children>
																																					<template match="n1:UserId" matchtype="schemagraphitem">
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
																																			<tablecell>
																																				<children>
																																					<template match="n1:UserName" matchtype="schemagraphitem">
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:ReferencesRequired  =1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:References  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Reference List ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:References" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:ReferenceTypeDesc" matchtype="schemagraphitem">
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
																											<template match="n1:ReferenceNumber" matchtype="schemagraphitem">
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
																											<template match="n1:ApplicationDate" matchtype="schemagraphitem">
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
																											<template match="n1:ApprovalDate" matchtype="schemagraphitem">
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
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:Comments" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:AmendRenewSRequired =1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:AmenRenewal  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Amendment/Renewal">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
																				<children>
																					<tablecell headercell="1">
																						<properties width="30%"/>
																						<children>
																							<text fixtext="Type"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties width="6%"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:AmenRenewal" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<styles font-family="Arial" font-size="9pt"/>
																								<children>
																									<tablecell>
																										<properties width="30%"/>
																										<children>
																											<template match="n1:AmendmentType" matchtype="schemagraphitem">
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
																										<properties width="6%"/>
																										<children>
																											<template match="n1:Version" matchtype="schemagraphitem">
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
																									<tablecell>
																										<children>
																											<template match="n1:DateCreated" matchtype="schemagraphitem">
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
																											<template match="n1:Summary" matchtype="schemagraphitem">
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
														</children>
													</conditionbranch>
												</children>
											</condition>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:AmendRenewModulesRequired  = 1">
										<children>
											<newline/>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:AmendRenewSummary  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p"/>
															<text fixtext="Amendment/Renewal Summary">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="1" cellpadding="0" cellspacing="0"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<styles font-weight="bold"/>
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
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:AmendRenewSummary" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<children>
																											<paragraph paragraphtag="pre-wrap">
																												<children>
																													<template match="n1:Summary" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<content>
																																<format datatype="string"/>
																															</content>
																														</children>
																													</template>
																												</children>
																											</paragraph>
																										</children>
																									</tablecell>
																									<tablecell>
																										<children>
																											<template match="n1:ProtocolModules" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="n1:ProtocolModuleDesc" matchtype="schemagraphitem">
																														<editorproperties elementstodisplay="5"/>
																														<children>
																															<list>
																																<children>
																																	<listrow/>
																																</children>
																															</list>
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="n1:Protocol/n1:PrintRequirement/n1:OtherDataRequired  = 1">
										<children>
											<condition>
												<children>
													<conditionbranch xpath="count(  n1:Protocol/n1:OthersData  )  &gt; 0">
														<children>
															<paragraph paragraphtag="p">
																<styles padding-top="10pt"/>
																<children>
																	<text fixtext=" ">
																		<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
																	</text>
																</children>
															</paragraph>
															<text fixtext="Others ">
																<styles font-family="Arial" font-size="12pt" font-weight="bold"/>
															</text>
															<newline/>
															<table>
																<properties border="0"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<template match="n1:Protocol" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="n1:OthersData" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties width="40%"/>
																										<styles font-weight="bold"/>
																										<children>
																											<template match="n1:ColumnName" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<content>
																														<format datatype="string"/>
																													</content>
																													<text fixtext=":"/>
																												</children>
																											</template>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties width="60%"/>
																										<children>
																											<template match="n1:ColumnValue" matchtype="schemagraphitem">
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
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<newline/>
						</children>
					</template>
					<newline/>
					<newline/>
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
					<template match="$XML" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-family="Arial" font-size="9pt" table-layout="fixed"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="18" width="30%"/>
														<styles padding="0" padding-bottom="0"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="4" height="18" width="30%"/>
														<styles padding="0" padding-bottom="0"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:SchoolInfo" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:Acronym" matchtype="schemagraphitem">
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
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-family="Arial" font-size="9pt" font-weight="bold" padding-bottom="0"/>
														<children>
															<text fixtext=" Protocol Number: ">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="bottom" width="30%"/>
														<styles font-family="Arial" padding-bottom="0" padding-left="0" padding-top="1pt"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
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
																	</template>
																</children>
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties align="right" valign="top" width="40%"/>
														<styles font-size="9pt" font-weight="bold" padding-bottom="0" padding-right="5pt"/>
														<children>
															<text fixtext="Expiration Date: ">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="15%"/>
														<styles font-size="9pt" padding-bottom="0"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:ExpirationDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
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
														<properties align="left" valign="top" width="15%"/>
														<styles font-weight="bold" padding-top="0"/>
														<children>
															<text fixtext="Investigator:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" valign="top" width="30%"/>
														<styles font-family="Arial" padding-left="0" padding-top="0"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:PrincipleInvestigatorName" matchtype="schemagraphitem">
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
														<properties align="right" valign="top" width="40%"/>
														<styles font-weight="bold" padding-right="6pt" padding-top="0"/>
														<children>
															<text fixtext="Last Approval Date:">
																<styles font-family="Arial" font-size="9pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties valign="bottom" width="15%"/>
														<styles font-size="9pt" padding-top="0"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="n1:ApprovalDate" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<content>
																						<format string="MM/DD/YYYY" datatype="date"/>
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
														<properties align="left" colspan="4" valign="top" width="30%"/>
														<styles font-family="Verdana" font-size="9pt" padding="0" padding-top="0"/>
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
					<newline/>
					<newline/>
				</children>
			</globaltemplate>
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

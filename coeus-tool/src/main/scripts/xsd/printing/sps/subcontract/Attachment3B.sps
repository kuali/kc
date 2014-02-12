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
			<nspair prefix="award" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/award"/>
			<nspair prefix="subcontract" uri="http://subcontractFdpReports.bean.xml.utils.coeus.mit.edu/subcontract"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="SubcontractFdpReports.xsd" workingxmlfile="Subcontract_Fdp_Report.xml">
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
								<properties border="0" width="90%"/>
								<styles font-family="Arial" font-size="9pt"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<properties valign="top"/>
												<styles font-family="Arial"/>
												<children>
													<tablecell>
														<properties align="right" colspan="9" height="32" valign="top" width="90%"/>
														<styles font-family="Arial" font-size="9pt"/>
														<children>
															<paragraph paragraphtag="center">
																<children>
																	<text fixtext=" "/>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Attachment 3B - Research Subaward Agreement"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<paragraph paragraphtag="center">
																<children>
																	<paragraph paragraphtag="h4">
																		<children>
																			<text fixtext="Subrecipient Contacts"/>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<text fixtext="Subaward Number:"/>
															<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="subcontract:PONumber" matchtype="schemagraphitem">
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
															<text fixtext="Institution/Organization (&quot;Subrecipient&quot;) "/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Name:  "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorName" matchtype="schemagraphitem">
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
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                 "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																							</template>
																							<text fixtext="                 "/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="City:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:City" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<children>
																							<text fixtext="State:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="9" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																						<children>
																							<text fixtext="Zip Code +4: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorOrgRolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="EIN No.:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:FedralEmployerId" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="3" width="30%"/>
																					</tablecell>
																					<tablecell>
																						<properties width="15%"/>
																						<styles font-family="Arial" font-size="9pt"/>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="6" width="18%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="3" width="100%"/>
																						<children>
																							<text fixtext="Is the Performance Site the Same Address as Above? "/>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteDiffFromOrgAddr = &quot;Y&quot;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteDiffFromOrgAddr = &quot;N&quot;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<text fixtext="If no, is the Performance Site the same as PI address below? "/>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteSameAsSubPiAddr = &quot;Y&quot;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:PerfSiteSameAsSubPiAddr = &quot;N&quot;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<text fixtext="If no to both questions, please complete 3B page 2 (if ARRA funding use Attachment 4A)."/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="60%"/>
																						<children>
																							<text fixtext="Subrecipient currently registered in CCR? "/>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SubRegisteredInCcr = &quot;Y&quot;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:SubRegisteredInCcr = &quot;N&quot;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="DUNS No.:"/>
																							<newline/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:DunsNumber" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="Parent DUNS No.:"/>
																							<newline/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:ParentDunsNumber" matchtype="schemagraphitem">
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
																						<properties width="60%"/>
																						<children>
																							<text fixtext="Is Subrecipient exempt from reporting compensation? "/>
																							<condition>
																								<children>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;Y&quot;">
																										<children>
																											<text fixtext="Yes"/>
																										</children>
																									</conditionbranch>
																									<conditionbranch xpath="subcontract:SubContractData/subcontract:SubcontractTemplateInfo/subcontract:ExemptFromRprtgExecComp = &quot;N&quot;">
																										<children>
																											<text fixtext="No"/>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																							<newline/>
																							<text fixtext="If no , please complete 3B page 2 (if ARRA funding use Attachment 4A)."/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="Congressional District:"/>
																							<newline/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SubcontractorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:CongressionalDistrict" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="20%"/>
																						<children>
																							<text fixtext="Congressional District:"/>
																							<newline/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractTemplateInfo" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:ParentCongressionalDistrict" matchtype="schemagraphitem">
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
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<newline/>
															<text fixtext="Administrative Contact"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Name: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                  "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="City:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:City" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<children>
																							<text fixtext="State:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="9" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Zip Code: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" width="8%"/>
																						<children>
																							<text fixtext="Telephone:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="5%"/>
																						<children>
																							<text fixtext="Fax:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="4" width="40%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="6" width="15%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="12" width="60%"/>
																						<children>
																							<text fixtext="Email: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AdministrativeContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Email" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<newline/>
															<text fixtext="Principal Investigator"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Name: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                 "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="City:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:City" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<children>
																							<text fixtext="State:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="9" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Zip Code: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" width="8%"/>
																						<children>
																							<text fixtext="Telephone:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="5%"/>
																						<children>
																							<text fixtext="Fax:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="4" width="40%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="6" width="15%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="12" width="60%"/>
																						<children>
																							<text fixtext="Email: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:SubcontractDetail" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:SiteInvestigatorDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Email" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<newline/>
															<text fixtext="Financial Contact"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Name: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                 "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="City:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:City" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<children>
																							<text fixtext="State:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="9" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Zip Code: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" width="8%"/>
																						<children>
																							<text fixtext="Telephone: "/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="5%"/>
																						<children>
																							<text fixtext="Fax:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="4" width="40%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="6" width="15%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="12" width="60%"/>
																						<children>
																							<text fixtext="Email: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:FinancialContact" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Email" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
															<line>
																<properties color="black" size="1"/>
															</line>
															<text fixtext="Authorized Official"/>
															<newline/>
															<table>
																<properties border="0" width="100%"/>
																<styles font-family="Arial" font-size="9pt"/>
																<children>
																	<tablebody>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Name: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:RolodexName" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="13" width="98%"/>
																						<children>
																							<text fixtext="Address: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address1" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																							<text fixtext="                 "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Address2" matchtype="schemagraphitem">
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
																							</template>
																							<newline/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties width="8%"/>
																						<children>
																							<text fixtext="City:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:City" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="5%"/>
																						<children>
																							<text fixtext="State:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="9" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:StateDescription" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																						<children>
																							<text fixtext="Zip Code: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Pincode" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties align="left" width="8%"/>
																						<children>
																							<text fixtext="Telephone:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="30%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:PhoneNumber" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" width="5%"/>
																						<children>
																							<text fixtext="Fax:"/>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="4" width="40%"/>
																						<children>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Fax" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties align="left" colspan="6" width="15%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																			<tablerow>
																				<children>
																					<tablecell>
																						<properties colspan="12" width="60%"/>
																						<children>
																							<text fixtext="Email: "/>
																							<template match="subcontract:SubContractData" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<template match="subcontract:AuthorizedOfficial" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="5"/>
																										<children>
																											<template match="subcontract:RolodexDetails" matchtype="schemagraphitem">
																												<editorproperties elementstodisplay="5"/>
																												<children>
																													<template match="subcontract:Email" matchtype="schemagraphitem">
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
																							</template>
																						</children>
																					</tablecell>
																					<tablecell>
																						<properties width="25%"/>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tablebody>
																</children>
															</table>
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
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientation="landscape" pageorientationportrait="portrait" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.2in" papermarginright="0.2in" papermargintop="0.1in" paperwidth="8.5in"/>
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

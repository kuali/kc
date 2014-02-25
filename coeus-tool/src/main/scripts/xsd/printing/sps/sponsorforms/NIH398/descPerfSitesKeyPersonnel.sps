<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="common" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace"/>
			<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
			<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
			<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="phs398schema.xsd" workingxmlfile="nihproposalSample.xml">
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
		<pagelayouts>
			<pagelayout match="#footerall" isactive="0"/>
			<pagelayout match="#footereven" isactive="1"/>
			<pagelayout match="#footerodd" isactive="1"/>
		</pagelayouts>
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
						<editorproperties elementstodisplay="5"/>
						<children>
							<newline/>
							<table>
								<properties border="1" cellspacing="0" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties height="3.4in" valign="top"/>
														<children>
															<text fixtext="PROJECT SUMMARY (See instructions):">
																<styles font-size="8pt"/>
															</text>
															<newline/>
															<paragraph paragraphtag="p">
																<children>
																	<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="Abstract" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AbstractText" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../AbstractTypeCode =1">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="8pt"/>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</paragraph>
															<newline/>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="1.4in" valign="top"/>
														<children>
															<text fixtext="RELEVANCE (See instructions):">
																<styles font-size="8pt"/>
															</text>
															<paragraph paragraphtag="p">
																<children>
																	<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="Abstract" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="AbstractText" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="../AbstractTypeCode =17">
																										<children>
																											<content>
																												<styles font-family="Verdana" font-size="8pt"/>
																												<format datatype="string"/>
																											</content>
																										</children>
																									</conditionbranch>
																								</children>
																							</condition>
																						</children>
																					</template>
																				</children>
																			</template>
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
							<text fixtext="PROJECT/PERFORMANCE SITE(S) (if additional space is needed, use Project/Performance Site Format Page)">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<table>
								<properties border="1" cellspacing="0" width="100%"/>
								<styles font-size="8pt"/>
								<children>
									<tablebody>
										<styles padding="0"/>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10pt"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Project/Performance Site Primary Location">
																<styles font-size="8pt" font-weight="bold" line-height="normal"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10pt"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Organizational Name:">
																<styles font-size="8pt" line-height="9pt"/>
															</text>
															<text fixtext="  "/>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="OrganizationName" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<styles font-family="Verdana"/>
																								<format datatype="token"/>
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
														<properties colspan="3" height="10pt"/>
														<styles padding="0"/>
														<children>
															<text fixtext="DUNS: ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="10pt"/>
														<styles line-height="inherit"/>
														<children>
															<text fixtext="Street1:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/PrimaryProjectSite/PostalAddress/Street[1]">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" height="10pt"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="Street2:">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="   "/>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/PrimaryProjectSite/PostalAddress/Street[2]">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="10pt"/>
														<styles line-height="inherit"/>
														<children>
															<text fixtext="City: ">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="City" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Verdana"/>
																										<format datatype="token"/>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10pt"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="County: ">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CountyName" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<styles font-family="Verdana"/>
																								<format datatype="token"/>
																							</content>
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</template>
																</children>
															</template>
															<text fixtext=" ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="State:">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="State" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Verdana"/>
																										<format datatype="token"/>
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
															</template>
															<text fixtext=" ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<styles line-height="inherit"/>
												<children>
													<tablecell>
														<properties height="10pt"/>
														<styles line-height="inherit"/>
														<children>
															<text fixtext="Province:">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10pt"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="Country:  ">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="Country" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Verdana"/>
																										<format datatype="token"/>
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
															</template>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="Zip/Postal Code:">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="PostalAddress" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<template match="PostalCode" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-family="Verdana" font-size="8pt"/>
																										<format datatype="token"/>
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10pt"/>
														<styles padding="0"/>
														<children>
															<text fixtext="Project/Performance Site Congressional Districts:">
																<styles font-size="8pt"/>
															</text>
															<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="PrimaryProjectSite" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="CongressionalDistrict" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<format datatype="token"/>
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
										</children>
									</tablebody>
								</children>
							</table>
							<text fixtext=" "/>
							<newline/>
							<table>
								<properties border="1" cellpadding="0" cellspacing="0" height="1.4in" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10" valign="top"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="Additional Project/Performance Site Location">
																<styles font-size="8pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10" valign="top"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="Organizational Name:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/OrganizationName">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10" valign="top"/>
														<styles line-height="normal"/>
														<children>
															<text fixtext="DUNS: ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="10" valign="top"/>
														<children>
															<text fixtext="Street1: ">
																<styles font-size="8pt"/>
															</text>
															<text fixtext=" "/>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/PostalAddress/Street[1]">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="2" height="10"/>
														<children>
															<text fixtext="Street2:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/PostalAddress/Street[2]">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="10" valign="top"/>
														<children>
															<text fixtext="City:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/PostalAddress/City">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10"/>
														<children>
															<text fixtext="County:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/CountyName">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10"/>
														<children>
															<text fixtext="State: ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/PostalAddress/State">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties height="10"/>
														<children>
															<text fixtext="Province:">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10"/>
														<children>
															<text fixtext="Country:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/PostalAddress/Country">
																<styles font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
													<tablecell>
														<properties height="10"/>
														<children>
															<text fixtext="Zip/Postal Code:  ">
																<styles font-size="8pt"/>
															</text>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/PostalAddress/PostalCode">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties colspan="3" height="10"/>
														<children>
															<text fixtext="Project/Performance Site Congressional Districts:">
																<styles font-size="8pt"/>
															</text>
															<text fixtext="  "/>
															<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[1]/CongressionalDistrict">
																<styles font-family="Verdana" font-size="8pt"/>
															</autocalc>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<newline break="page"/>
							<line>
								<properties size="0.5pt"/>
							</line>
							<newline/>
							<text fixtext="SCIENTIFIC/KEY PERSONNEL. See instructions. Use continuation pages as needed to providce the required information in the format shown below. Start with Program Director/Principal Investigator(s). List all other key personnel in alphabetical order, last name first.">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<table>
								<properties border="0" cellpadding="0" cellspacing="0" height="2.4in" width="100%"/>
								<styles vertical-align="top"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties height="2.4in" valign="top"/>
														<children>
															<table>
																<properties cellpadding="0" cellspacing="0"/>
																<styles font-size="8pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell headercell="1">
																						<properties valign="top"/>
																						<children>
																							<text fixtext="Name"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<properties valign="top"/>
																						<children>
																							<text fixtext="eRA Commons User Name"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<children>
																							<text fixtext="OrganizationName"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<children>
																							<text fixtext="Role On Project"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<properties valign="top"/>
																		<children>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="KeyPerson" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<properties valign="top"/>
																								<children>
																									<tablecell>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;true&quot;">
																														<children>
																															<template match="Name" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="FirstName" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<content>
																																				<styles font-family="Verdana"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" "/>
																																	<template match="LastName" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<content>
																																				<styles font-family="Verdana"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
																																</children>
																															</template>
																														</children>
																													</conditionbranch>
																												</children>
																											</condition>
																										</children>
																									</tablecell>
																									<tablecell>
																										<properties valign="top"/>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;true&quot; and nih:AccountIdentifier != &apos;Unknown&apos;">
																														<children>
																															<template match="nih:AccountIdentifier" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<styles font-family="Verdana"/>
																																		<format datatype="string"/>
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
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;true&quot;">
																														<children>
																															<template match="OrganizationName" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<styles font-family="Verdana" font-size="8pt"/>
																																		<format datatype="string"/>
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
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;true&quot; and  keyPersonFlag/keyPersonFlagDesc = &apos;PI&apos;">
																														<children>
																															<text fixtext="Principal Investigator"/>
																														</children>
																													</conditionbranch>
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode = &quot;true&quot; and  keyPersonFlag/keyPersonFlagDesc != &apos;PI&apos;">
																														<children>
																															<template match="nih:RoleOnProject" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<format datatype="token"/>
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
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<line>
								<properties size="0.5pt"/>
							</line>
							<newline/>
							<text fixtext="OTHER SIGNIFICANT CONTRIBUTORS">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<table>
								<properties border="0" cellspacing="0" height="2.4in" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties height="2.4in" valign="top"/>
														<children>
															<table>
																<properties border="0"/>
																<styles font-size="8pt"/>
																<children>
																	<tableheader>
																		<children>
																			<tablerow>
																				<children>
																					<tablecell headercell="1">
																						<properties valign="top"/>
																						<children>
																							<text fixtext="Name"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<children>
																							<text fixtext="Organization Name"/>
																						</children>
																					</tablecell>
																					<tablecell headercell="1">
																						<children>
																							<text fixtext="Role On Project"/>
																						</children>
																					</tablecell>
																				</children>
																			</tablerow>
																		</children>
																	</tableheader>
																	<tablebody>
																		<children>
																			<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="KeyPerson" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<children>
																											<condition>
																												<children>
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;false&quot;">
																														<children>
																															<template match="Name" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<template match="FirstName" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<content>
																																				<styles font-family="Verdana"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
																																	<text fixtext=" "/>
																																	<template match="LastName" matchtype="schemagraphitem">
																																		<editorproperties elementstodisplay="5"/>
																																		<children>
																																			<content>
																																				<styles font-family="Verdana"/>
																																				<format datatype="token"/>
																																			</content>
																																		</children>
																																	</template>
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
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;false&quot;">
																														<children>
																															<template match="OrganizationName" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<styles font-family="Verdana"/>
																																		<format datatype="string"/>
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
																													<conditionbranch xpath="keyPersonFlag/keyPersonFlagCode =&quot;false&quot;">
																														<children>
																															<template match="nih:RoleOnProject" matchtype="schemagraphitem">
																																<editorproperties elementstodisplay="5"/>
																																<children>
																																	<content>
																																		<styles font-family="Verdana"/>
																																		<format datatype="token"/>
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
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<newline/>
							<line>
								<properties size="0.5pt"/>
							</line>
							<text fixtext="Human Embyonic Stem Cells">
								<styles font-size="8pt" font-weight="bold"/>
							</text>
							<text fixtext="    ">
								<styles font-weight="bold"/>
							</text>
							<condition>
								<children>
									<conditionbranch xpath="boolean( nih:ResearchAndRelatedProject/nih:ProjectDescription/rar:ProjectSurvey/StemCellQuestion )">
										<children>
											<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="nih:ProjectDescription" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="rar:ProjectSurvey" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="StemCellQuestion" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<checkbox checkedvalue="false" checkedvalue1="0" uncheckedvalue="true">
																				<children>
																					<content>
																						<format datatype="boolean"/>
																					</content>
																				</children>
																			</checkbox>
																			<text fixtext="No   ">
																				<styles font-size="8pt" font-weight="bold"/>
																			</text>
																		</children>
																	</template>
																	<template match="StemCellQuestion" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<checkbox checkedvalue="true" checkedvalue1="1">
																				<children>
																					<content>
																						<format datatype="boolean"/>
																					</content>
																				</children>
																			</checkbox>
																			<text fixtext=" ">
																				<styles font-weight="bold"/>
																			</text>
																			<text fixtext="Yes">
																				<styles font-size="8pt" font-weight="bold"/>
																			</text>
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
									<conditionbranch>
										<children>
											<checkbox checkedvalue="false" checkedvalue1="0" uncheckedvalue="true">
												<children>
													<content/>
												</children>
											</checkbox>
											<text fixtext="No   ">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<checkbox checkedvalue="true" checkedvalue1="1">
												<children>
													<content/>
												</children>
											</checkbox>
											<text fixtext=" ">
												<styles font-weight="bold"/>
											</text>
											<text fixtext="Yes">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
										</children>
									</conditionbranch>
								</children>
							</condition>
							<newline/>
							<text fixtext="If the proposed project involves human embryonic stem cells, list below the registration number of the specific cell line(s) from the following list:">
								<styles font-size="8pt" font-weight="bold" line-height="9pt"/>
							</text>
							<newline/>
							<text fixtext="http://stemcells.nih.gov/research/registry/eligibilityCriteria.asp. Use continuation pages as needed.">
								<styles font-size="8pt" line-height="9pt"/>
							</text>
							<newline/>
							<text fixtext="If a specific line cannot be referenced at this time, include a statement that one from the Registry will be used.">
								<styles font-size="8pt" line-height="9pt" margin-bottom="0" padding-bottom="0"/>
							</text>
							<newline/>
							<line>
								<properties size="0.5pt"/>
								<styles margin-top="0" padding-top="0"/>
							</line>
							<text fixtext="Cell Line">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="5"/>
								<children>
									<template match="nih:ProjectDescription" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="5"/>
										<children>
											<template match="rar:ProjectSurvey" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="5"/>
												<children>
													<template match="StemCellText" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<content>
																<styles font-family="Verdana" font-size="8pt"/>
																<format datatype="token"/>
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
							<newline/>
							<newline/>
							<newline/>
							<newline/>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties papermarginbottom="1.1in" papermargintop=".6in"/>
		<styles font-size="8pt"/>
		<children>
			<globaltemplate match="#footereven" matchtype="named" parttype="footereven">
				<children>
					<table>
						<properties width="100%"/>
						<styles line-height="9pt" padding="0"/>
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
										<styles margin-bottom="0" padding-bottom="0"/>
										<children>
											<tablecell>
												<properties align="left"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="PHS 398 (Rev 6/09)                                                       Page ">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="3">
														<styles font-size="8pt" text-decoration="underline"/>
													</text>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" width="110"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="Form Page 2-continued ">
														<styles font-size="8pt"/>
													</text>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<styles font-size="6pt" margin-top="0" padding="0" padding-top="0"/>
										<children>
											<tablecell>
												<properties align="right"/>
												<styles border-spacing="0" font-size="7pt" line-height="6pt" margin-top="0" padding="0" padding-right="0" padding-top="0"/>
												<children>
													<text fixtext=" Number the following pages consecutively throughout"/>
													<newline/>
													<text fixtext=" the application. Do not use suffixes such as 4a, 4b."/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" width="150"/>
												<styles font-size="smaller" padding="0"/>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</globaltemplate>
			<globaltemplate match="#footerodd" matchtype="named" parttype="footerodd">
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
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="PHS 398 (Rev 6/09)                                                       Page  ">
														<styles font-size="8pt"/>
													</text>
													<text fixtext="2">
														<styles font-size="8pt" text-decoration="underline"/>
													</text>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" width="150"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="Form Page 2 ">
														<styles font-size="8pt"/>
													</text>
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
			<globaltemplate match="#headerall" matchtype="named" parttype="headerall">
				<children>
					<table>
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<styles color="white"/>
										<children>
											<tablecell>
												<properties colspan="2" height="15"/>
												<styles padding="0"/>
												<children>
													<text fixtext="."/>
													<newline/>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<styles font-family="Traditional Arabic" font-size="8pt"/>
										<children>
											<tablecell>
												<properties colspan="2" height="30"/>
												<styles padding="0"/>
												<children>
													<text fixtext="Program Director/Principal Investigator (Last, First,  Middle): ">
														<styles font-size="8pt"/>
													</text>
													<template match="nih:ResearchAndRelatedProject" matchtype="schemagraphitem">
														<editorproperties elementstodisplay="5"/>
														<children>
															<template match="nih:ResearchCoverPage" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="5"/>
																<children>
																	<template match="nih:ProgramDirectorPrincipalInvestigator" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="5"/>
																		<children>
																			<template match="Name" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="5"/>
																				<children>
																					<template match="LastName" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<format datatype="token"/>
																							</content>
																						</children>
																					</template>
																					<text fixtext=", ">
																						<styles border-top=".3in" font-size="8pt" margin-top="inherit"/>
																					</text>
																					<template match="FirstName" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="5"/>
																						<children>
																							<content>
																								<styles font-family="Verdana" font-size="8pt"/>
																								<format datatype="token"/>
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
													</template>
												</children>
											</tablecell>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecell>
												<properties colspan="2"/>
												<styles padding="0"/>
											</tablecell>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
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
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="PHS 398 (Rev 6/09)                                                       Page ">
														<styles font-size="8pt" font-weight="bold"/>
													</text>
													<autocalc xpath="last()"/>
													<text fixtext="   "/>
													<field type="pagetotal"/>
													<text fixtext=" "/>
													<autocalc xpath="position()"/>
												</children>
											</tablecell>
											<tablecell>
												<properties align="right" width="150"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext=" Form Page 2 ">
														<styles font-size="8pt" font-weight="bold"/>
													</text>
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

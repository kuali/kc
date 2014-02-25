<?xml version="1.0" encoding="UTF-8"?>
<structure version="6" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="common" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/common.namespace"/>
			<nspair prefix="nih" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
			<nspair prefix="phs398" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
			<nspair prefix="rar" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="C:\proposal printing\schemas\phs398schema.xsd" workingxmlfile="Y:\jenlu\locations2.xml">
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
						<editorproperties elementstodisplay="5"/>
						<children>
							<table>
								<properties border="1" height="12pt" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties height="12" width="30%"/>
														<children>
															<text fixtext="Program Director/Principal Investigator: ">
																<styles font-size="8pt"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties align="left" height="12" width="70%"/>
														<children>
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
																										<styles font-size="8pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=", ">
																								<styles font-size="8pt"/>
																							</text>
																							<template match="FirstName" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-size="8pt"/>
																										<format datatype="token"/>
																									</content>
																								</children>
																							</template>
																							<text fixtext=" ">
																								<styles font-size="8pt"/>
																							</text>
																							<template match="MiddleName" matchtype="schemagraphitem">
																								<editorproperties elementstodisplay="5"/>
																								<children>
																									<content>
																										<styles font-size="8pt"/>
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
										</children>
									</tablebody>
								</children>
							</table>
							<text fixtext="Use only if additional space is needed to list additional project/performance sites">
								<styles font-size="8pt"/>
							</text>
							<newline/>
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [2] )">
										<children>
											<newline/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[2]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [3] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[3]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [4] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[4]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [5] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[5]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [6] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[6]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [7] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[7]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [8] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[8]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [9] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[9]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [10] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[10]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [11] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[11]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [12] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[12]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [13] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[13]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [14] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[14]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [15] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[15]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [16] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[16]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [17] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[17]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [18] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[18]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [19] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[19]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
							<condition>
								<children>
									<conditionbranch xpath="boolean(  nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites [20] )">
										<children>
											<paragraph paragraphtag="p"/>
											<text fixtext="Additional Project/Performance Site Location">
												<styles font-size="8pt" font-weight="bold"/>
											</text>
											<newline/>
											<table>
												<properties border="1" cellpadding="0" height="1.4in" width="100%"/>
												<styles font-size="8pt"/>
												<children>
													<tablebody>
														<children>
															<tablerow>
																<children>
																	<tablecell>
																		<properties colspan="3" height="10" valign="top"/>
																		<styles line-height="normal"/>
																		<children>
																			<text fixtext="Organizational Name:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/OrganizationName"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/PostalAddress/Street"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties colspan="2" height="10"/>
																		<children>
																			<text fixtext="Street2:">
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
																			<text fixtext="City:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/PostalAddress/City"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="County:  ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/CountyName"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="State: ">
																				<styles font-size="8pt"/>
																			</text>
																			<text fixtext=" "/>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/PostalAddress/State"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/PostalAddress/Country"/>
																		</children>
																	</tablecell>
																	<tablecell>
																		<properties height="10"/>
																		<children>
																			<text fixtext="Zip/Postal Code:   ">
																				<styles font-size="8pt"/>
																			</text>
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/PostalAddress/PostalCode"/>
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
																			<autocalc xpath="nih:ResearchAndRelatedProject/nih:ResearchCoverPage/AlternateProjectSites[20]/CongressionalDistrict"/>
																		</children>
																	</tablecell>
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
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties papermarginbottom="1in" papermargintop=".6in"/>
		<children>
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
													<text fixtext="PHS 398/2590 (Rev 11/07) ">
														<styles font-size="7pt" font-weight="bold"/>
													</text>
													<text fixtext="                                                           ">
														<styles font-weight="bold"/>
													</text>
													<text fixtext="Page ___">
														<styles font-size="7pt" font-weight="bold"/>
													</text>
												</children>
											</tablecell>
											<tablecell>
												<properties width="150"/>
												<styles font-size="smaller" padding="0"/>
												<children>
													<text fixtext="Project/Performance Site Format Page">
														<styles font-size="7pt"/>
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

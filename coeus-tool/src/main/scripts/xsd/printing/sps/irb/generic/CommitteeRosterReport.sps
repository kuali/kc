<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" xsltversion="1" cssmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="UTF-8" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML" main="1" schemafile="D:\StyleVisionFiles\Correspondent templates\xmlpyproject\irb.xsd" workingxmlfile="Committesample2.xml">
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
								<properties border="1" width="100%"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="center"/>
														<children>
															<template match="n1:Committee" matchtype="schemagraphitem">
																<children>
																	<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeName" matchtype="schemagraphitem">
																				<children>
																					<content>
																						<styles font-weight="bold"/>
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
							<paragraph paragraphtag="p">
								<children>
									<table>
										<properties border="0" width="100%"/>
										<children>
											<tablebody>
												<children>
													<tablerow>
														<children>
															<tablecell>
																<properties width="141"/>
																<children>
																	<text fixtext="Home Unit">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<children>
																	<template match="n1:Committee" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:HomeUnitName" matchtype="schemagraphitem">
																						<children>
																							<content/>
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
																<properties width="141"/>
																<children>
																	<text fixtext="Research Area">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tablecell>
															<tablecell>
																<children>
																	<template match="n1:Committee" matchtype="schemagraphitem">
																		<children>
																			<template match="n1:ResearchArea" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:ResearchAreaDescription" matchtype="schemagraphitem">
																						<children>
																							<content/>
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
									<paragraph paragraphtag="p">
										<children>
											<paragraph paragraphtag="p">
												<children>
													<paragraph paragraphtag="p">
														<children>
															<paragraph paragraphtag="p">
																<children>
																	<paragraph paragraphtag="p">
																		<children>
																			<paragraph paragraphtag="p">
																				<children>
																					<text fixtext="Active Committee Members">
																						<styles font-weight="bold" text-decoration="underline"/>
																					</text>
																				</children>
																			</paragraph>
																		</children>
																	</paragraph>
																	<paragraph paragraphtag="p">
																		<children>
																			<template match="n1:Committee" matchtype="schemagraphitem">
																				<children>
																					<template match="n1:CommitteeMember" matchtype="schemagraphitem">
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
																															<template match="n1:Person" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:Fullname" matchtype="schemagraphitem">
																																		<children>
																																			<content>
																																				<styles font-weight="bold"/>
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
																														<children>
																															<template match="n1:Person" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:DirectoryTitle" matchtype="schemagraphitem">
																																		<children>
																																			<content/>
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
																														<children>
																															<template match="n1:MemberType" matchtype="schemagraphitem">
																																<children>
																																	<content/>
																																</children>
																															</template>
																														</children>
																													</tablecell>
																												</children>
																											</tablerow>
																											<tablerow>
																												<children>
																													<tablecell>
																														<children>
																															<template match="n1:CommitteeMemberRole" matchtype="schemagraphitem">
																																<children>
																																	<template match="n1:MemberRoleDesc" matchtype="schemagraphitem">
																																		<children>
																																			<content/>
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
																						</children>
																					</template>
																				</children>
																			</template>
																		</children>
																	</paragraph>
																</children>
															</paragraph>
															<paragraph paragraphtag="p">
																<children>
																	<paragraph paragraphtag="p">
																		<properties align="center"/>
																		<children>
																			<table>
																				<properties border="1" width="100%"/>
																				<children>
																					<tablebody>
																						<children>
																							<tablerow>
																								<children>
																									<tablecell>
																										<properties align="left"/>
																										<children>
																											<text fixtext="Committee Schedule">
																												<styles font-weight="bold"/>
																											</text>
																										</children>
																									</tablecell>
																								</children>
																							</tablerow>
																							<tablerow>
																								<children>
																									<tablecell>
																										<children>
																											<template match="n1:Committee" matchtype="schemagraphitem">
																												<children>
																													<template match="n1:Schedule" matchtype="schemagraphitem">
																														<children>
																															<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
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
																																									<template match="n1:Place" matchtype="schemagraphitem">
																																										<children>
																																											<content/>
																																										</children>
																																									</template>
																																								</children>
																																							</tablecell>
																																							<tablecell>
																																								<children>
																																									<template match="n1:ScheduledDate" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<format string="MM/DD/YYYY"/>
																																											</content>
																																										</children>
																																									</template>
																																									<text fixtext=" "/>
																																									<template match="n1:ScheduledTime" matchtype="schemagraphitem">
																																										<children>
																																											<content>
																																												<format string="hh:mm"/>
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
																	</paragraph>
																</children>
															</paragraph>
														</children>
													</paragraph>
												</children>
											</paragraph>
										</children>
									</paragraph>
								</children>
							</paragraph>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts>
		<children>
			<globaltemplate match="n1:Committee" matchtype="schemagraphitem">
				<children>
					<template match="n1:Committee" matchtype="schemagraphitem">
						<children>
							<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
								<children>
									<text fixtext="Committee Id: "/>
									<template match="n1:CommitteeId" matchtype="schemagraphitem">
										<children>
											<content/>
										</children>
									</template>
									<paragraph paragraphtag="p">
										<children>
											<text fixtext="Committee Name: "/>
											<template match="n1:CommitteeName" matchtype="schemagraphitem">
												<children>
													<content/>
												</children>
											</template>
										</children>
									</paragraph>
								</children>
							</template>
						</children>
					</template>
				</children>
			</globaltemplate>
		</children>
	</globalparts>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.89in" paperwidth="8.5in"/>
	</pagelayout>
	<designfragments/>
</structure>

<?xml version="1.0" encoding="UTF-8"?>
<structure version="12" xsltversion="1" htmlmode="strict" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" useimportschema="1" embed-images="1" enable-authentic-scripts="1" authentic-scripts-in-debug-mode-external="0" generated-file-location="DEFAULT">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://xml.coeus.mit.edu/iacuc"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="XML" main="1" schemafile="D:\Testing\IACUC SSheet\MileStone2\Testing2\Schema &amp; Xml\iacuc.xsd" workingxmlfile="D:\Testing\IACUC SSheet\MileStone2\Testing2\Schema &amp; Xml\Commiteeroster1.xml"/>
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
	<script-project>
		<Project version="1" app="AuthenticView"/>
	</script-project>
	<importedxslt/>
	<globalstyles>
		<rule url="toall.css">
			<media>
				<media value="all"/>
			</media>
		</rule>
	</globalstyles>
	<mainparts>
		<children>
			<globaltemplate subtype="main" match="/">
				<children>
					<documentsection>
						<properties columncount="1" columngap="0.50in" headerfooterheight="fixed" pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="auto" pagestart="next" paperheight="11in" papermarginbottom="0.79in" papermarginfooter="0.30in" papermarginheader="0.30in" papermarginleft="0.60in" papermarginright="0.60in" papermargintop="0.79in" paperwidth="8.50in"/>
					</documentsection>
					<template subtype="source" match="XML">
						<children>
							<tgrid>
								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
								<children>
									<tgridbody-cols>
										<children>
											<tgridcol/>
										</children>
									</tgridbody-cols>
									<tgridbody-rows>
										<children>
											<tgridrow>
												<styles height="0.50in"/>
												<children>
													<tgridcell>
														<properties align="center"/>
														<children>
															<template subtype="element" match="n1:Committee">
																<children>
																	<template subtype="element" match="n1:CommitteeMasterData">
																		<children>
																			<template subtype="element" match="n1:CommitteeName">
																				<children>
																					<content>
																						<styles font-weight="bold"/>
																						<format basic-type="xsd" datatype="string"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
																<variables/>
															</template>
														</children>
													</tgridcell>
												</children>
											</tgridrow>
										</children>
									</tgridbody-rows>
								</children>
							</tgrid>
							<paragraph paragraphtag="p">
								<children>
									<tgrid>
										<properties border="0" cellpadding="0" cellspacing="0" width="100%"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol>
														<styles width="141px"/>
													</tgridcol>
													<tgridcol/>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<newline/>
																	<text fixtext="Home Unit">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<newline/>
																	<template subtype="element" match="n1:Committee">
																		<children>
																			<template subtype="element" match="n1:CommitteeMasterData">
																				<children>
																					<template subtype="element" match="n1:HomeUnitName">
																						<children>
																							<content>
																								<format basic-type="xsd" datatype="string"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<text fixtext="Research Area">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tgridcell>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Committee">
																		<children>
																			<template subtype="element" match="n1:ResearchArea">
																				<children>
																					<template subtype="element" match="n1:ResearchAreaDescription">
																						<children>
																							<content>
																								<format basic-type="xsd" datatype="string"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
											</tgridbody-rows>
										</children>
									</tgrid>
								</children>
							</paragraph>
							<newline/>
							<text fixtext="Active Committee Members">
								<styles font-weight="bold" text-decoration="underline"/>
							</text>
							<template subtype="element" match="n1:Committee">
								<children>
									<template subtype="element" match="n1:CommitteeMember">
										<children>
											<tgrid>
												<properties border="0" width="100%"/>
												<children>
													<tgridbody-cols>
														<children>
															<tgridcol/>
														</children>
													</tgridbody-cols>
													<tgridbody-rows>
														<children>
															<tgridrow>
																<children>
																	<tgridcell>
																		<children>
																			<template subtype="element" match="n1:Person">
																				<children>
																					<template subtype="element" match="n1:Fullname">
																						<children>
																							<newline/>
																							<content>
																								<styles font-weight="bold"/>
																								<format basic-type="xsd" datatype="string"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
															<tgridrow>
																<children>
																	<tgridcell>
																		<children>
																			<template subtype="element" match="n1:Person">
																				<children>
																					<template subtype="element" match="n1:DirectoryTitle">
																						<children>
																							<content>
																								<format basic-type="xsd" datatype="string"/>
																							</content>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
															<tgridrow>
																<children>
																	<tgridcell>
																		<children>
																			<template subtype="element" match="n1:MemberType">
																				<children>
																					<content>
																						<format basic-type="xsd" datatype="string"/>
																					</content>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
															<tgridrow>
																<children>
																	<tgridcell>
																		<children>
																			<template subtype="element" match="n1:CommitteeMemberRole">
																				<children>
																					<template subtype="element" match="n1:MemberRoleDesc">
																						<children>
																							<content>
																								<format basic-type="xsd" datatype="string"/>
																							</content>
																							<newline/>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																	</tgridcell>
																</children>
															</tgridrow>
														</children>
													</tgridbody-rows>
												</children>
											</tgrid>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
							<paragraph paragraphtag="p">
								<properties align="center"/>
								<children>
									<tgrid>
										<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
										<children>
											<tgridbody-cols>
												<children>
													<tgridcol/>
												</children>
											</tgridbody-cols>
											<tgridbody-rows>
												<children>
													<tgridrow>
														<styles height="0.25in"/>
														<children>
															<tgridcell>
																<properties align="left"/>
																<children>
																	<text fixtext="  Committee Schedule">
																		<styles font-weight="bold"/>
																	</text>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
													<tgridrow>
														<children>
															<tgridcell>
																<children>
																	<template subtype="element" match="n1:Committee">
																		<children>
																			<template subtype="element" match="n1:Schedule">
																				<children>
																					<template subtype="element" match="n1:ScheduleMasterData">
																						<children>
																							<tgrid>
																								<properties border="1" cellpadding="0" cellspacing="0" width="100%"/>
																								<children>
																									<tgridbody-cols>
																										<children>
																											<tgridcol>
																												<styles width="1.75in"/>
																											</tgridcol>
																											<tgridcol/>
																										</children>
																									</tgridbody-cols>
																									<tgridbody-rows>
																										<children>
																											<tgridrow>
																												<styles height="0.28in"/>
																												<children>
																													<tgridcell>
																														<children>
																															<template subtype="element" match="n1:Place">
																																<children>
																																	<text fixtext=" "/>
																																	<content>
																																		<format basic-type="xsd" datatype="string"/>
																																	</content>
																																</children>
																																<variables/>
																															</template>
																														</children>
																													</tgridcell>
																													<tgridcell>
																														<children>
																															<template subtype="element" match="n1:ScheduledDate">
																																<children>
																																	<text fixtext=" "/>
																																	<content>
																																		<format basic-type="xsd" string="MM/DD/YYYY" datatype="date"/>
																																	</content>
																																</children>
																																<variables/>
																															</template>
																															<text fixtext=" "/>
																															<template subtype="element" match="n1:ScheduledTime">
																																<children>
																																	<content>
																																		<format basic-type="xsd" string="hh:mm" datatype="time"/>
																																	</content>
																																</children>
																																<variables/>
																															</template>
																														</children>
																													</tgridcell>
																												</children>
																											</tgridrow>
																										</children>
																									</tgridbody-rows>
																								</children>
																							</tgrid>
																						</children>
																						<variables/>
																					</template>
																				</children>
																				<variables/>
																			</template>
																		</children>
																		<variables/>
																	</template>
																</children>
															</tgridcell>
														</children>
													</tgridrow>
												</children>
											</tgridbody-rows>
										</children>
									</tgrid>
								</children>
							</paragraph>
							<template subtype="element" match="n1:Committee">
								<children>
									<template subtype="element" match="n1:CommitteeMasterData">
										<children>
											<newline/>
											<text fixtext="Committee Id        : "/>
											<template subtype="element" match="n1:CommitteeId">
												<children>
													<content>
														<format basic-type="xsd" datatype="string"/>
													</content>
												</children>
												<variables/>
											</template>
											<newline/>
											<text fixtext="Committee Name  : "/>
											<template subtype="element" match="n1:CommitteeName">
												<children>
													<content>
														<format basic-type="xsd" datatype="string"/>
													</content>
												</children>
												<variables/>
											</template>
											<newline/>
										</children>
										<variables/>
									</template>
								</children>
								<variables/>
							</template>
						</children>
						<variables/>
					</template>
				</children>
			</globaltemplate>
		</children>
	</mainparts>
	<globalparts/>
	<pagelayout>
		<properties paperheight="11.69in" paperwidth="8.27in"/>
	</pagelayout>
	<designfragments/>
	<xmltables/>
	<authentic-custom-toolbar-buttons/>
</structure>

<?xml version="1.0" encoding="UTF-8"?>
<structure version="7" cssmode="quirks" relativeto="*SPS" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8" embed-images="1">
	<parameters/>
	<schemasources>
		<namespaces>
			<nspair prefix="n1" uri="http://irb.mit.edu/irbnamespace"/>
		</namespaces>
		<schemasources>
			<xsdschemasource name="$XML1" main="1" schemafile="irb.xsd">
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
					<template match="$XML1" matchtype="schemasource">
						<editorproperties elementstodisplay="1"/>
						<children>
							<table>
								<properties border="0" width="100%"/>
								<styles font-size="14pt" font-weight="bold"/>
								<children>
									<tablebody>
										<styles font-size="14pt" font-weight="bold"/>
										<children>
											<tablerow>
												<styles font-size="14pt" font-weight="bold"/>
												<children>
													<tablecell>
														<properties align="center"/>
														<styles font-size="14pt" font-weight="bold"/>
														<children>
															<text fixtext="Review Comments">
																<styles font-size="14pt" font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<styles font-size="14pt" font-weight="bold"/>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0" width="100%"/>
								<styles font-size="9pt"/>
								<children>
									<tablebody>
										<styles font-size="9pt"/>
										<children>
											<tablerow>
												<styles font-size="9pt"/>
												<children>
													<tablecell>
														<properties nowrap="set" width="20"/>
														<styles font-size="9pt"/>
														<children>
															<text fixtext="Protocol Number: ">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties nowrap="set" width="20"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:ProtocolNumber" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
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
													<tablecell>
														<properties nowrap="set" width="20"/>
														<children>
															<text fixtext="Sequence Number:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties nowrap="set" width="40"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:SequenceNumber" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
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
														<properties nowrap="set"/>
														<styles font-size="9pt"/>
														<children>
															<text fixtext="Principal Investigator:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:Investigator" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:Person" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="n1:Fullname" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<content/>
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
														<properties nowrap="set"/>
														<styles font-size="9pt"/>
														<children>
															<text fixtext="Title:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties colspan="3"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:ProtocolMasterData" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:ProtocolTitle" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
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
														<properties nowrap="set"/>
														<styles font-size="9pt"/>
														<children>
															<text fixtext="Committee Id:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="n1:CommitteeId" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<content/>
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
														<properties nowrap="set"/>
														<children>
															<text fixtext="Committee Name:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:CommitteeMasterData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="n1:CommitteeName" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<content/>
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
														<properties nowrap="set"/>
														<styles font-size="9pt"/>
														<children>
															<text fixtext="Schedule Id:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="n1:ScheduleId" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<content/>
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
														<properties nowrap="set"/>
														<children>
															<text fixtext="Schedule Date:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
														<children>
															<template match="n1:Protocol" matchtype="schemagraphitem">
																<editorproperties elementstodisplay="1"/>
																<children>
																	<template match="n1:Submissions" matchtype="schemagraphitem">
																		<editorproperties elementstodisplay="1"/>
																		<children>
																			<template match="n1:ScheduleMasterData" matchtype="schemagraphitem">
																				<editorproperties elementstodisplay="1"/>
																				<children>
																					<template match="n1:ScheduledDate" matchtype="schemagraphitem">
																						<editorproperties elementstodisplay="1"/>
																						<children>
																							<condition>
																								<children>
																									<conditionbranch xpath="string-length(  .  )   &gt; 0">
																										<children>
																											<content>
																												<format string="MM/DD/YYYY"/>
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
															</template>
														</children>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<children>
													<tablecell>
														<properties nowrap="set"/>
														<styles font-size="9pt"/>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
													</tablecell>
													<tablecell>
														<properties nowrap="set"/>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<table>
								<properties border="0" width="100%"/>
								<styles font-size="9pt" text-align="justify"/>
								<children>
									<tablebody>
										<children>
											<tablerow>
												<children>
													<tablecell>
														<properties align="left" colspan="2" nowrap="set" scope="row" valign="baseline" width="5%"/>
														<children>
															<text fixtext="Review Comments:">
																<styles font-weight="bold"/>
															</text>
														</children>
													</tablecell>
												</children>
											</tablerow>
										</children>
									</tablebody>
								</children>
							</table>
							<template match="n1:Protocol" matchtype="schemagraphitem">
								<editorproperties elementstodisplay="1"/>
								<children>
									<template match="n1:Submissions" matchtype="schemagraphitem">
										<editorproperties elementstodisplay="1"/>
										<children>
											<template match="n1:Minutes" matchtype="schemagraphitem">
												<editorproperties elementstodisplay="1"/>
												<children>
													<condition>
														<children>
															<conditionbranch xpath="n1:PrivateCommentFlag = &apos;true&apos;">
																<children>
																	<table>
																		<properties border="0" width="100%"/>
																		<styles font-size="9pt"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="2%"/>
																								<styles font-weight="bold"/>
																								<children>
																									<text fixtext="#"/>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="95%"/>
																								<children>
																									<template match="n1:MinuteEntry" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<content/>
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
															</conditionbranch>
															<conditionbranch xpath="n1:PrivateCommentFlag = &apos;false&apos;">
																<children>
																	<table>
																		<properties border="0" width="100%"/>
																		<styles font-size="9pt"/>
																		<children>
																			<tablebody>
																				<children>
																					<tablerow>
																						<children>
																							<tablecell>
																								<properties width="2%"/>
																								<styles font-weight="bold"/>
																								<children>
																									<text fixtext="*"/>
																								</children>
																							</tablecell>
																							<tablecell>
																								<properties width="98%"/>
																								<children>
																									<template match="n1:MinuteEntry" matchtype="schemagraphitem">
																										<editorproperties elementstodisplay="1"/>
																										<children>
																											<content/>
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
															</conditionbranch>
														</children>
													</condition>
												</children>
											</template>
										</children>
									</template>
								</children>
							</template>
							<newline/>
							<table>
								<properties border="0" width="100%"/>
								<styles font-size="9pt"/>
								<children>
									<tablebody>
										<properties valign="top"/>
										<children>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="left" colspan="2" valign="bottom" width="50%"/>
														<styles font-weight="normal"/>
														<children>
															<text fixtext="#">
																<styles font-weight="bold"/>
															</text>
															<text fixtext="     Private Comment"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="justify" colspan="2" valign="middle" width="80%"/>
													</tablecell>
												</children>
											</tablerow>
											<tablerow>
												<properties valign="top"/>
												<children>
													<tablecell>
														<properties align="left" colspan="2" valign="bottom" width="50%"/>
														<styles font-weight="normal"/>
														<children>
															<text fixtext="*     Non-Private Comment"/>
														</children>
													</tablecell>
													<tablecell>
														<properties align="justify" colspan="2" valign="middle" width="80%"/>
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
	<pagelayout/>
	<designfragments/>
</structure>

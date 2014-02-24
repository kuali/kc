<?xml version="1.0" encoding="UTF-8"?>
<structure version="2" schemafile="C:\Proposal printing\schemas\phs398schema.xsd" workingxmlfile="Y:\lmrobin\printing\xml\Proposal_00000687Sponsor_000340$11102004-012517$.xml" templatexmlfile="">
	<nspair prefix="n1" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace"/>
	<nspair prefix="n2" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace"/>
	<nspair prefix="n3" uri="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace"/>
	<template>
		<styles font-family="Verdana" line-height="10pt"/>
		<match overwrittenxslmatch="/"/>
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
									<tablecol>
										<styles font-family="Verdana" font-size="10pt" font-weight="bold"/>
										<properties align="center"/>
										<children>
											<text fixtext="Budget Justification"/>
										</children>
									</tablecol>
								</children>
							</tablerow>
						</children>
					</tablebody>
				</children>
			</table>
			<newline/>
			<newline/>
			<template>
				<match match="n1:ResearchAndRelatedProject"/>
				<children>
					<template>
						<match match="Abstract"/>
						<children>
							<template>
								<match match="AbstractText"/>
								<children>
									<paragraph paragraphtag="pre">
										<children>
											<choice>
												<children>
													<choiceoption>
														<testexpression>
															<xpath value="../AbstractTypeDesc =&apos;BUDGET JUSTIFICATION&apos;"/>
														</testexpression>
														<children>
															<xpath allchildren="1">
																<styles font-family="Verdana" font-size="9pt"/>
															</xpath>
														</children>
													</choiceoption>
												</children>
											</choice>
										</children>
									</paragraph>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
		<footerall>
			<template>
				<match overwrittenxslmatch="/"/>
				<children>
					<table topdown="0">
						<properties width="100%"/>
						<children>
							<tablebody>
								<children>
									<tablerow>
										<children>
											<tablecol>
												<properties colspan="2" height="1"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles padding="0"/>
												<properties colspan="2" height="1"/>
											</tablecol>
										</children>
									</tablerow>
									<tablerow>
										<children>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" padding="0"/>
												<properties align="left" height="38"/>
												<children>
													<text fixtext="PHS 398 (Rev. 05/01)                                                                        Page   ___"/>
													<text fixtext=" "/>
												</children>
											</tablecol>
											<tablecol>
												<styles font-family="Verdana" font-size="7pt" font-weight="bold" padding="0"/>
												<properties align="right" height="38" width="150"/>
												<children>
													<text fixtext="Budget Justification Page "/>
												</children>
											</tablecol>
										</children>
									</tablerow>
								</children>
							</tablebody>
						</children>
					</table>
				</children>
			</template>
		</footerall>
	</pagelayout>
</structure>

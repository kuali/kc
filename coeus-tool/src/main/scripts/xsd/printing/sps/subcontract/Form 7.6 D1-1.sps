<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractXSD.xsd" workingxmlfile="Form 7.6 D1-1.xml" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<newline/>
			<newline/>
			<paragraph paragraphtag="h1">
				<children>
					<paragraph paragraphtag="h1">
						<children>
							<text fixtext="MIT">
								<styles font-weight="bold"/>
							</text>
							<newline/>
							<newline/>
						</children>
					</paragraph>
				</children>
			</paragraph>
			<text fixtext="TO:	                                       Ted Conroy, E19-429L">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="FROM:                                 ">
				<styles font-weight="bold"/>
			</text>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="UserDetails"/>
						<children>
							<template>
								<match match="UserName"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<newline/>
			<text fixtext="DATE:                                   ">
				<styles font-weight="bold"/>
			</text>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="UserDetails"/>
						<children>
							<template>
								<match match="Today"/>
								<children>
									<xpath allchildren="1"/>
									<datepicker ownvalue="1" datatype="date"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<newline/>
			<text fixtext="SUBJECT:  ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="                          "/>
			<text fixtext="Transmittal of ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=" Residual Property">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<text fixtext=" Report">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="REFERENCE:                     ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=" Contractor:"/>
			<text fixtext="    ">
				<styles font-weight="bold"/>
			</text>
			<template>
				<styles font-weight="bold"/>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="SubcontractorName"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<text fixtext="     ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="          "/>
			<text fixtext=" "/>
			<text fixtext="         "/>
			<newline/>
			<text fixtext="                                               Subaward #:"/>
			<text fixtext=" ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=" "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="PONumber"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<text fixtext="                                               "/>
			<text fixtext="Period of Performance:"/>
			<text fixtext=" ">
				<styles font-weight="bold"/>
			</text>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="StartDate"/>
								<children>
									<xpath allchildren="1"/>
									<datepicker ownvalue="1" datatype="date"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<text fixtext=" - "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="EndDate"/>
								<children>
									<xpath allchildren="1"/>
									<datepicker ownvalue="1" datatype="date"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<text fixtext="                                               MIT Account #:   "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractDetail"/>
						<children>
							<template>
								<match match="AccountNumber"/>
								<children>
									<xpath allchildren="1"/>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="ENCLOSURE:   ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="(1)  Copy of subject Property Report dated: "/>
			<newline/>
			<text fixtext="                  "/>
			<newline/>
			<text fixtext="Forwarded for your coordination with interested parties (Requisitioner, OSP Representative, Government--ACO or PCO, etc.)."/>
			<newline/>
			<newline/>
			<text fixtext="___ NEGATIVE REPORT"/>
			<newline/>
			<newline/>
			<text fixtext="___ POSITIVE REPORT "/>
			<newline/>
			<newline/>
			<text fixtext="Listed below are various methods for disposing of residual property.  Please indicate the disposition you desire, and return this form to me."/>
			<newline/>
			<newline/>
			<text fixtext="                                                        ____ Return to MIT"/>
			<newline/>
			<text fixtext="                                                        "/>
			<text fixtext="____ Transfer title to Subawardee"/>
			<newline/>
			<text fixtext="                                                        ____  Sell to Subaward and credit the subcontract"/>
			<newline/>
			<text fixtext="                                                       	____  Sell for  scrap and credit the subaward"/>
			<newline/>
			<text fixtext="                                                       	____  Discard property of little or no value"/>
			<newline/>
			<text fixtext="                                                       	____  Other ___________________________________________
"/>
			<newline/>
			<newline/>
			<text fixtext="Kindly acknowledge receipt of this Memo and inform me of the disposition decisions concerning the subject residual property inventory."/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Form 10.2D2 OSP RSO Revised 02/01
cc: {REQUISITIONER} with copy of enclosure (1)
"/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="0" paperheight="11in" papermarginbottom="0.39in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.39in" paperwidth="8.5in"/>
	</pagelayout>
</structure>

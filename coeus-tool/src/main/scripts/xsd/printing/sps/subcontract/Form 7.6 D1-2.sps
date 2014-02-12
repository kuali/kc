<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractXSD.xsd" workingxmlfile="C:\Documents and Settings\jobine\Desktop\form.xml" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<paragraph paragraphtag="h1">
				<children>
					<text fixtext="MIT"/>
					<newline/>
					<newline/>
					<newline/>
				</children>
			</paragraph>
			<text fixtext="TO:	                                   ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="Brianne Fitzgerald"/>
			<newline/>
			<text fixtext="                                               E19-750"/>
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
			<text fixtext="Transmittal of Final">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=" Patent Report">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="REFERENCE:                     ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="Contractor:"/>
			<text fixtext="                   ">
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
			<text fixtext="                 "/>
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
			<text fixtext=" ">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="                                               Period of Performance:"/>
			<text fixtext=" ">
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
			<text fixtext=" ">
				<styles font-weight="bold"/>
			</text>
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
			<text fixtext="                                               Funded under MIT Prime Contract/Grant: "/>
			<newline/>
			<text fixtext="                                               MIT Account #:             "/>
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
			<text fixtext="ENCLOSURE:    ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="(1) Copy of subject Patent Report dated:"/>
			<newline/>
			<text fixtext="                  "/>
			<newline/>
			<text fixtext="Forwarded for your coordination with interested parties (Requisitioner,OSP Representative, Government--ACO or PCO, etc.)."/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="___ NEGATIVE PATENT REPORT"/>
			<newline/>
			<newline/>
			<text fixtext="___ POSITIVE PATENT REPORT  "/>
			<newline/>
			<newline/>
			<newline/>
			<newline/>
			<newline/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="Form 10.2 D1 OSP RSO Revised 02/01
Copy: Patent Compliance Administrator, Technology Licensing Office NE25-230"/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" pageorientationportrait="0" paperheight="11in" papermarginbottom="0.39in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.39in" paperwidth="8.5in"/>
	</pagelayout>
</structure>

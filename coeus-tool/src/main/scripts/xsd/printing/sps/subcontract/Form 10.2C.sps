<?xml version="1.0" encoding="UTF-8"?>
<structure version="3" schemafile="subcontractXSD.xsd" workingxmlfile="" templatexmlfile="" xsltversion="2" encodinghtml="UTF-8" encodingrtf="ISO-8859-1" encodingpdf="UTF-8">
	<nspair prefix="xs" uri="http://www.w3.org/2001/XMLSchema"/>
	<template>
		<match overwrittenxslmatch="/"/>
		<children>
			<newline/>
			<newline/>
			<text fixtext="                                "/>
			<text fixtext="MASSACHUSETTS INSTITUTE OF TECHNOLOGY">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="                                             RESEARCH SUBAWARDS OFFICE">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="                                          OFFICE OF SPONSORED PROGRAMS">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="             "/>
			<text fixtext="Ian C. Cariolo                                                                  77 Massachusetts Avenue, E19-604">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="   Subcontract Closeout Administrator                                         Cambridge, Ma  02139-4307">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="   Phone: (617) 253-7260  Fax: (617) 253-2199">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<newline/>
			<text fixtext="                                                                             "/>
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
			<text fixtext="      "/>
			<text fixtext="  "/>
			<newline/>
			<newline/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="FirstName"/>
										<children>
											<xpath allchildren="1"/>
										</children>
									</template>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<text fixtext=" "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="LastName"/>
										<children>
											<xpath allchildren="1"/>
										</children>
									</template>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="Address1"/>
										<children>
											<xpath allchildren="1"/>
										</children>
									</template>
								</children>
							</template>
						</children>
					</template>
				</children>
			</template>
			<newline/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="Address2"/>
										<children>
											<xpath allchildren="1"/>
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
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractContacts"/>
						<children>
							<template>
								<match match="RolodexDetails"/>
								<children>
									<template>
										<match match="Pincode"/>
										<children>
											<xpath allchildren="1"/>
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
			<text fixtext="To Whom it May Concern   :"/>
			<newline/>
			<newline/>
			<text fixtext="In accordance with the terms of your Subaward with MIT, payment of your final invoice is being withheld pending receipt of the required closeout information.  Federal Regulations require that we obtain certain information from Subawardees in order to formally close out specific types of Subawards. Please provide the required documentation listed below as soon as possible so as not to further delay the payment of the final invoice. Thank you."/>
			<newline/>
			<newline/>
			<text fixtext=" 1 .  A Reference "/>
			<text fixtext="Copy">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=" of your cumulative invoice which"/>
			<text fixtext=" itemizes the cumulative charges under  each cost  category and ">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="       verifies the total amount paid under this Subaward, from its inception to completion."/>
			<newline/>
			<text fixtext="
	2 .	Executed Release and Assignment Forms (Enclosed)"/>
			<newline/>
			<text fixtext="
	3 .	A written report of any inventions, discoveries, or patents applied for under the cited Subaward"/>
			<text fixtext=", or a written statement tha">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="t">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="      &quot;there were no inventions or discoveries.&quot;">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="  *"/>
			<newline/>
			<text fixtext="
	4.	The name of all subawardees/subcontractors and your assigned order number for any lower-tier subawards/subcontracts"/>
			<newline/>
			<text fixtext="     issued which contain a patent rights clause,"/>
			<text fixtext=" or a written statement that &quot;there were no such lower-tier   ">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="     subawards/subcontracts.&quot;  *">
				<styles font-weight="bold"/>
			</text>
			<newline/>
			<text fixtext="
	5.	A listing of any property (equipment, hardware, components, material, etc.) furnished by MIT 	and/or the Government, or purchased by you under the cited Subaward, which still remains at  your facility as residual property to the Subaward"/>
			<text fixtext=", or a written statement that &quot;there is no  residual property.&quot;  ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="*
"/>
			<text fixtext="
*  I"/>
			<text fixtext="f"/>
			<text fixtext=" Not Applicab">
				<styles font-weight="bold"/>
			</text>
			<text>
				<styles font-weight="bold"/>
			</text>
			<text fixtext="le">
				<styles font-weight="bold"/>
			</text>
			<text fixtext=", please respond as shown above."/>
			<newline/>
			<newline/>
			<text fixtext="Subaward.">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<text fixtext="#		               ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="Period of Performance">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<text fixtext="			           ">
				<styles font-weight="bold"/>
			</text>
			<text fixtext="  Total Amount">
				<styles font-weight="bold" text-decoration="underline"/>
			</text>
			<newline/>
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
			<text fixtext="       "/>
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
			<text fixtext=" "/>
			<text fixtext=" - "/>
			<text fixtext="  "/>
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
			<text fixtext="          "/>
			<template>
				<match match="SubContractData"/>
				<children>
					<template>
						<match match="SubcontractAmountInfo"/>
						<children>
							<template>
								<match match="ObligatedAmount"/>
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
			<text fixtext="                                                                                   Sincerely,"/>
			<newline/>
			<newline/>
			<newline/>
			<newline/>
			<text fixtext="                                                                                   Subaward Closeout Administrator"/>
			<newline/>
			<text fixtext="                                                                             						OSP Research Subawards Office, E19-604"/>
			<newline/>
			<text fixtext="                                                                             						Tel:  (617) 253-7260	     FAX (617) 253-2199           "/>
			<newline/>
			<newline/>
			<text fixtext="Form 10.2C2 OSP RSO Revised 02/01
Subwards: Federal Contracts"/>
			<newline/>
			<newline/>
		</children>
	</template>
	<pagelayout>
		<properties pagemultiplepages="0" pagenumberingformat="1" pagenumberingstartat="1" paperheight="11in" papermarginbottom="0.79in" papermarginleft="0.6in" papermarginright="0.6in" papermargintop="0.79in" paperwidth="8.5in"/>
	</pagelayout>
</structure>

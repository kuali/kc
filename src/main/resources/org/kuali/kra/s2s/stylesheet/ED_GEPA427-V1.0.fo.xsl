<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
xmlns:ED_GEPA427="http://apply.grants.gov/forms/ED_GEPA427-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.5in" margin-right="0.5in">
                <fo:region-body margin-top="0.5in" margin-bottom="0.5in" />
                <fo:region-after extent=".5in" /> 
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="ED_GEPA427:GEPA427Attachments">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
            <fo:static-content flow-name="xsl-region-after">
 <fo:block>
	<fo:inline font-size="6px" font-weight="bold">
  Tracking Number: 
  <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber" /> 
  </fo:inline>
  </fo:block>
  </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                    
                            <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-column column-width="20pt" />
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="3" text-align="right" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-size="6px">OMB Control No. 1890-0007 (Exp. 09/30/2004)</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="3" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-size="12px" font-weight="bold">NOTICE TO ALL APPLICANTS </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell display-align="before" text-align="justify" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-size="8px">The purpose of this enclosure is to inform you about a new provision in the Department of Education's General Education Provisions Act (GEPA) that applies to applicants for new grant awards under Department programs.&#160; This provision is Section 427 of GEPA, enacted as part of the Improving America's Schools Act of 1994 (Public Law (P.L.) 103-382).
</fo:inline><fo:block><fo:leader leader-pattern="space"/></fo:block>
                                                <fo:inline font-size="8px" font-weight="bold">

To Whom Does This Provision Apply?</fo:inline><fo:block><fo:leader leader-pattern="space"/></fo:block>
                                                <fo:inline font-size="8px">

Section 427 of GEPA affects applicants for new grant awards under this program.</fo:inline>
                                                <fo:inline font-size="8px" font-weight="bold">&#160; ALL APPLICANTS FOR NEW AWARDS MUST INCLUDE INFORMATION IN THEIR APPLICATIONS TO ADDRESS THIS NEW PROVISION IN ORDER TO RECEIVE FUNDING UNDER THIS PROGRAM</fo:inline><fo:block><fo:leader leader-pattern="space"/></fo:block>
                                                <fo:inline font-size="8px">.

(If this program is a State-formula grant program, a State needs to provide this description only for projects or activities that it carries out with funds reserved for State-level uses.&#160; In addition, local school districts or other eligible applicants that apply to the State for funding need to provide this description in their applications to the State for funding.&#160; The State would be responsible for ensuring that the school district or other local entity has submitted a sufficient section 427 statement as described below.)
<fo:block><fo:leader leader-pattern="space"/></fo:block>
What Does This Provision Require?
<fo:block><fo:leader leader-pattern="space"/></fo:block>
Section 427 requires each applicant for funds (other than an individual person) to include in its application a description of the steps the applicant proposes to take to ensure equitable access to, and participation in, its Federally-assisted program for students, teachers, and other program beneficiaries with special needs.&#160; This provision allows applicants discretion in developing the required description.&#160; The statute highlights six types of barriers that can impede equitable access or participation: gender, race, national origin, color, disability, or age.&#160; Based on local circumstances, you should determine whether these or other barriers may prevent your students, teachers, etc. from such access or participation in, the Federally-funded project or activity.&#160; The description in your application of steps to be taken to overcome these barriers need not be lengthy; you may provide a clear and succinct </fo:inline>
                                                <fo:block>
                                                    <fo:leader leader-pattern="space" />
                                                </fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="justify" width="20pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell display-align="before" text-align="justify" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-size="8px">description of how you plan to address those barriers that are applicable to your circumstances.&#160; In addition, the information may be provided in a single narrative, or, if appropriate, may be discussed in connection with related topics in the application.
<fo:block><fo:leader leader-pattern="space"/></fo:block>
Section 427 is not intended to duplicate the requirements of civil rights statutes, but rather to ensure that, in designing their projects, applicants for Federal funds address equity concerns that may affect the ability of certain potential beneficiaries to fully participate in the project and to achieve to high standards.&#160; Consistent with program requirements and its approved application, an applicant may use the Federal funds awarded to it to eliminate barriers it identifies.</fo:inline><fo:block><fo:leader leader-pattern="space"/></fo:block>
                                                <fo:inline font-size="8px" font-weight="bold">

What are Examples of How an Applicant Might Satisfy the Requirement of This Provision?
</fo:inline><fo:block><fo:leader leader-pattern="space"/></fo:block>
                                                <fo:inline font-size="8px">

The following examples may help illustrate how an applicant may comply with Section 427.
<fo:block><fo:leader leader-pattern="space"/></fo:block>
(1) An applicant that proposes to carry out an adult literacy project serving, among others, adults with limited English proficiency, might describe in its application how it intends to distribute a brochure about the proposed project to such potential participants in their native language.
<fo:block><fo:leader leader-pattern="space"/></fo:block>
(2) An applicant that proposes to develop instructional materials for classroom use might describe how it will make the materials available on audio tape or in braille for students who are blind.
<fo:block><fo:leader leader-pattern="space"/></fo:block>
(3) An applicant that proposes to carry out a model science program for secondary students and is concerned that girls may be less likely than boys to enroll in the course, might indicate how it intends to conduct "outreach" efforts to girls, to encourage their enrollment.
<fo:block><fo:leader leader-pattern="space"/></fo:block>
We recognize that many applicants may already be implementing effective steps to ensure equity of access and participation in their grant programs, and we appreciate your cooperation in responding to the requirements of this provision.
</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block />
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="3" text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-size="8px" font-weight="bold">Estimated Burden Statement for GEPA Requirements
</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="3" text-align="justify" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-size="8px">According to the Paperwork Reduction Act of 1995, no persons are required to respond to a collection of information unless such collection displays a valid OMB control number. The valid OMB control number for this information collection is 1890-0007. The time required to complete this information collection is estimated to average 1.5 hours per response, including the time to review instructions, search existing data resources, gather the data needed, and complete and review the information collection. </fo:inline>
                                                <fo:inline font-size="8px" font-weight="bold">If you have any comments concerning the accuracy of the time estimate(s) or suggestions for improving this form, please write to:</fo:inline>
                                                <fo:inline font-size="8px"> Director, Grants Policy and Oversight Staff, U.S. Department of Education, 400 Maryland Avenue, SW (Room 3652, GSA Regional Office Building No. 3), Washington, DC 20202-4248.</fo:inline>
                                                <fo:inline font-size="10px">
</fo:inline>
<fo:block><fo:leader leader-pattern="space"/></fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="3" text-align="justify" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block />
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block />
                                                            </fo:table-cell>
                                                        </fo:table-row>

                                                        <fo:table-row>
                                                            <fo:table-cell number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <fo:inline font-size="12px" font-weight="bold">Attachment Information</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <fo:inline font-size="10px">File Name</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <fo:inline font-size="10px">Mime Type</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row >
                                                            <fo:table-cell   padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="ED_GEPA427:Attachments">
                                                                        <xsl:for-each select="att:FileName">
                                                                            <fo:inline font-size="10px">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell  number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:for-each select="ED_GEPA427:Attachments">
                                                                        <xsl:for-each select="att:MimeType">
                                                                            <fo:inline font-size="10px">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                                                                     
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>

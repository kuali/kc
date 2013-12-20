<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ED_Abstract="http://apply.grants.gov/forms/ED_Abstract-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
                <fo:region-body margin-top="0.79in" margin-bottom="0.79in" font-family="Helvetica,Times,Courier" font-size="14pt" />
<fo:region-after extent=".79in" /> 
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="ED_Abstract:AbstractAttachments">
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
                        <fo:block>
                            <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:block>
<fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column column-width="10pt" />
                            <fo:table-column />
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" width="10pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block>
                                            <fo:inline font-size="12px" font-weight="bold">Abstract</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell width="10pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block>
                                            <fo:inline font-size="10px">The abstract narrative must not exceed one page and should use language that will be understood by a range of audiences. For all projects, include the project title (if applicable), goals, expected outcomes and contributions for research, policy, practice, etc.&#160; Include population to be served, as appropriate. For research applications, also include the following:</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell width="10pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block>
                                            <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                                <fo:list-item>
                                                    <fo:list-item-label end-indent="label-end()">
                                                        <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                    </fo:list-item-label>
                                                    <fo:list-item-body start-indent="body-start()">
                                                        <fo:block>
                                                            <fo:inline font-size="10px">Theoretical and conceptual background of the study (i.e., prior research that this investigation builds upon and provides a compelling rational for this study)</fo:inline>
                                                        </fo:block>
                                                    </fo:list-item-body>
                                                </fo:list-item>
                                                <fo:list-item>
                                                    <fo:list-item-label end-indent="label-end()">
                                                        <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                    </fo:list-item-label>
                                                    <fo:list-item-body start-indent="body-start()">
                                                        <fo:block>
                                                            <fo:inline font-size="10px">Research issues, hypotheses and questions being addressed</fo:inline>
                                                        </fo:block>
                                                    </fo:list-item-body>
                                                </fo:list-item>
                                                <fo:list-item>
                                                    <fo:list-item-label end-indent="label-end()">
                                                        <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                    </fo:list-item-label>
                                                    <fo:list-item-body start-indent="body-start()">
                                                        <fo:block>
                                                            <fo:inline font-size="10px">Study design including a brief description of the sample including sample size, methods, principals dependent, independent, and control variables, and the approach to data analysis. 
</fo:inline>
                                                            <fo:inline>
</fo:inline>
                                                        </fo:block>
                                                    </fo:list-item-body>
                                                </fo:list-item>
                                            </fo:list-block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell width="10pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block>
                                            <fo:inline font-size="10px">[Note: For a non-electronic submission, include the name and address of your organization and the name, phone number and e-mail address of the contact person for this project.] </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                            
</fo:block>
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                            <xsl:for-each select="ED_Abstract:Attachments">
                                    <xsl:if test="position()=1">
                                        <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                            <fo:table-column />
                                            <fo:table-column />
                                            <fo:table-header>
                                                <fo:table-row>
                                                    <fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                                        <fo:block>
                                                            <fo:inline font-weight="bold">FileName</fo:inline></fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                                        <fo:block>
                                                            <fo:inline font-weight="bold">MimeType</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                            </fo:table-header>
                                            <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                            <fo:block>
                                                                <xsl:for-each select="att:FileName">
                                                                    <xsl:apply-templates />
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                            <fo:block>
                                                                <xsl:for-each select="att:MimeType">
                                                                    <xsl:apply-templates />
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                            </fo:table-body>
                                        </fo:table>
                                    </xsl:if>
                            </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>

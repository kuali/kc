package org.kuali.coeus.s2sgen.impl.print;


import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;

/**
 *
 * This interface marks reports, notifications, BOs and Documents as printable
 * in Kuali-Coeus. KC Docs & BOs that will be printed via KC printing services
 * should implement this interface.
 */
public interface S2SPrintable {

    /**
     *
     * This method provides a way to get the XSL Transform(s) for the KC
     * generated XML. This XSLT will create a transformed XML-FO stream that
     * will be converted to PDF. Note that multiple transforms are possible on
     * this data.
     */
    List<Source> getXSLTemplates();

    Map<String,Source> getXSLTemplateWithBookmarks();

    /**
     *
     * This method will provide the either reflected or XML-Bean based XML for
     * input to the Transform into XML-FO.
     */
    Map<String, byte[]> renderXML();

    /**
     * This method will return the PDF attachments specific to the printable.
     * During printing the attachments will be added as bookmarks to the output.
     * The Key in the map is used as the name of the bookmark.
     * @return Map of Attachment pdf bytes with bookmark names.
     */
    Map<String, byte[]> getAttachments();
}

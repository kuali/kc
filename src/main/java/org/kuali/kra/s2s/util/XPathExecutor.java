/*
 * Created on Jun 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.kuali.kra.s2s.util;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Class processes XPath Queries.
 * 
 */
public class XPathExecutor {

	private Document doc;
	
	
	private XPathExecutor(){
		
	}
	
	public XPathExecutor( String xml ) throws Exception{
		
		init( xml );
	}
	
	/**
	 * Method evaulates the XPath expression against the xml string.
	 * Currently utilizing a DOM implementation.
	 * @param xml
	 * @param xPath
	 * @return first node value returned
	 * @throws Exception
	 */
	public String execute( String xPath ) throws Exception {

		if ( xPath == null ){
			return null;
		}
		
		// Evaluate the xpath expression
		return XPathAPI.eval( getDoc(), xPath ).toString();
		
	
	}
	
	/**
	 * For a given XPath, a DOM Node that the XPath resolve to is returned.
	 * @param xpath A valid XPath referring to the Node that is to be returned
	 * @return The Node referred to by the xpath argument.
	 * @throws TransformerException
	 */
	public Node getNode(String xpath)
		throws TransformerException
	{
	    return XPathAPI.selectSingleNode(getDoc(), xpath);
	}
	
	private void init( String xml ) throws Exception {
	
		if ( xml == null ) {
			return;
		}
		
		
       ByteArrayInputStream stream = null;
		try {
			stream = new ByteArrayInputStream(xml.getBytes());
		
		DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		dfactory.setNamespaceAware(true);
		
		setDoc( dfactory.newDocumentBuilder().parse(stream) );

		}finally {
			try{ stream.close(); }catch( Exception ex){}
		}
		
		
	}

	
	/**
	 * @return the Document.
	 */
	public Document getDoc() {
		return doc;
	}
	/**
	 * @param doc the Document.
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}

}


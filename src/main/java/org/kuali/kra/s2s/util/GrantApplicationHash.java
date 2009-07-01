/*
 * Created on Jan 7, 2005
 * 
 * www.grants.gov WebServices Code
 */
package org.kuali.kra.s2s.util;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.apache.xml.security.algorithms.MessageDigestAlgorithm;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.utils.Base64;
import org.apache.xml.security.utils.DigesterOutputStream;
import org.kuali.kra.s2s.S2SException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * This is a convenience object that simplifies the hashing processing to one
 * method call.
 * 
 * @author David Wong
 */
public class GrantApplicationHash {

	static final Logger log = Logger.getLogger(GrantApplicationHash.class
			.getName());

	static java.security.MessageDigest messageDigester = null;

	static {
		org.apache.xml.security.Init.init();
		try {
			messageDigester = java.security.MessageDigest.getInstance("SHA-1");
		} catch (Exception ex) {
			log.fatal(
					"Unable to get instance of java.security.MessageDigester",
					ex);
		}
	}

	/**
	 * Added private constructor to prevent creation by user.
	 */
	private GrantApplicationHash() {

	}

	/**
	 * Computes the hash value for the Grants.gov application XML.
	 * 
	 * @param xml
	 *            The Grants.gov application XML.
	 * @return The SHA-1 hash value of &lt;grant:forms&gt; tag inside the
	 *         application XML.
	 * @throws Exception
	 *             When the XML cannot be parsed.
	 */
	public final static String computeGrantFormsHash(String xml) throws S2SException{
		GrantApplicationXpath xpath;
        try {
            xpath = new GrantApplicationXpath(xml);
            return _hash(xpath);
        }catch (Exception e) {
            log.error(e);
            throw new S2SException(e.getMessage());
        }
	}

	/**
	 * Computes the hash of an binary attachment.
	 * 
	 * @param attachment
	 * @return The SHA-1 hash value of the attachment byte array.
	 * @throws Exception
	 */
	public final static String computeAttachmentHash(byte[] attachment)
			throws Exception {

		byte[] rawDigest = messageDigester.digest(attachment);

		return Base64.encode(rawDigest);

	}

	/**
	 * Computes the hash value for the Grants.gov application XML.
	 * 
	 * @param xpath
	 *            An xpath object holding the Grants.gov application XML.
	 * @return The SHA-1 hash value of &lt;grant:forms&gt; tag inside the
	 *         application XML.
	 * @throws Exception
	 *             When the XML cannot be parsed.
	 */
	public final static String computeGrantFormsHash(GrantApplicationXpath xpath)
			throws Exception {
		return _hash(xpath);
	}

	/**
	 * Computes the hash value for the Grants.gov application XML.
	 * 
	 * @param xml
	 *            The Grants.gov application XML.
	 * @return The SHA-1 hash value of &lt;grant:forms&gt; tag inside the
	 *         application XML.
	 * @throws Exception
	 *             When the XML cannot be parsed.
	 */
	public final static String computeGrantFormsHash(Document xml) throws Exception {
		XPathExecutor executor = new XPathExecutor(null);
		executor.setDoc(xml);
		GrantApplicationXpath xpath = new GrantApplicationXpath(null);
		xpath.setExecutor(executor);
		return _hash(xpath);
	}

	private static String _hash(GrantApplicationXpath xpath)
			throws TransformerException, XMLSignatureException,
			InvalidCanonicalizerException, CanonicalizationException {
		Node formsNode = xpath.getFormsNode();
		DigesterOutputStream digester = _createDigesterOutputStream(xpath
				.getExecutor().getDoc());
		Canonicalizer canonicalizer = Canonicalizer
				.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
		canonicalizer.setWriter(digester);
		canonicalizer.canonicalizeSubtree(formsNode);
		byte[] hash = digester.getDigestValue();
		//HexDump.setWithByteSeparator(false);
		//return HexDump.toHexString(hash);
		return Base64.encode(hash);
	}

	private static DigesterOutputStream _createDigesterOutputStream(Document doc)
			throws XMLSignatureException {
		DigesterOutputStream stream = null;
		if (doc != null) {
			stream = new DigesterOutputStream(MessageDigestAlgorithm
					.getInstance(doc,
							MessageDigestAlgorithm.ALGO_ID_DIGEST_SHA1));
		}
		return stream;
	}
}
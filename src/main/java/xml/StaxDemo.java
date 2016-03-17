package xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.EventFilter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.security.utils.XPathFactory;

public class StaxDemo {

	@Test
	public void test01() {
		XMLInputFactory factory = XMLInputFactory.newFactory();
		InputStream is = StaxDemo.class.getClassLoader().getResourceAsStream("xml/books.xml");
		try {
			/**
			XMLStreamReader reader = factory.createXMLStreamReader(is);
			while(reader.hasNext()){
				int type = reader.next();
				if(type == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getName() + "";
					System.out.println(name);
					if("book".equals(name)) {
						System.out.println(reader.getAttributeName(0) + ":" + reader.getAttributeValue(0));
					}
				} else if(type == XMLStreamConstants.CHARACTERS) {
					System.out.println(reader.getText());
				} else if(type == XMLStreamConstants.END_ELEMENT) {
					System.out.println("/" + reader.getName());
				}
			}
			*/

			/**
			XMLEventReader eventReader = factory.createXMLEventReader(is);
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if(event.isStartElement()) {
					String name = event.asStartElement().getName() + "";
					if("title".equals(name)) {
						System.out.print(eventReader.getElementText() + ":");
					}
					if("price".equals(name)) {
						System.out.println(eventReader.getElementText());
					}
				}
			}
			*/

			XMLEventReader eventFilteredReader = factory.createFilteredReader(factory.createXMLEventReader(is),
					new EventFilter() {
						public boolean accept(XMLEvent event) {
							if(event.isStartElement()) {
								String name = event.asStartElement().getName() + "";
								if("title".equals(name) || "price".equals(name)) {
									return true;
								}
							}
							return false;
						}
				
			});
			while(eventFilteredReader.hasNext()) {
				XMLEvent event = eventFilteredReader.nextEvent();
				if(event.isStartElement()) {
					String name = event.asStartElement().getName() + "";
					if("title".equals(name)) {
						System.out.print(eventFilteredReader.getElementText() + ":");
					}
					if("price".equals(name)) {
						System.out.println(eventFilteredReader.getElementText());
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test02() {
		InputStream is = StaxDemo.class.getClassLoader().getResourceAsStream("xml/books.xml");
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = db.parse(is);
//			XPath xpath = XPathFactory.newInstance().newXPath();

			//读取节点
//			NodeList list = (NodeList) xpath.evaluate("//book[@category='WEB']", doc, XPathConstants.NODESET);
//			for(int i=0; i<list.getLength(); i++) {
//				Element e = (Element) list.item(i);
//				System.out.println(e.getElementsByTagName("title").item(0).getTextContent());
//			}
			//修改节点
//			Transformer tran = TransformerFactory.newInstance().newTransformer();
//			tran.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//			tran.setOutputProperty(OutputKeys.INDENT, "yes");
//			NodeList list = (NodeList) xpath.evaluate("//book[title='Learning XML']", doc, XPathConstants.NODESET);
//			Element be = (Element) list.item(0);
//			Element e = (Element) (be.getElementsByTagName("price").item(0));
//			e.setTextContent("888.888");
//			Result result = new StreamResult(System.out);
//			tran.transform(new DOMSource(doc), result);
//			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test03(){
		try {
			XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
			xsw.writeStartDocument("UTF-8","1.0");
			xsw.writeStartElement("person");
			xsw.writeStartElement("id");
			xsw.writeCharacters("1");
			xsw.writeEndElement();
			xsw.writeEndElement();
			xsw.writeEndDocument();
			xsw.flush();
			xsw.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
}

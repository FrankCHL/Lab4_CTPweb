import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXExample
{
    final  String  fileName = "phonebook.xml";
    final  String  TAG_NAME = "name";

    DefaultHandler handler = new DefaultHandler() {
        boolean tagOn = false; // флаг начала разбора тега

        @Override
        public void startElement(String uri,
                                 String localName,
                                 String qName,
                                 Attributes attributes) throws SAXException {

            tagOn = (qName.equalsIgnoreCase(TAG_NAME));
            System.out.println("\t<" + qName + ">");
        }

        @Override
        public void characters(char ch[],
                               int start, int length)
                throws SAXException {
            // Проверка флага
            if (tagOn) {
                // Флаг установлен
                System.out.println("\t\t" +
                        new String(ch,start,length));
                tagOn = false;
            }
        }
        @Override
        public void endElement(String uri,
                               String localName,
                               String qName)
                throws SAXException
        {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void startDocument() throws SAXException
        {
            System.out.println("Start read!");
        }

        @Override
        public void endDocument() throws SAXException
        {
            System.out.println("read complete!");
        }
    };

    public SAXExample()
    {
        try {
            SAXParserFactory factory;
            factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        new SAXExample();
        System.exit(0);
    }
}

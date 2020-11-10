package app;
// jdk1.4.1
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.net.*;
import java.io.*;

public class Transformer
{
    public void transform()
    {
        try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tFactory.newTransformer(new StreamSource("D:\\Programming\\XmlToHtmlParserApp\\src\\app\\filmLibrary.xsl"));
            transformer.transform(new javax.xml.transform.stream.StreamSource("D:\\Programming\\XmlToHtmlParserApp\\src\\app\\filmLibrary.xml"),
                                    new javax.xml.transform.stream.StreamResult( new FileOutputStream("D:\\Programming\\XmlToHtmlParserApp\\src\\app\\filmLibrary.html")));
        }
        catch (Exception e)
        {
            e.printStackTrace( );
        }
    }
}
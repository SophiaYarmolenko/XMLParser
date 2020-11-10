package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ParserDOM implements Parser
{
    ArrayList<Film> filmList = new ArrayList<Film>();

    @Override
    public void parse() throws ParserConfigurationException, SAXException, IOException
    {
        parseWithParameter(null, null, -1, null, null);
    }

    @Override
    public void parseWithParameter(String title, String genre, int year, String country, String director)
    {
        File file = new File("D:\\Programming\\XmlToHtmlParserApp\\src\\app\\filmLibrary.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        Document document = null;
        try
        {
            document = builder.parse(file);
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        NodeList filmNodeList = document.getElementsByTagName("film");

        for( int i = 0; i < filmNodeList.getLength(); i++)
        {
            if(filmNodeList.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                Element filmElement = (Element) filmNodeList.item(i);
                Film film = new Film();
                NodeList filmCharacteristic = filmElement.getChildNodes();

                for (int j = 0; j < filmCharacteristic.getLength(); j++)
                {
                    if(filmCharacteristic.item(j).getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element characteristicElement = (Element) filmCharacteristic.item(j);
                        checkElementNodeName(characteristicElement, film);
                    }
                }
                if( title!=null && !film.getTitle().equals(title))
                    continue;
                if( genre!=null && !film.getGenre().equals(genre))
                    continue;
                if( year > 0 && film.getYear() != year)
                    continue;
                if( country!=null && !film.getCountry().equals(country))
                    continue;
                if( director!=null && !film.getDirector().equals(director))
                    continue;

                filmList.add(film);
            }
        }
    }

    private void checkElementNodeName(Element characteristicElement, Film film)
    {
        switch (characteristicElement.getNodeName())
        {
            case "title":
            {
                film.setTitle(characteristicElement.getTextContent());
                break;
            }
            case "genre":
            {
                film.setGenre(characteristicElement.getTextContent());
                break;
            }
            case "year":
            {
                film.setYear(Integer.parseInt(characteristicElement.getTextContent()));
                break;
            }
            case "country":
            {
                film.setCountry(characteristicElement.getTextContent());
                break;
            }
            case "director":
            {
                film.setDirector(characteristicElement.getTextContent());
            }
        }
    }
    @Override
    public ArrayList<Film> getList()
    {
        return filmList;
    }
}

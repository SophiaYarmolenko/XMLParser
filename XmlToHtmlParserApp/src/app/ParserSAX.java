package app;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSAX implements Parser
{
    private static ArrayList<Film> films = new ArrayList<>();

    @Override
    public void parse() throws ParserConfigurationException, SAXException, IOException
    {
        parseWithParameter(null, null, -1, null, null);
    }

    @Override
    public void parseWithParameter(String title, String genre, int year, String country, String director) throws ParserConfigurationException, SAXException, IOException
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();

        XMLHandler handler = new XMLHandler(title, genre, year, country, director);
        saxParser.parse(new File("D:\\Programming\\XmlToHtmlParserApp\\src\\app\\filmLibrary.xml"), handler);
    }

    @Override
    public List<Film> getList()
    {
        return films;
    }

    private static class XMLHandler extends DefaultHandler
    {
        private String title;
        private String genre;
        private int year;
        private String country;
        private String director;
        private String lastElementName;
        private String neededTitle = null;
        private String neededGenre = null;
        private int neededYear = -1;
        private String neededCountry = null;
        private String neededDirector = null;

        public XMLHandler(String neededTitle, String neededGenre, int neededYear, String neededCountry, String neededDirector)
        {
            this.neededTitle = neededTitle;
            this.neededGenre = neededGenre;
            this.neededYear = neededYear;
            this.neededCountry = neededCountry;
            this.neededDirector = neededDirector;
            films.clear();
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException
        {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty())
            {
                switch (lastElementName)
                {
                    case "title":
                        title = information;
                        break;
                    case "genre":
                        genre = information;
                        break;
                    case "year":
                        year = Integer.parseInt(information);
                        break;
                    case "country":
                        country = information;
                        break;
                    case "director":
                        director = information;
                        break;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException
        {
            if ( ( title != null && !title.isEmpty())
                    && (genre != null && !genre.isEmpty())
                    && (country != null && !country.isEmpty())
                    && (director != null && !director.isEmpty())
                )
            {
                Film film = new Film();
                film.setTitle(title);
                film.setGenre(genre);
                film.setYear(year);
                film.setCountry(country);
                film.setDirector(director);
                title = null;
                genre = null;
                year = 0;
                country = null;
                director = null;
                if(neededTitle != null && !neededTitle.equals(film.getTitle()))
                    return;
                if(neededGenre != null && !neededGenre.equals(film.getGenre()))
                    return;
                if(neededYear > 0 && neededYear != film.getYear())
                    return;
                if(neededCountry != null && !neededCountry.equals(film.getCountry()))
                    return;
                if(neededDirector != null && !neededDirector.equals(film.getDirector()))
                    return;
                films.add(film);
            }
        }
    }
}

package app;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface Parser
{
     abstract void parse() throws ParserConfigurationException, SAXException, IOException;
     abstract void parseWithParameter(String title, String genre, int year, String country, String director) throws ParserConfigurationException, SAXException, IOException;
     abstract List<Film> getList();
}

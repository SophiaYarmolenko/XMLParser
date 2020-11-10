package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller
{
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextArea fileTextField;

    @FXML
    private CheckBox saxCheckBox;

    @FXML
    private CheckBox domCheckBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button transformButton;

    @FXML
    private CheckBox titleCheckBox;

    @FXML
    private CheckBox genreCheckBox;

    @FXML
    private CheckBox yearCheckBox;

    @FXML
    private CheckBox countryCheckBox;

    @FXML
    private CheckBox directorCheckBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField genreTextField;

    @FXML
    private TextField yearTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField directorTextField;

    private String title = null;
    private String genre = null;
    private int year = -1;
    private String country = null;
    private String director = null;

    private Parser parser;
    private Transformer transformer = new Transformer();

    @FXML
    void initialize()
    {
        searchButton.setOnAction(actionEvent ->
        {
            if(search().equals(""))
            {
                fileTextField.setText("Nothing(");
            }
            else
                {
                    fileTextField.setText(search());
                }
        });

        clearButton.setOnAction(actionEvent ->
        {
           clear();
        });

        transformButton.setOnAction(actionEvent ->
        {
            transform();
        });
    }

    private String search()
    {
        if(titleCheckBox.isSelected())
        {
            title = nameTextField.getText();
        }
        else
        {
            title = null;
        }
        if(genreCheckBox.isSelected())
        {
            genre = genreTextField.getText();
        }
        else
        {
            genre = null;
        }
        if(yearCheckBox.isSelected())
        {
            year = Integer.parseInt(yearTextField.getText());
        }
        else
        {
            year = -1;
        }
        if(countryCheckBox.isSelected())
        {
            country = countryTextField.getText();
        }
        else
        {
            country = null;
        }
        if(directorCheckBox.isSelected())
        {
            director = directorTextField.getText();
        }
        else
        {
            director = null;
        }
        if(domCheckBox.isSelected())
        {
            saxCheckBox.setSelected(false);
            parser = new ParserDOM();
        }
        else
        {
            parser = new ParserSAX();
        }

        try
        {
            parser.parseWithParameter(title, genre, year, country, director);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder result = new StringBuilder("");
        for(Film film : parser.getList())
        {
            result.append(film.toString() + "\n" + "-------------------------------------------------------------------------------" + "\n");
        }
       return result.toString();
    }

    private void transform()
    {
        transformer.transform();
        File htmlFile = new File("D:\\Programming\\XmlToHtmlParserApp\\src\\app\\filmLibrary.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clear()
    {
        titleCheckBox.setSelected(false);
        genreCheckBox.setSelected(false);
        yearCheckBox.setSelected(false);
        countryCheckBox.setSelected(false);
        directorCheckBox.setSelected(false);
        saxCheckBox.setSelected(false);
        domCheckBox.setSelected(false);
        nameTextField.clear();
        genreTextField.clear();
        yearTextField.clear();
        countryTextField.clear();
        directorTextField.clear();
        fileTextField.clear();
        title = null;
        genre = null;
        year = -1;
        country = null;
        director = null;
    }

}

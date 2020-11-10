package app;

public class Film
{
    private String title;
    private String genre;
    private int year;
    private String country;
    private String director;

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGenre()
    {
        return genre;
    }

    public int getYear()
    {
        return year;
    }

    public String getCountry()
    {
        return country;
    }

    public String getDirector()
    {
        return director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    @Override
    public String toString()
    {
        return "Film :" + '\n' +
                "title = " + title + '\n' +
                "genre = " + genre + '\n' +
                "year = " + year + '\n' +
                "country = " + country + '\n' +
                "director = " + director;
    }
}

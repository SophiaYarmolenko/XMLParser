<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:template match="/">
        <html>
            <head><title>Real's FilmLibrary</title></head>
            <body>
                <table border="1">
                    <tr>
                        <th>Title</th>
                        <th>Genre</th>
                        <th>Year</th>
                        <th>Country</th>
                        <th>Director</th>
                    </tr>
                    <xsl:for-each select="filmLibrary/film">
                        <tr>
                            <td><xsl:value-of select="title"/></td>
                            <td><xsl:value-of select="genre"/></td>
                            <td><xsl:value-of select="year"/></td>
                            <td><xsl:value-of select="country"/></td>
                            <td><xsl:value-of select="director"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body></html>
    </xsl:template>
</xsl:stylesheet>
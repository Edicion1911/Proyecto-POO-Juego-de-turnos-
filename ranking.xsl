<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Ranking - El despertar del Caballero</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    background-color: #f2f2f2;
                    text-align: center;
                    }

                    h1 {
                    color: #333333;
                    }

                    table {
                    margin: auto;
                    border-collapse: collapse;
                    width: 60%;
                    background-color: white;
                    }

                    th, td {
                    border: 1px solid #cccccc;
                    padding: 12px;
                    }

                    th {
                    background-color: #333333;
                    color: white;
                    }

                    tr:nth-child(even) {
                    background-color: #eeeeee;
                    }
                </style>
            </head>

            <body>
                <h1>Ranking de jugadores</h1>

                <table>
                    <tr>
                        <th>Posición</th>
                        <th>Jugador</th>
                        <th>Puntuación</th>
                    </tr>

                    <xsl:for-each select="ranking/jugador">
                        <xsl:sort select="puntuacion"
                                  data-type="number"
                                  order="descending"/>

                        <tr>
                            <td>
                                <xsl:value-of select="position()"/>
                            </td>
                            <td>
                                <xsl:value-of select="nombre"/>
                            </td>
                            <td>
                                <xsl:value-of select="puntuacion"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

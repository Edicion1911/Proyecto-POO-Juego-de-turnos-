<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"
                doctype-system="about:legacy-compat"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <title>Ranking - El despertar del Caballero</title>
                <!-- CSS externo: ruta relativa respecto a ranking.html -->
                <link rel="stylesheet" type="text/css" href="ranking.css"/>
            </head>

            <body>
                <h1>Ranking de jugadores</h1>

                <xsl:choose>
                    <!-- XML sin registros: mensaje claro en lugar de la tabla -->
                    <xsl:when test="not(ranking/jugador)">
                        <p class="vacio">Todavía no hay puntuaciones registradas.</p>
                    </xsl:when>

                    <xsl:otherwise>
                        <table>
                            <tr>
                                <th>Posición</th>
                                <th>Jugador</th>
                                <th>Puntuación</th>
                            </tr>

                            <!-- Orden numérico descendente; xsl:sort es estable,
                                 así que en los empates se conserva el orden del XML -->
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
                    </xsl:otherwise>
                </xsl:choose>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

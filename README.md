# El despertar del Caballero de los Siete Reinos

Juego de combate por turnos en consola, inspirado en Juego de Tronos.
Creas un caballero, te enfrentas a tres enemigos y tu puntuación se guarda en un ranking.

## Requisitos

- Java 21 o superior.

## Cómo compilar y ejecutar

Desde la carpeta del proyecto:

```
javac *.java
java Main
```

## Reglas básicas

- El caballero empieza con 100 de vida y 3 pociones que curan 25.
- Hay tres combates seguidos contra enemigos cada vez más fuertes.
- Cada enemigo derrotado da 100 puntos. Si ganas la aventura, se suma la vida que te queda.
- En cada turno puedes atacar, protegerte, usar una poción, ver el inventario o abandonar.

## Ranking (XML)

- Las puntuaciones se guardan en `ranking.xml` (máximo 10 jugadores).
- `ranking.dtd` define la estructura que debe tener el XML. El juego la declara solo en `ranking.xml` cada vez que guarda.
- Para validarlo:

```
xmllint --noout --valid ranking.xml
```

## Consultas XPath

Las cinco consultas están en `consultas_xpath.md`. Ejemplo:

```
xmllint --xpath "/ranking/jugador/nombre" ranking.xml
```

## Página HTML del ranking

- `ranking.xsl` transforma el XML en una tabla HTML.
- `ranking.css` da el formato a la tabla.

Para generar `ranking.html`:

```
xsltproc -o ranking.html ranking.xsl ranking.xml
```

Después abre `ranking.html` en el navegador. Si cambias algo en `ranking.xml`, repite el comando y la tabla se actualiza.

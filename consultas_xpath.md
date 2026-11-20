# Consultas XPath sobre `ranking.xml`

Archivo de prueba: `ranking.xml` (10 registros ficticios, con empates a 320 y a 200).
Estructura: `/ranking/jugador` contiene `nombre` y `puntuacion`.

Cómo ejecutarlas (desde la carpeta raíz del proyecto):

```
xmllint --xpath "EXPRESIÓN" ranking.xml
```

| Nº | Finalidad |
|----|-----------|
| 1  | Selección de campos |
| 2  | Filtro por puntuación |
| 3  | Filtro por jugador |
| 4  | Varias condiciones en un predicado |
| 5  | Función de recuento |

---

## Consulta 1 · Selección de campos

**Pregunta:** ¿Cuáles son los nombres de todos los jugadores del ranking?

**Expresión:**
```
/ranking/jugador/nombre
```

**Resultado esperado:** los 10 nombres, en el orden del XML.

**Resultado obtenido:**
```
<nombre>Jon</nombre>
<nombre>Raeghar</nombre>
<nombre>Harry</nombre>
<nombre>Edixon</nombre>
<nombre>Pepe</nombre>
<nombre>Tywin</nombre>
<nombre>Andres</nombre>
<nombre>Cersei</nombre>
<nombre>Rodrigo</nombre>
<nombre>Martin</nombre>
```

## Consulta 2 · Filtro por puntuación

**Pregunta:** ¿Qué jugadores tienen más de 320 puntos?

**Expresión:**
```
/ranking/jugador[puntuacion > 320]/nombre
```

**Resultado esperado:** Jon (327), Raeghar (326) y Harry (323). Edixon y Pepe tienen exactamente 320, por lo que no entran.

**Resultado obtenido:**
```
<nombre>Jon</nombre>
<nombre>Raeghar</nombre>
<nombre>Harry</nombre>
```

## Consulta 3 · Filtro por jugador

**Pregunta:** ¿Qué puntuación tiene el jugador Edixon?

**Expresión:**
```
/ranking/jugador[nombre='Edixon']/puntuacion/text()
```

**Resultado esperado:** solo el valor 320, sin etiquetas (`text()` devuelve el texto del elemento y no el elemento completo).

**Resultado obtenido:**
```
320
```

## Consulta 4 · Combinación de condiciones en un predicado

**Pregunta:** ¿Qué otro jugador está empatado a 320 puntos con Edixon?

**Expresión:**
```
/ranking/jugador[puntuacion = 320 and nombre != 'Edixon']/nombre
```

**Resultado esperado:** Pepe. Cumple las dos condiciones a la vez (misma puntuación y distinto nombre).

**Resultado obtenido:**
```
<nombre>Pepe</nombre>
```

## Consulta 5 · Función de recuento

**Pregunta:** ¿Cuántos jugadores tienen exactamente 200 puntos?

**Expresión:**
```
count(/ranking/jugador[puntuacion = 200])
```

**Resultado esperado:** 3 (Andres, Cersei y Rodrigo).

**Resultado obtenido:**
```
3
```

---

## Qué demuestran los datos

- Las consultas 2 y 4 usan el valor 320 de formas distintas: en la 2 el operador `>` excluye a los empatados; en la 4 la condición `and` los recupera y descarta a uno por nombre.
- La consulta 3 filtra por texto (`nombre`) y usa `text()` para obtener solo el valor; la 2 filtra por número (`puntuacion`), que XPath convierte a número al comparar con `>`.
- La consulta 5 devuelve un número, no un conjunto de nodos.

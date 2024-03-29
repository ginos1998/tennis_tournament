# tennis_tournament

## Descripcion

Simulador de partido de tenis hecho completamente en Java, utilizando JavaFX para GUI. 
La simulación muestra el nombre del torneo, nombre de jugadores y paso a paso el desarrollo del juego: 
indica quien tiene el saque, muestra la cantidad de puntos, games, sets y el resultado de cada set. 

## Guia de usuario
Al inciar, el programa muestra un boton para iniciar el torneo. Al presionarlo, 
se actualiza la ventana donde el usuario deberá asignar un nombre al torneo y a los jugadores, 
con un minimo de 6 caracteres. Con la barra deslizadora puede cambiar la probabilidad de ganar (1-99%) 
de los jugadores: hacia donde se deslice el cursor será el jugador que mas probabilidad de ganar tendrá. Al deslizar el cursor, 
se muestra la probabilidad parcial asignada para cada jugador. Por último, indicar la cantidad de sets (3 ó 5). 
Una vez completados los campos, el usuario podrá iniciar la simulacion del partido con el boton "Iniciar Partido".

En la pantalla de la simulacion del partido, el usuario podrá ver toda la dinámica del juego.

Al finalizar la simulacion, es decir cuando haya un ganador, se muestran los resultados finales y el usuario podrá
elegir la opción de jugar una revancha (botón "Revancha") con la misma configuracion, 
o volver al menu de configuracion (boton "Inicio") para cargar nuevos datos. 

En cada escena, el usuario puede cerrar correctamente el programa haciendo click en el botón "salir".

## Requisitos de instalación

El ejecutable (.jar) se encuentra en la carpeta Release. Para su ejecución, es necesario [Java 19](https://jdk.java.net/19/) 
o superior y [JavaFX](https://gluonhq.com/products/javafx/). Asegúrese de [configurar](https://openjfx.io/openjfx-docs/#install-javafx) 
correctamente JavaFX. Al ser un archivo .jar, es compatible en Windows y Linux.

### Ejecucion 
Independientemente del sistema operativo, abrir una terminal dentro de la carpeta donde se encuentra 
el archivo .jar y ejecutar:

    java -jar Tennis_Tournament-1.0-SNAPSHOT-shaded.jar

También es posible ejecutarlo haciendo doble click sobre el archivo.

## Demo e Imagenes

En el siguiente [link](https://drive.google.com/file/d/188oVOnfPHRWTRIgx7zK9h1HN8weB-IJ-/view) 
encontrrás un video de demostración. A continuación, se muestran algunas capturas durante la simulacion:

<img src="https://github.com/ginos1998/tennis_tournament/blob/main/Docs/Images/Captura%20desde%202022-11-21%2019-25-08.png" 
    alt="Inicio" caption="Imagen 1 - Pantalla de inicio">

![Configuracion](https://github.com/ginos1998/tennis_tournament/blob/main/Docs/Images/Captura%20desde%202022-11-21%2019-30-11.png "Vista nfiguracion")
![Simulacion](https://github.com/ginos1998/tennis_tournament/blob/main/Docs/Images/Captura%20desde%202022-11-21%2019-31-32.png "Durante la simulacion")
![Resultados](https://github.com/ginos1998/tennis_tournament/blob/main/Docs/Images/Captura%20desde%202022-11-21%2019-32-28.png "Resultados del partido")




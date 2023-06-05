# EcoApp
TFG
Aquí supongo que habrá que poner algo interesante xD


### Estructura de la aplicación (En orden descendente):

    -CoordinatorLayaout
    		-AppBarLayout:
    					-Toolbar con título de la función del fragmento seleccionado.

			-FrameLayaout: Aquí se ejecutará la función y aparecerá el contenido de los fragments.

			-BottomNavigationView: Con los siguientes iconos (ordenados de izquierda a derecha):
	
					-Home: Apecerá lo siguiente (En orden descendente):
					 	-Fecha, hora y tiempo en donde este localizado.
						-Ventana con consejo aleatorio sobre reciclaje, reducción de residuos o ecologismo.
						-Imagen motivadora aleatoria.
	

					-Google Maps API: Fragment con Mapa de Madrid señalizando donde están los cubos de basura, de que tipo son, puntos limpios, etc

					-Reciclar: Fragment Lista de categorias de residuos, al pulsar la categoria se pasa al siguiente fragmento en el que se muestran instrucciones de como reciclar el residuo.

					-Chat IA (opcional): Fragmento de chat con una IA al que hacer preguntas y dudaas reciclaje, reducción de residuos o ecologismo.

					-Otros: Al pulsar este icono se eleve un PopUpMenu hacia arriba desde la barra del BottomNavigationView, mostrando unas lista de opciones verticales:

						-Mensajes/Notificaciones: Fragment con notificaciones de la app o mensajes.
						-Ajustes: Fragment list con las categorias que se pueda configurar:
																	-Idioma
																	-Notificaciones
																	-Ubicación/Dirección principal
						-Ayuda: Dudas/FAQ: Fragment list con dudas
						-Contacto: Fragment con formulario de contacto.
						-Acerca de: Fragment con información sobre la app, el proyecto y los autores.

\

Al iniciar hacer registro (Código Postal, calle, etc) que usaremos como ubicación inicial para en el Fragment de Maps.

La información sensible, como la dirección del usuario, etc, debera guardarse en el dispositivo en un fichero interno.

\

**La información sobre los contenedores**, puntos limpios, etc la sacaremos del portal "datos abiertos", del ayuntamiento de Madrid: [link](https://datos.madrid.es/portal/site/egob/menuitem.c05c1f754a33a9fbe4b2e4b284f1a5a0/?vgnextoid=38ed95bac1ba6610VgnVCM1000001d4a900aRCRD&vgnextchannel=374512b9ace9f310VgnVCM100000171f5a0aRCRD&vgnextfmt=default).

\

La UI implementar Material Design ¿2?¿3?

\

**Best practices in Android development** [link](https://github.com/futurice/android-best-practices/blob/master/README.md).
\
**Tutorial de GIT y GitHub desde Android Studio** [link](https://www.youtube.com/watch?v=-q4uBF-vk4Q)
\
Estos son mas cortos, pero están en ingles. Parte 1 [link](https://www.youtube.com/watch?v=hMYM-_t_Go4)
Parte2 [link](https://www.youtube.com/watch?v=-q4uBF-vk4Q)
\
**ENLACE AL WORKSPACE DE TRELLO DESDE DONDE SEGUIR LAS TAREAS** [link](https://trello.com/invite/ecolago/ATTI2b941ecfc4ea7ef2dd3b91fe2c841199CE932E1C)

### Herramientas y software:

1. [x] -Control de versiones y colaboración en el código: **GitHub**, GitLab
2. [x] -Comunicación: Slack, **Teams**, Discord.
3. [x] -Gestión de Proyecto y Seguimiento de tareas: **Trello**, Asana, Notion.
4. [ ] -Diseño y Prototipado de UI: Figma, Adobe XD, Invision Studio
5. [x] -IDE: **Android Studio**.
6. [x] -Git GUI: GitHub, **GitKraken**, SourceTree

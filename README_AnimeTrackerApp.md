

## Participantes

- **Franco Caneda**
- **German Chiozzini**
- **Nicolas Barreiro**

## Video demostración

Se comparte link de Google Drive donde se encuentra alojado el video demostrativo
https://drive.google.com/drive/folders/1mVAYE_w3lmb5EmhfwY5QrRUp9hb71uUa?usp=drive_link

## Codigo de la aplicación:
Se comparte el código de la aplicación a través de GITHUB comprimido en un 7z para evitar problemas con el peso de los archivos que lo componen.

# AnimeTrackerApp 

**AnimeTrackerApp** Es una aplicación Android desarrollada en Kotlin que permite a los usuarios gestionar su historial de series de anime de forma simple, visual y divertida. El objetivo principal de la app es ayudar a los fans del anime a **registrar, editar, eliminar y visualizar** las series que han visto, están viendo o desean ver, con una interfaz inspirada en servicios como Crunchyroll.

---

##  ¿Qué hace esta aplicación?

La aplicación permite al usuario:

- **Agregar** series de anime con información como nombre, año, comentario, género, valoración y estado.
- **Editar** cualquier serie ya registrada para actualizar sus datos.
- **Eliminar** series que ya no quiera mantener en su historial.
- **Ver un listado completo** con todas las series registradas, con opciones de búsqueda y filtrado.
- **Filtrar por año o por valoración**, y buscar por nombre usando el buscador en tiempo real.
- **Visualizar las series con tarjetas de colores personalizados** según su género y estado, mejorando la experiencia visual.
- **Mantener persistencia de datos**, gracias al uso de `SharedPreferences`, lo cual permite que las series se conserven aunque la app se cierre o el dispositivo se reinicie.

---

## Pantalla inicial

Al abrir la aplicación, el usuario accede a la **pantalla de registro** (`MainActivity`). Allí puede completar los campos del formulario para agregar una nueva serie.

Una vez registrada la primera serie, se habilita el botón para acceder al **listado de series**, donde podrá administrar toda su colección.

---

## Funcionalidades clave

| Función                  | Descripción |
|--------------------------|-------------|
| Registro de serie        | Carga de datos: nombre, año, comentario, género, estado y valoración (de 0 a 7). |
| Edición de serie         | Permite modificar los datos de una serie previamente guardada. |
| Eliminación de serie     | Se puede borrar cualquier serie con un solo toque. |
| Listado con filtros      | Visualización organizada con filtros por año o valoración y búsqueda por nombre. |
| Persistencia de datos    | Las series se almacenan localmente usando `SharedPreferences` en formato JSON. |
| Estética adaptada        | Uso de `CardView`, colores según género y estado, y diseño con `Material Design`. |


## Tecnologías utilizadas

- Kotlin 
- ViewBinding
- RecyclerView + Adapter personalizado
- SharedPreferences para persistencia de datos
- Gson para serialización
- Material Design
- Android Studio


## Persistencia de datos

Toda la información se guarda en la memoria interna del dispositivo utilizando `SharedPreferences`, lo que permite conservar los datos entre sesiones sin necesidad de conexión a internet o base de datos externa.

---

## Instrucciones para ejecutar

1. Abrí **Android Studio**
2. Seleccioná **File > Open** y buscá la carpeta del proyecto descomprimido
3. Esperá que Gradle se sincronice
4. Conectá un emulador o un dispositivo físico con Android 7.0 o superior
5. Hacé clic en `Run ▶` o presioná `Shift + F10`

---

## Ejemplo de uso

1. En la pantalla inicial (`MainActivity`), completá los campos del formulario.
2. Presioná **"Registrar"** para guardar la serie.
3. Accedé al **listado de series** con el botón correspondiente.
4. Desde el listado (`ListadoActivity`) podés:
   - Buscar series por nombre
   - Ordenar por año o valoración
   - Editar o eliminar cualquier serie tocando los botones correspondientes.

package GUI.Localization.Resources;

import java.util.ListResourceBundle;

public class SpanishGuatBundle extends ListResourceBundle {

    private Object[][] dictionary = new Object[][]{
            {"Tickets", "Entradas"},
            {"Login", "Iniciar sesión"},
            {"Password", "Contraseña"},
            {"SignIn", "Registrarse"},
            {"Register", "Registrarse"},
            {"Registration", "Registro"},
            {"Repeat", "Repetir"},
            {"Mail", "Correo"},
            {"Table", "Mesa"},
            {"Graphic", "Gráfico"},
            {"Commands", "Comandos"},
            {"Exit", "Salida"},
            {"Ticket", "Boleto"},
            {"Coordinates", "Coordenadas"},
            {"Venue", "Lugar de eventos"},
            {"Address", "Habla a"},
            {"Town", "Pueblo"},
            {"Russian", "ruso"},
            {"Hungarian", "húngaro"},
            {"Finnish", "finlandés"},
            {"Spanish", "Español"},
            {"(Guatemala)", "Guatemala"},
            {"Insert", "Insertar"},
            {"Key", "Llave"},
            {"Name", "Nombre"},
            {"Price", "Precio"},
            {"Comment", "Comentario"},
            {"Refundable", "Reintegrable"},
            {"Type", "Tipo"},
            {"Capacity", "Capacidad"},
            {"Zipcode", "Código postal"},
            {"Execute", "Ejecutar"},
            {"Update", "Actualizar"},
            {"Remove", "Eliminar"},
            {"Script", "Guión"},
            {"Lower", "Inferior"},
            {"Replace", "Reemplazar"},
            {"If", "Si"},
            {"Greater", "Mayor"},
            {"Filter", "Filtrar"},
            {"By", "Por"},
            {"Client", "Cliente"},

            {"English", "Inglés"},
            {"Spanish (Guatemala)", "Español (Guatemala)"}

    };

    @Override
    protected Object[][] getContents() {
        return dictionary;
    }
}

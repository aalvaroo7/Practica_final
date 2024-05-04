package Utilidades;
public class Utilidades {

    // Método para capitalizar la primera letra de una cadena
    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    // Método para convertir una cadena a un número entero, devolviendo un valor predeterminado si la conversión falla
    public static int stringToInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}

import java.util.Map;

public class Plato {
    String nombre;
    int tiempoTotal;
    Map<String, Integer> tiempo;
    Map<String, String> nutricion;

    public Plato(String nombre, int tiempoTotal, Map<String, Integer> tiempo, Map<String, String> nutricion) {
        this.nombre = nombre;
        this.tiempoTotal = tiempoTotal;
        this.tiempo = tiempo;
        this.nutricion = nutricion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public Map<String, Integer> getTiempo() {
        return tiempo;
    }

    public void setTiempo(Map<String, Integer> tiempo) {
        this.tiempo = tiempo;
    }

    public Map<String, String> getNutricion() {
        return nutricion;
    }

    public void setNutricion(Map<String, String> nutricion) {
        this.nutricion = nutricion;
    }
}

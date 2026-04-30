package ejercicio2;

public class Empleado {
    private String nombre;
    private int edad;
    private String ciudad;
    private String fechaDeNacimiento;
    private ColorOjos colorOjos;

    public Empleado(String nombre, int edad, String ciudad, String fechaDeNacimiento, ColorOjos colorOjos) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.colorOjos = colorOjos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public ColorOjos getColorOjos() {
        return colorOjos;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", ciudad='" + ciudad + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", colorOjos=" + colorOjos +
                '}';
    }
}

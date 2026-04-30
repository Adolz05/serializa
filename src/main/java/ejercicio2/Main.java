package ejercicio2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static void main(String[] args) {
        File file1 = new File("src/main/resources/ejercicio2/empleados_input.json");
        System.out.println(file1.exists());

        String rutaInput = "src/main/resources/ejercicio2/empleados_input.json";
        String rutaSerializado = "src/main/resources/ejercicio2/empleados.json";

        List<Empleado> empleados = leerEmpleadosDesdeJson(rutaInput);

        try (FileWriter fw = new FileWriter(rutaSerializado)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fw.write(gson.toJson(empleados));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        int contador = 1;

        File file;

        do {
            file = new File("src/main/resources/ejercicio2/empleados_correjidos_v" +contador +".json");
            contador++;
        } while (file.exists());
        String rutaCorregida = file.getPath();

            try {
                List<String> lineas = Files.readAllLines(Paths.get(rutaSerializado));
                List<String> lineasCorregidas = new ArrayList<>();

                for (String linea : lineas){
                    if (linea.contains("\"ciudad\"")){
                        String nuevaLinea = "";
                        for (int i = 0; i < linea.length(); i++) {
                            char c = linea.charAt(i);
                            if (c == 'b'){
                                nuevaLinea += 'v';
                            } else if (c == 'v') {
                                nuevaLinea += 'b';
                            }else if (c == 'B') {
                                nuevaLinea += 'V';
                            }else if (c == 'V') {
                                nuevaLinea += 'B';
                            }else {
                                nuevaLinea += c;
                            }

                        }

                        lineasCorregidas.add(nuevaLinea);
                    }else {
                        lineasCorregidas.add(linea);
                    }
                }
                Files.write(file.toPath(), lineasCorregidas);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            List<Empleado> empleadosCorregidos = leerEmpleadosDesdeJson(rutaCorregida);
            System.out.println("--- EMPLEADOS CORREGIDOS ---");
            for (Empleado empleado: empleadosCorregidos){
                System.out.println(empleado.toString());
            }

            System.out.println();
            System.out.println("--- CONTEO POR COLOR DE OJOS ---");
            empleadosCorregidos.stream().collect(Collectors.groupingBy(Empleado::getColorOjos, Collectors.counting()))
                    .forEach((color, cantidad) -> System.out.println(color + ": " + cantidad));

            List<Empleado> mayores30 = empleadosCorregidos.stream().filter(empleado -> empleado.getEdad() > 30).collect(Collectors.toList());

            try(FileWriter fw = new FileWriter("src/main/resources/ejercicio2/empleados_mayores_30.json")){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                fw.write(gson.toJson(mayores30));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }



    public static List<Empleado> leerEmpleadosDesdeJson(String ruta){
        List<Empleado> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(ruta)){
            Gson gson = new Gson();
            Empleado [] arrayempleados = gson.fromJson(reader, Empleado[].class);
            if (arrayempleados !=null ){
                lista = new ArrayList<>(Arrays.asList(arrayempleados));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

}


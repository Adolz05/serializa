import ejercicio1.Plato;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

void main() {

    String rutaCsv = "src/main/resources/ejercicio1/platos.csv";
    String rutaJson = "src/main/resources/ejercicio1/platos.json";

    List<Plato> listaPlatos = new ArrayList<>();

    try {
        List<String> lineas = Files.readAllLines(Paths.get(rutaCsv));

        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            if (!linea.trim().isEmpty()) {
                String[] datos = linea.split(";");

                String nombre = datos[0].trim();
                int prep = Integer.parseInt(datos[1]);
                int cook = Integer.parseInt(datos[2]);

                int tiempoTotal = prep + cook;

                Map<String, Integer> tiempo = new LinkedHashMap<>();
                tiempo.put("Coccion", cook);
                tiempo.put("Preparacion", prep);

                String hierro = datos[5].trim().toLowerCase();
                String hierroIngles = "low";

                if (hierro.equals("alto")) {
                    hierroIngles = "high";
                } else if (hierro.equals("medio")) {
                    hierroIngles = "medium";
                }

                Map<String, String> nutricion = new LinkedHashMap<>();

                nutricion.put("Calorias", datos[3].trim() + " kcal");
                nutricion.put("Fibra", datos[4].trim() + " g");
                nutricion.put("Hierro", hierroIngles);

                Plato plato = new Plato(nombre, tiempoTotal, tiempo, nutricion);

                listaPlatos.add(plato);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(listaPlatos);

        IO.println(jsonOutput);

        FileWriter fw = new FileWriter(rutaJson);
        fw.write(jsonOutput);
        fw.close();

        System.out.println("PLATOS RECOLECTADOS");
        System.out.println("*******************");
        System.out.println();

        for (Plato plato: listaPlatos){
            System.out.println("Nombre '" + plato.getNombre() + "', tiempoTotal=" + plato.getTiempoTotal() + " minutos");

            System.out.println("\t"+"Desglose de tiempos");
            for (Map.Entry<String,Integer> entry : plato.getTiempo().entrySet()){
                System.out.println("\t" + "\t" + entry.getKey() + "\t" + entry.getValue());
            }

            System.out.println("\t"+"Desglose de Nutrición");
            for (Map.Entry<String,String> entry : plato.getNutricion().entrySet()){
                System.out.println("\t" + "\t" + entry.getKey() + "\t" + entry.getValue());
            }

            System.out.println();
        }
    } catch (IOException e) {
        IO.println("Error procesando los ficheros: " + e.getMessage());
    } catch (Exception e) {
        IO.println("Error del formato de los datos: " + e.getMessage());
    }


}

/**
 * La clase DataProcessor contiene métodos para procesar datos de un archivo CSV y generar
 * archivos de salida basados en una plantilla.
 */
package org.example;

import java.io.*;
import java.util.ArrayList;

public class DataProcessor {
    /**
     *
     * @param csv El nombre del archivo CSV que contiene los datos de entrada.
     * @param txt El nombre del archivo de plantilla de texto a procesar.
     */
    public static void processFiles(String csv, String txt) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(csv))) {
            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split("/");
                if (data.length >= 5) {
                    String id = data[0];
                    String company = data[1];
                    String city = data[2];
                    String email = data[3];
                    String worker = data[4];
                    String despedida = data[5];

                    processTemplate(txt, id, city, email, company, worker, despedida);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
    /*se abre el archivo de texto de la plantilla para lectura y se crea una lista templateLines
    para almacenar las líneas procesadas.*/
    private static void processTemplate(String txt, String id, String city, String email, String company, String worker, String despedida) {
        try (BufferedReader txtReader = new BufferedReader(new FileReader(txt))) {

            ArrayList<String> templateLines = new ArrayList<>();
            String line;
            while ((line = txtReader.readLine()) != null) {
                line = line.replace("%%2%%", city)
                        .replace("%%3%%", email)
                        .replace("%%4%%", company)
                        .replace("%%5%%", worker)
                        .replace("%%6%%", despedida);


                //excepcion en caso de no remplazar algun marcador da error
                if (line.contains("%%")){
                    throw new IllegalArgumentException("Marcador no remplazado en la linea: "+ line);
                }

                templateLines.add(line);
                System.out.println(line);
            }
            writeTemplateExit(id, templateLines);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo template: " + e.getMessage());
        }
    }
    /*
    * Una vez que se han procesado todas las líneas de la plantilla, se llama al método writeTemplateExit
    * para escribir las líneas procesadas en un archivo de salida con un nombre basado en el id
    * */
    private static void writeTemplateExit(String id, ArrayList<String> lines) {
        File fileexit = new File("Salida");
        //preguntamos si el archivo existe o no en caso de que no lo cree.
        if (!fileexit.exists()) {
            fileexit.mkdir();
        }

        try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter("Salida/template-" + id + ".txt"))) {
            for (String line : lines) {
                outputWriter.write(line);
                outputWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error de datos: " + e.getMessage());
        }
    }
}

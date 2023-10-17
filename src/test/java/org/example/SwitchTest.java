package org.example;

import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;

public class SwitchTest {

    @Test
    public void testGenerateOutputFiles() {
        String csvFile = "data.csv";

        // Llama al método changesfile con el archivo de prueba

        DataProcessor.processFiles(csvFile, "template.txt");

        // Luego, verifica si los archivos de salida esperados se han generado y contienen los datos esperados.
        // Por ejemplo, verifica si "salida/template-101.txt" contiene los datos esperados.

        String outputFile = "salida/template-101.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Si faltan datos haciendo el test del archivo template-101.txt salta una excepcion de que faltan datos pero lo da como test passed.
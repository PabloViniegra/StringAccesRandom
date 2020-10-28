import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("fichero.txt");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre del fichero: ");
        String nombre = sc.nextLine();
        if (!file.getName().equalsIgnoreCase(nombre)) {
            System.out.println("No existe tal fichero");
            System.exit(0);
        } else if (file.length() == 0) {
            System.out.println("El archivo está vacío");
            System.exit(0);
        } else {

            System.out.print("Escribe una palabra a buscar: ");
            String palabra = sc.nextLine();
            ArrayList<String> lista = new ArrayList<>();
            try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                raf.seek(0);
                String linea;
                byte[] bytes;
                while (raf.getFilePointer() != raf.length()) {
                    linea = raf.readLine();
                    if (linea.contains(palabra)) {
                        lista.add(linea);
                    }


                }
                ArrayList<String> auxList = new ArrayList<>();
                for (String s : lista) {
                    System.out.println(s);
                    String aux = null;
                    if (s.contains(palabra)) {
                        aux = upperCaseWord(s,palabra);
                    }
                    System.out.println(aux);
                    auxList.add(aux);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int size = 0;
            try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
                while (raf.getFilePointer() != raf.length()) {

                    if (raf.readLine().contains(palabra)) {
                        raf.seek(raf.getFilePointer());
                        raf.writeChars(lista.get(size));
                        size++;
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String upperCaseWord (String str, String word )
    {
        String finalStr = null;
        if ( str.indexOf ( word ) != -1 ) // Checking the Existance of word
        {
            int strLen = str.length();
            int index = str.indexOf ( word );
            int length = word.length();
            word = word.toUpperCase();
            String part1 = str.substring ( 0 , index );
            String part2 = str.substring ( index + length );
            finalStr = part1 + word + part2;
        }
        return finalStr;
    }
}

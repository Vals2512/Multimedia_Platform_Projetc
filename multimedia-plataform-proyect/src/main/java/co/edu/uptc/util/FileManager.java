package co.edu.uptc.util;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/***
 * @author Felipe Luna
 * La clase file manager hace uso de la dependencia de gson para serializar y despues crear,leer, actualizar y
 * borrar archivos .json
 */
public class FileManager {
     private Gson gson;
     private File file;
     private BufferedReader br;

     private PrintWriter pw;
//     private JsonParser jp=new JsonParser();

     public static final String PATH="src\\main\\java\\co\\edu\\uptc\\persistence\\",EXTENSION=".json";
     public FileManager() {
          this.gson = new GsonBuilder().setPrettyPrinting().create();
     }

     /***
      * sirve para crear un archivo .json
      * @param fileName nombre del archivo
      * @return
      */
     public boolean createFile(String fileName){
          try {
               file = new File(fileName);
               pw = new PrintWriter(new FileWriter(PATH + file + EXTENSION,true));
               pw.close();
               return true;
          }catch (IOException e){
          return false;
          }
     }

     /**
      *
      * @param fileName nombre del archivo sin extension, si no existe lo crea
      * @param objList la lista que tiene los objetos a guardar que debe estar previamente añadido
      * @param type el tipo de dato que maneja el objeto list
      * @return falso si hay un error dentro del proceso del metodo
      * @param <T> el tipo de dato en la lista
      */
     public <T> boolean saveObjectToFile(String fileName, List<T> objList, Type type){
          try{
               file = new File(fileName);
               pw = new PrintWriter(new FileWriter(PATH + file + EXTENSION));
               String json= gson.toJson(objList,type);

               pw.println(json);
               pw.close();
               return true;

          }catch (IOException e){
               return false;
          }
     }

     /***
      *
      *
      * @param fileName
      * @param type el tipo de array con el tipo de
      * @return
      * @param <T>
      */
     public <T> ArrayList<T> readFile(String fileName,Type type){
          try {

               ArrayList<T> typeArrayList;
               file = new File(fileName);
              br=new BufferedReader(new FileReader(PATH+file+EXTENSION));
              StringBuilder content=new StringBuilder();
              String line= br.readLine();
              while(line!=null) {

                   content.append(line);
                   line= br.readLine();
              }
              typeArrayList=gson.fromJson(content.toString(),type);
               return typeArrayList;
          } catch(IOException e){
               return null;
          }
     }
     //borrar solo es no leer el objeto


}

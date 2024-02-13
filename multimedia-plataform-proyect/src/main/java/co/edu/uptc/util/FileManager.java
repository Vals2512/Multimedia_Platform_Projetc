package co.edu.uptc.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

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
//     private BufferedReader br;
     private FileReader fr;
     private PrintWriter pw;

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
     public <T> boolean addObject(String fileName, List<T> objList, Type type){
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
     public void readFile(String fileName,Type typeobjs){
          try {
               file = new File(fileName);
               fr=new FileReader(PATH+file+EXTENSION);
//               JsonParser parser=new JsonParser();
//               Object obj= parser.parse(fr);
//               JsonObject jo=(JsonObject) obj;
//               JsonArray ja=jo.getAsJsonArray();
//               Type arrayType=

          }catch(IOException e){

          }
     }
     public void deleteFile(){}
}

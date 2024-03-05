package co.edu.uptc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// import com.itextpdf.text.BaseColor;
// import com.itextpdf.text.Document;
// import com.itextpdf.text.Element;
// import com.itextpdf.text.Font;
// import com.itextpdf.text.FontFactory;
// import com.itextpdf.text.Image;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.Phrase;
// import com.itextpdf.text.pdf.PdfPCell;
// import com.itextpdf.text.pdf.PdfPTable;
// import com.itextpdf.text.pdf.PdfWriter;

import co.edu.uptc.model.Payment;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Series;
import co.edu.uptc.model.User;

public class FileManagement {

    private String filename1 = "Series";    
    private String filename2 = "Peliculas";
    private String filename3 = "Usuarios";
    private static final Type SERIES_TYPE = new TypeToken<List<Series>>() {}.getType();
    private static final Type USERS_TYPE = new TypeToken<List<User>>() {}.getType();

    public static final String filePath = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    public static final String filePath1 = "src\\co\\edu\\uptc\\facturas\\";

    public static final String fileExtension = ".json";
    public static final String fileExtension1 = ".pdf";

    String fileNamee1 = filePath+filename1+fileExtension;
    String fileNamee3 = filePath+filename3+fileExtension;


    Gson gson = new Gson();


public void register(String email, String password, Plan plan, Payment payment) {
        User user = new User(email, password, plan, payment);
        List<User> users = readJsonFile(fileNamee3, USERS_TYPE);
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("El correo electrónico ya está en uso");
            }
        }
        users.add(user);
        writeJsonFile(fileNamee3, users);
    }

    public boolean login(String email, String password){
        List<User> users = readJsonFile(fileNamee3, USERS_TYPE);
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
        
    }



    public void saveSerie(Series serie){
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        series.add(serie);
        writeJsonFile(fileNamee1, series);

    }

    
    public void displaySeries(){
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        for (Series serie : series) {
            System.out.println(serie);
        }
    }

    public Payment makePayment(Plan plan, String metodoPago) {
        Payment payment = new Payment(plan.getPrice(), metodoPago);
        return payment;
    }

    // public void generarFactura(User user, String nombreArchivo) {
    //     Document document = new Document();
    //     try {
    //         PdfWriter.getInstance(document, new FileOutputStream(filePath1 + nombreArchivo + fileExtension1));
    //         document.open();
    
    //         // Logo
    //         Image logo = Image.getInstance("src\\co\\edu\\uptc\\images\\Logo_de_la_UPTC..png");
    //         logo.scaleToFit(100, 100);
    //         logo.setAlignment(Element.ALIGN_RIGHT);
    //         document.add(logo);
    
    //         // Título
    //         Font fontTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, BaseColor.BLACK);
    //         Paragraph titulo = new Paragraph("Factura de Registro", fontTitulo);
    //         titulo.setAlignment(Element.ALIGN_CENTER);
    //         titulo.setSpacingAfter(25);
    //         document.add(titulo);
    
    //         // Tabla
    //         PdfPTable table = new PdfPTable(2); // 2 columnas
    //         table.setWidthPercentage(100); // Ancho completo
    //         PdfPCell cell;
    
    //         Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
    //         Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
    
    //         cell = new PdfPCell(new Phrase("Email", fontHeader));
    //         cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase(user.getEmail(), fontBody));
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase("Plan", fontHeader));
    //         cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase(user.getPlan().getNombre(), fontBody));
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase("Precio", fontHeader));
    //         cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase(String.valueOf(user.getPlan().getPrecio()), fontBody));
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase("Método de pago", fontHeader));
    //         cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
    //         table.addCell(cell);
    
    //         cell = new PdfPCell(new Phrase(user.getPago().getMetodoPago(), fontBody));
    //         table.addCell(cell);
    
    
    //         document.add(table);
    
    //         // Pie de página
    //         Font fontFooter = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY);
    //         Paragraph footer = new Paragraph("Gracias por su compra", fontFooter);
    //         footer.setAlignment(Element.ALIGN_CENTER);
    //         footer.setSpacingBefore(25);
    //         document.add(footer);
    
    //         document.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
   
    public <T> List<T> readJsonFile(String fileName, Type type) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void deleteMovie(){}

    private <T> void writeJsonFile(String fileName, List<T> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

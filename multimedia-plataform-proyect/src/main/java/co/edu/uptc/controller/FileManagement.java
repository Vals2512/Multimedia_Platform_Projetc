package co.edu.uptc.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Payment;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Series;
import co.edu.uptc.model.User;

public class FileManagement {

    private String filename1 = "Series";
    private String filename2 = "Peliculas";
    private String filename3 = "users";
    private static final Type SERIES_TYPE = new TypeToken<List<Series>>() {
    }.getType();
    private static final Type USERS_TYPE = new TypeToken<List<User>>() {
    }.getType();

    public static final String filePath = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    public static final String filePath1 = "src\\main\\java\\co\\edu\\uptc\\invoices\\";

    public static final String fileExtension = ".json";
    public static final String fileExtension1 = ".pdf";
    String fileNamee1 = filePath + filename1 + fileExtension;
    String fileNamee3 = filePath + filename3 + fileExtension;
    private List<Series> seriesList;


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

    public boolean login(String email, String password) {
        List<User> users = readJsonFile(fileNamee3, USERS_TYPE);
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }

    public void saveSerie(Series serie) {
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        series.add(serie);
        writeJsonFile(fileNamee1, series);

    }

    
    public List<Series> getSeries() {
        return readJsonFile(fileNamee1, SERIES_TYPE);
    }

    public void deleteSeries(String seriesName) {
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        Series seriesToDelete = null;
        for (Series s : series) {
            if (s.getName().equals(seriesName)) {
                seriesToDelete = s;
                break;
            }
        }
        if (seriesToDelete != null) {
            series.remove(seriesToDelete);
            writeJsonFile(fileNamee1, series);
        } else {
            throw new IllegalArgumentException("La serie " + seriesName + " no existe");
        }
    }

    public Payment makePayment(Plan plan, String metodoPago) {
        Payment payment = new Payment(plan.getPrice(), metodoPago);
        return payment;
    }

    public List<String> playSerie(String serieName) {
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        List<String> playbackLog = new ArrayList<>();
        for (Series serie : series) {
            if (serie.getName().equals(serieName)) {
                playbackLog.add("Playing: " + serie.getName());
                for (Season season : serie.getSeasons()) {
                    for (Chapter chapter : season.getChapters()) {
                        playbackLog.add("Playing chapter: " + chapter.getName() + ", Season: " + season.getName());
                        playbackLog.add("Chapter duration is: " + chapter.getDuration() + " minutes");
                        int numberOfSpaces = 50;
                        long episodeDuration = chapter.getDuration() * 10;
                        for (int currentPitch = 0; currentPitch <= numberOfSpaces; currentPitch++) {
                            int porcentaje = (currentPitch * 100) / numberOfSpaces;

                            StringBuilder progress = new StringBuilder("\r|");
                            for (int i = 0; i < currentPitch; i++) {
                                progress.append("*");
                            }

                            for (int i = currentPitch; i < numberOfSpaces; i++) {
                                progress.append(" ");
                            }

                            progress.append("|").append(porcentaje).append(" %");
                            playbackLog.add(progress.toString());
                            try {
                                Thread.sleep(episodeDuration / numberOfSpaces); // Espera la cantidad de tiempo
                                                                                // calculada
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        playbackLog.add("The episode " + chapter.getName() + ", Season: " + season.getName()
                                + " has finished playing.");
                        playbackLog.add("Do you want to play the next episode? (1 for yes, 0 for no)");
                    }

                }
                playbackLog.add("There are no more episodes to play.");
                playbackLog.add("Do you want to play another series? (1 for yes, 0 for no)");
            }
        }
        return playbackLog;

    }

    public void generarFactura(User user, String nombreArchivo) {
        try {
            PdfWriter writer = new PdfWriter(filePath1 + nombreArchivo + fileExtension1);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Logo
            Image logo = new Image(
                    ImageDataFactory.create("src\\main\\java\\co\\edu\\uptc\\images\\Logo_de_la_UPTC..png"));
            logo.scaleToFit(100, 100);
            logo.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(logo);

            // Título
            PdfFont fontTitulo = PdfFontFactory.createFont("Times-Roman");
            Style styleTitulo = new Style().setFontSize(24).setFontColor(ColorConstants.BLACK).setFont(fontTitulo);
            Paragraph titulo = new Paragraph("Registration Invoice").addStyle(styleTitulo);
            titulo.setTextAlignment(TextAlignment.CENTER);
            titulo.setMarginBottom(25);
            document.add(titulo);

            // Tabla
            float[] columnWidths = { 1, 2 };
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100)); // tabla más ancha

            PdfFont fontHeader = PdfFontFactory.createFont("Helvetica-Bold");
            Style styleHeader = new Style().setFontSize(12).setFontColor(ColorConstants.BLACK).setFont(fontHeader);
            PdfFont fontBody = PdfFontFactory.createFont("Helvetica");
            Style styleBody = new Style().setFontSize(12).setFontColor(ColorConstants.BLACK).setFont(fontBody);

            Cell cell;

            cell = new Cell().add(new Paragraph("Email").addStyle(styleHeader))
                    .setBackgroundColor(new DeviceRgb(211, 211, 211)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(user.getEmail()).addStyle(styleBody)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph("Plan").addStyle(styleHeader))
                    .setBackgroundColor(new DeviceRgb(211, 211, 211)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(user.getPlan().getName()).addStyle(styleBody)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph("Price").addStyle(styleHeader))
                    .setBackgroundColor(new DeviceRgb(211, 211, 211)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(String.valueOf(user.getPlan().getPrice())).addStyle(styleBody))
                    .setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph("Payment Method").addStyle(styleHeader))
                    .setBackgroundColor(new DeviceRgb(211, 211, 211)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(user.getPayment().getPaymentMethod()).addStyle(styleBody))
                    .setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph("Duration (days)").addStyle(styleHeader))
                    .setBackgroundColor(new DeviceRgb(211, 211, 211)).setPadding(5);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(String.valueOf(user.getPlan().getDuration())).addStyle(styleBody))
                    .setPadding(5);
            table.addCell(cell);

            document.add(table);

            // Pie de página
            PdfFont fontFooter = PdfFontFactory.createFont("Helvetica-Oblique");
            Style styleFooter = new Style().setFontSize(10).setFontColor(ColorConstants.GRAY).setFont(fontFooter);
            Paragraph footer = new Paragraph("Thanks for your purchase").addStyle(styleFooter);
            footer.setTextAlignment(TextAlignment.CENTER);
            footer.setMarginTop(25);
            document.add(footer);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private <T> void writeJsonFile(String fileName, List<T> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

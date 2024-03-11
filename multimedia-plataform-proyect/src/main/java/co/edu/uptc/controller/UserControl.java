package co.edu.uptc.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.*;
import co.edu.uptc.util.FileManager;
import com.google.gson.reflect.TypeToken;

public class UserControl {
    private ArrayList<User> users;
    private String email, password;
    private final static String FILE = "users";
    private final static Type FILETYPE = new TypeToken<ArrayList<User>>() {
    }.getType();
    private FileManager fm;

    public UserControl() {
        users = new ArrayList<>();

        fm = new FileManager();
        users = fm.readFile(FILE, FILETYPE);
        if (users == null) {
            fm.createFile(FILE);
        }

    }

    public int searchUser(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return i;
            }
        }
        return -1;
    }

    // arrgelr
    public void createPlaylist(String email, String playlistName) {
        User user = searchUserObject(email);

        if (user != null) {
            Playlist playlist = new Playlist();
            playlist.setName(playlistName);
            user.setPlaylist(playlist);

            // Guardar cambios en el archivo users.json después de crear la playlist
            fm.saveObjectToFile(FILE, users, FILETYPE);
        }
    }

    public void modifyPlaylist(String email, String playlistName) {
        User user = searchUserObject(email);

        if (user != null) {
            Playlist playlist = user.getPlaylist();

            if (playlist != null) {
                playlist.setName(playlistName);

                // Guardar cambios en el archivo users.json después de modificar la playlist
                fm.saveObjectToFile(FILE, users, FILETYPE);
            }
        }
    }

    public void deletePlaylist(String email) {
        User user = searchUserObject(email);

        if (user != null) {
            user.setPlaylist(null);

            // Guardar cambios en el archivo users.json después de borrar la playlist
            fm.saveObjectToFile(FILE, users, FILETYPE);
        }
    }

    public void deleteMoviesFromPlaylist(String email, Movies movie) {
        User user = searchUserObject(email);

        if (user != null) {
            Playlist playlist = user.getPlaylist();

            if (playlist != null) {
                List<Object> content = playlist.getContent();

                // Obtener el índice de la película en la lista de reproducción
                int index = content.indexOf(movie);

                if (index != -1) {
                    content.remove(index);
                    fm.saveObjectToFile(FILE, users, FILETYPE);
                }
            }
        }
    }

    public void deleteSeriesFromPlaylist(String email, Series series) {
        User user = searchUserObject(email);

        if (user != null) {
            Playlist playlist = user.getPlaylist();

            if (playlist != null) {
                int index = obtainIdFromSerie(series.getName());

                if (index != -1) {
                    List<Object> content = playlist.getContent();
                    content.remove(index);
                    int userIndex = obtainIdFromUser(email);
                    ArrayList<User> users = this.obtainUsers();
                    users.set(userIndex, user);
                    fm.saveObjectToFile(FILE, users, FILETYPE);
                }
            }
        }
    }

    public void addSeriesToPlaylist(String email, Series series) {
        User user = searchUserObject(email);
        boolean serie = user.getPlaylist().getContent().add(series);
        if (serie) {
            int index = obtainIdFromUser(email);
            ArrayList<User> users = this.obtainUsers();
            users.set(index, user);
            fm.saveObjectToFile(FILE, users, FILETYPE);

        }
    }

    public void addMoviesToPlaylist(String email, Movies movies) {
        User user = searchUserObject(email);
        boolean movie = user.getPlaylist().getContent().add(movies);
        if (movie) {
            int index = obtainIdFromUser(email);
            ArrayList<User> users = this.obtainUsers();
            users.set(index, user);
            fm.saveObjectToFile(FILE, users, FILETYPE);
        }
    }

    public void clearPlaylist(String email) {

        searchUserObject(email).getPlaylist().getContent().clear();
    }

    public User searchUserObject(String email) {
        User userFound = null;
        for (User uIt : users) {
            if (uIt.getEmail().equals(email)) {
                userFound = uIt;
            }
        }
        return userFound;
    }

    public int obtainIdFromUser(String email) {
        if (email != null) {
            for (int i = 0; i <= users.size(); i++) {
                if (email.equals(users.get(i).getEmail())) {
                    return i;
                }

            }

        }
        return -1;

    }

    public int obtainIdFromMovie(String movieTitle) {
        for (User user : users) {
            Playlist playlist = user.getPlaylist();
            if (playlist != null) {
                List<Object> content = playlist.getContent();
                for (int i = 0; i < content.size(); i++) {
                    if (content.get(i) instanceof Movies) {
                        Movies movie = (Movies) content.get(i);
                        if (movie.getTittle().equals(movieTitle)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1; // Devuelve -1 si la película no se encuentra en ninguna lista de reproducción.
    }

    public int obtainIdFromSerie(String seriesName) {
        for (User user : users) {
            Playlist playlist = user.getPlaylist();
            if (playlist != null) {
                List<Object> content = playlist.getContent();
                for (int i = 0; i < content.size(); i++) {
                    if (content.get(i).toString().contains("seasons")) {
                        Series series = (Series) content.get(i);
                        if (series.getName().equals(seriesName)) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1; // Devuelve -1 si la serie no se encuentra en ninguna lista de reproducción.
    }

    public void addUser(String email, String password, Plan plan, Payment payment) {
        User user = new User();

        user.setPassword(password);
        user.setEmail(email);
        user.setPlan(plan);
        user.setPayment(payment);
        if (searchUser(user.getEmail()) == -1) {
            users.add(user);
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();

            fm.saveObjectToFile(FILE, users, type);
        }
    }

    public boolean deleteUser(String email) {
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            users.remove(userIndex);
            return fm.saveObjectToFile("users", users, new TypeToken<ArrayList<User>>() {
            }.getType());

        }
        return false;
    }

    public User getUser(String email) {
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            return users.get(userIndex);
        }
        return null;
    }

    public Playlist getPlaylistByName(String email, String playlistName) {
        User user = searchUserObject(email);

        if (user != null) {
            Playlist playlist = user.getPlaylist();

            if (playlist != null && playlist.getName().equals(playlistName)) {
                return playlist;
            }
        }

        return null;
    }

    public void modifyPlaylist(String email, String currentPlaylistName, String newPlaylistName) {
        User user = searchUserObject(email);

        if (user != null) {
            Playlist playlist = user.getPlaylist();

            if (playlist != null && playlist.getName().equals(currentPlaylistName)) {
                playlist.setName(newPlaylistName);

                // Guardar cambios en el archivo users.json después de modificar la playlist
                fm.saveObjectToFile(FILE, users, FILETYPE);
            }
        }
    }

    public ArrayList<User> obtainUsers() {
        return fm.readFile(FILE, FILETYPE);
    }

    public boolean login(String email, String password) {
        User user = getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
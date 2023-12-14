package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import co.uptc.edu.model.Chapter;
import co.uptc.edu.model.Movies;
import co.uptc.edu.model.Series;

public class SeriesControl {
    private ArrayList<Series> series;

    //int chapterDuration;

    public SeriesControl() {
        series = new ArrayList<>();
    }

    public int searchSerie(String tittle) {
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getTittle().equals(tittle)) {
                return i;
            }
        }
        return -1;
    }

    public Series getSerieTittle(String tittle) {
        int serieIndex = searchSerie(tittle);
        if (serieIndex != -1) {
            return series.get(serieIndex);
        }
        return null;
    }

    public boolean addSerie(Series tittle) {
        if (searchSerie(tittle.getTittle()) == -1) {
            series.add(tittle);
            return true;
        }
        return false;
    }

    public boolean deleteSeries(String tittle) {
        if (series.remove(searchSeriesObject(tittle))) {
            return true;
        }
        return false;
    }

    public void showSeriesTittles(){
            for (int i = 0; i < series.size(); i++) {
                System.out.println((i+1)+". "+series.get(i).getTittle());
            }
        }
        
    public String showSeries() {
        return series.toString();
    }

    public Series searchSeriesObject(String name) {
        for (Series s : series) {
            if (s.getTittle().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public Series updateSerie(String title, String newTitle, int newSeasons, List<Chapter> newChapters,
            int newReleaseYear) {
        int index = searchSerie(title);
        if (index != -1) {
            Series seriesToUpdate = series.get(index);

            if (!newTitle.isEmpty()) {
                seriesToUpdate.getMultimedia().setTittle(newTitle);
            }

            if (newSeasons > 0) {
                seriesToUpdate.setSeasons(newSeasons);
            }

            if (newChapters != null && !newChapters.isEmpty()) {
                seriesToUpdate.setChapters(newChapters);
            }

            if (newReleaseYear > 0) {
                seriesToUpdate.setReleaseYear(newReleaseYear);
            }

            series.set(index, seriesToUpdate);
            return seriesToUpdate;
        }

        return null;
    }

    public ArrayList<Series> getSeries() {
        return series;
    }

    

}

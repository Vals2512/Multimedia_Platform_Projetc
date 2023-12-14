package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.function.Predicate;
import co.uptc.edu.model.Series;

public class SeriesControl {
    private ArrayList<Series> series;

    int chapterDuration = 0;

    public SeriesControl() {
        series = new ArrayList<>();
    }

    public int searchSerie(String tittle){
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getTittle().equals(tittle)) {
                return i;
            }
        }
        return -1;
    }

    public Series getSerieTittle(String tittle){
        int serieIndex = searchSerie(tittle);
        if (serieIndex != -1) {
            return series.get(serieIndex);
        }
        return null;
    }

    public boolean addSerie(Series tittle){
        if (searchSerie(tittle.getTittle()) == -1) {
            series.add(tittle);
            return true;
        }
        return false;
    }
    public boolean deleteSeries(String tittle){
        if (series.remove(searchSeriesObject(tittle))){
            return true;
        }


        return false;
    }
    public String showSeries() {

        return series.toString();
    }
    public Series searchSeriesObject(String name){
        for (Series s: series) {
            if (s.getTittle().equals(name)){
            return s;
            }
        }
        return null;
    }

}

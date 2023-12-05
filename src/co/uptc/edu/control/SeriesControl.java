package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.List;

import co.uptc.edu.model.MultimediaAction;
import co.uptc.edu.model.Series;

public class SeriesControl extends MultimediaAction {
    private List<Series> series;
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




    @Override
    public void play() {

        try {

            // System.out.println("playing");// add seconds
            Thread.sleep(chapterDuration);

            // This gives the impression that it is executed from time to time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restart'");
    }

    @Override
    public void continuePlaying() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'continuePlaying'");
    }

    @Override
    public void stopPlay() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopPlay'");
    }

}

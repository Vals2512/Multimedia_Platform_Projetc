package co.uptc.edu.control;

import co.uptc.edu.model.MultimediaAction;

public class SeriesControl extends MultimediaAction {

    int chapterDuration = 0;

    @Override
    public void play() {

        try {

            // System.out.println("playing");// agregar segundos
            Thread.sleep(chapterDuration);

            // Así, se da la impresión de que se ejecuta cada cierto tiempo
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

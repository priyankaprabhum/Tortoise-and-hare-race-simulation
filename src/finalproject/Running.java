/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author PriyankaPrabhu
 */
public class Running {

    private int step;
    private String annoucements = " ";
    TestFrame frame;
    private boolean raceStart = true;
    private boolean lock = false;
    private int tT, tH;
    int i;

    public Running(TestFrame f) {

        frame = f;
    }

    public void running(char participant, int x, int y) {
        
        movingAnimal(x, y, participant);
    }

    public int randomMove(char participant) {

        double randomMove = Math.random();
        if (participant == 'T') {
            if (randomMove > 0 && randomMove <= 0.5) {
                step = 2;
                annoucements = "Fast plod";
            } else if (randomMove > 0.5 && randomMove <= 0.7) {
                step = -4;
                annoucements = "Slip";
            } else if (randomMove > 0.7 && randomMove <= 1) {
                step = 1;
                annoucements = "Slow plod";
            }

        } else if (randomMove > 0 && randomMove <= 0.2) {
            step = 0;
            annoucements = "Sleep";
        } else if (randomMove > 0.2 && randomMove <= 0.4) {
            step = 7;
            annoucements = "Big hop";
        } else if (randomMove > 0.4 && randomMove <= 0.5) {
            step = -10;
            annoucements = "Big Slip";
        } else if (randomMove > 0.5 && randomMove <= 0.8) {
            step = 1;
            annoucements = "Small hop";
        } else if (randomMove > 0.8 && randomMove <= 1) {
            step = -2;
            annoucements = "Small slip";
        }
        return step;
    }

    public void movingAnimal(int x, int y, char p) {

        while (raceStart) {
            race_start(p);
            go(x, y, p);
            race_stop(p);
        }
        lock = false;
    }

    public synchronized void race_start(char p) {

        while (lock) {

            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception");
            }
        }
        lock = true;
    }

    public synchronized void race_stop(char p) {

        lock = false;
        notifyAll();
        try {
            if (raceStart) {
                System.out.println("===================");
                System.out.println("Participant waiting :" + p);
                System.out.println("===================");
                wait();
            }

        } catch (InterruptedException e) {
            System.out.println("Interrupted exception");
        }
    }

    public void go(int x, int y, char p) {

        int position = 20;

        int random = randomMove(p);
        if (p == 'T') {
            
            tT += (random * position);

            if (tT < 0) {
                tT = 0;
            } else if (tT >= 1000) {
               
                tT = 1000;
            }
            x = tT;
            System.out.println("=================================================================================");
            System.out.println("Participant=" + p + " Random=" + random + " x=" + x + " raceStart=" + raceStart);
            System.out.println("=================================================================================");
            frame.move(x, y, p, annoucements);

        } else {
           
            tH += (random * position);

            if (tH < 0) {
                tH = 0;
            } else if (tH >= 1000) {
               
                tH = 1000;
            }
            x = tH;
            System.out.println("=================================================================================");
            System.out.println("Participant=" + p + " Random=" + random + " x=" + x + " raceStart=" + raceStart);
            System.out.println("=================================================================================");

            frame.move(x, y, p, annoucements);
        }
        if((tH >= 1000 || tT >= 1000) && raceStart ){
            raceStart = false;
            tT = 0;
            tH = 0;
            frame.finalDisplay(p);
        }
        try {
            Thread.sleep(300);
        } catch (Exception e) {
            System.err.println("Exception");
        }
    }

    public void setRaceStart(boolean raceStart) {
        this.raceStart = raceStart;
    }
}

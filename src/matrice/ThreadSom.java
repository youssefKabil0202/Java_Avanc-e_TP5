package matrice;

public class ThreadSom extends Thread {

    int[] t = null;  // ligne de la matrice
    int som = 0;     // somme calculée par ce thread

    public ThreadSom(int[] t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            som += t[i];
        }
    }
}
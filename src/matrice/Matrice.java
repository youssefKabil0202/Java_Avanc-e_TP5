package matrice;

public class Matrice {

    static void affiche(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                System.out.print(t[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // (1) Lire n depuis args ou prendre 10 par défaut
        int n = Integer.parseInt(args.length > 0 ? args[0] : "10");

        // (2) Créer une matrice n×n
        int m[][] = new int[n][n];

        // (3) Tableau pour stocker la somme de chaque ligne
        int som[] = new int[n];

        // (4) Remplir la matrice avec des valeurs aléatoires (0..9)
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                m[i][j] = (int) (Math.random() * 10);

        // (5) Afficher la matrice
        affiche(m);

        // (6) Créer un tableau de threads
        ThreadSom[] threadSoms = new ThreadSom[n];

        // (7) Créer et démarrer un thread par ligne
        for (int i = 0; i < n; i++) {
            threadSoms[i] = new ThreadSom(m[i]); // m[i] = ligne i
            threadSoms[i].start();               // lance le thread (run en parallèle)
        }

        // (8) Attendre la fin de tous les threads
        for (int i = 0; i < n; i++) {
            threadSoms[i].join();
        }

        // (9) Récupérer les sommes calculées + affichage
        for (int i = 0; i < n; i++) {
            som[i] = threadSoms[i].som;
            System.out.println(som[i]);
        }

        // (10) Calcul de la somme totale
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += som[i];
        }

        // (11) Affichage du résultat final
        System.out.println("Somme = " + total);
    }
}

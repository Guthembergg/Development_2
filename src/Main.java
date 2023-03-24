
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static <Elemento> void main(String[] args) {

        Catalogo catalogo = new Catalogo();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Scegli un'opzione:");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Aggiungi rivista");
            System.out.println("3. Rimuovi elemento dato un codice ISBN");
            System.out.println("4. Ricerca per ISBN");
            System.out.println("5. Ricerca per anno pubblicazione");
            System.out.println("6. Ricerca per autore");
            System.out.println("7. Carica lista elementi su disco");
            System.out.println("8. Salva lista elementi su disco");
            System.out.println("9. Stampa l'intero catalogo");
            System.out.println("0. Esci dal programma");


            int scelta = input.nextInt();
            input.nextLine(); // Consuma il newline residuo


            // Consuma il carattere di fine linea

            switch (scelta) {
                case 1:
                    System.out.println("Inserisci i dati del libro:");
                    System.out.print("ISBN: ");
                    String isbn = input.nextLine();
                    System.out.print("Titolo: ");
                    String titolo = input.nextLine();
                    System.out.print("Anno pubblicazione: ");
                    int anno = input.nextInt();
                    input.nextLine(); // Consuma il newline residuo
                    System.out.print("Numero pagine: ");
                    int pagine = input.nextInt();
                    input.nextLine(); // Consuma il newline residuo
                    System.out.print("Autore: ");
                    String autore = input.nextLine();
                    System.out.print("Genere: ");
                    String genere = input.nextLine();

                    Libro libro = new Libro(isbn, titolo, anno, pagine, autore, genere);


                    catalogo.aggiungi(libro);
                    break;

                case 2:
                    System.out.println("Inserisci i dati della rivista:");
                    System.out.print("ISBN: ");
                    String isbnR = input.nextLine();
                    System.out.print("Titolo: ");
                    String titoloR = input.nextLine();
                    System.out.print("Anno pubblicazione: ");
                    int annoR = input.nextInt();
                    input.nextLine(); // Consuma il newline residuo
                    System.out.print("Numero pagine: ");
                    int pagineR = input.nextInt();
                    input.nextLine(); // Consuma il newline residuo
                    System.out.print("Periodicità (SETTIMANALE, MENSILE o SEMESTRALE): ");
                    String periodicità = input.nextLine().toUpperCase();

                    Rivista rivista = new Rivista(isbnR, titoloR, annoR, pagineR, periodicità);
                    catalogo.aggiungi(rivista);
                    break;

                case 3:
                    System.out.print("Inserisci il codice ISBN dell'elemento da rimuovere: ");
                    String isbnToRemove = input.nextLine();
                    catalogo.rimuovi(isbnToRemove);
                    break;

                case 4:
                    System.out.print("Inserisci il codice ISBN dell'elemento da cercare: ");
                    String isbnToFind = input.nextLine();
                    Optional<ElementoCatalogo> risultatoISBN = catalogo.ricercaPerISBN(isbnToFind);
                    if (risultatoISBN == null) {
                        System.out.println("Elemento non trovato");
                    } else {
                        System.out.println(risultatoISBN);
                    }
                    break;

                case 5:
                    System.out.print("Inserisci l'anno di pubblicazione: ");
                    int anno2 = input.nextInt();
                    input.nextLine();

                    List<ElementoCatalogo> elementiPerAnno = catalogo.ricercaPerAnnoPubblicazione(anno2);
                    if (elementiPerAnno.size() > 0) {
                        System.out.println("Elementi pubblicati nell'anno " + anno2 + ":");
                        for (ElementoCatalogo e : elementiPerAnno) {
                            System.out.println(e.toString());
                        }
                    } else {
                        System.out.println("Nessun elemento trovato pubblicato nell'anno " + anno2);
                    }
                    break;

                case 6:
                    System.out.print("Inserisci l'autore: ");
                    String autore2 = input.nextLine();

                    List<Libro> elementiPerAutore = catalogo.ricercaPerAutore(autore2);
                    if (elementiPerAutore.size() > 0) {
                        System.out.println("Elementi dell'autore " + autore2 + ":");
                        for (ElementoCatalogo e : elementiPerAutore) {
                            System.out.println(e.toString());
                        }
                    } else {
                        System.out.println("Nessun elemento trovato dell'autore " + autore2);
                    }
                    break;

                case 7:
                    try {
                        System.out.print("Inserisci il percorso del file: ");
                        String filePath = input.nextLine();

                        catalogo.caricaDaDisco(filePath);
                        System.out.println("Caricamento completato");
                    } catch (IOException e) {
                        System.out.println("Errore durante il caricamento del file: " + e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        System.out.print("Inserisci il percorso del file: ");
                        String filePath = input.nextLine();

                        catalogo.salvaSuDisco(filePath);
                        System.out.println("Salvataggio completato");
                    } catch (IOException e) {
                        System.out.println("Errore durante il salvataggio del file: " + e.getMessage());
                    }
                    break;
                case 9:
                    catalogo.stampaCatalogo();
                    break;
                case 0:
                    System.out.println("Uscita dal programma...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;
            }

        }
    }
}

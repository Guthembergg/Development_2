import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Catalogo {
    private List<ElementoCatalogo> lista_elementi;


    public Catalogo() {
        lista_elementi = new ArrayList<>();
    }

    public void aggiungi(ElementoCatalogo elemento) {
        if (elemento instanceof Libro) {
            Libro libro = (Libro) elemento;
            boolean isbnPresente = lista_elementi.stream()
                    .filter(e -> e instanceof Libro)
                    .map(e -> (Libro) e)
                    .anyMatch(e -> e.getISBN().equals(libro.getISBN()));
            if (!isbnPresente) {
                lista_elementi.add(libro);
                System.out.println("Libro aggiunto con successo.");
            } else {
                System.out.println("Errore: l'ISBN inserito è già presente nella lista.");
            }
        } else if (elemento instanceof Rivista) {
            Rivista rivista = (Rivista) elemento;
            boolean isbnPresente = lista_elementi.stream()
                    .filter(e -> e instanceof Rivista)
                    .map(e -> (Rivista) e)
                    .anyMatch(e -> e.getISBN().equals(rivista.getISBN()));
            if (!isbnPresente) {
                lista_elementi.add(rivista);
                System.out.println("Rivista aggiunta con successo.");
            } else {
                System.out.println("Errore: l'ISBN inserito è già presente nella lista.");
            }
        }
    }

    public void rimuovi(String ISBN) {
        lista_elementi.removeIf(elemento -> elemento.getISBN().equals(ISBN));
    }

    public Optional<ElementoCatalogo> ricercaPerISBN(String ISBN) {
        return lista_elementi.stream().filter(elemento -> elemento.getISBN().equals(ISBN)).findFirst();
    }

    public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int anno) {
        return lista_elementi.stream().filter(elemento -> elemento.getAnnoPubblicazione() == anno).collect(Collectors.toList());
    }

    public List<Libro> ricercaPerAutore(String autore) {
        return lista_elementi.stream().filter(elemento -> elemento instanceof Libro).map(elemento -> (Libro) elemento).filter(libro -> libro.getAutore().equals(autore)).collect(Collectors.toList());
    }


    public void salvaSuDisco(String nomeFile) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        for (ElementoCatalogo e : lista_elementi) {
            if (e instanceof Libro) {
                Libro l = (Libro) e;
                stringBuilder.append(l.getISBN()).append("@").append(l.getTitolo()).append("@")
                        .append(l.getAnnoPubblicazione()).append("@").append(l.getNumeroPagine()).append("@")
                        .append(l.getAutore()).append("@").append(l.getGenere()).append("#");
            } else if (e instanceof Rivista) {
                Rivista r = (Rivista) e;
                stringBuilder.append(r.getISBN()).append("@").append(r.getTitolo()).append("@")
                        .append(r.getAnnoPubblicazione()).append("@").append(r.getNumeroPagine()).append("@")
                        .append(r.getPeriodicita()).append("#");
            }
        }

        File file = new File(nomeFile);
        FileUtils.writeStringToFile(file, stringBuilder.toString(), "UTF-8");
    }

    public void stampaCatalogo() {
        System.out.println("Catalogo bibliotecario:");
        if (lista_elementi.size() == 0) {
            System.out.println("Vuoto");
        }
        for (ElementoCatalogo elemento : lista_elementi) {
            System.out.println(elemento.toString());
        }
    }

    public void caricaDaDisco(String nomeFile) throws IOException {
        File file = new File(nomeFile);

        if (!file.exists()) {
            return;
        }

        String fileContent = FileUtils.readFileToString(file, "UTF-8");

        lista_elementi.clear();

        String[] elementi = fileContent.split("#");
        for (String elemento : elementi) {
            String[] dati = elemento.split("@");
            if (dati.length == 6) {
                Libro l = new Libro(dati[0], dati[1], Integer.parseInt(dati[2]), Integer.parseInt(dati[3]), dati[4], dati[5]);
                lista_elementi.add(l);
            } else if (dati.length == 5) {
                Rivista r = new Rivista(dati[0], dati[1], Integer.parseInt(dati[2]), Integer.parseInt(dati[3]), dati[4]);
                lista_elementi.add(r);
            }
        }
    }

}

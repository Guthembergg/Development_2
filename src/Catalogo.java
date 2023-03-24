import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Catalogo {
    private List<ElementoCatalogo> lista_elementi;

    public Catalogo() {
        lista_elementi = new ArrayList<>();
    }

    public void aggiungi(ElementoCatalogo elemento) {
        lista_elementi.add(elemento);
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

    public void salvaSuDisco(String nome_file) throws IOException {
        FileOutputStream file_output = new FileOutputStream(nome_file);
        ObjectOutputStream output = new ObjectOutputStream(file_output);
        output.writeObject(lista_elementi);
        output.close();
    }

    public void caricaDaDisco(String nome_file) throws IOException, ClassNotFoundException {
        FileInputStream file_input = new FileInputStream(nome_file);
        ObjectInputStream input = new ObjectInputStream(file_input);
        lista_elementi = (List<ElementoCatalogo>) input.readObject();
        input.close();
    }
}


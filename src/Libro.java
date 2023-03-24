class Libro extends ElementoCatalogo {
    private String autore;
    private String genere;

    public Libro(String ISBN, String titolo, int anno_pubblicazione, int numero_pagine, String autore, String genere) {
        super(ISBN, titolo, anno_pubblicazione, numero_pagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public String toString() {
        return "Libro{" +
                "isbn='" + this.getISBN() + '\'' +
                ", titolo='" + this.getTitolo() + '\'' +
                ", annoPubblicazione=" + this.getAnnoPubblicazione() +
                ", numPagine=" + this.getNumeroPagine() +
                ", autore='" + this.getAutore() + '\'' +
                ", genere='" + this.getGenere() + '\'' +
                '}';
    }
}
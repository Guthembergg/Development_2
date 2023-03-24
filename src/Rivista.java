class Rivista extends ElementoCatalogo {
    private Periodicita periodicità;

    enum Periodicita {SETTIMANALE, MENSILE, SEMESTRALE}

    ;

    public Rivista(String ISBN, String titolo, int anno_pubblicazione, int numero_pagine, String periodicità) {
        super(ISBN, titolo, anno_pubblicazione, numero_pagine);
        this.periodicità = Periodicita.valueOf(periodicità);
    }

    public Periodicita getPeriodicita() {
        return periodicità;
    }

    public String toString() {
        return "Rivista{" +
                "isbn='" + this.getISBN() + '\'' +
                ", titolo='" + this.getTitolo() + '\'' +
                ", annoPubblicazione=" + this.getAnnoPubblicazione() +
                ", numPagine=" + this.getNumeroPagine() +
                ", periodicita=" + this.getPeriodicita() +
                '}';
    }
}
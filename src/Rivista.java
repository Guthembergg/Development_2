class Rivista extends ElementoCatalogo {
    private Periodicita periodicità;

    public Rivista(String ISBN, String titolo, int anno_pubblicazione, int numero_pagine, Periodicita periodicità) {
        super(ISBN, titolo, anno_pubblicazione, numero_pagine);
        this.periodicità = periodicità;
    }

    public Periodicita getPeriodicita() {
        return periodicità;
    }
}
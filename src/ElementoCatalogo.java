public abstract class ElementoCatalogo {
    private String ISBN;
    private String titolo;
    private int anno_pubblicazione;
    private int numero_pagine;


    public ElementoCatalogo(String ISBN, String titolo, int anno_pubblicazione, int numero_pagine) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.anno_pubblicazione = anno_pubblicazione;
        this.numero_pagine = numero_pagine;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return anno_pubblicazione;
    }

    public int getNumeroPagine() {
        return numero_pagine;
    }
}

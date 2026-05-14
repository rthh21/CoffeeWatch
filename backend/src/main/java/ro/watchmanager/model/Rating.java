package ro.watchmanager.model;

public enum Rating {
    UNU(1), DOI(2), TREI(3), PATRU(4), CINCI(5);

    private final int valoare;

    Rating(int valoare) {
        this.valoare = valoare;
    }

    public int getValoare() {
        return valoare;
    }

    @Override
    public String toString() {
        return String.valueOf(valoare);
    }
}

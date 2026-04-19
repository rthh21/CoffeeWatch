package ro.watchmanager.model;

public class Recenzie {
    private int rating; // de la 1 la 5
    private String text;

    public Recenzie(int rating, String text) {
        this.rating = rating;
        this.text = text;
    }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @Override
    public String toString() {
        return "Rating: " + rating + "/5 - " + text;
    }
}

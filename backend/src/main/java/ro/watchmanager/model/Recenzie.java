package ro.watchmanager.model;

public class Recenzie {
    private Rating rating; // de la 1 la 5
    private String text;

    public Recenzie(Rating rating, String text) {
        this.rating = rating;
        this.text = text;
    }

    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    @Override
    public String toString() {
        return "Rating: " + rating + "/5 - " + text;
    }
}

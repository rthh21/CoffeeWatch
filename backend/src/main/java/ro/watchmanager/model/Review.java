package ro.watchmanager.model;

public class Review {
    private Rating rating; 
    private String text;
    private String user;

    public Review(Rating rating, String text) {
        this.rating = rating;
        this.text = text;
        this.user = "Anonymous";
    }

    public Review(Rating rating, String text, String user) {
        this.rating = rating;
        this.text = text;
        this.user = user;
    }

    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    @Override
    public String toString() {
        return "Rating: " + rating + "/5 - " + text;
    }
}

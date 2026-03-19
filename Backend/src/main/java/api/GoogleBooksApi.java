package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.regex.Pattern;

import org.json.*;

public class GoogleBooksApi {

    private final HttpClient client;

    private static final Pattern NO_PLUS_MINUSES_BETWEEN_DIGITS = Pattern.compile("(?<=\\d)[+\\-](?=\\d)");

    public GoogleBooksApi() {
        client = HttpClient.newHttpClient();
    }

     /** Searches the requested String using the Google book api
     * @param q search term
     * @return the api's response in Json format, empty Json object if request fails
     */
    private JSONObject sendRequest(String q){
        //build request
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.googleapis.com/books/v1/volumes?q="+q))
                .build();
        HttpResponse<String> response;

        try {
            //send request
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return failedRequest(false);
        }
        return new JSONObject(response.body());
    }


    /** Gives Feedback for a failed request
     * @param paramNonExists indicates if request fails because of parameter. Used for better feedback
     *  @return an empty Json object
     */

    private JSONObject failedRequest(boolean paramNonExists) {
        String errMessage = paramNonExists ? "Request failed, parameter should exist" : "Api request failed";
        System.err.println (errMessage);
        //returns empty json object
        return new JSONObject("{}");
    }

    /** Searches the requested String using the Google book api
     * @param q search term
     * @return the api's response in Json format, empty Json object if request fails
     */
    public JSONObject search(String q) {
        if (q == null || q.isBlank()) {
            return failedRequest(true);
        }

        //convert spaces to pluses to then insert to url
        String plus = q.replace(' ', '+');
        plus = NO_PLUS_MINUSES_BETWEEN_DIGITS.matcher(plus).replaceAll("");

        return sendRequest(plus);

    }

    /** Searches the requested isbn using the Google book api
     * @param isbn isbn to be searched
     * @return the api's response in Json format, empty Json object if request fails
     */
    public JSONObject searchISBN (String isbn) {
        if (isbn == null || isbn.isBlank()) {
            return failedRequest(true);
        }

        //delete spaces and minuses to then insert to url
        String clean_isbn = isbn.replaceAll("[\\s-]", "");
        //add isbn parameter
        String q = "isbn:" + clean_isbn;
        return sendRequest(q);
    }

    /** Searches the requested author, title and/or isbn using the Google book api. Only one parameter needed, other can be null.
     * @param title title to be searched
     * @param author author to be searched
     * @param isbn isbn to be searched
     * @return the api's response in Json format, empty Json object if request fails
     */
    public JSONObject searchAdvanced (String title, String author, String isbn) {

        boolean exitsTitle = true;
        boolean exitsAuthor = true;
        boolean exitsISBN = true;


        //check for existing params
        if (title == null || title.isBlank()) {
            exitsTitle = false;
        }
        if (author == null || author.isBlank()) {
            exitsAuthor = false;
        }
        if (isbn == null || isbn.isBlank()) {
            exitsISBN = false;
        }
        if (!exitsTitle && !exitsAuthor && !exitsISBN) {
            return failedRequest(true);
        }

        StringBuilder builder = new StringBuilder();

        //convert spaces to pluses to then insert to url
        //add parameters to search string
        if(exitsTitle) {
            String title_plus = title.replace(' ', '+');
            builder.append("intitle:").append(title_plus);

        }
        if(exitsAuthor) {
            String author_plus = author.replace(' ', '+');
            builder.append("+inauthor:").append(author_plus);

        }
        if(exitsISBN) {
            //delete spaces and minuses to then insert to url
            String isbn_clean = isbn.replaceAll("[\\s-]", "");
            //add isbn parameter
            builder.append("+isbn:").append(isbn_clean);

        }

        String q = builder.toString();
        return sendRequest(q);

    }

}

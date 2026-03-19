package controller;

import api.GoogleBooksApi;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@CrossOrigin("*")
@RequestMapping("/search")
public class SearchController {

    private final GoogleBooksApi api;

    @Autowired
    public SearchController(GoogleBooksApi api) {
        this.api = api;
    }

    @GetMapping("/{q}")
    public JSONObject search(@PathVariable String q){
        return api.search(q);
    }

    @GetMapping("search-isbn/{isbn}")
    public JSONObject searchISBN (@PathVariable String isbn) {
        return api.searchISBN(isbn);
    }

}

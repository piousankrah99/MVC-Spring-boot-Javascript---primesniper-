package primesniper;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")

@Controller
@AllArgsConstructor
@RequestMapping(path = "/api/v1/primeSniper/")

public class SniperController {

   private SniperService sniperService;



    @GetMapping(path = "index")
    public String getSniper(){

        return "index";
    }

    @GetMapping(path = "getAllItems")
    @ResponseBody   // This annotation tells Spring to directly return the data as the response body
    public List<Snipers> allItems(Model model){
        model.addAttribute("Snipers", new Snipers());

        return sniperService.getAllItems() ;
    }


    @PostMapping(path = "addItem")
    public ResponseEntity<String> addItem(@RequestBody Snipers newItem){

        sniperService.addItem(newItem);
        return ResponseEntity.ok("Item added successfully");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteItem(@PathVariable Long id, Snipers deletedItem){
        try {
            sniperService.deleteItem(id);
            return ResponseEntity.ok().build();

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
}

    @GetMapping("/css/styles.css")
    public String getStyles() {
        return "forward:/static/css/styles.css";
    }

    @GetMapping("/js/index.js")
    public String getIndexJs() {
        return "forward:/static/js/index.js";
    }

}

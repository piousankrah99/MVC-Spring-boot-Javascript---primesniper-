package primesniper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SniperService {

    SniperRepository sniperRepository;


    public List<Snipers> getAllItems(){
        return sniperRepository.findAll();
    }

    public void addItem(Snipers newItem) {

        Optional<Snipers> existingSubscriberOptional =
                sniperRepository.findById(newItem.getId());

        if (existingSubscriberOptional.isPresent()) {
            throw new IllegalStateException("Item Already Exists");
        }

        sniperRepository.save(newItem);
    }

    public void deleteItem(Long id) {
        boolean itemExists = sniperRepository.existsById(id);
        if (!itemExists){
            throw new IllegalStateException(
                    "Sniper Item with id " + id + "does not exists");
        }
        sniperRepository.deleteById(id);
    }


}

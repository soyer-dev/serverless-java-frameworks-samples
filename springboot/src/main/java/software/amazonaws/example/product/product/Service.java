package software.amazonaws.example.product.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazonaws.example.product.product.dao.CustomerRepository;
import software.amazonaws.example.product.product.entity.Customer;

@Component
public class Service {

    /**
     * Autowiring another Spring Bean
     */
    @Autowired
    CustomerRepository anotherService;

    public void save(Customer text) {
        anotherService.save(text);
    }
}

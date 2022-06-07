package software.amazonaws.example.product.product.dao;

import org.springframework.data.repository.CrudRepository;
import software.amazonaws.example.product.product.entity.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
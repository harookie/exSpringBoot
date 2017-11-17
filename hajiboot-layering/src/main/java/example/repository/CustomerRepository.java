package example.repository;

import example.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <ul>
 * <li>업무 그룹명 : tango-inventory-dr-controller-daemon</li>
 * <li>서브 업무명 : example</li>
 * <li>설  명 : CustomerRepository</li>
 * <li>작성일 : 2017. 11. 17.</li>
 * <li>작성자 : 하종현(Mobigen co.)</li>
 * </ul>
 */
@Repository
public class CustomerRepository {
    private final ConcurrentMap<Integer, Customer> customerMap = new ConcurrentHashMap<>();

    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    public Customer findOne(Integer customerId) {
        return customerMap.get(customerId);
    }

    public Customer save(Customer customer) {
        return customerMap.put(customer.getId(), customer);
    }

    public void delete(Integer customerId) {
        customerMap.remove(customerId);
    }
}
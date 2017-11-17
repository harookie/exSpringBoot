package example;

import example.domain.Customer;
import example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner
{
    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... strings) throws Exception {
        customerService.save(new Customer(1, "Nobita", "Nobi"));
        customerService.save(new Customer(2, "Takeshi", "Goda"));
        customerService.save(new Customer(3, "Suneo", "Honekawa"));

        customerService.findAll().forEach(System.out::println);
    }

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
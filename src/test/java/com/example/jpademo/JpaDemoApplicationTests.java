package com.example.jpademo;

import com.example.jpademo.persistence.entities.AddressEntity;
import com.example.jpademo.persistence.entities.PersonEntity;
import com.example.jpademo.persistence.entities.TourEntity;
import com.example.jpademo.persistence.repositories.AddressRepository;
import com.example.jpademo.persistence.repositories.PersonRepository;
import com.example.jpademo.persistence.repositories.TourRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JpaDemoApplicationTests {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private TourRepository tourRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test_AddressEntity() {
        AddressEntity a = AddressEntity.builder()
                .postcode(1010)
                .city("Wien")
                .street("Graben")
                .build();
        System.out.println(addressRepository.count() + " rows.");
        addressRepository.save(a);
        System.out.println(addressRepository.count() + " rows.");
    }

    @Test
    void test_PersonEntity() {
        PersonEntity p = PersonEntity.builder()
                .name("Markus")
                .email("holzerm@technikum-wien.at")
                .age(32)
                .build();

        personRepository.save(p);

        personRepository.save(PersonEntity.builder()
                .name("Anna")
                .age(33)
                .email("anna@technikum-wien.at")
                .build());

        System.out.printf("%d rows in table person\n", personRepository.count());
        personRepository.findAll().forEach(System.out::println);
    }

    @Test
    void test_PersonAddress() {
        PersonEntity anna = personRepository.save(PersonEntity.builder()
                .name("Anna")
                .age(33)
                .email("anna@technikum-wien.at")
                .build());
        AddressEntity graben = addressRepository.save(AddressEntity.builder()
                .postcode(1010)
                .city("Wien")
                .street("Graben")
                .person(anna)
                .build());
        AddressEntity kellergasse = addressRepository.save(AddressEntity.builder()
                .postcode(1210)
                .city("Wien")
                .street("Kellergasse")
                .person(anna)
                .build());
        /*
        Collection<AddressEntity> addresses = new ArrayList<>();
        addresses.add(graben);
        addresses.add(kellergasse);
        anna.setAddresses(addresses);
         */

        personRepository.findAll().forEach(System.out::println);

        addressRepository.findAll().forEach(a -> {
            System.out.println(a);
            //System.out.println(a.getPerson().getName());
        });

        System.out.println("find addresses by person:");
        addressRepository.findByPerson(anna).forEach(System.out::println);
    }

    @Test
    void test_TourEntity() {
        TourEntity tour1 = TourEntity.builder()
                .name("Tour 1")
                .description("Description 1")
                .from_location("Location 1")
                .to_location("Location 2")
                .transport_type("Bus")
                .distance(100)
                .time(2)
                .route_info("Route 1")
                .build();

        tourRepository.save(tour1);

        tourRepository.save(TourEntity.builder()
                .name("Tour 2")
                .description("Description 2")
                .from_location("Location 3")
                .to_location("Location 4")
                .transport_type("Train")
                .distance(200)
                .time(3)
                .route_info("Route 2")
                .build());

        System.out.printf("%d rows in table tour\n", tourRepository.count());
        tourRepository.findAll().forEach(System.out::println);
    }

}

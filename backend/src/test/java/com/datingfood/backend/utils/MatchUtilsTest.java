package com.datingfood.backend.utils;

import com.datingfood.backend.entities.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchUtilsTest {

    @Test
    void test_findCommonPersons(){
        // GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));


        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList2.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList2.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));

        // WHEN
        List<Person> result =  MatchUtils.findCommonPersons(personList, personList2);

        // THEN
        assertEquals(3, result.size());
    }

    @Test
    void test_findCommonPersons_no_matches(){
        // GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));


        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person("michael", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList2.add(new Person("emily", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList2.add(new Person("ad", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));

        // WHEN
        List<Person> result =  MatchUtils.findCommonPersons(personList, personList2);

        // THEN
        assertEquals(0, result.size());
    }

    @Test
    void test_findDifferentPersons(){
        // GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));


        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList2.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList2.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));

        // WHEN
        List<Person> result =  MatchUtils.findDifferentPersons(personList, personList2);

        // THEN
        assertEquals(2, result.size());
    }


    @Test
    void test_findDifferentPersons_no_difference(){
        // GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));


        List<Person> personList2 = new ArrayList<>();
        personList2.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList2.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList2.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList2.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList2.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));


        // WHEN
        List<Person> result =  MatchUtils.findDifferentPersons(personList, personList2);

        // THEN
        assertEquals(0, result.size());
    }

    @Test
    void test_findDifferentPersons_empty_list(){
        // GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("john_doe", "John", "Doe", "+1234567890", LocalDate.of(1990, 1, 1), "password1", null,null));
        personList.add(new Person("alice_smith", "Alice", "Smith", "+9876543210", LocalDate.of(1985, 5, 15), "password2", null,null));
        personList.add(new Person("michael_j", "Michael", "Johnson", "+1122334455", LocalDate.of(1982, 9, 22), "password3", null,null));
        personList.add(new Person("emily_w", "Emily", "Williams", "+9876543210", LocalDate.of(1995, 7, 8), "password4", null,null));
        personList.add(new Person("admin", "Admin", "Admin", "+12333333", LocalDate.of(2000, 1, 1), "admin_password", null,null));


        List<Person> personList2 = new ArrayList<>();

        // WHEN
        List<Person> result =  MatchUtils.findDifferentPersons(personList, personList2);

        // THEN
        assertEquals(5, result.size());
    }

}

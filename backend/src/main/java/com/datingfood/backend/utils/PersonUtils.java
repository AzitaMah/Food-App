package com.datingfood.backend.utils;

import com.datingfood.backend.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonUtils {

    // TODO change naming
    public static List<Person> findCommonPersons(List<Person> list1, List<Person> list2) {

        list1.retainAll(list2);

        return new ArrayList<>(list1);
    }
}

package com.datingfood.backend.utils;

import com.datingfood.backend.entities.Person;

import java.util.ArrayList;
import java.util.List;

public class MatchUtils {

    private MatchUtils(){

    }

    /**
     * compares two lists and returns a new one with persons that are in both lists
     * @param personList list from client where he chose partners
     * @param partnerList list where client was chosen as partner
     * @return List with common person
     */
    public static List<Person> findCommonPersons(final List<Person> personList, final List<Person> partnerList) {

        personList.retainAll(partnerList);

        return new ArrayList<>(personList);
    }
}

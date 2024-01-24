package com.datingfood.backend.utils;

import com.datingfood.backend.dto.ContactDTO;
import com.datingfood.backend.dto.PersonInfoDTO;
import com.datingfood.backend.entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * create a List with elements that are one in personList
     * @param personList
     * @param partnerList
     * @return
     */
    public static  List<Person> findDifferentPersons(final List<Person> personList, final List<Person> partnerList){
        personList.removeAll(partnerList);

        return new ArrayList<>(personList);
    }

    /**
     * Converts a list of Person objects to a list of PersonInfoDTO objects with less information
     * @param persons The list of Person objects to be converted
     * @return List of PersonInfoDTO objects
     */
    public static List<PersonInfoDTO> createPersonInfoDTOList(List<Person> persons) {
        return persons.stream()
                .map(person -> new PersonInfoDTO(
                        person.getUsername(),
                        person.getProfileImage(),
                        person.getBirthDate()))
                .collect(Collectors.toList());
    }

    /**
     * Converts a list of Person objects to a list of ContactDTO objects
     * @param persons The list of Person objects to be converted
     * @return List of ContactDTO objects
     */
    public static List<ContactDTO> createPersonContactDTOList(List<Person> persons){
        return persons.stream()
                .map(person -> new ContactDTO(
                        person.getUsername(),
                        person.getFirstName(),
                        person.getProfileImage(),
                        person.getBirthDate(),
                        person.getContact()))
                .collect(Collectors.toList());
    }
}

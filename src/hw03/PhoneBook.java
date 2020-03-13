package hw03;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> pb;

    PhoneBook() {
        this.pb = new HashMap<>();
    }

    public String add(String phone, String surname) {
        //check if a subscriber exists
        Set<String> pt
                = pb.getOrDefault(surname,new HashSet<>());
        pt.add(phone);
        pb.put(surname,pt);
        return String.format(
                "Subscriber '%s' with the phone number '%s' successfully added\n",
                surname, phone);
    }

    //return list phones by family name
    public Set<String> get(String surname) {
        return pb.get(surname);
    }

}

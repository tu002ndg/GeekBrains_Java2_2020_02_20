package hw03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, String> pb;

    PhoneBook() {
        this.pb = new HashMap<>();
    }

    public String add(String phone, String surname) {
         pb.put(phone, surname);
        return String.format(
                "Subscriber '%s' with the phone number '%s' successfully added\n",
                surname, phone);
    }

    //return list phones by family name
    public List<String> get(String surname) {
        List<String> al = new ArrayList<>();
        pb.forEach((k,v) -> {
            if(v.equals(surname)) {
                al.add(k);
            }
        });
        return  al;
    }

}

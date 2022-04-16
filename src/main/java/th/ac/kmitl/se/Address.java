package th.ac.kmitl.se;

public class Address {
    String name;
    String line1;
    String line2;
    String district;
    String city;
    String postcode;

    public Address(String name, String line1, String line2, String district, String city, String postcode) {
        this.name = name;
        this.line1 = line1;
        this.line2 = line2;
        this.district = district;
        this.city = city;
        this.postcode = postcode;
    }
}

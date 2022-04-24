package app.domain.model;

public class Adress {

    private String streetName;
    private int number;
    private String city;
    private String county;
    private String postalCode;

    private static final String STREETNAME_OMISSION="N/A";
    private static final int NUMBER_OMISSION=999;
    private static final String CITY_OMISSION="N/A";
    private static final String COUNTY_OMISSION="N/A";
    private static final String POSTAL_CODES_OMISSION="N/A";

    public Adress(){}

    public void addAdress(String adressFull){
        String[] adress=adressFull.split(",");
        this.streetName=adress[0];
        this.number= Integer.parseInt(adress[1]);
        this.county=adress[2];
        this.postalCode=adress[3];
        this.city=adress[5];
    }

}

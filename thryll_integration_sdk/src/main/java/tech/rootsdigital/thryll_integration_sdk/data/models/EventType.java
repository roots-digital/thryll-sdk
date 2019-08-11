package tech.rootsdigital.thryll_integration_sdk.data.models;

/**
 * Created by Shinigami on 12/13/2017.
 */

public final class EventType {

    public static final String Party = "Party";
    public static final String Concert = "Concert";
    public static final String ScienceTech = "Science & Tech";
    public static final String Fashion = "Fashion";
    public static final String Donation = "Fundraisers & Charity";
    public static final String Health = "Health & Fitness";
    public static final String NetPreneurship = "Networking & Entrepreneurship";
    public static final String Gametition = "Competition & Gaming";
    public static final String Exhibition = "Product Exhibition";
    public static final String Social = "Community & Social";
    public static final String Stage = "Creative Arts & Stage Acts";
    public static final String Seminar = "Seminars & Learning";
    public static final String Movie = "Movie";
    public static final String Other = "Other";
    public static final String Conference = "Conference";
    public static final String Religious = "Religious";

    // Excluded from public list
    public static final String National = "National Events";

    public static final int length = 9;

    public static String getEventType(int i){
        switch (i) {
            case 0:
                return Party;
            case 1:
                return Movie;
            case 2:
                return Concert;
            case 3:
                return NetPreneurship;
            case 4:
                return Exhibition;
            case 5:
                return Conference;
            case 6:
                return Gametition;
            case 7:
                return Religious;
            default:
                return Other;
        }
    }
}

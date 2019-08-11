package tech.rootsdigital.thryll_integration_sdk.data.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

//import rootsdigital.tech.thryll_organiser.data.models.tickets.TicketOption;


public class Event implements Serializable {

    // Identification Properties
    @SerializedName("_id") @Expose
    private String eventId;
    @SerializedName("eventCode") @Expose
    private String eventCode;
    @SerializedName("eventName") @Expose
    private String eventName;
    @SerializedName("creatorId") @Expose
    private String creatorId;
    @SerializedName("creatorName") @Expose
    private String creatorName;

    // Details Properties
    @SerializedName("eventTypeNum") @Expose
    private int eventTypeNum;
    @SerializedName("description") @Expose
    private String description;
    @SerializedName("endDate") @Expose
    private long endDate;
    @SerializedName("liveDate") @Expose
    private long liveDate; //TODO: Log todo somewhere
    @SerializedName("images") @Expose
    private List<String> images;
    @SerializedName("icon") @Expose
    private String icon;
    @SerializedName("previewImage") @Expose
    private String previewImage;
    @SerializedName("venueId") @Expose
    private String venueId;
    @SerializedName("venue") @Expose
    private String venue;
    @SerializedName("city") @Expose
    private String city;
    @SerializedName("latitude") @Expose
    private double latitude;
    @SerializedName("longitude") @Expose
    private double longitude;
//    @SerializedName("tickets") @Expose
//    private List<TicketOption> tickets;
    @SerializedName("eventdates") @Expose
    private List<EventDate> dates;

    // Like Properties
    @SerializedName("thryllScore") @Expose
    private int thryllScore;
    @SerializedName("likes") @Expose
    private int likes;
    @SerializedName("chats") @Expose
    private int chats;
    @SerializedName("hypes") @Expose
    private int hypes;
    @SerializedName("subscribers") @Expose
    private int subscribers;
    @SerializedName("eventStatus") @Expose
    private int eventStatus;
    @SerializedName("accessLevel") @Expose
    private int accessLevel;

    @SerializedName("commissionEnabled") @Expose
    private int commissionEnabled;
    @SerializedName("commissionPercentage") @Expose
    private int commissionPercentage;

    @SerializedName("timestamp") @Expose
    private long timeStamp;
    @SerializedName("api_key") @Expose
    private String apiKey;


    public Event(){}

    //region Getters | Setters

    // Identification Properties
    public String getEventCode() {
        return eventCode;
    }
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }



    // Details Properties

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getEndDate() {return endDate;}
    public void setEndDate(long time) {
        endDate = time; }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public double getLongitude() {return longitude;}
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
//


    // Like Properties
    public int getHypes() { return hypes; }
    public void setHypes(int hypes) { this.hypes = hypes; }

    public int getChats() { return chats;  }
    public void setChats(int chats) {  this.chats = chats; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getEventStatus() {
        return eventStatus;
    }
    public void setEventStatus(int eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCommissionEnabled() {
        return commissionEnabled;
    }

    public void setCommissionEnabled(int commissionEnabled) {
        this.commissionEnabled = commissionEnabled;
    }

    public int getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(int commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return eventName;
    }

//    public List<TicketOption> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<TicketOption> tickets) {
//        this.tickets = tickets;
//    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getEventTypeNum() {
        return eventTypeNum;
    }

    public void setEventTypeNum(int eventTypeNum) {
        this.eventTypeNum = eventTypeNum;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public long getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(long liveDate) {
        this.liveDate = liveDate;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public int getThryllScore() {
        return thryllScore;
    }

    public void setThryllScore(int thryllScore) {
        this.thryllScore = thryllScore;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<EventDate> getDates() {
        return dates;
    }

    public void setDates(List<EventDate> dates) {
        this.dates = dates;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }


}

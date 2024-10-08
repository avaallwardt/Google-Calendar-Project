package com.example.gcalsamaven;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* class to demonstrate use of Calendar events list API */
public class GCalHandler {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public void buildCalendarService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
    }

    public void findGCalSAEvents() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getDescription() == null){
                    System.out.println("Event found that was not generated by GCalSA.");
                }
                else if(items.get(i).getDescription().contains("GCalSA")){
                    service.events().delete("primary", items.get(i).getId()).execute();
                    System.out.println("Event removed.");
                }
                else{
                    System.out.println("Event found that is not generated by GCalSA.");
                }
            }
        }
    }

    private Calendar service;

    ArrayList<Event> aPeriod = new ArrayList<>();
    ArrayList<Event> bPeriod = new ArrayList<>();
    ArrayList<Event> cPeriod = new ArrayList<>();
    ArrayList<Event> dPeriod = new ArrayList<>();
    ArrayList<Event> ePeriod = new ArrayList<>();
    ArrayList<Event> fPeriod = new ArrayList<>();
    ArrayList<Event> gPeriod = new ArrayList<>();
    ArrayList<Event> hPeriod = new ArrayList<>();
    ArrayList<Event> advisory = new ArrayList<>();

    public void prepareService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();
    }

    public void findPeriodAClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("A")){
                    items.get(i).setSummary("Period A - " + name);
                    aPeriod.add(items.get(i));
                }
            }

        }
        System.out.println("in the method" + aPeriod.size());

    }


    public void findPeriodBClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("B")){
                    items.get(i).setSummary("Period B - " + name);
                    bPeriod.add(items.get(i));
                }
            }

        }

    }

    public void findPeriodCClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("C")){
                    items.get(i).setSummary("Period C - " + name);
                    cPeriod.add(items.get(i));
                }
            }

        }

    }

    public void findPeriodDClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("D")){
                    items.get(i).setSummary("Period D - " + name);
                    dPeriod.add(items.get(i));
                }
            }

        }

    }

    public void findPeriodEClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("E")){
                    items.get(i).setSummary("Period E - " + name);
                    ePeriod.add(items.get(i));
                }
            }

        }

    }

    public void findPeriodFClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("F")){
                    items.get(i).setSummary("Period F - " + name);
                    fPeriod.add(items.get(i));
                }
            }

        }

    }

    public void findPeriodGClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("G")){
                    items.get(i).setSummary("Period G - " + name);
                    gPeriod.add(items.get(i));
                }
            }

        }

    }

    public void findPeriodHClasses(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("H")){
                    items.get(i).setSummary("Period H - " + name);
                    hPeriod.add(items.get(i));
                }
            }

        }

    }

    public void findAdvisories(String name) throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().contains("Advisory")){
                    items.get(i).setSummary("Advisory - " + name);
                    advisory.add(items.get(i));
                }
            }

        }

    }


    public void populatePeriodXClasses() throws GeneralSecurityException, IOException {
        for(int i = 0; i < aPeriod.size(); i++){
            aPeriod.get(i).setDescription("Event generated by GCalSA");
            System.out.println("populating period a classes");
            Event newEvent = new Event();
            newEvent.setStart(aPeriod.get(i).getStart());
            newEvent.setEnd(aPeriod.get(i).getEnd());
            newEvent.setSummary(aPeriod.get(i).getSummary());
            newEvent.setDescription(aPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < bPeriod.size(); i++){
            bPeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(bPeriod.get(i).getStart());
            newEvent.setEnd(bPeriod.get(i).getEnd());
            newEvent.setSummary(bPeriod.get(i).getSummary());
            newEvent.setDescription(bPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < cPeriod.size(); i++){
            cPeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(cPeriod.get(i).getStart());
            newEvent.setEnd(cPeriod.get(i).getEnd());
            newEvent.setSummary(cPeriod.get(i).getSummary());
            newEvent.setDescription(cPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < dPeriod.size(); i++){
            dPeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(dPeriod.get(i).getStart());
            newEvent.setEnd(dPeriod.get(i).getEnd());
            newEvent.setSummary(dPeriod.get(i).getSummary());
            newEvent.setDescription(dPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < ePeriod.size(); i++){
            ePeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(ePeriod.get(i).getStart());
            newEvent.setEnd(ePeriod.get(i).getEnd());
            newEvent.setSummary(ePeriod.get(i).getSummary());
            newEvent.setDescription(ePeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < fPeriod.size(); i++){
            fPeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(fPeriod.get(i).getStart());
            newEvent.setEnd(fPeriod.get(i).getEnd());
            newEvent.setSummary(fPeriod.get(i).getSummary());
            newEvent.setDescription(fPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < gPeriod.size(); i++){
            gPeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(gPeriod.get(i).getStart());
            newEvent.setEnd(gPeriod.get(i).getEnd());
            newEvent.setSummary(gPeriod.get(i).getSummary());
            newEvent.setDescription(gPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < hPeriod.size(); i++){
            hPeriod.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(hPeriod.get(i).getStart());
            newEvent.setEnd(hPeriod.get(i).getEnd());
            newEvent.setSummary(hPeriod.get(i).getSummary());
            newEvent.setDescription(hPeriod.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
        for(int i = 0; i < advisory.size(); i++){
            advisory.get(i).setDescription("Event generated by GCalSA");
            Event newEvent = new Event();
            newEvent.setStart(advisory.get(i).getStart());
            newEvent.setEnd(advisory.get(i).getEnd());
            newEvent.setSummary(advisory.get(i).getSummary());
            newEvent.setDescription(advisory.get(i).getDescription());
            service.events().insert("primary", newEvent).execute();
        }
    }








    // ignore findSAEvents!!!
    public void findSAEvents() throws GeneralSecurityException, IOException {
        prepareService();
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("c_ad798c6deee79141b2ca88ab674de442ecce345f48d4f9995bc9365182c26ebf@group.calendar.google.com")
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        List<Event> items = events.getItems();


        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getSummary().contains("Advisory")){
                    advisory.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("A")){
                    aPeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("B")){
                    bPeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("C")){
                    cPeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("D")){
                    dPeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("E")){
                    ePeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("F")){
                    fPeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("G")){
                    gPeriod.add(items.get(i));
                }
                else if(items.get(i).getSummary().substring(items.get(i).getSummary().length() - 2, items.get(i).getSummary().length() - 1).equals("H")){
                    hPeriod.add(items.get(i));
                }
            }
            System.out.println(items);
        }
    }



}
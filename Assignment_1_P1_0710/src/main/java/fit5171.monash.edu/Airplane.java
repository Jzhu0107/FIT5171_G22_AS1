package fit5171.monash.edu;

import java.util.Arrays;

public class Airplane
{
    private int airplaneID;
    private String airplaneModel;
    private int businessSitsNumber;
    private int economySitsNumber;
    private int crewSitsNumber;

    public Airplane(int airplaneID, String airplaneModel, int businessSitsNumber, int economySitsNumber, int crewSitsNumber)
    {
        this.airplaneID=airplaneID;
        this.airplaneModel = airplaneModel;
        this.businessSitsNumber = businessSitsNumber;
        this.economySitsNumber = economySitsNumber;
        this.crewSitsNumber = crewSitsNumber;
    }

    public int getAirplaneID() {
        return airplaneID;
    }

    public String setAirplaneID(int airplaneID) {
        if(airplaneID>0 && airplaneID<10000) {
            this.airplaneID = airplaneID;
            return "AirplaneID successfully set";
        }
        else return "Type error";
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public String[] airplane_models = {"Boeing 737", "Airbus A320", "Boeing 777", "Airbus A380", "Boeing 787", "Airbus A350", "Boeing 747", "Airbus A330"};

    public String setAirplaneModel(String airplaneModel) {
        if (Arrays.asList(airplane_models).contains(airplaneModel)) {
            this.airplaneModel = airplaneModel;
            return "AirplaneModels successfully set";
        }
        else return "Type error";
    }

    public int getBusinessSitsNumber() {
        return businessSitsNumber;
    }

    public String setBusinessSitsNumber(int businessSitsNumber) {
        if(businessSitsNumber>0 && businessSitsNumber<301){
            this.businessSitsNumber = businessSitsNumber;
            return "AirplaneModels successfully set";
        }
        else return "Type error";
    }

    public int getEconomySitsNumber() {
        return economySitsNumber;
    }

    public String setEconomySitsNumber(int economySitsNumber) {
        if(economySitsNumber>0 && economySitsNumber<301){
            this.economySitsNumber = economySitsNumber;
            return "economySitsNumber successfully set";
        }
        else return "Type error";
    }

    public int getCrewSitsNumber() {
        return crewSitsNumber;
    }

    public String setCrewSitsNumber(int crewSitsNumber) {
        if(crewSitsNumber>0 && crewSitsNumber<301){
            this.crewSitsNumber = crewSitsNumber;
            return "crewSitsNumber successfully set";
        }
        else return "Type error";
    }

    public String toString()
    {
        return "Airplane{" +
                "model=" + getAirplaneModel() + '\'' +
                ", business sits=" + getBusinessSitsNumber() + '\'' +
                ", economy sits=" + getEconomySitsNumber() + '\'' +
                ", crew sits=" + getCrewSitsNumber() + '\'' +
                '}';
    }

    public static Airplane getAirPlaneInfo(int airplane_id) {
        // TODO Auto-generated method stub
        return null;
    }
}
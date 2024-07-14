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

    public static Airplane getAirplaneInfo(int airplaneID) {
        // 模拟数据源，返回一个示例的Airplane对象
        if (airplaneID == 1) {
            return new Airplane(airplaneID, "Boeing 737", 20, 80, 10);
        } else if (airplaneID == 2) {
            return new Airplane(airplaneID, "Airbus A320", 25, 100, 12);
        } else {
            return null;
        }
    }
}
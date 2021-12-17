package com.demba.mediquick.models;

public class Disease {
    String disease_name, symptom01, symptom02, symptom03, symptom04, symptom05, symptom06,
            symptom07, symptom08, symptom09, symptom10,disease_id;

    public Disease(String disease_name, String symptom01, String symptom02, String symptom03,
                   String symptom04, String symptom05, String symptom06, String symptom07,
                   String symptom08, String symptom09, String symptom10, String disease_id) {

        this.disease_name = disease_name;
        this.symptom01 = symptom01;
        this.symptom02 = symptom02;
        this.symptom03 = symptom03;
        this.symptom04 = symptom04;
        this.symptom05 = symptom05;
        this.symptom06 = symptom06;
        this.symptom07 = symptom07;
        this.symptom08 = symptom08;
        this.symptom09 = symptom09;
        this.symptom10 = symptom10;
        this.disease_id = disease_id;
    }

    public Disease() {
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getSymptom01() {
        return symptom01;
    }

    public void setSymptom01(String symptom01) {
        this.symptom01 = symptom01;
    }

    public String getSymptom02() {
        return symptom02;
    }

    public void setSymptom02(String symptom02) {
        this.symptom02 = symptom02;
    }

    public String getSymptom03() {
        return symptom03;
    }

    public void setSymptom03(String symptom03) {
        this.symptom03 = symptom03;
    }

    public String getSymptom04() {
        return symptom04;
    }

    public void setSymptom04(String symptom04) {
        this.symptom04 = symptom04;
    }

    public String getSymptom05() {
        return symptom05;
    }

    public void setSymptom05(String symptom05) {
        this.symptom05 = symptom05;
    }

    public String getSymptom06() {
        return symptom06;
    }

    public void setSymptom06(String symptom06) {
        this.symptom06 = symptom06;
    }

    public String getSymptom07() {
        return symptom07;
    }

    public void setSymptom07(String symptom07) {
        this.symptom07 = symptom07;
    }

    public String getSymptom08() {
        return symptom08;
    }

    public void setSymptom08(String symptom08) {
        this.symptom08 = symptom08;
    }

    public String getSymptom09() {
        return symptom09;
    }

    public void setSymptom09(String symptom09) {
        this.symptom09 = symptom09;
    }

    public String getSymptom10() {
        return symptom10;
    }

    public void setSymptom10(String symptom10) {
        this.symptom10 = symptom10;
    }

    public String getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(String disease_id) {
        this.disease_id = disease_id;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "disease_name='" + disease_name + '\'' +
                ", symptom01='" + symptom01 + '\'' +
                ", symptom02='" + symptom02 + '\'' +
                ", symptom03='" + symptom03 + '\'' +
                ", symptom04='" + symptom04 + '\'' +
                ", symptom05='" + symptom05 + '\'' +
                ", symptom06='" + symptom06 + '\'' +
                ", symptom07='" + symptom07 + '\'' +
                ", symptom08='" + symptom08 + '\'' +
                ", symptom09='" + symptom09 + '\'' +
                ", symptom10='" + symptom10 + '\'' +
                ", disease_id='" + disease_id + '\'' +
                '}';
    }
}

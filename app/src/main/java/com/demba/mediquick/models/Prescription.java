package com.demba.mediquick.models;

public class Prescription {
    String disease_name,prescription1,prescription2,prescription3,disease_id;

    public Prescription(String disease_name, String prescription1, String prescription2,
                        String prescription3, String disease_id) {
        this.disease_name = disease_name;
        this.prescription1 = prescription1;
        this.prescription2 = prescription2;
        this.prescription3 = prescription3;
        this.disease_id = disease_id;
    }
    public Prescription() {
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getPrescription1() {
        return prescription1;
    }

    public void setPrescription1(String prescription1) {
        this.prescription1 = prescription1;
    }

    public String getPrescription2() {
        return prescription2;
    }

    public void setPrescription2(String prescription2) {
        this.prescription2 = prescription2;
    }

    public String getPrescription3() {
        return prescription3;
    }

    public void setPrescription3(String prescription3) {
        this.prescription3 = prescription3;
    }

    public String getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(String disease_id) {
        this.disease_id = disease_id;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "disease_name='" + disease_name + '\'' +
                ", prescription1='" + prescription1 + '\'' +
                ", prescription2='" + prescription2 + '\'' +
                ", prescription3='" + prescription3 + '\'' +
                ", disease_id='" + disease_id + '\'' +
                '}';
    }
}

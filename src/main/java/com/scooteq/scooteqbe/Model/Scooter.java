package com.scooteq.scooteqbe.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="scooters")
public class Scooter {

    @Id
    @Column(nullable = false,unique = true)
    private int id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    // getter und setter
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getLat(){
        return lat;
    }
    public void setLat(String lat){
        this.lat = lat;
    }
    public String getLng(){
        return lng;
    }
    public void setLng(String lng){
        this.lng=lng;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
}

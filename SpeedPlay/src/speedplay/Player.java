/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;

import java.io.Serializable;

/**
 *
 * @author Tichnut
 */
public class Player implements Serializable{
    private String name;
    private int points;

    public Player() {
    }

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoint(int point){
        points+=point;
    }
        public void subPoint(int point){
        points-=point;
    }
    @Override
    public String toString() {
        return name+": "+points+" נקודות";
        
    }
}

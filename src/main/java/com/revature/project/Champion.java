package com.revature.project;

public class Champion {
    private int champId;
    private String name;
    private String classType;
    private int health;

    public Champion (int champId, String name, String classType, int health) {
        this.champId = champId;
        this.name = name;
        this.classType = classType;
        this.health = health;
    }

    public Champion (){
    }

    public int getChampId(){ return champId;}

    public void setChampId(){ this.champId = champId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Champion [champID=" + champId + ", name=" + name + ", class=" + classType + ", health=" + health + "]";
    }
}

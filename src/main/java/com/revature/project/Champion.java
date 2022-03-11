package com.revature.project;

public class Champion {
    private String name;
    private String classType;
    private int health;

    public Champion (String name, String classType, int health) {
        this.name = name;
        this.classType = classType;
        this.health = health;
    }

    public Champion (){

    }

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
        return "Champion [name=" + name + ", class=" + classType + ", health=" + health + "]";
    }
}

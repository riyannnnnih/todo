package com.example.todo;

public class Model {

    public String What, Time;

    public int Id;

    public int getId() {
        return Id;
    }

    public String getTime() {
        return Time;
    }

    public String getWhat() {
        return What;
    }

    public Model(int Id, String What, String Time){
        this.Id = Id;
        this.What = What;
        this.Time = Time;
    }
}

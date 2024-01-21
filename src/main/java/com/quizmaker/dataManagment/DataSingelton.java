package com.quizmaker.dataManagment;

//source: https://www.youtube.com/watch?v=MsgiJdf5njc
public class DataSingelton {

    private static final DataSingelton instance = new DataSingelton();

    private String data;

    private DataSingelton(){}

    public static DataSingelton getInstance(){
        return instance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data){
        this.data = data;
    }
}

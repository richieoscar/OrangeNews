package com.richieoscar.orangenews.model;

import java.util.ArrayList;

public class SourceJsonResult {
    String status;
    private ArrayList<Source> sources;

    public String getStatus() {
        return status;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }
}

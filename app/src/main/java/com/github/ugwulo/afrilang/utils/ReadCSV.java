package com.github.ugwulo.afrilang.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {

    InputStream mInputStream;

    public ReadCSV(InputStream inputStream){
        this.mInputStream = inputStream;
    }

    public List<String[]> read(){
        List<String[]> results = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try{
            String CsvLine;
            while((CsvLine = reader.readLine()) != null){
                String[] row = CsvLine.split(",");
                results.add(row);
            }
        } catch (IOException e){
            throw new RuntimeException("Error reading CSV file " + e);
        } finally {
            try{
                mInputStream.close();
            }catch (IOException e){
                throw new RuntimeException("Error while closing input stream " + e);
            }
        }
        return results;
    }
}

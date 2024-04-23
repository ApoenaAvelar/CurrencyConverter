package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class FileRecorder {

    public void saveJson(CurrencyExchange currencyExchange) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter operations = new FileWriter( currencyExchange.base_code() +
                currencyExchange.target_code() + ".json");
        operations.write(gson.toJson(currencyExchange));
        operations.close();

    }

}

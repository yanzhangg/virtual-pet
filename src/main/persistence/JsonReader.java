package persistence;

import model.PetAnimal;
import model.PetList;
import model.Cat;
import model.Dog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads petList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PetList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePetList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private PetList parsePetList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PetList pl = new PetList(name);
        addPetAnimals(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses pet animals from JSON object and adds them to pet list
    private void addPetAnimals(PetList pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("petList");
        for (Object json : jsonArray) {
            JSONObject nextPetAnimal = (JSONObject) json;
            addPetAnimal(pl, nextPetAnimal);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses PetAnimal from JSON object and adds it to petList
    private void addPetAnimal(PetList pl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        int happiness = jsonObject.getInt("happiness");
        int fullness = jsonObject.getInt("fullness");
        int energy = jsonObject.getInt("energy");

        if (type.equals("dog")) {
            PetAnimal animal = new Dog(name, happiness, fullness, energy);
            pl.addPetToList(animal);
        } else {
            PetAnimal animal = new Cat(name, happiness, fullness, energy);
            pl.addPetToList(animal);
        }
    }
}


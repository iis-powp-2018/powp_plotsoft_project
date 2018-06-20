package powp;

import com.google.gson.*;
import edu.iis.powp.command.IPlotterCommand;

import java.lang.reflect.Type;
import java.util.Map;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    private int polymorphic = 1;

    public T deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        if(polymorphic == 1) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
            String className = prim.getAsString();
            Class klass = getObjectClass(className);
            polymorphic = 0;
            JsonElement element = jsonObject.get(DATA);
            return jsonDeserializationContext.deserialize(element, klass);
        }
        else
        {
            Gson gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setPrettyPrinting()
                    .registerTypeAdapter(IPlotterCommand.class, this)
                    .create();
            polymorphic = 1;
            return gson.fromJson(jsonElement, type);
        }
    }

    public JsonElement serialize(T jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        if(polymorphic >= 1)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());

            polymorphic--;
            jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
            return jsonObject;
        }
        else
        {
            Gson gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()
                    .setPrettyPrinting()
                    .registerTypeAdapter(IPlotterCommand.class, this)
                    .create();
            polymorphic = 100; //TODO: Zamiast 100 dać na ilość potrzebnych zagnieżdżonych serializacji
            JsonElement je = gson.toJsonTree(jsonElement);
            return je;
        }
    }
    /****** Helper method to get the className of the object to be deserialized *****/
    private Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }
}
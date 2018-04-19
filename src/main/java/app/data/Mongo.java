package app.data;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Mongo
{
    private MongoClient client;
    private MongoDatabase db;

    private static Mongo instance;
    public static Mongo getInstance()
    {
        if (instance == null)
            instance = new Mongo();
        return instance;
    }

    private Mongo()
    {
        client = new MongoClient(); // Overide host and port
        db = client.getDatabase("somedbint");
    }

    public MongoDatabase getSomeDb()
    {
        return db;
    }
}

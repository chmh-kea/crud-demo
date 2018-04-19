package app.data;

import app.SomeObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

public class MongoSomeObjectCrud implements SomeObjectCrud
{
    private MongoDatabase someDb;
    private MongoCollection<Document> someObjects;

    private static MongoSomeObjectCrud instance;
    public static MongoSomeObjectCrud getInstance()
    {
        if(instance == null)
            instance = new MongoSomeObjectCrud();
        return instance;
    }

    private MongoSomeObjectCrud()
    {
        someDb = Mongo.getInstance().getSomeDb();
        someObjects = someDb.getCollection("someObjects");

    }

    @Override
    public boolean createSomeObject(SomeObject someObject)
    {
        someObjects.insertOne(someObject);
    }

    @Override
    public List<SomeObject> getSomeObjects()
    {
        return null;
    }

    @Override
    public SomeObject getSomeObject(int id)
    {
        return null;
    }

    @Override
    public boolean updateSomeObject(SomeObject someObject)
    {
        return false;
    }

    @Override
    public boolean deleteSomeObject(int id)
    {
        return false;
    }
}

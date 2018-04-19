package app.data;

import app.SomeObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoSomeObjectCrud implements SomeObjectCrud
{
    private MongoDatabase someDb;
    private MongoCollection<Document> someObjectsCollection;

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
        someObjectsCollection = someDb.getCollection("someObjectsCollection");

    }

    private Document toDocument(SomeObject someObject)
    {
        Document document = new Document();
        if (someObject.getId() > 0)
            document.append("_id", someObject.getId());
        document.append("someString", someObject.getSomeString());
        document.append("someInt",someObject.getSomeInt());
        return document;
    }

    private SomeObject fromDocument(Document document)
    {
        SomeObject someObject = new SomeObject();
        someObject.setId(document.getInteger("_id"));
        someObject.setSomeString(document.getString("someString"));
        someObject.setSomeInt(document.getInteger("someInt"));
        return someObject;
    }

    @Override
    public boolean createSomeObject(SomeObject someObject)
    {
        someObjectsCollection.insertOne(toDocument(someObject));
        return true;
    }

    @Override
    public List<SomeObject> getSomeObjectsCollection()
    {
        List<SomeObject> someObjects = new ArrayList<>();


        for (Document doc : someObjectsCollection.find())
        {
            someObjects.add(fromDocument(doc));
        }

        return someObjects;
    }

    @Override
    public SomeObject getSomeObject(int id)
    {
        return fromDocument(someObjectsCollection.find(Filters.eq("_id", id)).first());
    }

    @Override
    public boolean updateSomeObject(SomeObject someObject)
    {
        someObjectsCollection.findOneAndUpdate(Filters.eq("_id", someObject.getId()),toDocument(someObject));
        return true;
    }

    @Override
    public boolean deleteSomeObject(int id)
    {
        someObjectsCollection.findOneAndDelete(Filters.eq("_id", id));
        return true;
    }
}

package app.data;

import app.SomeObject;

import java.util.List;

// Repository
public interface SomeObjectCrud
{
    // Create
    boolean createSomeObject(SomeObject someObject);

    // Read all
    List<SomeObject> getSomeObjects();

    // Read one by id
    SomeObject getSomeObject(int id);

    // Update
    boolean updateSomeObject(SomeObject someObject);

    // Delete
    boolean deleteSomeObject(int id);

}

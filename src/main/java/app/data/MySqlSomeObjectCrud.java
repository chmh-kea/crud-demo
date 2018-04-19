package app.data;

import app.SomeObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlSomeObjectCrud implements SomeObjectCrud
{
    private MySql db;

    private static MySqlSomeObjectCrud instance;
    public static MySqlSomeObjectCrud getInstance()
    {
        if(instance == null)
            instance = new MySqlSomeObjectCrud();
        return instance;
    }

    private MySqlSomeObjectCrud()
    {
        db = MySql.getInstance();
    }

    @Override
    public boolean createSomeObject(SomeObject someObject)
    {
        try(Connection conn = db.getConn())
        {
            PreparedStatement preparedStatementInsert = conn.prepareStatement("INSERT INTO someobjects (someString,someInt) VALUES (?, ?)");
            preparedStatementInsert.setString(1, someObject.getSomeString());
            preparedStatementInsert.setInt(2, someObject.getSomeInt());

            int res = preparedStatementInsert.executeUpdate();
            if (res > 0)
                return true;
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SomeObject> getSomeObjectsCollection()
    {
        try (Connection conn = db.getConn())
        {
            Statement statementSelectAll = conn.createStatement();
            ResultSet resultSet = statementSelectAll.executeQuery("SELECT * FROM tech.someobjects");

            List<SomeObject> someObjects = new ArrayList<>();

            while (resultSet.next())
            {
                int columnIndex = 1;

                SomeObject someObject = new SomeObject(
                        resultSet.getInt(columnIndex++),
                        resultSet.getString(columnIndex++),
                        resultSet.getInt(columnIndex));

                someObjects.add(someObject);
            }

            if (someObjects.size() > 0)
                return someObjects;
            return null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public SomeObject getSomeObject(int id)
    {
        try (Connection conn = db.getConn())
        {
            PreparedStatement statementSelectSingle = conn.prepareStatement("SELECT * FROM tech.someobjects WHERE id=?"); //
            statementSelectSingle.setInt(1, id);
            ResultSet resultSet = statementSelectSingle.executeQuery();

            if (resultSet.next())
            {
                int columnIndex = 1;

                SomeObject someObject = new SomeObject(
                        resultSet.getInt(columnIndex++),
                        resultSet.getString(columnIndex++),
                        resultSet.getInt(columnIndex));

                return someObject;
            }

            return null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateSomeObject(SomeObject someObject)
    {
        try (Connection conn = db.getConn())
        {
            PreparedStatement preparedStatementUpdate = conn.prepareStatement("UPDATE someobjects SET someString=?, someInt=? WHERE id=?"); //
            preparedStatementUpdate.setString(1, someObject.getSomeString());
            preparedStatementUpdate.setInt(2, someObject.getSomeInt());
            preparedStatementUpdate.setInt(3, someObject.getId());

            int res = preparedStatementUpdate.executeUpdate();

            if (res > 0)
                return true;
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSomeObject(int id)
    {
        try (Connection conn = db.getConn())
        {
            PreparedStatement preparedStatementDelete = conn.prepareStatement("DELETE FROM someobjects WHERE id=?"); //
            preparedStatementDelete.setInt(1, id);

            int res = preparedStatementDelete.executeUpdate();
            if (res > 0)
                return true;
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}

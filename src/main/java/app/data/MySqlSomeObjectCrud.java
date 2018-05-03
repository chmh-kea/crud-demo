package app.data;

import app.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("mySqlSomeObjectCrud")
public class MySqlSomeObjectCrud implements SomeObjectCrud
{
    @Autowired
    private MySql db;

//    private static MySqlSomeObjectCrud instance;
//    public static MySqlSomeObjectCrud getInstance()
//    {
//        if(instance == null)
//            instance = new MySqlSomeObjectCrud();
//        return instance;
//    }

    public MySqlSomeObjectCrud()
    {
        //db = MySql.getInstance();
    }

    public boolean createSomeObjecs(List<SomeObject> list)
    {
        return  true;
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
            ResultSet resultSet = statementSelectAll.executeQuery("SELECT * FROM someobjects");

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
            PreparedStatement statementSelectSingle = conn.prepareStatement("SELECT * FROM someobjects WHERE id=?"); //
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
        Connection conn = null;

        try
        {
            conn = db.getConn();

            conn.setAutoCommit(false); // Disables auto commit so transaction is only done on commit
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // ISOLATION level arbitralaly set



            PreparedStatement updateString = conn.prepareStatement("UPDATE someobjects SET someInt=? WHERE id=?"); // !!!ERROR HERE!!!
            PreparedStatement updateInt = conn.prepareStatement("UPDATE someobjects SET someInt=? WHERE id=?");//"UPDATE someobjects SET someString=?, someInt=? WHERE id=?"); //


            updateString.setString(1, someObject.getSomeString());
            updateInt.setInt(1, someObject.getSomeInt());

            updateString.setInt(2, someObject.getId());
            updateInt.setInt(2, someObject.getId());

            int resInt = updateInt.executeUpdate();
            int resString = updateString.executeUpdate();


            if (resInt+resString > 1)
            {
                conn.commit();
                return true;
            }
            else
            {
                conn.rollback();
                return false;
            }
        }
        catch (SQLException ex)
        {
            try
            {
                conn.rollback(); // Rolls back on sql exceptions
            }
            catch (SQLException ex2)
            {
                ex2.printStackTrace();
            }
            ex.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
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

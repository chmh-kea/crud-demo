package app;

public class SomeObject
{
    public static int COUNT = 0;

    private int id;
    private String someString;
    private int someInt;

    public SomeObject()
    {

    }

    public SomeObject(int id, String someString, int someInt)
    {
        this.id = id;
        this.someString = someString;
        this.someInt = someInt;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSomeString()
    {
        return someString;
    }

    public void setSomeString(String someString)
    {
        this.someString = someString;
    }

    public int getSomeInt()
    {
        return someInt;
    }

    public void setSomeInt(int someInt)
    {
        this.someInt = someInt;
    }

    @Override
    public String toString()
    {
        return "SomeObject{" +
                "id=" + id +
                ", someString='" + someString + '\'' +
                ", someInt=" + someInt +
                '}';
    }
}

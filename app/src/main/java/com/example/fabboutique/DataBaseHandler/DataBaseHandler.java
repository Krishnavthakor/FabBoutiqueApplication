package com.example.fabboutique.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.Models.User;

public class DataBaseHandler extends SQLiteOpenHelper {
    public  static final String dbName="FabBoutique";
    public  static final int version=1;

    //user table fields
    public  static final String tableName="Users";
    public  static final String userCol1="UserId";
    public  static final String userCol2="Username";
    public  static final String userCol3="Password";
    public  static final String userCol4="FirstName";
    public  static final String userCol5="LastName";
    public  static final String userCol6="TelephoneNo";
    public  static final String userCol7="UserType";
    //products category table fields
    public  static final String tableCategory="ProductsCategory";
    public  static final String categoryCol1="CategoryId";
    public  static final String categoryCol2="CategoryTitle";
    public  static final String categoryCol3="CategoryName";
    public  static final String categoryCol4="CategoryDescription";
    public  static final String categoryCol5="CategoryImage";

    public  static final String CREATE_USER_TABLE="create table IF NOT EXISTS "+tableName+"("+userCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+userCol2+" TEXT NOT NULL,"+
            userCol3+" PASSWORD NOT NULL,"+userCol4+" TEXT NOT NULL,"+userCol5+" TEXT NOT NULL,"+userCol6+" LONG NOT NULL,"+userCol7+" TEXT NOT NULL);";

    public  static final String DROP_TABLE="DROP TABLE IF EXISTS "+tableName;

    public  static final String CREATE_CATEGORY_TABLE="create table "+tableCategory+"("+categoryCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+categoryCol2+" TEXT NOT NULL,"+
            categoryCol3+" TEXT NOT NULL,"+categoryCol4+" NUMBER NOT NULL,"+categoryCol5+" TEXT NOT NULL);";

    public  static final String DROP_CATEGORY_TABLE="DROP TABLE IF EXISTS "+tableCategory;

    public static final String DROP_DB="DROP DATABASE "+dbName;

    public DataBaseHandler(@Nullable Context context) {
        super(context,dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_CATEGORY_TABLE);
        onCreate(db);
    }

    public boolean registerUser(User user) {
        //instanse of SQLLITE Database
        SQLiteDatabase db =this.getWritableDatabase();

        //getting instance of content values
        ContentValues contentValues = new ContentValues();

        //Taking content value instance and putting data into the column
        contentValues.put(userCol2,user.getUsername());
        contentValues.put(userCol3,user.getPassword());
        contentValues.put(userCol4,user.getFirstName());
        contentValues.put(userCol5,user.getLastName());
        contentValues.put(userCol6,user.getTelephoneNo());
        contentValues.put(userCol7,user.getUserType());
        //if data is not inserted following method will return -1
        long result = db.insert(tableName,null,contentValues);

        if(result == -1)
        {
            return false;}
        else{
            return true;
        }
    }

    public boolean addProductsCategory(ProductCategory category) {
        //instanse of SQLLITE Database
        SQLiteDatabase db =this.getWritableDatabase();

        //getting instance of content values
        ContentValues contentValues = new ContentValues();

        //Taking content value instance and putting data into the column
        contentValues.put(categoryCol2,category.categoryTitle);
        contentValues.put(categoryCol3,category.categoryName);
        contentValues.put(categoryCol4,category.categoryDescription);
        contentValues.put(categoryCol5,category.categoryImage);

        //if data is not inserted following method will return -1
        long result = db.insert(tableCategory,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor searchData(String username, String password)
    {
        String name=username,passwd=password;
        SQLiteDatabase db=this.getWritableDatabase();

        //we are querying the database and storing the result in cursor instance
        Cursor cursor;
        cursor = db.rawQuery("select * from "+tableName+" where "+userCol2+" = '"+name+"' and "+userCol3+"='"+passwd+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

   public Cursor getAllCategory(String categoryStr)
    {
        //Select Data By category
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor=db.rawQuery("select * from "+tableCategory+" where "+categoryCol2+" = '"+categoryStr+"'",null);
       if(cursor!=null)
       {
           cursor.moveToFirst();
       }
        return cursor;
    }

    public boolean insertCategory(ProductCategory category) {
        //instanse of SQLLITE Database
        SQLiteDatabase db = this.getWritableDatabase();

        //getting instance of content values
        ContentValues contentValues = new ContentValues();

        //Taking content value instance and putting data into the column
        contentValues.put(categoryCol2, category.getCategoryTitle());
        contentValues.put(categoryCol3, category.getCategoryName());
        contentValues.put(categoryCol4, category.getCategoryDescription());
        contentValues.put(categoryCol5, category.getCategoryImage());
        //if data is not inserted following method will return -1
        long result = db.insert(tableCategory, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getProducts(String categoryStr) {
        //Select Data By category
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor=db.rawQuery("select * from "+tableCategory+" where "+categoryCol2+" = '"+categoryStr+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
}



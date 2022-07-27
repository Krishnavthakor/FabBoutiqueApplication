package com.example.fabboutique.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.example.fabboutique.Models.ProductCategory;
import com.example.fabboutique.Models.Products;
import com.example.fabboutique.Models.User;

import java.io.ByteArrayOutputStream;

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
    //product table fields
    public  static final String tableProduct="Products";
    public  static final String productCol1="ProductId";
    public  static final String productCol2="ProductName";
    public  static final String productCol3="ProductDescription";
    public  static final String productCol4="ProductPrice";
    public  static final String productCol5="ProductQuantity";
    public  static final String productCol6="ProductImage";
    public  static final String productCol7="CategoryId";

    //wishlist table fields
    public  static final String tableWishlist="Wishlists";
    public  static final String wishlistCol1="WishlistId";
    public  static final String wishlistCol2="ProductId";
    public  static final String wishlistCol3="UserId";

    //cart table fields
    public  static final String tableCart="Cart";
    public  static final String cartCol1="CartId";
    public  static final String cartCol2="ProductId";
    public  static final String cartCol3="UserId";
    public  static final String cartCol4="Quantity";

    //address table field
    public  static final String tableAddress="UserAddress";
    public  static final String addressCol1="AddressId";
    public  static final String addressCol2="UserId";
    public  static final String addressCol3="AddressLine1";
    public  static final String addressCol4="AddressLine2";
    public  static final String addressCol5="City";
    public  static final String addressCol6="PostalCode";
    public  static final String addressCol7="Province";
    public  static final String addressCol8="Country";
    public  static final String addressCol9="MobileNo";


    //all database tables query
    public  static final String CREATE_USER_TABLE="create table IF NOT EXISTS "+tableName+"("+userCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+userCol2+" TEXT NOT NULL,"+
            userCol3+" PASSWORD NOT NULL,"+userCol4+" TEXT NOT NULL,"+userCol5+" TEXT NOT NULL,"+userCol6+" LONG NOT NULL,"+userCol7+" TEXT NOT NULL);";


    public  static final String CREATE_CATEGORY_TABLE="create table "+tableCategory+"("+categoryCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+categoryCol2+" TEXT NOT NULL,"+
            categoryCol3+" TEXT NOT NULL,"+categoryCol4+" NUMBER NOT NULL,"+categoryCol5+" BLOB NOT NULL);";



    public  static final String CREATE_PRODUCT_TABLE="create table IF NOT EXISTS "+tableProduct+"("+productCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+productCol2+" TEXT NOT NULL,"+
            productCol3+" TEXT NOT NULL,"+productCol4+" INTEGER NOT NULL,"+productCol5+" INTEGER NOT NULL,"+productCol6+" BLOB NOT NULL, "+productCol7+" INTEGER NOT NULL,FOREIGN KEY("+productCol7+") REFERENCES "+tableCategory+"("+categoryCol1+"));";


    public  static final String CREATE_ADDRESS_TABLE="create table IF NOT EXISTS "+tableAddress+"("+addressCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+addressCol2+" INTEGER NOT NULL,"+
            addressCol3+" TEXT NOT NULL,"+addressCol4+" TEXT NOT NULL,"+addressCol5+" TEXT NOT NULL,"+addressCol6+" TEXT NOT NULL, "+addressCol7+" TEXT NOT NULL,"+addressCol8+" TEXT NOT NULL,"+addressCol9+" LONG NOT NULL,FOREIGN KEY("+addressCol2+") REFERENCES "+tableName+"("+userCol1+"));";


    public  static final String CREATE_WISHLIST_TABLE="create table IF NOT EXISTS "+tableWishlist+"("+wishlistCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+wishlistCol2+" INTEGER NOT NULL,"+wishlistCol3+" INTEGER NOT NULL," +
            "FOREIGN KEY("+wishlistCol2+") REFERENCES "+tableProduct+"("+productCol1+"),FOREIGN KEY("+wishlistCol3+") REFERENCES "+tableName+"("+userCol1+"));";

    public  static final String CREATE_CART_TABLE="create table IF NOT EXISTS "+tableCart+"("+cartCol1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+cartCol2+" INTEGER NOT NULL,"+cartCol3+" INTEGER NOT NULL,"+cartCol4+" INTEGER NOT NULL," +
            "FOREIGN KEY("+cartCol2+") REFERENCES "+tableProduct+"("+productCol1+"),FOREIGN KEY("+cartCol3+") REFERENCES "+tableName+"("+userCol1+"));";

    //drop table query attributes
    public  static final String DROP_PRODUCT_TABLE="DROP TABLE IF EXISTS "+tableProduct;
    public  static final String DROP_TABLE="DROP TABLE IF EXISTS "+tableName;
    public  static final String DROP_CATEGORY_TABLE="DROP TABLE IF EXISTS "+tableCategory;
    public static final String DROP_DB="DROP DATABASE "+dbName;
    public  static final String DROP_WISHLIST_TABLE="DROP TABLE IF EXISTS "+tableWishlist;
    public  static final String DROP_CART_TABLE="DROP TABLE IF EXISTS "+tableCart;
    public  static final String DROP_ADDRESS_TABLE="DROP TABLE IF EXISTS "+tableAddress;


    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public DataBaseHandler(@Nullable Context context) {
        super(context,dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_WISHLIST_TABLE);
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_CATEGORY_TABLE);
        db.execSQL(DROP_PRODUCT_TABLE);
        db.execSQL(DROP_CART_TABLE);
        db.execSQL(DROP_WISHLIST_TABLE);
        db.execSQL(DROP_ADDRESS_TABLE);
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

        //storing image in to db
        Bitmap imageToStoreBitmap=category.getCategoryImage();
        objectByteArrayOutputStream=new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

        imageInBytes=objectByteArrayOutputStream.toByteArray();
        contentValues.put(categoryCol5,imageInBytes);

        //if data is not inserted following method will return -1
        long result = db.insert(tableCategory,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addProduct(Products product) {
        //instanse of SQLLITE Database
        SQLiteDatabase db =this.getWritableDatabase();

        //getting instance of content values
        ContentValues contentValues = new ContentValues();

        //Taking content value instance and putting data into the column
        contentValues.put(productCol2,product.productName);
        contentValues.put(productCol3,product.productDesc);
        contentValues.put(productCol4,product.productPrice);
        contentValues.put(productCol5,product.productQty);

        //storing image in to db
        Bitmap imageToStoreBitmap=product.getProductImage();
        objectByteArrayOutputStream=new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
        imageInBytes=objectByteArrayOutputStream.toByteArray();
        contentValues.put(productCol6,imageInBytes);

        contentValues.put(productCol7,product.categoryId);

        //if data is not inserted following method will return -1
        long result = db.insert(tableProduct,null,contentValues);

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
    public Cursor getCategoryByName(String categoryStr)
    {
        //Select Data By category
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor=db.rawQuery("select * from "+tableCategory+" where "+categoryCol3+" = '"+categoryStr+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getProducts(int categoryId) {
        //Select Data By category
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor=db.rawQuery("select * from "+tableProduct+" where "+categoryCol2+" = '"+categoryId+"'",null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getAllProducts() {
        //Select Data By category
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor;
        cursor=db.rawQuery("select * from "+tableProduct,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }
}



package may.internship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ChannelsActivity extends AppCompatActivity {
    RecyclerView channelRecyclerview;
    String[] categoryNameArray = {"Code for a cause","Engineering in 5 min","Electronics at your tips","Industry","Electrical concepts","Civil engineering"};
    String[] views = {"23433", "354657", "56767", "3432", "1232", "34222"};
    String[] channelPriceArray = {"200","100","150", "430", "250", "100"};
    int[] categoryImageArray = {R.drawable.code,R.drawable.mechanical,R.drawable.electronics,R.drawable.industry,R.drawable.electrical,R.drawable.civil};
    String[] channelDescriptionArray = {"Code for Cause is an initiative started by a group of like-minded people working for a similar cause. Our primary focus is to provide guidance and mentorship to students. Not only for those who lack on-campus opportunities but also for those who lack awareness about the possibilities in the field. We provide a hands-on learning experience and keep students informed about the latest trends in technology, opportunities so that they can keep up with the fast-paced digital world via following a pi-shape learning pattern ",
            "Myself Shridhar Rajendra Mankar a Engineer l YouTuber l Educational Blogger l Educator l Podcaster. \n" + "My Aim- To Make Engineering Students Life EASY.\n" + "\n" + "On 5 Minutes Engineering you can find EASIEST explanations for all below mentioned subjects in DESI HINDI", "Hi, I am Basumati Rawal. I am an electronics practical holder teacher. I am going to teach from basic to advanced level of practical electronics. Stay Tuned.", "TECHNOLOGY IN SHORT focused on helping people acquire the skills and technical knowledge they need to thrive in the digital world.\n" + "We  explain all latest trending technologies in short and simple language.",
            "Covering all sectors, The Manufacturer, is an essential resource for every boardroom, senior management, delivering thought leadership articles, regulatory updates and best practice case studies.\n" +                    "\n" + "With regular events hosted around the country The Manufacturer brings extensive industry knowledge to you in person as well as online. For more information about our events and webinars go to our website.",
            "This channel has been created to enable the students to face competition exams and university level exams.\n" + "\n" + "In today's competition world every candidates/student's has the ability to find out success, good quality of knowledge and right guidelines to achieve his/her goals in life.\n" + "\n" +
                    "Our channel provide knowledge of all subjects in electrical field in hindi. We have tried to present all the topics in very simple and lucid way. \n" + "\n" + "I would like to request my esteemed viewers to kindly send me their valuable suggestions for improvement of this channel and to notify me of any error/mistakes they may come across while going through this channel.\n" + "\n" + "Please like, subscribe and share my channel for more updates.\n" + "Contact\n" + " me:-lcitgauravjaiswal@gmail.com", "Swati Sen \n" + "Civil Engineer,  Civil Contractor, 11+ Years Experience\n" + "\n" + "This channel will provide videos related to Civil Engineering.\n" + "      \n" + "      # Civil Engineering Drawings\n" + "      # AutoCAD Designs\n" + "      # Detailed Building Drawings\n" + "      # Foundations\n" + "      # AutoCAD Civil 3D\n" + "      # Concrete Designs\n" + "      # Steel Designs\n" + "      # Structural Analysis\n" + "      # Building Estimations\n" + "      # Water Resource  & Irrigation Engineering\n" + "      # Quantity Survey \n" + "      # Land survey\n" + "      # Environmental Engineering\n" + "      # Public Health Engineering\n" + "      # Soil Mechanics\n" + "      # Concrete Technology\n" + "      # Civil Infrastructure Site oriented knowledge.\n" + "      # Transportation Engineering\n" + "\n" + "These videos will help to learn and develop construction skills. \n" + "It will give practical construction based site information.",
            "My Name is Engr Hedaetullah (B.Sc in Civil Engineering) and my other partner NJ Disha (B.Sc in Civil Engineering-Running)\n" +
                    "In this Channel, We spread our Civil Engineering Knowledge all over the world to Civil Engineering Students.\n" +
                    "\n" +
                    "Here I teach the following Topics:\n" +
                    "1. Here actually I focus to teach different types of Software that are really essential for Civil Engineers. Mainly you can learn here structural analysis and design Field perfectly.\n" +
                    "2. Secondly I focus on Civil Engineering Basic knowledge that is really essential for Civil Engineers.\n" +
                    "3. Also, I provide subject-wise Lectures.\n" +
                    " \n" +
                    "So, If you are a civil engineer then stay with us and support us so that we can do something better in our field."};
    ArrayList<CartList> channelListArrayList;
    SharedPreferences sp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);
        sp = getSharedPreferences(ConstantData.PREF,MODE_PRIVATE);

        db = openOrCreateDatabase("MayInternship",MODE_PRIVATE,null);
        String tableQuery = "CREATE TABLE IF NOT EXISTS RECORD(NAME VARCHAR(100),EMAIL VARCHAR(100),CONTACT BIGINT(10),PASSWORD VARCHAR(15),DOB VARCHAR(10),GENDER VARCHAR(6),CITY VARCHAR(50))";
        db.execSQL(tableQuery);

        String wishlistTableQuery = "CREATE TABLE IF NOT EXISTS WISHLIST(CONTACT INT(10),PRODUCTNAME VARCHAR(100))";
        db.execSQL(wishlistTableQuery);

        String cartTableQuery = "CREATE TABLE IF NOT EXISTS CART(CONTACT VARCHAR(100),ORDERID INT(10),PRODUCTNAME VARCHAR(100),QTY INT(10),PRODUCTPRICE BIGINT(100),VIEWS BIGINT(100),PRODUCTIMAGE BIGINT(100))";
        db.execSQL(cartTableQuery);


        channelRecyclerview = findViewById(R.id.channel_recyclerview);
        channelRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        channelRecyclerview.setItemAnimator(new DefaultItemAnimator());
        channelListArrayList = new ArrayList<>();
        for(int i=0;i<categoryNameArray.length;i++){
            CartList list = new CartList();
            list.setName(categoryNameArray[i]);
            list.setImage(categoryImageArray[i]);
            list.setViews(views[i]);
            list.setDescription(channelDescriptionArray[i]);

            String wishlishCheckQuery = "SELECT * FROM WISHLIST WHERE CONTACT='"+sp.getString(ConstantData.CONTACT,"")+"' AND PRODUCTNAME='"+categoryNameArray[i]+"'";
            Cursor cursor = db.rawQuery(wishlishCheckQuery,null);
            if(cursor.getCount()>0){
                list.setWishlist(true);
            }
            else{
                list.setWishlist(false);
            }

            String cartCheckQuery = "SELECT * FROM CART WHERE CONTACT='"+sp.getString(ConstantData.CONTACT,"")+"' AND PRODUCTNAME='"+categoryNameArray[i]+"' AND ORDERID='0'";
            Cursor cursorCart = db.rawQuery(cartCheckQuery,null);
            if(cursorCart.getCount()>0){
                list.setCart(true);
            }
            else{
                list.setCart(false);
            }

            channelListArrayList.add(list);
        }
        CategoryAdapter catAdapter = new CategoryAdapter(ChannelsActivity.this,channelListArrayList);
        channelRecyclerview.setAdapter(catAdapter);
    }
}
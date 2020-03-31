package com.example.contentprovider;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        getContacts();
    }


    public void getContacts()
    {
        ArrayList<String> list=new ArrayList<>();
       // Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri uri=Uri.parse("content://com.example.sqldatabase.student.contentprovider");
        String[] projection=null;
        String selection=null;
        String[] selectionArgs=null;
        String sortOrder=null;
        ContentResolver resolver=getContentResolver();
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs,sortOrder);
        while(cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String phone=cursor.getString(cursor.getColumnIndex("age"));
            Log.d(TAG, "getContacts: "+name+" / "+phone);
            list.add(name+" / "+phone);
        }
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list));
    }
}



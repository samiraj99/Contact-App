package com.ryzen.contacts;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by samhacker on 3/2/2018.
 */

public class Utilities
{
    public static final String EXTRAS_NOTE_FILENAME = "EXTRAS_NOTE_FILENAME";
    public static final String FILE_EXTENSION = ".bin";

    public static boolean saveContact(Context context,Contact contact)
    {
        String fileName = String.valueOf(contact.getFirstName())+FILE_EXTENSION;
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos= context.openFileOutput(fileName,Context.MODE_PRIVATE);
            oos= new ObjectOutputStream(fos);
            oos.writeObject(contact);
            oos.close();
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    static ArrayList<Contact> getAllSavedContact (Context context)
    {
        ArrayList<Contact> contacts=new ArrayList<>();
        File filesDir = context.getFilesDir();

        ArrayList<String> contactFiles = new ArrayList<>();

        for(String file : filesDir.list()){
            if (file.endsWith(FILE_EXTENSION))
            {
                contactFiles.add(file);
            }
        }

        FileInputStream fis;
        ObjectInputStream ois;

        for (int i=0;i<contactFiles.size();i++)
        {
            try{
                fis=context.openFileInput(contactFiles.get(i));
                ois = new ObjectInputStream(fis);

                contacts.add((Contact)ois.readObject());
                fis.close();
                ois.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
return contacts;
    }

    public static Contact getContactByFileName(Context context,String filename) {

        File file = new File(context.getFilesDir(), filename);

        if (file.exists() && !file.isDirectory())

        {
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                fis = context.openFileInput(filename);
                ois = new ObjectInputStream(fis);
                Contact contact = (Contact) ois.readObject();
                fis.close();
                ois.close();

                return contact;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }

    public static boolean deleteFile(Context context,String fileName)
    {
        File DirFiles = context.getFilesDir();
        File file = new File(DirFiles,fileName);

        if(file.exists() && !file.isDirectory())
        {
            return file.delete();
        }
        return false;
    }
    public static boolean CheckAlreadyExitsFile (Context context,String firstName)
    {
        File filesDir = context.getFilesDir();

        ArrayList<String> contactFiles = new ArrayList<>();

        for(String file : filesDir.list()){
            if (file.endsWith(FILE_EXTENSION))
            {
                contactFiles.add(file);
            }
        }

        for(int i=0;i<contactFiles.size();i++)
        {
            if(contactFiles.get(i).equals(firstName+Utilities.FILE_EXTENSION))
            {

                return true;
            }
        }
        return false;
    }

}



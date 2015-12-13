package com.example.jake.itunes;

/**
 * Created by Jake on 12/13/15.
 */
public class SaveSearch {



    /*private BufferedWriter writer;
    private BufferedReader reader;
    File file;

    private static final String TAG = "SaveSearch";

    public SaveSearch() {
        file = new File(Environment.getExternalStorageDirectory(),"search.txt");
        try {
            if(!file.exists()) {
                Log.d(TAG, "created new file");
                file.createNewFile();
            }
            else {
                Log.d(TAG, "file already exists");
                Log.d(TAG, file.getCanonicalPath());
            }
            writer = new BufferedWriter(new FileWriter(file,true));
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSearch(String search, int entity) {
        //readFourLines();
        testRead();
        String sEntity = "" + entity;


        try {
            writer.write(search);
            writer.write(",");
            writer.write(sEntity);
            writer.write("\n");



        } catch (IOException e) {
            Log.d(TAG,"couldnt write");
            e.printStackTrace();
        }

    }

    private String [] readFourLines() {
        String [] searches = new String[4];

        for(int i = 0; i < 4; i++) {
            try {
                if(reader.readLine() != null) {
                    searches[i] = reader.readLine();
                    Log.d(TAG, searches[i]);
                }
                else {
                    Log.d(TAG, "its null");
                }
            } catch (IOException e) {
                Log.d(TAG, "not reading");
                e.printStackTrace();
            }
        }
        return searches;
    }

    public void testRead() {
        try {
            int temp = reader.read();
            Log.d(TAG, "read " + temp);
            String stemp =  reader.readLine();
            if(stemp!= null) {
                Log.d(TAG, stemp);
            }
            else {
                Log.d(TAG, "stemp is null");
            }
            for(int i = 0; i < 10; i++) {
                stemp = reader.readLine();
                if (stemp != null) {
                    Log.d(TAG, stemp);
                } else {
                    Log.d(TAG, "stemp is null");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/



}

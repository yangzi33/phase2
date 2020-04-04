package features;

import event.EventManager;
import event.Searching;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWriteFeature {
    /**A class contains all feature's reader and writer*/
    public static String MEMO_DATA_FILE = "./data/memoData.txt";
    public static String TAG_DATA_FILE = "./data/tagData.txt";
    private  Searching searching;


    public ArrayList<Memo> readMemoData(EventManager manager) {
        File memoFile = new File(MEMO_DATA_FILE);
        ArrayList<Memo> memos = new ArrayList<>();
        if (memoFile.exists()) {
            try {
                Scanner scanner = new Scanner(memoFile);
                while (scanner.hasNextLine()) {
                    String curr_line = scanner.nextLine().trim();
                    String[] attributes = curr_line.split(",");
                    if (attributes.length >= 2) {
                        String memoContent = attributes[0].trim();;
                        Memo newlymemo = new Memo();
                        newlymemo.setContent(memoContent);
                        for(int i = 1; i < attributes.length; i++){
                            newlymemo.addEventtoMemo(searching.searchById(attributes[i].trim())); }
                        memos.add(newlymemo);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(curr_line); } }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace(); }
        } else {
            System.out.println("Data file not found. No memo is created."); }
        manager.memos.allMemos = memos;
        return memos; }



    public static boolean writeMemotData(ArrayList<Memo> memos) {
        try {
            File memoFile = new File(MEMO_DATA_FILE);
            if (!memoFile.exists()) {
                if (!memoFile.createNewFile()) { return  false; } }
            FileWriter writer = new FileWriter(memoFile);
            for (Memo curr_memo : memos) {
                ArrayList<String> ids = new ArrayList<>();
                for (int i = 0; i < curr_memo.getEvents().size(); i++){
                    ids.add(curr_memo.getEvents().get(i).getEventId()); }
                String newList = "";
                for(int i = 0; i < ids.size(); i++){
                    if(i == ids.size() -1){
                        newList = newList.concat(ids.get(i));
                    }else{
                        newList = ids.get(i).concat(","); } }
                String datas = "";
                datas =  datas.concat(curr_memo.getContent()).concat(",").concat(newList);
                writer.write(datas + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();File userFile = new File(MEMO_DATA_FILE); }
        return true; }

    public ArrayList<Tag> readTagData(EventManager manager) {
        ArrayList<Tag> tags = new ArrayList<>();
        File tagFile = new File(TAG_DATA_FILE);
        if (tagFile.exists()) {
            try {
                Scanner scanner = new Scanner(tagFile);
                while (scanner.hasNextLine()) {
                    String curr_line = scanner.nextLine().trim();
                    String[] attributes = curr_line.split(",");
                    if (attributes.length >= 2) {
                        String tagContent = attributes[0].trim();;
                        Tag newlytag = new Tag();
                        newlytag.setName(tagContent);
                        for(int i = 1; i < attributes.length; i++){
                            newlytag.addEvent(searching.searchById(attributes[i].trim()));
                        }
                        tags.add(newlytag);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(curr_line);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("tagData file not found. No tag is created.");
        }
        manager.tags.allTags = tags;
        return tags;
    }



    public static boolean writeTagData(ArrayList<Tag> tags) {
        try {
            File tagFile = new File(TAG_DATA_FILE);
            if (!tagFile.exists()) {
                if (!tagFile.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(tagFile);
            for (Tag curr_tag : tags) {
                ArrayList<String> ids = new ArrayList<>();
                for (int i =0; i < curr_tag.getEvents().size(); i++){
                    ids.add(curr_tag.getEvents().get(i).getEventId());
                }
                String newList = " ";
                for(int i = 0; i < ids.size(); i++){
                    if(i == ids.size() -1){
                        newList = newList.concat(ids.get(i));
                    }else{
                        newList = ids.get(i).concat(",");
                    }
                }
                String datas = "";
                datas =datas.concat(curr_tag.getName().concat(",").concat(newList));
                writer.write(datas + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();File userFile = new File(TAG_DATA_FILE);
        }
        return true;
    }
}

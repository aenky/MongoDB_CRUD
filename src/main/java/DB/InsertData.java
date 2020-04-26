package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertData
{


    public static MongoDatabase db;

    public static void main(String[] args) {

        MongoClient mongo = null;
        try {
            //Create connection with mongoDB
            mongo = MongoClients.create("mongodb://viral:1010@tesbo-shard-00-00-u89xt.mongodb.net:27017,tesbo-shard-00-01-u89xt.mongodb.net:27017,tesbo-shard-00-02-u89xt.mongodb.net:27017/test?ssl=true&replicaSet=Tesbo-shard-0&authSource=admin&retryWrites=true&w=majority");


            //mongo = MongoClients.create();
        } catch (Exception e) { e.printStackTrace(); }

        //Get UserData Database (If you have not any database with same name then it will create new Database, and if exist then connect with them)
        db = mongo.getDatabase("UserData");

        InsertData insertData=new InsertData();
        insertData.insertSingleRecord();
        insertData.insertMultipleRecord();


        System.out.println("UserData table created on DB");
    }

    public void insertSingleRecord(){
        //Get "user" table from "UserData" Database (If database has not same name of collection/Table then it will create new Table, and if exist then connect with them)
        MongoCollection<Document> table = db.getCollection("user");
        Document document = new Document();
        document.put("userName", "Ankit");
        document.put("password", "aki123");
        document.put("email", "ankit123@gmail.com");

        // Add single record on Table/Collection
        table.insertOne(document);
    }

    public void insertMultipleRecord(){
        //Get "user" table from "UserData" Database (If database has not same name of collection/Table then it will create new Table, and if exist then connect with them)
        MongoCollection<Document> table = db.getCollection("user");
        List<Document> documents =new LinkedList<>();
        Document documentOne = new Document();
        documentOne.put("userName", "Testing");
        documentOne.put("password", "test123");
        documentOne.put("email", "test123@gmail.com");

        Document documentTwo = new Document();
        documentTwo.put("userName", "Viral");
        documentTwo.put("password", "vir123");
        documentTwo.put("email", "viral123@gmail.com");

        documents.add(documentOne);
        documents.add(documentTwo);

        // Add single record on Table/Collection
        table.insertMany(documents);
    }



}

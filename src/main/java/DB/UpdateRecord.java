package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class UpdateRecord {


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


        UpdateRecord updateRecord=new UpdateRecord();
        //updateRecord.updateSingleRecord();
        updateRecord.updateMultipleRecord();


        System.out.println("UserData table created on DB");
    }

    public void updateSingleRecord(){

        //Get "user" table from "UserData" Database (If database has not same name of collection/Table then it will create new Table, and if exist then connect with them)
        MongoCollection<Document> table = db.getCollection("user");
        Document document = new Document();
        document.put("userType", "Free");

        Document updateDocument=new Document("$set", document);

        ObjectId id = null;

        try { id = new ObjectId("5ea41b16334a2657d0c26717"); }
        catch (Exception e) { }


        table.updateOne(eq("_id", id), updateDocument);
    }


    public void updateMultipleRecord(){

        //Get "user" table from "UserData" Database (If database has not same name of collection/Table then it will create new Table, and if exist then connect with them)
        MongoCollection<Document> table = db.getCollection("user");
        Document document = new Document();
        document.put("userName", "FreeUser");
        document.put("status", true);

        Document updateDocument=new Document("$set", document);


        table.updateMany(eq("userType", "Free"), updateDocument);
    }
}

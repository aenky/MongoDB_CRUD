package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class DeleteRecord {
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


        DeleteRecord deleteRecord=new DeleteRecord();
        deleteRecord.deleteSingleRecord();
        deleteRecord.deleteMultipleRecord();


        System.out.println("UserData table created on DB");
    }

    public void deleteSingleRecord(){

        //Get "user" table from "UserData" Database (If database has not same name of collection/Table then it will create new Table, and if exist then connect with them)
        MongoCollection<Document> table = db.getCollection("user");

        ObjectId id = null;

        try { id = new ObjectId("5ea41b16334a2657d0c26717"); }
        catch (Exception e) { }


        table.deleteOne(eq("_id", id));
    }


    public void deleteMultipleRecord(){

        //Get "user" table from "UserData" Database (If database has not same name of collection/Table then it will create new Table, and if exist then connect with them)
        MongoCollection<Document> table = db.getCollection("user");

        table.deleteMany(eq("userType", "Free"));
    }

}

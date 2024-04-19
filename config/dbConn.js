// const mongoose = require("mongoose");

// const DB_URL =
//   process.env.DATABASE_URI
//   // || "mongodb://localhost:27017/OAuth_Mongo";
// const dbConn = () => {
//   mongoose.connect(DB_URL, {
//     useNewUrlParser: true,
//     useUnifiedTopology: true,
//   });
// };

// module.exports = dbConn;

const { MongoClient } = require("mongodb");

const uri = process.env.DATABASE_URI;
// || "mongodb://localhost:27017/OAuth_Mongo";
const client = new MongoClient(uri, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

async function dbConn() {
  try {
    await client.connect();
    console.log("Connected to MongoDB");
  } catch (error) {
    console.error("Error connecting to MongoDB:", error);
  }
}

module.exports = dbConn;

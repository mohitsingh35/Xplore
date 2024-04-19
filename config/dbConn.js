const mongoose = require("mongoose");

const DB_URL =
  process.env.DATABASE_URI 
  // || "mongodb://localhost:27017/OAuth_Mongo";
const dbConn = () => {
  mongoose.connect(DB_URL, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });
};

module.exports = dbConn;

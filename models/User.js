const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const User = new Schema(
  {
    googleId: String,
    displayName: {
      type: String,
      required: true,
    },
    firstName: String,
    lastName: String,
    profilePicture: {
      type: String,
      default: "https://www.gravatar.com/avatar/?d=identicon",
    },
  },
  { timestamps: true }
);

module.exports = mongoose.model("User", User);

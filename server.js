if (process.env.NODE_ENV !== "production") {
  require("dotenv").config();
}
const mongoose = require("mongoose");
const express = require("express");
const app = express();
const session = require("express-session");
const passport = require("passport");
const cors = require("cors");
const path = require("path");
const oauthRoute = require("./routes/oauth");
const userRoute = require("./routes/user");
const recommendRoute = require("./routes/recommend");
const corsOptions = require("./config/corsOptions"); // cors options
const dbConn = require("./config/dbConn");
const errorHandler = require("./middleware/errorHandler");
const cookieParser = require("cookie-parser");

const PORT = process.env.PORT || 5000;

// dbConn();

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// app.use(cors(corsOptions));
app.use(cors());

require("./strategies/OAuthStrategy");

app.use(
  session({
    resave: false,
    saveUninitialized: true,
    secret: process.env.SESSION_SECRET,
    cookie: { secure: true }, // use it while deploying
  })
);
app.use(passport.initialize());
app.use(passport.session());

app.use(cookieParser(process.env.COOKIE_SECRET));

app.use("/", recommendRoute);
app.use("/auth", oauthRoute);
app.use("/user", userRoute);

app.get("/", function (req, res) {
  res.send({ status: "success" });
});

app.all("*", (req, res) => {
  res.status(404);
  if (req.accepts("html")) {
    res.sendFile(path.join(__dirname, "views", "404.html"));
  } else if (req.accepts("json")) {
    res.json({ error: "404 Not Found" });
  } else {
    res.type("txt").send("404 Not Found");
  }
});

app.use(errorHandler);

app.listen(PORT, () => console.log(`Server running on port ${PORT}`));

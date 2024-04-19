const allowedOrigins = require("../config/allowedOrigins");

const CLIENT_URL = process.env.CLIENT_URL;
const credentials = (req, res, next) => {
  const origin = req.headers.origin;
  if (allowedOrigins.includes(origin)) {
    res.header("Access-Control-Allow-Credentials", true);
    res.header("Access-Control-Allow-Origin", CLIENT_URL);
  }
  next();
};

module.exports = credentials;

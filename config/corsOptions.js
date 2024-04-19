const allowedOrigins = require("./allowedOrigins");

const corsOptions = {
  origin: (origin, callback) => {
    // !origin - in dev mode need to make this check
    if (allowedOrigins.indexOf(origin) !== -1 || !origin) {
      callback(null, true);
    } else {
      callback(new Error("Not allowed by CORS"));
    }
  },
  methods: "GET,POST,PUT,PATCH,DELETE", // allowed methods
  credentials: true, // for cookies
  optionsSuccessStatus: 200, // an OK status
};

module.exports = corsOptions;
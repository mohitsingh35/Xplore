const express = require("express");
const router = express.Router();
const { getUser } = require("../controllers/user");

router.get("/user", getUser);

module.exports = router;
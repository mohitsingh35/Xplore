const express = require("express");
const router = express.Router();
const { getRecommendations } = require("../controllers/recommend");

router.get("/recommend", getRecommendations)

module.exports = router;


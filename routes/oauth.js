const router = require("express").Router();
const passport = require("passport");
// const CLIENT_URL = "https://shimmering-biscochitos-012b6c.netlify.app";
const CLIENT_URL = process.env.CLIENT_URL;
const { loginSuccess, loginFailed, logout } = require("../controllers/user.js");

// succeed
router.get("/login/success", loginSuccess);

// failed
router.get("/login/failed", loginFailed);

// passport logout
router.get("/logout", logout);

// google auth
router.get("/google", passport.authenticate("google", { scope: ["profile"] }));

router.get(
  "/google/callback",
  passport.authenticate("google", {
    successRedirect: CLIENT_URL,
    failureRedirect: "/login/failed",
  })
);

// linkedin auth
// router.get(
//   "/linkedin",
//   passport.authenticate("linkedin", { scope: ["profile"] })
// );
// // the login callback
// router.get(
//   "/linkedin/callback",
//   passport.authenticate("linkedin", {
//     successRedirect: CLIENT_URL,
//     failureRedirect: "/login/failed",
//   })
// );

module.exports = router;

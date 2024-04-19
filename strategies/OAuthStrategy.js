// Google auth strategy
const GoogleStrategy = require("passport-google-oauth").OAuth2Strategy;
const passport = require("passport");
const GOOGLE_CLIENT_ID = process.env.GOOGLE_CLIENT_ID;
const GOOGLE_CLIENT_SECRET = process.env.GOOGLE_CLIENT_SECRET;
passport.use(
  new GoogleStrategy(
    {
      clientID: GOOGLE_CLIENT_ID,
      clientSecret: GOOGLE_CLIENT_SECRET,
      callbackURL: "/auth/google/callback",
    },
    function (accessToken, refreshToken, profile, done) {
      return done(null, profile);
    }
  )
);

// serializing and deserializing for SESSIONS
passport.serializeUser((user, done) => {
  done(null, user);
});
passport.deserializeUser((user, done) => {
  done(null, user);
});

// LinkedIn auth startegy
// const LINKEDIN_CLIENT_ID = process.env.LINKEDIN_CLIENT_ID;
// const LINKEDIN_CLIENT_SECRET = process.env.LINKEDIN_CLIENT_SECRET;
// const LinkedInStrategy = require("passport-linkedin-oauth2").Strategy;

// passport.use(
//   new LinkedInStrategy(
//     {
//       clientID: LINKEDIN_CLIENT_ID,
//       clientSecret: LINKEDIN_CLIENT_SECRET,
//       callbackURL: "/auth/linkedin/callback",
//       scope: ["r_emailaddress", "r_liteprofile"],
//     },
//     function (accessToken, refreshToken, profile, done) {
//       return done(null, profile);
//     }
//   )
// );
const User = require("../models/User.js");
// const CLIENT_URL = "https://shimmering-biscochitos-012b6c.netlify.app";
const CLIENT_URL = process.env.CLIENT_URL;

////////////////////////////Auth//////////////////////////////////
const loginSuccess = async (req, res) => {
  // extracting and saving the data to db - after manipulation
  if (req.user) {
    console.log(req.user);
    const profile = req.user;
    const preUser = await User.findOne({ googleId: profile.id });

    if (preUser) {
      preUser.displayName = profile.displayName;
      preUser.profilePicture = profile.photos[0].value;

      await preUser.save();
      res.status(200).json({
        success: true,
        message: "successful",
        user: preUser,
      });
    } else {
      // User doesn't exist, create a new user
      const userData = {
        googleId: profile.id,
        firstName: profile.name.givenName,
        lastName: profile.name.familyName,
        displayName: profile.displayName,
        profilePicture: profile.photos[0].value,
      };
      const user = await User.create(userData);
      res.status(200).json({
        success: true,
        message: "successful",
        user: user,
      });
    }
  }
};

const loginFailed = (req, res) => {
  res.status(401).json({
    success: false,
    message: "failure",
  });
};

const logout = function (req, res, next) {
  req.logout(function (err) {
    if (err) {
      return next(err);
    }
    res.redirect(CLIENT_URL);
  });
};
////////////////////////////////////////////////////
const getUser = async (req, res) => {
  try {
    const { googleId } = req.body;

    // Fetch the user by Google ID
    const user = await User.findOne({ googleId });

    if (!user) {
      return res.status(404).json({ message: "User not found" });
    }

    return res.json(user);
  } catch (error) {
    console.error("Error fetching user:", error);
    return res.status(500).json({ message: "Server error" });
  }
};

module.exports = {
  loginSuccess,
  loginFailed,
  logout,
  getUser,
};

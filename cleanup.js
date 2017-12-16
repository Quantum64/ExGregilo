var githubRemoveAllReleases = require('github-remove-all-releases');

var AUTH = {
  type: 'oauth',
  token: process.env.TOKEN
};

githubRemoveAllReleases(AUTH, 'stevemaotest', 'github-remove-all-releases-test', null);

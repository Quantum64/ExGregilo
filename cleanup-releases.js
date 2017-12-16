var githubRemoveAllReleases = require('github-remove-all-releases');

var AUTH = {
  type: 'oauth',
  token: process.env.TOKEN
};

githubRemoveAllReleases(AUTH, 'Quantum64', 'ExGregilo', (err, data) => {});

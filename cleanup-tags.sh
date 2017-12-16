git config remote.origin.url https://Quantum64:${TOKEN}@github.com/Quantum64/ExGregilo.git
TAGS=$(git tag);
git tag -d $TAGS;
for TAG in $TAGS; do git push origin :refs/tags/$TAG; done;

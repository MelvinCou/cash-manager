Sources :
- https://www.codeheroes.fr/2020/06/29/git-comment-nommer-ses-branches-et-ses-commits/
- https://grafikart.fr/tutoriels/nommage-commit-1009

## **Créer une branche ?**

`<type>/<issue_ID>-<name>`
- Your branch name should be formatted as `fix/<ISSUENUMBER>-<TITLE>` for bug fixes or `feature/<ISSUENUMBER>-<TITLE>` for features.  
  Example:  
  `fix/4221-infinite-loop`  
  `feature/4222-aws-deployement`  
  `doc/4223-RGPD-compliance`

**Les types de branche :**

    - feature: Ajout d’une nouvelle fonctionnalité;

    - bugfix: Correction d’un bug;

    - hotfix: Correction d’un bug critique;

    - chore: Nettoyage du code;

    - experiment: Expérimentation de fonctionnalités.

**Le nom de la branche** décrit succinctement le but de celle-ci. Certaines règles doivent être respectées :\

    - Le nom doit faire moins de 50 caractères;
    - Le nom doit respecter la convention kebab-case (les mots doivent être en minuscule et liés par des tirets “-“);

Le reste fait référence au **numéro du ticket** (issue)

**Quelques exemples** \

`feature/27-add-users-controller`

`hotfix/621-profile-page-error/`

`experiment/63-try-api-key`

`chore/924-remove-deprecated-method`

## **Nommer ses commits?**

**Le format**

    <type>(<portée>): <sujet>

    <description>

    <footer>

**Les types :**

- build: Système de build (example : gulp, webpack, npm)
- ci: Intégration continue (example scopes: Travis, Circle, BrowserStack, SauceLabs)
- docs: Documentation
- feat: Ajout d'une fonctionnalité
- fix: Correction de bogue
- perf: Amélioration des performances
- refactor: Changement du code qui ne change rien au fonctionnement
- style: Changement du style du code (sans changer la logique)
- test: Modification des tests

**Portée** définit quelle partie de votre librairie / application est affectée par le commit (cette information est optionnelle)

**Sujet** contient une description succinte des changements,

En utilisant l'impératif présent ("change", et non pas "changed" ou "changes")
Sans majuscule au début\
Pas de "." à la fin de la description

**Description** permet de détailler plus en profondeur les motivations derrière le changement.\
Les règles sont les mêmes que pour la partie Sujet.

**Exemple** :
  ```sh
  git commit -m 'fetaure(Sercer):Infinite loop when pressing Alt-F4

  This was caused by a missing check in the event loop
  The program now checks when the window is set to close
  
  Linked #101'
  ```


### **How to submit a pull request?**

- Format your code

- Make sure your code has a proper set of unit tests that all pass

- Once validated, merge to PR to `main` and remove the source branch with `git branch -D <branch_name>`.

### **How to update your  branch?**

Use the following commands to update your feature branch with the latest changes from `main`:

```sh
# When checked out on your feature branch
git add .     # OPTIONAL: when you have uncommitted changes
git stash     # OPTIONAL: when you have uncommitted changes
git pull --rebase origin main
# Fix conflicts if needed
git stash pop # OPTIONAL: when you have uncommitted changes
```

Additionally, you can configure Git to always rebase by default when running `git pull`:

```sh
git config --global pull.rebase true
```

---

THANKS! 💚

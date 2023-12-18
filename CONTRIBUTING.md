# How to become a contributor

## **Create a branch ?**

`<type>/<issue_ID>-<name>`

**Branch types:**

    - feature: Add a new feature;

    - bugfix: Fix a bug;

    - hotfix: Correction of a critical bug;

    - chore: Code cleanup;

    - experiment: Feature experimentation.

**The branch name** briefly describes the purpose of the branch. Certain rules must be respected :\

    - The name must be less than 50 characters long;
    - The name must respect the kebab-case convention (words must be in lower case and linked by hyphens "-");

The rest refers to the **ticket number** (issue)

**Some examples** \

`feature/27-add-users-controller`

`hotfix/621-profile-page-error/`

`experiment/63-try-api-key`

`chore/924-remove-deprecated-method`

## **Naming your commits?**

**The format**

    <type>(<port>): <subject>

    <description>

    <footer>

### **How to title commits?**

- Commit often!

- Follow [the guidelines](https://cbea.ms/git-commit/)

- Use imperative tense (avoid past tense).

- The title of the commit must be a summary of the content and not be too long (less than 50 characters).

- Prefer putting detailed information inside the commit's description.

- Put special keywords at the last line of your message (`Linked`, `Closes`, ...) to link it to an issue or a pull request.  
  See the [GitHub documentation](https://docs.github.com/en/github/managing-your-work-on-github/linking-a-pull-request-to-an-issue) for more information.

  Example:

  ```sh
  git commit -m 'feat(src): Infinite loop when pressing Alt-F4

  This was caused by a missing check in the event loop
  The program now checks when the window is set to close

  Linked #101'
  ```

**Types:**

- build: Build system (example: gulp, webpack, npm)
- ci: Continuous integration (example scopes: Travis, Circle, BrowserStack, SauceLabs)
- docs: Documentation
- feat: Added functionality
- fix: Bug fix
- perf: Performance improvement
- refactor: Code change that does not alter operation
- style: Change in code style (without changing logic)
- test: Modification of tests

**scope** defines which part of your library/application is affected by the commit (this information is optional)

**Subject** contains a brief description of the changes,

Using the present imperative ("change", not "changed" or "changes")
No capital letter at the beginning
No "." at the end of the description

**Description** allows you to go into more detail about the reasons behind the change.
The rules are the same as for the Subject section.

### **Did you find a bug? / Do you want to suggest something?**

- Ensure the bug was not already reported by searching on [Issues](https://github.com/EpitechMscProPromo2025/T-POO-700-NAN_3/issues).  
  Otherwise, create a new one. Be sure to include a **clear title and description**, as much relevant information as possible, and a **sample code** or **executable test case** demonstrating the expected behavior that does not occur.

- Use issue creation templates

### **How to submit a pull request?**

- Format your code

- Make sure your code has a proper set of unit tests that all pass

- Once validated, merge to PR to `main` and remove the source branch with `git branch -D <branch_name>`.

### **How to update your feature branch?**

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

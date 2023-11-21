# How to become a contributor

✔️ You agree to the [Contributor License Agreements](https://www.contributor-covenant.org)

✔️ Make sure your code adheres to the existing style, technologies, and coding standards recommended for the respective language/framework.

### **Did you find a bug? / Do you want to suggest something?**

- Ensure the bug was not already reported by searching on [Issues](https://github.com/EpitechMscProPromo2025/T-POO-700-NAN_3/issues).  
  Otherwise, create a new one. Be sure to include a **clear title and description**, as much relevant information as possible, and a **sample code** or **executable test case** demonstrating the expected behavior that does not occur.

- Use issue creation templates

### **Do you want to create a branch?**

- Your branch name should be formatted as `fix/<ISSUENUMBER>-<TITLE>` for bug fixes or `feature/<ISSUENUMBER>-<TITLE>` for features.  
  Example:  
  `fix/4221-infinite-loop`  
  `feature/4222-aws-deployement`  
  `doc/4223-RGPD-compliance`

### **Do you want to fix an issue?**

- Create a new branch following the above convention
- Implement your features of fixes in it.

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
  git commit -m 'Infinite loop when pressing Alt-F4

  This was caused by a missing check in the event loop
  The program now checks when the window is set to close

  Linked #101'
  ```

### **How to submit a pull request?**

- Format your code using Mix Format & Prettier

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

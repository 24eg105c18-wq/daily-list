# Daylist Frontend

A clean, Todoist-inspired task dashboard with a sidebar, Today / Upcoming / All Tasks / Completed views, project labels, priority markers, search, filtering, task CRUD, progress summary, and light/dark theme toggle. This is an original student-project interface inspired by common task-manager patterns; it does not copy Todoist branding or proprietary assets.

## Run locally
Open `index.html` in your browser. Tasks and theme are saved in browser local storage, so it works without a server.

## Connect to the Java backend (optional)
Deploy or run the Java backend first. In `index.html`, add this line immediately before `<script src="app.js"></script>` and replace the URL with your backend's URL:

```html
<script>window.DAYLIST_API_BASE = 'https://YOUR-BACKEND-DOMAIN';</script>
<script src="app.js"></script>
```

When configured, the frontend loads tasks from `/api/tasks` and sends create, update, complete and delete operations to the API. When not configured, it uses local storage.

## Deploy frontend with GitHub Pages
1. Create a GitHub repository named `daylist-frontend`.
2. Upload the files in this folder to the repository root.
3. Open **Settings → Pages**.
4. Under **Build and deployment**, select **Deploy from a branch**, choose `main` and `/ (root)`, then save.
5. GitHub will display your public site URL on that Pages settings screen.


# 🎬 Trending Movies App

A full-stack application that shows trending movies with details and favorites functionality.  
- **Backend**: Spring Boot (Java 21, Maven)  
- **Frontend**: React (Create React App, Material UI)  

---

## 📂 Project Structure
```

trending-movies-app/
├── trending-movie-api   # Spring Boot backend
├── trending-movie-ui    # React frontend
└── README.md

````

---

## 🚀 Getting Started

### 1. Prerequisites
Make sure you have installed:
- [Java 21+](https://adoptium.net/)  
- [Maven](https://maven.apache.org/install.html)  
- [Node.js + npm](https://nodejs.org/)  

---

### 2. Backend Setup (Spring Boot API)

```bash
cd trending-movie-api
mvn clean install
mvn spring-boot:run
````

* The backend runs on **[http://localhost:8080](http://localhost:8080)**
* Make sure you have your API token is added in  `trending-movie-apiapplication.properties`.

---

### 3. Frontend Setup (React UI)

```bash
cd trending-movie-ui
npm install
npm start
```

* The frontend runs on **[http://localhost:3000](http://localhost:3000)**

---

### 4. Running Both Together

Open **two seperate terminals** and run the above steps in each:


Then open [http://localhost:3000](http://localhost:3000) 🎉

---

## 🧪 Tests

### Backend

```bash
cd trending-movie-api
mvn test
```

### Frontend

```bash
cd trending-movie-ui
npm test
```

---

## 📌 Notes

* Favorites are stored in **sessionStorage**.
* Backend uses **MapStruct + Lombok** (generated classes are handled by Maven).
*Backend implements Caffeine caching to increase performance and reduce duplicate calls to the TMDB API.
* Frontend uses **Material UI (MUI)** for styling.


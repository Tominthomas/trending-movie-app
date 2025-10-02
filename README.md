
# 🎬 Trending Movies App

A full-stack application that shows trending movies with details and favorites functionality.  
- **Backend**: Spring Boot (Java 21, Maven)  
- **Frontend**: React (JavaScript, Material UI)  

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

This project requires a TMDB API token to fetch movie data. You need to set it as an environment variable before starting the backend.
You can visit this site to get setup with a free account - [TDMB API Token](https://developer.themoviedb.org/docs/getting-started)  

1. Set your TMDB API token

* Open a terminal in the `trending-movie-api` folder and run:

```
export TMDB_API_TOKEN=your_api_token_here 

```

2. Verify the variable is set

```
echo $TMDB_API_TOKEN  
```
* You should see your token printed.

3. Run the backend
mvn spring-boot:run

```
cd trending-movie-api
mvn clean install
mvn spring-boot:run
````

* The backend runs on **[http://localhost:8080](http://localhost:8080)**
* You can also manually add your API token in  `trending-movie-api.application.properties`.

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
* Backend implements Caffeine caching to increase performance and reduce duplicate calls to the TMDB API.
* Frontend uses **Material UI (MUI)** for styling.


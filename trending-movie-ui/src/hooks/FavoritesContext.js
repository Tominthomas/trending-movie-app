import React, { createContext, useContext, useState, useEffect } from "react";

const FavoritesContext = createContext();
const STORAGE_KEY = "favorites";

export const FavoritesProvider = ({ children }) => {
  const [favorites, setFavorites] = useState(() => {
    const stored = sessionStorage.getItem(STORAGE_KEY);
    return stored ? JSON.parse(stored) : [];
  });

  useEffect(() => {
    sessionStorage.setItem(STORAGE_KEY, JSON.stringify(favorites));
  }, [favorites]);

  const toggleFavorite = (movie) => {
    setFavorites((prev) => {
      const exists = prev.some((fav) => fav.id === movie.id);
      return exists
        ? prev.filter((fav) => fav.id !== movie.id)
        : [...prev, movie];
    });
  };

  const isFavorite = (id) => favorites.some((fav) => fav.id === id);

  return (
    <FavoritesContext.Provider value={{ favorites, toggleFavorite, isFavorite }}>
      {children}
    </FavoritesContext.Provider>
  );
};

export const useFavorites = () => useContext(FavoritesContext);
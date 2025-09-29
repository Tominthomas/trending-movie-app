import { Card, CardMedia, CardContent, Typography, IconButton } from '@mui/material';
import { Favorite, FavoriteBorder } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import { useFavorites } from '../hooks/useFavorites';

const MovieCard = ({ movie }) => {
  const navigate = useNavigate();
  const { toggleFavorite, isFavorite } = useFavorites();

  return (
    <Card
      sx={{
        maxWidth: 200,
        margin: 2,
        cursor: 'pointer',
        position: 'relative',
        maxHeight: 500,
        textAlign: 'center'
      }}
    >
      <CardMedia
        component="img"
        height="300"
        image={movie.posterUrl}
        alt={movie.title}
        onClick={() => navigate(`/${movie.id}`)}
      />
      <IconButton
        onClick={(e) => {
          e.stopPropagation();
          toggleFavorite(movie);
        }}
        sx={{ position: 'absolute', top: 5, right: 5, color: 'red' }}
      >
        {isFavorite(movie.id) ? <Favorite /> : <FavoriteBorder />}
      </IconButton>
      <CardContent>
        <Typography gutterBottom variant="h6">{movie.title}</Typography>
        <Typography variant="body2">⭐ {movie.voteAverage}</Typography>
      </CardContent>
    </Card>
  );
};

export default MovieCard;
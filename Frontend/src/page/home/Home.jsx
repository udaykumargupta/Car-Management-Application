import  { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { Button } from "@/components/ui/button";
import { ChevronLeftIcon, ChevronRightIcon } from "@radix-ui/react-icons";
import { fetchCars } from '@/State/MyCars/Action';
import PropTypes from 'prop-types';

const Home = () => {
  const dispatch = useDispatch();
  const { cars, loading, error } = useSelector((state) => state.cars);

  useEffect(() => {
    dispatch(fetchCars());
  }, [dispatch]);

  return (
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">My Cars</h1>

      {loading && <p>Loading...</p>}
      {error && <p>Error: {error}</p>}
      
      {!loading && !error && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {cars.map((car) => (
            <CarCard key={car.id} car={car} />
          ))}
        </div>
      )}
    </div>
  );
};


const CarCard = ({ car }) => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  const images = car.images || [];

  const handlePrevImage = () => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === 0 ? images.length - 1 : prevIndex - 1
    );
  };

  const handleNextImage = () => {
    setCurrentImageIndex((prevIndex) =>
      prevIndex === images.length - 1 ? 0 : prevIndex + 1
    );
  };

  return (
    <div className="bg-white p-4 shadow rounded">
      <h2 className="text-xl font-bold mb-2 text-black">{car.title}</h2>
      <div className="relative">
        {images.length > 0 ? (
          <img
            src={images[currentImageIndex]} // Ensure the backend URL is used here
            alt={`${car.title} image ${currentImageIndex + 1}`}
            className="w-full h-48 object-cover rounded mb-2"
          />
        ) : (
          <p>No images available</p>
        )}

        {images.length > 1 && (
          <>
            <Button
              variant="ghost"
              size="icon"
              onClick={handlePrevImage}
              className="absolute top-1/2 left-0 transform -translate-y-1/2"
            >
              <ChevronLeftIcon />
            </Button>
            <Button
              variant="ghost"
              size="icon"
              onClick={handleNextImage}
              className="absolute top-1/2 right-0 transform -translate-y-1/2"
            >
              <ChevronRightIcon />
            </Button>
          </>
        )}
      </div>
      <p className="text-gray-600 mb-2">{car.description}</p>
      <div className="flex flex-wrap gap-2">
        {car.tags?.map((tag, index) => (
          <span
            key={index}
            className="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded"
          >
            {tag}
          </span>
        ))}
      </div>
    </div>
  );
};

CarCard.propTypes = {
  car: PropTypes.shape({
    id: PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    images: PropTypes.arrayOf(PropTypes.string),
    description: PropTypes.string,
    tags: PropTypes.arrayOf(PropTypes.string),
  }).isRequired,
};
CarCard.defaultProps = {
  car: {
    images: [], 
  },
};

export default Home;

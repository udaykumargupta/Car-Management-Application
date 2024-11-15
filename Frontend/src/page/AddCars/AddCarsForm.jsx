import { useState } from "react";
import axios from "axios";
import './AddCar.css'
import PropTypes from "prop-types";
import { useSelector } from "react-redux"; // Import useSelector from react-redux

const AddCarForm = ({ onClose }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [tags, setTags] = useState("");
  const [images, setImages] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Use useSelector to access the JWT token from Redux state
  const jwt = useSelector((state) => state.auth.jwt) || localStorage.getItem("jwt");

  // Handle file input change (multiple files)
  const handleImageChange = (event) => {
    setImages(event.target.files);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setLoading(true);
    setError(null);

    const formData = new FormData();
    formData.append("title", title);
    formData.append("description", description);
    formData.append("tags", tags);

    // Append images to formData
    for (let i = 0; i < images.length; i++) {
      formData.append("images", images[i]);
    }

    try {
      const response = await axios.post("http://localhost:5454/api/cars/add", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${jwt}`, // Ensure to send the JWT in the Authorization header
        },
      });
      console.log(response); // Log response after the request
      alert("Car added successfully!");
      onClose(); // Close the form after successful submission
    } catch (error) {
      setError("An error occurred while adding the car.");
      console.log(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Add Car</h2>
        <form onSubmit={handleSubmit}>
          <div>
            <label>Title:</label>
            <input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Description:</label>
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Tags (comma separated):</label>
            <input
              type="text"
              value={tags}
              onChange={(e) => setTags(e.target.value)}
            />
          </div>
          <div>
            <label>Images:</label>
            <input type="file" multiple onChange={handleImageChange} required />
          </div>
          <button type="submit" disabled={loading}>
            {loading ? "Submitting..." : "Add Car"}
          </button>
        </form>
        {error && <p className="error">{error}</p>}
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
};

AddCarForm.propTypes = {
  onClose: PropTypes.func.isRequired, // Validate that onClose is a function and is required
};

export default AddCarForm;

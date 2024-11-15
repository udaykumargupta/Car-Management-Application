import  { useState } from 'react'
import AddCarForm from './AddCarsForm';

const AddCars = () => {
    const [showAddCarsForm, setShowAddCarsForm] = useState(false);

    const handleAddCarClick = () => {
      setShowAddCarsForm(true);
    };
  
    const handleCloseForm = () => {
      setShowAddCarsForm(false);
    };
  
    return (
      <div className="p-4">
        {/* <h1 className="text-2xl font-bold mb-4">My Cars</h1> */}
  
        {/* Button to show the form */}
        <button onClick={handleAddCarClick} className="btn btn-primary">
          Add Car
        </button>
  
        {/* Show the AddCarForm when clicked */}
        {showAddCarsForm && <AddCarForm onClose={handleCloseForm} />}
      </div>
    );
}

export default AddCars
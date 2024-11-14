import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
<<<<<<< HEAD
import { Provider } from 'react-redux'
import { store } from './State/store'
import { BrowserRouter } from 'react-router-dom'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
     <Provider store={store}>
      <App />
    </Provider>
    </BrowserRouter>
=======

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
>>>>>>> 6efc6930cd76932a9056d7757e89d81f03ae1cdf
  </StrictMode>,
)

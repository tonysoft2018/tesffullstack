import './App.css'
import { Route, Routes } from 'react-router';
import Persons from './pages/Persons';
import CreatePerson from './pages/CreatePerson';
import EditPerson from './pages/EditPerson';
import Menu from './components/Menu';

function App() {
  return (
    <>
      <Menu />
      <Routes>
        <Route path='/' element={<Persons />} />
        <Route path='/create' element={<CreatePerson />} />
        <Route path='/editar/:id' element={<EditPerson />} />
      </Routes>
    </>
  )
}

export default App

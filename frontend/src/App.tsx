import { Route, Routes } from 'react-router'
import './App.css'
import { Layout } from './pages/layout/Layout'
import { Home } from './pages/home/Home'

function App() {

  return (
    <div>
      <Routes>
        <Route element={<Layout/>}>
          <Route path='/' element={<Home />}/>
        </Route>
      </Routes>
    </div>
  )
}

export default App

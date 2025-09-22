import { Route, Routes } from 'react-router'
import './App.css'
import { Layout } from './pages/layout/Layout'
import { Home } from './pages/home/Home'
import { LoginPage } from './pages/login/LoginPage'
import { AuthCallback } from './auth/AuthCallback'

function App() {

  return (
    <div>
      <Routes>
        <Route element={<Layout/>}>
          <Route path='/' element={<Home />}/>
          <Route path='/login' element={<LoginPage/>}/>
        </Route>
        <Route>
          <Route path='/redirect' element={<AuthCallback/>}/>
        </Route>
      </Routes>
    </div>
  )
}

export default App

import { useState } from 'react'
import heroImg from './assets/hero.png'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import './App.css'
import LibraryButton from './components/LibraryButton'
import Footer from './components/Footer'
import Header from './components/LibraryHeader'
import LibraryBooks from './components/LibraryBooks'
import LibraryLogin from './components/LibraryLogin'
import Office from './components/Office'
import OfficeList from './components/OfficeList'
function App() {

  return (
    <>
      {/* <div>
        <Header />
        <main>
          <LibraryBooks />
          <LibraryButton />
        </main>
        <Footer />
      </div> */}
    
      <Office />
      <OfficeList />
      
    
    </>
  )
}

export default App

import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Registration from "./Components/Registration";
import Header from "./Components/Header";
import Profile from "./Components/Profile";
import Login from "./Components/Login";
import SearchTour from "./Components/SearchTour";
import TourDetails from "./Components/TourDetails";
import Admin from "./Components/Admin";
import {BrowserRouter, Route, Routes} from "react-router-dom";
function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <div className="App">
                <Header/>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/registration" element={<Registration/>}/>
                    <Route path="/" element={<SearchTour/>}/>
                    <Route path="/profile" element={<Profile/>}/>
                    <Route path="/admin" element={<Admin/>}/>
                    <Route path="/tours/:id" element={<TourDetails/>}/>
                </Routes>
            </div>
        </BrowserRouter>
    </div>
  );
}

export default App;
